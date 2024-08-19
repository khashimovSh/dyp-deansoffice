package dyp.deansoffice.model.student;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private UUID id;
    private String studentId;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private String address;
    private LocalDate dateOfBirth;
    private String placeOfBirth;
    private String nationality;
    private String sex;
    private String level;
    private String faculty;
    private String group;
    private String formOfStudy;
    private String headTeacher;
    private LocalDate startDate;
    private LocalDate estimatedEndDate;
    private boolean active;
}
