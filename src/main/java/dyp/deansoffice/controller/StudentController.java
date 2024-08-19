package dyp.deansoffice.controller;

import dyp.deansoffice.entity.UserDetailsEntity;
import dyp.deansoffice.exception.UserNotAuthorizedException;
import dyp.deansoffice.model.endpoint.StudentResponse;
import dyp.deansoffice.model.student.Student;
import dyp.deansoffice.service.StudentService;
import java.security.Principal;

import dyp.deansoffice.service.security.CustomUserService;
import dyp.deansoffice.validator.StudentValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static dyp.deansoffice.model.security.Role.ADMIN;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/student")
@CrossOrigin
public class StudentController {

    private final CustomUserService userDetailsService;
    private final StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<StudentResponse> create(@RequestBody Student student, Principal user) {
        log.info("request for creating student with name " + student.getFirstname());
        var storedUser = (UserDetailsEntity) userDetailsService.loadUserByUsername(user.getName());
        if(!isAdmin(storedUser)) {
            throw new UserNotAuthorizedException("Only admin can create student");
        }

        StudentValidator.validate(student);

        var savedStudent = studentService.save(student);
        log.info("Student with name '{}' student id: '{}' created", student.getFirstname(),
                savedStudent.getStudentId());
        var userDetails = userDetailsService.createUser(savedStudent.getStudentId());
        log.info("User with username: '{}' created", userDetails.getUsername());
        return ResponseEntity.ok(StudentResponse.builder()
                .id(savedStudent.getId())
                .firstname(savedStudent.getFirstname())
                .lastname(savedStudent.getLastname())
                .username(userDetails.getUsername())
                .password(userDetails.getPassword())
                .build());
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> delete(@PathVariable String studentId, Principal user) {
        log.info("request for deleting student with studentId: {}", studentId);
        var storedUser = (UserDetailsEntity) userDetailsService.loadUserByUsername(user.getName());
        if(!isAdmin(storedUser)) {
            throw new UserNotAuthorizedException("Only admin can create student");
        }
        studentService.removeStudent(studentId);
        log.info("Student with student id: '{}' removed", studentId);
        userDetailsService.removeUser(studentId);
        log.info("User with username '{}' removed", studentId);
        return ResponseEntity.ok().build();
    }

    private boolean isAdmin(UserDetailsEntity userDetailsEntity) {
        return userDetailsEntity.getAuthorities()
            .stream()
            .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(ADMIN.name()));
    }
}
