package com.weread.service.read.service.impl;

import com.weread.service.read.entity.OrderPay;
import com.weread.service.read.mapper.OrderPayMapper;
import com.weread.service.read.service.IOrderPayService;
import com.weread.service.base.BaseService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 充值订单; InnoDB free: 6144 kB 服务实现类
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
@Service
public class OrderPayServiceImpl extends BaseService<OrderPayMapper, OrderPay> implements IOrderPayService {

	@Override
	public Long createPayOrder(Byte payChannel, Integer payAmount, Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePayOrder(Long outTradeNo, String tradeNo, String tradeStatus) {
		// TODO Auto-generated method stub
		
	}
	
}
