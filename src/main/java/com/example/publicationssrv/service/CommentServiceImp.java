package com.example.publicationssrv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.publicationssrv.Comment;
import com.example.publicationssrv.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImp implements CommentService{
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Long id, Comment comment) {
        if(commentRepository.existsById(id)){
            comment.setId(id);
            return commentRepository.save(comment);
        } else {
            return null;
        }
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
