package com.example.study.dto;

import com.example.study.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
//폼데이터를 받아올 그릇
public class ArticleDto {

    private Long id;
    private String title;
    private String content;


    //Dto로 변환
    public static ArticleDto toDto(Article article) {
        return new ArticleDto(
                article.getId(),
                article.getTitle(),
                article.getContent()
        );
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