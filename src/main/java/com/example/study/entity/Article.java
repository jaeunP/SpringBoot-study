package com.example.study.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Article {
    public Article() {/*Spring Data JPA 에서 Entity에 기본 생성자가 필요한 이유도 동적으로 객체 생성 시 Reflection API를 활용하기 때문*/}
    @Id //구분하기 위한 대표 값
    @GeneratedValue //자동 생성 어노테이션
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }



    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
