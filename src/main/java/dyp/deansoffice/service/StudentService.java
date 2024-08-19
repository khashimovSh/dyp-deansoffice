package dyp.deansoffice.service;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import dyp.deansoffice.entity.StudentEntity;
import dyp.deansoffice.mapper.StudentMapper;
import dyp.deansoffice.model.student.Student;
import dyp.deansoffice.repository.StudentRepository;
import dyp.deansoffice.util.StudentIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentEntity save(Student student) {
        var generatedStudentId = StudentIdGenerator.generateStudentId();
        student.setStudentId(generatedStudentId);
        student.setId(Uuids.timeBased());
        return studentRepository.save(StudentMapper.INSTANCE.mapDtoToEntity(student));
    }

    public void removeStudent(String studentId) {
        var student = studentRepository.findByStudentId(studentId);
        studentRepository.deleteStudentById(student.getId());
    }
}
