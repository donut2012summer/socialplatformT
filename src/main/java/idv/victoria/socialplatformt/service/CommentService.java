package idv.victoria.socialplatformt.service;

import idv.victoria.socialplatformt.model.Comment;
import idv.victoria.socialplatformt.model.dao.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }


    @Transactional
    public List<Comment> getCommentsByPostId(Long postId) {
        try {
            List<Comment> comments = commentRepository.findCommentByPostId(postId);
            return comments;

        } catch (Exception e) {
//            logger.error("Error fetching comments for post id: {}", postId, e);
            throw new RuntimeException("Error fetching comments for post id: " + postId, e);
        }
    }

    @Transactional
    public Comment saveComment(Comment comment) {
        try {
            return commentRepository.saveOrUpdateComment(comment.getCommentId(), comment.getUserId(), comment.getPostId(), comment.getContent());
        } catch (Exception e) {
//            logger.error("Error saving comment with id: {}", comment.getCommentId(), e);
            throw new RuntimeException("Error saving comment with id: " + comment.getCommentId(), e);
        }
    }

}
