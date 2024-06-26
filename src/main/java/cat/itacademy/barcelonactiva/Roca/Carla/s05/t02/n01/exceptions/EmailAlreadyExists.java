package cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.exceptions;

public class EmailAlreadyExists extends RuntimeException {
    public EmailAlreadyExists(String message) {
        super (message);
    }
}
