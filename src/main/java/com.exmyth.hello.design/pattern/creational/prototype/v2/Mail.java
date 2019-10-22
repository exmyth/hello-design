package com.exmyth.hello.design.pattern.creational.prototype.v2;
/*
 *　　只有实现clonable接口，才能调用clone方法
 */
public class Mail implements Cloneable{
    private String name;
    private String emailAddress;
    private String content;

    /**
     * 4    添加浅克隆方法
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        System.out.println("调用克隆方法");
        return super.clone();
    }

    /**
     * 1    添加初始化方法，目的是为了一会儿和克隆方法 出来的对象，作对比。
     *      查看克隆出来的对象是否是此初始化出来的。
     */
    public Mail(){
        System.out.println("Mail Class Constructor");
    }

    /**
     * 2    为打印方便，添加toString方法   
　　 *　添加super.toString方法是为了打印内存地址，比较克隆的对象内存地址和初始化的对象内存地址是否一致
　　 *  以及克隆对象之间内存地址是否一致 
    * @return
     */
    @Override
    public String toString() {
        return "Mail{" +
                "name='" + name + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", content='" + content + '\'' +
                '}'+super.toString();
    }

    /**
     * 3    以下是get和set方法
     * @return
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}