package com.mxj.htmlunit;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

import junit.framework.Assert;

public class StartedTest {

	public static void main(String[] args) {
		final WebClient webClient = new WebClient(BrowserVersion.CHROME);

		try {
			//模拟登录实验楼
			webClient.getOptions().setUseInsecureSSL(true);
			webClient.getOptions().setJavaScriptEnabled(true);
			webClient.getOptions().setCssEnabled(false);
			webClient.setAjaxController(new NicelyResynchronizingAjaxController());
			webClient.getOptions().setTimeout(50000);
			webClient.getOptions().setThrowExceptionOnScriptError(false);

			HtmlPage page = webClient.getPage("https://www.shiyanlou.com/");
			HtmlAnchor Anchor = (HtmlAnchor) page.getByXPath("//*[@id=\"header-navbar-collapses\"]/ul[2]/li[3]/a")
					.get(0);
			HtmlPage click = Anchor.click();
			HtmlForm form = (HtmlForm) click.getByXPath("//*[@id=\"signin-form\"]/form").get(0);

			HtmlInput email = form.getInputByName("login");
			HtmlInput pwd = form.getInputByName("password");
			email.setValueAttribute("649766951@qq.com");
			pwd.setValueAttribute("jh624@126.com");
			HtmlSubmitInput submit = (HtmlSubmitInput) form.getInputByName("submit");
			Page click2 = submit.click();
			
			
			HtmlPage page2 = click.getWebClient().getPage("https://www.shiyanlou.com/");
			String contentAsString = page2.getWebResponse().getContentAsString();

			// String contentAsString =
			// click2.getWebResponse().getContentAsString();

			System.out.println(contentAsString);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void homePage() throws Exception {
		final WebClient webClient = new WebClient();
		final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
		Assert.assertEquals("HtmlUnit - Welcome to HtmlUnit", page.getTitleText());

		final String pageAsXml = page.asXml();
		Assert.assertTrue(pageAsXml.contains("<body class=\"composite\">"));

		final String pageAsText = page.asText();
		Assert.assertTrue(pageAsText.contains("Support for the HTTP and HTTPS protocols"));

	}
}
