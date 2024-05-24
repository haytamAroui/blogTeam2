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
    public List<Comment> getAllCommentsByPostID(Long id) {
        return commentRepository.findAll()
                .stream()
                .filter(com -> com.getPost().getId().equals(id))
                .toList();
    }

    @Override
    public Comment getCommentByPostIdAndCommentId(Long postId, Long commentId) {
        Optional<Comment> commentOptional = commentRepository.findByIdAndPostId(postId, commentId);
        if(commentOptional.isEmpty()){
            throw new IllegalStateException("comment doesn't exist");
        }
        return commentOptional.get();
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

    @Override
    public List<Comment> findByPostId(Long Id) {
        return commentRepository.findAll()
                .stream()
                .filter(com -> com.getPost().getId().equals(Id))
                .toList();
    }


}