package ru.pfr.model.SnilsXls;

public class SnilsXls {
    private String id;
    private String snils;

    public SnilsXls(String id, String snils) {
        this.id = id;
        this.snils = snils;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSnils() {
        return snils;
    }

    public void setSnils(String snils) {
        this.snils = snils;
    }
}
