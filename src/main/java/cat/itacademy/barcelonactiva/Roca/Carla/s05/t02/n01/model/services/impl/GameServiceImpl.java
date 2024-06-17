package cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.services.impl;

import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.exceptions.PlayerNotFoundException;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.mapper.GameMapper;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.domain.Game;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.domain.Player;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.dto.GameDTO;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.repository.GameRepository;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.repository.PlayerRepository;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.services.GameService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameMapper gameMapper;

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public GameDTO createGame(Integer player_id) {
        Player player = playerRepository.findById(player_id).orElseThrow(
                () -> new PlayerNotFoundException("Player with ID " + player_id + " not found"));
        Game game = Game.createGame(player);
        Game savedGame = gameRepository.save(game);
        return gameMapper.toGameDTO(savedGame);
    }

    @Override
    public List<GameDTO> getGamesById(Integer player_id) {
        Player player = playerRepository.findById(player_id).orElseThrow(
                () -> new PlayerNotFoundException("Player with ID " + player_id + " not found"));
        return gameRepository.findByPlayer(player).stream()
                .map(gameMapper::toGameDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteGameById(Integer player_id) {
        Player player = playerRepository.findById(player_id).orElseThrow(
                () -> new PlayerNotFoundException("Player with ID " + player_id + " not found"));
        gameRepository.deleteByPlayer(player);
    }

}



