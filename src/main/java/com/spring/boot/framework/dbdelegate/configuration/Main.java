package com.spring.boot.framework.dbdelegate.configuration;

import org.springframework.boot.SpringApplication;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args )
    {
        DatabaseManager db = new DatabaseManager();
        SpringApplication.run(Main.class);
    }
}
