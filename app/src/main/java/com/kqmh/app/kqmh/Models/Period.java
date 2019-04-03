package com.kqmh.app.kqmh.Models;

import com.kqmh.app.kqmh.Utils.MyDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = MyDatabase.class, name = "period")
public class Period extends BaseModel {

    @Column
    @PrimaryKey
    private int id;

    @Column
    private String period;

    @Column
    private boolean isSelected = false;


    public Period(int id, String period) {

        setId(id);
        setPeriod(period);
    }

    public Period() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getName() {
        return this.period;
    }

    public void setName(){}

    @Override
    public String toString() {
        return period;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Period) {
            Period c = (Period) obj;
            if (c.getName().equals(period) && c.getId() == id) return true;
        }

        return false;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}