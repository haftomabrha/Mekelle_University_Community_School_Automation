package com.muluneh.MUCSchool.repository;

import com.muluneh.MUCSchool.domain.PasswordReset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetRepository extends JpaRepository<PasswordReset,Long> {
    PasswordReset findByToken(String token);
}
