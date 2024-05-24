package be.intecbrussel.blogteam2.Controller;

import be.intecbrussel.blogteam2.exception.ResourceNotFoundException;
import be.intecbrussel.blogteam2.models.User;
import be.intecbrussel.blogteam2.repository.UserRepository;
import be.intecbrussel.blogteam2.service.userService.UserService;
import be.intecbrussel.blogteam2.service.userService.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/users")
    public Page<User> getAllUsers(Pageable pageable)
    {
        return userRepository.findAll(pageable);
    }

    @GetMapping("/user/{userId}")
    public User getUserWithId(@PathVariable Long userId)
    {
        Optional<User> userOptional = userRepository.findById(userId);
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("userId " + userId + " not found"));

    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user)
    {

        return userRepository.save(user);

    }


  /*  @PutMapping("/user/{userId}")
    public User updateUser(@PathVariable Long userId, @Valid @RequestBody User userRequest)
    {
        return userRepository.findById(userId).map(user ->
        {
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            user.setStreet(userRequest.getStreet());
            user.setHouseNo(userRequest.getHouseNo());
            user.setCity(userRequest.getCity());
            user.setZip(userRequest.getZip());
            return userRepository.save(user);})

                .orElseThrow(() ->
                        new ResourceNotFoundException("UserId " + userId + " not found"));
    }*/

    @PutMapping("/user/{userId}")
    public User updateUser(@PathVariable Long userId, @Valid @RequestBody User userRequest) {
        return userRepository.findById(userId).map(user -> {
            // Check if the email is being changed and if the new email is already taken
            if (!user.getEmail().equals(userRequest.getEmail()) && userRepository.existsByEmail(userRequest.getEmail())) {
                //throw new IllegalArgumentException("Email already taken");
                //throw new ResourceNotFoundException("Email already taken");
                System.out.println("Email already taken");
            }

            // Update user details
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            user.setEmail(userRequest.getEmail());
            user.setStreet(userRequest.getStreet());
            user.setHouseNo(userRequest.getHouseNo());
            user.setCity(userRequest.getCity());
            user.setZip(userRequest.getZip());
            return userRepository.save(user);
        })
                .orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }

    @DeleteMapping("/users/{userId}") public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return userRepository.findById(userId).map(user -> { userRepository.delete(user);
            return ResponseEntity.ok().build(); }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found")); }












    @GetMapping("/")
    public String index()
    {
        return "index";
    }
    @GetMapping("/login")
    public String login()
    {
        return "Sing_in_page";
    }
    @GetMapping("/register")
    public String register()
    {
        return "Register_new_user";
    }



}
