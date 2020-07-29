package com.example.filter;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class PreFilter extends ZuulFilter {
	
	/*
	 *This method's boolean return value determines whether or not this
	 * filter's logic should be executed. YOu may create complex logic here
     * if you'd like OR you can just put "true" to have the filter 
	 * @return true
	 */
	@Override
	public boolean shouldFilter() {
       return true;
	}
	/*
	 * this is pretty much your filter's main method
	 * This is your filter's logic. Whatever logic you want it to do.
	 * @Return null or object
	 */
	@Override
	public Object run() throws ZuulException {
		
		RequestContext cont = RequestContext.getCurrentContext();
		HttpServletRequest myReq = cont.getRequest();
		
		System.out.println("this method: "+ myReq.getMethod());
		System.out.println("also: "+myReq.getRequestURI());
		return null;
	}

	/*
	 * Zuul will parse the String to figure out what type of filter this class should
	 * represent.
	 * pre- executes BEFORE the request is routed to the proper services.
	 * @Return "pre"
	 */
	@Override
	public String filterType() {
		return "pre";
	}
	
	/*
	 * If you have MANY filters of the same type then you can set a precedence
	 * order to them.
	 * Numeric order will be the order that the filters trigger
	 * @Return 0
	 */
	@Override
	public int filterOrder() {
		return 0;
	}
}
