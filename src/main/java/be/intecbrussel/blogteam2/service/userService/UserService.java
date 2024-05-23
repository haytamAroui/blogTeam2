package be.intecbrussel.blogteam2.service.userService;

import be.intecbrussel.blogteam2.models.User;

import java.util.List;

public interface UserService {

    List<User> getAllUser();

    User getUserById(Long id);

    void saveUser (User user);

    void deleteUserById(Long id);

}
