package pidev.elbey.userDto;


//import centraldachat.entity.Roles;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import pidev.elbey.Entities.Roles;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsersDTO implements Serializable {

  private Long id;
  private String email;
  private String password;
  private String FirstName;
  private String LastName;
  private String Mobile;
  private String verificationCode;
  private String PasswordToken;
  //private Role role;
 //private Set<Roles> roles;
  private boolean enabled;
}
