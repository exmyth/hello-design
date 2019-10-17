package com.exmyth.hello.design.pattern.creational.simplefactory;

public class Test {
    /**
     * 注：此为反例，
     * 没有用简单工厂模式之前，需要new对象。
     * @param args
     */
//    public static void main(String [] args){
//        Video video = new JavaVideo();
//        video .printVideo();
//    }

    /**
     * 2    简单工厂模式
     * 这里通过参数调取工厂类，直接获得工厂子类
　　 *  可以使用静态方法来创建工厂，优点是工厂类不用new了，但是缺点是静态方法继承后无法修改父类
     * @param args
     */
//    public static void main(String [] args){
//        VideoFactory videoFactory = new VideoFactory();
//        Video video = videoFactory.getVideo("net");
//        video.printVideo();
//    }

    /**
     * 3    简单工厂模式
     * 通过反射机制，获得工厂子类
     * @param args
     */
    public static void main(String [] args){
        VideoFactory videoFactory = new VideoFactory();
        //通过反射获取子类
        Video video = videoFactory.getVideo(JavaVideo.class);
        video.printVideo();
    }
}