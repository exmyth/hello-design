package com.exmyth.hello.design.pattern.creational.builder.v2;

public class Course {
    private String courseName;
    private String coursePPT;
    private String courseArticle;
    private String courseVideo;
    private String courseQA;

    private Course(CourseBuilder courseBuilder){
        this.courseName = courseBuilder.courseName;
        this.courseArticle = courseBuilder.courseArticle;
        this.coursePPT = courseBuilder.coursePPT;
        this.courseVideo = courseBuilder.courseVideo;
        this.courseQA = courseBuilder.courseQA;
    }

    /**
     * toString方法，为了方便测试，打印对象的时候，能够打印出对象的属性
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

    /**
     * 编写静态内部类，实现链式调用
     */
    public static class CourseBuilder{
        private String courseName;
        private String coursePPT;
        private String courseArticle;
        private String courseVideo;
        private String courseQA;

        public CourseBuilder buildCourseName(String courseName) {
            this.courseName = courseName;
            return this;
        }

        public CourseBuilder buildCoursePPT(String coursePPT) {
            this.coursePPT = coursePPT;
            return this;
        }

        public CourseBuilder buildCourseArticle(String courseArticle) {
            this.courseArticle = courseArticle;
            return this;
        }

        public CourseBuilder buildCourseVideo(String courseVideo) {
            this.courseVideo = courseVideo;
            return this;
        }

        public CourseBuilder buildCourseQA(String courseQA) {
            this.courseQA = courseQA;
            return this;
        }

        public Course build(){
            return new Course(this);
        }

    }
}