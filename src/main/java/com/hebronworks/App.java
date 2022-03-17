package com.hebronworks;

import com.hebronworks.etc.Company;
import com.hebronworks.etc.EtcConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);

//        ApplicationContext context= new AnnotationConfigApplicationContext(EtcConfig.class);
//        Company company= context.getBean(Company.class);
//        System.out.println(company);
    }
}