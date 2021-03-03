package com.weread.common.desensitize;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.serializer.ValueFilter;

/**
 * 在fastjson中使用此过滤器进行脱敏操作
 *
 */
public class ValueDesensitizeFilter implements ValueFilter {
	private final Logger logger = LoggerFactory.getLogger(ValueDesensitizeFilter.class);
	
	private static final ValueDesensitizeFilter MATCH_ANNOTATE = new ValueDesensitizeFilter(true);   //匹配注解和属性名
	private static final ValueDesensitizeFilter MATCH_KEYNAME = new ValueDesensitizeFilter(false);   //不匹配属性名，map除外
	
	private static final Set<String> KEYS_PHONE = new HashSet<String>(Arrays.asList("mobile", "phone")); //手机号匹配key
	private static final Set<String> KEYS_IDENTITYNO = new HashSet<String>(Arrays.asList("idCard", "公民身份号码", "id")); //身份证匹配key
	private static final Set<String> KEYS_BANKCARDNO = new HashSet<String>(Arrays.asList("bankAccountNo", "bankCardNo")); //银行卡号key
	
	private final boolean matchKeyName; //是否匹配key的名称
	
	private ValueDesensitizeFilter(boolean matchKeyName) {
		this.matchKeyName = matchKeyName;
	}
	
	public static ValueDesensitizeFilter getInstal() {
		return MATCH_ANNOTATE;
	}
	public static ValueDesensitizeFilter getInstal2() {
		return MATCH_KEYNAME;
	}

	@Override
	public Object process(Object object, String name, Object value) {
		if (null == value || !(value instanceof String || value instanceof Number) || (value instanceof String && ((String) value).length() == 0)) {
			return value;
		}
		
		if(!(object instanceof Map)) {
			try {
				Field field = object.getClass().getDeclaredField(name);
				Desensitization desensitization;
				if (null != (desensitization = field.getAnnotation(Desensitization.class))) {
					List<String> regular;
					DesensitionType type = desensitization.type();
					switch (type) {
					case CUSTOM:
						regular = Arrays.asList(desensitization.attach());
						break;
					case TRUNCATE:
						regular = truncateRender(desensitization.attach());
						break;
					default:
						regular = Arrays.asList(type.getRegular());
					}
					if (regular.size() > 1) {
						String match = regular.get(0);
						String result = regular.get(1);
						if (null != match && result != null && match.length() > 0) {
							return value.toString().replaceAll(match, result);
						}
					}
				}
			} catch (NoSuchFieldException e) {
				if(logger.isDebugEnabled()) {
					logger.debug("ValueDesensitizeFilter the class {} has no field {}", object.getClass(), name);
				}
			}
		}
		
		if(matchKeyName || object instanceof Map) {
			if(null != name) {
				DesensitionType type = null;
				if(KEYS_PHONE.contains(name)) {
					type = DesensitionType.PHONE;
				} else if(KEYS_IDENTITYNO.contains(name)) {
					type = DesensitionType.IDENTITYNO;
				} else if(KEYS_BANKCARDNO.contains(name)) {
					type = DesensitionType.BANKCARDNO;
				}
				if(null != type) {
					return value.toString().replaceAll(type.getRegular()[0], type.getRegular()[1]);
				}
			}
		}
		
		return value;
	}

	private List<String> truncateRender(String[] attachs) {
		List<String> regular = new ArrayList<>();
		if (null != attachs && attachs.length > 1) {
			String rule = attachs[0];
			String size = attachs[1];
			String template, result;
			if ("0".equals(rule)) {
				template = "^(\\S{%s})(\\S+)$";
				result = "$1";
			} else if ("1".equals(rule)) {
				template = "^(\\S+)(\\S{%s})$";
				result = "$2";
			} else {
				return regular;
			}
			try {
				if (Integer.parseInt(size) > 0) {
					regular.add(0, String.format(template, size));
					regular.add(1, result);
				}
			} catch (Exception e) {
				logger.warn("ValueDesensitizeFilter truncateRender size {} exception", size);
			}
		}
		return regular;
	}
}
