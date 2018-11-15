package com.kqmh.app.kqmh.Models;

import com.kqmh.app.kqmh.Utils.MyDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = MyDatabase.class, name = "facility")
public class FacilityUnit extends BaseModel {
    @Column
    private String county_id;

    @Column
    private String id;

    @Column
    private String name;

    @Column
    private boolean isSelected = false;


    @PrimaryKey(autoincrement = true)
    private long facility_id;

    public FacilityUnit(){

    }

    public FacilityUnit(long facility_id, String county_id, String id, String name){
        this.facility_id=facility_id;
        this.county_id=county_id;
        this.id=id;
        this.name=name;
    }

    public FacilityUnit(String id, String name){
        this.id=id;
        this.name=name;
    }

    public String getCounty_id() {
        return county_id;
    }

    public void setCounty_id(String county_id) {
        this.county_id = county_id;
    }

    public long getFacility_id() {
        return facility_id;
    }

    public void setFacility_id(long facility_id) {
        this.facility_id = facility_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
