package com.teamponytta.aidapp.model;

public class PrimerosAuxilios {

    private int idPa;
    private String nombre;
    private String descripcion;
    private int paPhoto;

    public PrimerosAuxilios() {
    }

    public PrimerosAuxilios(int idPa, String nombre, String descripcion, int paPhoto) {
        this.idPa = idPa;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.paPhoto = paPhoto;
    }

    public int getIdPa() {
        return idPa;
    }

    public void setIdPa(int idPa) {
        this.idPa = idPa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPaPhoto() {
        return paPhoto;
    }

    public void setPaPhoto(int paPhoto) {
        this.paPhoto = paPhoto;
    }
}
