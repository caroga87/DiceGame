package cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.configuration;

import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.repository.UserRepository;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.utils.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor

public class AppConfig {
    @Autowired
    private UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService (){
        return username -> userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(Constant.USERNAME_NOT_FOUND +username));
    }

    @Bean
    public AuthenticationProvider authenticationProvider (){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder ());
        return authenticationProvider;
    }
    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }



}
