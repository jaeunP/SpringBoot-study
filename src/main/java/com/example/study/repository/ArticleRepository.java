package com.example.study.repository;

import com.example.study.entity.Article;
import org.springframework.data.repository.CrudRepository;

//CRUD기능 사용 <관리대상 Entity, 대표값 타입>
public interface ArticleRepository extends CrudRepository<Article,Long> {

}
