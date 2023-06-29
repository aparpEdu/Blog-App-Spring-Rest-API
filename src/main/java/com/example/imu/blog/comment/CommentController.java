package com.example.imu.blog.comment;

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

import java.util.Optional;

@RestController
@RequestMapping("api/posts")
@Tag(name = "CRUD REST APIs for Comment Resource")
public class CommentController {
    private final CommentServiceImpl commentService;

    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }
    @Operation(
            summary = "Create Comment REST API",
            description = "Create Comment REST API is used to save comments into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http status 201 CREATED"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("{postId}/comments")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommentDto> createComment(@PathVariable Long postId, @Valid @RequestBody CommentDto comment){
        return new ResponseEntity<>(commentService.createComment(postId, comment), HttpStatus.CREATED);
    }
    @Operation(
            summary = "Get All Comments by Post Id REST API",
            description = "Get All Comments by Post Id REST API responds with all comments in a post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS"
    )
    @GetMapping("{postId}/comments")
    public ResponseEntity<CommentResponse> getAllComments(@PathVariable Long postId,
                                                          @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNo,
                                                          @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
                                                          @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy,
                                                          @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION) String sortDir){
        return ResponseEntity.ok(commentService.getAllComments(postId,pageNo, pageSize, sortBy, sortDir));
    }
    @Operation(
            summary = "Get Comment by Post Id And Comment Id REST API",
            description = "Get Comment by Post Id And Comment Id REST API is used to get a single comment " +
                    "in a specific post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS"
    )
    @GetMapping("{postId}/comments/{commentId}")
    public ResponseEntity<Optional<CommentDto>> getComment(@PathVariable Long postId, @PathVariable Long commentId){
        return ResponseEntity.ok(commentService.getCommentById(postId, commentId));
    }
    @Operation(
            summary = "Update Comment by Post Id and Comment Id REST API",
            description = "Update Comment by Post Id and Comment Id updates a single comment in a post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("{postId}/comments/{commentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long postId, @PathVariable Long commentId, @Valid @RequestBody CommentDto commentDto){
        return ResponseEntity.ok(commentService.updateComment(postId, commentId, commentDto));
    }
    @Operation(
            summary = "Delete Comment by Post Id and Comment Id REST API",
            description = "Delete Comment by Post Id and Comment Id REST API deletes a single comment in a post from " +
                    "the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("{postId}/comments/{commentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteComment(@PathVariable Long postId, @PathVariable Long commentId){
        commentService.deleteComment(postId, commentId);
        return ResponseEntity.ok("Comment successfully deleted");
    }
}
