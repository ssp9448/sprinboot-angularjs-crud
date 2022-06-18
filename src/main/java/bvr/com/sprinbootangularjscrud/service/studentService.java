package bvr.com.sprinbootangularjscrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bvr.com.sprinbootangularjscrud.dao.studentRepo;
import bvr.com.sprinbootangularjscrud.model.student;

@Service
public class studentService {
    @Autowired
    private studentRepo dao;

    public void save(student s){
        dao.save(s);
    }
    public student findbyId(int id){
        return dao.findById(id).get();
    }
    public List<student> findAll(){
        return dao.findAll();
    }
    public void deletebyId(int id){
        student s=dao.findById(id).get();
        dao.delete(s);
    }
        
        
}
