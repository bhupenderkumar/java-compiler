package file_statuses.entities;

import file_statuses.queries.Queries;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Kacper on 2014-10-11.
 */
@NamedQueries({
        @NamedQuery(name = Queries.FILE_STATUS_BY_ID,
            query = "select fs from FileStatus fs " +
                    "where fs.id = :id"),
        @NamedQuery(name = Queries.FILE_STATUS_BY_NAME,
            query = "select fs from FileStatus fs " +
                    "where fs.name = :name"),
        @NamedQuery(name = Queries.ALL_FILE_STATUSES,
            query = "select fs from FileStatus fs")
})
@NamedNativeQueries({
        @NamedNativeQuery(name = Queries.ADD_FILE_STATUS,
                query = "insert into file_statuses (name) values (?);"),
        @NamedNativeQuery(name = Queries.EDIT_FILE_STATUS,
                query = "update file_statuses set name = ? " +
                        "where file_status_id = ?;"),
        @NamedNativeQuery(name = Queries.DELETE_FILE_STATUS,
                query = "delete from file_statuses " +
                        "where file_status_id = ?;")
})
@Entity
@Table(name = "file_statuses")
public class FileStatus implements Serializable {


    private static final long serialVersionUID = -9029751675026820213L;

//    file_status_id serial NOT NULL,
//    name varchar(50) NOT NULL

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_status_id", nullable = false, unique = true, columnDefinition = "bigserial")
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
