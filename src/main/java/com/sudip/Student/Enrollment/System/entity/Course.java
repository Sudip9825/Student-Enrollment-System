package com.sudip.Student.Enrollment.System.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name ="courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="title" , nullable=false)
    private String courseTitle;
    @Column(name="description"  , nullable=false)
    private String description;
    @Column(name="category")
    private String category;
    @Column(name="price" , nullable=false)
    private Long price;
    @Column(name="is_free")
    private  boolean is_free;
    @Column(name="created_at" , nullable=false)
    private LocalDateTime created_at;
    @Column(name="updated_at")
    private LocalDateTime updated_at;
}
