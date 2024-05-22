package be.intecbrussel.blogteam2.repository;

import be.intecbrussel.blogteam2.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment , Long> {
}
