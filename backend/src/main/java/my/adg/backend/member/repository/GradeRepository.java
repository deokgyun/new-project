package my.adg.backend.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import my.adg.backend.member.domain.Grade;

public interface GradeRepository extends JpaRepository<Grade, Long> {

}
