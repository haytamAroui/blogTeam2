package be.intecbrussel.blogteam2.service.commentService;

import be.intecbrussel.blogteam2.models.Comment;
import be.intecbrussel.blogteam2.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;


    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getCommentById(Long id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if(commentOptional.isEmpty()){
            throw new IllegalStateException("comment doesn't exist");
        }
        return commentOptional.get();
    }

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void deleteCommentById(Long id) {
        commentRepository.deleteById(id);
    }
}
