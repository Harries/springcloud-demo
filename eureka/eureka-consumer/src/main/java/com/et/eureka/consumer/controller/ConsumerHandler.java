package com.et.eureka.consumer.controller;

import com.et.eureka.consumer.model.Student;
import com.et.eureka.consumer.service.MyFeignClient;
import com.netflix.appinfo.InstanceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/consumer")
@Slf4j
public class ConsumerHandler {
    @Autowired
    RestTemplate restTemplate;
    @Resource
    private DiscoveryClient discoveryClient;
    @Autowired
    private MyFeignClient myFeignClient;
    @GetMapping("/findAll")
    public String findAll(){
        return myFeignClient.findAll();
    }

    @GetMapping("/findAll2")
    public Collection findAll2(){
        return restTemplate.getForObject("http://PROVIDER/student/findAll",Collection.class);
    }
    @GetMapping("/findAll3")
    public String findAll3(){
        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> instances = discoveryClient.getInstances("PROVIDER");
        if (instances == null || instances.size() == 0) {
            log.info("instances is empty..");
            return "";
        }

        String uri = instances.get(0).getUri().toString();
        String serviceUrl = String.format("%s/student/findAll", uri);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);
        log.info("uri is :{},formatted url is:{}", uri, serviceUrl);
        ResponseEntity<String> resultExchange = restTemplate.exchange(serviceUrl, HttpMethod.GET, entity, String.class, "");
        if (resultExchange == null) {
            log.info("invoke result is null");
            return "";
        }
        String body = resultExchange.getBody();
        log.info("body is :{}", body);
        return body;


    }

    @GetMapping("/findById/{id}")
    public Student findById(@PathVariable("id") long id){
        return restTemplate.getForEntity("http://PROVIDER/student/findById/{id}",Student.class,id).getBody();
    }

    @GetMapping("/findById2/{id}")
    public Student findById2(@PathVariable("id") long id){
        return restTemplate.getForObject("http://PROVIDER/student/findById/{id}",Student.class,id);
    }

    @PostMapping("/save")
    public void save(@RequestBody Student student){
        restTemplate.postForEntity("http://PROVIDER/student/save",student,null).getBody();
    }

    @PostMapping("/save2")
    public void save2(@RequestBody Student student){
        restTemplate.postForObject("http://PROVIDER/student/save",student,null);
    }

    @PutMapping("/update")
    public void update(@RequestBody Student student){
        restTemplate.put("http://PROVIDER/student/update",student);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") long id){
        restTemplate.delete("http://PROVIDER/student/deleteById/{id}",id);
    }
}
