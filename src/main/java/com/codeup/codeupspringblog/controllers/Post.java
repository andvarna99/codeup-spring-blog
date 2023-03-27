package com.codeup.codeupspringblog.controllers;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 250, nullable = false)
    private String title;

    @Column(length = 1050, nullable = false)
    private String body;

}
