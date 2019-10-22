package com.exmyth.hello.design.pattern.structural.facade.v2;

import com.exmyth.hello.design.pattern.structural.facade.PointGift;
import com.exmyth.hello.design.pattern.structural.facade.PointPaymentService;
import com.exmyth.hello.design.pattern.structural.facade.QualifyService;
import com.exmyth.hello.design.pattern.structural.facade.ShippingService;

/**
 * 外观类
 */
public class GiftExchangeService {
    private QualifyService qualifyService = new QualifyService();
    private PointPaymentService pointPaymentService = new PointPaymentService();
    private ShippingService shippingService = new ShippingService();

    /**
     * 初始化的时候把这些服务类进行注入
     * @param qualifyService
     */
    /*public void setQualifyService(QualifyService qualifyService) {
        this.qualifyService = qualifyService;
    }

    public void setPointPaymentService(PointPaymentService pointPaymentService) {
        this.pointPaymentService = pointPaymentService;
    }

    public void setShippingService(ShippingService shippingService) {
        this.shippingService = shippingService;
    }*/

    public void giftExchange(PointGift pointGift){
        if(qualifyService.getQualify(pointGift)){
           //如果资格通过，校验积分是否通过，积分通过，会进行发货交易
            if(pointPaymentService.pay(pointGift)){
                shippingService.shipGift(pointGift);
            }
        }

    }
}