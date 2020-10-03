package com.dk.parent.lock;

public class LockDemo {

    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        o.wait();
        o.notify();
        o.notifyAll();

        Thread thread = new Thread();
        thread.start();
        thread.run();
        thread.join();


    }
}
