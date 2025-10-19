/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sise.restaurante.dao;
import com.sise.restaurante.model.Pedido;
import com.sise.restaurante.model.DetallePedido;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User02
 */
public class PedidoDao {
       private Connection conexion;

    public PedidoDao(Connection conexion) {
        this.conexion = conexion;
    }

    
    public boolean insertarPedido(Pedido pedido, List<DetallePedido> detalles) throws SQLException {
        String sqlPedido = "INSERT INTO Pedidos (Fecha, Mesa, Estado, Total, UsuarioID) VALUES (?, ?, ?, ?, ?)";
        String sqlDetalle = "INSERT INTO DetallePedido (PedidoID, PlatoID, Cantidad, PrecioUnitario) VALUES (?, ?, ?, ?)";

        try {
            conexion.setAutoCommit(false);

            try (PreparedStatement stmtPedido = conexion.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS)) {
                stmtPedido.setTimestamp(1, Timestamp.valueOf(pedido.getFecha()));
                stmtPedido.setString(2, pedido.getMesa());
                stmtPedido.setString(3, pedido.getEstado());
                stmtPedido.setDouble(4, pedido.getTotal());
                stmtPedido.setInt(5, pedido.getUsuarioID());
                stmtPedido.executeUpdate();

                ResultSet rs = stmtPedido.getGeneratedKeys();
                if (rs.next()) {
                    int pedidoID = rs.getInt(1);

                    for (DetallePedido d : detalles) {
                        try (PreparedStatement stmtDetalle = conexion.prepareStatement(sqlDetalle)) {
                            stmtDetalle.setInt(1, pedidoID);
                            stmtDetalle.setInt(2, d.getPlatoID());
                            stmtDetalle.setInt(3, d.getCantidad());
                            stmtDetalle.setDouble(4, d.getPrecioUnitario());
                            stmtDetalle.executeUpdate();
                        }
                    }
                }

                conexion.commit();
                return true;
            } catch (SQLException e) {
                conexion.rollback();
                throw e;
            }
        } finally {
            conexion.setAutoCommit(true);
        }
    }

    // Listar pedidos
    public List<Pedido> listarPedidos() {
        List<Pedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM Pedidos ORDER BY Fecha DESC";

        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Pedido p = new Pedido();
                p.setPedidoID(rs.getInt("PedidoID"));
                p.setFecha(rs.getTimestamp("Fecha").toLocalDateTime());
                p.setMesa(rs.getString("Mesa"));
                p.setEstado(rs.getString("Estado"));
                p.setTotal(rs.getDouble("Total"));
                p.setUsuarioID(rs.getInt("UsuarioID"));
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    
    public boolean actualizarEstado(int pedidoID, String nuevoEstado) {
        String sql = "UPDATE Pedidos SET Estado = ? WHERE PedidoID = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, nuevoEstado);
            stmt.setInt(2, pedidoID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    } 
}
