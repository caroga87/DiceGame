package cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String name;
    private String username;
    private String email;
    private String password;
}
