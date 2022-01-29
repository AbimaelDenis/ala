/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.gui;

import com.mycompany.ala.entities.OrderService;
import com.mycompany.ala.enums.OrderServiceFormType;
import com.mycompany.ala.models.OrderServiceTableModel;
import com.mycompany.ala.services.ImportSAPServicesData2;
import com.mycompany.ala.services.ImportServicesFromFile;
import com.mycompany.ala.util.DefaultFileChooser;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javax.swing.ListSelectionModel;

/**
 *
 * @author Abimael
 */
public class OrderServiceListView extends javax.swing.JFrame {

    private OrderServiceTableModel model = new OrderServiceTableModel(this);

    public OrderServiceListView() {
        initComponents();
        model.updateServicesList();
        tbOrderServices.setModel(model);
        tbOrderServices.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbOrderServices = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        btnNew = new javax.swing.JButton();
        btnConsult = new javax.swing.JButton();
        btnFilter = new javax.swing.JButton();
        btnImport = new javax.swing.JButton();
        btnSap = new javax.swing.JButton();
        btnExport = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbOrderServices.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbOrderServices);

        jToolBar1.setRollover(true);

        btnNew.setText("Novo");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        jToolBar1.add(btnNew);

        btnConsult.setText("Consultar");
        btnConsult.setToolTipText("");
        btnConsult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultActionPerformed(evt);
            }
        });
        jToolBar1.add(btnConsult);

        btnFilter.setText("Filtrar");
        btnFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterActionPerformed(evt);
            }
        });
        jToolBar1.add(btnFilter);

        btnImport.setText("Importar");
        btnImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportActionPerformed(evt);
            }
        });
        jToolBar1.add(btnImport);

        btnSap.setText("SAP");
        btnSap.setFocusable(false);
        btnSap.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSap.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSapActionPerformed(evt);
            }
        });
        jToolBar1.add(btnSap);

        btnExport.setText("Exportar");
        btnExport.setFocusable(false);
        btnExport.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExport.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jMenu1.setText("Serviços");

        jMenuItem1.setText("Ordem de Serviço");
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Projeto");
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Ocorrência");
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Sobre");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExport)
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        createOrderServiceFormView(null, OrderServiceFormType.NEW);
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFilterActionPerformed

    private void btnConsultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultActionPerformed
        if (tbOrderServices.getSelectedRow() != -1)
            createOrderServiceFormView(this.model.getOrderService(tbOrderServices.getSelectedRow()), OrderServiceFormType.CONSULT_MODE);
        else
            JOptionPane.showMessageDialog(this, "Selecione um serviço.");
    }//GEN-LAST:event_btnConsultActionPerformed

    private void btnImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportActionPerformed
        importServicesFromFile();
    }//GEN-LAST:event_btnImportActionPerformed

    private void btnSapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSapActionPerformed
        JFileChooser fc = DefaultFileChooser.createFileChooser();
        int result = fc.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            String path = fc.getSelectedFile().getAbsolutePath();

            ImportSAPServicesData2 importSapServicesData = new ImportSAPServicesData2(this, path);
            importSapServicesData.subscribeDataChangeListener(model);
            importSapServicesData.start();

            model.updateServicesList();

        }
    }//GEN-LAST:event_btnSapActionPerformed
    private void importServicesFromFile() {
        JFileChooser fc = DefaultFileChooser.createFileChooser();
        int result = fc.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            String path = fc.getSelectedFile().getAbsolutePath();

            ImportServicesFromFile isff = new ImportServicesFromFile(this, path);
            isff.subscribeDataChangeListener(model);
            isff.start();
        }
    }

    private void createOrderServiceFormView(OrderService os, OrderServiceFormType type) {
        JDialog osfv = new JDialog(this, true);
        osfv.setResizable(false);
        OrderServiceFormView view = new OrderServiceFormView(os, type);

        osfv.setContentPane(view.getContentPane());

        osfv.setSize(view.getSize());
        osfv.setTitle("Ordem de Serviço");
        osfv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        osfv.setLocationRelativeTo(null);
        osfv.setVisible(true);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OrderServiceListView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderServiceListView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderServiceListView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderServiceListView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrderServiceListView().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsult;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnImport;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSap;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tbOrderServices;
    // End of variables declaration//GEN-END:variables

}
