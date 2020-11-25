package com.teamponytta.aidapp.model;

public class Pastilla {

    private int idPastilla;
    private String nombre;
    private String desc;
    private String category;

    public Pastilla() {
    }

    public Pastilla(int idPastilla, String nombre, String desc, String category) {
        this.idPastilla = idPastilla;
        this.nombre = nombre;
        this.desc = desc;
        this.category = category;
    }

    public int getIdPastilla() {
        return idPastilla;
    }

    public void setIdPastilla(int idPastilla) {
        this.idPastilla = idPastilla;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
