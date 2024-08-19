package dyp.deansoffice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dyp.deansoffice.config.IntegrationTest;
import dyp.deansoffice.model.endpoint.AuthenticationRequest;
import dyp.deansoffice.model.endpoint.LoginResponse;
import dyp.deansoffice.model.endpoint.StudentResponse;
import dyp.deansoffice.model.student.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.HttpStatus.OK;

@IntegrationTest
public class StudentControllerTest {

    @LocalServerPort
    private int serverPort;

    @Test
    void adminShouldCreateAndDeleteStudent() throws IOException {
        var studentJson = new ClassPathResource("student/student.json").getFile();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        var student = objectMapper.readValue(studentJson, Student.class);

        var loginResponse = given()
                .port(serverPort)
                .contentType("application/json")
                .body(AuthenticationRequest.builder().username("shakhzod").password("shakhzod123").build())
                .post("/api/v1/auth/login")
                .then()
                .statusCode(OK.value())
                .extract()
                .body().as(LoginResponse.class);

        assertNotNull(loginResponse.getToken());

        var createdStudent = given()
                .port(serverPort)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .contentType("application/json")
                .body(student)
                .post("/api/v1/student/create")
                .then()
                .statusCode(OK.value())
                .extract()
                .body().as(StudentResponse.class);

        assertNotNull(createdStudent.getUsername());
        assertNotNull(createdStudent.getPassword());

        given()
                .port(serverPort)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .pathParam("studentId", createdStudent.getUsername())
                .delete("/api/v1/student/{studentId}")
                .then()
                .statusCode(OK.value());
    }
}
