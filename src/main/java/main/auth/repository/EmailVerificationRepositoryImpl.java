package main.auth.repository;

import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import main.auth.application.interfaces.EmailVerificationRepository;
import main.auth.domain.Email;
import main.auth.repository.entity.EmailVerificationEntity;
import main.auth.repository.jpa.JpaEmailVerificationRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EmailVerificationRepositoryImpl implements EmailVerificationRepository {

    private final JpaEmailVerificationRepository jpaEmailVerificationRepository;

    @Override
    @Transactional
    public void createEmailVerification(Email email, String token) {
        String emailAddress = email.getEmailText();
        Optional<EmailVerificationEntity> entity = jpaEmailVerificationRepository.findByEmail(
            emailAddress);

        if (entity.isPresent()) {
            EmailVerificationEntity emailVerificationEntity = entity.get();
            if (emailVerificationEntity.isVerified()) {
                throw new IllegalArgumentException("이미 인증된 이메일입니다.");
            }

            emailVerificationEntity.updateToken(token);
            return;
        }

        EmailVerificationEntity emailVerificationEntity = new EmailVerificationEntity(emailAddress,
            token);
        jpaEmailVerificationRepository.save(emailVerificationEntity);
    }

    @Override
    @Transactional
    public void verifyEmail(Email email, String token) {
        String emailAddress = email.getEmailText();

        EmailVerificationEntity emailVerificationEntity = jpaEmailVerificationRepository.findByEmail(
            emailAddress).orElseThrow(() -> new IllegalArgumentException("인증이 처리되지 않은 이메일 입니다."));

        if (emailVerificationEntity.isVerified()) {
            throw new IllegalArgumentException("이미 인증된 이메일입니다.");
        }

        if (!emailVerificationEntity.hasSameToken(token)) {
            throw new IllegalArgumentException("토큰이 일치하지 않습니다.");
        }

        emailVerificationEntity.verify();
    }
}
