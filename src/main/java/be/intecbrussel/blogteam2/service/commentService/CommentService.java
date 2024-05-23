package be.intecbrussel.blogteam2.service.commentService;

import be.intecbrussel.blogteam2.models.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getAllComments();
    Comment getCommentById(Long id);
    void saveComment(Comment post);
    void deleteCommentById(Long id);
}
