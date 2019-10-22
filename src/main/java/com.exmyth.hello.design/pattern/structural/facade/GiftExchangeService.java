package com.exmyth.hello.design.pattern.structural.facade;

/**
 * 外观类
 */
public class GiftExchangeService {
    private QualifyService qualifyService;
    private PointPaymentService pointPaymentService;
    private ShippingService shippingService;

    /**
     * 初始化的时候把这些服务类进行注入
     * @param qualifyService
     */
    public void setQualifyService(QualifyService qualifyService) {
        this.qualifyService = qualifyService;
    }

    public void setPointPaymentService(PointPaymentService pointPaymentService) {
        this.pointPaymentService = pointPaymentService;
    }

    public void setShippingService(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    public void giftExchange(PointGift pointGift){
        if(qualifyService.getQualify(pointGift)){
           //如果资格通过，校验积分是否通过，积分通过，会进行发货交易
            if(pointPaymentService.pay(pointGift)){
                shippingService.shipGift(pointGift);
            }
        }

    }
}