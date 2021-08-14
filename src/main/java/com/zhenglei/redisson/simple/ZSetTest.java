package com.zhenglei.redisson.simple;

import com.zhenglei.redisson.Base;
import org.redisson.api.RScoredSortedSet;
import org.redisson.client.codec.StringCodec;
import org.redisson.client.protocol.ScoredEntry;

import java.util.Collection;

public class ZSetTest extends Base {
    public static void main(String[] args) {
        RScoredSortedSet<Object> zset = redissonClient.getScoredSortedSet("test:zset", new StringCodec());
        zset.add(1.0,"zhangsan");
        zset.add(3.0,"wangwu");
        zset.add(4.0,"zhaoliu");
        zset.add(2.0,"lisi");

        Collection<Object> objects = zset.readAll();
        System.err.println(objects);

        //一移除第一个元素并返回
//        Object o = zset.pollFirst();
//        System.err.println(o);

        // 获取元素在集合中的位置
        Integer zhaoliu = zset.rank("zhaoliu");
        Integer aaa = zset.rank("aaa");
        System.err.println("zhaoliu: " + zhaoliu + " aaa: " + aaa);

        // 获取元素的评分
        Double wangwu = zset.getScore("wangwu");
        System.err.println(wangwu);

        System.err.println("============================");

        //获取区间分数倒叙的值
        Collection<Object> objects1 = zset.valueRangeReversed(2, 3);
        System.err.println(objects1);

        System.err.println("============================");

        Collection<ScoredEntry<Object>> scoredEntries1 = zset.entryRange(2, 3);
        scoredEntries1.forEach(i->{
            System.err.println("entryRange: score: " + i.getScore() + " value: " + i.getValue());
        });

        System.err.println("============================");
        //获取区间分数倒叙排列 的值和分数
        Collection<ScoredEntry<Object>> scoredEntries2 = zset.entryRangeReversed(1, 3);
        scoredEntries2.forEach(i->{
            System.err.println("entryRangeReversed: score: " + i.getScore() + " value: " + i.getValue());
        });
    }
}
