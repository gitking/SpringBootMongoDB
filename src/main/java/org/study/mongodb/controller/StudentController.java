package org.study.mongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.study.mongodb.dao.IStudentDao;
import org.study.mongodb.pojo.Student;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private IStudentDao studentDao;

    @PostMapping("/save")
    public String save (Student student) {
        studentDao.save(student);
        return "成功";
    }

    @PutMapping("/update")
    public String update (Student student) {
        studentDao.update(student);
        return "成功";
    }

    @GetMapping("/findAll")
    public List<Student> findAll () {
       return studentDao.findAll();
    }

    @DeleteMapping("/delete")
    public String delete (String id) {
        studentDao.delete("1");
        return "成功";
    }

}
