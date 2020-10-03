package com.dk.parent.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class NewClass {

    public static void main(String[] args) {

        try {
            Class helloClass = Class.forName("com.dk.parent.reflect.NewClass.Hello");


            Constructor constructor = helloClass.getConstructor();
            Object o1 = constructor.newInstance();
            System.out.println(o1);

            Object o = helloClass.newInstance();
            Class<? extends Class[]> aClass = helloClass.getDeclaredClasses().getClass();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }


        try {

            Constructor<?> cs = Hello.class.getDeclaredConstructor();//ClassB.class.getConstructor();//
            cs.setAccessible(true);
            Hello cls2 = (Hello) cs.newInstance();
            cls2.print();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    static class Hello {

        public static void print() {
            System.out.println("hello world!");
        }
    }
}
