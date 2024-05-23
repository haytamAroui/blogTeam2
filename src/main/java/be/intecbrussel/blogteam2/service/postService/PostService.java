package be.intecbrussel.blogteam2.service.postService;

import be.intecbrussel.blogteam2.models.Post;

import java.util.List;

public interface PostService {

    List<Post> getAllPosts();
    Post getPostId(Long id);
    Post savePost(Post post); // Change: method should return Post
    void deletePostById(Long id);
}
