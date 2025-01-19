package com.flab.jangteoapi.user.controller;

import com.flab.jangteoapi.user.dto.UserSignUpRequestDto;
import com.flab.jangteoapi.user.service.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity registerUser(@RequestBody @Valid UserSignUpRequestDto requestDto) {

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestParam String username, @RequestParam String password) {

        return ResponseEntity.ok().build();
    }

    /**
     * TODO 임시 | 로그아웃 시 세션 처리 or 토큰 만료처리 중 결정
     *
     * @param session
     * @return
     */
    @PostMapping("/logout")
    public ResponseEntity logout(HttpSession session) {

        return ResponseEntity.ok().build();
    }
}
