package com.dk.parent.thread;

import java.text.DecimalFormat;

/**
 * ThreadLocl的介绍与简单使用
 * ThreadLocal的作用主要是做数据隔离，填充的数据只属于当前线程，变量的数据对别的线程而言是相对隔离的，在多线程环境下，
 * 如何防止自己的变量被其他线程篡改。
 * Spring实现事务隔离级别的源码中就使用了ThreadLocal：
 * Spring采用ThreadLocal的方式来保证单个线程中的数据库操作使用的是同一个数据库连接，同时，采用这种方式可以使业务层使用事务时
 * 不需要感知并管理connection对象，通过传播级别，巧妙地管理多个事务配置之间的切换、挂起和恢复。
 *
 * @date 2020-7-27 22:05:53
 * @author fang dake
 */
public class ThreadLocalDemo {

    private static ThreadLocal<DecimalFormat> df = ThreadLocal.withInitial(() -> new DecimalFormat("0.00"));

    public static String formatAsPerson(Long one) {
        if (one == null) {
            return null;
        }

        // 亿
        if (one >= 100000000L) {
            return String.format("%s亿", df.get().format(one * 1.00d / 100000000.00d));
        } else {
            return "不足一亿";
        }
    }
}
