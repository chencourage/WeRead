package com.weread.common.base;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.weread.common.utils.StringUtil;

@Service
public class PropertyUtil implements EnvironmentAware{
	static Logger log = LoggerFactory.getLogger(PropertyUtil.class);
	
	private static Environment environment;
	private static Map<String,String> others = new HashMap<>();

	public static String getProperty(String key) {
		return environment.getProperty(key);
	}
	
	public static String getProperty(String name, String defVal) {
        String val = environment.getProperty(name);
        if(StringUtil.isEmpty(val)) {
        	return defVal;
        }
        return val;
    }
	
	public static boolean getProperty(String name, boolean defVal) {
        String val = environment.getProperty(name);
        if(StringUtil.isNotEmpty(val)) {
        	try {
				return Boolean.parseBoolean(val.trim());
			} catch (Exception e) {
			}
        }
        return defVal;
    }
	
	public static String setExProperty(String key,String value) {
		return others.put(key, value);
	}
	
	public static String getExProperty(String key) {
		return others.get(key);
	}

	@Override
	public void setEnvironment(Environment env) {
		environment = env;
	}
}
