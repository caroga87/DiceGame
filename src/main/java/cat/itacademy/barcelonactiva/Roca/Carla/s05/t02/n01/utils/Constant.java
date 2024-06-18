package cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.utils;

import org.springframework.stereotype.Component;

@Component
public class Constant {

    //exceptions
    public static final String playerNotFound = "Player not found with ID: ";
    public static final String usernameAlreadyExists = "Username already exists: ";

    //URL's
    public static final String home = "/players";
    public static final String update = "/{id}";
    public static final String createGame ="/{id}/games";
    public static final String deleteGame="/{id}/games";
    public static final String playerGame ="/{id}/games";
    public static final String ranking ="/ranking";
    public static final String bestPlayer ="/ranking/winner";
    public static final String worstplayer ="/ranking/loser";
}
