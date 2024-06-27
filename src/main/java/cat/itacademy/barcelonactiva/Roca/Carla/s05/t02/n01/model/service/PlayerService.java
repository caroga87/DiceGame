package cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.service;

import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.dto.PlayerDTO;

import java.util.List;

public interface PlayerService {

    PlayerDTO createPlayer(PlayerDTO playerDTO);
    List<PlayerDTO> getAllPlayers();
    PlayerDTO getPlayerById(Integer id);
    void updatePlayerUsername(Integer id, String newName);
    void deletePlayer(Integer id);
    List <PlayerDTO> playerRankingList();
    Double getAllPlayersAverage ();
    PlayerDTO getBestPlayerDTO ();
    PlayerDTO getWorstPlayerDTO ();

}
