package cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.utils;

import org.springframework.stereotype.Component;

@Component
public class Constant {

    //exceptions
    public static final String PLAYER_NOT_FOUND = "Player not found with ID: ";
    public static final String USERNAME_ALREADY_EXISTS = "Username already exists: ";
    public static final String USERNAME_NOT_FOUND= "User not found with username: ";
    public static final String EMAIL_ALREADY_EXISTS = "This email already exists";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String FORBIDDEN = "You don't have permissions";
    public static final String GAME_NOT_FOUND = "This player has not games";

    //URL's
    public static final String HOME = "/players";
    public static final String CREATE_PLAYER="/create";
    public static final String UPDATE = "/{playerId}";
    public static final String CREATE_GAME ="/{playerId}/games";
    public static final String DELETE_GAME ="/{playerId}/games";
    public static final String PLAYER_GAME ="/{playerId}/games";
    public static final String DELETE_PLAYER = "/delete/{id}";
    public static final String RANKING ="/ranking";
    public static final String BEST_PLAYER ="/ranking/winner";
    public static final String WORST_PLAYER ="/ranking/loser";
    public static final String LOGIN = "/login";
    public static final String REGISTER = "/register";
    public static final String AUTHENTICATION = "/auth";
    public static final String AVERAGE_SUCCESS = "/average";

    //JWT
    public static final int TOKEN_EXPIRATION_TIME = 24*60*60*1000;
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer";
    public static final String [] AUTH_WHITELIST = { "/register" , "/login"};
}
