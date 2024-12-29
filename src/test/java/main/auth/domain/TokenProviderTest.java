package main.auth.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TokenProviderTest {

    private final String secretKey = "testSecretKeyTestSecretKeyTestSecretKeyTestSecretKeyTestSecretKeyTestSecretKeyTestSecretKeyTestSecretKey";
    private final TokenProvider tokenProvider = new TokenProvider(secretKey);

    @Test
    void givenValidUserAndRole_whenCreateToken_thenReturnValidToken() {
        Long userId = 1L;
        String role = "ADMIN";

        String token = tokenProvider.createToken(userId, role);

        assertNotNull(token);
        assertEquals(userId, tokenProvider.getUserId(token));
        assertEquals(role, tokenProvider.getUserRole(token));
    }

    @Test
    void givenInvalidToken_whenGetUserId_thenThrowError() {
        String invalidToken = "invalidToken";

        assertThrows(Exception.class, () -> tokenProvider.getUserId(invalidToken));
    }
}