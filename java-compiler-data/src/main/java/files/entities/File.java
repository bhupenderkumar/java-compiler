package files.entities;

import files.queries.Queries;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Kacper on 2014-10-09.
 */
@NamedQueries({
    @NamedQuery(name = Queries.FILE_BY_ID,
            query = "select f from File f where f.id = :id"),
    @NamedQuery(name = Queries.USER_FILES,
            query = "select f from File f " +
                    "where f.userId = :id " +
                    "order by f.creationTime desc"),
    @NamedQuery(name = Queries.ALL_FILES,
            query = "select f from File f"),
    @NamedQuery(name = Queries.FILE_VISIBILITY_BY_FILE_ID,
            query = "select fv from FileVisibility fv, File f " +
                    "where fv.id = f.fileVisibilityId " +
                    "and f.id = :id"),
    @NamedQuery(name = Queries.FILE_STATUS_BY_FILE_ID,
            query = "select fs from FileStatus fs, File f " +
                    "where fs.id = f.fileStatusId " +
                    "and f.id = :id " +
                    "order by f.creationTime desc"),
    @NamedQuery(name = Queries.FILES_BY_VISIBILITY_ID,
            query = "select f from File f " +
                    "where f.fileVisibilityId = :id " +
                    "order by f.creationTime desc")    ,
    @NamedQuery(name = Queries.FILES_BY_STATUS_ID,
            query = "select f from File f " +
                    "where f.fileStatusId = :id"),
    @NamedQuery(name = Queries.RECENT_FILES,
            query = "select f from File f " +
                    "order by f.id desc"),
    @NamedQuery(name = Queries.COUNT_BY_VISIBILITY,
            query = "select count(f) from File f " +
                    "where f.fileVisibilityId = :id"),
    @NamedQuery(name = Queries.COUNT_BY_STATUS,
            query = "select count(f) from File f " +
                        "where f.fileStatusId = :id"),
    @NamedQuery(name = Queries.COUNT_ALL,
            query = "select count(f) from File f"),
    @NamedQuery(name = Queries.USER_FILES_PUBLIC,
            query = "select f from File f " +
                    "where f.userId = :id " +
                    "and f.fileVisibilityId = :fvId " +
                    "order by f.creationTime desc")
})
@NamedNativeQueries({
    @NamedNativeQuery(name = Queries.ADD_FILE,
        query = "insert into files (filename, file_status_id, file_visibility_id, user_id, creation_time, description) " +
                "values(?,?,?,?,?,?)"),
    @NamedNativeQuery(name = Queries.EDIT_FILE,
        query = "update files set filename = ?, file_status_id = ?, file_visibility_id = ?, description = ? " +
                "where file_id = ?"),
    @NamedNativeQuery(name = Queries.DELETE_FILE,
        query = "delete from comments " +
                "where file_id = ?;" +
                "delete from files " +
                "where file_id = ?;")
})
@Entity
@Table(name = "files")
public class File implements Serializable {

    private static final long serialVersionUID = -3159813981500842506L;

//    file_id serial NOT NULL,
//    source_code text NOT NULL,
//    file_status_id bigint NOT NULL,
//    file_visibility_id bigint NOT NULL

    @Id
    @Column(name = "file_id", nullable = false, columnDefinition = "bigserial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "filename", nullable = false)
    private String filename;

    @Column(name = "file_status_id", nullable = false)
    private Long fileStatusId;

    @Column(name = "file_visibility_id", nullable = false)
    private Long fileVisibilityId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "creation_time", nullable = false)
    private Date creationTime;

    @Column(name = "description", nullable = false)
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Long getFileStatusId() {
        return fileStatusId;
    }

    public void setFileStatusId(Long fileStatusId) {
        this.fileStatusId = fileStatusId;
    }

    public Long getFileVisibilityId() {
        return fileVisibilityId;
    }

    public void setFileVisibilityId(Long fileVisibilityId) {
        this.fileVisibilityId = fileVisibilityId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long user_id) {
        this.userId = user_id;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("[file_id=%d, filename=%s, file_visibility_id=%d, file_status_id=%s, user_id=%s", id, filename, fileVisibilityId, fileStatusId, userId);
    }
}
