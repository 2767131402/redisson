package com.zhenglei.redisson.lock;

import org.apache.http.HttpStatus;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class SyncController {
    @Autowired
    private RedissonClient redissonClient;

    /**
     * 同步获取锁
     */
    @RequestMapping("/syncLock")
    public int sync() {
        RLock lock = null;
        try {
//            lock = redissonClient.getFairLock("syncLock");//公平锁
            lock = redissonClient.getLock("syncLock");//非公平锁
            lock.lock();
            System.err.println(formatDate() + " " + Thread.currentThread().getName() + "获得锁");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (null != lock && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
        return HttpStatus.SC_OK;
    }

    /**
     * 当前时间
     */
    private String formatDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}
