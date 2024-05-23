package be.intecbrussel.blogteam2.repository;

import be.intecbrussel.blogteam2.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
}
