package com.mxj.netlogin;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.jjd.platform.common.constant.ResponseCode;
import com.jjd.platform.dao.BaseDaoI;
import com.jjd.platform.model.JjdJgCompany;
import com.jjd.platform.model.Jjd_sq_xuexin_info;
import com.jjd.platform.model.Jjd_sq_zhengxin_info;
import com.jjd.platform.model.XinYong;
import com.jjd.platform.pageModel.ResultJson;
import com.jjd.platform.pageModel.UserRegistResponse;
import com.jjd.platform.service.JjdInfoServiceI;
import com.jjd.platform.service.NetCatchServiceI;
import com.jjd.platform.service.util.CustomWeb;
import com.jjd.platform.service.util.ToolUtil;
import com.jjd.platform.service.util.XuexinWebCollection;
import com.jjd.platform.service.util.ZhengxinWebCollection;
import com.jjd.platform.util.QiniuApi;
import com.jjd.platform.util.QiniuApiTemp;

@Service("netCatchService")
public class NetCatchServiceImpl implements NetCatchServiceI {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Resource(name = "baseDao")
	private BaseDaoI<Jjd_sq_zhengxin_info> Jjd_sq_zhengxin_infoDao = null;
	@Resource(name = "baseDao")
	private BaseDaoI<JjdJgCompany> jjdJgCompanyDao = null;
	@Resource(name = "jjdInfoService")
	private JjdInfoServiceI jjdInfoService = null;

	@Override
	public ResultJson<JSONObject> zhengxinInit(UserRegistResponse ur)  throws Exception{
		ResultJson<JSONObject> result = new ResultJson<JSONObject>();
		JSONObject obj = new JSONObject();
		final WebClient webClient = getWebClient(ur);
		// 1.获取某个待测页面
		final HtmlPage pageLogin = webClient.getPage("https://ipcrs.pbccrc.org.cn/page/login/loginreg.jsp");
		HtmlImage valiCodeImg = (HtmlImage) pageLogin.getElementById("imgrc");
		if (valiCodeImg == null) {
			result.setCode(ResponseCode.FAIL);
			result.setMessage("初始化失败，未获得验证码");
			result.setObject(null);
		}else{
			String imgrcUrl = webImgToQiniuTemp(valiCodeImg, "imgs/imgrc_zhenxin/" + ToolUtil.generatorId());
			if (StringUtils.isBlank(imgrcUrl)) {
				result.setCode(ResponseCode.FAIL);
				result.setMessage("初始化失败，验证码存储失败");
				result.setObject(null);
				return result;
			}
			ZhengxinWebCollection.save(ur.getUserId(), webClient, pageLogin);
			obj.put("imgrc", imgrcUrl);
			result.setCode(ResponseCode.SUCCESS);
			result.setMessage("web初始化成功");
			result.setObject(obj);
		}
		return result;
	}

	/**
	 * 获取web浏览器
	 * @param ur
	 * @return
	 */
	private WebClient getWebClient(UserRegistResponse ur) {
		final WebClient webClient;
		if (ZhengxinWebCollection.get(ur.getUserId()) != null) {
			webClient = ZhengxinWebCollection.get(ur.getUserId()).getWebClient();
		} else {
			webClient = new WebClient(BrowserVersion.CHROME);
		}
		webClient.getOptions().setUseInsecureSSL(true);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setCssEnabled(false);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		webClient.getOptions().setTimeout(50000);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		return webClient;
	}

	@Override
	public ResultJson<JSONObject> getImgrc(HttpServletRequest request,
			UserRegistResponse ur) throws Exception {
		ResultJson<JSONObject> result = new ResultJson<JSONObject>();
		JSONObject obj = new JSONObject();
		CustomWeb cw=ZhengxinWebCollection.get(ur.getUserId());
		if (cw == null) {
			result.setCode(ResponseCode.INVALID);
			result.setMessage("浏览器容器尚未初始化");
			result.setObject(null);
			return result;
		}
		HtmlPage pageLogin = cw.getLoginPage();
		HtmlImage valiCodeImg = (HtmlImage) pageLogin.getElementById("imgrc");
		if (valiCodeImg == null) {
			result.setCode(ResponseCode.FAIL);
			result.setMessage("初始化失败，未获得验证码");
			result.setObject(null);
			return result;
		}
		valiCodeImg.setAttribute("src",	"/imgrc.do?a=" + System.currentTimeMillis());
		String imgrcUrl = webImgToQiniuTemp(valiCodeImg,"imgs/imgrc_zhenxin/" + ToolUtil.generatorId());
		if (StringUtils.isBlank(imgrcUrl)) {
			result.setCode(ResponseCode.FAIL);
			result.setMessage("初始化失败，验证码存储失败");
			result.setObject(null);
			return result;
		}
		obj.put("imgrc", imgrcUrl);
		result.setCode(ResponseCode.SUCCESS);
		result.setMessage("刷新验证码成功");
		result.setObject(obj);
		return result;
	}

