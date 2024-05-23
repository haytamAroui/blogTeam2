package be.intecbrussel.blogteam2.service.postService;

import be.intecbrussel.blogteam2.models.Post;
import be.intecbrussel.blogteam2.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> getAllPosts() {
        return List.of();
    }

    @Override
    public Post getPostId(Long id) {
        return null;
    }

    @Override
    public void savePost(Post post) {

    }

    @Override
    public void deletePostById(Long id) {

    }
}
