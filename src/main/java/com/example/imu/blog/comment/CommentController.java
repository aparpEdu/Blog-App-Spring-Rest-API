package com.example.imu.blog.comment;

import com.example.imu.blog.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/posts")
public class CommentController {
    private final CommentServiceImpl commentService;

    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @PostMapping("{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable Long postId, @Valid @RequestBody CommentDto comment){
        return new ResponseEntity<>(commentService.createComment(postId, comment), HttpStatus.CREATED);
    }

    @GetMapping("{postId}/comments")
    public ResponseEntity<CommentResponse> getAllComments(@PathVariable Long postId,
                                                          @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNo,
                                                          @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
                                                          @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy,
                                                          @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION) String sortDir){
        return ResponseEntity.ok(commentService.getAllComments(postId,pageNo, pageSize, sortBy, sortDir));
    }

    @GetMapping("{postId}/comments/{commentId}")
    public ResponseEntity<Optional<CommentDto>> getComment(@PathVariable Long postId, @PathVariable Long commentId){
        return ResponseEntity.ok(commentService.getCommentById(postId, commentId));
    }

    @PutMapping("{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long postId, @PathVariable Long commentId, @Valid @RequestBody CommentDto commentDto){
        return ResponseEntity.ok(commentService.updateComment(postId, commentId, commentDto));
    }

    @DeleteMapping("{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long postId, @PathVariable Long commentId){
        commentService.deleteComment(postId, commentId);
        return ResponseEntity.ok("Comment successfully deleted");
    }
}
