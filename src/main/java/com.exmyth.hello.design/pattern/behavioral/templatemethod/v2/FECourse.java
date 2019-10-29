package com.exmyth.hello.design.pattern.behavioral.templatemethod.v2;


public class FECourse extends ACourse {
    boolean bNeedMakeArticleFlag = false;

    /**
     * 用注入的方式 将该字段传入
     * @param bNeedMakeArticleFlag
     */
    public FECourse(boolean bNeedMakeArticleFlag) {
        this.bNeedMakeArticleFlag = bNeedMakeArticleFlag;
    }

    /**
     * 不同子类实现不同包装方法
     */
    @Override
    protected boolean needMakeArticle() {
        return this.bNeedMakeArticleFlag;
    }

    @Override
    void packageCourse() {
        System.out.println("提供课程前端代码");
        System.out.println("提供课程图片素材");
    }
}