package com.fca.dev.springboot.app.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class TimeElapsedPostHandlerFilter extends ZuulFilter{

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		Long startTime = (Long)request.getAttribute("startTime");
		Long finalTime = System.currentTimeMillis();
		Long elapsedTime = finalTime - startTime;
		request.setAttribute("startTime", startTime);
		System.out.println("Post handler filter init.");
		System.out.println("Elapsed in seconds: " + elapsedTime.doubleValue()/1000.00);
		return null;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	
}
