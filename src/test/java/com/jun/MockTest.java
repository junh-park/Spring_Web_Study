package com.jun;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.web.servlet.ModelAndView;

public class MockTest {

	@Test
	public void simpleGet() throws ServletException, IOException {
		MockHttpServletRequest req = new MockHttpServletRequest("GET", "/hello");
		req.addParameter("name", "Spring");
		
		MockHttpServletResponse resp = new MockHttpServletResponse();
		
		SimpleGetServlet servlet = new SimpleGetServlet();
		servlet.service(req, resp);
		
		assertThat(resp.getContentAsString().contains("Hello Spring"), is(true));
	}
	
//	@Test
//	public void helloController() throws ServletException, IOException {
//		ConfigurableDispatcherServlet servlet = new ConfigurableDispatcherServlet();
//		servlet.setLocations("/spring-servlet.xml");
//		servlet.setClasses(HelloSpring.class);
//		servlet.init(new MockServletConfig("spring"));
//		
//		
////		MockHttpServletRequest req = new MockHttpServletRequest("GET", "/app/hello");
////		req.setAttribute("javax.servlet.include.servlet_path", "/app");
//		
//		MockHttpServletRequest req = new MockHttpServletRequest("GET", "/hello");
//		req.addParameter("name", "Spring");
//		MockHttpServletResponse res = new MockHttpServletResponse();
//		
//		servlet.service(req, res);
//		
//		ModelAndView mav = servlet.getModelAndView();
//		assertThat(mav.getViewName(), is("/WEB-INF/view/hello.jsp"));
//		assertThat((String)mav.getModel().get("message"), is("Hello Spring"));
//		
//	}
	
	@Test
	public void controllerTest() throws Exception {
		GenericXmlApplicationContext ac = new GenericXmlApplicationContext("spring-servlet.xml");
		HelloController helloController = ac.getBean(HelloController.class);
		
		MockHttpServletRequest req = new MockHttpServletRequest("GET", "/hello");
		req.addParameter("name", "Spring");
		MockHttpServletResponse resp = new MockHttpServletResponse();
		
		ModelAndView mav = helloController.handleRequest(req, resp);
		
		assertThat(mav.getViewName(), is("/WEB-INF/views/hello.jsp"));
		assertThat((String)mav.getModel().get("message"), is("Hello Spring"));
	}
}
