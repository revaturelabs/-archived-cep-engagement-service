package com.example.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

public class PostFilter extends ZuulFilter {
	
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
	
	@Override
	public Object run() throws ZuulException {
		// this is pretty much your filter's main method
		System.out.println("in the post filter");
		return null;
	}
	
	/*
	 * Return a string of"post"
	 * Zuul will parse the String to figure out what type of filter this class should
	 * represent.
	 * post- executes AFTER the request has gone and come back with a response.
	 *@Return Post
	 */
	@Override
	public String filterType() {
		return "post";
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
