package az.developia.comp_shop_mashallah_isgenderli.controller;


import az.developia.comp_shop_mashallah_isgenderli.DTO.Request.AuthenticationRequest;
import az.developia.comp_shop_mashallah_isgenderli.DTO.Request.RegisterRequest;
import az.developia.comp_shop_mashallah_isgenderli.DTO.Response.AuthenticationResponse;
import az.developia.comp_shop_mashallah_isgenderli.service.AuthenticationService;
import az.developia.comp_shop_mashallah_isgenderli.service.JwtService;
import az.developia.comp_shop_mashallah_isgenderli.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/computer")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    private final AuthenticationService service;
    private final JwtService jwtService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request, HttpServletRequest httpRequest) {
        log.info("Method called: register | HTTP Method: {}", httpRequest.getMethod());
        AuthenticationResponse response = service.register(request);
        log.info("Method completed: register");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request, HttpServletRequest httpRequest) {
        log.info("Method called: authenticate | HTTP Method: {}", httpRequest.getMethod());
        AuthenticationResponse response = service.authenticate(request);
        log.info("Method completed: authenticate");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("Method called: refreshToken | HTTP Method: {}", request.getMethod());
        service.refreshToken(request, response);
        log.info("Method completed: refreshToken");
    }

}
