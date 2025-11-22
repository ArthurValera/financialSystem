package com.financial.system.financial.system.controller;

import com.financial.system.financial.system.service.projection.ProjectionCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/projection")
public class ProjectionController {
    @Autowired
    private ProjectionCalculator projectionCalculator;

    @GetMapping("/{personId}")
    public ResponseEntity<BigDecimal> projection(
            @PathVariable
            Long personId,
            @RequestParam("until")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate until) {

        var balance = projectionCalculator.projectBalance(personId, until);
        return ResponseEntity.ok(balance);
    }
}