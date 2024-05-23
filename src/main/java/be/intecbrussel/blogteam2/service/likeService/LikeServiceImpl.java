package be.intecbrussel.blogteam2.service.likeService;

import be.intecbrussel.blogteam2.models.Like;
import be.intecbrussel.blogteam2.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl implements LikeService{

    @Autowired
    private LikeRepository likeRepository;

    @Override
    public List<Like> getAllLikes() {
        return List.of();
    }

    @Override
    public Like getLikeById() {
        return null;
    }

    @Override
    public void saveLike() {

    }

    @Override
    public void deleteLikeById(Long id) {

    }
}
