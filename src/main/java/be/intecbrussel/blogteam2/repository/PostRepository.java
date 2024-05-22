package be.intecbrussel.blogteam2.repository;

import be.intecbrussel.blogteam2.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
