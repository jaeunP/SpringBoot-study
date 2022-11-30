package com.example.study.dto;

import com.example.study.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
//폼데이터를 받아올 그릇
public class ArticleDto {

    private Long id;
    private String title;
    private String content;

    //Entity로 반환
    public Article toEntity() {
        return new Article(id,title,content);
    }
}
/*  @AllArgsConstructor로 대체
    public ArticleFrom(String title, String content) {
        this.title = title;
        this.content = content;
    }*/

    /*  @ToString으로 대체
        @Override
        public String toString() {
            return "ArticleFrom{" + "title='" + title + '\'' + ", content='" + content + '\'' + '}';
        }*/