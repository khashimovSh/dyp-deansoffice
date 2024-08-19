package dyp.deansoffice.entity;

import java.time.LocalDate;
import java.util.UUID;

import lombok.*;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

@Data
@Generated
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("student")
public class StudentEntity {
    @CassandraType(type = CassandraType.Name.TIMEUUID)
    @PrimaryKeyColumn(name = "id",ordinal = 0,type = PrimaryKeyType.PARTITIONED)
    private UUID id;
    @Column("student_id")
    @PrimaryKeyColumn(name="student_id",ordinal = 1,type = PrimaryKeyType.CLUSTERED)
    private String studentId;
    private String firstname;
    private String lastname;
    private String email;
    @Column("phone_number")
    private String phoneNumber;
    private String address;
    @Column("date_of_birth")
    @CassandraType(type = Name.DATE)
    private LocalDate dateOfBirth;
    @Column("place_of_birth")
    private String placeOfBirth;
    private String nationality;
    private String sex;
    private String level;
    private String faculty;
    private String group;
    @Column("form_of_study")
    private String formOfStudy;
    @Column("head_teacher")
    private String headTeacher;
    @Column("start_date")
    @CassandraType(type = Name.DATE)
    private LocalDate startDate;
    @Column("estimated_end_date")
    @CassandraType(type = Name.DATE)
    private LocalDate estimatedEndDate;
    private boolean active;
}
