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
    public List<Article> index() {
        return articleRepository.findAll();
    }

    //상세 조회
    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }


    public Article create(ArticleDto dto) {
        Article article = dto.toEntity();
        if(article.getId() != null) {
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleDto dto) {

        // 1: DTO -> 엔티티
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());

        // 2: 타겟 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 3: 잘못된 요청 처리
        if (target == null || id != article.getId()) {
            // 400, 잘못된 요청 응답!
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return null;
        }
        // 4: 업데이트
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }


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

    @Transactional
    public List<Article> createArticles(List<ArticleDto> dtos) {
        // dto 묶음을 entity 묶음으로 변환
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());
        // entity 묶음을 DB로 저장
        articleList.stream()
                .forEach(article -> articleRepository.save(article));
        // 강제 예외 발생
        articleRepository.findById(-1L).orElseThrow(
                () -> new IllegalArgumentException("결제 실패!")
        );
        // 결과값 반환
        return articleList;
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