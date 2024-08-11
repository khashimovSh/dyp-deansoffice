package dyp.deansoffice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dyp.deansoffice.config.IntegrationTest;
import dyp.deansoffice.model.endpoint.AuthenticationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@IntegrationTest
class AuthenticationControllerTest {

	@LocalServerPort
	private int serverPort;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void shouldReturnUnauthorizedStatus_whenUserDoesNotExist() throws Exception {
		//when-then
		given()
				.port(serverPort)
				.body(AuthenticationRequest.builder().userName("shakhzod").password("123").build())
				.post("/api/v1/auth/login")
				.then()
				.statusCode(UNAUTHORIZED.value());
	}
}
