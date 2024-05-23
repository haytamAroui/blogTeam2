package be.intecbrussel.blogteam2.service.userService;

import be.intecbrussel.blogteam2.models.User;
import be.intecbrussel.blogteam2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        return null;
    }

    @Override
    public User getUserByUserName(String username) {
        return null;
    }

    @Override
    public void saveUser(User user) {

    }

    @Override
    public void deleteUserById(long id) {

    }

    @Override
    public void deleteUserByUserName(String username) {

    }
}
