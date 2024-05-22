package be.intecbrussel.blogteam2.repository;

import be.intecbrussel.blogteam2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
