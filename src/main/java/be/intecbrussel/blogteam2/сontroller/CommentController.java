package be.intecbrussel.blogteam2.сontroller;/*
package be.intecbrussel.blogteam2.сontroller;

import be.intecbrussel.blogteam2.models.Comment;
import be.intecbrussel.blogteam2.models.Post;
import be.intecbrussel.blogteam2.models.User;
import be.intecbrussel.blogteam2.service.commentService.CommentServiceImpl;
import be.intecbrussel.blogteam2.service.likeService.LikeServiceImpl;
import be.intecbrussel.blogteam2.service.postService.PostServiceImpl;
import be.intecbrussel.blogteam2.service.userService.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Optional;
@Controller
public class CommentController {
private PostServiceImpl postService;
private CommentServiceImpl commentService;
private UserServiceImpl userService;

@Autowired
public CommentController(PostServiceImpl postService, CommentServiceImpl commentService, UserServiceImpl userService) {
    this.postService = postService;
    this.commentService = commentService;
    this.userService = userService;
}

@GetMapping("/createComment/{post_id}")
public String createComment(@PathVariable Long post_id, Principal principal, Model model){
    Optional<Post> post = Optional.ofNullable(postService.findById(post_id));

    if(post.isPresent()){
        Optional<User> user = userService.findByUserName(principal.getName());
        if(user.isPresent()){
            Comment comment = new Comment();
            comment.setPost(post.get());
            comment.setUser(user.get());
            model.addAttribute("comment", comment);
            return "commentForm";
        }else{
            return "error";
        }
    }else
        return "error";
}

@PostMapping("/saveComment")
public String saveComment(@Valid Comment comment, BindingResult bindingResult){
    if (bindingResult.hasErrors()) {
        return "/commentForm";

    } else {
        commentService.saveComment(comment);
        return "redirect:/post/" + comment.getPost().getId();
    }
}
}

*/


import be.intecbrussel.blogteam2.models.Comment;
import be.intecbrussel.blogteam2.models.Post;
import be.intecbrussel.blogteam2.models.User;
import be.intecbrussel.blogteam2.service.commentService.CommentServiceImpl;
import be.intecbrussel.blogteam2.service.postService.PostServiceImpl;
import be.intecbrussel.blogteam2.service.userService.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Optional;

@Controller
public class CommentController {

    private final PostServiceImpl postService;
    private final CommentServiceImpl commentService;
    private final UserServiceImpl userService;

    @Autowired
    public CommentController(PostServiceImpl postService, CommentServiceImpl commentService, UserServiceImpl userService) {
        this.postService = postService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping("/createComment/{postId}")
    public String createComment(@PathVariable Long postId, Principal principal, Model model) {
        Optional<Post> post = Optional.ofNullable(postService.findById(postId));

        if (post.isPresent()) {
            Optional<User> user = userService.findByUserName(principal.getName());
            if (user.isPresent()) {
                Comment comment = new Comment();
                comment.setPost(post.get());
                comment.setUser(user.get());
                model.addAttribute("comment", comment);
                return "commentForm";
            } else {
                return "error";
            }
        } else {
            return "error";
        }
    }

    @PostMapping("/saveComment")
    public String saveComment(@RequestParam String text, @RequestParam Long postId, Principal principal, Model model) {
        Optional<Post> postOptional = Optional.ofNullable(postService.findById(postId));
        Optional<User> userOptional = userService.findByUserName(principal.getName());

        if (postOptional.isPresent() && userOptional.isPresent()) {
            Comment comment = new Comment();
            comment.setText(text);
            comment.setPost(postOptional.get());
            comment.setUser(userOptional.get());
            commentService.saveComment(comment);

            model.addAttribute("post", postOptional.get());
            return "fragments/post :: comments";
        } else {
            return "error";
        }
    }
}