	/**
	 * 
	 * 浏览器中抓取的图片对象转存到临时空间，临时空间最后会被清空，用于保存图片验证码
	 * 注意，该方法是浏览器中抓起时才能用，
	 * @param valiCodeImg
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private String webImgToQiniuTemp(HtmlImage valiCodeImg, String key)
			throws Exception {
		ImageReader imageReader = valiCodeImg.getImageReader();
		BufferedImage bufferedImage = imageReader.read(0);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
		return QiniuApiTemp.uploadFile(key, byteArrayOutputStream.toByteArray());
	}
	
	
	/**
	 * 浏览器中抓取的图片对象转存到正式空间，用于有用图片，如证书头像
	 * 注意，该方法是浏览器中抓起时才能用，
	 * @param valiCodeImg
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private String webImgToQiniuOfficial(HtmlImage valiCodeImg, String key)
			throws Exception {
		ImageReader imageReader = valiCodeImg.getImageReader();

		BufferedImage bufferedImage = imageReader.read(0);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "png", byteArrayOutputStream);

		return QiniuApi.uploadFile(key, byteArrayOutputStream.toByteArray());

	}

	@Override
	public ResultJson<JSONObject> saveZhengxinInfor(UserRegistResponse ur,
			String username, String pwd, String idCode, String imgrcCode,
			HttpServletRequest request) throws Exception {
		ResultJson<JSONObject> result = new ResultJson<JSONObject>();
		JSONObject obj = new JSONObject();
		if (StringUtils.isBlank(username) || StringUtils.isBlank(pwd)
				|| StringUtils.isBlank(imgrcCode)
				|| StringUtils.isBlank(idCode)) {
			result.setCode(ResponseCode.FAIL);
			result.setMessage("请填写完整");
			result.setObject(null);
			return result;
		}
		if (ZhengxinWebCollection.get(ur.getUserId()) == null) {
			result.setCode(ResponseCode.INVALID);
			result.setMessage("浏览器容器尚未初始化");
			result.setObject(null);
			return result;
		}
		HtmlPage pageLogin = ZhengxinWebCollection.get(ur.getUserId())
				.getLoginPage();
		// 2.获取页面上的表单
		final HtmlForm form = pageLogin.getForms().get(0);
		// 3.获取页面上的各个元素
		HtmlInput loginname = form.getInputByName("loginname");
		HtmlInput loginPassword = form.getInputByName("password");
		HtmlInput securCode = form.getInputByName("_@IMGRC@_");

		loginname.setValueAttribute(username);
		loginPassword.setValueAttribute(pwd);

		securCode.setValueAttribute(imgrcCode);

		DomNodeList<HtmlElement> allInputs = form.getElementsByTagName("input");

		HtmlElement submit = allInputs.get(allInputs.getLength() - 1);

		// 提交登陆，征信平台
		HtmlPage logined = (HtmlPage) submit.click();
		HtmlPage menuPage = logined.getWebClient().getPage(
				"https://ipcrs.pbccrc.org.cn/menu.do");

		HtmlElement getInfor = null;
		try {
			getInfor = menuPage.getElementById("menu")
					.getElementsByTagName("li").get(1).getElementsByTagName("ul").get(0)
					.getElementsByTagName("a").get(1);
		} catch (Exception e) {
			// 表示，用户名，密码错误，或者图片验证码错误
			String errorPageText = logined.asText();
			if (errorPageText.indexOf("验证码输入错误") != -1) {
				result.setCode(ResponseCode.FAIL);
				result.setMessage("验证码输入错误,请重新输入");
				result.setObject(null);
				return result;
			} else if (errorPageText.indexOf("登录名或密码错误") != -1) {
				result.setCode(ResponseCode.FAIL);
				result.setMessage("登录名或密码错误！ ");
				result.setObject(null);
				return result;
			} else {
				ZhengxinWebCollection.remove(ur.getUserId());
				result.setCode(ResponseCode.INVALID);
				result.setMessage("登陆时发生未知错误，请重试 ");
				result.setObject(null);
				return result;
			}
		}

		HtmlPage inforFormPage = (HtmlPage) getInfor.click();
		HtmlForm inforForm = inforFormPage.getForms().get(0);
		// 选择抓取信用信息
		inforForm.getInputsByName("reportformat").get(2)
				.setAttribute("checked", "checked");
		// 填写身份验证码
		inforForm.getInputByName("tradeCode").setValueAttribute(idCode);

		HtmlPage infor = (HtmlPage) inforFormPage.getElementById("nextstep")
				.click();

		if (infor.getUrl() == null
				|| infor.getUrl().toString().indexOf("simpleReport.do") == -1) {

			// 已经登陆的用户，需要重新初始化浏览器容器

			ZhengxinWebCollection.remove(ur.getUserId());
			result.setCode(ResponseCode.INVALID);
			result.setMessage("身份验证码错误。或已经过期，需要重新申请。 ");
			result.setObject(null);

			return result;
		}
		DomNodeList<DomElement> tables = infor.getElementsByTagName("table");
		XinYong xinyong = parseZhengxinXml(tables.get(0).asXml());
		Jjd_sq_zhengxin_info zhenxin = JSON.parseObject(
				JSON.toJSONString(xinyong), Jjd_sq_zhengxin_info.class);
		// 保存征信数据
		
		
		
		jjdInfoService.saveOrUpdateJjdZhengXinInfo(zhenxin, ur);
		ZhengxinWebCollection.remove(ur.getUserId());
		result.setCode(ResponseCode.SUCCESS);
		result.setMessage("授权征信信息成功");
		result.setObject(null);
		return result;
	}

	private XinYong parseZhengxinXml(String xml) {

		XinYong xinyong = new XinYong();

		Document doc = Jsoup.parse(Jsoup.parse(xml).select("table").eq(0)
				.html());

		String text1 = doc.select("table").eq(1).text();
		xinyong.setName(doc.select("table").eq(1).select("td .p").eq(0).text());
		xinyong.setIdcard(doc.select("table").eq(1).select("td .p").eq(2)
				.text());
		xinyong.setMarryed(doc.select("table").eq(1).select("td .p").eq(3)
				.text());

		Elements selectTr = doc.select("table").eq(3).select("table").eq(0)
				.select("table table").eq(0).select("tr");

		for (int i = 1; i < selectTr.size(); i++) {

			xinyong.getXinyonkaInfor()[i - 1] = Integer.parseInt(selectTr
					.get(i).select("td").eq(1).text());
			xinyong.getGoufangdaiInfor()[i - 1] = Integer.parseInt(selectTr
					.get(i).select("td").eq(2).text());
			xinyong.getOtherInfor()[i - 1] = Integer.parseInt(selectTr.get(i)
					.select("td").eq(3).text());
		}

		xinyong.setXinyongktitle(doc.select("ol ").eq(0).select("span strong")
				.eq(0).text());

		Elements elementli = doc.select("ol ").eq(0).select("li");
		for (int i = 0; i < elementli.size(); i++) {
			xinyong.getXinyongkdetail().add(elementli.get(i).text());
		}

		xinyong.setCommoninfor(doc.select("table").eq(7).select("tr").eq(1)
				.select("td").eq(0).text());

		return xinyong;
	}

	@Override
	public ResultJson<JSONObject> xuexinInit(HttpServletRequest request,
			UserRegistResponse ur) throws Exception {
		ResultJson<JSONObject> result = new ResultJson<JSONObject>();
		final WebClient webClient=getWebClient(ur);
		// 1.获取某个待测页面
		final HtmlPage pageLogin = webClient
				.getPage("https://account.chsi.com.cn/passport/login");
		XuexinWebCollection.save(ur.getUserId(), webClient, pageLogin);
		result.setCode(ResponseCode.SUCCESS);
		result.setMessage("web初始化成功");
		result.setObject(null);
		return result;
	}

	@Override
	public ResultJson<JSONObject> saveXuexinInfor(UserRegistResponse ur,
			String password, String username, String imgrc) throws Exception {
		ResultJson<JSONObject> result = new ResultJson<JSONObject>();
		JSONObject obj = new JSONObject();
		if (StringUtils.isBlank(password) || StringUtils.isBlank(username)) {
			result.setCode(ResponseCode.FAIL);
			result.setMessage("用户名和密码都不为空");
			result.setObject(null);
			return result;
		}
		CustomWeb cw=XuexinWebCollection.get(ur.getUserId());
		if (cw == null) {
			result.setCode(ResponseCode.INVALID);
			result.setMessage("web容器未初始化");
			result.setObject(null);
			return result;
		}

		final HtmlPage pageLogin = cw.getLoginPage();
		WebClient webClient = cw.getWebClient();

		final HtmlForm form = pageLogin.getForms().get(0);
		// 3.获取页面上的各个元素
		HtmlInput loginname = form.getInputByName("username");
		DomElement nloginpwd = pageLogin.getElementById("password");

		try {
			if (!StringUtils.isBlank(imgrc)) {
				HtmlInput captcha = form.getInputByName("captcha");
				captcha.setValueAttribute(imgrc);
			}
		} catch (ElementNotFoundException e) {
			log.info("无需图片验证码");
		}
		loginname.focus();
		loginname.setValueAttribute(username);
		loginname.blur();
		nloginpwd.setAttribute("value", password);
		DomElement submitBtn = form.getInputByName("submit");
		HtmlPage loginResult = (HtmlPage) submitBtn.click();
		String loginResultText = loginResult.asText();
		
		
		log.info("登陆反馈信息："+loginResultText);
									
		if ((loginResultText.indexOf("请输入校验码后重新登录") != -1)
				|| loginResultText.indexOf("图片校验码输入有误") != -1) {
			// 读取验证码
			// loginResultText.indexOf("图片校验码输入有误") != -1

			XuexinWebCollection.setLoginPage(ur.getUserId(), loginResult);

			HtmlInput captcha = loginResult.getForms().get(0)
					.getInputByName("captcha");
			captcha.focus();

			HtmlImage valiCodeImg = (HtmlImage) loginResult
					.getElementById("stu_reg_vcode");

			String imgrcUrl = webImgToQiniuTemp(valiCodeImg, "imgs/imgrc_xuexin/"
					+ ToolUtil.generatorId());

			JSONObject json = new JSONObject();
			json.put("imgrc", imgrcUrl);
			if (loginResultText.indexOf("您输入的用户名或密码有误") != -1) {
				result.setMessage("您输入的用户名或密码有误");
			} else if (loginResultText.indexOf("图片校验码输入有误") != -1) {
				result.setMessage("图片验证码错误");
			}else {
				result.setMessage("请输入图片验证码");
			}
			result.setCode(ResponseCode.EXPIRED);
			result.setObject(json);
			return result;
		}else if (loginResultText.indexOf("或密码有误") != -1) {
			result.setCode(ResponseCode.FAIL);
			result.setObject(null);
			result.setMessage("您输入的用户名或密码有误");
			return result;
		}else if(loginResultText.indexOf("账号已注销")!=-1){
			result.setCode(ResponseCode.FAIL);
			result.setObject(null);
			result.setMessage("您的账户已经注销。请用原账号登陆学信官网，查看更多相关。");
			return result;
		}else if(loginResultText.indexOf("欢迎")!=-1&& loginResultText.indexOf("退出")!=-1){
			final HtmlPage infor = webClient
					.getPage("http://my.chsi.com.cn/archive/xlarchive.action");

			List<Jjd_sq_xuexin_info> xllist = parseXuexinXml(infor.asXml());
			
			
			
			

			// 保存学信数据
			for (int i = 0; i < xllist.size(); i++) {// 处理学籍图片
				
				Jjd_sq_xuexin_info jjd_sq_xuexin_info = xllist.get(i);
				log.info("学历信息"+i+jjd_sq_xuexin_info.toString());
				
				
				if(jjd_sq_xuexin_info.getTitle().indexOf("该学历正在核查")!=-1){
					xllist.remove(i);
					continue;
				}
				
				HtmlImage valiCodeImg = (HtmlImage) infor.getElementsByTagName(
						"img").get(i);
				jjd_sq_xuexin_info.setImg(
						webImgToQiniuOfficial(valiCodeImg, "xuexin_info/img/" + i + "/"
								+ ur.getUserId()));
			}
			
			
			if(xllist.size()==0){
				
				result.setCode(ResponseCode.FAIL);
				result.setMessage("授权失败，学信网没有可用学历信息或信息正在核查。请登录学信查看更多相关。");
				result.setObject(null);
				return result;
			}
			
			
			
			
			
		
			
			
			jjdInfoService.saveOrUpdateJjdXueXinInfo(xllist, ur);

			XuexinWebCollection.remove(ur.getUserId());
			result.setCode(ResponseCode.SUCCESS);
			result.setMessage("授权成功");
			result.setObject(null);
			return result;
		}else{
			
			
			log.info("需要修改抓取代码");
			result.setCode(ResponseCode.FAIL);
			result.setMessage("授权失败，请稍后重试");
			result.setObject(null);
			return result;
		}

		
	}

	public static List<Jjd_sq_xuexin_info> parseXuexinXml(String xml) {

		Document doc = Jsoup.parse(xml);

		List<Jjd_sq_xuexin_info> xuelis = new ArrayList<Jjd_sq_xuexin_info>();

		Elements titleSpan = doc.select("#tabs ul li span");

		for (int i = 0; i < titleSpan.size(); i++) {
			
			
			Jjd_sq_xuexin_info xueli = new Jjd_sq_xuexin_info();
			xueli.setId(ToolUtil.generatorId());
			xueli.setTitle(titleSpan.get(i).text());

			Elements table = doc.select("#resultTable .xjxlTable ").eq(i);
			xueli.setName(table.select("tr").eq(0).select("td").eq(0).text());
			xueli.setImg("http://my.chsi.com.cn"
					+ table.select("tr").eq(0).select("td").eq(1).select("img")
							.attr("src"));

			xueli.setSex(table.select("tr").eq(1).select("td").eq(0).text());
			xueli.setBirth(table.select("tr").eq(1).select("td").eq(1).text());

			xueli.setBeginTime(table.select("tr").eq(2).select("td").eq(0)
					.text());
			xueli.setEndTime(table.select("tr").eq(2).select("td").eq(1).text());

			xueli.setKind(table.select("tr").eq(3).select("td").eq(0).text());
			xueli.setLevel(table.select("tr").eq(3).select("td").eq(1).text());

			xueli.setUniversity(table.select("tr").eq(4).select("td").eq(0)
					.text());
			xueli.setUniAddr(table.select("tr").eq(4).select("td").eq(1).text());

			xueli.setMajor(table.select("tr").eq(5).select("td").eq(0).text());
			xueli.setSystem(table.select("tr").eq(5).select("td").eq(1).text());

			xueli.setNO(table.select("tr").eq(6).select("td").eq(0).text());
			xueli.setGraduation_status(table.select("tr").eq(6).select("td")
					.eq(1).text());
			xueli.setCreatedatetime(new Date());
			// System.out.println(xueli);

			xuelis.add(xueli);
		}

		return xuelis;

	}

	@Override
	public ResultJson<JSONObject> getXuexinImgrc(UserRegistResponse ur) throws Exception {
		ResultJson<JSONObject> result = new ResultJson<JSONObject>();
		CustomWeb cw=XuexinWebCollection.get(ur.getUserId());
		if (cw == null) {
			result.setCode(ResponseCode.INVALID);
			result.setMessage("web容器未初始化");
			result.setObject(null);
			return result;
		}
		final HtmlPage pageLogin = cw.getLoginPage();
		HtmlForm form = pageLogin.getForms().get(0);
		HtmlInput captcha = form.getInputByName("captcha");
		captcha.focus();
		HtmlImage valiCodeImg = (HtmlImage) pageLogin.getElementById("stu_reg_vcode");
		valiCodeImg.click();// 模拟点击验证码事件
		String imgrcUrl = webImgToQiniuTemp(valiCodeImg,"imgs/imgrc_xuexin/" + ToolUtil.generatorId());

		JSONObject json = new JSONObject();
		json.put("imgrc", imgrcUrl);
		result.setCode(ResponseCode.SUCCESS);
		result.setMessage("保证安全，需要验证码");
		result.setObject(json);
		return result;
	}

}
