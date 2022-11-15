package com.example.study.dto;

//폼데이터를 받아올 그릇
public class ArticleFrom {

    private String title;
    private String content;

    public ArticleFrom(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "ArticleFrom{" + "title='" + title + '\'' + ", content='" + content + '\'' + '}';
    }
}
