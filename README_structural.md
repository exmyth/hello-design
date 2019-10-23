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

## 适配器模式（结构型）

### 1.1　　类型：
结构型

### 1.2　　定义：
将一个类的接口转换成客户期望的另一个接口

◆使原本接口不兼容的类可以一起工作

### 1.3　　适用场景：
◆已经存在的类，它的方法和需求不匹配时（方法结果相同或相似）

◆不是软件设计阶段考虑的设计模式，是随着软件维护，由于不同产品、不同厂家造成功能类似而接口不相同情况下的解决方案，是软件维护阶段需要考虑的事情

### 1.4　　优点：
◆能提高类的透明性和复用，现有的类复用但不需要改变（解决了现有类和目标类不匹配的问题）

◆目标类和适配器类解耦，提高程序扩展性（不太明白这块意思，代码见）

◆符合开闭原则

### 1.5　　缺点：
◆适配器编写过程需要全面考虑，可能会增加系统的复杂性

◆增加系统代码可读的难度

### 1.6　　扩展:
◆对象适配器（符合组合复用原则，并且使用委托机制）

◆类适配器（通过类继承实现）

### 1.7　　和其他设计模式的比较
适配器模式和外观模式：

它们都是现有类现存系统的封装，前者复用原有的接口，后者定义了新的接口，

前者使原有的两个接口协同工作，后者在现有的系统中提供一个更为方便的访问入口，

适配力度不同，后者适配整个子系统

类适配器和对象适配器最大的区别

类适配器通过继承关系达到适配的目的，而对象适配器通过组合达到适配目的。

### 代码演练
#### 2.1　　代码演练1（类适配器模式）

需求相关：a类和b类，想用b类或者b类的子类实现a类的方法

设计分析：

适配器类c继承了被适配器类a并且实现了目标类b的接口，通过适配器类c把被适配者a的方法适配给了目标类b；

适配器类c是被适配者a的子类，通过调用父类的方法实现了目标类b

#### 2.1　　代码演练2（对象适配模式）
需求：a类和b类，想用b类或者b类的子类实现a类的方法

设计分析：通过组合达到目的

关联关系：只有 适配类发生了变化，其他类都不变。

#### 2.3　　代码演练3（具体应用场景）
需求：手机电源适配器，把220V交流电转化为5V直流电

### 源码解析
#### 1.1　　源码解析1（在jdk中的应用）
xmlAdapter（此类是用于适配xml的一个类，是处理xml序列化和反序列化的一个类）
```text
public abstract class XmlAdapter<ValueType,BoundType> {

    /**
     * Do-nothing constructor for the derived classes.
     */
    protected XmlAdapter() {}

    /**
     *  处理反序列化*/
    public abstract BoundType unmarshal(ValueType v) throws Exception;

    /**
      * 处理序列化*/
    public abstract ValueType marshal(BoundType v) throws Exception;
}
```
对于xml序列化的时候，我们时间可以写一个date类，可以继承xmlAdapter抽象类，实现它的序列化和反序列化方法。

#### 1.2　　源码解析2（Spring中的通知管理）
功能描述：

通知适配器将通知类适配成各种类型的通知，如：前置通知，后置通知等等（关于通知的课程可以详细查看我的spring笔记）

通知适配器接口：
```text
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.springframework.aop.framework.adapter;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.Advisor;

public interface AdvisorAdapter {
    boolean supportsAdvice(Advice var1);

    MethodInterceptor getInterceptor(Advisor var1);
}
```
前置通知适配器实现类：
```text
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.springframework.aop.framework.adapter;

import java.io.Serializable;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.aop.MethodBeforeAdvice;

class MethodBeforeAdviceAdapter implements AdvisorAdapter, Serializable {
    MethodBeforeAdviceAdapter() {
    }

    public boolean supportsAdvice(Advice advice) {
        return advice instanceof MethodBeforeAdvice;
    }

    public MethodInterceptor getInterceptor(Advisor advisor) {
        MethodBeforeAdvice advice = (MethodBeforeAdvice)advisor.getAdvice();
        return new MethodBeforeAdviceInterceptor(advice);
    }
}
```
advisor组合得到通知类：
```java
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.springframework.aop;

import org.aopalliance.aop.Advice;

public interface Advisor {
    Advice getAdvice();

    boolean isPerInstance();
}
```
MethodBeforeAdvice继承超类(隔了好几层继承实现)：
```java
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.aopalliance.intercept;

import org.aopalliance.aop.Advice;

public interface Interceptor extends Advice {
}
```
#### 1.3　　源码解析3(SpringMVC中的应用)
```java
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.springframework.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HandlerAdapter {
    boolean supports(Object var1);

    ModelAndView handle(HttpServletRequest var1, HttpServletResponse var2, Object var3) throws Exception;

    long getLastModified(HttpServletRequest var1, Object var2);
}
```

