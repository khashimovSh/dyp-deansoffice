package dyp.deansoffice.model.student;

import java.util.Date;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Student {
    private UUID id;
    private String firstName;
    private String lastName;
    private String major;
    private Date dateOfBirth;
    private Date startDateOfStudies;
    private String nationality;
    private String placeOfBirth;
    private boolean active;
}
