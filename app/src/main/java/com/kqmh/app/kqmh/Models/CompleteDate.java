package com.kqmh.app.kqmh.Models;

import com.kqmh.app.kqmh.Utils.MyDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = MyDatabase.class, name = "completeDate")
public class CompleteDate extends BaseModel {

    @Column
    @PrimaryKey
    private String id;

    @Column
    private String completeDate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(String completeDate) {
        this.completeDate = completeDate;
    }

    public String getName() {
        return this.completeDate;
    }
}