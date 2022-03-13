package dyp.deansoffice.model.student;

import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@Generated
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table("student")
public class StudentEntity {
    @PrimaryKey
    @CassandraType(type = CassandraType.Name.TIMEUUID)
    private UUID id;
    private String firstName;
    private String lastName;
    private String major;
    @CassandraType(type = Name.DATE)
    private Date dateOfBirth;
    @CassandraType(type = Name.DATE)
    private Date startDateOfStudies;
    private String nationality;
    private String placeOfBirth;
    private boolean active;

}
