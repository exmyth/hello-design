package com.exmyth.hello.design.pattern.creational.factorymethod;


public abstract class VideoFactory {
    private Video video = null;

    /**
     * 可以使用静态方法来构建
     * @param c
     * @return
     */
//    public Video getVideo(String param) {
//        if("java".equals(param)){
//            return  new JavaVideo();
//        }else if("net".equals(param)){
//            return  new ();
//        }else{
//            return null;
//        }
//    }


//    public Video getVideo(Class c){
//        Video video = null;
//        try {
//            video = (Video) Class.forName(c.getName()).newInstance();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return video;
//    }

    /**
     * 工厂设计模式，将其改为抽象方法，子方法继承即可。
     */
    public abstract Video getVideo();
}