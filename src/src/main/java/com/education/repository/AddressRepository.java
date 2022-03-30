package com.education.repository;

import com.education.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AddressRepository extends JpaRepository<Address, Long>, AbstractRepository {

}
