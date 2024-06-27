package cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.controller;

import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.exception.NoPermissionsException;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.dto.GameDTO;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.service.GameService;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.service.PlayerService;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (Constant.HOME)
public class DiceGameController {
    @Autowired
    private  PlayerService playerService;
    @Autowired
    private  GameService gameService;

    @PostMapping (Constant.CREATE_PLAYER)
    public ResponseEntity <PlayerDTO> createPlayer (@RequestBody PlayerDTO playerDTO){
        PlayerDTO createdPlayer = playerService.createPlayer(playerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlayer);
    }

    @PutMapping (Constant.UPDATE)
    public ResponseEntity <Void> updatePlayerName (@PathVariable Integer playerId, @RequestBody String newName){
        playerService.updatePlayerUsername(playerId, newName);
        return ResponseEntity.noContent().build(); // ResponseEntity.status (HttpStatus.OK).body(
    }

    @PostMapping(Constant.CREATE_GAME)
    public ResponseEntity<GameDTO> createGame(@PathVariable Integer playerId) {
        GameDTO createdGame = gameService.createGame(playerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGame);
    }

    @DeleteMapping(Constant.DELETE_GAME)
    public ResponseEntity<?> deleteGamesByPlayerId(@PathVariable Integer playerId, Authentication authentication) {
        if (!authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            throw new NoPermissionsException(Constant.FORBIDDEN);
        }
        gameService.deleteGameById(playerId);
        return new ResponseEntity<>("Games deleted.", HttpStatus.OK);
    }
    @DeleteMapping(Constant.DELETE_PLAYER)
    public ResponseEntity<?> deletePlayerById(@PathVariable Integer playerId, Authentication authentication) {
        if (!authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            throw new NoPermissionsException(Constant.FORBIDDEN);
        }
        playerService.deletePlayer(playerId);
        return new ResponseEntity<>("Player deleted.", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> getAllPlayers() {
        List<PlayerDTO> players = playerService.getAllPlayers();
        return ResponseEntity.ok(players);
    }

    @GetMapping(Constant.PLAYER_GAME)
    public ResponseEntity<List<GameDTO>> getGamesByPlayerId(@PathVariable Integer playerId) {
        List<GameDTO> games = gameService.getGamesById(playerId);
        return ResponseEntity.ok(games);
    }

    @GetMapping(Constant.AVERAGE_SUCCESS)
    public ResponseEntity<Double> getAverageSuccessRate() {
        double averageSuccessRate = playerService.getAllPlayersAverage();
        return ResponseEntity.ok(averageSuccessRate);
    }

    @GetMapping(Constant.RANKING)
    public ResponseEntity<List<PlayerDTO>> getPlayerRankingList() {
        List<PlayerDTO> playerRankingList = playerService.playerRankingList();
        return ResponseEntity.ok(playerRankingList);
    }


    @GetMapping(Constant.WORST_PLAYER)
    public ResponseEntity<PlayerDTO> getWorstPlayer() {
        PlayerDTO worstPlayer = playerService.getWorstPlayerDTO();
        return ResponseEntity.ok(worstPlayer);
    }

    @GetMapping(Constant.BEST_PLAYER)
    public ResponseEntity<PlayerDTO> getBestPlayer() {
        PlayerDTO bestPlayer = playerService.getBestPlayerDTO();
        return ResponseEntity.ok(bestPlayer);
    }
}
