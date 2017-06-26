package com.gkuijper.vakantiespreiding;

import java.io.Serializable;

/**
 * Created by Gabrielle on 24-06-17.
 */

public class Tijdvak implements Serializable{
    String region;
    String startdate;
    String enddate;

    public Tijdvak(String region, String startdate, String enddate) {
        this.region = region;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public Tijdvak(){

    }
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    @Override
    public String toString() {
        return "Tijdvak{" +
                "region='" + region + '\'' +
                ", startdate='" + startdate + '\'' +
                ", enddate='" + enddate + '\'' +
                '}';
    }
}
