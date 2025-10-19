/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sise.restaurante.dao;
import com.sise.restaurante.model.Plato;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author User02
 */
public class PlatoDao {
        private Connection conexion;

    public PlatoDao(Connection conexion) {
        this.conexion = conexion;
    }

    public List<Plato> listarPlatos() {
        List<Plato> lista = new ArrayList<>();
        String sql = "SELECT * FROM Platos";

        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Plato p = new Plato();
                p.setPlatoID(rs.getInt("PlatoID"));
                p.setNombre(rs.getString("Nombre"));
                p.setDescripcion(rs.getString("Descripcion"));
                p.setPrecio(rs.getDouble("Precio"));
                p.setCategoria(rs.getString("Categoria"));
                p.setActivo(rs.getBoolean("Activo"));
                p.setFechaCreacion(rs.getDate("FechaCreacion").toLocalDate());
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean insertarPlato(Plato plato) {
        String sql = "INSERT INTO Platos (Nombre, Descripcion, Precio, Categoría, Activo, FechaCreacion) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, plato.getNombre());
            stmt.setString(2, plato.getDescripcion());
            stmt.setDouble(3, plato.getPrecio());
            stmt.setString(4, plato.getCategoria());
            stmt.setBoolean(5, plato.isActivo());
            stmt.setDate(6, Date.valueOf(plato.getFechaCreacion()));
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