适配器类：
```java
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.springframework.web.servlet.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;

public class SimpleControllerHandlerAdapter implements HandlerAdapter {
    public SimpleControllerHandlerAdapter() {
    }
    /**  如果对应的handler有确切的Controller并支持的话，返回true，否则，返回false
　　　*/
    public boolean supports(Object handler) {
        return handler instanceof Controller;
    }

    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return ((Controller)handler).handleRequest(request, response);
    }

    public long getLastModified(HttpServletRequest request, Object handler) {
        return handler instanceof LastModified ? ((LastModified)handler).getLastModified(request) : -1L;
    }
}
```
DispatcherServlet(通过handlermapper找到对应的handler，适配器中的client)
```text
protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpServletRequest processedRequest = request;
        HandlerExecutionChain mappedHandler = null;
        boolean multipartRequestParsed = false;
        WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(request);

        try {
            try {
                ModelAndView mv = null;
                Exception dispatchException = null;

                try {
                    processedRequest = this.checkMultipart(request);
                    multipartRequestParsed = processedRequest != request;
                    mappedHandler = this.getHandler(processedRequest);
                    if (mappedHandler == null || mappedHandler.getHandler() == null) {
                        this.noHandlerFound(processedRequest, response);
                        return;
                    }

                    HandlerAdapter ha = this.getHandlerAdapter(mappedHandler.getHandler());
                    String method = request.getMethod();
                    boolean isGet = "GET".equals(method);
                    if (isGet || "HEAD".equals(method)) {
                        long lastModified = ha.getLastModified(request, mappedHandler.getHandler());
                        if (this.logger.isDebugEnabled()) {
                            this.logger.debug("Last-Modified value for [" + getRequestUri(request) + "] is: " + lastModified);
                        }

                        if ((new ServletWebRequest(request, response)).checkNotModified(lastModified) && isGet) {
                            return;
                        }
                    }

                    if (!mappedHandler.applyPreHandle(processedRequest, response)) {
                        return;
                    }

                    try {
　　　　　　　　　　　　//通过适配器handle方法，返回对应的model&View对象
                        mv = ha.handle(processedRequest, response, mappedHandler.getHandler());
                    } finally {
                        if (asyncManager.isConcurrentHandlingStarted()) {
                            return;
                        }

                    }

                    this.applyDefaultViewName(request, mv);
                    mappedHandler.applyPostHandle(processedRequest, response, mv);
                } catch (Exception var27) {
                    dispatchException = var27;
                }

                this.processDispatchResult(processedRequest, response, mappedHandler, mv, dispatchException);
            } catch (Exception var28) {
                this.triggerAfterCompletion(processedRequest, response, mappedHandler, var28);
            } catch (Error var29) {
                this.triggerAfterCompletionWithError(processedRequest, response, mappedHandler, var29);
            }

        } finally {
            if (asyncManager.isConcurrentHandlingStarted()) {
                mappedHandler.applyAfterConcurrentHandlingStarted(processedRequest, response);
                return;
            } else {
                if (multipartRequestParsed) {
                    this.cleanupMultipart(processedRequest);
                }

            }
        }
    }
```
controller接口：
```java
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.springframework.web.servlet.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

public interface Controller {
    ModelAndView handleRequest(HttpServletRequest var1, HttpServletResponse var2) throws Exception;
}
```

## 享元模式
### 1.1　　类型：结构型

### 1.2　　定义：
◆定义：提供了减少对象数量从而改善应用所需的对象结构的方式

◆运用共享技术有效地支持大量细粒度的对象

（可能对于内存溢出类型的问题解决有效）（池子）

### 1.3　　应用场景：
◆ 常常应用于系统底层的开发，以便解决系统的性能问题。

