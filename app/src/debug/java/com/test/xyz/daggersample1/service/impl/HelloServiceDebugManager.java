package com.test.xyz.daggersample1.service.impl;

import com.test.xyz.daggersample1.service.api.HelloService;

public class HelloServiceDebugManager implements HelloService {

    @Override
    public String greet(String userName) {
        return "[Debug] Hello " + userName + "!";
    }
}
