package com.HttpClient.TestHttpClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

/**
 * @author zhangmaozhuang
 * @date 2018年9月7日上午9:32:28
 */
public class TestHttpClient {
	public JSONObject httpPost(String url,JSONObject jsonParam) throws UnsupportedEncodingException {
		//创建一个httpClient实例
		CloseableHttpClient httpClient =HttpClients.createDefault();
		//
		String APPKEY="ab11874dfe23c8e2cb27d55bf92a498c";
		jsonParam.put("APPKEY", APPKEY);
		String s=jsonParam.toString();
		List<NameValuePair> name=new ArrayList<NameValuePair>();
		name.add(new BasicNameValuePair("msg",s));
		JSONObject resultJSON = null;
		HttpPost httpPost = new  HttpPost(url);
		//httpPost.addHeader("Content-Type","application/json; charset=utf-8");
        //httpPost.setHeader("Accept", "application/json");
        httpPost.setEntity(new UrlEncodedFormEntity(name,"UTF-8"));
        try {
			HttpResponse response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed:" + response.getStatusLine());
            }else{
                String resultStr = EntityUtils.toString(response.getEntity());
                 resultJSON=JSONObject.parseObject(resultStr);
                System.out.println("resultJSON:"+resultJSON);
            }
			
		} catch (ClientProtocolException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return resultJSON;
		
		
	}

	@Test
	public void start() throws UnsupportedEncodingException {
		String url = "http://v.juhe.cn/weixin/query?key=ab11874dfe23c8e2cb27d55bf92a498c";
		JSONObject o =new JSONObject();
		httpPost(url, o);
	}
}
