package com.example.publicationssrv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.publicationssrv.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    
}
