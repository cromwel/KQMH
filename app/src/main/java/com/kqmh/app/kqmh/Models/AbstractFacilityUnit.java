package com.kqmh.app.kqmh.Models;

import com.kqmh.app.kqmh.Utils.MyDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = MyDatabase.class, name = "facilityUnits")
public class AbstractFacilityUnit extends BaseModel {

    @Column
    @PrimaryKey
    private String facilityID;
    @Column
    private String facility;
    @Column
    private String ward;
    @Column
    private String wardID;
    @Column
    private String subCounty;
    @Column
    private String subCountyID;
    @Column
    private String county;
    @Column
    private String countyID;


   /* public AbstractFacilityUnit(String facilityID, String facility, String ward, String wardID, String subCounty, String subCountyID, String county, String countyID){
        this.facilityID=facilityID;
        this.facility=facility;
        this.ward=ward;
        this.wardID=wardID;
        this.subCounty=subCounty;
        this.subCountyID=subCountyID;
        this.county=county;
        this.countyID=countyID;
    }
*/

    public String getFacilityID() {
        return facilityID;
    }

    public void setFacilityID(String facilityID) {
        this.facilityID = facilityID;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getWardID() {
        return wardID;
    }

    public void setWardID(String wardID) {
        this.wardID = wardID;
    }

    public String getSubCounty() {
        return subCounty;
    }

    public void setSubCounty(String subCounty) {
        this.subCounty = subCounty;
    }

    public String getSubCountyID() {
        return subCountyID;
    }

    public void setSubCountyID(String subCountyID) {
        this.subCountyID = subCountyID;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCountyID() {
        return countyID;
    }

    public void setCountyID(String countyID) {
        this.countyID = countyID;
    }


    public void setFacility() {
    }

    public void setFacilityID() {
    }

    public void setWard() {
    }

    public void setWardID() {
    }

    public void setSubCounty() {
    }

    public void setSubCountyID() {
    }

    public void setCountyID() {
    }

    public void setCounty() {
    }
}

