package com.javyhuerta.crud.services;

import com.javyhuerta.crud.entities.Student;
import com.javyhuerta.crud.repositories.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    IStudentRepository studentRepo;

    public Student saveStudent(Student student){
        return studentRepo.save(student);
    }

    public Page<Student> allStudent(Integer page, Integer size, Boolean enablePagination){
        return studentRepo.findAll(enablePagination ? PageRequest.of(page,size): Pageable.unpaged());
    }

    public void delete(Long id){
        studentRepo.deleteById(id);
    }

    public Student update(Long id,Student student){
        Optional<Student> std = studentRepo.findById(id);
        if(std.isEmpty()){
           return null;
        }

        std.get().setAge(student.getAge());
        std.get().setLastName(student.getLastName());
        std.get().setName(student.getName());
        return studentRepo.save(std.get());
    }

}
