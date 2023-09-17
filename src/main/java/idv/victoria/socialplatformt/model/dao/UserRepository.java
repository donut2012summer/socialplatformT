package idv.victoria.socialplatformt.model.dao;

import idv.victoria.socialplatformt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /*
    * Procedure HasMobileExists
    *
    * @param mobile
    * @return
    * */
    @Procedure(name = "HasMobileExists")
    Integer hasMobileExists(@Param("p_mobile") String mobile);

    /*
    * Procedure SaveOrUpdateUser
    *
    * @param userId, userName, mobile, email, password, biography
    * */
    @Procedure(name = "SaveOrUpdateUser")
    void saveOrUpdateUser(@Param("p_user_id") Long userId,
                          @Param("p_user_name") String userName,
                          @Param("p_mobile") String mobile,
                          @Param("p_email") String email,
                          @Param("p_password") String password,
                          @Param("p_biography") String biography);



    /*
    * Procedure FindUserByMobile
    *
    * @param mobile
    * @return
    * */
    @Procedure(name = "FindUserByMobile")
    User findUserByMobile(@Param("p_mobile") String mobile);


    /*
     * Procedure FindUserByPostId
     *
     * @param commentId
     * @return
     * */
    @Procedure(name = "FindUserByPostId")
    String findUserByPostId(@Param("p_post_id") Long postId);


    /*
    * Procedure FindUserByCommentId
    *
    * @param commentId
    * @return
    * */
    @Procedure(name = "FindUserByCommentId")
    User findUserByCommentId(@Param("p_comment_id") Long commentId);


    /*
    * Procedure FindUserById
    *
    * @param userId
    * @return
    * */
    @Procedure(name = "FindUserById")
    User findUserById(@Param("p_user_id") Long id);


}
