package com.example.study.repository;


import com.example.study.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
     //특정 게시글의 모든 댓글 조회
     @Query(value = "SELECT * " +
             "FROM comment " +
             "WHERE article_id = :articleId",
             nativeQuery = true)   //nativeQuery = true는 SQL, false는 JPQL
     List<Comment> findByArticleId(@Param("articleId") Long articleId);


}
