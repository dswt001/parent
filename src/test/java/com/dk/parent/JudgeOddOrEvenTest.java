package com.dk.parent;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JudgeOddOrEvenTest {


    @Test
    public void judgeOddOrEven() {
        int number = 10;

        // 测试基数和偶数：0-偶数，1-基数，不管正负数都可以
        if ((number & 1) == 0) {
            System.out.println("---------这是个偶数---------");
        }

        if ((number & 1) == 1) {
            System.out.println("---------这是个基数---------");
        }

        // ＆＆逻辑与，｜｜逻辑或，它们都是逻辑运算符
        // ＆按位与，｜按位或　　它们都是位运算符
//        ｉｆ（ａ＝＝１＆＆ｂ＝＝２）　这是说既要满足ａ＝１也要满足ｂ＝２
//        ｉｆ（ａ＝＝１｜｜ｂ＝＝２）　这是说或者满足ａ＝１或者要满足ｂ＝２
//        而ａ＆ｂ或者ａ｜ｂ则是二进制的与或运算
//        ＆同为１时为１，否则为０
//        ｜同为０时为０，否则为１
        System.out.println(-10 & 1);
        System.out.println(10 | 0);
    }



}
