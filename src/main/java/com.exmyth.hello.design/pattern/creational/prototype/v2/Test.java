package com.exmyth.hello.design.pattern.creational.prototype.v2;


/**
 * 测试类：
 */
public class Test {

    /**
     * 1    测试使用原型模式（克隆之前）
     * @param args
     */
    public static void main(String [] args) throws CloneNotSupportedException {
        Mail mail = new Mail();
        mail.setContent("初始化模版");
        System.out.println("初始化对象内存地址"+mail);

        for(int i = 0;i < 10;i++){
            Mail mailTemp = (Mail) mail.clone();
            mailTemp.setName("姓名"+i);
            mailTemp.setContent("内容"+i);
            mailTemp.setEmailAddress(i+"@imooc.com");
            MailUtil.sendMail(mailTemp);
            System.out.println("克隆对象"+i+"内存地址"+mailTemp);
        }
        MailUtil.saveOriginMail(mail);
    }

}