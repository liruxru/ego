package com.ego.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ego.document.Goods;
import com.ego.document.po.PingJiaWithDescription;
import com.ego.exception.ParamException;
import com.ego.mapper.po.Img;
import com.ego.mapper.po.Store;
import com.ego.mapper.po.Type;
import com.ego.mapper.po.User;
import com.ego.mapper.po.relationPo.StoreCart;
import com.ego.service.AreaService;
import com.ego.service.CartService;
import com.ego.service.CityService;
import com.ego.service.GoodsService;
import com.ego.service.LiuLanService;
import com.ego.service.PingJiaService;
import com.ego.service.ProvinceService;
import com.ego.service.StoreService;
import com.ego.service.TypeService;
import com.ego.service.UserService;
import com.ego.utils.PageBean;
import com.ego.utils.ReturnMessage;
import com.ego.utils.SSM;
import com.ego.utils.SearchGoods;
import com.ego.utils.SessionUtils;
import com.ego.utils.UploadPath;

@Controller
@RequestMapping("/member")
public class CommonController {
	private static final String URL = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
	private static final String APIID = "C49847686";
	private static final String APIKEY = "205cae86cf391be65fabc7c2404ea0c3";
	long mobile_code = 0;
	
	@Resource(name="uploadPath")
	private UploadPath uploadPath;  
	public UploadPath getUploadPath() {
		return uploadPath;
	}
	public void setUploadPath(UploadPath uploadPath) {
		this.uploadPath = uploadPath;
	}

	@Resource(name = "goodsService")
	private GoodsService goodsService;
	@Resource(name = "cartService")
	private CartService cartService;
	@Resource(name = "userService")
	private UserService userService;
	@Resource(name = "provinceService")
	private ProvinceService provinceService;
	@Resource(name = "cityService")
	private CityService cityService;
	@Resource(name = "areaService")
	private AreaService areaservice;
	@Resource(name = "storeService")
	private StoreService storeService;
	@Resource(name = "typeService")
	private TypeService typeService;
	@Resource(name = "liuLanService")
	private LiuLanService liulanService;
	@Resource(name = "pingJiaService")
	private PingJiaService pingJiaService;
	@Resource(name = "sessionUtils")
	private SessionUtils sessionUtils;

	@RequestMapping(value = "/useradd")
	public String useradd(Model model, @RequestParam(value = "loginname", required = false) String loginname,
			@RequestParam(value = "loginpwd", required = false) String loginpwd,
			@RequestParam(value = "userPhone", required = false) String phone,
			@RequestParam(value = "userMail", required = false) String mail) {
		loginpwd = DigestUtils.md5Hex(loginpwd);
		User user = new User(null, null, loginname, loginpwd, null, null, null, phone, 1, 1, 1, mail, null,
				null, null);
		ReturnMessage<User> rm = userService.addUser(user);
		if (rm.getResultCode() == 0) {
			// System.out.println(rm.getResultCode());
			return "redirect:gologinView";
		} else {
			model.addAttribute("user", user);
			return "member/reg";
		}
	}

	@RequestMapping(value = "/userRegServlet")
	@ResponseBody // 异步
	public Integer userRegServlet(Model model, @RequestParam(value = "vCode", required = false) String vCode) {
//		mobile_code = 696932;
		if (vCode != null && String.valueOf(mobile_code).equals(vCode)) {
			return 0;
		} else {
			return 1;
		}
	}

