package com.ikubinfo.project.restaurantapp.controller;

import com.ikubinfo.project.restaurantapp.domain.auth.AuthRequest;
import com.ikubinfo.project.restaurantapp.domain.auth.TokenDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.UserDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.User;
import com.ikubinfo.project.restaurantapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.Instant;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor @Validated
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final JwtEncoder jwtEncoder;
  private final UserService userService;

  @PostMapping("/login")
  public ResponseEntity<TokenDTO> login(@RequestBody @Valid AuthRequest request) {
    try {
      Authentication authentication =
              authenticationManager.authenticate(
                      new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

      User user = (User) authentication.getPrincipal();

      Instant now = Instant.now();
      Long expiry = 3600L;

      String scope =
              authentication.getAuthorities().stream()
                      .map(GrantedAuthority::getAuthority)
                      .collect(Collectors.joining(" "));

      JwtClaimsSet claims =
              JwtClaimsSet.builder()
                      .issuer("ikubinfo.al")
                      .issuedAt(now)
                      .expiresAt(now.plusSeconds(expiry))
                      .subject(user.getUsername())
                      .claim("roles", scope)
                      .audience(Arrays.asList("Audienca"))
                      .build();

      String token = this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

      return ResponseEntity.ok()
              .header(HttpHeaders.AUTHORIZATION, "Bearer ".concat(token))
              .body(new TokenDTO("Bearer ".concat(token)));
    } catch (BadCredentialsException ex) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }

  @PostMapping("/register")
  public ResponseEntity<UserDTO> registerUser(@RequestBody @Valid UserDTO u){
    return ResponseEntity.ok(userService.registerUser(u,null));
  }

}
