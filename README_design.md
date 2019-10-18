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








