package org.study.mongodb.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.study.mongodb.dao.IStudentDao;
import org.study.mongodb.pojo.Student;

import java.util.List;

@Component
public class StudentDaoImpl implements IStudentDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 往MongoDB里面插入一条文档
     * @param student
     */
    @Override
    public void save(Student student) {
        mongoTemplate.save(student);
    }

    @Override
    public void update(Student student) {
        //更新的条件,更新哪些数据
        Query query = new Query(Criteria.where("_id").is(student.getId()));

        Update update = new Update();
        update.set("content", student.getContent());
        update.set("userid", student.getUserId());
        update.set("name", student.getName());
        update.set("visits", student.getVisits());

        mongoTemplate.updateFirst(query, update, Student.class);
    }

    @Override
    public List<Student> findAll() {
        return mongoTemplate.findAll(Student.class);
    }

    @Override
    public void delete(String id) {
        Student student = mongoTemplate.findById(id, Student.class);

        //注意remove方法的参数是一个文档对象,参数类似是Object,虽然也能传String id，但是不要传，应该传一个文档对象Student进去
        mongoTemplate.remove(student);
    }
}
