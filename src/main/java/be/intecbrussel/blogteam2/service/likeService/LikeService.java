package be.intecbrussel.blogteam2.service.likeService;

import be.intecbrussel.blogteam2.models.Like;

import java.util.List;

public interface LikeService {

    List<Like> getAllLikes();
    Like getLikeById(Long id);
    Like saveLike(Like like);  // Change: The method should return Like
    void deleteLikeById(Long id);
}
