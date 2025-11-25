package com.financial.system.financial.system.infra.security.auth;

import com.financial.system.financial.system.dto.LoginDTO;
import com.financial.system.financial.system.infra.security.jwt.TokenDTO;
import com.financial.system.financial.system.infra.security.jwt.TokenService;
import com.financial.system.financial.system.infra.security.user.PersonDetails;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationManager manager;
    private final TokenService tokenService;
        public AuthenticationController(AuthenticationManager manager, TokenService tokenService) {
        this.manager = manager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> makeLogin(@RequestBody @Valid LoginDTO data) {
        var authToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = manager.authenticate(authToken);
        String tokenJWT = tokenService.generateToken((PersonDetails) auth.getPrincipal());
        return ResponseEntity.ok(new TokenDTO(tokenJWT));
    }
}
