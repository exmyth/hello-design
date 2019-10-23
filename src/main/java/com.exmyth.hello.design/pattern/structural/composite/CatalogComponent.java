package com.exmyth.hello.design.pattern.structural.composite;

import java.io.IOException;

/**
 * 课程目录组件，可以被课程和课程目录继承
 */
public abstract class CatalogComponent {
    //添加目录
    public void addCatalog(CatalogComponent catalogComponent){
        throw new UnsupportedOperationException("不支持添加操作");
    }

    //移除目录
    public void removeCatalog(CatalogComponent catalogComponent){
        throw new UnsupportedOperationException("不支持移除操作");
    }

    //得到课程名称
    public String getName(CatalogComponent catalogComponent){
        throw new UnsupportedOperationException("不支持获取名称操作");
    }

    //得到课程价格
    public double getPrice(CatalogComponent catalogComponent){
        throw new UnsupportedOperationException("不支持获取价格操作");
    }

    //打印目录  注意这里抛出异常 是为了抽象类 的方法不被调取，之所以没有写抽象方法，是因为目录类和课程类总有些方法没有实现
    public void printCatalog(){
         throw new UnsupportedOperationException("不支持的操作");
    }
}