package com.wang.ll;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ObjectCopy {
    public static void copy(Object source, Object target) {
        Class str = source.getClass();
        Class ste = target.getClass();
        Field[] srcfile = str.getDeclaredFields();
        Field[] stefile = ste.getDeclaredFields();
        for (Field c : srcfile) {
            c.setAccessible(true);
            String setName = "set" + c.getName().substring(0, 1).toUpperCase() +
                    (c.getName().substring(1).length() > 1 ? c.getName().substring(1) : "");
            for (Field cf : stefile) {
                c.setAccessible(true);
                if (c.getName() == cf.getName() && c.getType() == cf.getType()) {
                    try {
                        Method method = ste.getMethod(setName, c.getType());
                        try {
                            method.invoke(target, c.get(source));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                } else {
                   continue;
                }
            }
        }
    }
        public static void main (String[]args){
            Person person = new Person(20, "王红");
            Student student = new Student();
            copy(person, student);
            System.out.println(person.toString());
            System.out.println(student.toString());
        }
    }

    class Person {
        private int age;
        private  String name;
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        Person(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    ", id=" + id +
                    '}';
        }
    }

    class Student {
        private int age;
        private String name;
        private String look;

        public String getLook() {
            return look;
        }

        public void setLook(String look) {
            this.look = look;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    ", look='" + look + '\'' +
                    '}';
        }
    }
