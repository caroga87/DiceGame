package cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.services.impl;

import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.exceptions.PlayerNotFoundException;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.exceptions.UsernameAlreadyExistsException;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.mapper.PlayerMapper;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.domain.Player;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.repository.GameRepository;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.repository.PlayerRepository;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.services.PlayerService;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.utils.Constant;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            throw new UsernameAlreadyExistsException(Constant.usernameAlreadyExists + playerDTO.getUsername());
        }

        Player player = playerMapper.toPlayerEntity(playerDTO);
        Player savedPlayer = playerRepository.save(player);
        return playerMapper.toPlayerDTO(savedPlayer);
    }

    @Override
    public List<PlayerDTO> getAllPlayers() {
        return playerRepository.findAll().stream()
                .map (playerMapper :: toPlayerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PlayerDTO getPlayerById(Integer id){
        Player player = playerRepository.findById(id).orElseThrow(() -> new PlayerNotFoundException(Constant.playerNotFound +id));
        return playerMapper.toPlayerDTO(player);
    }

    @Override
    @Transactional // per si hi ha un error a l'hora d'executar els canvis, farà rollback per tal de que no es realitzin canvis parcials a la bd
    public void updatePlayerName(Integer id, String newName){

        Player player = playerRepository.findById(id).orElseThrow(
                () -> new PlayerNotFoundException (Constant.playerNotFound +id));

        if (playerRepository.findByNameIgnoreCase(newName).isPresent())
            throw new UsernameAlreadyExistsException(Constant.usernameAlreadyExists + newName);

        player.setUserName(newName);
        playerRepository.save(player);
    }

    @Override
    @Transactional
    public void deletePlayer(Integer id) {
        Player player = playerRepository.findById(id).orElseThrow(
                () -> new PlayerNotFoundException (Constant.playerNotFound +id));

        playerRepository.deleteById(id);
    }

    @Override
    public double calculateSuccessPercentage(PlayerDTO playerDTO) {
        return 0;
    }

    @Override
    public double calculateAverageSuccessPercentage() {
        return 0;
    }

    @Override
    public List<PlayerDTO> playerRankingList() {
        List<PlayerDTO> players = getAllPlayers();
        return players.stream()
                .filter(player -> player.getPercentageWonGame() != null ) // per a que no tingui en compte jugador amb percentatges nulls
                .sorted(Comparator.comparingDouble(PlayerDTO::getPercentageWonGame).reversed()) // Ordena de major a menor
                .collect(Collectors.toList());

    }
}
