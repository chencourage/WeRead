package com.weread.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.weread.common.exception.YSErrorCode;
import com.weread.common.exception.YSException;


/**
 * @author	@version		@operation	@desc	 
 * chenkai	201501211000	add			http util
 */
public class HttpUtil {
	private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);
	
	private final static String IE11   ="MSIE 11.0"; 
	private final static String IE10   ="MSIE 10.0"; 
	private final static String IE9    ="MSIE 9.0";  
    private final static String IE8    ="MSIE 8.0";  
    private final static String IE7    ="MSIE 7.0";  
    private final static String IE6    ="MSIE 6.0";  
    private final static String IE     ="MSIE";
    private final static String MAXTHON="Maxthon";  
    private final static String QQ="QQBrowser";  
    private final static String GREEN="GreenBrowser";  
    private final static String SE360="360SE";  
    private final static String FIREFOX="Firefox";  
    private final static String OPERA="Opera";  
    private final static String CHROME="Chrome";  
    private final static String SAFARI="Safari";  
    private final static String OTHER="其它";  
    
    private static final int MAX_TIMEOUT = 30000;
	    
	/**
	 * 获取客户端ip
	 * @param request
	 * @return
	 */
	public static  String getIpAddress(HttpServletRequest request) {  
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址  
        String ip = request.getHeader("X-Forwarded-For");  
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {  
            if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("Proxy-Client-IP");  
            }  
            if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("WL-Proxy-Client-IP");  
            }  
            if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("HTTP_CLIENT_IP");  
            }  
            if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
            }  
            if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getRemoteAddr();  
            }  
            if(StringUtils.isNotBlank(ip)){
            	ip = ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
            }
        }else if (ip.length() > 15) {  
            String[] ips = ip.split(",");  
            for (int index = 0; index < ips.length; index++) {  
                String strIp = (String) ips[index];  
                if (!("unknown".equalsIgnoreCase(strIp))) {  
                    ip = strIp;  
                    break;  
                }  
            }  
        }  
