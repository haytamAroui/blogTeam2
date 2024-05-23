package be.intecbrussel.blogteam2.Controller;

import be.intecbrussel.blogteam2.models.Comment;
import be.intecbrussel.blogteam2.models.Post;
import be.intecbrussel.blogteam2.repository.CommentRepository;
import be.intecbrussel.blogteam2.service.commentService.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/posts/{postId}/comments")
    public String getAllCommentsByPostId(@PathVariable (value = "postId")
                                                Long postId, Model model) {
        model.addAttribute("comments", commentService.getAllCommentsByPostID(postId));
        return "Post";
    }


    @PostMapping(value = "/addNewComment")
    public String createComment(@ModelAttribute("postId") Comment comment){
        commentService.saveComment(comment);
        return "redirect:/showPost/" + comment.getPost().getId();
    }

    @GetMapping("/post/{postId}/comments/{commentId}")
    public String showUpdatePostForm(@PathVariable(value = "commentId")
                                         Long id, Model model){
        return null;
    }



    @GetMapping("/post/{postId}/comments/{commentId}")
    public String deleteComment(@PathVariable(value = "postId") Long postId,
                                @PathVariable(value = "commentId") Long commentId){
        //commentService.getAllCommentsByPostID(postId, commentId);
        //commentService.deleteCommentById(id);
        return "redirect:/";
    }


}
