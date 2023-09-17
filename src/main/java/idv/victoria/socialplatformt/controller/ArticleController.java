package idv.victoria.socialplatformt.controller;

import idv.victoria.socialplatformt.model.Post;
import idv.victoria.socialplatformt.model.User;
import idv.victoria.socialplatformt.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@RestController
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService){
        this.articleService = articleService;
    }

    /*
    * Sanitize and post article
    *
    * @param Post ( from request
    * @return
    * */
    @PostMapping("/postArticle")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {

        // Prevent XSS attack from the content
        String sanitizedContent = HtmlUtils.htmlEscape(post.getContent());
        post.setContent(sanitizedContent);

        // Save article
        Post createdPost = articleService.savePost(post);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }


    /*
    * Get all posts
    *
    * @return
    *
    * */
    @GetMapping("/getPosts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = articleService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    /*
    * Delete post by postId
    *
    * @param postId ( path variable
    *
    * */
    @GetMapping("/deletePost/{postId}")
    public void deletePost(@PathVariable Long postId) {
        articleService.deletePostById(postId);
    }


    /*
     * Get username and bio
     *
     * @param userId ( path variable
     * @return
     * */
    @GetMapping("/userInfo/{userId}")
    public ResponseEntity<User> getUserName(@PathVariable Long userId){
        User user = articleService.getUserInfo(userId);
        return ResponseEntity.ok(user);

    }


}
