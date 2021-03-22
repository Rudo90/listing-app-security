package am.itspace.demo.service;

import am.itspace.demo.model.User;
import am.itspace.demo.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public User getById(Integer id){
        return userRepo.findById(id).get();
    }

    public void addUser(User user){
        userRepo.save(user);
    }

    public void deleteUser(Integer id){
       userRepo.deleteById(id);
    }

}
