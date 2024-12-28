package main.auth.repository.jpa;

import java.util.Optional;
import main.auth.repository.entity.EmailVerificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaEmailVerificationRepository extends
    JpaRepository<EmailVerificationEntity, Long> {

    Optional<EmailVerificationEntity> findByEmail(String email);
}