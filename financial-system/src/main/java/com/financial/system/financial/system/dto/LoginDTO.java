package com.financial.system.financial.system.dto;

import jakarta.validation.constraints.NotNull;

public record LoginDTO(
        String email,
        String password
) {
}
