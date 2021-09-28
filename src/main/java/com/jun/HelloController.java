package com.jun;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class HelloController implements Controller {
	@Autowired HelloSpring helloSpring;

	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String name = req.getParameter("name");
		String message = this.helloSpring.sayHello(name);
		HashMap<String, Object> model = new HashMap<String, Object>();
		model.put("message", message);
		return new ModelAndView("/WEB-INF/views/hello.jsp", model);
	}

}