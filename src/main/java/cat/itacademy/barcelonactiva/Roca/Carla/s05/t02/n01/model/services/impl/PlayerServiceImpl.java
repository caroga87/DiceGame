package cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.services.impl;

import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.exceptions.UsernameAlreadyExistsException;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.mapper.PlayerMapper;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.domain.Player;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.repository.GameRepository;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.repository.PlayerRepository;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerMapper playerMapper;

    @Autowired
    private GameRepository gameRepository;

    @Override
    public PlayerDTO createPlayer(PlayerDTO playerDTO) {
        if (playerRepository.existsByUsername(playerDTO.getUsername())) {
            throw new UsernameAlreadyExistsException("Username " + playerDTO.getUsername() + " already exists");
        }

        Player player = playerMapper.toPlayerEntity(playerDTO);
        Player savedPlayer = playerRepository.save(player);
        return playerMapper.toPlayerDTO(savedPlayer);
    }

    @Override
    public List<PlayerDTO> getAllPlayers() {
        return List.of();
    }

    @Override
    public Optional<PlayerDTO> getPlayerById(Integer id) {
        return Optional.empty();
    }

    @Override
    public PlayerDTO updatePlayerName(Integer id, String newName) {
        return null;
    }

    @Override
    public void deletePlayerGames(Integer id) {

    }

    @Override
    public double calculateSuccessPercentage(PlayerDTO playerDTO) {
        return 0;
    }

    @Override
    public double calculateAverageSuccessPercentage() {
        return 0;
    }
}
