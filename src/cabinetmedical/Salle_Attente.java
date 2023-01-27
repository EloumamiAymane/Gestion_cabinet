/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cabinetmedical;

import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author HP
 */
public class Salle_Attente extends javax.swing.JFrame {
Connection c;
    /**
     * Creates new form Salle_Attente
     */
    public Salle_Attente() {
        initComponents();
         try{
      //étape 1: charger la classe de driver
      Class.forName("com.mysql.cj.jdbc.Driver");
      //étape 2: créer l'objet de connexion
     c = DriverManager.getConnection(
      "jdbc:mysql://localhost:3306/Cabinet_medicale", "root", "");
          this.setLocationRelativeTo(null);
          this.setTitle("Salle d'attente");
          afficherSalleAttente();
          DateHeure();
         
        }catch(Exception e){
            JOptionPane.showMessageDialog(this,"Erreur "+e.getMessage());
        }
    }
    
    void  afficherSalleAttente() throws Exception{
        int codeMed=AfficherIDMED();
       int z=0;
     DefaultTableModel  m=new DefaultTableModel(null,
                   new String[]{"ID_RDV","Nom Patient","Prenom Patient","Date","Heure","id_Pat"});
              PreparedStatement st= c.prepareStatement("select r.ID_RDV,p.NOM_PAT,p.PRENOM_PAT,r.DATE_RDV ,r.HEURE_RDV,p.id_pat  from patient p, rendez_vous r, medecin m"
                + " where p.ID_PAT=r.ID_PAT  and m.id_med=r.id_med and r.ETAT='confirmer' and r.DATE_RDV=CURRENT_DATE and m.id_med=?");
              st.setInt(1, codeMed);
       ResultSet rslt= st.executeQuery();
       
    
       while(rslt.next()) {
         z++;
         
			m.addRow(new Object[]{rslt.getInt(1),rslt.getString(2),rslt.getString(3),
                            rslt.getDate(4),rslt.getString(5),rslt.getInt(6)});
		}
          nbrRDV.setText(Integer.toString(z));
    
                tableRDV.setModel(m);
    }
    
       void DateHeure(){
       java.util.Date dd= new java.util.Date() ;
        SimpleDateFormat dat= new SimpleDateFormat("EEEE-dd-MMM-yyyy");
        SimpleDateFormat heur=new SimpleDateFormat("HH:mm");
        date.setText(dat.format(dd));
       // heure.setText(heur.format(dd));
    }
    
     int  AfficherIDMED() throws Exception {
           int id_user=MEDICAL.idMed;
           String nom_Medc;
           int idM=0;
           PreparedStatement p= c.prepareStatement("select login from user where id_user=?");
           p.setInt(1, id_user);
           ResultSet r=p.executeQuery();
           if(r.next()){
               nom_Medc=r.getString(1);
               PreparedStatement pp= c.prepareStatement("select id_med from medecin where nom_med=?");
           pp.setString(1, nom_Medc);
           ResultSet re=pp.executeQuery();
           if(re.next())
               idM=re.getInt(1);

           
           }
                         return  idM;
     }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableRDV = new javax.swing.JTable();
        nbrRDV = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        date = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableRDV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID_RDV", "Nom Patient", "Prenom Patient", "Date ", "Heure", "id_Pat"
            }
        ));
        tableRDV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableRDVMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableRDV);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 740, 310));

        nbrRDV.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        nbrRDV.setText("0");
        jPanel1.add(nbrRDV, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 40, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nombre des patient est : ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 230, 30));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cabinetmedical/home-_2_ (1).jpg"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 30, 40, 40));

        date.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        date.setForeground(new java.awt.Color(0, 0, 0));
        date.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cabinetmedical/78e9a8fd-5632-4a4a-a42b-802037b5.jpg"))); // NOI18N
        date.setText("21/02/2022");
        date.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255), 2));
        jPanel1.add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 40, 240, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cabinetmedical/salle-attente-dentiste.jpg"))); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Salle d'Attente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 3, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, 430));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 430));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableRDVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableRDVMouseClicked
       Consultation c= new Consultation();
    
      
       
      c.show();
      dispose();
    }//GEN-LAST:event_tableRDVMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Page_Acceuil p=new Page_Acceuil();
p.show();// TODO add your handling code here:
dispose();
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
            java.util.logging.Logger.getLogger(Salle_Attente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Salle_Attente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Salle_Attente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Salle_Attente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Salle_Attente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel date;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nbrRDV;
    public static javax.swing.JTable tableRDV;
    // End of variables declaration//GEN-END:variables
}