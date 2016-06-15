package com.form.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class MyData {

    @Id
    @GeneratedValue
    protected Integer id;
    protected String name;

    public MyData(){
        super();
    }

    public MyData(String name){
        super();
        this.name = name;
    }


    public String toString(){
        return "[name:" + name + "]";
    }
}