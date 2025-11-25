package com.financial.system.financial.system.infra.security.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.financial.system.financial.system.infra.security.jwt.TokenService;
import com.financial.system.financial.system.infra.security.user.PersonDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final PersonDetailsService personDetailsService;

    public SecurityFilter(TokenService tokenService, PersonDetailsService personDetailsService) {
        this.tokenService = tokenService;
        this.personDetailsService = personDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String tokenJWT = resolveToken(request);

        if (tokenJWT != null) {
            try{
                String subject = tokenService.getSubject(tokenJWT);
                var personDetails = personDetailsService.loadUserByUsername(subject);
                var authentication = new UsernamePasswordAuthenticationToken(personDetails,
                        null,
                        personDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }catch (JWTVerificationException exception){
                System.out.println("Token invalido" + exception.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || authorizationHeader.startsWith("Bearer ")) {
            return null;
        }
        return authorizationHeader.replace("Bearer ", "");
    }
}
