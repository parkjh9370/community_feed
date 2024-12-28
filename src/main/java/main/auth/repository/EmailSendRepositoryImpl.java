package main.auth.repository;

import lombok.RequiredArgsConstructor;
import main.auth.application.interfaces.EmailSendRepository;
import main.auth.domain.Email;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EmailSendRepositoryImpl implements EmailSendRepository {

    @Override
    public void sendEmail(Email email, String randomToken) {
        // TODO
    }
}
