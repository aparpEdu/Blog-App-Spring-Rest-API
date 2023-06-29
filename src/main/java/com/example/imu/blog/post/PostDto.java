package com.example.imu.blog.post;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;



@Schema(description = "PostDto Model Information")
@Data
public class PostDto {

    private Long id;
    @Schema(description = "Blog Post Title")
    @NotBlank(message = "Title cannot be left empty")
    @Size(min = 2, max = 20, message = "Title should be in the range of 2-20 characters")
    private String title;
    @Schema(description = "Blog Post Description")
    @Size(max = 20, message = "description should not be larger than 20 characters")
    private String description;
    @Schema(description = "Blog Post Content")
    @Size(max = 200, message = "content should not be larger than 200 characters")
    private String content;
    @Schema(description = "Blog Post Category")
    private Long categoryId;
}
