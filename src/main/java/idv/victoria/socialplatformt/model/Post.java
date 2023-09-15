package idv.victoria.socialplatformt.model;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
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
    private User user;
}
