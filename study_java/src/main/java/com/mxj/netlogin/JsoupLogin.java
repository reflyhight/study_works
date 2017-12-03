package com.mxj.netlogin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


/**
 * 骗人，根本没有登录成功
 * @author Rob Jiang
 * @dat 2017年12月4日
 * @email jh624haima@126.com
 * @blog blog.mxjhaima.com
 */
public class JsoupLogin {

	public static void main(String[] args) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", "649766951@qq.com");
		map.put("password", "jh624@126.com");

		Response response = Jsoup.connect("https://account.sogou.com/web/login").data(map).method(Method.POST)
				.timeout(200000).execute();
		
		Map<String, String> cookies=null;
		

		
		if(response.statusCode()==200){
			 cookies = response.cookies();
			 System.out.println("login sucess");
		}
		
		
		Document document = Jsoup.connect("https://account.sogou.com/").cookies(cookies).get();
		System.out.println(document.toString());
		
	}
}
