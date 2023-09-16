package idv.victoria.socialplatformt.model.dao;

import idv.victoria.socialplatformt.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {


    @Procedure(name = "FindCommentsByPostId")
    List<Comment> findCommentByPostId(@Param("p_post_id") Long postId);


    @Procedure(name = "SaveOrUpdateComment")
    Comment saveOrUpdateComment(@Param("p_comment_id") Long commentId,
                                @Param("p_user_id") Long userId,
                                @Param("p_post_id") Long postId,
                                @Param("p_content") String content);



}
