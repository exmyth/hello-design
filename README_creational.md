# hello-design

The Way To Architect

## 简单工厂

定义：由一个工厂对象决定创建出哪一种产品类的实例。

简单工厂模式严格意义上说并不是一种设计模式，它是一种编码上的风格和习惯。

场景：工厂类负责创建的对象比较少；客户端只知道传入工厂类的参数，对于如何创建对象不关心。

优点：只需要传进去一个参数，就可以获取需要的对象，无需知道创建细节；规范管理对象，想查看对象的时候，直接在工厂类查看即可。

缺点：工厂类的职责过重，增加新的产品，需要修改工厂类的判断逻辑，违背开闭原则；无法形成基于继承的等级结构。

Calendar
```text
/**
     * Gets a calendar using the specified time zone and default locale.
     * The <code>Calendar</code> returned is based on the current time
     * in the given time zone with the default locale. 
     *
     * @param zone the time zone to use
     * @return a Calendar.
     */
    public static Calendar getInstance(TimeZone zone)
    {
        return createCalendar(zone, Locale.getDefault());
    }

private static Calendar createCalendar(TimeZone zone, Locale aLocale)
    {
    // If the specified locale is a Thai locale, returns a BuddhistCalendar
    // instance.
    if ("th".equals(aLocale.getLanguage())
        && ("TH".equals(aLocale.getCountry()))) {
        return new sun.util.BuddhistCalendar(zone, aLocale);
    } else if ("JP".equals(aLocale.getVariant())
           && "JP".equals(aLocale.getCountry())
           && "ja".equals(aLocale.getLanguage())) {
        return new JapaneseImperialCalendar(zone, aLocale);
    }        

    // else create the default calendar
        return new GregorianCalendar(zone, aLocale);    
    ｝
```

DriverManager
```text
public static Connection getConnection(String url, 
    java.util.Properties info) throws SQLException {
  
        // Gets the classloader of the code that called this method, may 
    // be null.
    ClassLoader callerCL = DriverManager.getCallerClassLoader();

        return (getConnection(url, info, callerCL));
    }

//  Worker method called by the public getConnection() methods.
    private static Connection getConnection(
    String url, java.util.Properties info, ClassLoader callerCL) throws SQLException {
    java.util.Vector drivers = null;
        /*
     * When callerCl is null, we should check the application's
     * (which is invoking this class indirectly)
     * classloader, so that the JDBC driver class outside rt.jar
     * can be loaded from here.
     */
    synchronized(DriverManager.class) {     
      // synchronize loading of the correct classloader.
      if(callerCL == null) {
          callerCL = Thread.currentThread().getContextClassLoader();
       }    
    } 
     
    if(url == null) {
        throw new SQLException("The url cannot be null", "08001");
    }
    
    println("DriverManager.getConnection(\"" + url + "\")");
    
    if (!initialized) {
        initialize();
    }

    synchronized (DriverManager.class){ 
            // use the readcopy of drivers
        drivers = readDrivers;  
        }

    // Walk through the loaded drivers attempting to make a connection.
    // Remember the first exception that gets raised so we can reraise it.
    SQLException reason = null;
    for (int i = 0; i < drivers.size(); i++) {
        DriverInfo di = (DriverInfo)drivers.elementAt(i);
      
        // If the caller does not have permission to load the driver then 
        // skip it.
        if ( getCallerClass(callerCL, di.driverClassName ) != di.driverClass ) {
        println("    skipping: " + di);
        continue;
        }
        try {
        println("    trying " + di);
        Connection result = di.driver.connect(url, info);
        if (result != null) {
            // Success!
            println("getConnection returning " + di);
            return (result);
        }
        } catch (SQLException ex) {
        if (reason == null) {
            reason = ex;
        }
        }
    }
    
    // if we got here nobody could connect.
    if (reason != null)    {
        println("getConnection failed: " + reason);
        throw reason;
    }
    
    println("getConnection: no suitable driver found for "+ url);
    throw new SQLException("No suitable driver found for "+ url, "08001");
    }
```

Logback

## 工厂方法（创建型）

定义：定义一个创建对象的接口，但让实现这个接口的类来决定实例化哪个类，工厂方法让类的实例化推迟到子类中进行。创建对象用的 ，方法 通过子类实现方法来创建对象。

场景：创建对象需要大量重复的代码；客户端（应用层）不依赖于产品类实例如何被创建、实现等细节；一个类通过其子类来指定创建哪个对象

优点：用户只需要关心所需产品对应的工厂，无须关心创建细节；加入新产品符合开闭原则，提高可扩展性

缺点：类的个数容易过多，增加复杂度；增加了系统的抽象性和理解难度。

collection的iterator解析（collection相当于抽象工厂（这里的抽象工厂指代工厂的父类），iterator相当于抽象类,abstractList相当于工厂，Itr相当于实现类）
                     
