package comments.entities;

import comments.queries.Queries;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Kacper on 2014-10-11.
 */
@NamedQueries({
    @NamedQuery(name = Queries.ALL_COMMENTS_BY_FILE_ID,
        query = "select c from Comment c " +
                "where c.fileId = :id " +
                "order by c.time desc")
})
@NamedNativeQueries({
    @NamedNativeQuery(name = Queries.ADD_COMMENT,
        query = "insert into comments (comment_text, user_id, file_id, timePlaced) " +
                "values (?, ?, ?, ?);"),
    @NamedNativeQuery(name = Queries.EDIT_COMMENT,
        query = "update comments set " +
                "comment_text = ?, " +
                "user_id = ?," +
                "file_id = ?, " +
                "timePlaced = ? " +
                "where comment_id = ?;"),
    @NamedNativeQuery(name = Queries.DELETE_COMMENT,
        query = "delete from comments " +
                "where comment_id = ?;")
})
@Entity
@Table(name = "comments")
public class Comment implements Serializable {


    private static final long serialVersionUID = -5231661456318990049L;

//    comment_id serial NOT NULL,
//    comment_text text NOT NULL,
//    user_id bigint NOT NULL,
//    time timestamp NOT NULL

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false, unique = true, columnDefinition = "bigserial")
    private Long id;

    @Column(name = "comment_text", nullable = false)
    private String commentText;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "timePlaced", nullable = false)
    @Type(type = "timestamp")
    private Date time;

    @Column(name = "file_id", nullable = false)
    private Long fileId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    @Override
    public String toString() {
        return String.format("[id=%d, commentText=%s, user_id=%d, file_id=%d, time=%s", userId, commentText, userId, fileId, time);
    }
}
