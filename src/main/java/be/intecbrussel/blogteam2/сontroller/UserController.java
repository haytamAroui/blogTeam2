package be.intecbrussel.blogteam2.Controller;

import be.intecbrussel.blogteam2.exception.EmailAlreadyExistsException;
import be.intecbrussel.blogteam2.exception.ResourceNotFoundException;
import be.intecbrussel.blogteam2.models.User;
import be.intecbrussel.blogteam2.repository.UserRepository;
import be.intecbrussel.blogteam2.service.userService.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController

//@Controller
//@RequestMapping(path ="/user")

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

   // @GetMapping(path ="{userId}")

    @GetMapping("/user/{userId}")

    public ResponseEntity<?> getUserWithId(@PathVariable Long userId)
    {
        Optional<User> userOptional = userRepository.findById(userId);
        try
        {
            if(userOptional.isPresent())
            {  return new ResponseEntity<>(userOptional,HttpStatus.OK);}
            else
            {
            throw new ResourceNotFoundException("userId " + userId + " not found");}
        }
        catch (ResourceNotFoundException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user)
    {
        try
        {
            if (userService.isEmailUnique(user))
            {
                return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
            }
            else
            {
                return new ResponseEntity<>("User creation failed.", HttpStatus.BAD_REQUEST);
            }
        }
        catch (EmailAlreadyExistsException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @Valid @RequestBody User userRequest) {
        try {
            return userRepository.findById(userId)
                    .map(user -> {
                                if (!userService.isEmailUnique(user))
                                {
                                    throw new EmailAlreadyExistsException("User with email " + userRequest.getEmail() + " already exists.");
                                }


                                // Update user details
                                    user.setFirstName(userRequest.getFirstName());
                                    user.setLastName(userRequest.getLastName());
                                    user.setEmail(userRequest.getEmail());
                                    user.setStreet(userRequest.getStreet());
                                    user.setHouseNo(userRequest.getHouseNo());
                                    user.setCity(userRequest.getCity());
                                    user.setZip(userRequest.getZip());

                                    return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);

                            }
                    )
                    .orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
        }
        catch (ResourceNotFoundException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (EmailAlreadyExistsException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


        catch (Exception e)
        {
            return new ResponseEntity<>("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        try {

            return userRepository.findById(userId)
                    .map(user ->
                    {
                        userRepository.delete(user);
                        return ResponseEntity.ok().build();
                    })
                    .orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }







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