```text
Iterator<E> iterator();

    /**
     * Returns an array containing all of the elements in this collection.
     * If this collection makes any guarantees as to what order its elements
     * are returned by its iterator, this method must return the elements in
     * the same order.
     *
     * <p>The returned array will be "safe" in that no references to it are
     * maintained by this collection.  (In other words, this method must
     * allocate a new array even if this collection is backed by an array).
     * The caller is thus free to modify the returned array.
     *
     * <p>This method acts as bridge between array-based and collection-based
     * APIs.
     *
     * @return an array containing all of the elements in this collection
     */
```

```text
/**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * <p>This implementation returns a straightforward implementation of the
     * iterator interface, relying on the backing list's {@code size()},
     * {@code get(int)}, and {@code remove(int)} methods.
     *
     * <p>Note that the iterator returned by this method will throw an
     * {@code UnsupportedOperationException} in response to its
     * {@code remove} method unless the list's {@code remove(int)} method is
     * overridden.
     *
     * <p>This implementation can be made to throw runtime exceptions in the
     * face of concurrent modification, as described in the specification
     * for the (protected) {@code modCount} field.
     *
     * @return an iterator over the elements in this list in proper sequence
     *
     * @see #modCount
     */
    public Iterator<E> iterator() {
    return new Itr();
    }

private class Itr implements Iterator<E> {

/**
     * Index of element to be returned by subsequent call to next.
     */
    int cursor = 0;

    /**
     * Index of element returned by most recent call to next or
     * previous.  Reset to -1 if this element is deleted by a call
     * to remove.
     */
    int lastRet = -1;

    /**
     * The modCount value that the iterator believes that the backing
     * List should have.  If this expectation is violated, the iterator
     * has detected concurrent modification.
     */
    int expectedModCount = modCount;

    public boolean hasNext() {
            return cursor != size();
    }

    public E next() {
            checkForComodification();
        try {
        E next = get(cursor);
        lastRet = cursor++;
        return next;
        } catch (IndexOutOfBoundsException e) {
        checkForComodification();
        throw new NoSuchElementException();
        }
    }

    public void remove() {
        if (lastRet == -1)
        throw new IllegalStateException();
            checkForComodification();

        try {
        AbstractList.this.remove(lastRet);
        if (lastRet < cursor)
            cursor--;
        lastRet = -1;
        expectedModCount = modCount;
        } catch (IndexOutOfBoundsException e) {
        throw new ConcurrentModificationException();
        }
    }

    final void checkForComodification() {
        if (modCount != expectedModCount)
        throw new ConcurrentModificationException();
    }
    }
```
http协议类的解析(urlstreamhandlerfactory)该类相当于创建工厂抽象类
```text
/*
 * %W% %E%
 *
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package java.net;

/**
 * This interface defines a factory for <code>URL</code> stream
 * protocol handlers.
 * <p>
 * It is used by the <code>URL</code> class to create a
 * <code>URLStreamHandler</code> for a specific protocol.
 *
 * @author  Arthur van Hoff
 * @version %I%, %G%
 * @see     java.net.URL
 * @see     java.net.URLStreamHandler
 * @since   JDK1.0
 */
public interface URLStreamHandlerFactory {
    /**
     * Creates a new <code>URLStreamHandler</code> instance with the specified
     * protocol.
     *
     * @param   protocol   the protocol ("<code>ftp</code>",
     *                     "<code>http</code>", "<code>nntp</code>", etc.).
     * @return  a <code>URLStreamHandler</code> for the specific protocol.
     * @see     java.net.URLStreamHandler
     */
    URLStreamHandler createURLStreamHandler(String protocol);
}
```

```text
private static class Factory implements URLStreamHandlerFactory {
        private static String PREFIX = "sun.net.www.protocol";

        private Factory() {
        }

        public URLStreamHandler createURLStreamHandler(String var1) {
            String var2 = PREFIX + "." + var1 + ".Handler";

            try {
                Class var3 = Class.forName(var2);
                return (URLStreamHandler)var3.newInstance();
            } catch (ClassNotFoundException var4) {
                var4.printStackTrace();
            } catch (InstantiationException var5) {
                var5.printStackTrace();
            } catch (IllegalAccessException var6) {
                var6.printStackTrace();
            }

            throw new InternalError("could not load " + var1 + "system protocol handler");
        }
    }
}
```

```text
public URLStreamHandler createURLStreamHandler(String var1) {
            String var2 = PREFIX + "." + var1 + ".Handler";

            try {
                Class var3 = Class.forName(var2);
                return (URLStreamHandler)var3.newInstance();
            } catch (ClassNotFoundException var4) {
                var4.printStackTrace();
            } catch (InstantiationException var5) {
                var5.printStackTrace();
            } catch (IllegalAccessException var6) {
                var6.printStackTrace();
            }

            throw new InternalError("could not load " + var1 + "system protocol handler");
        }
```

