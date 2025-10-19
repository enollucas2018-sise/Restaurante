/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sise.restaurante.dao;
import com.sise.restaurante.model.Usuario;
import javax.swing.JOptionPane;
import java.sql.*;
import com.sise.restaurante.utils.HashUtil;


/**
 *
 * @author User02
 */
public class AutenticadorDao {
    private Connection conexion;
    
    
    public AutenticadorDao(Connection conexion) {
        this.conexion = conexion;
    }
    
   
    public Usuario validarLogin(String userName, String password) {
        // Solo permitir usuario 'admin'
        if (!"admin".equals(userName)) {
            return null;
        }
        
        Usuario usuario = null;
        String sql = "{call sp_VerificarLogin(?, ?)}";
        
        try (CallableStatement stmt = this.conexion.prepareCall(sql)) {
            String passwordHash = HashUtil.generarHashSHA1(password);
            
            stmt.setString(1, userName);
            stmt.setString(2, passwordHash);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                boolean loginExitoso = rs.getBoolean("LoginExitoso");
                
                if (loginExitoso) {
                    usuario = new Usuario(
                        rs.getInt("UsuarioID"),
                        rs.getString("UserName"),
                        rs.getString("NombreCompleto"),
                        rs.getString("Email"),
                        rs.getString("Rol")
                    );
                }
            }
            
        } catch (SQLException e) {
            mostrarError("Error en la autenticación: " + e.getMessage());
            e.printStackTrace();
        }
        
        return usuario;
    }
    
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
