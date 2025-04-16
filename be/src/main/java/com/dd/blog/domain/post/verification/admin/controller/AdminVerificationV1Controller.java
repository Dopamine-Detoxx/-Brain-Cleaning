package com.dd.blog.domain.post.verification.admin.controller;

import com.dd.blog.domain.post.verification.admin.dto.VerificationStatusUpdateDto;
import com.dd.blog.domain.post.verification.admin.service.AdminVerificationService;
import com.dd.blog.domain.post.verification.entity.Verification;
import com.dd.blog.domain.post.verification.entity.VerificationStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/verifications")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')") // TODO: 관리자 권한 체크 필요
public class AdminVerificationV1Controller {

    private final AdminVerificationService adminVerificationService;

    @PatchMapping("/{verificationId}")
    public ResponseEntity<Void> updateVerificationStatus(
            @PathVariable Long verificationId,
            @Valid @RequestBody VerificationStatusUpdateDto statusUpdateDto) {

        VerificationStatus requestedStatus = statusUpdateDto.getStatus();

        if(requestedStatus == VerificationStatus.APPROVED)
            adminVerificationService.approveVerification(verificationId);
        else if (requestedStatus == VerificationStatus.REJECTED)
            adminVerificationService.rejectVerification(verificationId);
        else
            throw new IllegalArgumentException("요청 본문의 status 값은 반드시 APPROVED 또는 REJECTED 여야함");

        return ResponseEntity.ok().build();
    }

    // PENDING 상태인 인증 요청 목록을 페이징하여 조회
    @GetMapping
    public ResponseEntity<Page<Verification>> getPendingVerifications(Pageable pageable) {
        Page<Verification> pendingVerifications = adminVerificationService.getPendingVerification(pageable);
        return ResponseEntity.ok(pendingVerifications);
    }

}
