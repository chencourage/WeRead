/*
 * Java Base64 - A pure Java library for reading and writing Base64
 *               encoded streams.
 * 
 * Copyright (C) 2008 Carlo Pelliccia (www.sauronsoftware.it)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.weread.common.secret;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.weread.common.base.SystemConfig;

/**
 * Base64工具类
 * @date 2019年1月12日
 */
public class Base64 {
	
	private static Logger LOGGER = LoggerFactory.getLogger(Base64.class);
	
	public static String encode(byte[] src) {
		return encode(src, SystemConfig.DEF_ENCODE);
	}
	
	public static String encode(byte[] src, String charsetName) {
		try {
			return new String(java.util.Base64.getEncoder().encode(src), charsetName);
		} catch (Exception e) {
			LOGGER.error("Base64编码（字节流）失败，原字符串："+src);
		}
		return "";
	}
	
	public static String encode(String src) {
		try {
			return new String(java.util.Base64.getEncoder().encode(src.getBytes(SystemConfig.DEF_ENCODE)), SystemConfig.DEF_ENCODE);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Base64编码失败，原字符串："+src);
		}
		return "";
	}

	public static String decode(String src) {
		try {
			return new String(java.util.Base64.getDecoder().decode(src.getBytes(SystemConfig.DEF_ENCODE)), SystemConfig.DEF_ENCODE);
		} catch (Exception e) {
			LOGGER.error("Base64解码失败，原字符串："+src);
		}
		return "";
	}
	
	public static byte[] decode(byte[] src) {
		return java.util.Base64.getDecoder().decode(src);
	}
	
	public static byte[] decodeToBytes(String src) {
		try {
			return java.util.Base64.getDecoder().decode(src.getBytes(SystemConfig.DEF_ENCODE));
		} catch (Exception e) {
			LOGGER.error("Base64解码失败，原字符串："+src);
		}
		return null;
	}

}