package com.dk.parent.algorithm;

import java.util.concurrent.CountDownLatch;

public class WaitNotifyPrint2 {

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
            /*try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            synchronized (o) {
                while (!t2Started) {
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
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
//                    latch.countDown();
                    t2Started = true;
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
    // 线程具有多种状态：刚开始new的时候叫new状态，结束的时候叫做Teminated，如果你调用Thread.sleep(time),
    // o.wait(time),t.join(time),LockSuport.parkNanos(),LockSupport.parkUtil()方法会进入TimedWaiting
    // 状态，如果调用o.wait(),t.join(),LockSuport.park()方法会进入Waiting状态，
    // 调用wait会进入Blocked状态？
    // 当你new完之后调用start方法的时候，这个线程开始运行并不代表它马上就占用CPU开始执行，而是进入到CPU的等待
    // 队列里面，也就是说进入了Ready状态，即准备好了，可以开始执行任务了。只有它被线程调度器选中执行才开始执行
    // 进入Running状态；当线程被挂起，或者调用Thread.yield方法会重新进入Ready状态。
    // t1.start和t2.start都表示他们两个准备好了，至于CPU会选哪一个，这是CPU调度的事情，不一定会选哪一个。

    // 如果我想保证t2在t1之前打印，即先打印A而不是1，该怎么处理？
    // 1.sleep一段时间，稍微长一点时间，让t2执行完再执行t1.只是不怎么优雅，如果只sleep几毫秒，
    // 很可能不能防止t1先运行，只能稍微长一点，比如几秒钟。
    // 2.setPriority()设置线程的优先级并不能保证一定会优先执行，只是分配的CPU资源多了一些。
    // 3.CountDownLatch.
    // 4.定义一个标志位
    // cas和CountDownLatch比较优雅
}
