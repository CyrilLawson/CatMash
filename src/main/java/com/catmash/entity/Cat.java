package com.catmash.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CATS")
@NoArgsConstructor
@Data
public class Cat {
    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "URL")
    private String url;
    @Column(name = "SCORE")
    private int score = 0;
}
