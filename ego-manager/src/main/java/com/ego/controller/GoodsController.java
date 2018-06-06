package com.ego.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.junit.runners.Parameterized.Parameter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;

import com.ego.document.Goods;
import com.ego.mapper.po.Store;
import com.ego.mapper.po.Type;
import com.ego.service.GoodsService;
import com.ego.service.StoreService;
import com.ego.service.TypeService;
import com.ego.utils.FileUtils;
import com.ego.utils.PageBean;
import com.ego.utils.ReturnMessage;
import com.ego.utils.SearchGoods;
import com.ego.utils.UploadPath;
import com.sun.jndi.url.rmi.rmiURLContextFactory;

@Controller("goodsController")
@RequestMapping("/admin/goods")
public class GoodsController {
	@Resource(name="uploadPath")
	private UploadPath uploadPath;  
	public UploadPath getUploadPath() {
		return uploadPath;
	}
	public void setUploadPath(UploadPath uploadPath) {
		this.uploadPath = uploadPath;
	}

	@Resource(name="goodsService")
	private GoodsService goodsService;
	@Resource(name="typeService")
	private TypeService  typeService;
	@Resource(name="storeService")
	private StoreService storeService;
	
	@RequestMapping("/list")
	public String list(Model model,Integer pageCode,SearchGoods searchGoods,HttpSession httpSession) {
		if(pageCode==null||"".equals(pageCode)){
			pageCode =1;
		}
		Integer sessionPageCode = (Integer) httpSession.getAttribute("pageCode");
		if(sessionPageCode!=null) {
			pageCode =sessionPageCode;
			httpSession.removeAttribute("pageCode");
		}
		Integer pageSize = 9;
		searchGoods.setPageSize(pageSize);
		searchGoods.setCurrentPage(pageCode);
		searchGoods.setLock(0);
		//searchGoods = new SearchGoods(null, null, null, null, null, null, null, null, null, 5, 1, null, null, 3);
		PageBean<Goods> pa=goodsService.findAll(searchGoods);
		Integer totalPage = pa.getPageCount();
		model.addAttribute("listGood", pa.getBeanList());
		model.addAttribute("page", pa);
		model.addAttribute("totalPage", totalPage);
		return "admin/goods/list";
		
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Map delete(String id){
		Goods goods = this.goodsService.findone(id);
		Map<String,Object> map = new HashMap<String, Object>();
		ReturnMessage<Goods> rm = new ReturnMessage<Goods>();
		if(goods.getLock()!=1) {			
			rm =this.goodsService.upodateGoodsLockById(id, 1);
		}else {
			rm =this.goodsService.upodateGoodsLockById(goods.get_id(), 0);
		}
		map.put("code", rm.getResultCode());
		return map;
		
	}
	
	@RequestMapping("/deleteAll")
	public String deleteAll(String ch[]){
		ReturnMessage<Goods> rm =this.goodsService.upodateGoodsLockByIds(ch, 1);
		return "redirect:/admin/goods/list";
		
	}
	
	@RequestMapping("/edit")
	public String edit(Model model,@RequestParam(value="_id",required=false)String _id,Integer pageCode,HttpSession session){
		session.setAttribute("pageCode", pageCode);
		String path = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/")+"upload/";
		PageBean<Type> pageType = this.typeService.selectAllType(1, 1000);
		PageBean<Store> pageStore = this.storeService.selectAllStore(1, 1000);
		model.addAttribute("typeList", pageType.getBeanList());
		model.addAttribute("storeList", pageStore.getBeanList());
		if(_id==null) {
			return "admin/goods/add";
		}else {
			Goods goods = this.goodsService.findone(_id);
			model.addAttribute("good", goods);
			model.addAttribute("path", path);
			return "admin/goods/edit";
		}
		
	}
	
	@RequestMapping("/add")
	public String add(Model model,Goods goods,@RequestParam(value="_id",required=false)String _id,@RequestParam(value="cover",required=false) MultipartFile coverimg,@RequestParam("imgs")MultipartFile[] imgs) throws IllegalStateException, IOException{
		String path = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/")+"resources/upload/";
//		String path=uploadPath.getUploadPath();
		if(_id==null){
			//判断图片格式和不能为空
			for (int i = 0;coverimg!=null&&i<imgs.length;i++) {  
				System.out.println(coverimg.getContentType());
				System.out.println(imgs[i].getContentType());
				if(!"application/octet-stream".equals(imgs[i].getContentType())&&(imgs[i]==null|| coverimg==null||!"image/jpeg".equals(coverimg.getContentType())||!"image/jpeg".equals((imgs[i]).getContentType()))){	
					PageBean<Type> pageType = this.typeService.selectAllType(1, 1000);
					PageBean<Store> pageStore = this.storeService.selectAllStore(1, 1000);
					model.addAttribute("typeList", pageType.getBeanList());
					model.addAttribute("storeList", pageStore.getBeanList());
					model.addAttribute("fileError", "上传图片类型错误");
					model.addAttribute("good", goods);
					return "redirect:edit";
				}	
			}		 
			//生成新的文件名字
			String suffix = FileUtils.getSuffix(coverimg.getOriginalFilename());
			String newFileName = FileUtils.getFileSn()+suffix;
			coverimg.transferTo(new File(path+newFileName));
			goods.setCoverimg(newFileName);
			List<String> ls = new ArrayList<String>();
			List<String> lss = new ArrayList<String>();
			for (int i = 0;imgs!=null&&i<imgs.length;i++) {
				if("image/jpeg".equals(imgs[i].getContentType())&&(imgs[i]!=null)){	
					lss.add(imgs[i].getOriginalFilename());
					String suf = FileUtils.getSuffix((imgs[i].getOriginalFilename()));
					String newImg= FileUtils.getFileSn()+suf;
					ls.add(newImg);
					imgs[i].transferTo(new File(path+newImg));
			  }
			}
		/*	System.out.println(goods);
			System.out.println(coverimg.getContentType());*/
			ReturnMessage<Goods> rm = this.goodsService.addGoods(goods,ls,lss);
			if(rm.getResultCode()==0) {
				return "redirect:list";			
			}else {
				PageBean<Type> pageType = this.typeService.selectAllType(1, 1000);
				PageBean<Store> pageStore = this.storeService.selectAllStore(1, 1000);
				model.addAttribute("typeList", pageType.getBeanList());
				model.addAttribute("storeList", pageStore.getBeanList());
				model.addAttribute("good", rm.getBean());
				model.addAttribute("error", rm.getMessage());
				return "admin/goods/add";
			}
		}else{
			goods.set_id(_id);
			if(coverimg.getSize()>0) {
				String suffix = FileUtils.getSuffix(coverimg.getOriginalFilename());
				String newFileName = FileUtils.getFileSn()+suffix;
				coverimg.transferTo(new File(path+newFileName));
				goods.setCoverimg(newFileName);
			}
			ReturnMessage<Goods> rm = this.goodsService.updateGoods(goods);
			if(rm.getResultCode()==0) {
				return "redirect:list";			
			}else {
				PageBean<Type> pageType = this.typeService.selectAllType(1, 1000);
				PageBean<Store> pageStore = this.storeService.selectAllStore(1, 1000);
				model.addAttribute("typeList", pageType.getBeanList());
				model.addAttribute("storeList", pageStore.getBeanList());
				model.addAttribute("good", rm.getBean());
				model.addAttribute("error", rm.getMessage());
				return "admin/goods/edit";
			}
		}
		
	}
	
	
	
	
	
	
	
	
	

}
