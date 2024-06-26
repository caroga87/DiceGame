package cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.mapper;

import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.domain.Game;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.domain.Player;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.dto.GameDTO;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.dto.PlayerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class PlayerMapper {

    @Autowired
    private GameMapper gameMapper;

    public PlayerDTO toPlayerDTO(Player player) {
        PlayerDTO playerDTO = null;
        if (player != null) {
            playerDTO = createPlayerDTO(player);
            playerDTO.calculationPercentageWin();
        }
        return playerDTO;
    }

    public Player toPlayerEntity (PlayerDTO playerDTO) {
       Player player = null;
        if (playerDTO != null) {
            player = createPlayerEntity(playerDTO);
        }
        return player;
    }


    private PlayerDTO createPlayerDTO(Player player) {
        List<GameDTO> rollDice = player.getRollDice() != null ? player.getRollDice().stream()
                .map(game -> gameMapper.toGameDTO(game))
                .toList() : new ArrayList<>();
        return PlayerDTO.builder()
                .id(player.getPlayerId())
                .rollDice(rollDice)
                .username(player.getUsername())
                .build();
    }

    private Player createPlayerEntity(PlayerDTO playerDTO) {
        List<Game> rollDice = playerDTO.getRollDice() != null ? playerDTO.getRollDice().stream()
                .map(gameDTO -> gameMapper.toGameEntity(gameDTO))
                .toList() : new ArrayList<>();
        return Player.builder()
                .playerId(playerDTO.getId())
                .username(playerDTO.getUsername() != null ? playerDTO.getUsername() : "ANONYMOUS")
                .rollDice(rollDice)
                .build();
    }

}
