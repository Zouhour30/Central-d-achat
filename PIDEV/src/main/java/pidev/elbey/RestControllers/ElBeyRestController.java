package pidev.elbey.RestControllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.elbey.Entities.Roles;
import pidev.elbey.Entities.User;
import pidev.elbey.Services.ElBeyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/Elbey")
public class ElBeyRestController {
    @Autowired
    ElBeyService elBeyService ;



    @GetMapping("/users")
    List<User> getUsers(){
        return elBeyService.getUsers();
    }

    @PostMapping("/user/add")
    User saveUser(@RequestBody User user){
        return elBeyService.saveUser(user);
    }
    @PostMapping("/role/add")
    Roles saveRole(@RequestBody Roles role){
        return elBeyService.saveRole(role);

    }
    @PostMapping("/role/addtouser")
    void AddRoleToUser(@RequestBody RoleToUserForm form){
        elBeyService.AddRoleToUser(form.getUsername(), form.getRoleName());

    }

    @GetMapping ("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                try {
                    String refreshToken = authorizationHeader.substring("Bearer ".length());
                    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = verifier.verify(refreshToken);
                    String username = decodedJWT.getSubject();
                    User user = elBeyService.getUser(username);
                    String access_token = JWT.create()
                            .withSubject(user.getUsername())
                            .withExpiresAt(new Date(System.currentTimeMillis() +10*60*1000))
                            .withIssuer(request.getRequestURL().toString())
                            .withClaim("roles",user.getRoles().stream().map(Roles::getName).collect(Collectors.toList()))
                            .sign(algorithm);
                    String refresh_token = JWT.create()
                            .withSubject(user.getUsername())
                            .withExpiresAt(new Date(System.currentTimeMillis() +50*60*1000))
                            .withIssuer(request.getRequestURL().toString())
                            .sign(algorithm);
                    Map<String,String> tokens = new HashMap<>();
                    tokens.put("access_token",access_token);
                    tokens.put("refresh_token",refresh_token);
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(),tokens);



                } catch (Exception exception) {

                    response.setHeader("ERROR", exception.getMessage());
                    response.setStatus(FORBIDDEN.value());
                    //response.sendError(FORBIDDEN.value());
                    Map<String, String> error = new HashMap<>();
                    error.put("error_message", exception.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), error);

                }
            } else {
                throw new RuntimeException("refresh token is missing");
            }

    }


    }

@Data
class RoleToUserForm{
    private String username;
    private String roleName;
}
