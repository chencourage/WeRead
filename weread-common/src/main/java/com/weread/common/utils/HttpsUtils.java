package com.weread.common.utils;
//package com.cdb.agent.common.util;
//
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.net.ConnectException;
//import java.net.URL;
//import java.security.cert.CertificateException;
//import java.security.cert.X509Certificate;
//import java.util.Map;
//
//import javax.net.ssl.HostnameVerifier;
//import javax.net.ssl.HttpsURLConnection;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.SSLSession;
//import javax.net.ssl.SSLSocketFactory;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.X509TrustManager;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//@Component
//public class HttpsUtils {
//
//	private final static Logger LOGGER = LoggerFactory.getLogger(HttpsUtils.class);
//
//	private static final int MAX_TIMEOUT = 30000;
//
//	private static class TrustAnyTrustManager implements X509TrustManager {
//
//		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//		}
//
//		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//		}
//
//		public X509Certificate[] getAcceptedIssuers() {
//			return new X509Certificate[] {};
//		}
//	}
//
//	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
//		public boolean verify(String hostname, SSLSession session) {
//			return true;
//		}
//	}
//
//	public static String sendGet(String url, Integer ovvrTimeOut) throws Exception {
//		if(null == ovvrTimeOut) ovvrTimeOut = MAX_TIMEOUT;
//		String result = "";
//		BufferedReader in = null;
//		try {
//			SSLContext sc = SSLContext.getInstance("SSL");
//			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());
//			URL realUrl = new URL(url);
//			// 打开和URL之间的连接
//			HttpsURLConnection connection = (HttpsURLConnection) realUrl.openConnection();
//			// 设置https相关属性
//			connection.setSSLSocketFactory(sc.getSocketFactory());
//			connection.setHostnameVerifier(new TrustAnyHostnameVerifier());
//			connection.setDoOutput(true);
//			connection.setConnectTimeout(ovvrTimeOut);
//			connection.setReadTimeout(ovvrTimeOut);
//
//			// 设置通用的请求属性
//			connection.setRequestProperty("accept", "*/*");
//			connection.setRequestProperty("connection", "Keep-Alive");
//			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//			// 建立实际的连接
//			connection.connect();
//
//			// 定义 BufferedReader输入流来读取URL的响应
//			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
//			String line;
//			while ((line = in.readLine()) != null) {
//				result += line;
//			}
//			LOGGER.debug("获取的结果为：" + result);
//		} catch (Exception e) {
//			LOGGER.error("发送GET请求出现异常！" +  e.getMessage(), e);
//		} finally {
//			// 使用finally块来关闭输入流
//			try {
//				if (in != null) {
//					in.close();
//				}
//			} catch (Exception e) {
//				LOGGER.error("发送GET请求出现异常！" + e.getMessage(), e);
//			}
//		}
//		return result;
//
//	}
//
//	/**
//	 * 描述: 发起https请求并获取结果
//	 * 
//	 * @param requestUrl    请求地址
//	 * @param requestMethod 请求方式（GET、POST）
//	 * @param outputStr     提交的数据
//	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
//	 */
//	public static String httpRequest(String requestUrl, String requestMethod, String outputStr) {
//
//		String returnStr = null;
//		StringBuffer buffer = new StringBuffer();
//		try {
//			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
//			TrustManager[] tm = { new TrustAnyTrustManager() };
//			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
//			sslContext.init(null, tm, new java.security.SecureRandom());
//			// 从上述SSLContext对象中得到SSLSocketFactory对象
//			SSLSocketFactory ssf = sslContext.getSocketFactory();
//
//			URL url = new URL(requestUrl);
//			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
//			httpUrlConn.setSSLSocketFactory(ssf);
//
//			httpUrlConn.setDoOutput(true);
//			httpUrlConn.setDoInput(true);
//			httpUrlConn.setUseCaches(false);
//
//			// 设置请求方式（GET/POST）
//			httpUrlConn.setRequestMethod(requestMethod);
//
//			if ("GET".equalsIgnoreCase(requestMethod))
//				httpUrlConn.connect();
//
//			// 当有数据需要提交时
//			if (null != outputStr) {
//				OutputStream outputStream = httpUrlConn.getOutputStream();
//				// 注意编码格式，防止中文乱码
//				outputStream.write(outputStr.getBytes("UTF-8"));
//				outputStream.close();
//			}
//
//			// 将返回的输入流转换成字符串
//			InputStream inputStream = httpUrlConn.getInputStream();
//			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
//			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//
//			String str = null;
//			while ((str = bufferedReader.readLine()) != null) {
//				buffer.append(str);
//			}
//			bufferedReader.close();
//			inputStreamReader.close();
//			// 释放资源
//			inputStream.close();
//			inputStream = null;
//			httpUrlConn.disconnect();
//			returnStr = buffer.toString();
//		} catch (ConnectException ce) {
//			LOGGER.error("Weixin server connection timed out.");
//		} catch (Exception e) {
//			LOGGER.error("https request error:{}", e);
//		}
//		return returnStr;
//	}
//	
//	public static void main(String[] args) {
//		try {
//			String xml = "<xml><version>1.0</version><mch_id>xxxx</mch_id><appid>xxxx</appid><openid>xxx</openid><real_name>xxx</real_name><cred_id>xxx</cred_id><cred_type>1</cred_type><access_token>xxx</access_token><nonce_str>ec2316275641faa3aacf3cc599e8730f</nonce_str><sign_type>HMAC-SHA256</sign_type><sign>0CB01533B8C1EF103065174F50BCA001</sign></xml>";
//			String httpResp = HttpsUtils.httpRequest("https://fraud.mch.weixin.qq.com/secsvc/realnameauth", "POST", xml);
////			String httpResp = HttpsUtils.sendGet("https://api.mch.weixin.qq.com/appauth/getaccesstoken?mch_id=MCHID&appid=APPID&openid=OPENID&code=CODE&scope=SCOPE&grant_type=authorization_code&sign_type=HMAC-SHA256&sign=SIGN", null);
//			System.out.println(httpResp);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//}
