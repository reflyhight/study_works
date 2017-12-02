package com.mxj.jsoup;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Parsingfile {
	
	
	public static void main(String[] args) {
		File in= new File("E:\\blog\\.deploy_git\\404.html");
		
		//http://example.com/	为补充相对连接
		try {
			Document doc = Jsoup.parse(in,"UTF-8","http://example.com/");
			String text = doc.text();
			System.out.println(text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
