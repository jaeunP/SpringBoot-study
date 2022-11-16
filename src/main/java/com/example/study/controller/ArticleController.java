package com.example.study.controller;

import com.example.study.dto.ArticleFrom;
import com.example.study.entity.Article;
import com.example.study.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    @Autowired  //Spring Boot가 미리 생성해놓은 객체를 가져다가 연결
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }
    @PostMapping("/articles/create")
    public String createArticle(ArticleFrom form) {
        System.out.println(form);

        // 1.Dto를 Entity로 변환
        Article article = form.toEntity();
        System.out.println(article.toString());

        // 2. Repository에게 Entity DB안에 저장하게 함
        Article saved = articleRepository.save(article);
        System.out.println(saved.toString());
        return "";
    }
}