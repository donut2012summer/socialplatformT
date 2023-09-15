package idv.victoria.socialplatformt.model.dao;

import idv.victoria.socialplatformt.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {


}
