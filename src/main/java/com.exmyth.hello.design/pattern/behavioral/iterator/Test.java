package com.exmyth.hello.design.pattern.behavioral.iterator;

public class Test {
    public static void main(String [] args){
        //新增6个课程
        Course course1 = new Course("java电商一期");
        Course course2 = new Course("java电商二期");
        Course course3 = new Course("java电商三期");
        Course course4 = new Course("java前端HTML一期");
        Course course5 = new Course("java前端HTML二期");
        Course course6 = new Course("java设计模式一期");

        //将6个课程放到list中去
        CourseAggregate courseAggregate = new CourseAggregateImpl();
        courseAggregate.addCourse(course1);
        courseAggregate.addCourse(course2);
        courseAggregate.addCourse(course3);
        courseAggregate.addCourse(course4);
        courseAggregate.addCourse(course5);
        courseAggregate.addCourse(course6);

        print(courseAggregate);

        courseAggregate.removeCourse(course1);
        courseAggregate.removeCourse(course3);
        print(courseAggregate);
    }


    /**
     * 此方法 传入CourseAggregate而不是CourseIterator 比较好，
     * 如果传入 CourseIterator ,CourseIterator 只能此初始化一次，也就是只能打印一个循环
     * @param courseAggregate
     */
    public static void print(CourseAggregate courseAggregate){
        //正常代码中，我们直接写getIterator()就可以了，代码规范通常它们获取得的直接是变量的迭代器
        CourseIterator courseIterator = courseAggregate.getCourseIterator();
        while (!courseIterator.isLastCourse()){
            Course course = courseIterator.nextCourse();
            System.out.println(course.getName());
        }
        System.out.println("===========================================================");

    }
}