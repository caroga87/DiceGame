package cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.service.impl;

import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.exception.PlayerNotFoundException;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.exception.UsernameAlreadyExistsException;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.mapper.PlayerMapper;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.domain.Player;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.repository.GameRepository;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.repository.PlayerRepository;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.repository.UserRepository;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.service.PlayerService;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.utils.Constant;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerMapper playerMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;

    @Override
    public PlayerDTO createPlayer(PlayerDTO playerDTO)  {
        if (playerRepository.existsByUsername(playerDTO.getUsername())) {
            throw new UsernameAlreadyExistsException(Constant.USERNAME_ALREADY_EXISTS + playerDTO.getUsername());
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
        Player player = playerRepository.findById(id).orElseThrow(() -> new PlayerNotFoundException(Constant.PLAYER_NOT_FOUND +id));
        return playerMapper.toPlayerDTO(player);
    }

    @Override
    @Transactional // per si hi ha un error a l'hora d'executar els canvis, farà rollback per tal de que no es realitzin canvis parcials a la bd
    public void updatePlayerUsername(Integer id, String newUsername){

        Player player = playerRepository.findById(id).orElseThrow(
                () -> new PlayerNotFoundException (Constant.PLAYER_NOT_FOUND +id));

        if (playerRepository.findByUsernameIgnoreCase(newUsername).isPresent())
            throw new UsernameAlreadyExistsException(Constant.USERNAME_ALREADY_EXISTS + newUsername);

        player.setUsername(newUsername);
        playerRepository.save(player);
    }

    @Override
    @Transactional
    public void deletePlayer(Integer id) {
        Player player = playerRepository.findById(id).orElseThrow(
                () -> new PlayerNotFoundException (Constant.PLAYER_NOT_FOUND +id));

        playerRepository.deleteById(id);
    }

    @Override
    public List<PlayerDTO> playerRankingList() {
        List<PlayerDTO> players = getAllPlayers();
        return players.stream()
                .filter(player -> player.getPercentageWonGame() != null) // per a que no tingui en compte jugador amb percentatges nulls
                .sorted(Comparator.comparingDouble(PlayerDTO::getPercentageWonGame).reversed()) // Ordena de major a menor
                .collect(Collectors.toList());
    }

    @Override
    public Double getAllPlayersAverage () {
        List <PlayerDTO> players = getAllPlayers();
        if (players.isEmpty()){
            return 0.0;
        }
        return   players.stream()
                .mapToDouble(player -> player.getPercentageWonGame() != null ? player.getPercentageWonGame() : 0.0)
                .average()
                .orElse(0.0);

    }

    @Override
    public PlayerDTO getBestPlayerDTO () {
        return playerRankingList().get(0);
    }
    @Override
    public PlayerDTO getWorstPlayerDTO () {
        return playerRankingList().get(playerRankingList().size()-1);
    }
}
