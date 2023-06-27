package com.example.imu.blog.post;

import com.example.imu.blog.category.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;



@RepositoryRestResource
public interface PostRepository extends JpaRepository<Post,Long> {
    Page<Post> findAllByCategory(Category category, Pageable pageable);
}
