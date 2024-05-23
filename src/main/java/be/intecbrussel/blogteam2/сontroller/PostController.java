package be.intecbrussel.blogteam2.сontroller;

import be.intecbrussel.blogteam2.models.Post;
import be.intecbrussel.blogteam2.service.postService.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // Get all posts
    @GetMapping
    public String getAllPosts(Model model) {
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "posts";
    }

    // Get a single post by ID
    @GetMapping("/{id}")
    public String getPostById(@PathVariable(value = "id") Long postId, Model model) {
        Post post = postService.getPostId(postId);
        model.addAttribute("post", post);
        return "post";
    }

    // Create a new post (form)
    @GetMapping("/create")
    public String showCreatePostForm(Model model) {
        model.addAttribute("post", new Post());
        return "create_post";
    }

    // Handle post creation
    @PostMapping
    public String createPost(@Validated @ModelAttribute Post post) {
        postService.savePost(post);
        return "redirect:/posts";
    }

    // Update an existing post (form)
    @GetMapping("/update/{id}")
    public String showUpdatePostForm(@PathVariable(value = "id") Long postId, Model model) {
        Post post = postService.getPostId(postId);
        model.addAttribute("post", post);
        return "update_post";
    }

    // Handle post update
    @PostMapping("/update/{id}")
    public String updatePost(@PathVariable(value = "id") Long postId, @Validated @ModelAttribute Post postDetails) {
        Post post = postService.getPostId(postId);

        post.setTitle(postDetails.getTitle());
        post.setDescription(postDetails.getDescription());
        post.setContent(postDetails.getContent());
        post.setLikes(postDetails.getLikes());
        post.setUser(postDetails.getUser());

        postService.savePost(post);
        return "redirect:/posts";
    }

    // Delete a post
    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable(value = "id") Long postId) {
        postService.deletePostById(postId);
        return "redirect:/posts";
    }
}
