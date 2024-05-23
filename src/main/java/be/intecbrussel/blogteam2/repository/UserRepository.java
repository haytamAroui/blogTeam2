package be.intecbrussel.blogteam2.repository;

import be.intecbrussel.blogteam2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
