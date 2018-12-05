package Test1;/*
    name wang;
    */
import java.io.*;
import java.util.zip.CheckedInputStream;

 class ClassA {

        public ClassA(){
            System.out.println("1.Hello A!父类构造方法");
        }
        {
            System.out.println("2.i'm A class.父类非静态代码块");
        }
        static{
            System.out.println("3.static A 父类静态代码块");
        }
    }
    public class Test11 extends ClassA {
        public Test11(){
            System.out.println("4.Hello B! 构造方法");
        }
        {
            System.out.println("5.i'm B class.非静态代码块");
        }
        static{
            System.out.println("6.static B 静态代码块");
        }
        public static void main(String[] args) {
            System.out.println("7.---start---");
            new Test11();
            new ClassA();
            System.out.println("8.---end---");
        }
    }

