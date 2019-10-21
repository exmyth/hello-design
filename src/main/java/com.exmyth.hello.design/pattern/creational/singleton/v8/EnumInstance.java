package com.exmyth.hello.design.pattern.creational.singleton.v8;

/**
 * 这个类是enum类型
 */
public enum  EnumInstance {
    INSTANCE;
    private Object data;
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
/**
     * Java类中的静态变量在程序运行期间，其内存空间对所有该类的对象实例而言是共享的，有些时候可以认为是全局变量。
     * 因此在某些时候为了节省系统内存开销、共享资源，可以将类中的一些变量声明为静态变量!
     *
     * 最最主要的原因，单例模式外部类不能new出对象，要使用该方法只能是静态的
     *
     * 最终返回的肯定是本类，所以该方法的类型为EnumInstance
     */
    public static EnumInstance getInstane(){
        return INSTANCE;
    }
}