package dyp.deansoffice.controller;

import dyp.deansoffice.util.JwtTokenHelper;
import dyp.deansoffice.model.AuthenticationRequest;
import dyp.deansoffice.model.LoginResponse;
import dyp.deansoffice.model.security.User;
import dyp.deansoffice.model.security.UserInfo;
import java.security.Principal;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenHelper jwtTokenHelper;
    private final UserDetailsService userDetailsService;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) {
        System.out.println(authenticationRequest);
        final var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            authenticationRequest.getUserName(), authenticationRequest.getPassword()));
        if (Objects.isNull(authentication))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(authenticationRequest);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var user=(User)authentication.getPrincipal();
        var jwtToken=jwtTokenHelper.generateToken(user.getUsername());
        var response = new LoginResponse();
        response.setToken(jwtToken);


        return ResponseEntity.ok(response);
    }

    @GetMapping("/auth/userinfo")
    public ResponseEntity<?> getUserInfo(Principal user){
        var userObj = (User) userDetailsService.loadUserByUsername(user.getName());
        System.out.println(userObj);
        var userInfo = new UserInfo();
        userInfo.setFirstName(userObj.getUsername());
        userInfo.setLastName(userObj.getLastName());
        userInfo.setRoles(userObj.getAuthorities().toArray());

        return ResponseEntity.ok(userInfo);

    }
}
