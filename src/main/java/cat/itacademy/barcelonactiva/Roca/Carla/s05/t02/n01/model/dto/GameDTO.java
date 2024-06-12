package cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameDTO {

    private int die1;
    private int die2;
    private boolean wonGame;
}
