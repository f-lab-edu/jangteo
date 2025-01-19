package com.flab.jangteoapi.user.service;

import com.flab.jangteoapi.user.domain.User;
import com.flab.jangteoapi.user.dto.UserSignUpRequestDto;
import com.flab.jangteoapi.user.exception.UserNotFoundException;
import com.flab.jangteoapi.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AuthService {

    private final UserRepository userRepository;

    public User registerUser(UserSignUpRequestDto requestDto) {

        User user = User.builder()
                .username(requestDto.getUsername())
                .password(requestDto.getPassword())
                .email(requestDto.getEmail())
                .fullName(requestDto.getFullName())
                .build();

        return userRepository.save(user);
    }

    public Optional<User> loginUser(String username, String password) {

        return userRepository.findByUsername(username)
                .filter(user -> user.getPassword().equals(password));
    }

    public User getUserById(UUID userId) {

        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("해당하는 사용자가 없습니다."));
    }
}
