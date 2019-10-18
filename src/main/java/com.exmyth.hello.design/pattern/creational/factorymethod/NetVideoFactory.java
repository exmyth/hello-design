package com.exmyth.hello.design.pattern.creational.factorymethod;

public class NetVideoFactory extends VideoFactory{
    /**
     * 这里生成netVideo
     * @return
     */
    @Override
    public Video getVideo() {
        Video video = new NetVideo();
        return video;
    }
}