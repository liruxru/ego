package com.ego.listener;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.ego.mapper.po.Store;
import com.ego.mapper.po.Type;
import com.ego.service.StoreService;
import com.ego.service.TypeService;
import com.ego.utils.PageBean;

public class CommonListener implements ServletContextListener {
	
	/*@Resource(name="typeService")
	private TypeService  typeService;
	@Resource(name="storeService")
	private StoreService storeService;*/

	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc=sce.getServletContext();
		/*PageBean<Type> pageType = this.typeService.selectAllType(1, 1000);
		PageBean<Store> pageStore = this.storeService.selectAllStore(1, 1000);
		sc.setAttribute("typeList", pageType.getBeanList());
		sc.setAttribute("storeList", pageStore.getBeanList());*/
		sc.setAttribute("root", sc.getContextPath());
		sc.setAttribute("js", sc.getContextPath()+"/resources/js");
		sc.setAttribute("css", sc.getContextPath()+"/resources/css");
		sc.setAttribute("img", sc.getContextPath()+"/resources/img");
	}

	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
