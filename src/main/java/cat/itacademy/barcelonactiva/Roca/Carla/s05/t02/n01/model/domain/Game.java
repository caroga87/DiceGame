package cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;

@Data
@Entity
@Table (name = "game")
@NoArgsConstructor
@AllArgsConstructor
@Builder (builderMethodName = "rollDice")
public class Game {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer gameId;

    @Column (name= "first_die")
    private int die1;

    @Column (name = "second_die")
    private int die2;

    private boolean win;

    @ManyToOne
    @JoinColumn (name= "playerId", nullable = false)
    private Player player;


    //metode per crear una jugada, al main es cridaria com a Game game = Game.CREATE_GAME(player)
    public static Game createGame(Player player) {
        Random random = new Random();
        int die1 = random.nextInt(6) + 1;
        int die2 = random.nextInt(6) + 1;
        boolean win = (die1 + die2 == 7);
        return Game.rollDice()
                .die1(die1)
                .die2 (die2)
                .win(win)
                .player(player)
                .build();


    }

}
