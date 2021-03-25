package am.itspace.demo.controller;

import am.itspace.demo.model.AuthenticationRequest;
import am.itspace.demo.model.AuthenticationResponse;
import am.itspace.demo.model.User;
import am.itspace.demo.repositories.UserRepo;
import am.itspace.demo.security.UserDetailsServiceImpl;
import am.itspace.demo.service.UserService;
import am.itspace.demo.util.JwtUtil;
import javassist.bytecode.DuplicateMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final JwtUtil jwtUtil;

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> list = userService.getAllUsers();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        try {
            User user = userService.getById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        Optional<User> byUsername = userRepo.findByUsername(authenticationRequest.getUsername());
        if (byUsername.isPresent()) {
            if (passwordEncoder.matches(authenticationRequest.getPassword(), byUsername.get().getPassword())) {
                UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(authenticationRequest.getUsername());
                String jwt = jwtUtil.generateToken(userDetails);
                return ResponseEntity.ok(new AuthenticationResponse(jwt));
            }
        }
        else {
            throw new DuplicateMemberException("user does not exist!");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorizing does not succeedded");
   }

    @PostMapping()
    public void addUser(@RequestBody User user) throws DuplicateMemberException {
        if (userRepo.findByEmail(user.getEmail()).isEmpty()){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.addUser(user);
        } else {
            throw new DuplicateMemberException ("user already exists!");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Integer id) {
        try {
            User existingUser = userService.getById(id);
            if (existingUser.getId().equals(id)) {
                user.setId(id);
                userService.addUser(user);
            }
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        try {
            userService.deleteUser(id);
            String message = "User was deleted successfully";
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
