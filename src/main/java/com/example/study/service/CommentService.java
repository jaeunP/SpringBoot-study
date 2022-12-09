package com.example.study.service;

import com.example.study.dto.CommentDto;

import com.example.study.entity.Article;
import com.example.study.entity.Comment;
import com.example.study.repository.ArticleRepository;
import com.example.study.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    //특정 ID의 댓글들 조회
    public List<CommentDto> comments(Long articleId) {
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }
    public CommentDto show(Long id){
        CommentDto detail = CommentDto.createCommentDto(commentRepository.findById(id).orElse(null));
        return detail;
    }


    //생성
    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        //게시글 조회 및 예외 발생
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패, 대상 게시글이 없습니다"));

        //댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);

        //댓글 엔티티를 DB로 저장
        Comment created = commentRepository.save(comment);

        // DTO로 변경하여 반환
        return CommentDto.createCommentDto(created);
    }
    
    //수정
    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        //댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패, 대상 댓글이 없습니다"));

        //댓글을 수정
        target.patch(dto);

        //DB로 갱신
        Comment updated = commentRepository.save(target);
        //댓글 Entity를 DTO로 변환
        return CommentDto.createCommentDto(updated);
    }
    
    //삭제
    @Transactional
    public CommentDto delete(Long id) {
        //댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패, 댓글이 없습니다"));

        //댓글 DB에서 삭제
        commentRepository.delete(target);

        //삭제 댓글을 DTO로 반환
        return CommentDto.createCommentDto(target);
    }
}
//          //댓글 목록 조회
//          List<Comment> comments = commentRepository.findByArticleId(articleId);
//          //Entity -> DTO로 변환
//          List<CommentDto> dtos = new ArrayList<CommentDto>();
//          for (int i = 0; i < comments.size(); i++){
//              Comment comment =comments.get(i);
//              CommentDto dto = CommentDto.createCommentDto(comment);
//              dtos.add(dto);
//          }
//        //반환
//        return dtos;
