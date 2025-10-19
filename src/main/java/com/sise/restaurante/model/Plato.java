/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sise.restaurante.model;

import java.time.LocalDate;

/**
 *
 * @author User02
 */
public class Plato {
    private int platoID;
    private String nombre;
    private String descripcion;
    private double precio;
    private String categoria;
    private boolean activo;
    private LocalDate fechaCreacion;

    // Constructor vacío
    public Plato() {}

    // Constructor completo
    public Plato(int platoID, String nombre, String descripcion, double precio, String categoria, boolean activo, LocalDate fechaCreacion) {
        this.platoID = platoID;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
    }

    // Getters y setters
    public int getPlatoID() { return platoID; }
    public void setPlatoID(int platoID) { this.platoID = platoID; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public LocalDate getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDate fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}
