package com.example.imu.blog.post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
    PostDto getPostById(Long id);
    PostDto updatePost(Long id, PostDto postDto);
    PostResponse getPostsByCategory(Long categoryId,int pageNo, int pageSize, String sortBy, String sortDir);
    void deletePost(Long id);
}
