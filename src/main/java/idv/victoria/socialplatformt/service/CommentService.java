package idv.victoria.socialplatformt.service;

import idv.victoria.socialplatformt.MyUtils;
import idv.victoria.socialplatformt.model.Comment;
import idv.victoria.socialplatformt.model.User;
import idv.victoria.socialplatformt.model.dao.CommentRepository;
import idv.victoria.socialplatformt.model.dao.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    // Logger
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, UserRepository userRepository){
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    /*
    * Get comments by postId
    *
    * @param postId
    * @return
    * */
    @Transactional
    public List<Comment> getCommentsByPostId(Long postId) {
        try {
            // Get comment list
            List<Comment> comments = commentRepository.findCommentByPostId(postId);

            // Iterate comments
            for(Comment comment : comments){
                // Get user
                User user = userRepository.findUserByCommentId(comment.getCommentId());

                // Set username
                comment.setUserName(user.getUserName());

                // Formatted createdAt of comment
                String formattedTimestamp = MyUtils.formatTimestamp(comment.getCreatedAt());

                // Set timestamp
                comment.setFormattedCreatedAt(formattedTimestamp);
            }

            return comments;

        } catch (Exception e) {
            // Handle exception
            logger.error("Error fetching comments for post id: {}", postId, e);
            throw new RuntimeException("Error fetching comments for post id: " + postId, e);

        }
    }

    /*
    * Save comments
    *
    * @param comment
    * @return
    * */
    @Transactional
    public Comment saveComment(Comment comment) {
        try {
            return commentRepository.saveOrUpdateComment
                    (comment.getCommentId(), comment.getUserId(),
                            comment.getPostId(), comment.getContent());

        } catch (Exception e) {
            // Handle Exception
            logger.error("Error saving comment with id: {}", comment.getCommentId(), e);
            throw new RuntimeException("Error saving comment with id: " + comment.getCommentId(), e);

        }
    }

}
