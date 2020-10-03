package com.dk.parent.lock;

import org.openjdk.jol.info.ClassLayout;

public class MyLockDemo {
    public static void main(String[] args) {
        Object o = new Object();
        String s = ClassLayout.parseInstance(o).toPrintable();
        System.out.println("------------------S--------------------------");
        System.out.println(s);



        Object o1 = new Object();
        System.out.println("------------------S1--------------------------");
        synchronized (o1){
            String s1 = ClassLayout.parseInstance(o).toPrintable();
            System.out.println(s1);
        }



//        System.out.println(o2);
//        System.out.println("------------------------------------------------");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Object o2 = new Object();
        System.out.println("----------------------S2--------------------------");
        synchronized (o2){
            String s2 = ClassLayout.parseInstance(o2).toPrintable();
            System.out.println(s2);
        }

    }
}
