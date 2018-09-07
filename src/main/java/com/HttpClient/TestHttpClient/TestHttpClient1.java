package com.HttpClient.TestHttpClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

/**
 * @author zhangmaozhuang
 * @date 2018年9月7日下午1:47:38
 */
public class TestHttpClient1 {
	//带参数的POST请求
	public JSONObject doPost(String url,Map<String,Object>map) throws UnsupportedEncodingException {
		CloseableHttpClient httpClient=HttpClients.createDefault();
		HttpPost httpPost=new HttpPost(url);
		//用于存储返回信息
		JSONObject resultJson=new JSONObject();
		if(map != null) {
			//存放参数的list集合
			List<NameValuePair> params=new ArrayList<NameValuePair>();
			for(Map.Entry<String, Object> entry :map.entrySet()) {
				params.add(new BasicNameValuePair(entry.getKey(), entry.getKey().toString()));
			}
			//创建form表单对象
			UrlEncodedFormEntity form=new UrlEncodedFormEntity(params,"utf-8");
			//讲表单对象设置到httpPost
			httpPost.setEntity(form);
			
			//使用HttpCient发送请求，返回response
			try {
				CloseableHttpResponse response=httpClient.execute(httpPost);
				int statusCode = response.getStatusLine().getStatusCode();
				if (statusCode != HttpStatus.SC_OK) {
	                System.err.println("Method failed:" + response.getStatusLine());
	            }else{
	                String resultStr = EntityUtils.toString(response.getEntity());
	                 resultJson=JSONObject.parseObject(resultStr);
	                System.out.println("resultJSON:"+resultJson);
	            }
				response.close();
				httpClient.close();
				
			} catch (ClientProtocolException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			
		}
		return resultJson;
		
		
		
		
	}
	@Test
	public void test() {
		String url="http://v.juhe.cn/weixin/query?key=ab11874dfe23c8e2cb27d55bf92a498c";
		Map<String,Object>map=new HashMap<String, Object>();
		//map.put("key", "ab11874dfe23c8e2cb27d55bf92a498c");
		//map.put("pno", "");
		//map.put("ps", "");
		//map.put("dtype", "");
		try {
			doPost(url, map);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
