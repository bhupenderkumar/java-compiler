package users_files.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Kacper on 2014-10-10.
 */
@Entity
@Table(name = "users_files")
public class UserFile implements Serializable {
    private static final long serialVersionUID = 5944022521539054807L;


//    users_files_id serial NOT NULL,
//    user_id bigint NOT NULL,
//    file_id bigint NOT NULL

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_files_id", nullable = false, unique = true, columnDefinition = "bigserial")
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "file_id", nullable = false)
    private Long fileId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }
}
