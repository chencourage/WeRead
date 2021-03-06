package com.weread.common.message;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.alibaba.fastjson.JSONObject;
import com.weread.common.base.PropertyUtil;
import com.weread.common.base.SystemConfig;
import com.weread.common.desensitize.DesensitionUtil;
import com.weread.common.exception.REDAssert;
import com.weread.common.exception.REDException;
import com.weread.common.redis.IRedisService;
import com.weread.common.utils.BizThreadUtil;
import com.weread.common.utils.DataFormCheck;
import com.weread.common.utils.HttpUtil;
import com.weread.common.utils.RandomUtil;
import com.weread.common.utils.SpringContextUtil;
import com.weread.common.utils.StringUtil;

/**
 * 短信工具类
 * @author lisheng
 */
public class ShortMsgManager {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShortMsgManager.class);
	
	private static final String SOURCE = SystemConfig.APP_NAME; //短信发送来源
	private static final long SMSCODE_TIMEOUT = 5; //验证码过期时间（分钟）
	private static final String SEND_URL = PropertyUtil.getProperty("shortmsg.send.url");
//	private static final String CHECK_URL = PropertyUtil.getProperty("shortmsg.check.url");
	
	private static final IRedisService redisService = SpringContextUtil.getBean(IRedisService.class);
	
	/**
	 * 验证短信验证码
	 * @param userCode
	 * @param phone
	 * @param smsCode
	 * @param func
	 * @return
	 */
	public static boolean verifySms(String userCode, String phone, String smsCode, ShortMsgFuncEnum func) {
		REDAssert.notBlank(userCode, "用户号不能为空");
		REDAssert.notBlank(phone, "手机号不能为空");
		REDAssert.notBlank(smsCode, "短信验证码不能为空");
		REDAssert.notNull(func, "功能代码不能为空");
		REDAssert.isTrue(DataFormCheck.isPhone(phone), "手机号格式不正确");
		Map<String, String> params = new HashMap<String, String>();
		params.put("busiCode", func.getCode());
		params.put("mobile",  phone);
		params.put("userCode", userCode);
		params.put("verifyCode", smsCode);
		
		// 验证短信验证码
		String smsKey = getSmsKey(userCode, phone, func);
		String _smsCode = redisService.get(smsKey);
		boolean success = smsCode.equals(_smsCode);
		if(success) {
			redisService.del(smsKey);
		}
		LOGGER.info("验证码验证结果："+success);
		return success;
//		final long startTime = System.currentTimeMillis();
//		try {
//			LOGGER.info("校验短信验证码，入参：{}", DesensitionUtil.desJSONString(params));
//			String respStr = HttpUtil.httpPostForm(CHECK_URL, params, null);
//			LOGGER.info("校验短信验证码, 返回结果[{}]", respStr);
//			if(StringUtils.isBlank(respStr)) {
//				LOGGER.info("校验短信验证码接口返回空数据");
//				return false;
//			}
//			JSONObject jsonObject = JSONObject.parseObject(respStr);
//			String code = jsonObject.getString("code");
//			if("00".equals(code)){
//				return true;
//			} else {
//				return false;
//			}
//		} catch (Exception e) {
//			LOGGER.warn("校验短信验证码送失败："+e.getMessage(), e);
//		} finally {
//			LOGGER.info("校验短信验证码-耗时: {}", StringUtil.costTime(System.currentTimeMillis()-startTime));
//		}
//		return false;
	}
	
	/**
	 * 发送短信
	 * @param userCode
	 * @param phone
	 * @param func
	 * @param arguments 需要替换到短信模板中的入参
	 * @return
	 */
	public static boolean sendSms(String userCode, String phone, ShortMsgFuncEnum func, Object ... arguments) {
		REDAssert.notBlank(userCode, "用户号不能为空");
		REDAssert.notBlank(phone, "手机号不能为空");
		REDAssert.notNull(func, "功能代码不能为空");
		REDAssert.isTrue(DataFormCheck.isPhone(phone), "手机号格式不正确");
		try {
			if(func.needValideCode) {
				return sendSmsCode(userCode, phone, func, arguments);
			} else {
				return sendSmsMsg(userCode, phone, func, arguments);
			}
		} catch (Exception e) {
			LOGGER.error("短信发送失败："+e.getMessage(), e);
			return false;
		}
	}
	
	/**
	 * 发送短信验证码
	 * @param userCode 用户号
	 * @param phone 手机号
	 * @param func 功能模块
	 * @param dataTemplate 短信内容
	 * @return
	 * @throws Exception 
	 */
	private static boolean sendSmsCode(String userCode, String phone, ShortMsgFuncEnum func, Object ... arguments) throws Exception {
		String smsCode = RandomUtil.generateString(6, RandomUtil.SCOPE_SNSCODE);
		String data = func.formatDataTemplate(smsCode, SMSCODE_TIMEOUT);
		if(!func.isSync()) {
			try {
				LOGGER.info("异步发送短信验证码");
				String curLoggerId = MDC.get("LOGGER_ID");
				String loggerId = StringUtils.isBlank(curLoggerId) ? userCode : curLoggerId;
				BizThreadUtil.getThreadUtil().execute(new Runnable() {
					@Override
					public void run() {
						MDC.put("LOGGER_ID", loggerId);
						sendSmsCode(userCode, phone, func, smsCode, data);
					}
				});
			} catch (IllegalArgumentException e) {
				LOGGER.warn("启动异步发送线程失败，改为同步发送", e);
				return sendSmsCode(userCode, phone, func, smsCode, data);
			}
		} else {
			return sendSmsCode(userCode, phone, func, smsCode, data);
		}
		return true;
	}
	
	private static boolean sendSmsCode(String userCode, String phone, ShortMsgFuncEnum func, String smsCode, String data) {
		Map<String, String> params = new HashMap<String, String>();
//		params.put("areaCode", func.getAreaCode());
		params.put("mobile", phone);
		params.put("source", SOURCE);
		params.put("usercode", userCode);
		params.put("msgContent", data);
		params.put("bizCode", func.getCode());

		LOGGER.info("发送短信验证码，入参：{}", DesensitionUtil.desJSONString(params));
		boolean success = sendShortMsg(params);
		if(success) {
			String smsKey = getSmsKey(userCode, phone, func);
			redisService.set(smsKey, smsCode, SMSCODE_TIMEOUT, TimeUnit.MINUTES);
		}
		return success;
	}
	
	/**
	 * 发送短信消息
	 * @param userCode 用户号
	 * @param phone 手机号
	 * @param func 功能模块
	 * @param dataTemplate 短信内容
	 * @return
	 * @throws Exception 
	 */
	private static boolean sendSmsMsg(String userCode, String phone, ShortMsgFuncEnum func, Object ... arguments) throws Exception {
		String data = func.formatDataTemplate(arguments);
		Map<String, String> params = new HashMap<String, String>();
//		params.put("areaCode", func.getAreaCode());
		params.put("mobile", phone);
		params.put("source", SOURCE);
		params.put("usercode", userCode);
		params.put("msgContent", data);
		params.put("bizCode", func.getCode());
		
		if(ShortMsgFuncEnum.REGIST_SUCCESS == func) {
			LOGGER.info("发送短信消息，入参：{}", DesensitionUtil.desJSONString(params).replaceAll("^(.+#code#=).{6,8}(\",.+)$", "$1******$2"));
		} else {
			LOGGER.info("发送短信消息，入参：{}", DesensitionUtil.desJSONString(params));
		}
		if(!func.isSync()) {
			try {
				LOGGER.info("异步发送短信消息");
				String curLoggerId = MDC.get("LOGGER_ID");
				String loggerId = StringUtils.isBlank(curLoggerId) ? userCode : curLoggerId;
				BizThreadUtil.getThreadUtil().execute(new Runnable() {
					@Override
					public void run() {
						MDC.put("LOGGER_ID", loggerId);
						sendShortMsg(params);
					}
				});
			} catch (IllegalArgumentException e) {
				LOGGER.warn("启动异步发送线程失败，改为同步发送", e);
				return sendShortMsg(params);
			}
		} else {
			return sendShortMsg(params);
		}
		return true;
	}
	
	private static boolean sendShortMsg(Map<String, String> params) {
		final long startTime = System.currentTimeMillis();
		try {
			String respStr = HttpUtil.httpPostForm(SEND_URL, params, null);
			LOGGER.info("发送短信，返回结果：{}", respStr);
			if(StringUtils.isBlank(respStr)) {
				LOGGER.info("发送短信接口返回空数据");
				return false;
			}
			JSONObject jsonObject = JSONObject.parseObject(respStr);
			String code = jsonObject.getString("code");
			if ("00".equals(code)) {
				LOGGER.info("短信发送成功！");
				return true;
			} else {
				LOGGER.info("短信发送失败！");
				return false;
			}
		} catch (Exception e) {
			if(e instanceof REDException) {
				LOGGER.info("短信发送失败："+e.getMessage());
			} else {
				LOGGER.warn("短信发送失败："+e.getMessage(), e);
			}
		} finally {
			LOGGER.info("发送短信-耗时: {}", StringUtil.costTime(System.currentTimeMillis()-startTime));
		}
		return false;
	}
	
	private static String getSmsKey(String userCode, String phone, ShortMsgFuncEnum func) {
		return String.format("%s%s_%s_%s", SystemConfig.SYSTEM_REDIS_PREFIX, userCode, phone, func.ordinal());
	}
	
}
