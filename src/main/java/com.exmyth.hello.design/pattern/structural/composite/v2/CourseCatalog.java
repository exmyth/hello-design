package com.exmyth.hello.design.pattern.structural.composite.v2;

import java.util.ArrayList;
import java.util.List;

public class CourseCatalog extends CatalogComponent {
    private List<CatalogComponent> items = new ArrayList<CatalogComponent>();
    private String name;
    private Integer level;//加入等级， 判断是否是目录,之所以不用int 是因为不用给它初始值， 也好做空判断

    /**
     * 一定要写有参构造方法，因为后边还要打印日志
     * @param name
     */
    public CourseCatalog(String name,Integer level) {
        this.name = name;
        this.level = level;
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
        System.out.println(this.name);//打印目录名字
        for(CatalogComponent catalogComponent :items){
            if(this.level != null){
                for(int i = 0;i < level;i++){
                    System.out.print("  ");
                }
            }
            catalogComponent.printCatalog();//调用课程或者目录打印方法
        }
    }
}