package am.itspace.demo.repositories;

import am.itspace.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
    Optional<User> findByPassword(String password);
    Optional<User> findByEmail(String email);

}
