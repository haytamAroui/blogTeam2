package be.intecbrussel.blogteam2.service.likeService;

import be.intecbrussel.blogteam2.models.Like;
import be.intecbrussel.blogteam2.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Override
    public List<Like> getAllLikes() {
        return likeRepository.findAll();
    }

    @Override
    public Like getLikeById(Long id) {
        Optional<Like> likeOptional = likeRepository.findById(id);
        if (likeOptional.isEmpty()) {
            throw new IllegalStateException("Like doesn't exist");
        }
        return likeOptional.get();
    }

    @Override
    public Like saveLike(Like like) {
        return likeRepository.save(like);  // Change: return saved like
    }

    @Override
    public void deleteLikeById(Long id) {
        likeRepository.deleteById(id);
    }
}
