package com.kqmh.app.kqmh.Models;

import com.kqmh.app.kqmh.Utils.MyDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = MyDatabase.class, name = "dataSet")
public class DataSet extends BaseModel {

        @Column
        @PrimaryKey
        private String id;

        @Column
        private String displayName;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = "TA4FU3zu93l";
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = "KQMH(Checklist for Assessing Quality of Care)";
        }

        public String getName() {
            return this.displayName;
        }
    }

