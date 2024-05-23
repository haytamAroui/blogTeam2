package be.intecbrussel.blogteam2.service.userService;

import be.intecbrussel.blogteam2.models.User;

import java.util.List;

public interface UserService {

    List<User> getAllUser();

    User getUserById(long id);

    User getUserByUserName(String username);

    void saveUser (User user);

    void deleteUserById(long id);

    void deleteUserByUserName(String username);

}
