package com.jun.main;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component
public class Hello {
	String name;
	
	@Resource
	Printer printer;
	
	public String sayHello() {
		return "Hello " + name;
	}
	
	public void print() {
		this.printer.print(sayHello());
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrinter(Printer printer) {
		this.printer = printer;
	}
	

}
