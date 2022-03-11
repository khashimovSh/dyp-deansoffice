package dyp.security.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class JwtTokenHelperTest {

    @Test
    void shouldGenerateToken() {
        //given
        var helper = new JwtTokenHelper();
        helper.setSecretKey("myKey");

        //when
        var token = helper.generateToken("shakhzod");

        //then
        assertThat(token).isNotEmpty();
        assertThat(token).startsWith("ey");
    }
}