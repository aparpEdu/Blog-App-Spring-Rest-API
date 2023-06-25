package com.example.imu.blog.comment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 20, message = "Name should be at least 2 characters and max 20 characters")
    private String name;
    @NotBlank(message = "Comment body cannot be left empty")
    @Size(min = 2, max = 100, message = "Comment body should be at least 2 characters and 100 max")
    private String body;
    @NotBlank(message = "Email cannot be left empty")
    @Email(message = "Input should be an email")
    private String email;
}