```text
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sun.net.www.protocol.http;

import java.io.IOException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

public class Handler extends URLStreamHandler {
    protected String proxy;
    protected int proxyPort;

    protected int getDefaultPort() {
        return 80;
    }

    public Handler() {
        this.proxy = null;
        this.proxyPort = -1;
    }

    public Handler(String var1, int var2) {
        this.proxy = var1;
        this.proxyPort = var2;
    }

    protected URLConnection openConnection(URL var1) throws IOException {
        return this.openConnection(var1, (Proxy)null);
    }

    protected URLConnection openConnection(URL var1, Proxy var2) throws IOException {
        return new HttpURLConnection(var1, var2, this);
    }
}
```

logback

org.slf4j.LoggerFactory
```text
/**
   * Return a logger named according to the name parameter using the statically
   * bound {@link ILoggerFactory} instance.
   *
   * @param name The name of the logger.
   * @return logger
   */
  public static Logger getLogger(String name) {
    ILoggerFactory iLoggerFactory = getILoggerFactory();
    return iLoggerFactory.getLogger(name);
  }
```


## 抽象工厂讲解（创建型）

定义：抽象工厂模式提供一个创建一系列相关或相互依赖对象的接口；无须指定它们具体的类。

客户端需要创建抽象工厂的具体实现，使用抽象工厂作为接口来创建这一主题的具体对象。

场景：客户端（应用层）不依赖于产品类实例如何被创建、实现等细节；强调一系列相关的产品对象（属于同一产品族）一起使用创建对象需要大量重复的代码；提供一个产品类的库，所有的产品以同样的接口出现，从而使客户端不依赖于具体实现。

优点：具体产品在应用层代码隔离，无须关心创建细节；将一个系列的产品族统一到一起创建。

缺点：规定了所有可能被创建的产品集合，产品族中扩展新的产品困难，需要修改抽象工厂的接口；增加了系统的抽象性和理解难度。

理解：产品等级和产品簇


优点：
> 应用层代码不和具体的产品发生依赖，只和具体的产品族工厂发生依赖关系，低耦合，高内聚。

>从具体的产品工厂取出来的肯定是同一产品族，开发的时候逻辑清晰。（ab其实可以归为1点）

> 对于产品族来说，符合开闭原则，增加新的产品族的时候，对扩展开放

缺点：
> 增加新的产品时候，不符合开闭原则，工作量变大（解决方案：针对产品增加时间跨度比较大的业务场景，使用抽象方法比较好）

为何有产品族的业务场景宜用抽象工厂设计模式？而不是工厂设计模式？
> 如果使用工厂设计模式，可能会因为工行类太多而产生类爆炸的现象。

java.sql.Connection.java(两个方法属于同一个产品族，这是连接的父类)
```text
    Statement createStatement() throws SQLException;

    PreparedStatement prepareStatement(String sql)
        throws SQLException;
```
java.sql.Statement.java(executeQuery方法和executeUpdate方法属于同一个产品族)
```text
    ResultSet executeQuery(String sql) throws SQLException;
    int executeUpdate(String sql) throws SQLException;

```

mybaties 的sqlsession源码解析

sqlSessionFactory
```text
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.apache.ibatis.session;

import java.sql.Connection;

public interface SqlSessionFactory {
    SqlSession openSession();

    SqlSession openSession(boolean var1);

    SqlSession openSession(Connection var1);

    SqlSession openSession(TransactionIsolationLevel var1);

    SqlSession openSession(ExecutorType var1);

    SqlSession openSession(ExecutorType var1, boolean var2);

    SqlSession openSession(ExecutorType var1, TransactionIsolationLevel var2);

    SqlSession openSession(ExecutorType var1, Connection var2);

    Configuration getConfiguration();
}
```

SqlSessionManager
```text
public Configuration getConfiguration() {
        return this.sqlSessionFactory.getConfiguration();
    }

    public SqlSession openSession(ExecutorType execType, TransactionIsolationLevel level) {
        return this.sqlSessionFactory.openSession(execType, level);
    }
```
DefaultSqlSessionFactory
```text
public Configuration getConfiguration() {
        return this.configuration;
    }

 public SqlSession openSession(ExecutorType execType, TransactionIsolationLevel level) {
        return this.openSessionFromDataSource(execType, level, false);
    }

    private SqlSession openSessionFromDataSource(ExecutorType execType, TransactionIsolationLevel level, boolean autoCommit) {
        Transaction tx = null;

        DefaultSqlSession var8;
        try {
            Environment environment = this.configuration.getEnvironment();　　　　　　　　　　　　　　　　　　　　//获取环境变量
            TransactionFactory transactionFactory = this.getTransactionFactoryFromEnvironment(environment); 　//初始化事务工厂
            tx = transactionFactory.newTransaction(environment.getDataSource(), level, autoCommit);　　　　//通过工厂获得事务对象
            Executor executor = this.configuration.newExecutor(tx, execType);　　　　　　　　　　　　　　　　//通过事务入参获取执行器
            var8 = new DefaultSqlSession(this.configuration, executor, autoCommit);　　　　　　　　　　　　 //返回defaultsqlsession
        } catch (Exception var12) {
            this.closeTransaction(tx);
            throw ExceptionFactory.wrapException("Error opening session.  Cause: " + var12, var12);
        } finally {
            ErrorContext.instance().reset();
        }
```

