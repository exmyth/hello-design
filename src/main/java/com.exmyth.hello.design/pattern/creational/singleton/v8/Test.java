package com.exmyth.hello.design.pattern.creational.singleton.v8;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Test {
    /**
     * 序列化代码演练
     * 将 HungrySingleton对象放入文件，再从文件读取该对象，还是同一个对象吗？
     * 实际应用：在从文件存入后读取，用equals时需要注意（equals比较的是hash码）
     *
     * @param args
     */
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        try {
            //将singleton对象写入到输出流中
            EnumInstance instance = EnumInstance.getInstane();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("singleton_file"));
            oos.writeObject(instance);

            //从输入流中读取到该对象
            File file = new File("singleton_file");
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            EnumInstance instance2 = (EnumInstance) ois.readObject();
            System.out.println(instance);
            System.out.println(instance2);
            System.out.println(instance == instance2);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}