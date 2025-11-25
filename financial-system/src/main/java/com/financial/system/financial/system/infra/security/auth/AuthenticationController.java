package com.financial.system.financial.system.infra.security.auth;

import com.financial.system.financial.system.dto.LoginDTO;
import com.financial.system.financial.system.infra.security.jwt.TokenDTO;
import com.financial.system.financial.system.infra.security.jwt.TokenService;
import com.financial.system.financial.system.infra.security.user.PersonDetails;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity makeLogin(@RequestBody @Valid LoginDTO data){
        var token =  new UsernamePasswordAuthenticationToken(data.email(), data.password());

        var authentication = manager.authenticate(token);

        var tokenJWT = tokenService.generateToken((PersonDetails)
                authentication.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(tokenJWT));
    }
}
