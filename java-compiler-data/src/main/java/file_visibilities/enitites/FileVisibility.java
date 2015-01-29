package file_visibilities.enitites;

import file_visibilities.queries.Queries;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Kacper on 2014-10-11.
 */
@NamedQueries({
        @NamedQuery(name = Queries.FILE_VISIBILITY_BY_NAME,
            query = "select fv from FileVisibility fv " +
                    "where fv.name = :name"),
        @NamedQuery(name = Queries.FILE_VISIBILITY_BY_ID,
            query = "select fv from FileVisibility fv " +
                    "where fv.id = :id"),
        @NamedQuery(name = Queries.ALL_FILE_VISIBILITIES,
            query = "select fv from FileVisibility fv")
})
@NamedNativeQueries({
        @NamedNativeQuery(name = Queries.ADD_FILE_VISIBILITY,
            query = "insert into file_visibility (name) values (?);"),
        @NamedNativeQuery(name = Queries.EDIT_FILE_VISIBILITY,
            query = "update file_visibility set name = ? " +
                    "where file_visibility_id = ?;"),
        @NamedNativeQuery(name = Queries.DELETE_FILE_VISIBILITY,
            query = "delete from file_visibility " +
                    "where file_visibility_id = ?;")
})
@Entity
@Table(name = "file_visibility")
public class FileVisibility implements Serializable {

    private static final long serialVersionUID = 3021862694641081936L;

//    file_visibility_id serial NOT NULL,
//    name varchar(50)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_visibility_id", nullable = false, unique = true, columnDefinition = "bigserial")
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

    @Override
    public String toString() {
        return String.format("[id=%d, name=%s", id, name);
    }
}
