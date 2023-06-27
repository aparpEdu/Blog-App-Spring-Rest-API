package com.example.imu.blog.comment;

import com.example.imu.blog.exception.BlogAPIException;
import com.example.imu.blog.exception.ResourceNotFoundException;
//import com.example.imu.blog.mapper.CustomMapper;
import com.example.imu.blog.post.Post;
import com.example.imu.blog.post.PostRepository;
import com.example.imu.blog.utils.CustomMapper;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    private final CustomMapper mapper;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentServiceImpl(CustomMapper mapper, CommentRepository commentRepository, PostRepository postRepository) {
        this.mapper = mapper;
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentResponse getAllComments(Long postId, int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sortDirection = sortBy.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sortDirection);

        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
        Page<Comment> comments = commentRepository.findAllByPost(post, pageable);
        List<CommentDto> content =  comments.getContent().stream().map(this::mapToDto).toList();
        return new CommentResponse(
                content,
                comments.getNumber(),
                comments.getSize(),
                comments.getTotalElements(),
                comments.getTotalPages(),
                comments.isLast()
        );
    }

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
        Comment commentData = mapper.map(commentDto, Comment.class);
        commentData.setPost(post);
        Comment comment = commentRepository.save(commentData);
        return mapToDto(comment);
    }

    @Override
    public Optional<CommentDto> getCommentById(Long postId, Long commentId) {
        Comment comment = findRelation(postId, commentId);
        findRelation(postId, commentId);
        return Optional.ofNullable(mapToDto(comment));
    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto) {
        Comment comment = findRelation(postId, commentId);
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        Comment commentToUpdate = commentRepository.save(comment);
        return mapToDto(commentToUpdate);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        Comment comment = findRelation(postId, commentId);
        commentRepository.delete(comment);
    }

    private CommentDto mapToDto(Comment comment) {
        return mapper.map(comment, CommentDto.class);
    }
    private Comment findRelation(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment", "id", commentId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment with id:"+commentId+" does not belong to post " +
                    "with id:"+postId);
        }
        return comment;
    }
}
