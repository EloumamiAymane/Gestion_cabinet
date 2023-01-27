/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cabinetmedical;

import java.awt.FlowLayout;
import java.sql.*;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author HP
 */
public class Ajouter_RDV extends javax.swing.JFrame {
Connection c;
    /**
     * Creates new form Ajouter_RDV
     */
    public Ajouter_RDV() {
        initComponents();
          try {
            //étape 1: charger la classe de driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //étape 2: créer l'objet de connexion
            c = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Cabinet_medicale", "root", "");
            AjouterPatientCombox();
            AjouterMedecinCombox();
              this.setTitle(" Ajouter Rendez_Vous");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur:" + e.getMessage());
        }
         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
    
    void AjouterRDV(int idPat,int idMed,LocalDate d,String time,String Remarque,String status) throws Exception{
        Date d1=Date.valueOf(d);
        PreparedStatement p=c.prepareStatement("INSERT INTO rendez_vous(id_pat,id_med,date_rdv,heure_rdv,etat,remarque) VALUES(?,?,?,?,?,?)");
        p.setInt(1, idPat);
          p.setInt(2, idMed);
        p.setDate(3, d1);
        p.setString(4, time);
        p.setString(5, status);
        p.setString(6, Remarque);
        
        p.executeUpdate();
         
    }
    void AjouterPatientCombox(){
               try{
                   idPatient.removeAllItems();
       Statement stm1=c.createStatement();
       ResultSet rslt1= stm1.executeQuery("select id_pat from patient order by id_pat");
       while(rslt1.next()){
         int idPat=rslt1.getInt(1);
         String Patient=  Integer.toString(idPat);
         idPatient.addItem(Patient);
       }
      
        }catch(Exception e){
            e.printStackTrace();} }

    void AjouterMedecinCombox(){
               try{
                   idMed.removeAllItems();
       Statement stm1=c.createStatement();
       ResultSet rslt1= stm1.executeQuery("select id_med from medecin order by id_med");
       while(rslt1.next()){
         int idPat=rslt1.getInt(1);
         String Patient=  Integer.toString(idPat);
         idMed.addItem(Patient);
       }
      
        }catch(Exception e){
            e.printStackTrace();} }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        AddRDV = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        DatePresent = new com.toedter.calendar.JDateChooser();
        idPatient = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        Remarque = new javax.swing.JTextArea();
        idMed = new javax.swing.JComboBox<>();
        PrenomMed = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        NomMed = new javax.swing.JLabel();
        HeurePresent = new javax.swing.JTextField();
        Prenom = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        Nom = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cabinetmedical/folder_add (1).jpg"))); // NOI18N
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 40, 40));

        AddRDV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cabinetmedical/save-_1_ (1).jpg"))); // NOI18N
        AddRDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddRDVActionPerformed(evt);
            }
        });
        jPanel1.add(AddRDV, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 40, 40));

        clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cabinetmedical/delete (1).jpg"))); // NOI18N
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });
        jPanel1.add(clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 40, 40));

        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 470, 60));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel2.setText("ID Patient  :");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 130, 20));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel3.setText("Date de present  :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 120, 20));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel4.setText("Heure de present  :");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, 130, 20));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel7.setText("Remarque  :");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, 130, 20));

        DatePresent.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(DatePresent, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 320, 160, -1));

        idPatient.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        idPatient.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                idPatientItemStateChanged(evt);
            }
        });
        idPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idPatientActionPerformed(evt);
            }
        });
        jPanel1.add(idPatient, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, 130, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel10.setText("Ajouter RDV");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 240, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 23, 290, 10));

        Remarque.setColumns(20);
        Remarque.setRows(5);
        jScrollPane2.setViewportView(Remarque);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 410, -1, -1));

        idMed.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                idMedItemStateChanged(evt);
            }
        });
        jPanel1.add(idMed, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 140, -1));

        PrenomMed.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.add(PrenomMed, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 280, 130, 20));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Prenom :");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 280, 70, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Nom :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 50, -1));

        NomMed.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.add(NomMed, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 130, 20));

        HeurePresent.setToolTipText("");
        HeurePresent.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(HeurePresent, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 370, 160, 20));

        Prenom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(Prenom, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 160, 120, 20));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("ID Medecin  :");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, -1, -1));

        Nom.setBackground(new java.awt.Color(204, 0, 0));
        Nom.setForeground(new java.awt.Color(0, 0, 0));
        Nom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(Nom, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 120, 20));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel8.setText("Prenom :");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel12.setText("Nom :");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        jLabel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 470, 400));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cabinetmedical/kkkkkkkkkkkkkk.jpg"))); // NOI18N
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -4, 500, 530));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel9.setText("ID Patient  :");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 130, 20));

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 524, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddRDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddRDVActionPerformed
          if(Remarque.getText().equals("")||this.HeurePresent.getText().equals("")
                  ||DatePresent.getDate()==null){
            JOptionPane.showMessageDialog(this,"Tout les Infos sont obligatoire !");
      }
          else{
         try{
          String str=idPatient.getSelectedItem().toString();
         int idPat=Integer.parseInt(str); 
          
         String str1=idMed.getSelectedItem().toString();
         int idMed=Integer.parseInt(str1); 
         //recuperer la date en format  sql
         java.sql.Date d1=new java.sql.Date(DatePresent.getDate().getTime());
         // transformer la date du sql  en localdate
         LocalDate date=LocalDate.parse(d1.toString());
         String time=HeurePresent.getText();
         String Remarque=this.Remarque.getText();
         String status="en attente";
         
      
             AjouterRDV(idPat,idMed, date, time, Remarque,status);
             JOptionPane.showMessageDialog(this,"Rendez vous est ajouté avec succés");
         }catch(Exception e){
             JOptionPane.showMessageDialog(this, "Erreur "+e.getMessage());
            // e.printStackTrace();
         }
          }
    }//GEN-LAST:event_AddRDVActionPerformed

    private void idPatientItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_idPatientItemStateChanged
        String str=idPatient.getSelectedItem().toString();
        int idPat=Integer.parseInt(str);
        try {
            PreparedStatement p=c.prepareStatement("select nom_pat,prenom_pat from patient where id_pat=?");
            p.setInt(1, idPat);
            ResultSet rs=p.executeQuery();
            if(rs.next()){
                Nom.setText(rs.getString(1));
                Prenom.setText(rs.getString(2));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur "+e.getMessage());
        }
    }//GEN-LAST:event_idPatientItemStateChanged

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        Remarque.setText("");
       
        HeurePresent.setText("");
        
        DatePresent.setDate(null);
    }//GEN-LAST:event_clearActionPerformed

    private void idPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idPatientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idPatientActionPerformed

    private void idMedItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_idMedItemStateChanged
        String str=idMed.getSelectedItem().toString();
        int idPat=Integer.parseInt(str);
        try {
            PreparedStatement p=c.prepareStatement("select nom_med,prenom_med from medecin where id_med=?");
            p.setInt(1, idPat);
            ResultSet rs=p.executeQuery();
            if(rs.next()){
                NomMed.setText(rs.getString(1));
                PrenomMed.setText(rs.getString(2));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur "+e.getMessage());
        }
    }//GEN-LAST:event_idMedItemStateChanged

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
            java.util.logging.Logger.getLogger(Ajouter_RDV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ajouter_RDV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ajouter_RDV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ajouter_RDV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ajouter_RDV().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddRDV;
    private com.toedter.calendar.JDateChooser DatePresent;
    private javax.swing.JTextField HeurePresent;
    private javax.swing.JLabel Nom;
    private javax.swing.JLabel NomMed;
    private javax.swing.JLabel Prenom;
    private javax.swing.JLabel PrenomMed;
    private javax.swing.JTextArea Remarque;
    private javax.swing.JButton clear;
    private javax.swing.JComboBox<String> idMed;
    private javax.swing.JComboBox<String> idPatient;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
