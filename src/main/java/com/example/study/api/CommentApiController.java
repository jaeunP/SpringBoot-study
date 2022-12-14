package com.example.study.api;

import com.example.study.annotation.RunningTime;
import com.example.study.service.CommentService;
import com.example.study.dto.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;

    // 댓글 목록 조회
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId) {
        //서비스에게 위임
        List<CommentDto> dtos=commentService.comments(articleId);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @GetMapping("/api/comment/{id}")
    public ResponseEntity<CommentDto> show(@PathVariable Long id){

        CommentDto showDto = commentService.show(id);

        return ResponseEntity.status(HttpStatus.OK).body(showDto);

    }

    //POST
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId, @RequestBody CommentDto dto){

        // 서비스에게 위임
        CommentDto createdDto = commentService.create(articleId,dto);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }
    //PATCH
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id,
                                             @RequestBody CommentDto dto){

        // 서비스에게 위임
        CommentDto updatedDto = commentService.update(id,dto);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    @RunningTime
    //DELETE
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id){

        // 서비스에게 위임
        CommentDto deletedDto = commentService.delete(id);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }

    @DeleteMapping("/api/article/comments/{articleId}")
    public ResponseEntity<String> deleteAll(@PathVariable Long articleId){

        // 서비스에게 위임
        String deleted = commentService.deleteAll(articleId);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(deleted);
    }
}
