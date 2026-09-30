package com.financial.system.financial.system.controller;

import com.financial.system.financial.system.infra.security.user.PersonDetails;
import com.financial.system.financial.system.service.projection.ProjectionCalculator;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/projection")
@SecurityRequirement(name = "bearer-key")
public class ProjectionController {
    @Autowired
    private ProjectionCalculator projectionCalculator;

    @GetMapping
    public ResponseEntity<BigDecimal> projection(
            @AuthenticationPrincipal PersonDetails principal,
            @RequestParam("until")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate until) {

        var balance = projectionCalculator.projectBalance(principal.getId(), until);
        return ResponseEntity.ok(balance);
    }
}