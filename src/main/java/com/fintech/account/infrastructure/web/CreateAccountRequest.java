package com.fintech.account.infrastructure.web;

import jakarta.validation.constraints.NotBlank;

public record CreateAccountRequest(@NotBlank String holder) {}