## 建造者模式（创建型）

定义：将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示；用户只需指定需要建造的类型就可以得到它们，建造过程及细节不需要知道。

场景：如果一个对象有非常复杂的内部结构（很多属性），想把复杂对象的创建和使用分离。

优点：封装性好，创建和使用分离，扩展性好、建造类之间独立、一定程度上解耦。

缺点：产生多余的Builder对象，产品内部发生变化，建造者都要修改，成本较大。

建造者模式和工厂模式的区别
> 建造者模式更注重方法的调用顺序，工厂模式注重于创建产品

> 建造者模式侧重于创建复杂的产品，由各种复杂的组件构成，工厂模式创建的产品都是一个样子。

静态内部类演练建造者模式（链式调用）

StringBuffer
```text
public StringBuilder append(boolean b) {
        super.append(b);
        return this;
    }

    public StringBuilder append(char c) {
        super.append(c);
        return this;
    }

    public StringBuilder append(int i) {
        super.append(i);
        return this;
    }

    public StringBuilder append(long lng) {
        super.append(lng);
        return this;
    }

    public StringBuilder append(float f) {
        super.append(f);
        return this;
    }

    public StringBuilder append(double d) {
        super.append(d);
        return this;
    }
```
StringBuffer
```text
public synchronized StringBuffer append(Object obj) {
        super.append(String.valueOf(obj));
        return this;
    }

    public synchronized StringBuffer append(String str) {
        super.append(str);
        return this;
    }
```

guava解析（ImmutableSet）

copyOf方法
```text
public static <E> ImmutableSet<E> copyOf(Collection<? extends E> elements) {
        if (elements instanceof ImmutableSet && !(elements instanceof ImmutableSortedSet)) {
            ImmutableSet<E> set = (ImmutableSet)elements;
            if (!set.isPartialView()) {
                return set;
            }
        } else if (elements instanceof EnumSet) {
            return copyOfEnumSet((EnumSet)elements);
        }

        Object[] array = elements.toArray();
        return construct(array.length, array);
    }
```
Of方法
```text
public static <E> ImmutableSet<E> of() {
        return RegularImmutableSet.EMPTY;
    }

    public static <E> ImmutableSet<E> of(E element) {
        return new SingletonImmutableSet(element);
    }

    public static <E> ImmutableSet<E> of(E e1, E e2) {
        return construct(2, e1, e2);
    }

    public static <E> ImmutableSet<E> of(E e1, E e2, E e3) {
        return construct(3, e1, e2, e3);
    }

    public static <E> ImmutableSet<E> of(E e1, E e2, E e3, E e4) {
        return construct(4, e1, e2, e3, e4);
    }

    public static <E> ImmutableSet<E> of(E e1, E e2, E e3, E e4, E e5) {
        return construct(5, e1, e2, e3, e4, e5);
    }
```

add方法（注意静态内部类）
```text
@CanIgnoreReturnValue
        public ImmutableSet.Builder<E> add(E element) {
            super.add(element);
            return this;
        }

public static class Builder<E> extends ArrayBasedBuilder<E> {
        public Builder() {
            this(4);
        }

        Builder(int capacity) {
            super(capacity);
        }

        @CanIgnoreReturnValue
        public ImmutableSet.Builder<E> add(E element) {
            super.add(element);
            return this;
        }

        @CanIgnoreReturnValue
        public ImmutableSet.Builder<E> add(E... elements) {
            super.add(elements);
            return this;
        }

        @CanIgnoreReturnValue
        public ImmutableSet.Builder<E> addAll(Iterable<? extends E> elements) {
            super.addAll(elements);
            return this;
        }

        @CanIgnoreReturnValue
        public ImmutableSet.Builder<E> addAll(Iterator<? extends E> elements) {
            super.addAll(elements);
            return this;
        }

        public ImmutableSet<E> build() {
            ImmutableSet<E> result = ImmutableSet.construct(this.size, this.contents);
            this.size = result.size();
            return result;
        }
```
spring BeanDefinitionBuilder
```text
public static BeanDefinitionBuilder genericBeanDefinition() {
        BeanDefinitionBuilder builder = new BeanDefinitionBuilder();
        builder.beanDefinition = new GenericBeanDefinition();
        return builder;
    }
```

