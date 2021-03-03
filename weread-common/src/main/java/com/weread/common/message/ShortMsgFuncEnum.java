package com.weread.common.message;

import java.text.MessageFormat;

public enum ShortMsgFuncEnum {
    AUTH_LOGIN("00", "#code#={0}&#m#={1,number,#}", true), //授权登录验证码 code-验证码，m-过期时间（分钟）
    WEB_LOGIN("01", "#code#={0}&#m#={1,number,#}", true), //网页登录验证码
    FORGE_PASSWORD("02", "#code#={0}&#m#={1,number,#}", true), //忘记密码验证码
    MERC_REGISTER("03", "#code#={0}&#m#={1,number,#}", true), //商户注册验证码
    REGIST_SUCCESS("04", "#name#={0}&#code#={1}", false), //注册成功通知 name-用户号，code-密码
    AGENT_REGISTER("05", "#code#={0}&#m#={1,number,#}", true), //代理商注册验证码
    UPDATE_PHONE("06", "#code#={0}&#m#={1,number,#}", true), //修改手机号验证码
    UPDATE_PASSWORD("07", "#code#={0}&#m#={1,number,#}", true), //修改密码验证码
    WITHDRAW("08", "#code#={0}&#m#={1,number,#}", true); //提现验证码

    public final String code; //功能代码
    public final String dataTemplate; //数据模板
    public final boolean needValideCode; //是否需要验证码
    public final boolean sync; //是否同步发送短信（默认异步），true-同步请求，等待返回结果， false-异步请求，不等待返回结果
    public final String areaCode = "86";

    private ShortMsgFuncEnum(String code, String dataTemplate, boolean needValideCode) {
        this(code, dataTemplate, needValideCode, false);
    }

    private ShortMsgFuncEnum(String code, String dataTemplate, boolean needValideCode, boolean sync) {
        this.code = code;
        this.dataTemplate = dataTemplate;
        this.needValideCode = needValideCode;
        this.sync = sync;
    }

    /**
     * 获取短信内容
     *
     * @param arguments 需要替换到短信模板中的入参，为空时，直接返回短信模板
     * @return
     * @throws Exception 
     */
    public String formatDataTemplate(Object... arguments) throws Exception {
        if (null != arguments && arguments.length > 0) {
//        	return URLEncoder.encode(MessageFormat.format(this.dataTemplate, arguments), "UTF-8");
        	return MessageFormat.format(this.dataTemplate, arguments);
        } else {
//            return URLEncoder.encode(this.dataTemplate, "UTF-8");
        	return this.dataTemplate;
        }
    }

    public String getCode() {
        return code;
    }

    public String getDataTemplate() {
		return dataTemplate;
	}

	public boolean isSync() {
        return sync;
    }

    public String getAreaCode() {
        return areaCode;
    }
}
