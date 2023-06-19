package com.example.imu.blog.comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    CommentResponse getAllComments(Long postId, int pageNumber, int pageSize, String sortBy, String sortDirection);
    CommentDto createComment(Long postId, CommentDto commentDto);
    Optional<CommentDto> getCommentById(Long postId, Long commentId);
    CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto);
    void deleteComment(Long postId, Long commentId);

}
