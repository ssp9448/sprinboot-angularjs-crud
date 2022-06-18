package bvr.com.sprinbootangularjscrud.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bvr.com.sprinbootangularjscrud.model.student;

@Repository
public interface studentRepo extends JpaRepository<student,Integer> {
    
   public student findByemail(String email);
}
