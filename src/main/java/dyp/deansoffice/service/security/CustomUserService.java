package dyp.deansoffice.service.security;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import dyp.deansoffice.entity.UserDetailsEntity;
import dyp.deansoffice.model.security.Authority;
import dyp.deansoffice.model.security.Role;
import dyp.deansoffice.repository.security.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserService implements UserDetailsService {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int PASSWORD_LENGTH = 5;
    private static final SecureRandom random = new SecureRandom();
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Loading user '{}'...", username);
        return userRepository.findByUsername(username);
    }

    public UserDetails createUser(String username) {
        var userDetails = UserDetailsEntity.builder()
                .id(Uuids.timeBased())
                .userName(username)
                .password(passwordEncoder.encode(generateRandomPassword()))
                .authorities(List.of(Authority.builder()
                        .role(Role.STUDENT)
                        .roleDescription("Student Role")
                        .build()))
                .build();
        return userRepository.save(userDetails);
    }

    public void removeUser(String username) {
        var user = userRepository.findByUsername(username);
        userRepository.deleteByUserId(user.getId());
    }

    public static String generateRandomPassword() {
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(index));
        }

        return password.toString();
    }
}
