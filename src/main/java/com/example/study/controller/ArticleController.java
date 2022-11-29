package com.example.study.controller;

import com.example.study.service.CommentService;
import com.example.study.dto.ArticleForm;
import com.example.study.dto.CommentDto;
import com.example.study.entity.Article;
import com.example.study.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j  // 로깅을 위한 어노테이션
public class ArticleController {

    @Autowired  //Spring Boot가 미리 생성해놓은 객체를 가져다가 연결
    private ArticleRepository articleRepository;

    @Autowired
    private CommentService commentService;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
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
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id=" + id);

        //1: ID로 데이터를 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null);//해당 아이디값이 없다면 null
        List< CommentDto> commentDtos = commentService.comments(id);

        //2: 가져온 데이터를 모델에 등록
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos", commentDtos);

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

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Article articleEntity =articleRepository.findById(id).orElse(null);

        model.addAttribute("article",articleEntity);

        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());

        //1: DTO를 Entity로 변환
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());

        //2: Entity를 DB로 저장
        //DB에서 기존 데이터를 가져옴
        Article target =articleRepository.findById(articleEntity.getId()).orElse(null);

        if(target != null){
            articleRepository.save(articleEntity); // Entity가 DB로 갱신
        }
        //3: 페이지 뷰
        return "redirect:/articles/" + articleEntity.getId();
    }

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