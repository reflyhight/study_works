package com.mxj.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ElementsFinding {

	
	public static void main(String[] args) {
		String html = "<div><p href='xxx'>Lorem ipsum.</p>";
		Document doc = Jsoup.parseBodyFragment(html);
		
		
		//		getElementById(String id)
		//		getElementsByTag(String tag)
		//		getElementsByClass(String className)
		//		getElementsByAttribute(String key)
		//		doc.getElementsByAttributeValueStarting(String key, String valuePrefix)
		Elements element = doc.getElementsByAttribute("href");
		System.out.println(element.toString());
		
		//以hr开头的属性element
		Elements attr = doc.getElementsByAttributeStarting("hr");
		System.out.println(attr.toString());
		
		
		Elements elements = doc.getElementsByAttributeValueStarting("href", "x");
		
		
	}
}
