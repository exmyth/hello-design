## 外观模式（结构型）

### 1.1　　类型
类型：结构型

### 1.2　　定义
定义：又叫门面模式，提供了一个统一的接口，用来访问子系统中的一群接口

外观模式定义了一个高层接口，让子系统更容易使用

### 1.3　　适用场景
◆子系统越来越复杂，增加外观模式提供简单调用接口

◆构建多层系统结构，利用外观对象作为每层的入口，简化层间调用

### 1.4　　优点
◆简化了调用过程，无需了解深入子系统，防止带来风险。
◆减少系统依赖、松散耦合
◆更好的划分访问层次
◆符合迪米特法则，即最少知道原则

### 1.5　　缺点
◆增加子系统、扩展子系统行为容易引入风险

◆增加子系统、扩展子系统行为不符合开闭原则


### 1.6　　相关联设计模式对比
◆外观模式和中介者模式

前者关注外界和子系统的交互，后者关注子系统内部的交互

◆外观模式和单例模式

外观模式和单例模式可以结合使用

◆外观模式和抽象工厂模式

前者可以通过后者获取子系统的实例，子系统可以经内部对外观类进行屏蔽。

总结：

应用层不关心子系统，应用层只和外观类通信，子系统只和外观类通信。

如果继续增加子系统的话，使用实体外观类的话，不符合开闭原则，如果使用抽象外观类或者外观接口的话，符合开闭原则。

### 源码解析
1.1　　源码解析1（jdk中的JDBCUtils工具类）
jdbc在springJDBC中的封装
```text
/**
     * Close the given JDBC Connection and ignore any thrown exception.
     * This is useful for typical finally blocks in manual JDBC code.
     * @param con the JDBC Connection to close (may be {@code null})
     */
    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            }
            catch (SQLException ex) {
                logger.debug("Could not close JDBC Connection", ex);
            }
            catch (Throwable ex) {
                // We don't trust the JDBC driver: It might throw RuntimeException or Error.
                logger.debug("Unexpected exception on closing JDBC Connection", ex);
            }
        }
    }

    /**
     * Close the given JDBC Statement and ignore any thrown exception.
     * This is useful for typical finally blocks in manual JDBC code.
     * @param stmt the JDBC Statement to close (may be {@code null})
     */
    public static void closeStatement(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (SQLException ex) {
                logger.trace("Could not close JDBC Statement", ex);
            }
            catch (Throwable ex) {
                // We don't trust the JDBC driver: It might throw RuntimeException or Error.
                logger.trace("Unexpected exception on closing JDBC Statement", ex);
            }
        }
    }

    /**
     * Close the given JDBC ResultSet and ignore any thrown exception.
     * This is useful for typical finally blocks in manual JDBC code.
     * @param rs the JDBC ResultSet to close (may be {@code null})
     */
    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            }
            catch (SQLException ex) {
                logger.trace("Could not close JDBC ResultSet", ex);
            }
            catch (Throwable ex) {
                // We don't trust the JDBC driver: It might throw RuntimeException or Error.
                logger.trace("Unexpected exception on closing JDBC ResultSet", ex);
            }
        }
    }
```

### 1.2　　源码解析2（mybaties应用的Configuration）
通过封装之后，我们的客户端都有这些功能，这一组接口交给客户端来访问
```text
public MetaObject newMetaObject(Object object) {
    return MetaObject.forObject(object, objectFactory, objectWrapperFactory, reflectorFactory);
  }

  public ParameterHandler newParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
    ParameterHandler parameterHandler = mappedStatement.getLang().createParameterHandler(mappedStatement, parameterObject, boundSql);
    parameterHandler = (ParameterHandler) interceptorChain.pluginAll(parameterHandler);
    return parameterHandler;
  }

  public ResultSetHandler newResultSetHandler(Executor executor, MappedStatement mappedStatement, RowBounds rowBounds, ParameterHandler parameterHandler,
      ResultHandler resultHandler, BoundSql boundSql) {
    ResultSetHandler resultSetHandler = new DefaultResultSetHandler(executor, mappedStatement, parameterHandler, resultHandler, boundSql, rowBounds);
    resultSetHandler = (ResultSetHandler) interceptorChain.pluginAll(resultSetHandler);
    return resultSetHandler;
  }
```

