package com.kqmh.app.kqmh.Models;

import com.kqmh.app.kqmh.Utils.MyDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by ekirapa on 10/23/18 .
 */
@Table(database = MyDatabase.class, name = "progress")

public class AssesmentProgress extends BaseModel {
    @Column
    @PrimaryKey
    private String assessment;
    @Column
    private int progress = 0;
    @Column
    private String displayName;
    @Column
    private int max = 0;

    public AssesmentProgress() {
    }

    public AssesmentProgress(String assessment, String displayName, int progress, int max) {
        this.assessment = assessment;
        this.progress = progress;
        this.displayName = displayName;
        this.max = max;
    }

    public String getAssessment() {
        return assessment;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
