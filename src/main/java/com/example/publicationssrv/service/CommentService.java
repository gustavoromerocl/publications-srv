package com.example.publicationssrv.service;

import com.example.publicationssrv.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> getAllComments();
    Optional<Comment> getCommentById(Long id);
    Comment createComment(Comment comment);
    Comment updateComment(Long id, Comment comment);
    void deleteComment(Long id);
}
