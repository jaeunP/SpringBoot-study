package com.example.study.api;


import com.example.study.service.ArticleService;
import com.example.study.dto.ArticleDto;
import com.example.study.entity.Article;
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

    //index
    @GetMapping("/api/articles")
    public List<ArticleDto> index() {

        return articleService.index();
    }

    //show
    @GetMapping("/api/articles/{id}")
    public ArticleDto show(@PathVariable Long id) {
        return articleService.show(id);
    }

    //POST
    @PostMapping("/api/articles")
    public ResponseEntity<ArticleDto> create(@RequestBody ArticleDto dto) {
        ArticleDto createdDto = articleService.create(dto);

        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }

    //PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<ArticleDto> update(@PathVariable Long id,
                                          @RequestBody ArticleDto dto) {

        //서비스에게 위임
        ArticleDto updated = articleService.update(id, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article deleted = articleService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
