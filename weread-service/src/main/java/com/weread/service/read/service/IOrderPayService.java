package com.weread.service.read.service;

import com.weread.service.read.entity.OrderPay;
import com.weread.service.base.IBaseService;

/**
 * <p>
 * 充值订单; InnoDB free: 6144 kB 服务类
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
public interface IOrderPayService extends IBaseService<OrderPay> {
	
	/**
     * 创建充值订单
     *
     * @param payChannel 支付渠道
     * @param payAmount 支付金额
     * @param userId 用户ID
     * @return 商户订单号
     * */
    Long createPayOrder(Byte payChannel, Integer payAmount, Long userId);


    /**
     * 更新订单状态
     * @param outTradeNo 商户订单号
     * @param tradeNo      支付宝/微信 订单号
     * @param tradeStatus 支付状态
     * */
    void updatePayOrder(Long outTradeNo, String tradeNo, String tradeStatus);
	
}
