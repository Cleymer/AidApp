package com.teamponytta.aidapp.model;

public class Botiquin {

    private int idElementoB;
    private String nombre;
    private String desc;
    private int bPhoto;

    public Botiquin() {
    }

    public Botiquin(int idElementoB, String nombre, String desc, int bPhoto) {
        this.idElementoB = idElementoB;
        this.nombre = nombre;
        this.desc = desc;
        this.bPhoto = bPhoto;
    }

    public int getIdElementoB() {
        return idElementoB;
    }

    public void setIdElementoB(int idElementoB) {
        this.idElementoB = idElementoB;
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

    public int getbPhoto() {
        return bPhoto;
    }

    public void setbPhoto(int bPhoto) {
        this.bPhoto = bPhoto;
    }
}
