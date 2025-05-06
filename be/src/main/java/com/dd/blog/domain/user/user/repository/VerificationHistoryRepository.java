package com.dd.blog.domain.user.user.repository;

import com.dd.blog.domain.user.user.entity.VerificationHistory;
import com.dd.blog.domain.user.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VerificationHistoryRepository extends JpaRepository<VerificationHistory, Long> {
    List<VerificationHistory> findByUserAndDateBetween(User user, LocalDate startDate, LocalDate endDate);
}
