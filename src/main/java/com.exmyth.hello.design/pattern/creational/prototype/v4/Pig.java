package com.exmyth.hello.design.pattern.creational.prototype.v4;

import java.util.Date;

public class Pig implements Cloneable{
    private String name;
    private Date birthday;

    /**
     * 4    添加有参构造方法，方便测试类赋值
     * @param name
     * @param birthday
     */
    public Pig(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    /**
     * 3    添加克隆方法
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * 2    添加toString方法，方便测试
     * @return
     */
    @Override
    public String toString() {
        return "Pig{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +super.toString()+
                '}';
    }

    /**
     * 1    getset方法
     * @return
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}