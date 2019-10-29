## 模板方法模式
### 1.1 类型：行为型

### 1.2　　定义：

◆定义：定义了一个算法的骨架，并允许子类为一个或多个步骤提供实现

◆模板方法使得子类可以在不改变算法结构的情况下，重新定义算法的某些步骤

如：a 把冰箱门打开，b 装大象  c 把冰箱门关上

### 1.3　　适用场景：
◆各子类中公共的行为被提取出来并集中到一个公共父类中，从而避免代码重复

◆一次性实现一个算法的不变的部分，并将可变的行为留给子类来实现

比如：我开冰箱门和关冰箱门的共同部分抽取出来，用一些子类继承这些模板，具体装大象还是装老虎，由这些子类来实现。

### 1.4　　优点：
◆提高复用性（将相同代码部分放到抽象父类中）

◆提高扩展性

◆符合开闭原则

### 1.5　　缺点：
◆继承关系自身缺点，如果父类添加新的抽象方法，所有子类都要改一遍

◆类数目增加

◆增加了系统实现的复杂度

### 1.6　　模板方法扩展：
钩子方法：详细参考代码

### 1.7　　与其他设计模式关系：
模板方法和工厂方法模式：

工厂方法是模板方法的一种特殊实现。

模板方法模式和策略模式：

模板方法模式不改变算法流程的，策略方法可以改变算法流程的，并且策略方法之间是可以相互替换的。

这两中方式都有封装方法。策略方法模式的目的是使不同的算法可以相互替换，并且不影响应用层客户端的使用。

模板方法模式是针对定义一个算法的流程，将一些不太一样的具体步骤交给子类去实现。

### 1.8 源码解析
1.1　　　　源码解析1(在jdk中的使用)
AbstractList（父类）
```java
public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E> {

//get方法为抽象方法，完全交给子类去实现

abstract public E get(int index);

}
```
ArrayList（子类）
```java
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable
{
//子类来实现get方法
 public E get(int index) {
        rangeCheck(index);

        return elementData(index);
    }
}
```
同理：AbstractSet，AbstractMap同样采用了模版方法模式

#### 1.2　　　　源码解析2（在servlet中的应用）
HttpServlet
```java
public abstract class HttpServlet extends GenericServlet {
/***
   *    doget方法，dopost方法，service方法
   *     httpServlet中定义了一套模版，我们使用的时候只要继承httpServlet，并且实现doget和dopost方法就可以了
   *     注意：权限是protected，也就是只有子类才能实现这些方法
   */

//doget方法
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String protocol = req.getProtocol();
        String msg = lStrings.getString("http.method_get_not_supported");
        if (protocol.endsWith("1.1")) {
            resp.sendError(405, msg);
        } else {
            resp.sendError(400, msg);
        }

    }

//dopost方法
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String protocol = req.getProtocol();
        String msg = lStrings.getString("http.method_post_not_supported");
        if (protocol.endsWith("1.1")) {
            resp.sendError(405, msg);
        } else {
            resp.sendError(400, msg);
        }

    }
}
```
#### 1.3　　　　源码解析3（在mybaties中的应用）
BaseExecutor（父类）
```java
public abstract class BaseExecutor implements Executor {
//这是一个执行sql的类
//其中有四个方法，重点看doUpdate方法，
//它有四个子类，分别是在简单sql doupdate，复用sql doupdate，批量sql doupdate，等

   protected abstract int doUpdate(MappedStatement var1, Object var2) throws SQLException;

    protected abstract List<BatchResult> doFlushStatements(boolean var1) throws SQLException;

    protected abstract <E> List<E> doQuery(MappedStatement var1, Object var2, RowBounds var3, ResultHandler var4, BoundSql var5) throws SQLException;

    protected abstract <E> Cursor<E> doQueryCursor(MappedStatement var1, Object var2, RowBounds var3, BoundSql var4) throws SQLException;
}
```
子类BatchExecutor ：
```java
public class BatchExecutor extends BaseExecutor {
　　@Override
  public int doUpdate(MappedStatement ms, Object parameterObject) throws SQLException {
    final Configuration configuration = ms.getConfiguration();
    final StatementHandler handler = configuration.newStatementHandler(this, ms, parameterObject, RowBounds.DEFAULT, null, null);
    final BoundSql boundSql = handler.getBoundSql();
    final String sql = boundSql.getSql();
    final Statement stmt;
    if (sql.equals(currentSql) && ms.equals(currentStatement)) {
      int last = statementList.size() - 1;
      stmt = statementList.get(last);
      applyTransactionTimeout(stmt);
     handler.parameterize(stmt);//fix Issues 322
      BatchResult batchResult = batchResultList.get(last);
      batchResult.addParameterObject(parameterObject);
    } else {
      Connection connection = getConnection(ms.getStatementLog());
      stmt = handler.prepare(connection, transaction.getTimeout());
      handler.parameterize(stmt);    //fix Issues 322
      currentSql = sql;
      currentStatement = ms;
      statementList.add(stmt);
      batchResultList.add(new BatchResult(ms, sql, parameterObject));
    }
  // handler.parameterize(stmt);
    handler.batch(stmt);
    return BATCH_UPDATE_RETURN_VALUE;
  }

}
```
子类SimpleExecutor ：
```java
public class SimpleExecutor extends BaseExecutor {
  @Override
  public int doUpdate(MappedStatement ms, Object parameter) throws SQLException {
    Statement stmt = null;
    try {
      Configuration configuration = ms.getConfiguration();
      StatementHandler handler = configuration.newStatementHandler(this, ms, parameter, RowBounds.DEFAULT, null, null);
      stmt = prepareStatement(handler, ms.getStatementLog());
      return handler.update(stmt);
    } finally {
      closeStatement(stmt);
    }
  }
}
```





















