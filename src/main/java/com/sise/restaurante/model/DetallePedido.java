/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sise.restaurante.model;

/**
 *
 * @author User02
 */
public class DetallePedido {
    private int detalleID;
    private int pedidoID;
    private int platoID;
    private int cantidad;
    private double precioUnitario;
 
        public int getDetalleID() {
        return detalleID;
    }

    public void setDetalleID(int detalleID) {
        this.detalleID = detalleID;
    }

    public int getPedidoID() {
        return pedidoID;
    }

    public void setPedidoID(int pedidoID) {
        this.pedidoID = pedidoID;
    }

    public int getPlatoID() {
        return platoID;
    }

    public void setPlatoID(int platoID) {
        this.platoID = platoID;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
