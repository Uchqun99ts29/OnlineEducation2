package com.education.repository;

import com.education.entity.AuthUser;
import com.education.entity.Daily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
@EnableJpaRepositories
public interface DailyRepository extends JpaRepository<Daily,Long>,AbstractRepository {

    @Modifying
    @Transactional
    @Query("update Daily d set d.deleted=true where d.id=:id")
    void deleteSoft(Long id);

    List<Daily> findAllByStudentEducationIdAndDeleted(Long id,Boolean deleted);

    List<Daily> findAllByTimeAndDeleted(Date date, Boolean deleted);
}