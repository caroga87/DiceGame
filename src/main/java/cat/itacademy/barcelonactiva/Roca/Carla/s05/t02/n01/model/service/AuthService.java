package cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.service;

import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.dao.response.AuthResponse;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.dao.request.AuthenticationRequest;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.dao.request.RegisterRequest;

public interface AuthService {
    AuthResponse register (RegisterRequest request);
    AuthResponse authenticate (AuthenticationRequest request);
}
