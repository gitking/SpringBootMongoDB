package org.study.mongodb.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Student {

    private String id;

    private String content;

    private String name;

    private String userId;

    private String visits;
}
