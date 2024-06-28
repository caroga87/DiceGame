package cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PlayerDTO {

    private Integer id;
    private String username;
    private List<GameDTO> rollDice;
    private Double percentageWonGame;

    public void calculationPercentageWin () {
        percentageWonGame = rollDice.isEmpty() ? 0 : Math.round((percentageWonGame()) * 100.0)/100.0;
    }

    private double percentageWonGame() {
        return wonGameQuantity()*100/gameRounds();
    }
    private double wonGameQuantity() {
        return (double) rollDice.stream().filter(GameDTO::isWonGame).count();
    }
    private int gameRounds () {
        return rollDice.size();
    }
}
