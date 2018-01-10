package com.test.xyz.daggersample1.service.impl;

import com.test.xyz.daggersample1.service.api.HelloService;

public class HelloServiceReleaseManager implements HelloService {

    @Override
    public String greet(String userName) {
        return "[Release] Hello " + userName + "!";
    }
}
