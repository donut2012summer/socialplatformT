package idv.victoria.socialplatformt.model.dao;

import idv.victoria.socialplatformt.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {


}
