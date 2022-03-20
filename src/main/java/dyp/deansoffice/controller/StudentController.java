package dyp.deansoffice.controller;

import dyp.deansoffice.model.student.StudentEntity;
import dyp.deansoffice.model.security.User;
import dyp.deansoffice.service.StudentService;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("student")
@CrossOrigin
public class StudentController {

    private final UserDetailsService userDetailsService;
    private final StudentService studentService;

    private static final String ADMIN = "ADMIN";

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody StudentEntity student, Principal user) {
        log.info("request for creating student with name " + student.getFirstName());
        var storedUser = (User) userDetailsService.loadUserByUsername(user.getName());
        if(isAdmin(storedUser)) {
            studentService.save(student);
            return ResponseEntity.ok(String.format("Student %s %s successfully saved",
                student.getFirstName(), student.getLastName()));

        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You don't have permission to add student");
    }

    private boolean isAdmin(User user) {
        return user.getAuthorities()
            .stream()
            .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(ADMIN));
    }
}
