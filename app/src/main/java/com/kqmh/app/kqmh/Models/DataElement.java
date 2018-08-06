package com.kqmh.app.kqmh.Models;

import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by ekirapa on 8/6/18 .
 */
public class DataElement extends BaseModel {
    private String dataElementId="";
    private String category="";
    private String value="";
    private String comment="";
    private String entity = "";

    public String getDataElementId() {
        return dataElementId;
    }

    public void setDataElementId(String dataElementId) {
        this.dataElementId = dataElementId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }
}
