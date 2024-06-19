package cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.utils;

import org.springframework.stereotype.Component;

@Component
public class Constant {

    //exceptions
    public static final String PLAYER_NOT_FOUND = "Player not found with ID: ";
    public static final String USERNAME_ALREADY_EXISTS = "Username already exists: ";

    //URL's
    public static final String HOME = "/players";
    public static final String CREATE_PLAYER="/create";
    public static final String UPDATE = "/{id}";
    public static final String CREATE_GAME ="/{id}/games";
    public static final String DELETE_GAME ="/{id}/games";
    public static final String PLAYER_GAME ="/{id}/games";
    public static final String RANKING ="/ranking";
    public static final String BEST_PLAYER ="/ranking/winner";
    public static final String WORST_PLAYER ="/ranking/loser";

    //JWT
    public static final String SECRET_KEY = "aGFuZHBvdHVuaXRvZmZpY2Vyc2hpbm5pbmdwaHlzaWNhbHRocm93bmF3YXljb29rbGE=";
}
