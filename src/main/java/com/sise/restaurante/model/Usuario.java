/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sise.restaurante.model;

/**
 *
 * @author User02
 */
public class Usuario {
  private int usuarioID;
    private String userName;
    private String nombreCompleto;
    private String email;
    private String rol;
    private boolean activo;
    
    
    public Usuario() {}
    
    public Usuario(int usuarioID, String userName, String nombreCompleto, String email, String rol) {
        this.usuarioID = usuarioID;
        this.userName = userName;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.rol = rol;
    }
    
   
    public int getUsuarioID() { return usuarioID; }
    public void setUsuarioID(int usuarioID) { this.usuarioID = usuarioID; }
    
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    
    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
    
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }  
}
