package idv.victoria.socialplatformt.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Entity
// Map procedures
@NamedStoredProcedureQueries({

        // Map to procedure
        @NamedStoredProcedureQuery(
                name = "HasMobileExists",
                procedureName = "HasMobileExists",
                // Param for procedure
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mobile", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_exists", type = Integer.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "SaveOrUpdateUser",
                procedureName = "SaveOrUpdateUser",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_user_id", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_user_name", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mobile", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_email", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_password", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_biography", type = String.class),
                }
        ),
        @NamedStoredProcedureQuery(
                name = "FindUserByMobile",
                procedureName = "FindUserByMobile",
                resultClasses = User.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mobile", type = String.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "HasUserVerified",
                procedureName = "HasUserVerified",
                resultClasses = User.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mobile", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_password", type = String.class)
                }
        ),

        @NamedStoredProcedureQuery(
                name = "FindUserByPostId",
                procedureName = "FindUserByPostId",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_post_id", type = Integer.class)
                }
        ),

        @NamedStoredProcedureQuery(
                name = "FindUserByCommentId",
                procedureName = "FindUserByCommentId",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_comment_id", type = Long.class)
                },
                resultClasses = {User.class}
        ),

        @NamedStoredProcedureQuery(
                name = "FindUserById",
                procedureName = "FindUserById",
                resultClasses = User.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_user_id", type = Integer.class)
                }
        )

})
@Data
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userName;
    private String mobile;
    private String email;

    // Hashed password
    private String password;
    private String biography;

}
