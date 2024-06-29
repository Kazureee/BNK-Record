package com.mycompany.bnk;

import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.JOptionPane;


public class HomePage extends javax.swing.JFrame {

    private DefaultTableModel model;

    public HomePage() {
        initComponents();
        show_table();
        this.setTitle("BNK Collection");
        this.setVisible(true);

        Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                show_table();
            }
        });
        timer.start();
    }

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

private void show_table() {
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
        // Load the MySQL JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish connection
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/invoicemanagementsystem", "root", "");

        // Your SQL query and result processing logic
        String searchText = txtSearch.getText().trim(); // Get search text from txtSearch
        String query = "SELECT o.InvoiceId, c.Customer_Name, o.Total_Amount AS Price, o.Order_Date, " +
                       "CASE WHEN o.Status = 'Paid' THEN 'Paid' ELSE 'Unpaid' END AS Order_Status " +
                       "FROM Orderstbl o " +
                       "JOIN Customertbl c ON o.CustomerId = c.CustomerId ";

        // Append WHERE clause if searchText is not empty
        if (!searchText.isEmpty()) {
            query += "WHERE c.Customer_Name LIKE ?";
        }

        pst = con.prepareStatement(query);

        // Set parameter if searchText is not empty
        if (!searchText.isEmpty()) {
            pst.setString(1, "%" + searchText + "%");
        }

        rs = pst.executeQuery();

        DefaultTableModel DFT = (DefaultTableModel) tblInvoice.getModel();
        DFT.setRowCount(0);

        ResultSetMetaData RSMD = rs.getMetaData();
        int CC = RSMD.getColumnCount();

        while (rs.next()) {
            Vector<Object> row = new Vector<>();
            for (int i = 1; i <= CC; i++) {
                row.add(rs.getObject(i));
            }
            DFT.addRow(row);
        }

        tblInvoice.getColumnModel().getColumn(1).setPreferredWidth(300);
        tblInvoice.getColumnModel().getColumn(3).setPreferredWidth(300);

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, "JDBC Driver not found: ", ex);
        JOptionPane.showMessageDialog(this, "JDBC Driver not found: " + ex.getMessage(), "Driver Error", JOptionPane.ERROR_MESSAGE);
    } catch (SQLException ex) {
        Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, "SQL Error: ", ex);
        JOptionPane.showMessageDialog(this, "SQL Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Close resources in finally block to ensure they are always closed
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            if (con != null) con.close();
        } catch (SQLException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, "Error closing resources: ", ex);
        }
    }
}

    private void deleteInvoice() {
    int selectedRow = tblInvoice.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select an invoice to delete.", "No Invoice Selected", JOptionPane.WARNING_MESSAGE);
        return;
    }

    DefaultTableModel model = (DefaultTableModel) tblInvoice.getModel();
    int invoiceId = (int) model.getValueAt(selectedRow, 0);

    int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this invoice?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
    if (confirm != JOptionPane.YES_OPTION) {
        return;
    }

    Connection con = null;
    PreparedStatement pst = null;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/invoicemanagementsystem", "root", "");

        // Step 1: Delete from orders_quantitytbl
        String deleteQuantitySQL = "DELETE FROM orders_quantitytbl WHERE InvoiceId = ?";
        pst = con.prepareStatement(deleteQuantitySQL);
        pst.setInt(1, invoiceId);
        pst.executeUpdate();

        // Step 2: Delete from orderstbl
        String deleteOrderSQL = "DELETE FROM orderstbl WHERE InvoiceId = ?";
        pst = con.prepareStatement(deleteOrderSQL);
        pst.setInt(1, invoiceId);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(this, "Invoice deleted successfully!");

    } catch (SQLException ex) {
        Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, "SQL Error: ", ex);
        JOptionPane.showMessageDialog(this, "Error deleting invoice: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, "JDBC Driver not found: ", ex);
        JOptionPane.showMessageDialog(this, "JDBC Driver not found: " + ex.getMessage(), "Driver Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            if (pst != null) pst.close();
            if (con != null) con.close();
        } catch (SQLException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, "Error closing resources: ", ex);
        }
    }

    show_table(); // Refresh table
}

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        panelHeaer = new javax.swing.JPanel();
        panelOutlie = new javax.swing.JPanel();
        btnBNK = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInvoice = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnUpdateInvoice = new javax.swing.JButton();
        btnCreateInvoice = new javax.swing.JButton();
        btnDeleteInvoice = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        panelHeaer.setBackground(new java.awt.Color(214, 182, 144));

        panelOutlie.setBackground(new java.awt.Color(68, 55, 45));
        panelOutlie.setPreferredSize(new java.awt.Dimension(722, 1));

        javax.swing.GroupLayout panelOutlieLayout = new javax.swing.GroupLayout(panelOutlie);
        panelOutlie.setLayout(panelOutlieLayout);
        panelOutlieLayout.setHorizontalGroup(
            panelOutlieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelOutlieLayout.setVerticalGroup(
            panelOutlieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        btnBNK.setBackground(new java.awt.Color(214, 182, 144));
        btnBNK.setFont(new java.awt.Font("Rubik Light", 0, 24)); // NOI18N
        btnBNK.setForeground(new java.awt.Color(68, 55, 45));
        btnBNK.setText("BNK COLLECTION");
        btnBNK.setBorder(null);
        btnBNK.setBorderPainted(false);
        btnBNK.setContentAreaFilled(false);
        btnBNK.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBNK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBNKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelHeaerLayout = new javax.swing.GroupLayout(panelHeaer);
        panelHeaer.setLayout(panelHeaerLayout);
        panelHeaerLayout.setHorizontalGroup(
            panelHeaerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelOutlie, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1530, Short.MAX_VALUE)
            .addGroup(panelHeaerLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(btnBNK)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelHeaerLayout.setVerticalGroup(
            panelHeaerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeaerLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(btnBNK, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelOutlie, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tblInvoice.setFont(new java.awt.Font("Rubik Light", 0, 24)); // NOI18N
        tblInvoice.setForeground(new java.awt.Color(68, 55, 45));
        tblInvoice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "INVOICE ID", "Costumer_Name", "Total_Amount", "Order_Date", "Order_Status"
            }
        ));
        tblInvoice.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblInvoice.setGridColor(new java.awt.Color(255, 255, 255));
        tblInvoice.setRowHeight(25);
        tblInvoice.setSelectionBackground(new java.awt.Color(232, 225, 215));
        tblInvoice.setSelectionForeground(new java.awt.Color(68, 55, 45));
        tblInvoice.setShowGrid(false);
        tblInvoice.setShowHorizontalLines(true);
        tblInvoice.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblInvoice);
        tblInvoice.getAccessibleContext().setAccessibleName("");

        jLabel1.setFont(new java.awt.Font("Rubik Light", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(68, 55, 45));
        jLabel1.setText("Kyla!");

        btnUpdateInvoice.setBackground(new java.awt.Color(214, 182, 144));
        btnUpdateInvoice.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnUpdateInvoice.setForeground(new java.awt.Color(68, 55, 45));
        btnUpdateInvoice.setText("UPDATE INVOICE");
        btnUpdateInvoice.setBorder(null);
        btnUpdateInvoice.setBorderPainted(false);
        btnUpdateInvoice.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdateInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateInvoiceActionPerformed(evt);
            }
        });

        btnCreateInvoice.setBackground(new java.awt.Color(214, 182, 144));
        btnCreateInvoice.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnCreateInvoice.setForeground(new java.awt.Color(68, 55, 45));
        btnCreateInvoice.setText("CREATE INVOICE");
        btnCreateInvoice.setBorder(null);
        btnCreateInvoice.setBorderPainted(false);
        btnCreateInvoice.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCreateInvoice.setFocusPainted(false);
        btnCreateInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateInvoiceActionPerformed(evt);
            }
        });

        btnDeleteInvoice.setBackground(new java.awt.Color(214, 182, 144));
        btnDeleteInvoice.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnDeleteInvoice.setForeground(new java.awt.Color(68, 55, 45));
        btnDeleteInvoice.setText("DELETE INVOICE");
        btnDeleteInvoice.setBorder(null);
        btnDeleteInvoice.setBorderPainted(false);
        btnDeleteInvoice.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeleteInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteInvoiceActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Rubik Light", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(68, 55, 45));
        jLabel3.setText("WELCOME BACK, ");

        txtSearch.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelHeaer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1254, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCreateInvoice, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUpdateInvoice, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDeleteInvoice, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(121, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(panelHeaer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDeleteInvoice)
                    .addComponent(btnCreateInvoice)
                    .addComponent(btnUpdateInvoice)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(97, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBNKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBNKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBNKActionPerformed

    private void btnUpdateInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateInvoiceActionPerformed
    
    }//GEN-LAST:event_btnUpdateInvoiceActionPerformed

    private void btnCreateInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateInvoiceActionPerformed
        dispose();
        addInvoice createinvoice = new addInvoice();
        createinvoice.setVisible(true); 
        show_table();

    }//GEN-LAST:event_btnCreateInvoiceActionPerformed

    private void btnDeleteInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteInvoiceActionPerformed
        deleteInvoice();
        show_table();
    }//GEN-LAST:event_btnDeleteInvoiceActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
        show_table(); 
    }//GEN-LAST:event_txtSearchActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomePage().setVisible(true);
            }
        });
    

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBNK;
    private javax.swing.JButton btnCreateInvoice;
    private javax.swing.JButton btnDeleteInvoice;
    private javax.swing.JButton btnUpdateInvoice;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelHeaer;
    private javax.swing.JPanel panelOutlie;
    private javax.swing.JTable tblInvoice;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
