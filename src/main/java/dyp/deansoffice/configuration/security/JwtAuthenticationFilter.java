package dyp.deansoffice.configuration.security;

import dyp.deansoffice.util.JwtTokenHelper;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JwtTokenHelper jwtTokenHelper;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,@NonNull FilterChain filterChain)
        throws ServletException, IOException {
        String authToken;

        authToken = jwtTokenHelper.getToken(request);

        if (null != authToken) {
            var userName = jwtTokenHelper.getUsernameFromToken(authToken);

            if (userName != null) {
                var userDetails = userDetailsService.loadUserByUsername(userName);

                if (jwtTokenHelper.validateToken(authToken, userDetails)) {
                    var authentication = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