mybaties解析
SqlSessionFactoryBuilder类（建造者模式中再使用建造者）
```text
public SqlSessionFactory build(Reader reader, String environment, Properties properties) {
        SqlSessionFactory var5;
        try {
            XMLConfigBuilder parser = new XMLConfigBuilder(reader, environment, properties);
            var5 = this.build(parser.parse());
        } catch (Exception var14) {
            throw ExceptionFactory.wrapException("Error building SqlSession.", var14);
        } finally {
            ErrorContext.instance().reset();

            try {
                reader.close();
            } catch (IOException var13) {
                ;
            }

        }

        return var5;
    }
```

XMLConfigBuilder类
```text
public Configuration parse() {
    if (parsed) {
      throw new BuilderException("Each XMLConfigBuilder can only be used once.");
    }
    parsed = true;
    parseConfiguration(parser.evalNode("/configuration"));
    return configuration;
  }

  private void parseConfiguration(XNode root) {
    try {
      Properties settings = settingsAsPropertiess(root.evalNode("settings"));
      //issue #117 read properties first
      propertiesElement(root.evalNode("properties"));
      loadCustomVfs(settings);
      typeAliasesElement(root.evalNode("typeAliases"));
      pluginElement(root.evalNode("plugins"));
      objectFactoryElement(root.evalNode("objectFactory"));
      objectWrapperFactoryElement(root.evalNode("objectWrapperFactory"));
      reflectorFactoryElement(root.evalNode("reflectorFactory"));
      settingsElement(settings);
      // read it after objectFactory and objectWrapperFactory issue #631
      environmentsElement(root.evalNode("environments"));
      databaseIdProviderElement(root.evalNode("databaseIdProvider"));
      typeHandlerElement(root.evalNode("typeHandlers"));
      mapperElement(root.evalNode("mappers"));
    } catch (Exception e) {
      throw new BuilderException("Error parsing SQL Mapper Configuration. Cause: " + e, e);
    }
  }
```

## 单例模式(创建型)
定义：保证一个类仅有一个实例，并提供一个全局访问点

场景：想确保任何情况下都绝对只有一个实例；当一个对象需要频繁的被创建销毁的时候，并且创建或者销毁的性能无法优化。

例如：单服务情况下网站的计数器可以使用单例模式；线程池情况下使用单例模式；数据库连接池也会使用单例模式。

优点：在内存只有一个实例，减少了内存开销；可以避免对资源的多重占用；设置全局访问点，严格控制访问（对外不能被new出来，无法实例化）。

缺点：没有接口，扩展困难。

重点：
> 私有构造器　　禁止单例类外部调用构造函数来创建对象，需要设置该构造函数的权限为private

> 线程安全

> 延迟加载　　（使用的时候创建，而不是初始化定的时候创建，可以节省开销）

> 序列化和反序列化安全 （序列化和反序列化会对单例模式破坏）  (加分项)

> 反射　　（单例模式如何应对反射攻击）(加分项)

> doublecheck 双层检查锁的内存机制

> 单例静态内部类的实现方案

实用技能
> 反编译

> 内存原理

> 多线层debug

单例-相关设计模式：单例模式和工厂模式；单例模式和享元模式。

如果锁放在静态方法上，锁的是整个class文件，如果放在非静态方法上，锁的是在堆内存中生成的对象。
syncronized同步锁有加锁和开锁的开销，比较消耗资源，对性能有一定的影响。
外部类调取LazySingleton的时候才会进行初始化，所以实现了懒加载。
锁的调用：（优点：解决了多线程懒加载初始化的问题。缺点： 多线程的时候，由于一个线程被锁，其他的线程无法访问该类，被堵塞。性能大大降低）

由于一个线程被锁，其他的线程无法访问该类，被堵塞。性能大大降低，双重检查主要是应用解决此类问题。
双重检查可以使更多的线程堵塞在方法中，而不是在类之外，这样的话，当锁被释放的时候，能够更快的执行，可以大大的提高效率。

缺点：java在执行过程中，可能会出现指令重排序问题，（后边语句参考本节2.1代码演练2）导致a线程（先来的）的对象已经赋值，但是还没有初始化完成，这时线程b（后到的）经过判断，也开始访问对象（因为现在对象不为空），导致线程b访问的是线程a还未初始化完成的对象。由于对象并没有被完整的初始化上，系统会报异常。

指令重排序讲解
初始化的时候，实际进行了三个步骤：

> 给该对象分配内存

> 初始化该对象

> 设置该对象指向给该对象分配的内存

一般情况下，按照abc的顺序执行，但是也会有一定几率bc 颠倒。这在单线程中执行的时候并没有问题，而且能够提高运行的效率。

如何解决指令重排序问题
两种方法:

> 使重排序不再发生，每个执行的进程都按照初始化的正常步骤进行 参见  本节代码2.2

> 不允许后来的线程 看到 先来的线程进行的重排序问题

java语言规范中规定：所有线程执行java程序时，必须要遵守intra-thread semantics ，

