package com.example.study.service;

import com.example.study.dto.ArticleDto;
import com.example.study.entity.Article;
import com.example.study.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service    //서비스 선언 ( 서비스 객체를 스프링부트에 생성)
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    //전체 목록 조회
    public List<ArticleDto> index() {
        List<Article> articleList = articleRepository.findAll();
        return articleList
                .stream()
                .map(article -> ArticleDto.toDto(article))
                .collect(Collectors.toList());
    }

    //상세 조회
    public ArticleDto show(Long id) {
        ArticleDto detail = ArticleDto.toDto( articleRepository.findById(id).orElse(null));
        return detail;
    }

    //생성
    @Transactional
    public ArticleDto create(ArticleDto dto) {
        //DTO -> Entity
        Article article = Article.toEntity(dto);
        if(article.getId() != null) {
            return null;
        }
        Article created = articleRepository.save(article);

        return ArticleDto.toDto(created);
    }
    
    //수정
    @Transactional
    public ArticleDto update(Long id, ArticleDto dto) {
        //게시물 조회
        Article target = articleRepository.findById(id).orElse(null);

        //게시물 수정
        target.patch(dto);

        //DB로 갱신
        Article updated = articleRepository.save(target);

        //Dto로 변환
        return ArticleDto.toDto(updated);
    }

    //삭제
    @Transactional
    public Article delete(Long id) {
        // 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        // 잘못된 요청 처리
        if (target == null) {
            return null;
        }
        // 대상 삭제
        articleRepository.delete(target);
        return target;
    }
}
/*  dto를 Entity로 변환코드 for문으로 표현
 *List<Aritcle> articleList = new ArrayList<>();
 * ArticleForm dto = dtos.get(i);
 * Article entity = dto.toEntity();
 * articleList.add(entity);
 */
/*  entity 묶음을 DB로 저장, 위에 코드 for문으로 표현
 * for(int i =0; i< articleList.size(); i++){
 * Article article = articleList.get(i);
 * articleRepository.save(article);
 * }
 */