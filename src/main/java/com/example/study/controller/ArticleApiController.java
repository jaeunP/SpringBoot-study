package com.example.study.controller;


import com.example.study.Service.ArticleService;
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

    @Autowired  //DI, 생성 객체를 가져와 연결
    private ArticleService articleService;

    //GET
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleService.index();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleService.show(id);
    }

    //POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {
        Article created = articleService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

//    //PATCH
//    @PatchMapping("/api/articles/{id}")
//    public ResponseEntity<Article> update(@RequestBody ArticleForm dto,
//                                          @PathVariable Long id) {
//        //1. 수정용 Entity 생성
//        Article article = dto.toEntity();
//        log.info("id: {}, article: {}", id, article.toString());
//
//        //2.대상 Entity를 조회
//        Article target = articleRepository.findById(id).orElse(null);
//
//        //3. 잘못된 요청 처리
//        if (target == null || id != article.getId()) {
//            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//
//        }
//        //4. 업데이트 및 정상 응답
//        target.patch(article);  //수
//        Article updated = articleRepository.save(target);
//        return ResponseEntity.status(HttpStatus.OK).body(updated);
//    }

//    //DELETE
//    @DeleteMapping("/api/articles/{id}")
//    public ResponseEntity<Article> delete(@PathVariable Long id) {
//        //대상 찾기
//        Article target = articleRepository.findById(id).orElse(null);
//
//        //잘못된 요청 처리
//        if (target == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        //대상 삭제
//        articleRepository.delete(target);
//
//        //데이터 반환
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
}