intra-thread semantics 保证重排序不会改变单线程内的程序执行结果。换句话说，intra-thread semantics 允许那些在单线程内，不会改变单线程程序执行结果的重排序。

如何实现让后边线程看不到前边线程是否有重排序呢（综合上节看）:使用基于类初始化的方案，使用静态内部类的单例模式来解决。

原理：
jvm 在类的初始化阶段，也就是class被加载后并且被线程使用之前，都是类的初始化阶段。在这个阶段，会执行类的初始化。在此期间，jvm会获取锁，这个锁会同步多个线程对一个类的初始化。

基于上述特性，我们可以实现基于静态内部类并且线程安全的延迟初始化方案。基于类初始化的延迟加载解决方案。（即非构造线程无法看到重排序）

5种情况下，一个类（泛指，包括interface）会被立即初始化
> a类型实例被创建（new方法）

> a类声明的静态方法被调用

> a类声明的静态成员被赋值

> a类声明的静态成员被使用，并且该成员不是常量成员

> a类如果是顶级类，并且在类中有嵌套的断言语句 

饿汉式不会出现多线程问题：刚开始加载的时候就完成了初始化，避免了线程同步问题。

优点：刚开始加载的时候就完成了初始化，避免了线程同步问题。

缺点：如果该类不经常使用，比较消耗资源，造成内存浪费。


通过反射创建对象，序列化和反序列化把单例模式破坏了
所以，在序列化和反序列化（把对象写入文件，从文件读取该对象）的时候，序列化和反序列化后的对象和之前的对象的hash码不再相同，不要再使用equals和==方法

在底层类中，搜索 实现序列化接口的类是否有readResolve方法，有的话，就new一个新对象，否则，返回null。

通过反射创建的对象是新的对象，不是原来的对象。

