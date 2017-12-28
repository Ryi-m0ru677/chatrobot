package jice.vigortech.chat.robot.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class HttpHelper {
	
	private static Logger logger = LoggerFactory.getLogger(HttpHelper.class);
	
	public static String httpGet(String url, JSONObject params, JSONObject headers) throws Exception {
		logger.info("reqest url:" + url + ";params:" + JSON.toJSONString(params));
		StringBuilder urlSb = new StringBuilder();
		/*if(url.indexOf("?") > -1) {
			urlSb.append(url);
		} else {
			urlSb.append(url).append("?");
		}
		if(params != null) {
			for(String key : params.keySet()) {
				urlSb.append("&").append(key).append("=").append(params.get(key));
			}
		}*/
		logger.info("get request url:" + urlSb.toString());
		HttpClient client = HttpClientBuilder.create()
				.build();
		/*ClientConnectionManager connManager = new PoolingClientConnectionManager();
		DefaultHttpClient client = new DefaultHttpClient(connManager);*/
		
		HttpGet get = new HttpGet(urlSb.toString());
		/*if(headers != null) {
			for(String header : headers.keySet()) {
				get.addHeader(header, headers.getString(header));
			}
		}*/
		HttpResponse response = client.execute(get);
		return parseResponse(response);
	}
	
	public static String httpPostWithStringStream(String url, JSONObject params, JSONObject headers) throws Exception {
		HttpEntity entity = null;
		if(params != null && !params.isEmpty()) {
			entity = new StringEntity(params.toString(), Charset.forName("utf-8"));  
		}
		return httpPost(url, entity, headers);
	}
	

	public static String httpPostWithKeyValueStream(String url, JSONObject params, JSONObject headers) throws Exception {
		HttpEntity entity = null;
		if(params != null) {
			List<NameValuePair> list = new ArrayList<NameValuePair>();  
			Iterator<Entry<String, Object>> iterator = params.entrySet().iterator();  
			while(iterator.hasNext()){  
				Entry<String,Object> elem = (Entry<String, Object>) iterator.next();  
				list.add(new BasicNameValuePair(elem.getKey(),String.valueOf(elem.getValue())));  
			}  
			if(list.size() > 0){  
				entity = new UrlEncodedFormEntity(list, Charset.forName("utf-8"));  
			}
		}
		return httpPost(url, entity, headers);
	}
	
	private static String httpPost(String url, HttpEntity entity, JSONObject headers) throws Exception {
		logger.info("post request url:" + url);
		HttpClient client = HttpClientBuilder.create()
				.build();
		HttpPost post = new HttpPost(url);
		if(entity != null) {
			post.setEntity(entity);
		}
		if(headers != null) {
			for(String header : headers.keySet()) {
				post.addHeader(header, headers.getString(header));
			}
		}
		HttpResponse response = client.execute(post);
		return parseResponse(response);
	}	
	private static String parseResponse(HttpResponse response) throws ParseException, IOException {
		if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
			return "error";
		}
		return EntityUtils.toString(response.getEntity());
	}
	
	
	//处理http请求  requestUrl为请求地址  requestMethod请求方式，值为"GET"或"POST"  
    public static String httpRequest(String requestUrl,String requestMethod,String outputStr){ 
    	logger.info("post request url:" + requestUrl);
        StringBuffer buffer=null;  
        try{ 
        	//requestUrl = URLEncoder.encode(requestUrl, "iso8859-1");
        URL url=new URL(requestUrl);  
        HttpURLConnection conn=(HttpURLConnection)url.openConnection(); 
        System.out.println(conn.toString());
        conn.setDoOutput(true);  
        conn.setRequestProperty("contentType", "utf-8");  
        conn.setRequestProperty("Accept-Charset", "utf-8");  
        conn.setDoInput(true);  
        conn.setRequestMethod(requestMethod);  
        conn.connect();  
        //往服务器端写内容 也就是发起http请求需要带的参数  
        if(null!=outputStr){  
            OutputStream os=conn.getOutputStream();  
            os.write(outputStr.getBytes("utf-8"));  
            os.close();  
        }  
          
        //读取服务器端返回的内容  
        InputStream is=conn.getInputStream();  
        InputStreamReader isr=new InputStreamReader(is,"utf-8");  
        BufferedReader br=new BufferedReader(isr);  
        buffer=new StringBuffer();  
        String line=null;  
        while((line=br.readLine())!=null){  
            buffer.append(line);  
        }  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
        return buffer.toString();  
    }  
}
