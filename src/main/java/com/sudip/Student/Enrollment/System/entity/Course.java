package com.sudip.Student.Enrollment.System.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
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
    public Boolean isFree;

    @CreatedDate
    @Column(name="created_at" , nullable=false)
    private LocalDateTime created_at;

    @Column(name="updated_at")
    @LastModifiedDate
    private LocalDateTime updated_at;

    @PrePersist
    @PreUpdate
    private void setIsFreeBasedOnPrice() {
        this.isFree = (this.price == null || this.price == 0);
    }

}
