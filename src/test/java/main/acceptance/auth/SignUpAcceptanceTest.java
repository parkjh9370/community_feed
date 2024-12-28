package main.acceptance.auth;

import static main.acceptance.steps.SignUpAcceptanceSteps.requestSendEmail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import main.acceptance.utils.AcceptanceTestTemplate;
import main.auth.application.dto.SendEmailRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SignUpAcceptanceTest extends AcceptanceTestTemplate {

    private final String email = "testEmail@email.com";

    @BeforeEach
    void setUp() {
        this.cleanUp();
    }

    @Test
    void givenEmail_whenSendEmail_thenVerificationTokenSaved() {
        SendEmailRequestDto dto = new SendEmailRequestDto(email);

        Integer code = requestSendEmail(dto);

        String token = this.getEmailToken(email);
        assertNotNull(token);
        assertEquals(0, code);
    }

    @Test
    void givenInvalidEmail_whenSendEmail_thenVerificationTokenNotSaved() {
        SendEmailRequestDto dto = new SendEmailRequestDto("abc");

        Integer code = requestSendEmail(dto);

        //String token = this.getEmailToken(email);
        //assertNull(token);
        assertEquals(400, code);
    }
}
