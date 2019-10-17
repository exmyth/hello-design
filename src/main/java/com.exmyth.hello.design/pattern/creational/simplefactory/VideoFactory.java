package com.exmyth.hello.design.pattern.creational.simplefactory;

public class VideoFactory {
    private Video video = null;

    public Video getVideo(String param) {
        if("java".equals(param)){
            return  new JavaVideo();
        }else if("net".equals(param)){
            return  new NetVideo();
        }else{
            return null;
        }
    }

    public Video getVideo(Class c){
        Video video = null;
        try {
            video = (Video) Class.forName(c.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return video;
    }
}