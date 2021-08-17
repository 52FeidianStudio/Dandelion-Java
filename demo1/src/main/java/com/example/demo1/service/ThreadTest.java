package com.example.demo1.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ThreadTest {

    @Async("teskExcutor")
    public void update() {
        try {
            System.out.println("thread"+new Date());
            Thread.sleep(3000);
            System.out.println("thread"+new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
