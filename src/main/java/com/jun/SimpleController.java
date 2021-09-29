package com.jun;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public abstract class SimpleController implements Controller {
	private String[] requiredParams;
	private String viewName;

	public void setRequiredParams(String[] requiredParams) {
		this.requiredParams = requiredParams;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(viewName == null) throw new IllegalStateException();
		
		HashMap<String, String> params = new HashMap<String, String>();
		for (String param : requiredParams) {
			String value = req.getParameter(param);
			if(value == null) throw new IllegalStateException();
			params.put(param, value);
		}
		HashMap<String, Object> model = new HashMap<String, Object>();
		this.control(params, model);
		return new ModelAndView(this.viewName, model);
	}

	public abstract void control(HashMap<String, String> params, HashMap<String, Object> model);
}
