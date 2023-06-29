package com.example.imu.blog.category;

import com.example.imu.blog.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "CRUD REST APIs for Category Resource")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }
    @Operation(
            summary = "Create Category REST API",
            description = "Create Category REST API is used to save a category inside the database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http status 201 CREATED"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        return new  ResponseEntity<>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
    }
    @Operation(
            summary = "Get Category by Id REST API",
            description = "Get Category by Id REST API is used to get a single category from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS"
    )
    @GetMapping("{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.getCategory(id));
    }
    @Operation(
            summary = "Get All Categories REST API",
            description = "Get All Categories REST API is used to get all  categories from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS"
    )
    @GetMapping("")
    public ResponseEntity<CategoryResponse> getAllCategories(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION) String sortDir){
        return ResponseEntity.ok(categoryService.getAllCategories(pageNo, pageSize, sortBy, sortDir));
    }
    @Operation(
            summary = "Update Category by Id REST API",
            description = "Update Category by Id REST API is used to update a single category from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryDto categoryDto){
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryDto));
    }
    @Operation(
            summary = "Delete Category by Id REST API",
            description = "Delete Category by Id REST API is used to delete a single category from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category was deleted successfully");
    }
}
