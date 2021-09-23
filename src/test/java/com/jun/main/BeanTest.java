package com.jun.main;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;


public class BeanTest {

	@Test
	public void getBeanTest() {
		StaticApplicationContext ac = new StaticApplicationContext();
		ac.registerSingleton("hello1", Hello.class);

		Hello hello1 = ac.getBean("hello1", Hello.class);
		assertThat(hello1, is(notNullValue()));

		RootBeanDefinition helloDef = new RootBeanDefinition(Hello.class);

		helloDef.getPropertyValues().add("name", "Spring");
		ac.registerBeanDefinition("hello2", helloDef);

		Hello hello2 = ac.getBean("hello2", Hello.class);
		assertThat(hello2, is(notNullValue()));
		assertThat(hello2.name, is("Spring"));
		assertThat(hello2.sayHello(), is("Hello Spring"));
		assertThat(ac.getBeanFactory().getBeanDefinitionCount(), is(2));
	}

	@Test
	public void registerBeanWithDependency() {
		StaticApplicationContext ac = new StaticApplicationContext();
		ac.registerBeanDefinition("printer", new RootBeanDefinition(StringPrinter.class));

		RootBeanDefinition helloDef = new RootBeanDefinition(Hello.class);
		helloDef.getPropertyValues().addPropertyValue("name", "Spring");
		helloDef.getPropertyValues().add("printer", new RuntimeBeanReference("printer"));

		ac.registerBeanDefinition("hello", helloDef);

		Hello hello = ac.getBean("hello", Hello.class);
		hello.print();

		assertThat(ac.getBean("printer").toString(), is("Hello Spring"));
	}


	@Test
	public void childAndParentContextTest() {
		String basePath = StringUtils.cleanPath(ClassUtils.classPackageAsResourcePath(getClass()) + "/");
		ApplicationContext parent = new GenericXmlApplicationContext("parentContext.xml");
		GenericApplicationContext child = new GenericApplicationContext(parent);
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(child);
		reader.loadBeanDefinitions("childContext.xml");
		child.refresh();

		Printer printer = child.getBean("printer", Printer.class);
		assertThat(printer, is(notNullValue()));

		Hello hello = child.getBean("hello", Hello.class);
		hello.print();
		assertThat(hello, is(notNullValue()));
		assertThat(printer.toString(), is("Hello Child"));
	}

	@Test
	public void simpleBeanScanning() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext("com.jun.main");
		AnnotatedHello hello = ctx.getBean("annotatedHello", AnnotatedHello.class);

		assertThat(hello, is(notNullValue()));
	}

	@Test
	public void simpleBeanScanningUsingJavaConfig() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AnnotatedHelloConfig.class);
		AnnotatedHello hello = ctx.getBean("annotatedHello", AnnotatedHello.class);

		assertThat(hello, is(notNullValue()));

		AnnotatedHelloConfig config = ctx.getBean("annotatedHelloConfig", AnnotatedHelloConfig.class);
		assertThat(config, is(notNullValue()));
		assertThat(config.annotatedHello(), is(sameInstance(hello)));
	}

	@SuppressWarnings("resource")
	@Test
	public void simpleAtAutowired() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(BeanA.class, BeanB.class);
		BeanA beanA = ac.getBean(BeanA.class);
		assertThat(beanA.beanB, is(notNullValue()));
	}

	private static class BeanA {
		@Autowired BeanB beanB;
	}
	
	private static class BeanB {
		
	}
}
