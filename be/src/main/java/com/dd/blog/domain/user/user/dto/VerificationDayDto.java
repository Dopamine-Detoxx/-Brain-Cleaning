package com.dd.blog.domain.user.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class VerificationDayDto {
    private LocalDate date;
    private boolean verified;
}
