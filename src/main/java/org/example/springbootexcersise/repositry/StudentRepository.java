package org.example.springbootexcersise.repositry;
import org.example.springbootexcersise.Entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface StudentRepository extends JpaRepository<Students, Long> {

        boolean existsByEmail(String email);

        boolean existsByEmailAndIdNot(String email, Long id);

        Optional<Students> findByEmail(String email);
    }

