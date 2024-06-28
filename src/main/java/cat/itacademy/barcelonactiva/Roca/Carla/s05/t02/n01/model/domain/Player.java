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
    private Integer playerId;

    @Builder.Default
    @Column (name= "username")
    private String username= "ANONYMOUS";

    @Builder.Default
    @Column (name = "registration_date", nullable = false, updatable = false)
    private LocalDateTime registrationDate= LocalDateTime.now();

    @Builder.Default
    @OneToMany (mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Game> rollDice= new ArrayList<>();


}
