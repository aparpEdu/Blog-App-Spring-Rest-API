package com.example.imu.blog.comment;

import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private String name;
    private String body;
    private String email;
}
