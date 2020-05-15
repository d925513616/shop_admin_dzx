package com.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class CodeFiliter
 */
public class CodeFiliter implements Filter {
	
	
    public CodeFiliter() {
       
    }

	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		String url = req.getRequestURI();
		
		if(url.contains(".css") || url.contains(".js")){
			chain.doFilter(request, response);
			return ;
		}
		
		else {
			request.setCharacterEncoding("utf-8");// 请求
			response.setCharacterEncoding("utf-8");// 响应
			response.setContentType("text/html;charset=utf-8");// 响应
		}
		//System.out.print("过滤了");
		chain.doFilter(request, response);
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
