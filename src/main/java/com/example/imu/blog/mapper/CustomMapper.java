//package com.example.imu.blog.mapper;
//
//import com.example.imu.blog.comment.Comment;
//import com.example.imu.blog.comment.CommentDto;
//import com.example.imu.blog.post.Post;
//import com.example.imu.blog.post.PostDto;
//import org.modelmapper.Converter;
//import org.modelmapper.ModelMapper;
//import org.modelmapper.PropertyMap;
//import org.springframework.stereotype.Component;
//
//import java.util.HashSet;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Component
//public class CustomMapper extends ModelMapper {
//
//    public CustomMapper() {
//        configureMappings();
//    }
//
//    private void configureMappings() {
//        // Mapping configuration for Post to PostDto
//        addMappings(new PropertyMap<Post, PostDto>() {
//            @Override
//            protected void configure() {
//                using(mapCommentToDto).map(source.getComments(), destination.getComments());
//            }
//        });
//    }
//
//    private Converter<Set<Comment>, Set<CommentDto>> mapCommentToDto = context -> {
//        Set<Comment> source = context.getSource();
//        Set<CommentDto> destination = new HashSet<>();
//        for (Comment comment : source) {
//            CommentDto commentDto = new CommentDto();
//            commentDto.setId(comment.getId());
//            commentDto.setName(comment.getName());
//            commentDto.setEmail(comment.getEmail());
//            commentDto.setBody(comment.getBody());
//            destination.add(commentDto);
//        }
//        return destination;
//    };
//}