（系统中如果有大量的对象，可能会造成内存溢出，我们可以把共同的部分抽象出来，有相同的业务请求，则返回在内存中的已有对象，避免重新创建。）

◆ 系统有大量相似对象、需要缓冲池的场景。

（某个对象的复用度越高，越倾向于使用享元模式）

### 1.4　　优点：
◆减少对象的创建，降低内存中对象的数量，降低系统的内存，提高效率

◆减少内存之外的其他资源占用

> new对象需要时间，当我们直接从池子中取对象的时候，对象不用创建，节省了时间，特别是当对象并发或者使用率比较高的时候，提高了效率

> 文件句柄和窗口句柄是有一定的限制的，当同一对象被特别多的时候，往往可能导致句柄达到极限而导致崩溃）

### 1.5　　缺点：
◆关注内/外部状态、关注线程安全问题

我们使用共享模式的时候，大都是使用hashMap，不会用HashTable（用hashTable会由于同步锁造成效率过低（特别是应用在报考系统3天内登录多少人，某一天微博大事件情况））

◆使系统、程序的逻辑复杂化

外部状态不应该随着内部状态的变化而变化

### 1.6　　扩展：
内部状态： 在享元模式内部并且不会随着环境改变而改变的共享部分；无论外部环境如何变化，我都不变，并且该状态在享元模式内部。对象属性

外部状态： 随着环境改变而改变的就是外部状态，这种状态记录在享元模式的外部。方法参数

### 1.7　　和其他设计模式比较：
享元模式和代理模式：代理模式是代理一个类，如果生成这个代理类花的资源和时间比较多，可以使用享元模式处理这个类的速度。

享元模式和单例模式：容器单例是两种方式的一种结合。享元模式是一种复用对象的思想

### 1.8 源码解析

#### 1.1 源码解析1（jdk中的应用）
```text
public final class Integer extends Number implements Comparable<Integer> {

 /**
     * Returns an {@code Integer} instance representing the specified
     * {@code int} value.  If a new {@code Integer} instance is not
     * required, this method should generally be used in preference to
     * the constructor {@link #Integer(int)}, as this method is likely
     * to yield significantly better space and time performance by
     * caching frequently requested values.
     *
     * This method will always cache values in the range -128 to 127,
     * inclusive, and may cache other values outside of this range.
     *　如果传入的数值在缓存的-127和128之间，那么都会在cache中，否则的话，会new出新的对象，这也是为什么100==100为true，1000==1000为false
     *  test中a，b，c，d都执行了该方法，一共执行了4次   。
     * @param  i an {@code int} value.
     * @return an {@code Integer} instance representing {@code i}.
     * @since  1.5
     */
    public static Integer valueOf(int i) {
        assert IntegerCache.high >= 127;
        if (i >= IntegerCache.low && i <= IntegerCache.high)
            return IntegerCache.cache[i + (-IntegerCache.low)];
        return new Integer(i);
    }


/**
     * Cache to support the object identity semantics of autoboxing for values between
     * -128 and 127 (inclusive) as required by JLS.
     *
     * The cache is initialized on first usage.  The size of the cache
     * may be controlled by the -XX:AutoBoxCacheMax=<size> option.
     * During VM initialization, java.lang.Integer.IntegerCache.high property
     * may be set and saved in the private system properties in the
     * sun.misc.VM class.
     */

    private static class IntegerCache {
        static final int low = -128;
        static final int high;
        static final Integer cache[];

        static {
            // high value may be configured by property
            int h = 127;
            String integerCacheHighPropValue =
                sun.misc.VM.getSavedProperty("java.lang.Integer.IntegerCache.high");
            if (integerCacheHighPropValue != null) {
                int i = parseInt(integerCacheHighPropValue);
                i = Math.max(i, 127);
                // Maximum array size is Integer.MAX_VALUE
                h = Math.min(i, Integer.MAX_VALUE - (-low) -1);
            }
            high = h;

            cache = new Integer[(high - low) + 1];
            int j = low;
            for(int k = 0; k < cache.length; k++)
                cache[k] = new Integer(j++);
        }

        private IntegerCache() {}
    }
}
```
#### 1.2 源码解析2（tomcat中的应用）
父类（GenericObjectPoolConfig）：添加了一些默认的配置
```java
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.apache.commons.pool2.impl;

public class GenericObjectPoolConfig extends BaseObjectPoolConfig {
    public static final int DEFAULT_MAX_TOTAL = 8;
    public static final int DEFAULT_MAX_IDLE = 8;
    public static final int DEFAULT_MIN_IDLE = 0;
    private int maxTotal = 8;
    private int maxIdle = 8;
    private int minIdle = 0;

    public GenericObjectPoolConfig() {
    }

    public int getMaxTotal() {
        return this.maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxIdle() {
        return this.maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMinIdle() {
        return this.minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public GenericObjectPoolConfig clone() {
        try {
            return (GenericObjectPoolConfig)super.clone();
        } catch (CloneNotSupportedException var2) {
            throw new AssertionError();
        }
    }
}
```
子类（GenericKeyedObjectPoolConfig）（选2版本）：

