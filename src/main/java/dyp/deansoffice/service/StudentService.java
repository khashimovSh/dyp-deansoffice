package dyp.deansoffice.service;

import dyp.deansoffice.model.student.StudentEntity;
import dyp.deansoffice.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentEntity save(StudentEntity student) {
        //for further filtering
        /*var studentEntity = StudentEntity.builder()
            .id(student.getId())
            .firstName(student.getFirstName())
            .lastName(student.getLastName())
            .major(student.getMajor())
            .dateOfBirth(student.getDateOfBirth().getTime())
            .startDateOfStudies(student.getStartDateOfStudies().getTime())
            .nationality(student.getNationality())
            .placeOfBirth(student.getPlaceOfBirth())
            .active(student.isActive()).build();

         */
        return studentRepository.save(student);
    }
}
