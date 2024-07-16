package com.er.eureka.provider.controller;

import com.er.eureka.provider.model.Student;
import com.er.eureka.provider.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentHandler {
    @Autowired
    private StudentService studentService;
    @Value("${server.port}")
    private String port;
    @GetMapping("/findAll")
    public Collection findAll(){
        return studentService.findAll();
    }

    @GetMapping("/findById/{id}")
    public Student findById(@PathVariable("id") long id){
        return studentService.findById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody Student student){
        studentService.saveOrUpdate(student);
    }

    @PutMapping("/update")
    public void update(@RequestBody Student student){
        studentService.saveOrUpdate(student);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") long id){
        studentService.deleteById(id);
    }

    @GetMapping("/index")
    public String index(){
        return "当前端口："+this.port;
    }
}
