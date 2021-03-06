package io.simpolor.graphql.repository;

import io.simpolor.graphql.repository.entity.ReportCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportCardRepository extends JpaRepository<ReportCard, Long> {

}
