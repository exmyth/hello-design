package com.exmyth.hello.design.pattern.creational.builder;

public class Course {
    private String courseName;
    private String coursePPT;
    private String courseArticle;
    private String courseVideo;
    private String courseQA;

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCoursePPT(String coursePPT) {
        this.coursePPT = coursePPT;
    }

    public void setCourseArticle(String courseArticle) {
        this.courseArticle = courseArticle;
    }

    public void setCourseVideo(String courseVideo) {
        this.courseVideo = courseVideo;
    }

    public void setCourseQA(String courseQA) {
        this.courseQA = courseQA;
    }

    /**
     * toString方法，为了方便测试，进行打印
     * @return
     */
    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", coursePPT='" + coursePPT + '\'' +
                ", courseArticle='" + courseArticle + '\'' +
                ", courseVideo='" + courseVideo + '\'' +
                ", courseQA='" + courseQA + '\'' +
                '}';
    }
}