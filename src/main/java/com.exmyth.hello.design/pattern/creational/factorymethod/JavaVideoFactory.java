package com.exmyth.hello.design.pattern.creational.factorymethod;

public class JavaVideoFactory extends VideoFactory{
    /**
     * 这里实现抽象方法 getVideo
     * @return
     */
    @Override
    public Video getVideo() {
        Video video = new JavaVideo();
        return video;
    }
}