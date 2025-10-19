/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.sise.restaurante.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import com.sise.restaurante.config.DatabaseAccess;
import javax.swing.table.TableColumn;

/**
 *
 * @author User02
 */
public class GeneraPedidoUI extends javax.swing.JInternalFrame {

    /**
     * Creates new form GeneraPedidoUI
     */
    public GeneraPedidoUI() {
        initComponents();
        tblPlatos.addPropertyChangeListener(evt -> {
        if ("tableCellEditor".equals(evt.getPropertyName())) {
        if (!tblPlatos.isEditing()) {
            calcularTotal();
        }
    }
});

        cargarPlatos();
        configurarEventos();
        lblTotal.setText("Total: S/ 0.00");


    }
    private void cargarPlatos() {
     try {
       
        Connection conexion = DatabaseAccess.getConnection();
        Statement stmt = conexion.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT PlatoID, Nombre, Precio FROM Platos WHERE Activo = 1");

DefaultTableModel modelo = new DefaultTableModel(
    new Object[]{"ID", "Nombre", "Precio", "Cantidad"}, 0
) {
    @Override
    public boolean isCellEditable(int row, int column) {
        return column == 3; // solo la columna cantidad editable
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> Integer.class;
            case 1 -> String.class;
            case 2 -> Double.class;
            case 3 -> Integer.class;
            default -> Object.class;
        };
    }

        };

        while (rs.next()) {
            modelo.addRow(new Object[]{
            rs.getInt("PlatoID"),
            rs.getString("Nombre"),
            rs.getDouble("Precio"),
            0 
            });

        }

        tblPlatos.setModel(modelo);
       

        TableColumn cantidadColumn = tblPlatos.getColumnModel().getColumn(3);
        cantidadColumn.setCellEditor(new DefaultCellEditor(new JTextField()) {
        @Override
        public Object getCellEditorValue() {
        String value = ((JTextField) getComponent()).getText().trim();
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
        }
});
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar platos: " + e.getMessage());
    }
    }    
    private double calcularTotal() {
        if (tblPlatos.isEditing()) {
        tblPlatos.getCellEditor().stopCellEditing();
    }

       double total = 0.0;
    for (int i = 0; i < tblPlatos.getRowCount(); i++) {
        Object precioObj = tblPlatos.getValueAt(i, 2);
        Object cantidadObj = tblPlatos.getValueAt(i, 3);

        double precio = 0.0;
        int cantidad = 0;

        try {
            precio = precioObj instanceof Number
                ? ((Number) precioObj).doubleValue()
                : Double.parseDouble(precioObj.toString());

            cantidad = cantidadObj instanceof Number
                ? ((Number) cantidadObj).intValue()
                : Integer.parseInt(cantidadObj.toString().trim());
        } catch (Exception ex) {
            continue; // ignorar fila inválida
        }

        if (cantidad > 0) {
            total += precio * cantidad;
        }
    }

    lblTotal.setText(String.format("Total: S/ %.2f", total));
    return total;

    }
    
        private void configurarEventos() {
            
        btnCancelar.addActionListener(e -> dispose());
        btnConfirmar.addActionListener(e -> {
        if (tblPlatos.isEditing()) {
        tblPlatos.getCellEditor().stopCellEditing();
        }
   

        

    double total = calcularTotal();
    String mesa = txtMesa.getText();

            try {
                Connection conexion = DatabaseAccess.getConnection();
                conexion.setAutoCommit(false);

                PreparedStatement stmtPedido = conexion.prepareStatement(
                    "INSERT INTO Pedidos (Mesa, Estado, Total) VALUES (?, 'Pendiente', ?)",
                    Statement.RETURN_GENERATED_KEYS
                );
                stmtPedido.setString(1, mesa);
                stmtPedido.setDouble(2, total);
                stmtPedido.executeUpdate();

                ResultSet rs = stmtPedido.getGeneratedKeys();
                rs.next();
                int pedidoID = rs.getInt(1);

                PreparedStatement stmtDetalle = conexion.prepareStatement(
                    "INSERT INTO DetallePedido (PedidoID, PlatoID, Cantidad, PrecioUnitario) VALUES (?, ?, ?, ?)"
                );

                for (int i = 0; i < tblPlatos.getRowCount(); i++) {
                    int cantidad = (int) tblPlatos.getValueAt(i, 3);
                    if (cantidad > 0) {
                        stmtDetalle.setInt(1, pedidoID);
                        stmtDetalle.setInt(2, (int) tblPlatos.getValueAt(i, 0));
                        stmtDetalle.setInt(3, cantidad);
                        stmtDetalle.setDouble(4, (double) tblPlatos.getValueAt(i, 2));
                        stmtDetalle.executeUpdate();
                    }
                }

                conexion.commit();
                JOptionPane.showMessageDialog(this, "Pedido registrado correctamente.");
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar pedido: " + ex.getMessage());
            }
        });
             btnLimpiar.addActionListener(e -> {
        for (int i = 0; i < tblPlatos.getRowCount(); i++) {
            tblPlatos.setValueAt(0, i, 3); // columna 3 = Cantidad
        }
        calcularTotal();
    });
         btnNuevoPedido.addActionListener(e -> {
        txtMesa.setText("");
        for (int i = 0; i < tblPlatos.getRowCount(); i++) {
            tblPlatos.setValueAt(0, i, 3);
        }
        calcularTotal();
    });
        
  }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtMesa = new javax.swing.JTextField();
        lblMesa = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPlatos = new javax.swing.JTable();
        lblTotal = new javax.swing.JLabel();
        btnConfirmar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnNuevoPedido = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();

        lblMesa.setText("Mesa");

        jScrollPane1.setViewportView(tblPlatos);

        btnConfirmar.setText("Confirmar");

        btnCancelar.setText("Cancelar");

        btnNuevoPedido.setText("Nuevo Pedido");

        btnLimpiar.setText("Limpiar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblTotal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnConfirmar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNuevoPedido, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(lblMesa)
                    .addComponent(txtMesa, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblMesa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(btnConfirmar)
                        .addGap(24, 24, 24)
                        .addComponent(btnLimpiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNuevoPedido)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)))
                .addContainerGap(108, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
 



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnNuevoPedido;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMesa;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tblPlatos;
    private javax.swing.JTextField txtMesa;
    // End of variables declaration//GEN-END:variables
}
