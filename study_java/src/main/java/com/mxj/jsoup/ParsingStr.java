package com.mxj.jsoup;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParsingStr {

    public static void main(String[] args) {
        String html = "<html><head><title>First parse</title></head>"
                + "<body><p>Parsed HTML into a doc.</p></body></html>";
        Document doc = Jsoup.parse(html);
        
        Elements tag = doc.getElementsByTag("head");
        for (Element element : tag) {
			System.out.println(element.text());
		}
        
        
        

    }
}

