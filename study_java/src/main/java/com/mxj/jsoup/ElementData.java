package com.mxj.jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;

public class ElementData {

	
	public static void main(String[] args) throws IOException {
		String html = "<div><p href='xxx' id='name'>Lorem ipsum.</p>";
		Document doc = Jsoup.parseBodyFragment(html);
		
		
		Element name = doc.getElementById("name");
		//		attr(String key)	获取值的方法
		//		attr(String key, String value)	设置值的方法
		//		attributes() 获取所有的	
		
		Attributes attributes = name.attributes();
		for (Attribute attribute : attributes) {
			String key = attribute.getKey();
			System.out.println(key);
		}
		
		//		attributes() to get all attributes
		//		id(), className() and classNames()
		//		text() to get and text(String value) to set the text content
		//		html() to get and html(String value) to set the inner HTML content
		//		outerHtml() to get the outer HTML value
		//		data() to get data content (e.g. of script and style tags)
		//		tag() and tagName()
		
		
		String id = name.id();
		Tag tag = name.tag();
		String tagName = name.tagName();
		System.out.println(tagName);
		
		//		tagName(String tagName)	直接改变元素
		Element ele = name.tagName("li");
		System.out.println(ele.toString());
		
		String outerHtml = name.outerHtml();
		System.out.println(outerHtml);
		
		
		Element body = doc.body();
		String html2 = body.html();
		System.out.println(html2);
		
		System.out.println("========doc.data==============");
		Document doc2 = Jsoup.connect("https://www.baidu.com/").get();
		System.out.println(doc2.data());
		
	}
}
