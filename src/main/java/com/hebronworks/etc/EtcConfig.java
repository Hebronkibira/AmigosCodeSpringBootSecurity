package com.hebronworks.etc;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class EtcConfig {

    @Bean
    public Address getAdress(){
        return new Address("Kimathi","Nairobi");
    }

}
