package com.dk.parent.algorithm;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 这里是通过lock和condition，有一种Condition和两种Condition两种解法，两种Condition相当于有两把锁，
 * lock如果只有一种Condition的话和wait、notify没有什么区别。
 * synchronized只有一把锁，或者叫做只有一种状态，一种条件，但是使用ReentrantLock可重入锁的话，可以使用一把锁
 * 做出两种条件，在一种条件下锁定第一个线程，另外的条件下锁定第二个线程。
 *
 * 上来后先lock。想使用condition必须先lock，然后第一个线程打印c，然后通知第二个线程，即谁在t2条件下等待的那个线程启动。
 * 然后在condition1这个条件下等待。
 * 直观理解，原来有一把锁，我通过不同的条件把它转化为了两把不同条件下的锁定，就是两把锁。
 * t1线程等待一把锁上，t2线程等待在另一把锁上，t1执行完一遍之后告诉等待在t2这个线上的启动一个，t2完了告诉t1线程启动一个。
 * 我们能精确的通知在哪种条件下通知哪些线程。
 *
 * 和notify的区别：
 * notify在整个等待队列中，无法精确指定让哪个线程启动，或者让某一类线程选一个启动。
 * 但是condition就优雅的多，我们可以精确的指定在哪种条件下等待哪个线程启动。
 *
 * 这个题目最好的解法有两种：
 * 1.lockSupport，代码很简洁也能说清楚；
 * 2.Lock、Condition。
 * 这两种是最好的，当然第三种，你能写的特别完善也是不错的，就是synchronized、wait、notify。
 * Lock、Condition是看起来最舒服的。
 *
 */
public class Lock_Condition_Print2 {

    public static void main(String[] args) {
        String s = "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26";
        String[] aI = s.split(",");
        //        char[] aI = "1234567891011121314151617181920212223242526".toCharArray();
        char[] aC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();

        new Thread(() -> {
            try {
                lock.lock();
                for (String c : aI) {
                    System.out.print(c);
                    condition2.signal();
                    condition1.await();
                }
                condition2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                lock.lock();
                for (char c : aC) {
                    System.out.print(c);
                    condition1.signal();
                    condition2.await();
                }
                condition2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
}
