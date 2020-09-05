package com.example.demo.service;


import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liangyourong on 2020/8/8.
 */
@RestController
public class DcController {

    @Autowired
    DiscoveryClient discoveryClient;


    @GetMapping("/getHi")
    public String hi() {
        return "Hi";
    }

}
