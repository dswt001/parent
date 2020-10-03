package com.dk.parent.thread;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

public class Test {

    private static final Log LOG = LogFactory.getLog(Test.class);

    public static void main(String[] args) {

        String str = "{(){}[]";
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
//            System.out.println(chars[i]);
        }
        judgeStr(str);

    }

    public static boolean judgeStr(String str) {
        char[] chars = str.toCharArray();

        int a1 = 0;
        int a2 = 0;
        int b1 = 0;
        int b2 = 0;
        int c1 = 0;
        int c2 = 0;
        String s;

        for (int i = 0; i < chars.length; i++) {
            s = String.valueOf(chars[i]);
          /*  if (s.equals("(")) {
                a1 += 1;
            } else if (s.equals(")")) {
                a2 += 1;
            } else if (s.equals("{")) {
                b1 += 1;
            } else if (s.equals("}")) {
                b2 += 1;
            } else if (s.equals("[")) {
                c1 += 1;
            } else if (s.equals("]")) {
                c2 += 1;
            }*/

            switch (s) {
                case "(":
                    a1 += 1;
                case ")":
                    a2 += 1;
                case "{":
                    b1 += 1;
                case "}":
                    b2 += 1;
                case "[":
                    c1 += 1;
                case "]":
                    c2 += 1;
            }
        }

        System.out.println(a1);
        System.out.println(a2);
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(c1);
        System.out.println(c2);

        if (a1 == a2 && b1 == b2 && c1 == c2) {
            LOG.info("---------str is double---------");
            return true;
        } else {
            LOG.info("---------str is not double---------");
            return false;
        }
    }
}
