package cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.mapper;

import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.domain.Game;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.dto.GameDTO;
import org.springframework.stereotype.Component;

@Component
public class GameMapper {
    public GameDTO toGameDTO (Game game) {
        GameDTO gameDTO = null;
        if (game !=null) gameDTO = createGameDTO (game);
        return gameDTO;
    }

    private GameDTO createGameDTO (Game game){
        return GameDTO.builder()
                .die1(game.getDie1())
                .die2(game.getDie2())
                .wonGame(game.isWin())
                .build();
    }
}
