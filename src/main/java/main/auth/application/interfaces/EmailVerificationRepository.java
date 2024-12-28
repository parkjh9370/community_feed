package main.auth.application.interfaces;

import main.auth.domain.Email;

public interface EmailVerificationRepository {

    void createEmailVerification(Email email, String token);
}
