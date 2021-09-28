package com.jun;

import java.io.UnsupportedEncodingException;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

public class AbstractDispatcherServletTest implements AfterRunService {
	protected MockHttpServletRequest request;
	protected MockHttpServletResponse response;
	protected MockServletConfig config = new MockServletConfig("spring");
	protected MockHttpSession session;
	
	private ConfigurableDispatcherServlet dispatcherServlet;
	private Class<?>[] classes;
	private String[] locations;
	private String[] relativeLocations;
	private String servletPath;
	
	public String getContentAsString() throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		return null;
	}

	public WebApplicationContext getContext() {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T getBean(Class<T> beanType) {
		// TODO Auto-generated method stub
		return null;
	}

	public ModelAndView getModelAndView() {
		// TODO Auto-generated method stub
		return null;
	}

	public AfterRunService assertViewName() {
		// TODO Auto-generated method stub
		return null;
	}

	public AfterRunService assertModel(String name, Object value) {
		// TODO Auto-generated method stub
		return null;
	}
}
