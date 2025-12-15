package com.sudip.Student.Enrollment.System.repository;

import com.sudip.Student.Enrollment.System.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<User> findById(Integer id);
    Optional<User> findByEmail(String email);

    //query to select all when id deleted is false
    @Query("SELECT u FROM User u WHERE u.isDeleted = false")
    Page<User> findAll(Pageable pageable);


    @Query("""
                SELECT u FROM User u
                WHERE (:keyword IS NULL OR LOWER(u.username) LIKE LOWER(CONCAT('%', :keyword, '%')))
                   OR (:keyword IS NULL OR LOWER(u.email) LIKE LOWER(CONCAT('%', :keyword, '%')))
            """)
    Page<User> searchUser(String keyword, Pageable pageable);


}
