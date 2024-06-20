package cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.services;

import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.dao.AuthResponse;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.dao.AuthenticationRequest;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.dao.RegisterRequest;

public interface AuthService {
    AuthResponse register (RegisterRequest request);
    AuthResponse authenticate (AuthenticationRequest request);
}
