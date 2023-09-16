package idv.victoria.socialplatformt.service;

import idv.victoria.socialplatformt.model.Post;
import idv.victoria.socialplatformt.model.User;
import idv.victoria.socialplatformt.model.dao.PostRepository;
import idv.victoria.socialplatformt.model.dao.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private PostRepository postRepository;

    private UserRepository userRepository;

    @Autowired
    public ArticleService(PostRepository postRepository, UserRepository userRepository){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Post savePost(Post post) {
        try {
            return postRepository.saveOrUpdatePost(
                    post.getPostId(),
                    post.getUserId(),
                    post.getContent());

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving post with id: " + post.getPostId(), e);
        }
    }

    @Transactional
    public List<Post> getAllPosts() {
        try {
            // Get all posts
            List<Post> posts = postRepository.findAllPosts();

            // Iterate posts
            for (Post post : posts){
                // Set the userName
                post.setUserName(userRepository.findUserByPostId(post.getPostId()));

            }
            return posts;

        } catch (Exception e) {
//            logger.error("Error fetching all posts", e);
            throw new RuntimeException("Error fetching all posts", e);
        }
    }

    @Transactional
    public Post getPostById(Long id) {
        try {
            return postRepository.findPostById(id);

        } catch (Exception e) {
//            logger.error("Error fetching post by id: {}", id, e);
            throw new RuntimeException("Error fetching post by id: " + id, e);
        }
    }

    @Transactional
    public void deletePostById(Long id) {
        try {
            postRepository.deletePostById(id);
        } catch (Exception e) {
//            logger.error("Error deleting post and its comments by id: {}", id, e);
            throw new RuntimeException("Error deleting post and its comments by id: " + id, e);
        }
    }


}