通过object的双端队列来保存对象池的对象，重点看p是怎么给值的。
```text
/**
   *     借
   */
 public T borrowObject(K key, long borrowMaxWaitMillis) throws Exception {
        this.assertOpen();
        PooledObject<T> p = null;
        boolean blockWhenExhausted = this.getBlockWhenExhausted();
        long waitTime = 0L;
        GenericKeyedObjectPool.ObjectDeque objectDeque = this.register(key);

        try {
            while(p == null) {
                boolean create = false;
                if (blockWhenExhausted) {
                    p = (PooledObject)objectDeque.getIdleObjects().pollFirst();
                    if (p == null) {
                        create = true;
                        p = this.create(key);
                    }

                    if (p == null) {
                        if (borrowMaxWaitMillis < 0L) {
                            p = (PooledObject)objectDeque.getIdleObjects().takeFirst();
                        } else {
                            waitTime = System.currentTimeMillis();
                            p = (PooledObject)objectDeque.getIdleObjects().pollFirst(borrowMaxWaitMillis, TimeUnit.MILLISECONDS);
                            waitTime = System.currentTimeMillis() - waitTime;
                        }
                    }

                    if (p == null) {
                        throw new NoSuchElementException("Timeout waiting for idle object");
                    }

                    if (!p.allocate()) {
                        p = null;
                    }
                } else {
                    p = (PooledObject)objectDeque.getIdleObjects().pollFirst();
                    if (p == null) {
                        create = true;
                        p = this.create(key);
                    }

                    if (p == null) {
                        throw new NoSuchElementException("Pool exhausted");
                    }

                    if (!p.allocate()) {
                        p = null;
                    }
                }

                if (p != null) {
                    try {
                        this.factory.activateObject(key, p);
                    } catch (Exception var22) {
                        try {
                            this.destroy(key, p, true);
                        } catch (Exception var21) {
                            ;
                        }

                        p = null;
                        if (create) {
                            NoSuchElementException nsee = new NoSuchElementException("Unable to activate object");
                            nsee.initCause(var22);
                            throw nsee;
                        }
                    }

                    if (p != null && this.getTestOnBorrow()) {
                        boolean validate = false;
                        Throwable validationThrowable = null;

                        try {
                            validate = this.factory.validateObject(key, p);
                        } catch (Throwable var20) {
                            PoolUtils.checkRethrow(var20);
                            validationThrowable = var20;
                        }

                        if (!validate) {
                            try {
                                this.destroy(key, p, true);
                                this.destroyedByBorrowValidationCount.incrementAndGet();
                            } catch (Exception var19) {
                                ;
                            }

                            p = null;
                            if (create) {
                                NoSuchElementException nsee = new NoSuchElementException("Unable to validate object");
                                nsee.initCause(validationThrowable);
                                throw nsee;
                            }
                        }
                    }
                }
            }
        } finally {
            this.deregister(key);
        }

        this.updateStatsBorrow(p, waitTime);
        return p.getObject();
    }


/**
   *     还
   */
  public void returnObject(K key, T obj) {
        GenericKeyedObjectPool<K, T>.ObjectDeque<T> objectDeque = (GenericKeyedObjectPool.ObjectDeque)this.poolMap.get(key);
        PooledObject<T> p = (PooledObject)objectDeque.getAllObjects().get(obj);
        if (p == null) {
            throw new IllegalStateException("Returned object not currently part of this pool");
        } else {
            long activeTime = p.getActiveTimeMillis();
            if (this.getTestOnReturn() && !this.factory.validateObject(key, p)) {
                try {
                    this.destroy(key, p, true);
                } catch (Exception var11) {
                    this.swallowException(var11);
                }

                this.updateStatsReturn(activeTime);
            } else {
                try {
                    this.factory.passivateObject(key, p);
                } catch (Exception var13) {
                    this.swallowException(var13);

                    try {
                        this.destroy(key, p, true);
                    } catch (Exception var10) {
                        this.swallowException(var10);
                    }

                    this.updateStatsReturn(activeTime);
                    return;
                }

                if (!p.deallocate()) {
                    throw new IllegalStateException("Object has already been retured to this pool");
                } else {
                    int maxIdle = this.getMaxIdlePerKey();
                    LinkedBlockingDeque<PooledObject<T>> idleObjects = objectDeque.getIdleObjects();
                    if (this.isClosed() || maxIdle > -1 && maxIdle <= idleObjects.size()) {
                        try {
                            this.destroy(key, p, true);
                        } catch (Exception var12) {
                            this.swallowException(var12);
                        }
                    } else if (this.getLifo()) {
                        idleObjects.addFirst(p);
                    } else {
                        idleObjects.addLast(p);
                    }

                    if (this.hasBorrowWaiters()) {
                        this.reuseCapacity();
                    }

                    this.updateStatsReturn(activeTime);
                }
            }
        }
    }
```
工厂类：
```java
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.apache.commons.pool2;

public interface KeyedPooledObjectFactory<K, V> {
   //创建对象方法
    PooledObject<V> makeObject(K var1) throws Exception;

  //销毁对象方法
    void destroyObject(K var1, PooledObject<V> var2) throws Exception;

    //验证对象方法
    boolean validateObject(K var1, PooledObject<V> var2);
    //激活对象方法，用的时候激活
    void activateObject(K var1, PooledObject<V> var2) throws Exception;
    //钝化对象方法，不用的钝化
    void passivateObject(K var1, PooledObject<V> var2) throws Exception;
}
```
## 组合模式
### 1.1　　类型：
结构型

