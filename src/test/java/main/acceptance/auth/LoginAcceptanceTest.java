package main.acceptance.auth;

import static main.acceptance.steps.LoginAcceptanceSteps.requestLoginGetResponseCode;
import static main.acceptance.steps.LoginAcceptanceSteps.requestLoginGetToken;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import main.acceptance.utils.AcceptanceTestTemplate;
import main.auth.application.dto.LoginRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoginAcceptanceTest extends AcceptanceTestTemplate {

    private final String email = "testEmail@email.com";

    @BeforeEach
    void setUp() {
        this.cleanUp();
        this.createUser(email);
    }

    @Test
    void givenEmailAndPassword_whenLogin_thenReturnToken() {
        LoginRequestDto dto = new LoginRequestDto(email, "password");

        String token = requestLoginGetToken(dto);

        assertNotNull(token);
    }

    @Test
    void givenEmailAndWrongPassword_whenLogin_thenReturnCodeNotZero() {
        LoginRequestDto dto = new LoginRequestDto(email, "12password");

        Integer code = requestLoginGetResponseCode(dto);

        assertEquals(400, code);
    }
}
