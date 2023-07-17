package com.ikubinfo.project.restaurantapp.controller;

import com.ikubinfo.project.restaurantapp.domain.auth.AuthRequest;
import com.ikubinfo.project.restaurantapp.domain.auth.TokenDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.UserDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.User;
import com.ikubinfo.project.restaurantapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final JwtEncoder jwtEncoder;
  private final UserService userService;
  private final String tokenStatik = "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJndWVzdEBnbWFpbC5jb20iLCJhdWQiOiJBdWRpZW5jYSIsInJvbGVzIjoiQ1VTVE9NRVIiLCJpc3MiOiJpa3ViaW5mby5hbCIsImV4cCI6MTE2ODk2MTk3MzYsImlhdCI6MTY4OTYxOTczN30.OdxN2yS-25AYXFeXct2bnqJ7XQDwyaFQgBBaehKsMe5npsEX8UL8i8S85lGmvtkEPj_vuk48viXCUAI67if-BByF47Ae45Xt9sratWTppkLbj_TA_Dvdj-oyCW8zJZgd3eYJvp2TYGyMxXJWuRm1CwVygWY7AJXaq5XgZnhHt6K2KUQkm02I2znSu6RHieKkaIPiADicM3MSm85ohZyuKZ2IAuXJ8ZAX9j_EBWpA86v2WCapEaasWT-Bum6q2UuuRPmNbsJmXQ6loZPLGKR-83UjMiOxZPyE2X4q5Rg1ubrhLrPBaI4z3anc6AWmU9fCF61HYW3iTSdNXgkL2JfV6Q";
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

      String token = "";


      String userEmail = user.getEmail();
      if(userEmail.equals("guest@gmail.com")){
        token = tokenStatik;
        log.info("--------token statik-------" + token);
      }else{
        token = this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        log.info("--------token-------" + token);

      }

//      String token = this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

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
