package ru.pfr.model;

public abstract  class Views {

    public abstract  String getCSV();

    //для деления строки на имя фамилию и отчество
    protected String splitFio(String fio){
        while (fio.contains("  ")) {
            fio = fio.replaceAll("  ", " ");
        }
        String[] fioSplitted = fio.split("\\s");
        if (fioSplitted.length == 3) {
            return fio.replaceAll("\\s", ";");
        } else
        if (fioSplitted.length == 2) {
            return fioSplitted[0] + ";" + fioSplitted[1] + ";";
        } else
        if (fioSplitted.length > 3) {
            for (int i = 3; i < fioSplitted.length; i++) {
                fioSplitted[2] += " " + fioSplitted[i];
            }
            return fioSplitted[0] + ";" + fioSplitted[1] + ";" + fioSplitted[2].trim();
        }
        return ";;";
    }

}
