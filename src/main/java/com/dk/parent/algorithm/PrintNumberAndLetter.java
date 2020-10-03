package com.dk.parent.algorithm;

import java.util.concurrent.locks.LockSupport;

/**
 * 交叉打印数字和字母；
 * 考察的是线程间的通信；
 * 线程间除了同步，就是线程间的通信；
 * 推荐的下发就是通过LockSupport.park()和LockSupport.unpark(t2)。
 *
 * @date 2020-7-23 00:09:14
 * @author dake
 */
public class PrintNumberAndLetter {

    static Thread t1 = null;
    static Thread t2 = null;

    // 该方法有问题，只能打印10一下的交叉执行，10以上的因为数字转换成char类型的时候会被分割成两部分，
    // 打印的时候也会被分开，最终线程会一直被阻塞
    public static void main(String[] args) {
        String s = "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26";
        String[] aI = s.split(",");
//        char[] aI = "1234567891011121314151617181920212223242526".toCharArray();
        char[] aC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        t1 = new Thread(() -> {

            for (String c : aI) {
                System.out.print(c);
                // 让t2先执行
                LockSupport.unpark(t2);
                // 让t1阻塞
                LockSupport.park();
            }
        }, "t1");

        t2 = new Thread(() -> {
            for (char c : aC) {
                // t2上来就阻塞
                LockSupport.park();
                System.out.print(c);
                LockSupport.unpark(t1);
            }
        }, "t2");

        // 1.如果t1先执行，那么会打印数字，然后让t2执行；
        // 2.如果t2已经执行了，再执行LockSupport.park();就不会让t2阻塞，这是LockSupport.park()的特性；打印字母，然后让t1执行

        // 1.如果t2先执行，上来t2就阻塞，然后t1执行；、
        // 2.t1执行的时候先打印数字，然后让t2执行，此时让t1阻塞；
        t1.start();
        t2.start();
    }
}
