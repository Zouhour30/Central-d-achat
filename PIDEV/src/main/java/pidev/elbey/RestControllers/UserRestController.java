package pidev.elbey.RestControllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidev.elbey.Entities.Orders;
import pidev.elbey.Entities.Roles;
import pidev.elbey.Entities.User;
import pidev.elbey.Services.UserService;
import pidev.elbey.userDto.UsersDTO;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/Elbey")
public class   UserRestController {
    public final static String FOUND = "FOUND";
    public final static String BAD_REQUEST = "BAD_REQUEST";
    public final static String NOT_FOUND = "NOT_FOUND";

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    UserService userService;



    // Get the list of all users
    @GetMapping("/users")
    List<User> getUsers(){
        return userService.getUsers();
    }

    // Register a new user
    @PostMapping("/user/register")
    User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    // Update a new user
    @PutMapping("/user/update/{userId}")
    public User UpdateUser(@PathVariable("userId") Long userId, @RequestBody User user) {
        return userService.UpdateUser(user,userId);
    }
    // delete a user
    @DeleteMapping("/user/delete")
    public void delete(@RequestBody User u){
        userService.delete(u);}
    @PostMapping("/role/add")
    Roles saveRole(@RequestBody Roles role){
        return userService.saveRole(role);

    }
    // authorize user
    @PostMapping("/role/authorizeuser")
    void AddRoleToUser(@RequestBody RoleToUserForm form){
        userService.AddRoleToUser(form.getUsername(), form.getRoleName());

    }
    // Get order history for a user
    @GetMapping("/user/{userId}/orders")
    public List<Orders> getOrderHistory(@PathVariable("userId") Long userId) {
        return userService.getOrderHistory(userId);
    }
    // Change user password



    // Get the refresh token
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
                    User user = userService.getUser(username);
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

    @PostMapping("/oauth/forgetPassword")

    public ResponseEntity<Object> forgetPassword(@RequestBody UsersDTO usersDTO) throws UnsupportedEncodingException, MessagingException {

        User userReq = modelMapper.map(usersDTO, User.class);
        userService.sendPassMail(userReq.getEmail());

        return new ResponseEntity<>(FOUND, HttpStatus.OK);
    }

    @PostMapping("/oauth/resetPassword/{token}")

    public ResponseEntity<Object> resetPass(@PathVariable String token, @RequestBody UsersDTO usersDTO) {
        User userReq = modelMapper.map(usersDTO, User.class);
        System.out.println(token+ "  "+ userReq.getPassword());
        userService.verifyPassToken(token,userReq.getPassword());
        return new ResponseEntity<>(FOUND, HttpStatus.OK);





    }



    }

@Data
class RoleToUserForm{
    private String username;
    private String roleName;
}
