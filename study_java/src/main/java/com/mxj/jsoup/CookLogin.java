package com.mxj.jsoup;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class CookLogin {

	public static void main(String[] args) {
		//登录实验楼后拿到cookies，设置到请求connect中
		
		// _ga=GA1.2.2018048988.1502417217;
		// session=7fbc5b0b-8bf6-4260-871b-05f3a098ad27.LkdK6yUppqcCEBf3mvjaZqJh0fo;
		// _gid=GA1.2.1795199852.1513071333
		
		String domain= "https://www.shiyanlou.com";
		String url = "https://www.shiyanlou.com/courses/9/labs/2416/document";
		Map<String, String> cookies = new HashMap<String, String>();
		cookies.put("_ga", "GA1.2.2018048988.1502417217");
		cookies.put("session", "7fbc5b0b-8bf6-4260-871b-05f3a098ad27.LkdK6yUppqcCEBf3mvjaZqJh0fo");
		cookies.put("_gid", "GA1.2.1795199852.1513071333");
		
		try {
			Document document = Jsoup.connect(url).cookies(cookies)
					.referrer("https://www.shiyanlou.com/courses/9/labs/2416/document").get();
			
			String attr = document.getElementById("jinja-data").attr("data-courses-doc");
			String secondUrl=domain+attr;
			Document document2 = Jsoup.connect(secondUrl).ignoreContentType(true).cookies(cookies)
					.referrer(secondUrl).get();
			
			System.out.println(document2.toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
