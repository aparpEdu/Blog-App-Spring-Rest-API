package com.example.imu.blog.category;

import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse {
    private List<CategoryDto> content;
    private int pageNo;
    private int pageSize;
    private long numberOfElements;
    private int totalPages;
    private boolean isLast;
}
