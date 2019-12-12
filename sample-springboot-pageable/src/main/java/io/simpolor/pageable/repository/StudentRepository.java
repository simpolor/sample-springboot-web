package io.simpolor.pageable.repository;

import io.simpolor.pageable.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // todo : custom query
    @Query("SELECT s FROM Student s WHERE s.seq = ?1")
    Student selectStudent(long seq);
}
