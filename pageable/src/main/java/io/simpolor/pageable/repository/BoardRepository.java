package io.simpolor.pageable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import io.simpolor.pageable.repository.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
