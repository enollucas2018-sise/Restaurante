/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sise.restaurante.dao;
import com.sise.restaurante.model.DetallePedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author User02
 */
public class DetallePedidoDao {
    private Connection conexion;

    public DetallePedidoDao(Connection conexion) {
        this.conexion = conexion;
    }

    // Listar detalles por PedidoID
    public List<DetallePedido> listarPorPedido(int pedidoID) {
        List<DetallePedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM DetallePedido WHERE PedidoID = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, pedidoID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                DetallePedido d = new DetallePedido();
                d.setDetalleID(rs.getInt("DetalleID"));
                d.setPedidoID(rs.getInt("PedidoID"));
                d.setPlatoID(rs.getInt("PlatoID"));
                d.setCantidad(rs.getInt("Cantidad"));
                d.setPrecioUnitario(rs.getDouble("PrecioUnitario"));
                lista.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Eliminar detalles por PedidoID (por si se cancela el pedido)
    public boolean eliminarPorPedido(int pedidoID) {
        String sql = "DELETE FROM DetallePedido WHERE PedidoID = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, pedidoID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    } 
}
