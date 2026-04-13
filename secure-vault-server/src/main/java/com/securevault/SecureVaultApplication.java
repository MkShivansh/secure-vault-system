package com.securevault;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class SecureVaultApplication {
    @Bean
    public ServletRegistrationBean<HelloServlet> servletBean() {
        return new ServletRegistrationBean<>(new HelloServlet(), "/hello");
    }
    public static void main(String[] args) {
        SpringApplication.run(SecureVaultApplication.class, args);
    }
}