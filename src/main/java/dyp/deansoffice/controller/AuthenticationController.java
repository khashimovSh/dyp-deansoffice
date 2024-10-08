package dyp.deansoffice.controller;

import dyp.deansoffice.entity.UserDetailsEntity;
import dyp.deansoffice.util.JwtTokenHelper;
import dyp.deansoffice.model.endpoint.AuthenticationRequest;
import dyp.deansoffice.model.endpoint.LoginResponse;
import dyp.deansoffice.model.security.UserInfo;
import java.security.Principal;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class AuthenticationController {

    private final AuthenticationProvider authenticationProvider;
    private final JwtTokenHelper jwtTokenHelper;
    private final UserDetailsService userDetailsService;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) {
        log.info("received request to authenticate user: {}", authenticationRequest.getUsername());
        final var authentication = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(
            authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        if (Objects.isNull(authentication))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(authenticationRequest);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var user=(UserDetailsEntity)authentication.getPrincipal();
        var jwtToken=jwtTokenHelper.generateToken(user.getUsername());
        var response = new LoginResponse();
        response.setToken(jwtToken);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/auth/userinfo")
    public ResponseEntity<?> getUserInfo(Principal user) {
        log.info("Loading user '{}'...", user.getName());
        var userObj = (UserDetailsEntity) userDetailsService.loadUserByUsername(user.getName());
        var userInfo = new UserInfo();
        userInfo.setFirstName(userObj.getUsername());
        userInfo.setLastName(userObj.getLastName());
        userInfo.setRoles(userObj.getAuthorities().toArray());

        return ResponseEntity.ok(userInfo);

    }
}
