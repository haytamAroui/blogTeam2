package be.intecbrussel.blogteam2.сontroller;

import be.intecbrussel.blogteam2.models.Post;
import be.intecbrussel.blogteam2.service.postService.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // Get all posts
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    // Get a single post by ID
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable(value = "id") Long postId) {
        Post post = postService.getPostId(postId);
        return ResponseEntity.ok().body(post);
    }

    // Create a new post
    @PostMapping
    public Post createPost(@Validated @RequestBody Post post) {
        postService.savePost(post);
        return post;
    }

    // Update an existing post
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable(value = "id") Long postId,
                                           @Validated @RequestBody Post postDetails) {
        Post post = postService.getPostId(postId);

        post.setTitle(postDetails.getTitle());
        post.setDescription(postDetails.getDescription());
        post.setContent(postDetails.getContent());
        post.setLikes(postDetails.getLikes());
        post.setUser(postDetails.getUser());

        final Post updatedPost = postService.savePost(post);
        return ResponseEntity.ok(updatedPost);
    }

    // Delete a post
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable(value = "id") Long postId) {
        postService.deletePostById(postId);
        return ResponseEntity.noContent().build();
    }
}
