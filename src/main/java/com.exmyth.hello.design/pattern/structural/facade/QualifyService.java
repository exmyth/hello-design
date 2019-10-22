package com.exmyth.hello.design.pattern.structural.facade;

/**
 * 资格校验类
 */
public class QualifyService {
    /**
     * 获取是否得到校验资格
     * @param pointGift
     * @return
     */
    public boolean getQualify(PointGift pointGift){
        //判断是否木木网会员类逻辑
        System.out.println(pointGift.getGiftName()+"资格校验通过!");
        return true;
    }

}