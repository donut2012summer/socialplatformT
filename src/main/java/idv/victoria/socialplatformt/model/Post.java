package idv.victoria.socialplatformt.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

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
                name = "FindPostById",
                procedureName = "FindPostById",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_post_id", type = Long.class)
                },
                resultClasses = {Post.class}
        ),

        @NamedStoredProcedureQuery(
                name = "DeletePostByPostId",
                procedureName = "DeletePostByPostId",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_post_id", type = Long.class)
                }
        )




})



@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private Long userId;
    private String content;
    private byte[] image;
    private LocalDateTime createdAt;

    @Transient
    private Comment comment;

    @Transient
    private String userName;
}
