package cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.configuration;

import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.services.JwtService;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.utils.Constant;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {


    private final UserDetailsService userDetailsService;


    private final JwtService jwtService;


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, // lo que el cliente pide en la peticion
                                    @NonNull HttpServletResponse response, // lo que nosotros entregamos al cliente
                                    @NonNull FilterChain filterChain) throws ServletException, IOException { // continua con el proceso de la solicitud
        final String autHeader = request.getHeader(Constant.AUTHORIZATION_HEADER);
        final String jwt;
        final String username;
        if (autHeader == null || !autHeader.startsWith(Constant.BEARER_PREFIX)){ // verifica si envia un jwt (header de autorizacion) o si envia las credenciales empezando por bearer, si no lo hace se le retorna un error 403
            filterChain.doFilter(request, response);
            return; //
        }
        jwt = autHeader.substring(Constant.BEARER_PREFIX.length()); // si envia un jwt, coja a partir del valor 7, sin tener en cuenta la palabra bearer
        username = jwtService.getUsername(jwt);
        if (username !=null && SecurityContextHolder.getContext().getAuthentication() == null){ // valida que el username no sea nulo, y que en la peticion no esté ya autenticado
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username); // va directo a la bd para verificar que existe
            if (jwtService.validateToken (jwt, userDetails)){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }
        }
        filterChain.doFilter(request, response);

    }
}
