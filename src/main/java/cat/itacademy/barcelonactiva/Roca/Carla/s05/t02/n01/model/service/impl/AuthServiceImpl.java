package cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.service.impl;

import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.exception.EmailAlreadyExistsException;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.exception.InvalidCredentialException;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.domain.Role;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.service.JwtService;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.dao.response.AuthResponse;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.dao.request.AuthenticationRequest;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.dao.request.RegisterRequest;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.domain.User;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.repository.UserRepository;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.service.AuthService;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.utils.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExistsException(Constant.EMAIL_ALREADY_EXISTS);
        }
        User user = User.builder()
                .name(request.getName())
                //.username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role((request.getName().startsWith("admin") ? Role.ADMIN : Role.USER))
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
                request.getEmail(),
                request.getPassword()
        ));
        var user = userRepository.findUserByEmail(request.getEmail()).orElseThrow(()-> new InvalidCredentialException("Invalid email or password"));
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();

    }
}
