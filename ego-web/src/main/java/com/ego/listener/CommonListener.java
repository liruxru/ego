package com.ego.listener;

import java.io.File;
import java.io.FilenameFilter;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
@WebListener("commonListener")
public class CommonListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		sc.setAttribute("root", sc.getContextPath());
		sc.setAttribute("resources", sc.getContextPath() + "/resources/");
		sc.setAttribute("js", sc.getContextPath() + "/resources/js/");
		sc.setAttribute("css", sc.getContextPath() + "/resources/css/");
		
		File file = new File(sc.getRealPath("/") + "/resources/img/bank");
		String[] fileNames = null;
		if (file.isDirectory()) {
			fileNames = file.list(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					String ext = name.substring(name.indexOf(".") + 1);
					if (ext.equals("gif") || ext.equals("jpg"))
						return true;
					else
						return false;
				}

			});
			sc.setAttribute("bank", fileNames);
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {

	}

}
