package be.intecbrussel.blogteam2.сontroller;

import be.intecbrussel.blogteam2.models.Comment;
import be.intecbrussel.blogteam2.service.commentService.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String createComment(@ModelAttribute("newComment") Comment newComment){
        commentService.saveComment(newComment);
        return "redirect:/showPost/" + newComment.getPost().getId();
    }

    //For comment's author
    public String UpdateComment(@PathVariable(value = "commentId") Long commentId,
                                @ModelAttribute("updateComment") Comment updateComment) {
        Comment commentToUpdate = commentService.getCommentById(commentId);
        commentToUpdate.setText(updateComment.getText());
        commentService.saveComment(commentToUpdate);
        return "redirect:/showPost/" + updateComment.getPost().getId();
    }

    //For admin and comment's author
    @PostMapping("/post/{postId}/comments/{commentId}")
    public String deleteComment(@PathVariable(value = "commentId") Long commentId){
        commentService.deleteCommentById(commentId);
        return "redirect:/";
    }



    //    @GetMapping("/post/{postId}/comments/{commentId}")
//    public String deleteComment(@PathVariable(value = "postId") Long postId,
//                                @PathVariable(value = "commentId") Long commentId){
//        Comment commentToDelete = commentService.getCommentByPostIdAndCommentId(postId, commentId);
//        commentService.deleteCommentById(commentToDelete.getId());
//        return "redirect:/";
//    }



    //    @GetMapping("/post/{postId}/comments/{commentId}")
//    public String UpdateComment(@PathVariable(value = "postId") Long postId,
//                                     @PathVariable(value = "commentId") Long commentId,
//                                     @ModelAttribute("updateComment") Comment updateComment){
//        Comment commentToUpdate = commentService.getCommentByPostIdAndCommentId(postId, commentId);
//        commentToUpdate.setText(updateComment.getText());
//        commentService.saveComment(commentToUpdate);
//        return "redirect:/showPost/" + updateComment.getPost().getId();
//    }


}