package com.example.imu.blog.security.authenticaton;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
