package com.weread.service.base.req;

import com.weread.common.desensitize.DesensitionUtil;
import com.weread.common.exception.REDException;

/**
 * @author lisheng
 * @date 2019年1月11日
 */
public class BaseReq {
	
	/**
	 * 参数检查
	 * @throws EDUException
	 */
	public void validate() throws REDException {
		
	}
	
	public String desJSONString() {
		return DesensitionUtil.desJSONString(this);
	}
}
