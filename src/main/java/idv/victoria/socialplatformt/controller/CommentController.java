package idv.victoria.socialplatformt.controller;

import idv.victoria.socialplatformt.model.Comment;
import idv.victoria.socialplatformt.service.CommentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@RestController
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @GetMapping("/getCommentsByPostId/{postId}")
    public List<Comment> getCommentsByPostId(@PathVariable("postId") Long id) {
        List<Comment> comments = commentService.getCommentsByPostId(id);
        return comments;
    }


    @PostMapping("/addComment")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        // 驗證並清理用戶輸入
        String sanitizedText = HtmlUtils.htmlEscape(comment.getContent());
        comment.setContent(sanitizedText);

        Comment savedComment = commentService.saveComment(comment);
        return ResponseEntity.ok(savedComment);
    }

}
