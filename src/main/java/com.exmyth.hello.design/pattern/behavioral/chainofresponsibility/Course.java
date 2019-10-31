package com.exmyth.hello.design.pattern.behavioral.chainofresponsibility;

/**
 * @author maming.zhong
 * @date 2019-10-31 20:51
 * @description
 */
public class Course {
    private String article;
    private String video;
    private String title;

    public Course(String article, String video, String title) {
        this.article = article;
        this.video = video;
        this.title = title;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
