package com.example.imu.blog.category;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto getCategory(Long id);
    CategoryResponse getAllCategories(int pageNo, int pageSize, String sortBy, String sortDir);
    CategoryDto updateCategory(Long id, CategoryDto categoryDto);
    void deleteCategory(Long id);
}
