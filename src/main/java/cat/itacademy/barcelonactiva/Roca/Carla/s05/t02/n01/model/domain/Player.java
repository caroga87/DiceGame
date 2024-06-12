package cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table (name = "player")
public class Player {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer player_Id;

    private String name;

    @Column (name= "userName")
    private String userName;

    @Column (name = "registrationDate")
    private LocalDateTime registrationDate;

    @OneToMany (mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Game> rollDice;

    @Builder
    public Player (){
        this.name = "ANONYMOUS";
        this.registrationDate = LocalDateTime.now();
        this.rollDice = new ArrayList<>();
    }



}
