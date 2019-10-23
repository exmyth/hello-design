package com.exmyth.hello.design.pattern.structural.composite;

import java.util.ArrayList;
import java.util.List;

public class CourseCatalog extends CatalogComponent {
    private List<CatalogComponent> items = new ArrayList<CatalogComponent>();
    private String name;

    /**
     * 一定要写有参构造方法，因为后边还要打印日志
     * @param name
     */
    public CourseCatalog(String name) {
        this.name = name;
    }

    @Override
    public void addCatalog(CatalogComponent catalogComponent) {
        items.add(catalogComponent);
    }

    @Override
    public void removeCatalog(CatalogComponent catalogComponent) {
        items.remove(catalogComponent);
    }

    /**
     * 对于目录来说，课程名字可以出重写，
     * 所以这里可以进行修改！！！
     * @param catalogComponent
     * @return
     */
    @Override
    public String getName(CatalogComponent catalogComponent) {
       return this.name;
    }

    @Override
    public void printCatalog() {
        for(CatalogComponent catalogComponent :items){
            catalogComponent.printCatalog();
        }
    }
}