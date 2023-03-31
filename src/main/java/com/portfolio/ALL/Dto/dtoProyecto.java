/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.ALL.Dto;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author llopi
 */
public class dtoProyecto {
    @NotBlank
    private String nombre;
    @NotBlank
    private String descripcion;
    @NotBlank
    private String imgp;
    //Constructores

    public dtoProyecto() {
    }

    public dtoProyecto(String nombre, String descripcion, String imgp) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imgp = imgp;
    }
    //Getters & Setters

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

    public String getImgp() {
        return imgp;
    }

    public void setImgp(String imgp) {
        this.imgp = imgp;
    }
    
    
}