### 1.2　　定义：
◆定义：将对象组合成树形结构以表示”部分-整体”的层次结构
◆组合模式使客户端对单个对象和组合对象保持一致的方式处理

### 1.3　　适用场景：
◆希望客户端可以忽略组合对象与单个对象的差异时

◆处理一个树形结构时

### 1.4　　优点：
◆清楚地定义分层次的复杂对象，表示对象的全部或部分层次

◆让客户端忽略了层次的差异，方便对整个层次结构进行控制

◆简化客户端代码

◆符合开闭原则

### 1.5　　缺点：
◆限制类型时会较为复杂

◆使设计变得更加抽象

### 1.6　　与其他模式的交互
◆组合模式和访问者模式

◆可以用访问模式访问组合模式的递归结构

### 源码解析

#### 1.1　　jdk源码解析之Container

```java
//实现窗口菜单，属于编写cs软件结构时所使用的
//该方法将相同类型的接口类型或者抽象类类型转化为树状结构
public class Container extends Component { 
/**
     * Appends the specified component to the end of this container.
     * This is a convenience method for {@link #addImpl}.
     * <p>
     * This method changes layout-related information, and therefore,
     * invalidates the component hierarchy. If the container has already been
     * displayed, the hierarchy must be validated thereafter in order to
     * display the added component.
     *
     * @param     comp   the component to be added
     * @exception NullPointerException if {@code comp} is {@code null}
     * @see #addImpl
     * @see #invalidate
     * @see #validate
     * @see javax.swing.JComponent#revalidate()
     * @return    the component argument
     */
    public Component add(Component comp) {
        addImpl(comp, null, -1);
        return comp;
    }
}
```

