package am.itspace.demo.serviceImpl;

import am.itspace.demo.model.User;
import am.itspace.demo.repositories.UserRepo;
import am.itspace.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public User getById(Integer id){
        return userRepo.findById(id).get();
    }

    public User addUser(User user){
       return userRepo.save(user);
    }

    public void deleteUser(Integer id){
       userRepo.deleteById(id);
    }

}
