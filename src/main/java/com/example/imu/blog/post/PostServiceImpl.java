package com.example.imu.blog.post;

import com.example.imu.blog.category.Category;
import com.example.imu.blog.category.CategoryRepository;
import com.example.imu.blog.exception.ResourceNotFoundException;
import com.example.imu.blog.utils.CustomMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final CustomMapper mapper;

    public PostServiceImpl(PostRepository postRepository, CategoryRepository categoryRepository, CustomMapper mapper) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Category foungCategory = categoryRepository.findById(postDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("category", "id", postDto.getCategoryId()));
        Post post = mapper.map(postDto, Post.class);
        post.setCategory(foungCategory);
        Post postToSave = postRepository.save(post);
        return mapper.map(postToSave, PostDto.class);
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sortDirection = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sortDirection);
        Page<Post> posts = postRepository.findAll(pageable);
        return getPostResponse(posts);
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(Long id, PostDto postDto) {
           Category foungCategory = categoryRepository.findById(postDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("category", "id", postDto.getCategoryId()));
           Post foundPost = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
           foundPost.setContent(postDto.getContent());
           foundPost.setDescription(postDto.getDescription());
           foundPost.setTitle(postDto.getTitle());
           foundPost.setCategory(foungCategory);
           Post postToSave = postRepository.save(foundPost);
           return mapToDto(postToSave);
    }

    @Override
    public PostResponse getPostsByCategory(Long categoryId, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sortDirection = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sortDirection);
        Category foungCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("category", "id", categoryId));
        Page<Post> posts = postRepository.findAllByCategory(foungCategory, pageable);
        return getPostResponse(posts);
    }

    private PostResponse getPostResponse(Page<Post> posts) {
        List<Post> listOfPosts = posts.getContent();
        List<PostDto> content = listOfPosts.stream().map(this::mapToDto).toList();
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setLast(posts.isLast());
        postResponse.setTotalPages(posts.getTotalPages());
        return postResponse;
    }

    @Override
    public void deletePost(Long id) {
        Post postForDeletion = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(postForDeletion);
    }

    private PostDto mapToDto(Post post) {
        return mapper.map(post, PostDto.class);
    }
}
