package cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.mapper;

import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.domain.Player;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.dto.GameDTO;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.dto.PlayerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class PlayerMapper {

    @Autowired
    private GameMapper gameMapper;

    public PlayerDTO toplayerDTO (Player player) {
        PlayerDTO playerDTO = null;
        if (player != null) {
            playerDTO = createPlayerDTO(player);
            playerDTO.calculationPercentageWin();
        }
        return playerDTO;
    }


    private PlayerDTO createPlayerDTO (Player player){
        List< GameDTO> rollDice = player.getRollDice().stream().map(game -> gameMapper.toGameDTO (game)).toList();
        return PlayerDTO.builder()
                .id(player.getPlayer_Id())
                .name(player.getName())
                .rollDice(rollDice)
                .userName(player.getUserName())
                .build();

    }

}
