package ru.pfr.xmlparsertemp;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Xml10kModel {
    private String surname;
    private String name;
    private String middlename;
    private String snils;

    public Xml10kModel() {
    }

    public Xml10kModel(String surname, String name, String middlename, String snils) {
        this.surname = surname;
        this.name = name;
        this.middlename = middlename;
        this.snils = snils;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getSnils() {
        return snils;
    }

    public void setSnils(String snils) {
        this.snils = snils;
    }
}
