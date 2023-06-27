package com.example.imu.blog.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryDto {
    private Long id;
    @Size(min = 2, message = "category name should be at least 2 symbols long")
    @NotBlank(message = "category name cannot be left blank")
    private String name;
    @Size(max = 200, message = "Category description should not be longer than 200 symbols")
    private String description;
}
