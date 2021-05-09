package com.zhenglei.redisson.lock;

import org.apache.http.HttpStatus;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@RestController
public class AsyncController {
    @Autowired
    private RedissonClient redissonClient;

    /**
     * 同步获取锁
     */
    @RequestMapping("/asyncLock")
    public int async() {
        RLock lock = null;
        try {
//            lock = redissonClient.getFairLock("syncLock");//公平锁
            lock = redissonClient.getLock("syncLock");//非公平锁
            if (lock.tryLock()) {
                System.err.println(formatDate() + " " + Thread.currentThread().getName() + "获得锁");
                Thread.sleep(2000);
            } else {
                System.err.println(formatDate() + " " + Thread.currentThread().getName() + "未获得锁");
            }
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
     * 同步获取锁
     */
    @RequestMapping("/asyncLock1")
    public int async1() {
        RLock lock = null;
        try {
//            lock = redissonClient.getFairLock("syncLock");//公平锁
            lock = redissonClient.getLock("syncLock");//非公平锁
            //最多等待锁2秒，10秒后强制解锁
            if (lock.tryLock(2, 10, TimeUnit.SECONDS)) {
                System.err.println(formatDate() + " " + Thread.currentThread().getName() + "获得锁");
                Thread.sleep(2000);
            } else {
                System.err.println(formatDate() + " " + Thread.currentThread().getName() + "未获得锁");
            }
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
