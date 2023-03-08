package pidev.elbey.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pidev.elbey.Entities.Orders;
import pidev.elbey.Entities.Roles;
import pidev.elbey.Entities.User;

import pidev.elbey.Registration.Token.ConfirmationTokenService;
import pidev.elbey.Repositories.OrdersRepo;
import pidev.elbey.Repositories.RolesRepo;
import pidev.elbey.Repositories.UserRepo;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class UserService implements IUserService, UserDetailsService {

    private final ConfirmationTokenService confirmationTokenService;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    UserRepo userRepo;
    @Autowired
    RolesRepo rolesRepo;
    @Autowired
    OrdersRepo ordersRepo;
    @Autowired
    private JavaMailSender mailSender;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userRepo.findByUsername(username);
        if(user==null){
            log.error("user not found in the database");
        }
        else
        {
            log.info("user found in the database: {}",username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(roles -> {
            authorities.add(new SimpleGrantedAuthority(roles.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }

    public String signUpUser(User appUser) {
        boolean userExists = userRepo
                .findByEmail(appUser.getEmail())
                .isPresent();

        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = passwordEncoder
                .encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        userRepo.save(appUser);

        String token = UUID.randomUUID().toString();

       /* ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

       confirmationTokenService.saveConfirmationToken(
                confirmationToken);*/

//        TODO: SEND EMAIL

        return token;
    }

    public int enableAppUser(String email) {
        return userRepo.enableAppUser(email);
    }
    @Override
    public User saveUser(User user) {
         user.setPassword(passwordEncoder.encode(user.getPassword()));
         return userRepo.save(user);
    }

    // Get user by ID
    public User getUserById(Long userId) {
        return userRepo.findById(userId).get();}

    @Override
    public User UpdateUser(User user , Long userId) {

            User userToUpdate = getUserById(userId);
            userToUpdate.setFirstName(user.getFirstName());
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setAdresse(user.getAdresse());
            userToUpdate.setPhoneNumber(user.getPhoneNumber());
            return userRepo.save(userToUpdate);

    }

    @Override
    public void delete(User u) {
        userRepo.delete(u);
    }

    @Override
    public Roles saveRole(Roles role) {
        return rolesRepo.save(role);
    }

    @Override
    public void AddRoleToUser(String username, String roleName) {
        User user = userRepo.findByUsername(username);
        Roles role= rolesRepo.findByName(roleName);
        user.getRoles().add(role);
    }


    @Override
    public List<Orders> getOrderHistory(Long userId) {
        User user = getUserById(userId);
        return ordersRepo.findByUser(user);
    }

    // Change user password
    public void changePassword(Long userId, String newPassword) {
        User userToUpdate = getUserById(userId);
        userToUpdate.setPassword(newPassword);
        userRepo.save(userToUpdate);
    }
    @Override
    public User getUser(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    public void forgetPassword(String token, String newPassword) throws UnsupportedEncodingException, MessagingException {
        User user = userRepo.findByPasswordToken(token).get();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        user.setPasswordToken(null);
//        usersRepository.save(user.get());
    }

    public void sendPassMail(String userEmail) throws MessagingException, UnsupportedEncodingException {

        String toAddress = userEmail;
        String fromAddress = "doulakamel@gmail.com";
        String senderName = "RE-XPERT";
        String subject = "Your RE-Xpert password";
        String content = "Dear Mr/Madame, did you want to reset your password ? "
                + "Someone (hopefully you) has asked us to reset the password for your<br>"
                + "account. Please click the button below to do so. If you didn't<br>"
                + "request this password reset, you can go ahead and ignore this email!"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">RESET PASSWORD</a></h3>"
                + "Thank you,<br>"
                + "RE-XPERT.";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        String token = UUID.randomUUID().toString();

        String verifyURL = "http://localhost:1111/oauth/resetPassword/" + token;

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);


        mailSender.send(message);

        Optional<User> user = userRepo.findByEmail(userEmail);


        user.get().setPasswordToken(token);
        userRepo.save(user.get());


    }

    private void sendVerificationEmail(User user) throws MessagingException, UnsupportedEncodingException {

        String toAddress = user.getEmail();
        String fromAddress = "doulakamel@gmail.com";
        String senderName = "RE-Xpert";
        String subject = "Please verify your registration";
        String content = "Dear Mr/Madame,<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "RE-Xpert.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        String verifyURL = "http://localhost:8085/oauth/verify/" + user.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);

        System.out.println("Email has been sent");
    }
    public boolean existsEmail(String email) {

        return userRepo.existsByEmail(email);
    }

    public ResponseEntity<User> verify(String verificationCode) {
        if (verificationCode == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<User> user = userRepo.findByVerificationCode(verificationCode);

        if (user.isPresent()) {
            user.get().setVerificationCode(null);
            user.get().setEnabled(true);
            userRepo.save(user.get());
            return ResponseEntity.ok(user.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    public void verifyPassToken(String token,String newPassword) {

        Optional<User> user = userRepo.findByPasswordToken(token);


        String encodedPassword = passwordEncoder.encode(newPassword);
        user.get().setPassword(encodedPassword);

        userRepo.save(user.get());




    }





}
