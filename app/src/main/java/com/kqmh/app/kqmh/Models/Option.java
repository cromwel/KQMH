package com.kqmh.app.kqmh.Models;

import com.kqmh.app.kqmh.Utils.MyDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = MyDatabase.class, name = "option")
public class Option extends BaseModel {
    @Column
    private String parentId;

    @Column
    private String name;

    @Column
    private String code;

    @Column
    private boolean isSelected = false;

    @Column
    private String id;


    @PrimaryKey(autoincrement = true)
    private long option_id;

    public Option(){

    }
    public Option(String parentId, String id,String code, String name){
        this.parentId = parentId;
        this.id=id;
        this.code=code;
        this.name=name;

    }
    public Option(String id,String code, String name){
        this.id=id;
        this.code=code;
        this.name=name;

    }

    public long getOption_id() {
        return option_id;
    }

    public void setOption_id(long option_id) {
        this.option_id = option_id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
