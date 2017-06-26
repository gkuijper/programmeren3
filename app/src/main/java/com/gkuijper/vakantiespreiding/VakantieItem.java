package com.gkuijper.vakantiespreiding;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Gabrielle on 24-06-17.
 */

public class VakantieItem implements Serializable {
    public String name;
    public boolean compulsorydates;
    public ArrayList<Tijdvak> Tijdvak;

    public VakantieItem(String name, boolean compulsorydates, ArrayList<com.gkuijper.vakantiespreiding.Tijdvak> tijdvak) {
        this.name = name;
        this.compulsorydates = compulsorydates;
        Tijdvak = tijdvak;
    }

    public VakantieItem() {

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompulsorydates() {
        return compulsorydates;
    }

    public void setCompulsorydates(boolean compulsorydates) {
        this.compulsorydates = compulsorydates;
    }

    public ArrayList<com.gkuijper.vakantiespreiding.Tijdvak> getTijdvak() {
        return Tijdvak;
    }

    public void setTijdvak(ArrayList<com.gkuijper.vakantiespreiding.Tijdvak> tijdvak) {
        Tijdvak = tijdvak;
    }

    @Override
    public String toString() {
        return "VakantieItem{" +
                "name='" + name + '\'' +
                ", compulsorydates=" + compulsorydates +
                ", Tijdvak=" + Tijdvak +
                '}';
    }

}
