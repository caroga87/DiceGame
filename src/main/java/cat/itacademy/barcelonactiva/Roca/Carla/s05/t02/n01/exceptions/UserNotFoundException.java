package cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message){
        super (message);
    }
}
