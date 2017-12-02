package com.mxj.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


/**
 * 
 * @author Rob Jiang
 * @dat 2017年12月2日
 * @email jh624haima@126.com
 * @blog blog.mxjhaima.com
 */
public class ParsingFragment {
	
	
	public static void main(String[] args) {
		
	
		String html = "<div><p>Lorem ipsum.</p>";
		Document doc = Jsoup.parseBodyFragment(html);
		Element body = doc.body();
		System.out.println(body.toString());
		
		
//		输出结果
//		<body>
//		 <div>
//		  <p>Lorem ipsum.</p>
//		 </div>
//		</body>
	}

}
