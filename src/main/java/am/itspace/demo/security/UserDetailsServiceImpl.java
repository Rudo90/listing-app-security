package am.itspace.demo.security;

import am.itspace.demo.model.User;
import am.itspace.demo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
       User user = userRepo.findByUsername(userName).orElseThrow(()->new UsernameNotFoundException("User not found"));
        return new CurrentUser(user);
    }
}
