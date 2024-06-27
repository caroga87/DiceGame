package cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message) {
        super (message);
    }
}
