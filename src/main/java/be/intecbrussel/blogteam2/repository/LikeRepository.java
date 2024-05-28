package be.intecbrussel.blogteam2.repository;

import be.intecbrussel.blogteam2.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByUserIdAndPostId(Long userId, Long postId);
    Long countByPostId(Long postId);

    List<Like> findAllByPostIdAndUserId(Long postId, Long userId);
}
