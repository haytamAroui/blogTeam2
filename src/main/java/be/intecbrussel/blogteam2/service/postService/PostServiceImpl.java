package be.intecbrussel.blogteam2.service.postService;

import be.intecbrussel.blogteam2.models.Comment;
import be.intecbrussel.blogteam2.models.Post;
import be.intecbrussel.blogteam2.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostId(Long id) {
        Optional<Post> postOptional = postRepository.findById(id);
        if(postOptional.isEmpty()){
            throw new IllegalStateException("post doesn't exist");
        }
        return postOptional.get();
    }

    @Override
    public void savePost(Post post) {
        postRepository.save(post);
    }

    @Override
    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }
}
