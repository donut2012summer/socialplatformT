package idv.victoria.socialplatformt.model.dao;

import idv.victoria.socialplatformt.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    /*
    * Procedure SaveOrUpdatePost
    *
    * @param postId, userId, content
    * @return
    * */
    @Procedure(name = "SaveOrUpdatePost")
    Post saveOrUpdatePost(@Param("p_post_id") Long postId,
                          @Param("p_user_id") Long userId,
                          @Param("p_content") String content
    );


    /*
    * Procedure GetAllPosts
    *
    * @return
    * */
    @Procedure(name = "GetAllPosts")
    List<Post> findAllPosts();


    /*
    * Procedure DeletePostAndComments
    *
    * @param postId
    *
    * */
    @Procedure(name = "DeletePostAndComments")
    void deletePostAndComments(@Param("p_post_id") Long postId);


}
