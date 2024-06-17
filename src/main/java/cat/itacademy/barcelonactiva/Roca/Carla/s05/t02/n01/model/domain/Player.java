package cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults (level = AccessLevel.PRIVATE)
@Table (name = "player")
public class Player {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer player_id;

    @Builder.Default
    private String name = "ANONYMOUS";

    @Column (name= "username")
    private String userName;

    @Builder.Default
    @Column (name = "registrationDate")
    private LocalDateTime registrationDate= LocalDateTime.now();

    @Builder.Default
    @OneToMany (mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Game> rollDice= new ArrayList<>();

}
