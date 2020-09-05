package com.example.demo.service;


import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by liangyourong on 2020/8/8.
 */
@RestController
public class DcController {

    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    EurekaClient eurekaClient;


    @GetMapping("/client4")
    public String client4() {
        List<InstanceInfo> instances = eurekaClient.getInstancesByVipAddress("provider",false);
        for(InstanceInfo ins:instances) {
            System.out.println(ToStringBuilder.reflectionToString(ins));
        }
        if(instances.size()>0) {
            InstanceInfo instanceInfo = instances.get(0);
            if(instanceInfo.getStatus()== InstanceInfo.InstanceStatus.UP) {
                String url = "http://"+instanceInfo.getHostName()+":"+instanceInfo.getPort()+"/getHi";
                System.out.println(url);
                RestTemplate restTemplate = new RestTemplate();
                String res = restTemplate.getForObject(url,String.class);
                System.out.println("res:"+res);
            }
        }
        return "xxoo";
    }

}
