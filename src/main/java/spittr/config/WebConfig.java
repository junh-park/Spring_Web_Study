package spittr.config;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.MessageSourceResourceBundleLocator;
import org.springframework.web.context.support.ServletConfigPropertySource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
@EnableWebMvc
@ComponentScan("spittr.web")
public class WebConfig implements WebMvcConfigurer {
	
	@Bean
	public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
//		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//		resolver.setPrefix("/WEB-INF/views/");
//		resolver.setSuffix(".jsp");
//		resolver.setExposeContextBeansAsAttributes(true);
//		return resolver;
		
		 ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		 viewResolver.setTemplateEngine(templateEngine);
		 return viewResolver;
	}
	
	@Bean
	public TemplateEngine templateEngine(ITemplateResolver templateResolver) {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
		return templateEngine;
	}
	
	@Bean
	public ServletContextTemplateResolver templateResolver(ServletContext context) {
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(context);	
		templateResolver.setPrefix("/WEB-INF/views/");
	    templateResolver.setSuffix(".html");
	    templateResolver.setTemplateMode("HTML5");
	    return templateResolver;
	}
	
	@Bean
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/messages");
		messageSource.setCacheSeconds(10);
		return messageSource;
	}
	
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tiles = new TilesConfigurer();
		tiles.setDefinitions(new String[] { "/WEB-INF/layout/tiles.xml" });
		tiles.setCheckRefresh(true);
		return tiles;
	}
}
