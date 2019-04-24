package com.liust.server;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

import javax.annotation.PostConstruct;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class GeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeneratorApplication.class, args);
    }

    @PostConstruct
    public void init() throws IOException {
        DefaultListableBeanFactory defaultListableBeanFactory=new DefaultListableBeanFactory();
        ClassLoader beanClassLoader = defaultListableBeanFactory.getBeanClassLoader();
        Enumeration<URL> resources = beanClassLoader.getResources("com.liust.server");
        System.out.println(defaultListableBeanFactory);
    }
}
