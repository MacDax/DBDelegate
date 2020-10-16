package com.spring.boot.framework.dbdelegate.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.spring"})
public class Main 
{
    public static void main( String[] args )
    {
        DatabaseManager db = new DatabaseManager();
        SpringApplication.run(Main.class);
    }
}
