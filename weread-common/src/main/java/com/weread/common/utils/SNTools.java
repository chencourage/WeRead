package com.weread.common.utils;

import java.util.Date;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import com.weread.common.base.SystemConfig;
import com.weread.common.base.UserTypeEnum;
import com.weread.common.redis.IRedisService;

public class SNTools {

	/***
	 * 默认的系统代码
	 */
	private static final String DEFAULT_SYSTEM_CODE = "888";

	private static final IRedisService redisService = SpringContextUtil.getBean(IRedisService.class);
	
	/***
	 * 生成流水
	 * 
	 * @param functionCode
	 * @return
	 */
	public static final String getSN(String functionCode) {
		try {
			return BaseSNTools.getSeq(DEFAULT_SYSTEM_CODE, functionCode);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 生成16位纯数字订单号
	 * @return
	 */
	public static final String genOrderSN16() {
		return DateUtil.format(new Date(), "yyMMdd") + StringUtils.leftPad(Long.toString(redisService.incr(SystemConfig.SYSTEM_REDIS_PREFIX+"ordersn_incr")%100000), 5, "0") + RandomUtil.generateString(5, RandomUtil.SCOPE_NUM);
	}
	
	public static final String genUserCode(String userType) {
		if(UserTypeEnum.AGENT.code.equals(userType)) {
			return genAgentUserCode();
		} else if(UserTypeEnum.MERC.code.equals(userType)) {
			return genMercUserCode();
		} else {
			throw new RuntimeException("未知用户类型【"+userType+"】");
		}
	}
	
	public static final String genAgentUserCode() {
		return "A" + genUserCode();
	}
	
	public static final String genCustUserCode() {
		return "C" + genUserCode();
	}
	
	public static final String genMercUserCode() {
		return "M" + genUserCode();
	}
	
	public static final String genAccountNo() {
		return "D" + genUserCode();
	}
	
	private static final String genUserCode() {
		return DateUtil.format(new Date(), "yyMMdd") + StringUtils.leftPad(Long.toString(redisService.incr(SystemConfig.SYSTEM_REDIS_PREFIX+"usercode_incr")%100000), 5, "0") + RandomUtil.generateString(3, RandomUtil.SCOPE_NUM);
	}
}
