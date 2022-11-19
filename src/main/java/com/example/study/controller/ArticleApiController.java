package com.example.study.controller;


import com.example.study.dto.ArticleForm;
import com.example.study.entity.Article;
import com.example.study.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {

    @Autowired
    private ArticleRepository articleRepository;

    //GET
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article index(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    //POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto) {
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }
    //PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@RequestBody ArticleForm dto,
                                         @PathVariable Long id) {
        //1. 수정용 Entity 생성
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());

        //2.대상 Entity를 조회
        Article target = articleRepository.findById(id).orElse(null);

        //3. 잘못된 요청 처리
        if(target == null || id != article.getId()){
            log.info("잘못된 요청! id: {}, article: {}" , id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }
        //4. 업데이트 및 정상 응답
        target.patch(article);
        Article updated = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
    //DELETE
}
