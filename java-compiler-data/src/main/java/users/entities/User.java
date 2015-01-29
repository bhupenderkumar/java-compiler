package users.entities;

import users.queries.Queries;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Kacper on 2014-10-09.
 */
@NamedQueries({
        @NamedQuery(name = Queries.USERS_BY_NAME_ASC,
                query = "SELECT u FROM User u where u.username like :username order by u.username asc"),
        @NamedQuery(name = Queries.USERS_BY_NAME_DESC,
                query = "SELECT u FROM User u where u.username like :username order by u.username desc"),
        @NamedQuery(name = Queries.ALL_USERS_ASC,
                query = "SELECT u FROM User u order by u.username asc"),
        @NamedQuery(name = Queries.ALL_USERS_DESC,
                query = "SELECT u FROM User u order by u.username desc"),
        @NamedQuery(name = Queries.USER_BY_NAME,
                query = "SELECT u FROM User u WHERE u.username = :username"),
        @NamedQuery(name = Queries.AUTH_USER,
                query = "SELECT u FROM User u WHERE u.username = :username " +
                        "and u.password = :password"),
        @NamedQuery(name = Queries.COMMENT_AUTHOR,
                query = "select u from User u, Comment c " +
                        "where c.userId = u.id and c.id = :id"),
        @NamedQuery(name = Queries.USERS_BY_ROLE_ID,
                query = "select u from User u " +
                        "where u.roleId = :id")
})
@NamedNativeQueries({
        @NamedNativeQuery(name = Queries.ADD_USER,
            query = "insert into users (username, password, role_id)" +
                    " values (?, ?, ?);"),
        @NamedNativeQuery(name = Queries.EDIT_USER,
            query = "update users set username = ?, password = ?, role_id = ?" +
                    " where user_id = ?;"),
        @NamedNativeQuery(name = Queries.DELETE_USER,
            query = "delete from users " +
                    "where user_id = ?;")
})
@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = -3842497888869568196L;


//    user_id serial NOT NULL,
//    username varchar(50) NOT NULL,
//    password varchar(200) NOT NULL,
//    role_id bigint NOT NULL

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, unique = true, columnDefinition = "bigserial")
    private Long id;

    @Column(name = "username", length = 50, nullable = false)
    private String username;

    @Column(name = "password", length = 200, nullable = false)
    private String password;

    @Column(name = "role_id", nullable = false)
    private Long roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return String.format("[user_id=%d, username=%s, password=%s, role_id=%d", id, username, password, roleId);
    }
}
