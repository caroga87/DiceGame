package cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Random;

@Data
@Entity
@Table (name = "game")
public class Game {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer game_Id;

    @Column (name= "first dice")
    private int die1 = (new Random()).nextInt(6)+1;

    @Column (name = "second dice")
    private int die2 = (new Random()).nextInt(6)+1;

    private boolean win = (die1 + die2 == 7);

    @ManyToOne
    @JoinColumn (name= "player_Id")
    private Player player;

    @Builder (builderMethodName = "rollDice")
    public Game (Player player) {
        Random random = new Random();
        this.die1 = random.nextInt(6) +1;
        this.die2 = random.nextInt(6) +1;
        this.win = this.die1 + this.die2 ==7;
        this.player = player;

    }

}
