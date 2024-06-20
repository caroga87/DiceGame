package cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.services.impl;

import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.services.JwtService;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.dao.AuthResponse;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.dao.AuthenticationRequest;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.dao.RegisterRequest;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.domain.Role;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.domain.User;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.repository.UserRepository;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {
        var user = User.builder()
                .name(request.getName())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));
        var user = userRepository.findUserByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();

    }
}
