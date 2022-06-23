package com.peter.moon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.*;
import java.util.stream.Stream;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class MoonApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(MoonApplication.class, args);
    }
}
