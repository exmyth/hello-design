package com.exmyth.hello.design.pattern.creational.singleton.v9;

import java.util.HashMap;
import java.util.Map;

public class ContainerSingleton {
    /**
     * 私有构造器，防止外部类实例化该类
     */
    private ContainerSingleton(){

    }

    private static Map<String,Object> singletonMap = new HashMap<String,Object>();

    /**
     * map赋值，赋值单例对象
     * @param key
     * @param instance
     */
    public static void putInstance(String key,Object instance){
        if(null != key && !"".equals(key)&&instance != null){
            if(!singletonMap.containsKey(key)){
                singletonMap.put(key, instance);
            }
        }
    }

    /**
     * 留着口子外部调用
     * @return
     */
    public static Object getInstance(String key){
       return singletonMap.get(key);
    }
}