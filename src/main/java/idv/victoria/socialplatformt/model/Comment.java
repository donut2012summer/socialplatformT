package idv.victoria.socialplatformt.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
// Map to procedures
@NamedStoredProcedureQueries({

        // Map to procedure
        @NamedStoredProcedureQuery(
                name = "FindCommentsByPostId",
                procedureName = "FindCommentsByPostId",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_post_id", type = Long.class)
                },
                resultClasses = {Comment.class}
        ),

        @NamedStoredProcedureQuery(
                name = "SaveOrUpdateComment",
                procedureName = "SaveOrUpdateComment",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.INOUT, name = "p_comment_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_user_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_post_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_content", type = String.class)
                },
                resultClasses = {Comment.class}
        )



})

@Data
public class Comment implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    private Long postId;
    private Long userId;
    private String content;
    private Timestamp createdAt;

    @Transient
    private String userName;

    @Transient
    private String formattedCreatedAt;
}
