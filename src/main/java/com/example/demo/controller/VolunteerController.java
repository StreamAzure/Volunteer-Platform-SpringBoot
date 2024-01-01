package com.example.demo.controller;

import com.example.demo.pojo.LogRequest;
import com.example.demo.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class VolunteerController {
    @Autowired
    private VolunteerService volunteerService;

    @PostMapping("/log")
    public ResponseEntity<String> logVolunteerHours(@RequestBody LogRequest logRequest) {
        try {
            System.out.println("logVolunteerHours 接收数据:\n" + logRequest.toString());
            String userName = logRequest.getUsername();
            int hour = logRequest.getDuration();
            volunteerService.logVolunteerHours(userName, hour);

            // 如果保存成功，返回成功消息和状态码
            return new ResponseEntity<>("成功提交志愿时长登记", HttpStatus.OK);
        } catch (Exception e) {
            // 如果保存失败，返回失败消息和状态码
            return new ResponseEntity<>("提交志愿时长登记失败：" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/apply")
    @ResponseBody
    public void applyCertificate(@RequestParam String username){
        volunteerService.applyCertificate(username);
    }
}
