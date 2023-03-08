package pidev.elbey.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pidev.elbey.Entities.User;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);


    @Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);


    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.PasswordToken = ?1")
    public Optional<User> findByPasswordToken(String token);
    @Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
    public Optional<User> findByVerificationCode(String code);


    boolean existsByEmail(String email);
}
