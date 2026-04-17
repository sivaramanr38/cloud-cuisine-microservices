package com.cloudcuisine.customerservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Value("${spring.datasource.url}")
    private String springDatasourceUrl;

    @GetMapping("/")
    public String sayHello(){
        return "Hello World";
    }

    @GetMapping("/config")
    public String getConfig(){
        return springDatasourceUrl;
    }
}
