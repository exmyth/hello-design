package com.exmyth.hello.design.pattern.creational.prototype;

public class Mail {
    private String name;
    private String emailAddress;
    private String content;

    /**
     * 1    添加初始化方法，目的是为了一会儿和克隆方法 出来的对象，作对比。
     *      查看克隆出来的对象是否是此初始化出来的。
     */
    public Mail(){
        System.out.println("Mail Class Constructor");
    }

    /**
     * 2    为打印方便，添加toString方法
     * @return
     */
    @Override
    public String toString() {
        return "Mail{" +
                "name='" + name + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", content='" + content + '\'' +
                '}';
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