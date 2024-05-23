package be.intecbrussel.blogteam2.сontroller;

import be.intecbrussel.blogteam2.models.Like;
import be.intecbrussel.blogteam2.service.likeService.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    @Autowired
    private LikeService likeService;

    // Add a like to a post or comment
    @PostMapping
    public ResponseEntity<Like> addLike(@RequestBody Like like) {
        Like savedLike = likeService.saveLike(like);
        return ResponseEntity.ok(savedLike);
    }

    // Remove a like
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeLike(@PathVariable(value = "id") Long likeId) {
        likeService.deleteLikeById(likeId);
        return ResponseEntity.noContent().build();
    }
}
