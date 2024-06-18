package cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.controllers;

import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.dto.GameDTO;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.services.GameService;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.services.PlayerService;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (Constant.home)
public class DiceGameController {
    @Autowired
    private  PlayerService playerService;
    @Autowired
    private  GameService gameService;

    @PostMapping
    public ResponseEntity <PlayerDTO> createPlayer (@RequestBody PlayerDTO playerDTO){
        PlayerDTO createdPlayer = playerService.createPlayer(playerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlayer);
    }

    @PutMapping (Constant.update)
    public ResponseEntity <Void> updatePlayerName (@PathVariable Integer id, @RequestBody String newName){
        playerService.updatePlayerName(id, newName);
        return ResponseEntity.noContent().build(); // ResponseEntity.status (HttpStatus.OK).body(
    }

    @PostMapping(Constant.createGame)
    public ResponseEntity<GameDTO> createGame(@PathVariable Integer id) {
        GameDTO createdGame = gameService.createGame(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGame);
    }

    @DeleteMapping(Constant.deleteGame)
    public ResponseEntity<Void> deleteGamesByPlayerId(@PathVariable Integer id) {
        gameService.deleteGameById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> getAllPlayers() {
        List<PlayerDTO> players = playerService.getAllPlayers();
        return ResponseEntity.ok(players);
    }

    @GetMapping(Constant.playerGame)
    public ResponseEntity<List<GameDTO>> getGamesByPlayerId(@PathVariable Integer id) {
        List<GameDTO> games = gameService.getGamesById(id);
        return ResponseEntity.ok(games);
    }

    @GetMapping(Constant.ranking)
    public ResponseEntity<Double> getAverageSuccessRate() {
        double averageSuccessRate = playerService.getAllPlayersAverage();
        return ResponseEntity.ok(averageSuccessRate);
    }

    @GetMapping(Constant.worstplayer)
    public ResponseEntity<PlayerDTO> getWorstPlayer() {
        PlayerDTO worstPlayer = playerService.getWorstPlayerDTO();
        return ResponseEntity.ok(worstPlayer);
    }

    @GetMapping(Constant.bestPlayer)
    public ResponseEntity<PlayerDTO> getBestPlayer() {
        PlayerDTO bestPlayer = playerService.getBestPlayerDTO();
        return ResponseEntity.ok(bestPlayer);
    }
}
