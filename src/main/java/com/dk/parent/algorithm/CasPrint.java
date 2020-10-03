package com.dk.parent.algorithm;

public class CasPrint {

    enum Ready2Run{T1, T2}

    // 思考：为什么必须是volatile
    static volatile Ready2Run r = Ready2Run.T1;

    public static void main(String[] args) {

        String s = "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26";
        String[] aI = s.split(",");
//        char[] aI = "1234567891011121314151617181920212223242526".toCharArray();
        char[] aC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        new Thread(() -> {
            for (String c : aI) {
                while (r != Ready2Run.T1) {}
                System.out.print(c);
                r = Ready2Run.T2;
            }
        }, "t1").start();

        new Thread(() -> {
            for (char c : aC) {
                while (r != Ready2Run.T2) {}
                System.out.print(c);
                r = Ready2Run.T1;
            }
        }, "t2").start();
    }
}
