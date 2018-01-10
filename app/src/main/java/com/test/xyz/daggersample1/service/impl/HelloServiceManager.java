package com.test.xyz.daggersample1.service.impl;

import com.test.xyz.daggersample1.service.api.HelloService;

public class HelloServiceManager implements HelloService {

    @Override
    public String greet(String userName) {
        return "Hello " + userName + "!";
    }
}
