package com.example.study.Service;

import com.example.study.dto.CommentDto;

import com.example.study.repository.ArticleRepository;
import com.example.study.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;


    public List<CommentDto> comments(Long articleId) {
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());


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
