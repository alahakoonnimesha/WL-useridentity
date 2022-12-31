package com.nishiProductions.wluserIdentity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class WlUserIdentityApplication {

    public static void main(String[] args) {
        SpringApplication.run(WlUserIdentityApplication.class, args);
    }

}
