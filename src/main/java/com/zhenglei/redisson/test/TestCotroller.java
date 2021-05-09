package com.zhenglei.redisson.test;

import com.zhenglei.redisson.utils.HttpClientUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestCotroller {

    @RequestMapping("/testSync")
    public void testSync(){
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpClientUtils.getInstance().httpGet("http://localhost:8080/syncLock");
                }
            });
            t.start();
        }
        System.err.println("----------over----------");
    }
    @RequestMapping("/testAsync")
    public void testAsync(){
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpClientUtils.getInstance().httpGet("http://localhost:8080/asyncLock");
                }
            });
            t.start();
        }
        System.err.println("----------over----------");
    }
    @RequestMapping("/testToken")
    public void token(){
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpClientUtils.getInstance().httpGet("http://localhost:8080/token");
                }
            });
            t.start();
        }
        System.err.println("----------over----------");
    }
}
