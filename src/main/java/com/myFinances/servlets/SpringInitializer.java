package com.myFinances.servlets;

import com.myFinances.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class SpringInitializer implements ServletContextListener {

    public static final String SPRING_CTX = "springCtx";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:Beans.xml");

        UserServiceImpl.getInstance()._init(context);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
