package roles.entities;

import roles.queries.Queries;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Kacper on 2014-10-10.
 */
@NamedQueries({
        @NamedQuery(name = Queries.ALL_ROLES, query = "select r from Role r"),
        @NamedQuery(name = Queries.ROLE_BY_NAME, query = "select r from Role r " +
                "where r.name = :name"),
        @NamedQuery(name = Queries.ROLE_BY_ID, query = "select r from Role r " +
                "where r.id = :id")
}
)
@NamedNativeQueries({
        @NamedNativeQuery(name = Queries.ADD_ROLE,
                query = "insert into roles (name) " +
                        "values(?)"),
        @NamedNativeQuery(name = Queries.EDIT_ROLE,
                query = "update roles set " +
                        "name = ? " +
                        "where role_id = ?"),
        @NamedNativeQuery(name = Queries.DELETE_ROLE,
                query = "delete from roles " +
                        "where role_id = ?;")
})
@Entity
@Table(name = "roles")
public class Role implements Serializable {


    private static final long serialVersionUID = 8936560618804164881L;

//    role_id serial NOT NULL,
//    name varchar(50) NOT NULL

    @Id
    @Column(name = "role_id", nullable = false, unique = true, columnDefinition = "bigserial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
