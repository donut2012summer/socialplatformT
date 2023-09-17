package idv.victoria.socialplatformt.controller;

import idv.victoria.socialplatformt.model.Comment;
import idv.victoria.socialplatformt.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@RestController
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }


    /*
    * Get comment by postId
    *
    * @param postId ( Path variable
    * @return
    * */
    @GetMapping("/getCommentsByPostId/{postId}")
    public List<Comment> getCommentsByPostId(@PathVariable("postId") Long id) {
        return commentService.getCommentsByPostId(id);
    }


    /*
    * Sanitized and add comments
    *
    * @param comment
    * @return
    * */
    @PostMapping("/addComment")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        // Prevent XSS attack from the content
        String sanitizedText = HtmlUtils.htmlEscape(comment.getContent());
        comment.setContent(sanitizedText);

        // Save comment
        Comment savedComment = commentService.saveComment(comment);

        return ResponseEntity.ok(savedComment);
    }

}
