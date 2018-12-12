package Stream;/*
    name wang
    */

import sun.awt.geom.AreaOp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class TestStream{
    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        Collections.addAll(list,"zhangsan","lisi","wangwu");
        list.forEach(System.out::println);
        Stream<String> stream=list.stream();
        System.out.println(stream.count());
    }
}