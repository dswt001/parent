package com.dk.parent.loop;

import com.dk.parent.entity.Person;

import java.util.ArrayList;
import java.util.List;

public class InsideLoopAndOutsideLoop {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<Person> list = new ArrayList<>();
//        Person person = new Person();

        Person person;

        int i = 0;
        while (true) {
//            Person person = new Person();
            person = new Person();
            person.setName("张三" + i);
            person.setAge(i % 10);
            person.setGender((i & 1 )== 1);

            if (i >= 10000000) {
                break;
            }
            list.add(person);
            i++;
        }
        long endTime = System.currentTimeMillis();
//        for (Person person1 : list) {
//            System.out.println(person1.getName() + ";" + person1.getAge() + ";" + person1.isGender());
//        }
        System.out.println("耗费时间：" + (endTime - startTime));
    }

    public static void testOutsideLoop(){
        long startTime = System.currentTimeMillis();
        List<Person> list = new ArrayList<>();
//        Person person = new Person();

        Person person = null;

        int i = 0;
        for (; ;) {
            person = new Person();
            person.setName("张三" + i);
            person.setAge(i % 10);
            person.setGender((i & 1 )== 1);

            if (i >= 1000000) {
                break;
            }
            list.add(person);
            i++;
        }
        long endTime = System.currentTimeMillis();
       /* for (Person person1 : list) {
            System.out.println(person1.getName() + ";" + person1.getAge() + ";" + person1.isGender());
        }*/
        System.out.println("耗费时间：" + (endTime - startTime));

    }

}
