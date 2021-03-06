/*
 * Patrick Angle Commons Library
 * Copyright 2018 Patrick Angle
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.patrickangle.commons.playground;

import com.patrickangle.commons.beansbinding.BasicBinding;
import com.patrickangle.commons.beansbinding.BindingGroup;
import com.patrickangle.commons.beansbinding.interfaces.Binding;
import com.patrickangle.commons.beansbinding.swing.boundfields.JListBoundField;
import com.patrickangle.commons.beansbinding.swing.boundfields.JSliderBoundField;
import com.patrickangle.commons.beansbinding.swing.boundfields.JTableBoundField;
import com.patrickangle.commons.beansbinding.swing.boundfields.JTextComponentBoundField;
import com.patrickangle.commons.beansbinding.swing.models.ObservableComboBoxModel;
import com.patrickangle.commons.beansbinding.swing.models.ObservableListModel;
import com.patrickangle.commons.beansbinding.swing.models.ObservableListTableModel;
import com.patrickangle.commons.beansbinding.util.BindableFields;
import com.patrickangle.commons.objectediting.ObjectEditingDialog;
import com.patrickangle.commons.observable.collections.ObservableCollections;
import com.patrickangle.commons.observable.collections.ObservableCopyOnWriteArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author patrickangle
 */
public class BeansBinding extends javax.swing.JFrame {
    List<FunPojo> funPojos = new ObservableCopyOnWriteArrayList<>(Arrays.asList(new FunPojo(), new FunPojo(), new FunPojo(), new FunPojo(), new FunPojo(), new FunPojo(), new FunPojo(), new FunPojo()));

    FunPojo selectedPojo;
    
    List<String> binding6List = new ObservableCopyOnWriteArrayList<>(Arrays.asList("1 Potato", "2 Potato", "Red Potato", "Blue Potato", "1 Potato", "2 Potato", "Red Potato", "Blue Potato", "1 Potato", "2 Potato", "Red Potato", "Blue Potato", "1 Potato", "2 Potato", "Red Potato", "Blue Potato"));
    
    /**
     * Creates new form BeansBinding
     */
    public BeansBinding() {
        initComponents();
        
        BindingGroup bindingGroup = new BindingGroup();
        
        Binding binding1 = new BasicBinding(jLabel1, "text", jTextField1, JTextComponentBoundField.SYNTHETIC_FIELD_TEXT, Binding.UpdateStrategy.READ_WRITE);
        bindingGroup.add(binding1);
        
        Binding binding2 = new BasicBinding(jLabel2, "text", jTextField1, JTextComponentBoundField.SYNTHETIC_FIELD_TEXT_ON_FOCUS_LOST, Binding.UpdateStrategy.READ_WRITE);
        bindingGroup.add(binding2);
        
        Binding binding3 = new BasicBinding(jLabel3, "text", jTextField1, JTextComponentBoundField.SYNTHETIC_FIELD_TEXT_ON_ACTION_OR_FOCUS_LOST, Binding.UpdateStrategy.READ_WRITE);
        bindingGroup.add(binding3);
        
        Binding binding4 = new BasicBinding(jLabel4, "text", jSlider1, JSliderBoundField.SYNTHETIC_FIELD_VALUE, Binding.UpdateStrategy.WRITE_ONLY);
        bindingGroup.add(binding4);
        
        Binding binding5 = new BasicBinding(jLabel5, "text", jSlider1, JSliderBoundField.SYNTHETIC_FIELD_VALUE_IGNORE_ADJUSTING, Binding.UpdateStrategy.WRITE_ONLY);
        bindingGroup.add(binding5);
        
        Binding binding6 = new BasicBinding(jLabel6, "text", jComboBox1.getModel(), "selectedItem", Binding.UpdateStrategy.READ_WRITE);
        bindingGroup.add(binding6);
        
        Binding binding7 = new BasicBinding(this, "binding6List", jComboBox1.getModel(), "items", Binding.UpdateStrategy.READ_WRITE);
        bindingGroup.add(binding7);
        
        Binding binding8 = new BasicBinding(jComboBox1.getModel(), "selectedItem", jList1, JListBoundField.SYNTHETIC_FIELD_SELECTED_VALUE, Binding.UpdateStrategy.READ_WRITE);
        bindingGroup.add(binding8);
        
        Binding binding9 = new BasicBinding(this, "binding6List", jList1.getModel(), "items", Binding.UpdateStrategy.READ_WRITE);
        bindingGroup.add(binding9);
        
        Binding binding10 = new BasicBinding(jLabel6, "text", jList1, JListBoundField.SYNTHETIC_FIELD_SELECTED_VALUE, Binding.UpdateStrategy.WRITE_ONLY);
        bindingGroup.add(binding10);
        
        List<ObservableListTableModel.ColumnDefinition<FunPojo>> columnDefinitions = BindableFields.forClass(FunPojo.class).stream().map((bindableField) -> {
            return new ObservableListTableModel.ColumnDefinition<>(bindableField);
        }).collect(Collectors.toList());
        
        jTable1.setModel(new ObservableListTableModel(FunPojo.class, columnDefinitions));
        
        Binding tableBinding1 = new BasicBinding(this, "funPojos", jTable1.getModel(), "items", Binding.UpdateStrategy.READ_WRITE);
        bindingGroup.add(tableBinding1);
        
        Binding tableBinding2 = new BasicBinding(this, "selectedPojo", jTable1, JTableBoundField.SYNTHETIC_FIELD_SELECTED_ELEMENT, Binding.UpdateStrategy.READ_WRITE);
        bindingGroup.add(tableBinding2);
        
        bindingGroup.bind();
    }

    public List<String> getBinding6List() {
        return binding6List;
    }

    public List<FunPojo> getFunPojos() {
        return funPojos;
    }

    public void setFunPojos(List<FunPojo> funPojos) {
        this.funPojos = funPojos;
    }

    public FunPojo getSelectedPojo() {
        return selectedPojo;
    }

    public void setSelectedPojo(FunPojo selectedPojo) {
        this.selectedPojo = selectedPojo;
    }
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSlider1 = new javax.swing.JSlider();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("jLabel1");

        jTextField1.setText("jTextField1");

        jLabel2.setText("jLabel2");

        jLabel3.setText("jLabel3");

        jLabel4.setText("jLabel4");

        jLabel5.setText("jLabel5");

        jComboBox1.setEditable(true);
        jComboBox1.setModel(new ObservableComboBoxModel<String>(String.class));

        jLabel6.setText("jLabel6");

        jList1.setModel(new ObservableListModel<String>(String.class));
        jScrollPane1.setViewportView(jList1);

        jLabel8.setText("jLabel8");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable1);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new ObjectEditingDialog(this, true, selectedPojo).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(BeansBinding.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BeansBinding.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BeansBinding.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BeansBinding.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BeansBinding().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
