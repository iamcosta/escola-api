package app.netlify.iambarroso.escolaapi.service;

import app.netlify.iambarroso.escolaapi.exceptions.AuthException;
import app.netlify.iambarroso.escolaapi.rest.dto.LoginResponseDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    public LoginResponseDto login(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + jwtExpiration);

            return new LoginResponseDto(
                    Jwts.builder()
                    .setSubject(userDetails.getUsername())
                    .setIssuedAt(now)
                    .setExpiration(expiryDate)
                    .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                    .compact());
        } catch (InternalAuthenticationServiceException e) {
            throw new AuthException(e.getMessage());
        } catch (BadCredentialsException e) {
            throw new AuthException("Credenciais inválidas, verifique login/senha e tente novamente");
        }
    }

}
