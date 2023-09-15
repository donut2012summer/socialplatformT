package idv.victoria.socialplatformt.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.jpa.repository.query.Procedure;


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
})
@Data
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userName;
    private String mobile;
    private String email;
    private String password; // Hashed password
    private byte[] coverImage;
    private String biography;

}
