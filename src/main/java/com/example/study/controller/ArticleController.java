package com.example.study.controller;

import com.example.study.service.CommentService;
import com.example.study.entity.Article;
import com.example.study.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j  // 로깅을 위한 어노테이션
public class ArticleController {

    @Autowired  //Spring Boot가 미리 생성해놓은 객체를 가져다가 연결
    private ArticleRepository articleRepository;

    @Autowired
    private CommentService commentService;

    //index
    @RequestMapping("/articles")
    public String index() {return "articles/index.html";}

    //new
    @RequestMapping("/articles/new")
    public String newArticle() {
        return "articles/new.html";
    }


    //show
    @RequestMapping("/articles/{id}")
    public String show() {return "articles/show.html";}


    //edit
    @RequestMapping("/articles/edit/{id}")
    public String edit() {
        return "articles/edit.html";
    }

    //delete
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제 요청");

        //1: 삭제 대상을 가져옴
        Article articleEntity =articleRepository.findById(id).orElse(null);
        log.info(articleEntity.toString());

        //2: 대상을 삭제한다
        if(articleEntity != null){
            articleRepository.delete(articleEntity);
            rttr.addFlashAttribute("msg","삭제가 완료되었습니다");
        }
        
        //3: 결과 페이지로 리다이렉트

        return "redirect:/articles";
    }



}