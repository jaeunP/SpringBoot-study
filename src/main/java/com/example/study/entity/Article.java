package com.example.study.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Article {

    @Id //구분하기 위한 대표 값을 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB가 id를 자동 생성 어노테이션
    private Long id;

    @Column 
    private String title;

    //@Column은 생략 가능
    private String content;


}
/*  @Getter로 대체
    public Long getId() {
    return id;
    }
*/

/*  @NoArgsConstructor로 기본생성자 해결
    public Article() {}
*/

/*  @AllArgsConstructor로 대체
    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
*/



/*  @ToString로 대체
    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
}
*/

