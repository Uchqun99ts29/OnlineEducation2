package com.education.repository;


import com.education.entity.Education;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long>, AbstractRepository {


    //    @Query(value = "from education e where e.id=:id and  e.deleted=0 limit :counts offset :page;", nativeQuery = true)
//    List<Education> getAllByCriteria(Long id, Integer page, Integer counts);
//
    @Modifying
    @Query(value = "update education e set  e.isBlock  = case when e.isBlock=true then false when e.isBlock=false then true else true  end ", nativeQuery = true)
    Void block(@NotNull Long id);

    @Query(value = "select e.* from education e where not e.deleted and e.id=:id", nativeQuery = true)
    Education getNotDelete(@NotNull Long id);

    @Query(value = "select e.* from education e where  not e.deleted  order by e.name limit :page offset :size ;",nativeQuery = true)
    List<Education> getAll(Integer page, Integer size);

//    @Modifying
//    @Query(value = "update Education e set e.deleted=true where not e.deleted and e.id=:id")
//    Void deleted(Long id);
}
