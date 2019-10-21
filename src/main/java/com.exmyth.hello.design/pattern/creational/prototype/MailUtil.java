package com.exmyth.hello.design.pattern.creational.prototype;

import java.text.MessageFormat;

/**
 * 邮件工具类，实现发送邮件和保存邮件初始化模版的功能
 */
public class MailUtil {

    /**
     * 1    发送邮件功能的实现
     * 重点：  占位符赋值的实现
     * @param mail
     */
    public static void sendMail(Mail mail){
        String content = "向{0}同学发送邮件内容为{1},邮件地址为：{2}";
        System.out.println(MessageFormat.format(content,mail.getName(),mail.getContent(),mail.getEmailAddress()));
    }

    /**
     * 2    保存日志记录
     */
    public static void saveOriginMail(Mail mail){
        System.out.println("邮件内容为"+mail.getContent());
    }
}