//        log.info("getIpAddress.getIp {}",ip);
        return ip;  
    }  
	
	/**
	 * session attribute是否存在key的value
	 * @param req :request
	 * @param key :key
	 * @return boolean
	 */
	public static boolean isNullSesAttr(HttpServletRequest req,String key){
		if(req.getSession()==null) return true;
		return req.getSession().getAttribute(key)==null;
	}
	/**
	 * 是否不为空 session.attribute(key)
	 * @param req
	 * @param key
	 * @return
	 */
	public static boolean isNotNullSesAttr(HttpServletRequest req,String key){
		return !isNullSesAttr(req, key);
	}
	/**
	 * 获取session.attribute(key)
	 * @param req
	 * @param key
	 * @return
	 */
	public static Object getSesAttr(HttpServletRequest req,String key){
		return req.getSession().getAttribute(key);
	}
	/**
	 * set session.attribute(key)
	 * @param req
	 * @param key
	 * @return
	 */
	public static void  setSesAttr(HttpServletRequest req,String key,Object value){
		req.getSession().setAttribute(key,value);
	}
	/**
	 * 获取basepath
	 * @param req
	 * @return
	 */
	public static String getBashPath(HttpServletRequest req){
		return req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath();
	}
	
	/**
	 * 获取访问路径
	 * @param req
	 * @return
	 */
	public static String getAccessPath(HttpServletRequest req){
		String spath = req.getServletPath();
		if(spath.startsWith("/")){
			spath = spath.substring(1, spath.length());
		}
		spath = spath.substring(0, spath.lastIndexOf("."));
		return spath;
	}
	
	/**
	 * 是否是ajax请求
	 * @param req
	 * @return
	 */
	public static boolean isAjaxRequest(HttpServletRequest req){
		boolean flag = false;
		String xReqWith = req.getHeader("X-Requested-With");
		if(StringUtils.isNotBlank(xReqWith)){
			if (xReqWith != null && xReqWith.equals("XMLHttpRequest")) {
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * map组装url
	 * @param map
	 * @return
	 */
	public static String getParamUrl(Map<String,String[]> map){
		StringBuilder params = new StringBuilder();
		//拼接参数
		if(map!=null && map.size()>0){
	        Iterator<Map.Entry<String, String[]>> iter = map.entrySet().iterator();
	        while (iter.hasNext())
	        {
	            Map.Entry<String, String[]> entry = iter.next();
	            String[] value = entry.getValue();
	            for (String v : value)
	            {
	                params.append(entry.getKey()).append("=").append(v).append("&");
	            }
	        }
	        if (params.length() > 0)
	        {
	            params.delete(params.length() - 1, params.length());
	        }
		}
		//url转码
//		String p = params.toString();
//		if(p.length()>0){
//			try {
//				p = URLEncoder.encode(p.toString(),"UTF-8");
//			} catch (UnsupportedEncodingException e) {
//				logger.catching(e);
//			}
//		}
        return params.toString();
	}
	
	public static String getOs(HttpServletRequest req){
		String os = "";
		try{
			os = getOs(req.getHeader("User-Agent"));
		}catch(Exception e){
			log.error("get Os error->{}",req.getHeader("User-Agent"));
		}
		return os;
	}
	public static String getOs(String userAgent){
		if(regex(CHROME, userAgent)){
			StringTokenizer st = new StringTokenizer(userAgent, "()");
			st.nextToken();
			return st.nextToken();
		}else if(regex(FIREFOX, userAgent)){
			StringTokenizer st = new StringTokenizer(userAgent, "()");
			st.nextToken();
			String os = st.nextToken();
			if(os.indexOf(";")!=-1){
				os = os.substring(0,os.indexOf(";"));
			}
			return os;
		}else if(regex(IE, userAgent)){
			StringTokenizer st = new StringTokenizer(userAgent, ";");
			st.nextToken();
			st.nextToken();
			return st.nextToken();
		}
		return null;
	}
	public static String getBrowerVersion(HttpServletRequest req) {
		try{
			String userAgent = req.getHeader("User-Agent");
	        if(regex(OPERA, userAgent))return OPERA;  
	        if(regex(CHROME, userAgent)){ 
	        	// chrome浏览器获取操作系统和客户端浏览器
	        	int start = userAgent.indexOf(CHROME)+(CHROME.length()+1);
	        	int end = userAgent.indexOf(" ",start);
	        	return userAgent.substring(start,end);
	        }
	        if(regex(FIREFOX, userAgent)){
	        	int start = userAgent.indexOf(FIREFOX)+(FIREFOX.length()+1);
	        	int end = userAgent.indexOf(" ",start);
	        	if(end==-1){
	        		end = userAgent.length();
	        	}
	        	return userAgent.substring(start,end);  
	        }
//	        if(regex(SAFARI, userAgent))return SAFARI;  
//	        if(regex(SE360, userAgent))return SE360;  
//	        if(regex(GREEN,userAgent))return GREEN;  
//	        if(regex(QQ,userAgent))return QQ;  
//	        if(regex(MAXTHON, userAgent))return MAXTHON;  
	        if(regex(IE11,userAgent))return "11.0";
	        if(regex(IE10,userAgent))return "10.0";
	        if(regex(IE9,userAgent))return "9.0";  
	        if(regex(IE8,userAgent))return "8.0";  
	        if(regex(IE7,userAgent))return "7.0";  
	        if(regex(IE6,userAgent))return "6.0";
		}catch(Exception e){
			log.error("get getBrowerVersion error->{}",req.getHeader("User-Agent"));
		}
        return OTHER;  
	}
	
	public static String getBrowerType(HttpServletRequest req) {
		try{
			String userAgent = req.getHeader("User-Agent");
	        if(regex(OPERA, userAgent))return OPERA;  
	        if(regex(CHROME, userAgent))return CHROME;  
	        if(regex(FIREFOX, userAgent))return FIREFOX;  
	        if(regex(SAFARI, userAgent))return SAFARI;  
	        if(regex(SE360, userAgent))return SE360;  
	        if(regex(GREEN,userAgent))return GREEN;  
	        if(regex(QQ,userAgent))return QQ;  
	        if(regex(MAXTHON, userAgent))return MAXTHON;
	        if(regex(IE11,userAgent))return IE11;
	        if(regex(IE10,userAgent))return IE10;
	        if(regex(IE9,userAgent))return IE9;
	        if(regex(IE8,userAgent))return IE8;  
	        if(regex(IE7,userAgent))return IE7;  
	        if(regex(IE6,userAgent))return IE6;  
		}catch(Exception e){
			log.error("get getBrowerVersion error->{}",req.getHeader("User-Agent"));
		}
        return OTHER;  
	}
	private static boolean regex(String regex,String str){  
        Pattern p =Pattern.compile(regex,Pattern.MULTILINE);  
        Matcher m=p.matcher(str);  
        return m.find();  
    }
	
	
	/**
	 * @param url			请求地址
	 * @param params		请求参数
	 * @param filename		附件名称
	 * @param inputStream	输入流
	 * @param ovvrTimeOut	超时时间
	 * @return
	 */
	public static String httpPostMulti(String url, Map<String, String> params, String filename, InputStream inputStream, Integer ovvrTimeOut) {
		String responseStr = null;
		HttpResponse response = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(ovvrTimeOut != null ? ovvrTimeOut : MAX_TIMEOUT).setConnectTimeout(MAX_TIMEOUT).setSocketTimeout(MAX_TIMEOUT).build();
			httpPost.setConfig(requestConfig);
			InputStreamBody inputStreamBody = new InputStreamBody(inputStream, filename);

			MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create().addPart(filename, inputStreamBody);
			if (null != params && !params.isEmpty()) {
				ContentType contentType = ContentType.create("text/plain", Consts.UTF_8);
				for (Map.Entry<String, String> entity : params.entrySet()) {
					multipartEntityBuilder.addTextBody(entity.getKey(), entity.getValue(), contentType);
				}
			}
			HttpEntity reqEntity = multipartEntityBuilder.build();

			httpPost.setEntity(reqEntity);
			log.info("[httpclient->url:" + url + "]请求参数:" + JSON.toJSONString(params));
			response = HttpClients.createDefault().execute(httpPost);
			HttpEntity entity = response.getEntity();
			responseStr = EntityUtils.toString(entity, Consts.UTF_8); 
			log.info("http返回码:" + response.getStatusLine().getStatusCode());
			log.info("http返回参数:" + responseStr);
		} catch (Exception e) {
			log.error("系统异常："+e.getMessage(), e);
			throw new YSException(YSErrorCode.HTTP_ERROR);	
		} finally {
			if (response != null) {  
                try {  
                    EntityUtils.consume(response.getEntity());  
                } catch (IOException e) {  
                	log.error("IO异常", e);
                }  
            }
		}
		return responseStr;
	}
	
	/**
	 * @param url			请求地址
	 * @param params		请求参数
	 * @param filename		附件名称
	 * @param fileBytes		附件字节流
	 * @param ovvrTimeOut	超时时间
	 * @return
	 */
	public static String httpPostMulti(String url, Map<String, String> params, String filename, byte[] fileBytes, Integer ovvrTimeOut) {
		String responseStr = null;
		HttpResponse response = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(ovvrTimeOut != null ? ovvrTimeOut : MAX_TIMEOUT).setConnectTimeout(MAX_TIMEOUT).setSocketTimeout(MAX_TIMEOUT).build();
			httpPost.setConfig(requestConfig);

			ByteArrayBody byteArrayBody = new ByteArrayBody(fileBytes, filename);
			MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create().addPart(filename, byteArrayBody);
			if (null != params && !params.isEmpty()) {
				ContentType contentType = ContentType.create("text/plain", Consts.UTF_8);
				for (Map.Entry<String, String> entity : params.entrySet()) {
					multipartEntityBuilder.addTextBody(entity.getKey(), entity.getValue(), contentType);
				}
			}
			HttpEntity reqEntity = multipartEntityBuilder.build();

			httpPost.setEntity(reqEntity);
			log.debug("[httpclient->url:" + url + "]请求参数:" + JSON.toJSONString(params));
			response = HttpClients.createDefault().execute(httpPost);
			HttpEntity entity = response.getEntity();
			responseStr = EntityUtils.toString(entity, Consts.UTF_8); 
			log.debug("http返回参数:" + responseStr);
		} catch (Exception e) {
			log.error("系统异常："+e.getMessage(), e);
			throw new YSException(YSErrorCode.HTTP_ERROR);
		} finally {
			if (response != null) {  
                try {  
                    EntityUtils.consume(response.getEntity());  
                } catch (IOException e) {  
                	log.error("IO异常", e);
                }  
            }  
		}
		return responseStr;
	}
	
	/**
	 * @param url			请求地址
	 * @param params		请求参数
	 * @param filename		附件名称
	 * @param inputStream	输入流
	 * @param ovvrTimeOut	超时时间
	 * @return
	 */
	public static String httpPostMulti(String url, Map<String, String> params, Map<String, InputStream> fileMap, List<String> uploadNameList, Integer ovvrTimeOut) {
		String responseStr = null;
		HttpResponse response = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(ovvrTimeOut != null ? ovvrTimeOut : MAX_TIMEOUT).setConnectTimeout(MAX_TIMEOUT).setSocketTimeout(MAX_TIMEOUT).build();
			httpPost.setConfig(requestConfig);
			
			MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
			if(null != fileMap && fileMap.size() > 0) {
				int i = 0;
				for(Map.Entry<String, InputStream> entity : fileMap.entrySet()) {
					InputStreamBody inputStreamBody = new InputStreamBody(entity.getValue(), entity.getKey());
					String uploadName = entity.getKey();
					if(null != uploadNameList && uploadNameList.size() > i) {
						uploadName = uploadNameList.get(i++);
					}
					multipartEntityBuilder.addPart(uploadName, inputStreamBody);
				}
			}

			if (null != params && !params.isEmpty()) {
				ContentType contentType = ContentType.create("text/plain", Consts.UTF_8);
				for (Map.Entry<String, String> entity : params.entrySet()) {
					multipartEntityBuilder.addTextBody(entity.getKey(), entity.getValue(), contentType);
				}
			}
			HttpEntity reqEntity = multipartEntityBuilder.build();

			httpPost.setEntity(reqEntity);
			log.info("[httpclient->url:" + url + "]请求参数:" + JSON.toJSONString(params));
			response = HttpClients.createDefault().execute(httpPost);
			HttpEntity entity = response.getEntity();
			responseStr = EntityUtils.toString(entity, Consts.UTF_8); 
			log.info("http返回码:" + response.getStatusLine().getStatusCode());
			log.info("http返回参数:" + responseStr);
		} catch (Exception e) {
			log.error("系统异常："+e.getMessage(), e);
			throw new YSException(YSErrorCode.HTTP_ERROR);	
		} finally {
			if (response != null) {  
                try {  
                    EntityUtils.consume(response.getEntity());  
                } catch (IOException e) {  
                	log.error("IO异常", e);
                }  
            }
		}
		return responseStr;
	}
	
	public static void downloadFile(String url, Map<String, String> params, OutputStream out, Integer ovvrTimeOut) {
    	try {
			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(ovvrTimeOut != null ? ovvrTimeOut : MAX_TIMEOUT).setConnectTimeout(MAX_TIMEOUT).setSocketTimeout(MAX_TIMEOUT).build();
			
//			URIBuilder ub = new URIBuilder();  
//			ub.setPath(url); 
//			if(params!=null) {
//				List<NameValuePair> pairs = covertParams2NVPS(params);  
//			    ub.setParameters(pairs);
//			}
			if(null != params && !params.isEmpty()) {
				StringBuilder paramStr = new StringBuilder();
				for(Map.Entry<String, String> entity : params.entrySet()) {
					paramStr.append(entity.getKey()).append("=").append(entity.getValue()).append("&");
				}
				if(url.indexOf("?") != -1) {
					url = url + "&" + paramStr.deleteCharAt(paramStr.length()-1).toString();
				} else {
					url = url + "?" + paramStr.deleteCharAt(paramStr.length()-1).toString();
				}
			}
			
			HttpGet httpGet = new HttpGet(url);
			httpGet.setConfig(requestConfig);
			
			
	        CloseableHttpResponse response = HttpClients.createDefault().execute(httpGet);  
	        HttpEntity entity = response.getEntity(); 
	        if(entity!=null) {
	        	IOUtils.copy(entity.getContent(), out);
	        }
	        response.close();
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
	}
	
	public static byte[] downloadFile(String url, Map<String, String> params, Integer ovvrTimeOut) {
    	try {
			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(ovvrTimeOut != null ? ovvrTimeOut : MAX_TIMEOUT).setConnectTimeout(MAX_TIMEOUT).setSocketTimeout(MAX_TIMEOUT).build();
			
//			URIBuilder ub = new URIBuilder();  
//			ub.setPath(url); 
//			if(params!=null) {
//				List<NameValuePair> pairs = covertParams2NVPS(params);  
//			    ub.setParameters(pairs);
//			}
			if(null != params && !params.isEmpty()) {
				StringBuilder paramStr = new StringBuilder();
				for(Map.Entry<String, String> entity : params.entrySet()) {
					paramStr.append(entity.getKey()).append("=").append(entity.getValue()).append("&");
				}
				if(url.indexOf("?") != -1) {
					url = url + "&" + paramStr.deleteCharAt(paramStr.length()-1).toString();
				} else {
					url = url + "?" + paramStr.deleteCharAt(paramStr.length()-1).toString();
				}
			}
			
			HttpGet httpGet = new HttpGet(url);
			httpGet.setConfig(requestConfig);
			
			
	        CloseableHttpResponse response = HttpClients.createDefault().execute(httpGet);  
	        HttpEntity entity = response.getEntity(); 
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        if(entity!=null) {
	        	IOUtils.copy(entity.getContent(), baos);
	        }
	        response.close();
	        return baos.toByteArray();
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
    	return null;
	}
	
//	private static ArrayList<NameValuePair> covertParams2NVPS(Map<String, String> params) {  
//        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();  
//        if(params!=null)
//	        for (Map.Entry<String, String> param : params.entrySet()) {  
//	            pairs.add(new BasicNameValuePair(param.getKey(), param.getValue()));  
//	        }
//  
//        return pairs;
//    }
	
	/**
	 * @param url			请求地址
	 * @param params		请求参数
	 * @param ovvrTimeOut	超时时间
	 * @return
	 */
	public static String httpPostForm(String url, Map<String, String> params, Integer ovvrTimeOut) {
		String responseStr = null;
		HttpResponse response = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			RequestConfig.Builder builder = RequestConfig.custom();
			if(null == ovvrTimeOut) ovvrTimeOut = MAX_TIMEOUT;
			builder.setConnectionRequestTimeout(ovvrTimeOut).setConnectTimeout(ovvrTimeOut).setSocketTimeout(ovvrTimeOut);
			RequestConfig requestConfig = builder.build();
			httpPost.setConfig(requestConfig);

			MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
			if (null != params && !params.isEmpty()) {
				ContentType contentType = ContentType.create("text/plain", Consts.UTF_8);
				for (Map.Entry<String, String> entity : params.entrySet()) {
					multipartEntityBuilder.addTextBody(entity.getKey(), entity.getValue(), contentType);
				}
			}
			HttpEntity reqEntity = multipartEntityBuilder.build();

			httpPost.setEntity(reqEntity);
//			log.info("[httpclient->url:" + url + "]请求参数:" + DesensitionUtil.desJSONString(params));
			response = HttpClients.createDefault().execute(httpPost);
			HttpEntity entity = response.getEntity();
			responseStr = EntityUtils.toString(entity, Consts.UTF_8); 
//			log.info("http返回码:" + response.getStatusLine().getStatusCode());
//			log.info("http返回参数:" + responseStr);
		} catch (Exception e) {
			log.error("系统异常："+e.getMessage(), e);
			throw new YSException(YSErrorCode.HTTP_ERROR);	
		} finally {
			if (response != null) {  
                try {  
                    EntityUtils.consume(response.getEntity());  
                } catch (IOException e) {  
                	log.error("IO异常", e);
                }  
            }
		}
		return responseStr;
	}
	
	/**
	 * @param url			请求地址
	 * @param params		请求参数
	 * @param ovvrTimeOut	超时时间
	 * @return
	 */
	public static String httpPostJson(String url, String param, Integer ovvrTimeOut) {
		String responseStr = null;
		HttpResponse response = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			RequestConfig.Builder builder = RequestConfig.custom();
			if(null == ovvrTimeOut) ovvrTimeOut = MAX_TIMEOUT;
			builder.setConnectionRequestTimeout(ovvrTimeOut).setConnectTimeout(ovvrTimeOut).setSocketTimeout(ovvrTimeOut);
			RequestConfig requestConfig = builder.build();
			httpPost.setConfig(requestConfig);
			httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
		    StringEntity stringEntity = new StringEntity(param, "utf-8");
		    httpPost.setEntity(stringEntity);

//			log.info("[httpclient->url:" + url + "]请求参数:" + DesensitionUtil.desJSONString(param));
			response = HttpClients.createDefault().execute(httpPost);
			HttpEntity entity = response.getEntity();
			responseStr = EntityUtils.toString(entity, Consts.UTF_8); 
			log.info("http返回码:" + response.getStatusLine().getStatusCode());
//			log.info("http返回参数:" + responseStr);
		} catch (Exception e) {
			log.error("系统异常："+e.getMessage(), e);
			throw new YSException(YSErrorCode.HTTP_ERROR);	
		} finally {
			if (response != null) {  
                try {  
                    EntityUtils.consume(response.getEntity());  
                } catch (IOException e) {  
                	log.error("IO异常", e);
                }  
            }
		}
		return responseStr;
	}
	
	/**
	 * @param url			请求地址
	 * @param ovvrTimeOut	超时时间
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String httpGet(String url, Integer ovvrTimeOut) throws ClientProtocolException, IOException {
		String responseStr = null;
		HttpResponse response = null;
		try {
			HttpGet httpGet = new HttpGet(url);
			RequestConfig.Builder builder = RequestConfig.custom();
			if(null == ovvrTimeOut) ovvrTimeOut = MAX_TIMEOUT;
			builder.setConnectionRequestTimeout(ovvrTimeOut).setConnectTimeout(ovvrTimeOut).setSocketTimeout(ovvrTimeOut);
			RequestConfig requestConfig = builder.build();
			httpGet.setConfig(requestConfig);

			response = HttpClients.createDefault().execute(httpGet);
			HttpEntity entity = response.getEntity();
			responseStr = EntityUtils.toString(entity, Consts.UTF_8); 
		} finally {
			if (response != null) {  
                try {  
                    EntityUtils.consume(response.getEntity());  
                } catch (IOException e) {  
                	log.error("IO异常", e);
                }  
            }
		}
		return responseStr;
	}
    
	
	public static void main(String[] args) {
		String userAgent  ="Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.124 Safari/537.36";
//		String userAgent = "Mozilla/5.0 (Windows NT 5.1; rv:34.0) Gecko/20100101 Firefox/34.0";
//		String userAgent = "User-Agent=Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; Tablet PC 2.0; .NET4.0C; .NET4.0E)";
		System.out.println(getOs(userAgent));
		
	}
}
