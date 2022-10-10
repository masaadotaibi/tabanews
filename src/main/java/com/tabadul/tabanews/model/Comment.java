package com.tabadul.tabanews.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "COMMENT")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "comment", nullable = false)
    private String commentText;

    @CreatedDate
    private LocalDateTime createdDate;

    @OneToMany
    @JoinColumn(name = "username_id")
    @CreatedBy
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User username;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Article article;

}
