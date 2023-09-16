package idv.victoria.socialplatformt.controller;

import idv.victoria.socialplatformt.model.Post;
import idv.victoria.socialplatformt.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@RestController
public class ArticleController {
    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService){
        this.articleService = articleService;
    }

    @PostMapping("/postArticle")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {

        System.out.println(post.getContent());
        // 驗證並清理用戶輸入
//        String sanitizedContent = HtmlUtils.htmlEscape(post.getContent());
//        post.setContent(sanitizedContent);
//
//        logger.info("Received POST request for creating a post with content: {}", sanitizedContent);
//
        Post createdPost = articleService.savePost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    @GetMapping("/getPosts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = articleService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/deletePost/{postId}")
    public void deletePost(@PathVariable Long postId) {
        articleService.deletePostById(postId);
    }




}
