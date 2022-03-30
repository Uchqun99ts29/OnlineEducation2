package com.education.repository;


import com.education.entity.Education;
import com.education.entity.Group;
import com.education.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long>, AbstractRepository {

    boolean existsByNameAndEducation(String name, Education education);

    Optional<Group> findByIdAndEducationAndDeletedFalse(Long aLong, Education education);

}