	 @RequestMapping(value="/userRegYanZheng")
	 @ResponseBody
	public Integer userRegYanZheng(Model model, @RequestParam("phone") String phone) {
		System.out.println("++++++++++++++"+phone);
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(URL);
		client.getParams().setContentCharset("GBK");
		method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=GBK");
		mobile_code = (long) ((Math.random() * 9 + 1) * 100000);
		String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");
		NameValuePair[] data = { // 提交短信
				new NameValuePair("account", APIID), // 查看用户名是登录用户中心->验证码短信->产品总览->APIID
				new NameValuePair("password", APIKEY), // 查看密码请登录用户中心->验证码短信->产品总览->APIKEY
				// new NameValuePair("password", util.StringUtil.MD5Encode("密码")),
				new NameValuePair("mobile", phone), new NameValuePair("content", content), };
		method.setRequestBody(data);
		try {
			client.executeMethod(method);
			/*String SubmitResult =method.getResponseBodyAsString();
			  System.out.println("SubmitResult:"+SubmitResult); 
			  Document doc = DocumentHelper.parseText(SubmitResult); 
			  Element root = doc.getRootElement();
			  String code = root.elementText("code"); 
			  String msg = root.elementText("msg");
			  String smsid = root.elementText("smsid"); 
			  System.out.println("code:"+code);
			  System.out.println("msg:"+msg); 
			  System.out.println("smsid:"+smsid);
			  if("2".equals(code)){
			   		System.out.println("短信提交成功"); 
			  } */
			return 2;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}
	 
	 @RequestMapping(value = "/updateUser")
	public String updateUser(Model model,HttpSession session, HttpServletRequest request,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "user-phone", required = false) String phone,
			@RequestParam(value = "sex", required = false) Integer sex,
			@RequestParam(value = "user-newLoginPwd1", required = false) String loginpwd,
			@RequestParam(value = "user-email", required = false) String mail) {
		System.out.println(username+" "+phone+" "+sex+" "+loginpwd);
		User user=(User) session.getAttribute("user");
		user.setUsername(username);
		user.setPhone(phone);
		user.setSex(sex);
		user.setMail(mail);
		if (loginpwd!=null && loginpwd!="") {
			user.setLoginpwd(DigestUtils.md5Hex(loginpwd));
		}
		user.setModifidate(new Date());
		ReturnMessage<User> rm=userService.updateUser(user);
		if (rm.getResultCode()==0) {
			return "redirect:/user/person/golist";
		}else {
			model.addAttribute("user", user);
			return "redirect:/user/person/golist";
		}
	 }

	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String gosearch(Model model, SearchGoods searchGoods, HttpServletRequest request) {
		if (searchGoods != null) {
			try {
				if (searchGoods.getName() != null && !"".equals(searchGoods.getName())) {
					if (request.getMethod().equalsIgnoreCase(SSM.TIJIAOFANGSHI)) {
						searchGoods.setName(new String(searchGoods.getName().getBytes("iso-8859-1"), "utf-8"));
					}
					/* searchGoods.getName().replaceAll("([^,])", "$1|"); */
				
				}
				if (searchGoods.getFullName() != null && !"".equals(searchGoods.getFullName())) {
					if (request.getMethod().equalsIgnoreCase(SSM.TIJIAOFANGSHI)) {
						searchGoods.setFullName(new String(searchGoods.getFullName().getBytes("iso-8859-1"), "utf-8"));
					}
					/* searchGoods.getFullName().replaceAll("([^,])", "$1|"); */
					
				}
				if (searchGoods.getTypename() != null && !"".equals(searchGoods.getTypename())) {
					if (request.getMethod().equalsIgnoreCase(SSM.TIJIAOFANGSHI)) {
						searchGoods.setTypename(new String(searchGoods.getTypename().getBytes("iso-8859-1"), "utf-8"));
					}
					
				}
				if (searchGoods.getStorename()!=null && !"".equals(searchGoods.getStorename())) {
					if (request.getMethod().equalsIgnoreCase(SSM.TIJIAOFANGSHI)) {
						searchGoods.setStorename(new String(searchGoods.getStorename().getBytes("iso-8859-1"), "utf-8"));
					}
					
				}
				
				if (searchGoods.getCurrentPage() == 0 || "".equals(searchGoods.getCurrentPage())) {
					searchGoods.setCurrentPage(1);
				}
				if (searchGoods.getPageSize() == 0 || "".equals(searchGoods.getPageSize())) {
					searchGoods.setPageSize(15);
				}
				// 升序 降序 有值即为选中，无值即未选中
				// 0代表上架
				searchGoods.setLock(0);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		PageBean<Goods> pageBean = this.goodsService.selectGoodsBySearchGoods(searchGoods);
		List<Type> typelist = this.typeService.selectAllType(1, 15).getBeanList();
		PageBean<Store> pageStore = this.storeService.selectAllStore(1, 50);
		List<Store> listStore = pageStore.getBeanList();

		List<Goods> listGoods = new ArrayList<Goods>();
		if (pageBean.getBeanList() != null) {
			listGoods = pageBean.getBeanList();
		}
		model.addAttribute("listGood", listGoods);
		model.addAttribute("page", pageBean);
		model.addAttribute("listType", typelist);
		model.addAttribute("sc", searchGoods);
		model.addAttribute("listStore", listStore);
		return "member/searchGoods";
	}

	@RequestMapping("/goXiangqing")
	public String goXiangqing(Model model, @RequestParam(value = "id", required = false) String goodsId,
			HttpSession session) {
		List<Type> listType = this.typeService.selectAllType(1, 15).getBeanList();
		Goods goodsDo = this.goodsService.findone(goodsId);
		Store store = new Store();
		User user = (User) session.getAttribute("user");

		if (user != null) {
			this.liulanService.insertLiulanByUserId(user.getId().toString(), goodsDo);
		}

		if (goodsDo != null) {
			if (goodsDo.getStore() != null) {
				store = this.storeService.selectStoreByName(goodsDo.getStore());
			}
		}
		List<Img> lsimg = this.goodsService.selectImgsByGoodsId(goodsId);

		model.addAttribute("arrImgs", lsimg);
		PageBean<PingJiaWithDescription> page = pingJiaService.selectPingjiaByGoodsId(goodsId, 1, 16);
		List<PingJiaWithDescription> pingJiaWithDescriptionList = page.getBeanList();
		model.addAttribute("pingJiaWithDescriptionList", pingJiaWithDescriptionList);

		model.addAttribute("store", store);
		model.addAttribute("goods", goodsDo);
		model.addAttribute("listType", listType);
		if (goodsDo.getType().equals("服装品牌")) {
			return "member/xiangqing";
		} else if (goodsDo.getType().equals("高科技电子")) {
			return "member/xiangqing";
		} else {
			return "member/xiangqing";
		}
	}

	@RequestMapping("/goreg")
	public String goreg(Model model) {
		model.addAttribute("listProvince", this.provinceService.findAllprovince());
		return "member/reg";
	}

	@RequestMapping(value = "/checkLoginname", method = RequestMethod.POST)
	@ResponseBody
	public Integer checkLoginName(String loginname) {
		User user = this.userService.getUserByLoginName(loginname);
		if (user != null) {
			return 1;
		} else {
			return 2;
		}
	}

	@RequestMapping(value = "/checkLoginPhone")
	@ResponseBody
	public Integer checkLoginPhone(String phone) {
		User user = this.userService.getUserByLoginPhone(phone);
		if (user != null) {
			return 1;
		} else {
			return 2;
		}
	}

	@RequestMapping("/getUserByLoginName")
	@ResponseBody
	public Integer getUserByLoginName(Model model,
			@RequestParam(value = "loginname", required = false) String loginname,RedirectAttributes attr,
			@RequestParam(value = "loginpwd", required = false) String loginpwd) {
		User user=this.userService.getUserByLoginName(loginname);
		String password=DigestUtils.md5Hex(loginpwd);
		if (user.getLoginpwd().equals(password)) {
			return 0;
		}else {
			return 1;
		}
	}
	@RequestMapping("/login")
	@ResponseBody
	public Map<String, String> login(@CookieValue("JSESSIONID") String jSessionId, Model model,
			String yanzhengma, User user, HttpSession session,
			RedirectAttributes attr) {
		Map<String, String> map = new HashMap<String, String>();
		System.out.println("jSessionId::" + jSessionId);
		// 账号登录
		if (user.getPhone() == null || user.getPhone() == "") {
			try {
				user.setLoginpwd(DigestUtils.md5Hex(user.getLoginpwd()));
				User userDo = this.userService.getUserByLoginName(user);
				if (userDo == null) {
					map.put("code", "1");
					return map;
				}
				session.setAttribute("user", userDo);
				sessionUtils.setUserInfo(jSessionId, userDo);
				String goUrl = (String) session.getAttribute("goUrl");
				String goodsId = (String) session.getAttribute("goodsId");
				if (goUrl != null) {
					map.put("code", "2");
					map.put("goUrl", "goXiangqing");
					map.put("id", goodsId);
					return map;
				}
				map.put("code", "0");
				return map;
			} catch (Exception e) {
				e.printStackTrace();
				map.put("code", "1");
				return map;
			}
		} else {
			// 验证码的登录
//			mobile_code=696932;  
			if (yanzhengma != null && String.valueOf(mobile_code).equals(yanzhengma)) { // 成功
				try {
					User userDo = this.userService.getUserByLoginPhone(user.getPhone());
					session.setAttribute("user", userDo);
					sessionUtils.setUserInfo(jSessionId, user);
					String goUrl = (String) session.getAttribute("goUrl");
					String goodsId = (String) session.getAttribute("goodsId");
					if (goUrl != null) {
						map.put("goUrl", "goXiangqing");
						map.put("id", goodsId);
						map.put("code", "2");
						return map;
					}
					map.put("code", "0");
					return map;
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				model.addAttribute("phone", user.getPhone());
				map.put("code", "1");
				return map;
			}
		}
		map.put("code", "1");
		return map;
	}

	@RequestMapping("/gologinView")
	public String gologin(@RequestParam(value = "tag", required = false) String tag,
			@RequestParam(value = "goodsId", required = false) String goodsId, HttpSession session) {
		if (tag != null) {
			session.setAttribute("goUrl", "goXiangqing");
			session.setAttribute("goodsId", goodsId);
		}
		return "login";
	}

	@RequestMapping("/home")
	public String home(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		// 获取四个商品div
		SearchGoods searchGoods1 = new SearchGoods(null, null, null, null, null, null, null, "高科技电子", null, 5, 1, null,
				null, 3);
		PageBean<Goods> pa1 = goodsService.findAllByTypes(searchGoods1);
		List<Goods> ls1 = new ArrayList<Goods>();
		if (pa1.getBeanList() != null) {
			ls1 = pa1.getBeanList();
		}
		SearchGoods searchGoods2 = new SearchGoods(null, null, null, null, null, null, null, "家用电器", null, 6, 1, null,
				null, 3);
		PageBean<Goods> pa2 = goodsService.findAllByTypes(searchGoods2);
		List<Goods> ls2 = new ArrayList<Goods>();
		if (pa2.getBeanList() != null) {
			ls2 = pa2.getBeanList();
		}
		SearchGoods searchGoods3 = new SearchGoods(null, null, null, null, null, null, null, "高科技电子", null, 6, 1, null,
				null, 3);
		PageBean<Goods> pa3 = goodsService.findAllByTypes(searchGoods3);
		List<Goods> ls3 = new ArrayList<Goods>();
		if (pa3.getBeanList() != null) {
			ls3 = pa3.getBeanList();
		}
		SearchGoods searchGoods4 = new SearchGoods(null, null, null, null, null, null, null, "服装品牌", null, 6, 1, null,
				null, 3);
		PageBean<Goods> pa4 = goodsService.findAllByTypes(searchGoods4);
		List<Goods> ls4 = new ArrayList<Goods>();
		if (pa4.getBeanList() != null) {
			ls4 = pa4.getBeanList();
		}
		List<Type> typename = this.typeService.selectAllType(1, 15).getBeanList();

		model.addAttribute("typename", typename);
		model.addAttribute("listgoods1", ls1);
		model.addAttribute("listgoods2", ls2);
		model.addAttribute("listgoods3", ls3);
		model.addAttribute("listgoods4", ls4);

		// 获取右侧购物车列表
		List<StoreCart> storeCartList = null;
		if (user == null) {
			model.addAttribute("storeCartList", storeCartList);
		} else {
			PageBean<StoreCart> page = this.cartService.selectCartByUserId(user.getId(), 1, 100);
			storeCartList = page.getBeanList();
			model.addAttribute("storeCartList", storeCartList);
		}
		return "member/home";
	}

	@RequestMapping("/xieyi")
	public String xieyi(Model model) {
		return "member/xieyi";
	}

	/**
	 * 退出登录
	 */
	@RequestMapping("/outlogin")
	public String outLogin(HttpSession session, HttpServletRequest request) {
		String jSessionId = request.getRequestedSessionId();
		session.removeAttribute("user");
		session.removeAttribute("goUrl");
		session.removeAttribute("goodsId");
		try {
			sessionUtils.removeUserInfo(jSessionId);
		} catch (ParamException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return "redirect:gologinView";
	}

}
