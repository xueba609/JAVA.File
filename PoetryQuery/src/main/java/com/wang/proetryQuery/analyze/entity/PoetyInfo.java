package com.wang.proetryQuery.analyze.entity;

import lombok.Data;

@Data
public class PoetyInfo {
    //标题
    private String title;
    //朝代
    private String dynasty;
    //作者
    private String author;
    //正文
    private String content;
}
