package com.kqmh.app.kqmh.Models;

public class Option {
    private String name;
    private String code;
    private String id;

    public Option(String id,String code, String name){
        this.id=id;
        this.code=code;
        this.name=name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
