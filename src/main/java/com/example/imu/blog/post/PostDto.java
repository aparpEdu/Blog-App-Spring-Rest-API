package com.example.imu.blog.post;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;




@Data
public class PostDto {
    private Long id;
    @NotBlank(message = "Title cannot be left empty")
    @Size(min = 2, max = 20, message = "Title should be in the range of 2-20 characters")
    private String title;
    @Size(max = 20, message = "description should not be larger than 20 characters")
    private String description;
    @Size(max = 200, message = "content should not be larger than 200 characters")
    private String content;
    private Long categoryId;
}
