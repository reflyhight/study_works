package com.mxj.jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ParsingUrl {
	
	
	public static void main(String[] args) {
		try {
			Document doc = Jsoup.connect("http://example.com/").get();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
