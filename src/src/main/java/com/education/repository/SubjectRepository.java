package com.education.repository;

import com.education.entity.Education;
import com.education.entity.Subject;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>, AbstractRepository {

    boolean existsByNameAndEducation(String name, Education education);

    Optional<Subject> findByIdAndEducationAndDeletedFalse(Long aLong, Education education);

    List<Subject> findAllByEducationAndDeletedFalse(Education education);

    List<Subject> findAllByDeletedFalse(PageRequest request);
}
