package main.auth.application.interfaces;

import main.auth.domain.Email;

public interface EmailSendRepository {

    void sendEmail(Email email, String randomToken);
}
