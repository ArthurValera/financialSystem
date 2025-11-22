package com.financial.system.financial.system.dto;

import jakarta.validation.constraints.NotNull;

public record PersonUpdateDTO(@NotNull Long id,
                              String name,
                              AddressDTO address) {
}
