package am.itspace.demo.services;

import am.itspace.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> getAllUsers();

    User getById(Integer id);

    User addUser(User user);

    void deleteUser(Integer id);
}
