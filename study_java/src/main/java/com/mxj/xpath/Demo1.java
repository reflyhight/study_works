package com.mxj.xpath;

import java.io.IOException;
import java.net.URI;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import org.w3c.dom.html.HTMLCollection;
import org.xml.sax.SAXException;

public class Demo1 {
	
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, XPatherException {
		
		String content = getContentByURL("https://github.com/reflyhight?tab=following");
		HtmlCleaner cleaner = new HtmlCleaner();
		TagNode clean = cleaner.clean(content);
		Object[] evaluateXPath = clean.evaluateXPath("//div[@class='position-relative']/div/div[2]");
		for (Object object : evaluateXPath) {
			TagNode node=(TagNode)object;
			
			 Object[] evaluateXPath2 = node.evaluateXPath("/a/span");
			 if(evaluateXPath2.length==2){
				 TagNode a=	 (TagNode)evaluateXPath2[0];
				 CharSequence gitName = a.getText();
				 
				 TagNode b=	 (TagNode)evaluateXPath2[1];
				 CharSequence gitAlia = b.getText();
				 System.out.println(String.format("git账户：%s,别名：%s", gitName,gitAlia));
			 }
		}
		
		
	}
	
	
	public static String getContentByURL(String url){
		long currentTimeMillis = System.currentTimeMillis();
		HttpClientBuilder builder = HttpClients.custom();
		//String url="https://github.com/reflyhight";
		//HttpHost proxy = new HttpHost("111.79.25.9", 8080);
		//clientbuilder.setProxy(proxy);
		CloseableHttpClient client = builder.build();
		
		HttpGet request = new HttpGet(url);
		CloseableHttpResponse response;
		String content=null;
		try {
			response = client.execute(request);
			
			HttpEntity entity = response.getEntity();
			content= EntityUtils.toString(entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
		return content;
	}

}
