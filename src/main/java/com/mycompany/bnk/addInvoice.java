package com.mycompany.bnk;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.*;
import javax.swing.text.AbstractDocument;

public class addInvoice extends javax.swing.JFrame {

    Connection con;
    float totalPrice = 0.0f;

    public addInvoice() {
        initComponents();
        Connect(); // Establish database connection on form initialization
    }

    addInvoice(int invoiceId, String customerName, BigDecimal totalPrice, String orderStatus) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/InvoiceManagementSystem", "root", "");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(addInvoice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int getInvoiceId() throws SQLException {
    int invoiceId = 0;
    String sql = "SELECT MAX(InvoiceId) as InvoiceId FROM orderstbl";

    try (PreparedStatement stmt = con.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
            invoiceId = rs.getInt("InvoiceId");
        }
    }

    return invoiceId;
}
// Method to add data to the database (when saving invoice)




// Method to retrieve ProductId based on productName
private int getProductId(String productName) throws SQLException {
    String sql = "SELECT ProductId FROM Productstbl WHERE ProductName = ?";
    try (PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setString(1, productName);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("ProductId");
            } else {
                throw new SQLException("Product not found for name: " + productName);
            }
        }
    }
}

    private void updateTotalPrice() {
    float total = 0.0f;
    DefaultTableModel model = (DefaultTableModel) tblProduct.getModel();
    for (int i = 0; i < model.getRowCount(); i++) {
        int quantity = (int) model.getValueAt(i, 0);
        float price = (float) model.getValueAt(i, 2); // Assuming the price is already the total price for the quantity
        total += quantity * price;
    }
    txttotalprice.setText(String.format("%.2f", total)); // Update total price label
}

    

    private float getPriceFromComboBox(String product) {
        // Define a map for products and their prices
        Map<String, Float> productPrices = new HashMap<>();
        productPrices.put("Top 180.00", 180.00f);
        productPrices.put("T-Shirt 150.00", 150.00f);
        productPrices.put("Short 150.00", 150.00f);
        productPrices.put("Pants 200.00", 200.00f);
        productPrices.put("Jacket 250.00", 250.00f);
        productPrices.put("Dress 250.00", 250.00f);

        // Retrieve price from map based on product name
        return productPrices.getOrDefault(product, 0.0f);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        popupMenu1 = new java.awt.PopupMenu();
        jPanel1 = new javax.swing.JPanel();
        panelHeaer = new javax.swing.JPanel();
        panelOutlie = new javax.swing.JPanel();
        btnBNK = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProduct = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txttotalprice = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        cmbProduct = new javax.swing.JComboBox<>();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        txtQTY = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox<>();

        jMenuItem1.setText("jMenuItem1");

        popupMenu1.setLabel("popupMenu1");

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
            .addComponent(panelOutlie, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1088, Short.MAX_VALUE)
            .addGroup(panelHeaerLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(btnBNK)
                .addGap(24, 866, Short.MAX_VALUE))
        );
        panelHeaerLayout.setVerticalGroup(
            panelHeaerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeaerLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(btnBNK, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelOutlie, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tblProduct.setFont(new java.awt.Font("Rubik Light", 0, 24)); // NOI18N
        tblProduct.setForeground(new java.awt.Color(68, 55, 45));
        tblProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "QUANTITY", "PRODUCT NAME", "PRICE"
            }
        ));
        tblProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblProduct.setFocusable(false);
        tblProduct.setGridColor(new java.awt.Color(255, 255, 255));
        tblProduct.setRowHeight(25);
        tblProduct.setSelectionBackground(new java.awt.Color(232, 225, 215));
        tblProduct.setSelectionForeground(new java.awt.Color(68, 55, 45));
        tblProduct.setShowGrid(false);
        tblProduct.setShowHorizontalLines(true);
        tblProduct.getTableHeader().setReorderingAllowed(false);
        tblProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProduct);
        tblProduct.getAccessibleContext().setAccessibleName("");

        jLabel1.setFont(new java.awt.Font("Rubik Light", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(68, 55, 45));
        jLabel1.setText("Kyla!");

        jLabel3.setFont(new java.awt.Font("Rubik Light", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(68, 55, 45));
        jLabel3.setText("WELCOME BACK, ");

        txtName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtName.setForeground(new java.awt.Color(68, 55, 45));
        txtName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNametxtUserFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNameFocusLost(evt);
            }
        });
        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNametxtUserActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("COSTUMER NAME");

        txttotalprice.setBackground(new java.awt.Color(0, 0, 0));
        txttotalprice.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txttotalprice.setText("0.00 ");

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("TOTAL PRICE");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("PHP");

        btnSave.setText("SAVE");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        cmbProduct.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cmbProduct.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Top 180.00", "T-Shirt 150.00", "Short 150.00", "Pants 200.00", "Jacket 250.00", "Dress 250.00" }));

        btnAdd.setText("ADD");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setText("UPDATE");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        txtQTY.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtQTY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQTYActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("QTY");

        cmbStatus.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Unpaid", "Paid", " " }));

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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(159, 159, 159)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtName, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtQTY)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cmbProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(656, 656, 656)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(txttotalprice, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel2))
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGap(159, 159, 159)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(btnAdd)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnUpdate)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btnDelete))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 683, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(panelHeaer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txttotalprice)
                                    .addComponent(jLabel2)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtQTY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(86, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBNKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBNKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBNKActionPerformed

    private void txtNametxtUserFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNametxtUserFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNametxtUserFocusGained

    private void txtNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameFocusLost

    private void txtNametxtUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNametxtUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNametxtUserActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dispose();
        HomePage homepage = new HomePage();
        homepage.setVisible(true);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void tblProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductMouseClicked
    int selectedRowIndex = tblProduct.getSelectedRow();

    // Ensure a row is actually selected
    if (selectedRowIndex != -1) {
        // Get data from the selected row
        DefaultTableModel model = (DefaultTableModel) tblProduct.getModel();
        int qty = (int) model.getValueAt(selectedRowIndex, 0); // Assuming quantity is stored as Integer
        String product = (String) model.getValueAt(selectedRowIndex, 1);
        float price = (float) model.getValueAt(selectedRowIndex, 2); // Assuming price is stored as Float

        // Display data in input fields for editing
        txtQTY.setText(String.valueOf(qty));
        cmbProduct.setSelectedItem(product);
    }


    }//GEN-LAST:event_tblProductMouseClicked

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
    String customerName = txtName.getText().trim(); // Get customer name from txtName field

    // Check if customer name is empty
    if (customerName.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter a customer name.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    float totalAmount = 0.0f;
    try {
        totalAmount = Float.parseFloat(txttotalprice.getText().trim());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid total price format.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        // Step 1: Check if customer exists, if not, insert
        String selectCustomerSQL = "SELECT CustomerId FROM Customertbl WHERE Customer_Name = ?";
        String insertCustomerSQL = "INSERT INTO Customertbl (Customer_Name) VALUES (?)";

        int customerId;

        // Check if the customer exists in the database
        try (PreparedStatement selectCustomerStmt = con.prepareStatement(selectCustomerSQL)) {
            selectCustomerStmt.setString(1, customerName);

            try (ResultSet rs = selectCustomerStmt.executeQuery()) {
                if (rs.next()) {
                    customerId = rs.getInt("CustomerId");
                } else {
                    // Customer not found, insert into Customertbl
                    try (PreparedStatement insertCustomerStmt = con.prepareStatement(insertCustomerSQL, Statement.RETURN_GENERATED_KEYS)) {
                        insertCustomerStmt.setString(1, customerName);
                        int affectedRows = insertCustomerStmt.executeUpdate();

                        if (affectedRows == 0) {
                            throw new SQLException("Creating customer failed, no rows affected.");
                        }

                        // Get the generated customer id
                        try (ResultSet generatedKeys = insertCustomerStmt.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                customerId = generatedKeys.getInt(1); // Retrieve the generated customer id
                            } else {
                                throw new SQLException("Creating customer failed, no ID obtained.");
                            }
                        }
                    }
                }
            }
        }

        // Step 2: Insert into Orderstbl to get the invoice id
        String insertOrderSQL = "INSERT INTO Orderstbl (CustomerId, Total_Amount) VALUES (?, ?)";
        int invoiceId;

        try (PreparedStatement insertOrderStmt = con.prepareStatement(insertOrderSQL, Statement.RETURN_GENERATED_KEYS)) {
            insertOrderStmt.setInt(1, customerId);
            insertOrderStmt.setFloat(2, totalAmount);

            // Execute the insert statement
            int affectedRows = insertOrderStmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Inserting order failed, no rows affected.");
            }

            // Get the generated invoice id
            try (ResultSet generatedKeys = insertOrderStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    invoiceId = generatedKeys.getInt(1); // Retrieve the generated invoice id

                    // Step 3: Insert into Orders_Quantitytbl for each item in tblProduct
                    DefaultTableModel model = (DefaultTableModel) tblProduct.getModel();
                    String insertOrderItemSQL = "INSERT INTO Orders_Quantitytbl (InvoiceId, ProductId, Quantity) VALUES (?, ?, ?)";
                    try (PreparedStatement insertOrderItemStmt = con.prepareStatement(insertOrderItemSQL)) {
                        for (int i = 0; i < model.getRowCount(); i++) {
                            String productName = (String) model.getValueAt(i, 1);
                            int quantity = (int) model.getValueAt(i, 0);
                            int productId = getProductId(productName); // Assuming you have a method getProductId

                            insertOrderItemStmt.setInt(1, invoiceId);
                            insertOrderItemStmt.setInt(2, productId);
                            insertOrderItemStmt.setInt(3, quantity);

                            insertOrderItemStmt.addBatch(); // Add to batch for batch insert
                        }

                        // Execute batch insert for order items
                        int[] batchResult = insertOrderItemStmt.executeBatch();
                        System.out.println("Inserted " + batchResult.length + " order items successfully.");

                        JOptionPane.showMessageDialog(this, "Invoice saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

                        // Optionally clear the UI or do further processing
                        // For example, clear the table:
                        model.setRowCount(0);
                        txtName.setText("");
                        txttotalprice.setText("0.00");

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this, "Error saving invoice items: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error saving invoice: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace(); // Print stack trace for debugging
    }

    HomePage home = new HomePage();
    dispose();

    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
    int selectedRowIndex = tblProduct.getSelectedRow();
    
    // Ensure a row is actually selected
    if (selectedRowIndex != -1) {
        // Prompt user for confirmation
        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this item?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        
        if (option == JOptionPane.YES_OPTION) {
            // Remove the selected row from the table model
            DefaultTableModel model = (DefaultTableModel) tblProduct.getModel();
            model.removeRow(selectedRowIndex);
            
            txtQTY.setText("");
            cmbProduct.setSelectedIndex(0);
        }
    } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.", "Delete Error", JOptionPane.ERROR_MESSAGE);
    }

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
    String qtyText = txtQTY.getText();
    String selectedProductWithPrice = cmbProduct.getSelectedItem().toString();

    // Extract product name without the price suffix
    String selectedProduct = selectedProductWithPrice.split("\\s\\d+\\.\\d+")[0];

    // Extract price from selectedProduct string
    float price = getPriceFromComboBox(selectedProductWithPrice);

    int qty;
    try {
        qty = Integer.parseInt(qtyText);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Quantity must be a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    float producttotalprice = qty * price; // Calculate total amount for the current item
    DefaultTableModel model = (DefaultTableModel) tblProduct.getModel();
    model.addRow(new Object[]{qty, selectedProduct, producttotalprice}); // Add row with total amount

    // Clear input fields
    txtQTY.setText("");
    cmbProduct.setSelectedIndex(0);

    // Update total price
    updateTotalPrice();

    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
     int selectedRowIndex = tblProduct.getSelectedRow();

    // Check if a row is selected
    if (selectedRowIndex == -1) {
        JOptionPane.showMessageDialog(this, "Please select a row to update.", "Update Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String qtyText = txtQTY.getText();
    String selectedProductWithPrice = cmbProduct.getSelectedItem().toString();
    String selectedProduct = selectedProductWithPrice.split("\\s\\d+\\.\\d+")[0];
    float price = getPriceFromComboBox(selectedProductWithPrice);
    int qty;
    try {
        qty = Integer.parseInt(qtyText);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Quantity must be a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    float productTotalPrice = qty * price; // Calculate total amount for the current item

    DefaultTableModel model = (DefaultTableModel) tblProduct.getModel();
    model.setValueAt(qty, selectedRowIndex, 0); 
    model.setValueAt(selectedProduct, selectedRowIndex, 1); 
    model.setValueAt(productTotalPrice, selectedRowIndex, 2); 
    txtQTY.setText("");
    cmbProduct.setSelectedIndex(0);
    model.fireTableDataChanged();
    updateTotalPrice();

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void txtQTYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQTYActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQTYActionPerformed
        
    public static void main(String args[]) {


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new addInvoice().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBNK;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbProduct;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelHeaer;
    private javax.swing.JPanel panelOutlie;
    private java.awt.PopupMenu popupMenu1;
    private javax.swing.JTable tblProduct;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtQTY;
    private javax.swing.JLabel txttotalprice;
    // End of variables declaration//GEN-END:variables
}
