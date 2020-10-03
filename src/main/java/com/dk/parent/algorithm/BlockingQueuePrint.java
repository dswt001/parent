package com.dk.parent.algorithm;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * 利用队里的阻塞特性
 */
public class BlockingQueuePrint {

    static BlockingQueue<String> q1 = new ArrayBlockingQueue<>(1);
    static BlockingQueue<String> q2 = new ArrayBlockingQueue<>(1);

    /**
     * 有两个线程，如果t1先执行，则往q1中放入一个值，然后从q2中取值，此时取不到值，会一直等待，此时t2会执行；
     * t2从q1中取值，取到值了，打印，然后往q2中放入一个值；如果未取到值，一直等待，等待t1先执行。
     *
     *
     * @param args
     */
    public static void main(String[] args) {

        String s = "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26";
        String[] aI = s.split(",");
//        char[] aI = "1234567891011121314151617181920212223242526".toCharArray();
        char[] aC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        // take和put都会阻塞，取不到值的时候就会阻塞
        new Thread(() -> {
            for (String c : aI) {
                System.out.print(c);
                try {
                    q1.put("ok");
                    // take是阻塞，阻塞在这里，需要从q2中拿一个值出来
                    q2.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        new Thread(() -> {
            for (char c : aC) {

                try {
                    q1.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(c);
                try {
                    q2.put("ok");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2").start();
    }
}