ObjectStreamClass
```text
/**
     * Returns true if represented class is serializable/externalizable and can
     * be instantiated by the serialization runtime--i.e., if it is
     * externalizable and defines a public no-arg constructor, or if it is
     * non-externalizable and its first non-serializable superclass defines an
     * accessible no-arg constructor.  Otherwise, returns false.
     */
    boolean isInstantiable() {
        requireInitialized();
        return (cons != null);
    }
    
/**
     * Returns true if represented class is serializable or externalizable and
     * defines a conformant readResolve method.  Otherwise, returns false.
     */
    boolean hasReadResolveMethod() {
        requireInitialized();
        return (readResolveMethod != null);
    }
    
private Object readOrdinaryObject(boolean unshared)
        throws IOException
    {
        if (bin.readByte() != TC_OBJECT) {
            throw new InternalError();
        }

        ObjectStreamClass desc = readClassDesc(false);
        desc.checkDeserialize();

        Class<?> cl = desc.forClass();
        if (cl == String.class || cl == Class.class
                || cl == ObjectStreamClass.class) {
            throw new InvalidClassException("invalid class descriptor");
        }

        Object obj;
        try {
            obj = desc.isInstantiable() ? desc.newInstance() : null;
        } catch (Exception ex) {
            throw (IOException) new InvalidClassException(
                desc.forClass().getName(),
                "unable to create instance").initCause(ex);
        }

        passHandle = handles.assign(unshared ? unsharedMarker : obj);
        ClassNotFoundException resolveEx = desc.getResolveException();
        if (resolveEx != null) {
            handles.markException(passHandle, resolveEx);
        }

        if (desc.isExternalizable()) {
            readExternalData((Externalizable) obj, desc);
        } else {
            readSerialData(obj, desc);
        }

        handles.finish(passHandle);

        //核心关注desc.hasReadResolveMethod方法
        if (obj != null &&
            handles.lookupException(passHandle) == null &&
            desc.hasReadResolveMethod())
        {
            Object rep = desc.invokeReadResolve(obj);
            if (unshared && rep.getClass().isArray()) {
                rep = cloneArray(rep);
            }
            if (rep != obj) {
                handles.setObject(passHandle, obj = rep);
            }
        }

        return obj;
    }
```
枚举方式实现单例
```text
/**
     * Underlying readObject implementation.
     */
    private Object readObject0(boolean unshared) throws IOException {
        boolean oldMode = bin.getBlockDataMode();
        if (oldMode) {
            int remain = bin.currentBlockRemaining();
            if (remain > 0) {
                throw new OptionalDataException(remain);
            } else if (defaultDataEnd) {
                /*
                 * Fix for 4360508: stream is currently at the end of a field
                 * value block written via default serialization; since there
                 * is no terminating TC_ENDBLOCKDATA tag, simulate
                 * end-of-custom-data behavior explicitly.
                 */
                throw new OptionalDataException(true);
            }
            bin.setBlockDataMode(false);
        }

        byte tc;
        while ((tc = bin.peekByte()) == TC_RESET) {
            bin.readByte();
            handleReset();
        }

        depth++;
        try {
            switch (tc) {
                case TC_NULL:
                    return readNull();

                case TC_REFERENCE:
                    return readHandle(unshared);

                case TC_CLASS:
                    return readClass(unshared);

                case TC_CLASSDESC:
                case TC_PROXYCLASSDESC:
                    return readClassDesc(unshared);

                case TC_STRING:
                case TC_LONGSTRING:
                    return checkResolve(readString(unshared));

                case TC_ARRAY:
                    return checkResolve(readArray(unshared));
 　　　　　　　　　　/*
                 * 注意：读取enum
                 * 
                 */
                case TC_ENUM:
                    return checkResolve(readEnum(unshared));

                case TC_OBJECT:
                    return checkResolve(readOrdinaryObject(unshared));

                case TC_EXCEPTION:
                    IOException ex = readFatalException();
                    throw new WriteAbortedException("writing aborted", ex);

                case TC_BLOCKDATA:
                case TC_BLOCKDATALONG:
                    if (oldMode) {
                        bin.setBlockDataMode(true);
                        bin.peek();             // force header read
                        throw new OptionalDataException(
                            bin.currentBlockRemaining());
                    } else {
                        throw new StreamCorruptedException(
                            "unexpected block data");
                    }

                case TC_ENDBLOCKDATA:
                    if (oldMode) {
                        throw new OptionalDataException(true);
                    } else {
                        throw new StreamCorruptedException(
                            "unexpected end of block data");
                    }

                default:
                    throw new StreamCorruptedException(
                        String.format("invalid type code: %02X", tc));
            }
        } finally {
            depth--;
            bin.setBlockDataMode(oldMode);
        }
    }

```
```
 /**
     * Reads in and returns enum constant, or null if enum type is
     * unresolvable.  Sets passHandle to enum constant's assigned handle.
     */
    private Enum readEnum(boolean unshared) throws IOException {
        if (bin.readByte() != TC_ENUM) {
            throw new InternalError();
        }

        ObjectStreamClass desc = readClassDesc(false);
        if (!desc.isEnum()) {
            throw new InvalidClassException("non-enum class: " + desc);
        }

        int enumHandle = handles.assign(unshared ? unsharedMarker : null);
        ClassNotFoundException resolveEx = desc.getResolveException();
        if (resolveEx != null) {
            handles.markException(enumHandle, resolveEx);
        }

        String name = readString(false);//通过readString方法获取到枚举对象的名称
        Enum en = null;
        Class cl = desc.forClass();
        if (cl != null) {
            try {
                en = Enum.valueOf(cl, name);//通过类型和名称获取到枚举常量，枚举中的name是唯一的，并且对应一个枚举常量，所以拿到的肯定是唯一的对象。
            } catch (IllegalArgumentException ex) {
                throw (IOException) new InvalidObjectException(
                    "enum constant " + name + " does not exist in " +
                    cl).initCause(ex);
            }
            if (!unshared) {
                handles.setObject(enumHandle, en);
            }
        }

        handles.finish(enumHandle);
        passHandle = enumHandle;
        return en;
    }
```

jad https://varaneckas.com/jad/

jad EnumInstance.class


场景 单例设计模式-容器模式
应用于在程序初始化的时候把多个单例对象放入到singletonMap中，使用的时候直接通过key获取对象。可以应用在懒汉模式中，不适用于饿汉模式（饿汉模式由于每次都要重新初始化会出现多线程安全问题）

使用hashtable会线程安全，但是由于其同步锁，会影响性能。


场景

使用同步锁使用时间换空间的方式，（线程排队时间比较长）

而使用threadlocal使用空间换时间的方式。

基于threadlocal的单例模式，为每一个线程提供了一个对象，多线程访问的时候不会相互影响。

基于ThreadLocal的单例实现，数据库连接池的这种应用场景直接得提高了并发量，并且隔离了线程，不至于因为某一线程数据库连接断开造成全局断开，直接得提高了应用的容错率。但是代码层面要避免混用不同线程产生的单例。

理解：其实这种带引号的单例模式（threadLocal）已经不能称为单例模式了，因为同一个单例类，取出来的对象已经不一样了

解析：

java.lang.Runtime
```text
/**
 *    饿汉式加载，初始化的时候，就已经new出了对象
 */ 
private static Runtime currentRuntime = new Runtime();

    /**
     * Returns the runtime object associated with the current Java application.
     * Most of the methods of class <code>Runtime</code> are instance
     * methods and must be invoked with respect to the current runtime object.
     *
     * @return  the <code>Runtime</code> object associated with the current
     *          Java application.
     */
    public static Runtime getRuntime() {
        return currentRuntime;
    }
```

