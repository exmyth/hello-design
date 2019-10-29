package com.exmyth.hello.design.pattern.behavioral.templatemethod.v2;

/**
 * 制作抽象类 课程类，用于被其他类继承
 */
public abstract class ACourse {

    /**
     * 整合课程 的方法，
     * 该流程是固定的，子类不能重写
     */
    protected final void makeCourse(){
        makePPT();
        makeVideo();
        if(needMakeArticle()){
            makeArticle();
        }
        packageCourse();
    }
    /**
     * 制作PPT 方法
     * 目的：如果想要每个子类都不能重写该方法，就用final修饰
     * 原因：每个子类都必须有制作PPT的方法
     */
    final void makePPT(){
        System.out.println("开始制作PPT");
    }

    /**
     * 同上
     */
    final void makeVideo(){
        System.out.println("制作课程视频");
    }

    /**
     * 制作手记方法
     * 该方法不可被重写
     * 有的课程需要制作，有的课程不需要
     */
    final void makeArticle(){
        System.out.println("编写手记");
    }

    /**
     * 添加钩子方法
     * 该方法可被子类重写，用于判断子类是否需要制作手记
     * @return
     */
    protected boolean needMakeArticle(){
        return false;
    }

    /**
     * 包装课程方法，
     * 目的：不同的课程，包装的素材也不一样，所以声明抽象的，子类可以重写
     */
    abstract void packageCourse();
}