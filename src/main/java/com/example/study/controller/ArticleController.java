package com.example.study.controller;

import com.example.study.dto.ArticleFrom;
import com.example.study.entity.Article;
import com.example.study.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j  // 로깅을 위한 어노테이션
public class ArticleController {

    @Autowired  //Spring Boot가 미리 생성해놓은 객체를 가져다가 연결
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleFrom form) {
        log.info(form.toString());
        //System.out.println(form.toString());기록에 남지도 않고 서버의 성능에 악영향 ->logging으로 대체

        // 1.Dto를 Entity로 변환
        Article article = form.toEntity();
        log.info(article.toString());
        //System.out.println(article.toString());

        // 2. Repository에게 Entity DB안에 저장하게 함
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        //System.out.println(saved.toString());
        return "";
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id=" + id);

        //1: ID로 데이터를 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null);//해당 아이디값이 없다면 null

        //2: 가져온 데이터를 모델에 등록
        model.addAttribute("article", articleEntity);

        //3: 보여줄 페이지를 설정
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {
        // 1: 모든 Article을 가져온다
        //서로 타입이 다르기 때문에 타입변환 필요, ArticleRepository에서 변환
        List<Article> articleEntityList = articleRepository.findAll();

        // 2: 가져온 Article 묶음을 뷰로 전달
        model.addAttribute("articleList",articleEntityList);

        // 3: 뷰 페이지를 설정
        return "articles/index";
    }
}