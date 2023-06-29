package com.example.imu.blog.category;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "CategoryDto Model Information")
public class CategoryDto {
    private Long id;
    @Schema(description = "Post Category name")
    @Size(min = 2, message = "category name should be at least 2 symbols long")
    @NotBlank(message = "category name cannot be left blank")
    private String name;
    @Schema(description = "Post Category Description")
    @Size(max = 200, message = "Category description should not be longer than 200 symbols")
    private String description;
}
