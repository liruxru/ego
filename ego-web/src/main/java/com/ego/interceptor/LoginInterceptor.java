package com.ego.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ego.utils.SessionUtils;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Resource(name="sessionUtils")
	private SessionUtils sessionUtils;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		String jSessionId=request.getRequestedSessionId();
		System.out.println("拦截器的前置方法");
		/*HttpSession session=request.getSession();*/
		
		if(sessionUtils.getUserInfo(jSessionId)==null) {
			String header=request.getHeader("X-Requested-With");//判断是否为异步请求
			if ("XMLHttpRequest".equals(header)){
				response.getWriter().print("301");
				return false;
			}
			String tag=request.getParameter("tag");
			String goodsId=request.getParameter("goodId");
			if (tag!=null) {
				request.setAttribute("tag", tag);
				request.setAttribute("goodsId", goodsId);
				request.getRequestDispatcher("/member/gologinView").forward(request,response);
			}
			response.sendRedirect(request.getContextPath()+"/member/gologinView");
			return false;
		}
		return true;
		
		/*if (session.getAttribute("user")==null) {
			String header=request.getHeader("X-Requested-With");//判断是否为异步请求
			if ("XMLHttpRequest".equals(header)){
				response.getWriter().print("301");
				return false;
			}
			String tag=request.getParameter("tag");
			String goodsId=request.getParameter("goodId");
			if (tag!=null) {
				request.setAttribute("tag", tag);
				request.setAttribute("goodsId", goodsId);
				request.getRequestDispatcher("/member/gologinView").forward(request,response);
			}
			response.sendRedirect(request.getContextPath()+"/member/gologinView");
			return false;
		}*/
		
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	
	
}
