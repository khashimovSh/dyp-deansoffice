package dyp.deansoffice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dyp.deansoffice.model.endpoint.AuthenticationRequest;
import dyp.deansoffice.service.security.CustomUserService;
import dyp.deansoffice.util.JwtTokenHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(AuthenticationController.class)
class AuthenticationControllerTest {
	@MockBean
	private CustomUserService customUserService;
	@MockBean
	private JwtTokenHelper jwtTokenHelper;
	@MockBean
	private AuthenticationEntryPoint authenticationEntryPoint;
	@MockBean
	private AuthenticationManager authenticationManager;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private MockMvc mockMvc;

	@Test
	void shouldReturnForbiddenStatus_whenUserDoesNotExist() throws Exception {
		//when-then
		mockMvc.perform(MockMvcRequestBuilders
			.post("/api/v1/auth/login")
			.content(objectMapper.writeValueAsString(
				AuthenticationRequest.builder().userName("shakhzod").password("shakh").build()))
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isForbidden());

	}

}
