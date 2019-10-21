package com.exmyth.hello.design.pattern.creational.singleton.v2;

public class LazyDoubleCheckSingleton {
    /*
 volatile关键字的作用
       将当前处理器缓存行的数据写回到内存，该操作会使在其他cpu内存中缓存了该内存地址的数据无效。它们又从共享内存同步数据。  如此操作保存内存的可见性。j
     */
    private volatile static LazyDoubleCheckSingleton lazyDoubleCheckSingleton = null;

    /**
     * 构造方法私有，其他类无法实例化该类
     */
    private LazyDoubleCheckSingleton(){
    }


    /**
     * 换一种写法，
     *
     * @return
     */
      public static LazyDoubleCheckSingleton getInstance(){
          if(lazyDoubleCheckSingleton == null){
              synchronized (LazyDoubleCheckSingleton.class){
                  if(lazyDoubleCheckSingleton == null){
                      lazyDoubleCheckSingleton = new LazyDoubleCheckSingleton();
                  }
              }
          }
          return lazyDoubleCheckSingleton;
    }


}