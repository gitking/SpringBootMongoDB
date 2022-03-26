package org.study.mongodb.dao;

import org.study.mongodb.pojo.Student;

import java.util.List;

public interface IStudentDao {

    void save (Student student);

    void update (Student student);

    List<Student> findAll();

    void delete (String id);
}
