package com.dk.parent.algorithm;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 利用管道流的特性；
 * 管道流是专门用来实现线程间通信的，接收数据和发送数据。是半双工的，一个只能发，一个只能收，不能用同一个管道
 * 同时收、同时发。
 * 效率太低，写着玩的。
 */
public class PipedInputStreamPrint {


    /**
     * 在一个线程中如果有一个OutputStream，另一个线程中有一个InputStream，则可以把这两个管道流连起来；
     * 这样第一个线程往里面写，第二个线程 就能接收到。如果t2想和t1通信，用另一个管道输出，写入到t1的线程
     * 的输入流中。
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {

        String s = "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26";
        String[] aI = s.split(",");
//        char[] aI = "1234567891011121314151617181920212223242526".toCharArray();
        char[] aC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        PipedInputStream input1 = new PipedInputStream();
        PipedInputStream input2 = new PipedInputStream();
        PipedOutputStream output1 = new PipedOutputStream();
        PipedOutputStream output2 = new PipedOutputStream();

        input1.connect(output2);
        input2.connect(output1);

        String msg = "Your Turn";

        new Thread(() -> {
            byte[] buffer = new byte[9];

            try {
                for (String c : aI) {
                    input1.read(buffer);
                    if (new String(buffer).equals(msg)) {
                        System.out.print(c);
                    }
                    output1.write(msg.getBytes());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            byte[] buffer = new byte[9];

            try {
                for (char c : aC) {
                    System.out.print(c);
                    output2.write(msg.getBytes());
                    input2.read(buffer);
                    if (new String(buffer).equals(msg)) {
                        continue;
                    }
//                    output1.write(msg.getBytes());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }, "t2").start();

    }
}
