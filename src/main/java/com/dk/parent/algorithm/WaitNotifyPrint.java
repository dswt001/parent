package com.dk.parent.algorithm;

import java.util.concurrent.CountDownLatch;

/**
 * 这个问题考察的其实就是使用synchronized、wait、notify的知识点。
 * 这个要求比较高。
 * 1.想使用wait和notify的时候，必须对某个对象加锁，也就是锁定某个对象；
 * synchronized的意思是：
 * 它锁定的是某个对象，这里锁定的是o，而不是这个代码块。
 * 在Hotspot的实现中，我们new完对象的时候，这个对象上有一个对象头，有两位数字代表这个对象有没有被锁定；
 * 锁定的话就是10或者01，没有锁定就是00.刚新建的时候是00，被我们锁定后会变为10或者01。其他的线程想拿到
 * 这把锁的时候发现已经被别的线程锁定，那么就等待，或者自旋或者其他操作。
 * 当我们使用synchronized的时候，我们锁定了某个对象，我们才能执行大括弧里面的代码。
 *
 * 这里使用this不行，因为我们使用的lambda表达式，这里是匿名内部类，我们t1中锁定的是t1中的匿名内部类，t2锁定的是t2中的
 * 匿名内部类，这两个线程之间不能形成同步，互相之间没有任何关系，所以不行。
 * 但是如果不使用匿名内部类就可以使用this了。
 *
 * 你只有对这个对象加完锁之后才能调用这个对象的notify和wait方法，notify的意思是：
 * 比如A已经持有这把锁了，与此同时还有B,C,D等多个线程（这里叫做等待队列）在这个对象上等待这把锁被释放，
 * notify的意思是叫醒这个对象上的等待队列中的某一个（任意一个）线程，notifyAll是叫醒等待队列中的所有线程。
 * 等待队列中的线程必须被叫醒才能够继续向下运行，有可能获取到这把锁。
 *
 * wait什么意思：
 * 当前线程正在运行，如果我调用o.wait()的话，当前线程就进入等待队里了，与此同时我释放这把锁，叫让出锁。
 *
 */
public class WaitNotifyPrint {

    private static volatile boolean t2Started = false;

//    private static CountDownLatch latch = new CountDownLatch(1);

    /**
     * 运行步骤：
     * t1先持有这个对象的锁，打印，然后叫醒t2，同时t1进入等待队列；
     * t2获取到这个对象的锁之后，打印，然后叫醒t1，同时自己进入等待队列；
     * 依次交叉执行。
     *
     * @param args
     */
    public static void main(String[] args) {

        final Object o = new Object();

        String s = "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26";
        String[] aI = s.split(",");
        //        char[] aI = "1234567891011121314151617181920212223242526".toCharArray();
        char[] aC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        // 我第一个线程打印c之后必须叫醒一个线程，同时自己进入等待队列
        new Thread(() -> {
            synchronized (o) {
                for (String c : aI) {
                    System.out.print(c);
                    // 不可以先wait在notify，因为wait之后该线程进入等待队列，
                    // 释放了CPU的执行权，无法叫醒另外的线程
                    try {
                        // 叫醒等待队列里面的某一个线程
                        o.notify();
                        // 当前线程让出锁，进入等待队列
                        o.wait();// 让出锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // t1和t2最后的两个notify必不可少
                // 因为不管怎么执行，最后总有一个线程会进入wait状态，如果不进行叫醒
                // 会一直在等待队列中阻塞，程序无法结束。
                o.notify();// 必须，否则无法停止程序
            }
        }, "t1").start();

        // 争取拿到这把锁，输入一个值后，叫醒等待队列里的某一个线程，然后自己进入等待队列
        new Thread(() -> {
            synchronized (o) {
                for (char c : aC) {
                    System.out.print(c);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        }, "t2").start();
    }
    // 谁先执行的问题：不能确定t1还是t2先执行。

    // 如果我想保证t2在t1之前打印，即先打印A而不是1，该怎么处理？
    // 1.sleep一段时间，稍微长一点时间，让t2执行完再执行t1.只是不怎么优雅，如果只sleep几毫秒，
    // 很可能不能防止t1先运行，只能稍微长一点，比如几秒钟。
    // 2.setPriority()设置线程的优先级并不能保证一定会优先执行，只是分配的CPU资源多了一些。
    // 3.CountDownLatch.

}
