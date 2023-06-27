package com.example.imu.blog.category;

import com.example.imu.blog.exception.ResourceNotFoundException;
import com.example.imu.blog.utils.CustomMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    private final CustomMapper mapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CustomMapper mapper, CategoryRepository categoryRepository) {
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = categoryRepository.save(mapToEntity(categoryDto));
        return mapToDto(category);
    }

    @Override
    public CategoryDto getCategory(Long id) {
        Category foungCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("category", "id", id));
        return mapToDto(foungCategory);
    }

    @Override
    public CategoryResponse getAllCategories(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sortDirection = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sortDirection);
        Page<Category> categories = categoryRepository.findAll(pageable);
        List<CategoryDto> listOfCategories  = categories.getContent().stream()
                .map(this::mapToDto).toList();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(listOfCategories);
        categoryResponse.setPageNo(categories.getNumber());
        categoryResponse.setPageSize(categories.getSize());
        categoryResponse.setLast(categories.isLast());
        categoryResponse.setNumberOfElements(categories.getNumberOfElements());
        categoryResponse.setTotalPages(categories.getTotalPages());
        return categoryResponse;
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        Category foungCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("category", "id", id));
        foungCategory.setName(categoryDto.getName());
        foungCategory.setDescription(categoryDto.getDescription());
        return mapToDto(categoryRepository.save(foungCategory));
    }

    @Override
    public void deleteCategory(Long id) {
        Category foungCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("category", "id", id));
        categoryRepository.delete(foungCategory);
    }

    private CategoryDto mapToDto(Category category) {
        return mapper.map(category, CategoryDto.class);
    }
    private Category mapToEntity(CategoryDto categoryDto) {
        return mapper.map(categoryDto, Category.class);
    }
}
