package org.study.mongodb.app;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.HashMap;
import java.util.Map;

/**
 * B站学习视频:
 * https://www.bilibili.com/video/BV1xJ411y7PU?spm_id_from=333.999.0.0 《Java操作MongoDB》
 * Up主:学IT的小王子
 * 使用SpringData-JPA操作MongoDB
 * https://blog.csdn.net/feinifi/article/details/83024898
 * MongoRepository
 * 使用SpringBoot操作MongoDB
 * 使用SpringData-JPA操作MongoDB
 * @since 2022年3月6日13:44:40
 */
public class Demo {
    public static void main(String[] args) {
        //创建MongoDB的连接，如果MongoDB服务端没有设置登录验证的话就可以这样连接
        MongoClient client = new MongoClient("192.168.126.154");
        //获取操作的数据库
        MongoDatabase studentdb = client.getDatabase("studentdb");
        //获取集合(collections)
        MongoCollection<Document> student = studentdb.getCollection("student");

        FindIterable<Document> documents = student.find();

        for (Document document : documents) {
            System.out.println(document.get("content"));
        }

        client.close();//关闭MongoDB客户端连接
    }

    /**
     * 按照条件进行查询
     */
    public void findByContion () {
        //创建MongoDB的连接，如果MongoDB服务端没有设置登录验证的话就可以这样连接
        MongoClient client = new MongoClient("192.168.126.154");
        //获取操作的数据库
        MongoDatabase studentdb = client.getDatabase("studentdb");
        //获取集合(collections)
        MongoCollection<Document> student = studentdb.getCollection("student");

        //按照条件进行查询where
        BasicDBObject bsonContion = new BasicDBObject("userid", "1013");

        /**
         * BasicDBObject的构造方法支持传入一个Map,并且支持多条件查询,
         * 相当于 where 1 = 1 and 2 = 2
         */
        Map<String, Object> map = new HashMap<>();
        map.put("userid", "1013");
        map.put("name", "rock");
        BasicDBObject bsonContionMap = new BasicDBObject(map);

        FindIterable<Document> documents = student.find(bsonContion);
        //first()只查询一条数据
        //Document document = student.find(bsonContion).first();

        for (Document document : documents) {
            System.out.println(document.get("content"));
        }

        client.close();//关闭MongoDB客户端连接
    }

    /**
     * 按照条件进行查询二:Filters.eq
     */
    public void findByContionByFilters () {
        //创建MongoDB的连接，如果MongoDB服务端没有设置登录验证的话就可以这样连接
        MongoClient client = new MongoClient("192.168.126.154");
        //获取操作的数据库
        MongoDatabase studentdb = client.getDatabase("studentdb");
        //获取集合(collections)
        MongoCollection<Document> student = studentdb.getCollection("student");

        /**
         * find方法还支持传入一个Bson对象
         */
        Bson bson = Filters.eq("userid","1013");
        FindIterable<Document> documents = student.find(bson);
        //first()只查询一条数据
        //Document document = student.find(bsonContion).first();

        for (Document document : documents) {
            System.out.println(document.get("content"));
        }

        client.close();//关闭MongoDB客户端连接
    }

    /**
     * 插入文档
     */
    public void insertDemo () {
        //创建MongoDB的连接，如果MongoDB服务端没有设置登录验证的话就可以这样连接
        MongoClient client = new MongoClient("192.168.126.154");
        //获取操作的数据库
        MongoDatabase studentdb = client.getDatabase("studentdb");
        //获取集合(collections)
        MongoCollection<Document> student = studentdb.getCollection("student");

        Map<String, Object> map = new HashMap<>();
        map.put("_id", 5);
        map.put("content", "今天天气还算是不错的");
        map.put("userid", "1018");
        map.put("name", "qingmu");
        map.put("visits", 200);

        Document document = new Document(map);

        //插入数据
        student.insertOne(document);

        client.close();//关闭MongoDB客户端连接
    }

    /**
     * 插入文档
     */
    public void deleteDemo () {
        //创建MongoDB的连接，如果MongoDB服务端没有设置登录验证的话就可以这样连接
        MongoClient client = new MongoClient("192.168.126.154");
        //获取操作的数据库
        MongoDatabase studentdb = client.getDatabase("studentdb");
        //获取集合(collections)
        MongoCollection<Document> student = studentdb.getCollection("student");

        Bson bson = Filters.eq("_id",5);

        //删除匹配到的第一条数据
        student.deleteOne(bson);
        //student.deleteMany(bson);删除多条数据，删除匹配到的所有数据
        System.out.println("删除成功");

        client.close();//关闭MongoDB客户端连接
    }

    /**
     * 更新文档
     */
    public void updateDemo () {
        //创建MongoDB的连接，如果MongoDB服务端没有设置登录验证的话就可以这样连接
        MongoClient client = new MongoClient("192.168.126.154");
        //获取操作的数据库
        MongoDatabase studentdb = client.getDatabase("studentdb");
        //获取集合(collections)
        MongoCollection<Document> student = studentdb.getCollection("student");

        Bson bson = Filters.eq("_id","4");
        Document document = new Document("$set", new Document("content", "每天坚持上车"));

        //更新匹配到的第一条数据
        student.updateOne(bson, document);

        System.out.println("更新成功");

        client.close();//关闭MongoDB客户端连接
    }
}