### 1.3　　源码解析3（在tomcat(7.0.9版本或以上)中的应用）
request.java 和RequestFacade.java 都是HttpServletRequest  的子类，request.java声明RequestFacade，获取request的时候，实际返回的是RequestFacade的对象。我们看起来是使用了HTTPRequest的类，实际上我们使用的是RequestFacade这个外观类。
[Apache Tomcat®](https://tomcat.apache.org/download-90.cgi)

RequestFacade
```text
@SuppressWarnings("deprecation")
public class RequestFacade implements HttpServletRequest {

    @Override
    public String getParameter(String name) {

        if (request == null) {
            throw new IllegalStateException(
                            sm.getString("requestFacade.nullRequest"));
        }

        if (Globals.IS_SECURITY_ENABLED){
            return AccessController.doPrivileged(
                new GetParameterPrivilegedAction(name));
        } else {
            return request.getParameter(name);
        }
    }

}
```

request.java
```text
public class Request
implements HttpServletRequest {

/**
     * The facade associated with this request.
     */
    protected RequestFacade facade = null;


    /**
     * @return the <code>ServletRequest</code> for which this object
     * is the facade.  This method must be implemented by a subclass.
     */
    public HttpServletRequest getRequest() {
        if (facade == null) {
            facade = new RequestFacade(this);
        }
        return facade;
    }

}
```

## 装饰者模式

### 1.1　　类型
结构型

### 1.2　　定义
◆定义：在不改变原有对象的基础之上，将功能附加到对象上

提供了比继承更有弹性的替代方案（扩展原有对象功能）

比如说：我买蛋糕的时候加草莓还是芒果，我烧饼加火腿，鱼豆腐还是鸡蛋

### 1.3　　应用场景
◆扩展一个类的功能或给一个类添加附加职责

◆动态的给一个对象添加功能，这些功能可以再动态的撤销

### 1.4　　优点
◆继承的有力补充，比继承灵活，不改变原有对象的情况下给一个对象扩展功能

◆通过使用不同装饰类以及这些装饰类的排列组合，可以实现不同效果

◆符合开闭原则

### 1.5　　缺点
◆会出现更多的代码，更多的类，增加程序复杂性

◆动态装饰时，多层装饰时会更复杂

### 1.6　　关联设计模式
◆装饰者模式和代理模式：

> 装饰者模式关注动态的添加方法，代理模式关注于控制对对象的访问，
> 代理模式中的代理类可以对它的客户隐藏一个对象的具体信息，通常在使用代理模式的时候常常在代理类中创建一个对象的实例，装饰者模式通常把原始对象作为一个参数传入装饰者的构造器，这是使用上的不同。

◆装饰者模式和适配器模式。包装模式（wrapper）

两者都是包装者模式，前者装饰者是原始对象的子类，后者适配器和原始对象有不同的接口

### 1.7　　其他知识点
装饰者本身也实现了继承，继承是扩展形式之一，不见得能达到弹性设计的最佳方式，

装饰者模式做的，是把类中的装饰功能从类中移出去，简化了原来被装饰的类，同时把类的核心功能和类的装饰功能区分开，还可以去除不同类中重复的装饰逻辑

### 代码演练
#### 1.1　　代码演练1（未使用装饰者模式）

需求：大妈下班卖煎饼，加一个鸡蛋加一元，一个火腿两元，现在a买一个煎饼，b买加蛋的煎饼，c买加肠加蛋的煎饼，请实现它

#### 代码演练2(使用装饰者模式)
需求变更：

现在肠和蛋随机，a  加2蛋2肠 b加1蛋2肠，。。。请用最简单的方式实现它

要求：

所谓装饰者模式，要有抽象的实体类和确定的实体类，同时要有抽象的装饰者和确定的装饰者。现在实体类是煎饼，装饰者是鸡蛋和香肠。

关联：

煎饼实体类继承煎饼抽象类，装饰者抽象类也继承煎饼抽象类，

如何创建煎饼实体类和装饰者抽象类的关系呢？答案是可以通过它们的父类组合来达到目的

实质：

自我理解，这就像是i=i+1；鸡蛋装饰类也好，香肠装饰类也好，都是把煎饼类new一次，基础上describe加一个描述，价格加一个固定值。

### 源码解析
1.1　　源码解析1（jdk1.7中的应用一）

BufferedReader
```text
/**
  *
  *  bufferedReader 继承了Reader，并把Reader组合到BufferedReader中
  */
public class BufferedReader extends Reader {

    private Reader in;

/**
     * Creates a buffering character-input stream that uses an input buffer of
     * the specified size.
     *
     * @param  in   A Reader
     * @param  sz   Input-buffer size
     *
     * @exception  IllegalArgumentException  If sz is <= 0
     */
    public BufferedReader(Reader in, int sz) {
        super(in);
        if (sz <= 0)
            throw new IllegalArgumentException("Buffer size <= 0");
        this.in = in;
        cb = new char[sz];
        nextChar = nChars = 0;
    }

}
```
Reader
```text
/**
   *   Reader是抽象类    
   */ 
public abstract class Reader implements Readable, Closeable {

）
```
1.2　　源码解析2（jdk1.7中的应用二）

装饰父类1的子类：BufferedInputStream

```text
public
class BufferedInputStream extends FilterInputStream {

//传入父类的父类
    public BufferedInputStream(InputStream in) {
        this(in, defaultBufferSize);
    }

/**
   *    实现读取流数据，起到装饰的作用
   */
private void fill() throws IOException {
        byte[] buffer = getBufIfOpen();
        if (markpos < 0)
            pos = 0;            /* no mark: throw away the buffer */
        else if (pos >= buffer.length)  /* no room left in buffer */
            if (markpos > 0) {  /* can throw away early part of the buffer */
                int sz = pos - markpos;
                System.arraycopy(buffer, markpos, buffer, 0, sz);
                pos = sz;
                markpos = 0;
            } else if (buffer.length >= marklimit) {
                markpos = -1;   /* buffer got too big, invalidate mark */
                pos = 0;        /* drop buffer contents */
            } else {            /* grow buffer */
                int nsz = pos * 2;
                if (nsz > marklimit)
                    nsz = marklimit;
                byte nbuf[] = new byte[nsz];
                System.arraycopy(buffer, 0, nbuf, 0, pos);
                if (!bufUpdater.compareAndSet(this, buffer, nbuf)) {
                    // Can't replace buf if there was an async close.
                    // Note: This would need to be changed if fill()
                    // is ever made accessible to multiple threads.
                    // But for now, the only way CAS can fail is via close.
                    // assert buf == null;
                    throw new IOException("Stream closed");
                }
                buffer = nbuf;
            }
        count = pos;
        int n = getInIfOpen().read(buffer, pos, buffer.length - pos);
        if (n > 0)
            count = n + pos;
    }

}
```

装饰父类1：FilterInputStream
```text
public
class FilterInputStream extends InputStream {
    /**
     * The input stream to be filtered.
     */
    protected volatile InputStream in;

    protected FilterInputStream(InputStream in) {
        this.in = in;
    }

}
```
被装饰类：InputStream
```text
public abstract class InputStream implements Closeable {

}
```
装饰父类2：FileInputStream
```text
public class FileInputStream extends InputStream
{
/*
 *  传入file，装饰成inputstream
*/
public FileInputStream(File file) throws FileNotFoundException {
        String name = (file != null ? file.getPath() : null);
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkRead(name);
        }
        if (name == null) {
            throw new NullPointerException();
        }
        if (file.isInvalid()) {
            throw new FileNotFoundException("Invalid file path");
        }
        fd = new FileDescriptor();
        fd.incrementAndGetUseCount();
        this.path = name;
        open(name);
    }

}
```
1.3　　源码解析3（Spring中的应用）
TransactionAwareCacheDecorator
```text
/**
   *    该类是储存缓存和事务的相关类
   */
public class TransactionAwareCacheDecorator implements Cache {
    private final Cache targetCache;

    public TransactionAwareCacheDecorator(Cache targetCache) {
        Assert.notNull(targetCache, "Target Cache must not be null");
        this.targetCache = targetCache;
    }
}
```
1.4　　源码解析4（Servlet中的应用）

spring-session SessionRepositoryRequestWrapper

1.5　　源码解析5（myBaties中的应用）delegate
```text
public class FifoCache implements Cache {

  private final Cache delegate;
  private Deque<Object> keyList;
  private int size;


  public FifoCache(Cache delegate) {
    this.delegate = delegate;
    this.keyList = new LinkedList<Object>();
    this.size = 1024;
  }
}
```
org.mybatis mybatis
```text
package org.apache.ibatis.cache.decorators

FifoCache
LoggingCache
LruCache
ScheduledCache
SerializedCache
SoftCache
SynchronizedCache
TransactionalCache
WeakCache
```














