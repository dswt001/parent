package com.dk.parent.loop;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ForLoop {

    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        int size = list.size();
        // for循环
      /*  IntStream.range(0, 5).forEach(
               i -> System.out.println(i)
        );

        IntStream.rangeClosed(0, 5).forEach(
                i -> System.out.println(i)
        );*/

        IntStream.iterate(0, e -> e + 3).takeWhile(a -> a > 100).sum();

    

    }


}
