package idv.victoria.socialplatformt.service;

import idv.victoria.socialplatformt.MyUtils;
import idv.victoria.socialplatformt.model.Comment;
import idv.victoria.socialplatformt.model.Post;
import idv.victoria.socialplatformt.model.User;
import idv.victoria.socialplatformt.model.dao.CommentRepository;
import idv.victoria.socialplatformt.model.dao.PostRepository;
import idv.victoria.socialplatformt.model.dao.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    // Logger
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final CommentRepository commentRepository;

    @Autowired
    public ArticleService(PostRepository postRepository, UserRepository userRepository, CommentRepository commentRepository){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    /*
    * Save post
    *
    * @param Post
    * @return
    * */
    @Transactional
    public Post savePost(Post post) {
        try {
            return postRepository.saveOrUpdatePost(
                    post.getPostId(),
                    post.getUserId(),
                    post.getContent());

        } catch (Exception e) {
            // Handle exception
            logger.error("Error fetching all posts", e);
            throw new RuntimeException("Error fetching all posts", e);
        }
    }


    /*
    * Get all posts with formatted time and Username
    *
    * @return
    * */
    @Transactional
    public List<Post> getAllPosts() {
        try {
            // Get all posts
            List<Post> posts = postRepository.findAllPosts();

            // Iterate posts
            for (Post post : posts){

                // Set the userName
                post.setUserName(userRepository.findUserByPostId(post.getPostId()));

                // Set formatted timestamp
                post.setFormattedCreatedAt(MyUtils.formatTimestamp(post.getCreatedAt()));

            }
            return posts;

        } catch (Exception e) {
            // Handle exception
            logger.error("Error fetching all posts", e);
            throw new RuntimeException("Error fetching all posts", e);
        }
    }

    /*
    * Delete post and comments by postId
    *
    * @param postId
    * @return
    * */
    @Transactional
    public void deletePostById(Long id) {
        try {
            postRepository.deletePostAndComments(id);

        } catch (Exception e) {
            // Handle exception
            logger.error("Error deleting post and its comments by id: {}", id, e);
            throw new RuntimeException("Error deleting post and its comments by id: " + id, e);
        }
    }

    /*
    * Get username and bio by userId
    *
    * @param userId
    * @return
    * */
    @Transactional
    public User getUserInfo(Long userId){
        // Get user by userId
        User user = userRepository.findUserById(userId);

        // New a user as DTO
        User userDTO = new User();

        // Set data to the DTO
        userDTO.setUserName(user.getUserName());
        userDTO.setBiography(user.getBiography());

        return userDTO;
    }

}
