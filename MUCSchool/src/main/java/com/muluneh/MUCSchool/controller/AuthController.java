package com.muluneh.MUCSchool.controller;
import com.muluneh.MUCSchool.dto.AuthCredentialRequest;
import com.muluneh.MUCSchool.dto.AuthResponse;
import com.muluneh.MUCSchool.util.JwtUtil;
import com.muluneh.MUCSchool.domain.Account;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthCredentialRequest request){
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getUsername(), request.getPassword()
                            )
                    );

            Account account = (Account) authenticate.getPrincipal();
            account.setPassword(null);
//            String token = jwtUtil.generateToken(account);
//            String welcomeMessage = "Welcome " + account.getUsername() + "!";
//            AuthResponse response = new AuthResponse(account, token, welcomeMessage);

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            jwtUtil.generateToken(account)
                    )
                    .body(account);

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }
    @GetMapping("/validate")
    public ResponseEntity<?> validate(@RequestParam String token,
                                      @AuthenticationPrincipal Account account){
        try {
            Boolean isTokenValid = jwtUtil.validateToken(token,account);
            return ResponseEntity.ok(isTokenValid);
        } catch (ExpiredJwtException e){
            return ResponseEntity.ok(false);
        }



    }
}
