package com.xian.learn.springboot.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @ClassName HelloController
 * @Description
 * @Author lenovo
 * @Date 2019/9/15 10:43
 * @Version 1.0.0
 **/
@RestController
public class HelloController {

    private final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private Registration registration;
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/hello")
    public String index() {
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances(registration.getServiceId());
        if (serviceInstanceList != null && serviceInstanceList.size() > 0) {
            logger.info("/hello, host:[{}], serviceId:[{}],", serviceInstanceList.get(0).getHost(), serviceInstanceList.get(0).getServiceId());
        }
        return "Hello world";
    }
}
