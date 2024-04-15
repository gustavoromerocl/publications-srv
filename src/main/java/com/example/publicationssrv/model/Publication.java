package com.example.publicationssrv.model;

import java.util.List;
import java.util.stream.Collectors;

import com.example.publicationssrv.Comment;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "publication")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "publication_date")
    private String publicationDate;

    @Column(name = "contain")
    private String contain;

    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comment> comments;

    public List<CommentDTO> getCommentDTOs() {
        if (comments != null) {
            return comments.stream()
                    .map(comment -> new CommentDTO(comment.getCommentText()))
                    .collect(Collectors.toList());
        }
        return null;
    }
}
