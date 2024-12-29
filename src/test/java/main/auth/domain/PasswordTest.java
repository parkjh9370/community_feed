package main.auth.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class PasswordTest {

    @Test
    void givenPassword_whenMatchSamePassword_thenReturnThrow() {
        Password password = Password.createEncryptPassword("password");

        assertTrue(password.matchPassword("password"));
    }

    @Test
    void givenPassword_whenUnmatchedPassword_thenReturnThrow() {
        Password password = Password.createEncryptPassword("password12");

        assertFalse(password.matchPassword("password"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenPasswordIsNull_thenThrowError(String password) {
        assertThrows(IllegalArgumentException.class,
            () -> Password.createEncryptPassword(password));
    }
}
