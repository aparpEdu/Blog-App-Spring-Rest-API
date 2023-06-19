package com.example.imu.blog.post;

import com.example.imu.blog.comment.CommentDto;
import lombok.Data;

import java.util.Set;

@Data
public class PostDto {
    private Long id;
    private String title;
    private String description;
    private String content;
    private Set<CommentDto> comments;
}