java.awt.Desktop(cs)
```text
/**
     * Returns the <code>Desktop</code> instance of the current
     * browser context.  On some platforms the Desktop API may not be
     * supported; use the {@link #isDesktopSupported} method to
     * determine if the current desktop is supported.
     * @return the Desktop instance of the current browser context
     * @throws HeadlessException if {@link
     * GraphicsEnvironment#isHeadless()} returns {@code true}
     * @throws UnsupportedOperationException if this class is not
     * supported on the current platform
     * @see #isDesktopSupported()
     * @see java.awt.GraphicsEnvironment#isHeadless
     */

    /*
     *    同步锁，context取对象，如果该对象为为null，new出新的对象，然后放入context
     */
    public static synchronized Desktop getDesktop(){
        if (GraphicsEnvironment.isHeadless()) throw new HeadlessException();
        if (!Desktop.isDesktopSupported()) {
            throw new UnsupportedOperationException("Desktop API is not " +
                                                    "supported on the current platform");
        }

        sun.awt.AppContext context = sun.awt.AppContext.getAppContext();
        Desktop desktop = (Desktop)context.get(Desktop.class);

        if (desktop == null) {
            desktop = new Desktop();
            context.put(Desktop.class, desktop);
        }


    /**
     *  context put的时候加上同步锁，可以避免多线程put异常
     */
    public Object put(Object var1, Object var2) {
        HashMap var3 = this.table;
        synchronized(this.table) {
            MostRecentKeyValue var4 = this.mostRecentKeyValue;
            if (var4 != null && var4.key == var1) {
                var4.value = var2;
            }

            return this.table.put(var1, var2);
        }
    }
```

单例解析3（Spring框架获取单例对象）
```text
public final T getObject() throws Exception {
        if (this.isSingleton()) {
            return this.initialized ? this.singletonInstance : this.getEarlySingletonInstance();
        } else {
            return this.createInstance();
        }
    }

/*
 *    如果被初始化，获取早期的单例对象
 * 
 */
//通过代理去拿新对象
    private T getEarlySingletonInstance() throws Exception {
        Class<?>[] ifcs = this.getEarlySingletonInterfaces();
        if (ifcs == null) {
            throw new FactoryBeanNotInitializedException(this.getClass().getName() + " does not support circular references");
        } else {
            if (this.earlySingletonInstance == null) {
                this.earlySingletonInstance = Proxy.newProxyInstance(this.beanClassLoader, ifcs, new AbstractFactoryBean.EarlySingletonInvocationHandler());
            }

            return this.earlySingletonInstance;
        }
    }
```

单例解析4（基于threadLocal的线程案例）（mybaties获取单例对象）
```text
private static final ThreadLocal<ErrorContext> LOCAL = new ThreadLocal<ErrorContext>();
  
 private ErrorContext() {
  }

  public static ErrorContext instance() {
    ErrorContext context = LOCAL.get();
if (context == null) {
      context = new ErrorContext();
      LOCAL.set(context);
    }
    return context;
  }
```

## 原型模式（创建型）


定义：指原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象

特点：不需要知道任何创建的细节，不调用构造函数。果要实现拷贝的方法，最好覆盖Object方法（深克隆，详见下节）

场景：

类初始化消耗较多资源

new产生的一个对象需要非常繁琐的过程（数据准备、访问权限等）

构造函数比较复杂

循环体中生产大量对象时

优点：

原型模式性能比直接new一个对象性能高

简化创建过程

缺点：

必须配备克隆方法

对克隆复杂对象或对克隆出的对象进行复杂改造时，容易引入风险

深拷贝、浅拷贝要运用得当

扩展：深克隆和浅克隆

源码解析1（java.lang.Object对象）
```text
//native 调用非java代码接口 
protected native Object clone() throws CloneNotSupportedException;
```
 
源码解析2（ArrayList实现克隆）
```text
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable{

/**
     * Returns a shallow copy of this <tt>ArrayList</tt> instance.  (The
     * elements themselves are not copied.)
     *
     * @return a clone of this <tt>ArrayList</tt> instance
     */
    public Object clone() {
        try {
            @SuppressWarnings("unchecked")
                ArrayList<E> v = (ArrayList<E>) super.clone();
            v.elementData = Arrays.copyOf(elementData, size);
            v.modCount = 0;
            return v;
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError();
        }
    }

}
```
源码解析3（mybaties 的cacheKey）
```text
/**
 *    Copyright 2009-2016 the original author or authors.
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
package org.apache.ibatis.cache;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Clinton Begin
 */
public class CacheKey implements Cloneable, Serializable {
 
 @Override
  public CacheKey clone() throws CloneNotSupportedException {
    CacheKey clonedCacheKey = (CacheKey) super.clone();
    clonedCacheKey.updateList = new ArrayList<Object>(updateList);
    return clonedCacheKey;
  }

}
```

思考？

> 克隆对象的引用问题：查看克隆出的对象是否是同一个对象

> 工厂方法和抽象工厂最大的区别是什么

> 产品镞和产品等级结构



