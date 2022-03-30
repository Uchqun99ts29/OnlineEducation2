package com.education.repository;

import com.education.entity.AuthUser;
import com.education.entity.Payload;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PayloadRepository extends JpaRepository<Payload, Long>, AbstractRepository {

    Optional<List<Payload>> findAllByStudentAndDeletedFalse(AuthUser student);

    Optional<List<Payload>> findAllByDeletedFalse(PageRequest request);
//
//    //    @Query("select ")
//    Optional<List<Payload>> findAllByEducation(Long id);
}
