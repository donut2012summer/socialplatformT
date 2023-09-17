package idv.victoria.socialplatformt.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
// Map to Procedures
@NamedStoredProcedureQueries({

        // Map to Procedure
        @NamedStoredProcedureQuery(
                name = "SaveOrUpdatePost",
                procedureName = "SaveOrUpdatePost",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.INOUT, name = "p_post_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_user_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_content", type = String.class),
                },
                resultClasses = {Post.class}
        ),

        @NamedStoredProcedureQuery(
                name = "GetAllPosts",
                procedureName = "GetAllPosts",
                resultClasses = {Post.class}
        ),

        @NamedStoredProcedureQuery(
                name = "GetAllPostsAndComments()",
                procedureName = "GetAllPostsAndComments()",
                resultClasses = {Post.class}
        ),

        @NamedStoredProcedureQuery(
                name = "FindPostById",
                procedureName = "FindPostById",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_post_id", type = Long.class)
                },
                resultClasses = {Post.class}
        ),

        @NamedStoredProcedureQuery(
                name = "DeletePostAndComments",
                procedureName = "DeletePostAndComments",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_post_id", type = Long.class)
                }
        )

})


@Data
public class Post implements Serializable {

    @Serial
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private Long userId;
    private String content;
    private Timestamp createdAt;

    @Transient
    private String userName;

    @Transient
    private String formattedCreatedAt;

}