#### 1.2　　jdk源码解析之HashMap
```java
public class HashMap<K,V>
    extends AbstractMap<K,V>
    implements Map<K,V>, Cloneable, Serializable
{

 /**
     * Copies all of the mappings from the specified map to this map.
     * These mappings will replace any mappings that this map had for
     * any of the keys currently in the specified map.
     *
     * @param m mappings to be stored in this map
     * @throws NullPointerException if the specified map is null
     */
    public void putAll(Map<? extends K, ? extends V> m) {
        int numKeysToBeAdded = m.size();
        if (numKeysToBeAdded == 0)
            return;

        if (table == EMPTY_TABLE) {
            inflateTable((int) Math.max(numKeysToBeAdded * loadFactor, threshold));
        }

        /*
         * Expand the map if the map if the number of mappings to be added
         * is greater than or equal to threshold.  This is conservative; the
         * obvious condition is (m.size() + size) >= threshold, but this
         * condition could result in a map with twice the appropriate capacity,
         * if the keys to be added overlap with the keys already in this map.
         * By using the conservative calculation, we subject ourself
         * to at most one extra resize.
         */
        if (numKeysToBeAdded > threshold) {
            int targetCapacity = (int)(numKeysToBeAdded / loadFactor + 1);
            if (targetCapacity > MAXIMUM_CAPACITY)
                targetCapacity = MAXIMUM_CAPACITY;
            int newCapacity = table.length;
            while (newCapacity < targetCapacity)
                newCapacity <<= 1;
            if (newCapacity > table.length)
                resize(newCapacity);
        }

        for (Map.Entry<? extends K, ? extends V> e : m.entrySet())
            put(e.getKey(), e.getValue());
    }
}
```

#### 1.3　　jdk源码解析之ArrayList
```java
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable
{
 public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);  // Increments modCount
        System.arraycopy(a, 0, elementData, size, numNew);
        size += numNew;
        return numNew != 0;
    }

}
```
```java
public interface List<E> extends Collection<E> {

}
```
#### 1.4　　MyBaties源码解析之SqlNode
SqlNode接口：
```java
package org.apache.ibatis.scripting.xmltags;

public interface SqlNode {
    boolean apply(DynamicContext var1);
}
```
MixedSqlNode实现类：
```java
/**
 *    Copyright 2009-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.scripting.xmltags;

import java.util.List;

/**
 * @author Clinton Begin
 */
public class MixedSqlNode implements SqlNode {
  private List<SqlNode> contents;

  public MixedSqlNode(List<SqlNode> contents) {
    this.contents = contents;
  }

  @Override
  public boolean apply(DynamicContext context) {
    for (SqlNode sqlNode : contents) {
      sqlNode.apply(context);//此处和14-2的print是异曲同工的
    }
    return true;
  }
}
```
WhereSqlNode实现类（引入apply方法）：
```java
/**
 *    Copyright 2009-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.scripting.xmltags;

import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.session.Configuration;

/**
 * @author Clinton Begin
 */
public class WhereSqlNode extends TrimSqlNode {

  private static List<String> prefixList = Arrays.asList("AND ","OR ","AND\n", "OR\n", "AND\r", "OR\r", "AND\t", "OR\t");

  public WhereSqlNode(Configuration configuration, SqlNode contents) {
    super(configuration, contents, "WHERE", prefixList, null, null);
  }

}
```
TrimSqlNode（调到apply方法）
````java
public class TrimSqlNode implements SqlNode {

public TrimSqlNode(Configuration configuration, SqlNode contents, String prefix, String prefixesToOverride, String suffix, String suffixesToOverride) {
    this(configuration, contents, prefix, parseOverrides(prefixesToOverride), suffix, parseOverrides(suffixesToOverride));
  }

protected TrimSqlNode(Configuration configuration, SqlNode contents, String prefix, List<String> prefixesToOverride, String suffix, List<String> suffixesToOverride) {
    this.contents = contents;
    this.prefix = prefix;
    this.prefixesToOverride = prefixesToOverride;
    this.suffix = suffix;
    this.suffixesToOverride = suffixesToOverride;
    this.configuration = configuration;
  }

  @Override
  public boolean apply(DynamicContext context) {
    FilteredDynamicContext filteredDynamicContext = new FilteredDynamicContext(context);
    boolean result = contents.apply(filteredDynamicContext);
    filteredDynamicContext.applyAll();
    return result;
  }

public void applyAll() {
  sqlBuffer = new StringBuilder(sqlBuffer.toString().trim());
  String trimmedUppercaseSql = sqlBuffer.toString().toUpperCase(Locale.ENGLISH);
  if (trimmedUppercaseSql.length() > 0) {
    applyPrefix(sqlBuffer, trimmedUppercaseSql);
    applySuffix(sqlBuffer, trimmedUppercaseSql);
  }
  delegate.appendSql(sqlBuffer.toString());
}
}
````



