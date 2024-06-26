package be.intecbrussel.blogteam2.repository;

import be.intecbrussel.blogteam2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByUserName(String username);
    void deleteUserByUserName(String username);
//**********************************************************************************************
    boolean existsByEmail(String email);


}
