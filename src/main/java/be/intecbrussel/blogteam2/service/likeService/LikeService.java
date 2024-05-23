package be.intecbrussel.blogteam2.service.likeService;

import be.intecbrussel.blogteam2.models.Like;

import java.util.List;

public interface LikeService {

    List<Like> getAllLikes();
    Like getLikeById(Long id);
    void saveLike(Like like);
    void deleteLikeById(Long id);
}
