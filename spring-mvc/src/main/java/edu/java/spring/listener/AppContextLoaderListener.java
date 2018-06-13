package edu.java.spring.listener;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

public class AppContextLoaderListener extends ContextLoaderListener {
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		super.contextDestroyed(event);
		System.out.println("-------> da huy ung dung");

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		super.contextInitialized(event);
		System.out.println("-------> da khoi tao ung dung");
	}
}
