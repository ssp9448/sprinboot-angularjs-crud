package bvr.com.sprinbootangularjscrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import bvr.com.sprinbootangularjscrud.model.student;
import bvr.com.sprinbootangularjscrud.service.studentService;

@RestController
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://127.0.0.1:5501"})
public class studentCtrl {
    @Autowired
    private studentService service;
    
    @GetMapping(value = "/")
    public List<student> findAll(){
        return service.findAll();
    }
    @GetMapping(value = "/{id}")
    public student findbyId(@PathVariable("id") int id){
         return service.findbyId(id);
    }
    @DeleteMapping(value = "/{id}")
    public void deletebyId(@PathVariable("id") int id){
      service.deletebyId(id);
   }
   @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
   public void createUser(@RequestBody final student s) {
    service.save(s);
   }
   @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
   public void updateUser(@PathVariable("id") int id,@RequestBody final student s) {

    if(service.findbyId(id).getId()==s.getId())
    service.save(s);
   }
}
