/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cabinetmedical;

import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author HP
 */
public class resultat1 extends javax.swing.JFrame {
Connection c;
    /**
     * Creates new form resultat
     */
    public resultat1() {
        initComponents();
          this.setLocationRelativeTo(null);
          this.setTitle("Resultat");
          try {
              
            //étape 1: charger la classe de driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //étape 2: créer l'objet de connexion
            c = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Cabinet_medicale", "root", "");
             AfficherIDCER();
             AfficherIDMED();
             AfficherIDOR();
             AjouterPatientCombox();
              afficherConsultation();
              a();
              afficherOrdonnance();
              afficherRendez();
              afficherCertif();
             
              System.out.println(MEDICAL.idMed);
          // afficherRDV();
       
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur:" + e.getMessage());
            e.printStackTrace();
        }
    }
     void afficherCertif() throws Exception {
             int x=Salle_Attente.tableRDV.getSelectedRow();
       TableModel model =Salle_Attente.tableRDV.getModel();
     
     String id= model.getValueAt(x, 5).toString();
     int code=Integer.parseInt(id);
           DefaultTableModel  m=new DefaultTableModel(null,
                   new String[]{"ID Certificat","Nbr De Jours","Details"});
       
		PreparedStatement stmt =c.prepareStatement("select  distinct(m.ID_CERTIF),m.NBR_JOURS,m.DETAILS from certificat_medical m,consultation c  where c.ID_CERTIF=m.ID_CERTIF and c.id_pat=? ");
                stmt.setInt(1,code);
	ResultSet rslt=stmt.executeQuery();
		
		while(rslt.next()) {
			m.addRow(new Object[]{rslt.getInt(1),rslt.getInt(2),rslt.getString(3)});
		}
                tableCr.setModel(m);
                

               
    
       } 
    
    
     void a(){

     int x=Salle_Attente.tableRDV.getSelectedRow();
       TableModel model =Salle_Attente.tableRDV.getModel();
     
     String id= model.getValueAt(x, 5).toString();
      
       
      
      //System.out.println(id);
    
}
     void afficherOrdonnance() throws Exception {
             int x=Salle_Attente.tableRDV.getSelectedRow();
       TableModel model =Salle_Attente.tableRDV.getModel();
     
     String id= model.getValueAt(x, 5).toString();
     int code=Integer.parseInt(id);
           DefaultTableModel  m=new DefaultTableModel(null,
                   new String[]{"ID Ordonnance","Date Ordonnance","Nom_Medicaments","dose","description","Quantite"});
       
		PreparedStatement stmt =c.prepareStatement("select distinct(o.id_ord),o.date_ord, m.NOM_MEDM,m.dose,m.description,p.QUANTITE from Ordonnance o,consultation c,medicaments m ,prescrire p\n" +
"where c.ORD_ID_ORD =o.id_ord\n" +
"and p.ID_ORD=o.id_ord\n" +
"and p.ID_MEDM=m.id_medm\n" +
"\n" +
"and c.id_pat=? ");
                int codePrecedent=-1,codeActual;
                stmt.setInt(1,code);
	ResultSet rslt=stmt.executeQuery();
		
		while(rslt.next()) {
                    codeActual=rslt.getInt(1);
                    if(codeActual!=codePrecedent) {
			m.addRow(new Object[]{rslt.getInt(1),rslt.getDate(2),rslt.getString(3),rslt.getInt(4),rslt.getString(5),rslt.getInt(6)});
		}else{
                   m.addRow(new Object[]{null,null,rslt.getString(3),rslt.getInt(4),rslt.getString(5),rslt.getInt(6)});}
                    codePrecedent=codeActual;
                tableO.setModel(m);
                

               
    
       }
     }
    
     void afficherRendez() throws Exception {
             int x=Salle_Attente.tableRDV.getSelectedRow();
       TableModel model =Salle_Attente.tableRDV.getModel();
     
     String id= model.getValueAt(x, 5).toString();
     int code=Integer.parseInt(id);
           DefaultTableModel  m=new DefaultTableModel(null,
                   new String[]{"ID Rendez_Vous","Date_Rendez_Vous","HeureRendez-vous","Remarque"});
       
		PreparedStatement stmt =c.prepareStatement("select r.ID_RDV,r.DATE_RDV,r.HEURE_RDV ,r.REMARQUE from Rendez_vous r,patient p  where r.id_pat = p.id_pat and p.id_pat=? ");
                stmt.setInt(1,code);
	ResultSet rslt=stmt.executeQuery();
		
		while(rslt.next()) {
			m.addRow(new Object[]{rslt.getInt(1),rslt.getDate(2),rslt.getString(3),rslt.getString(4)});
		}
                tableR.setModel(m);
                

               
    
       } 
    
     void afficherConsultation() throws Exception {
             int x=Salle_Attente.tableRDV.getSelectedRow();
       TableModel model =Salle_Attente.tableRDV.getModel();
     String id= model.getValueAt(x, 5).toString();
     int code=Integer.parseInt(id);
           DefaultTableModel  m=new DefaultTableModel(null,
                   new String[]{"ID Consultation","Nom Patient","Prenom Patient","Resultat","Prix"});
       
		PreparedStatement stmt =c.prepareStatement("select c.Id_Con,p.nom_pat,p.prenom_pat ,c.Resultat,c.prix from patient p,consultation c where c.id_pat=p.id_pat and p.id_pat=? ");
                stmt.setInt(1,code);
	ResultSet rslt=stmt.executeQuery();
		
		while(rslt.next()) {
			m.addRow(new Object[]{rslt.getInt(1),rslt.getString(2),rslt.getString(3),
                            rslt.getString(4),rslt.getDouble(5)});
		}
                tableC.setModel(m);
                

               
    
       } 
    
     void Insert(int id_ord,int id_cer,int id_med,int id_pat,LocalDate d,String heure,double prix,String resultat) throws Exception{
        Date date=Date.valueOf(d);
        PreparedStatement p= c.prepareStatement("Insert into consultation (ORD_ID_ORD ,ID_CERTIF,"
                + "ID_MED,ID_PAT,DATE_CON,HEURE,PRIX,RESULTAT)values(?,?,?,?,?,?,?,?)");
        p.setInt(1, id_ord);
         p.setInt(2, id_cer);
          p.setInt(3, id_med);
           p.setInt(4, id_pat);
           p.setDate(5, date);
           p.setString(6, heure);
           p.setDouble(7, prix);
           p.setString(8,resultat );
           p.executeUpdate();
    }
     void Supprimer(int numeroSelected ) throws Exception{
        PreparedStatement stmt =c.prepareStatement("delete from consultation where id_con=?");
		stmt.setInt(1,numeroSelected );

		 stmt.executeUpdate();
    }
     
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        id_Ord = new javax.swing.JComboBox<>();
        id_Pat = new javax.swing.JLabel();
        id_Certif = new javax.swing.JComboBox<>();
        date = new com.toedter.calendar.JDateChooser();
        heure = new javax.swing.JTextField();
        prix = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultlat = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableC = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        refresh = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        ADDUSER = new javax.swing.JButton();
        Clear = new javax.swing.JButton();
        homebtn = new javax.swing.JButton();
        id_Med = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableR = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableCr = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableO = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setBackground(new java.awt.Color(204, 204, 255));
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });
        jTabbedPane1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jTabbedPane1ComponentHidden(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Les Consultations ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(358, 8, 226, 33));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel2.setText("Resultat : ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 60, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel3.setText("ID Ordonance : ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel4.setText("ID Certificat :");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel5.setText("ID Medecin :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel6.setText("ID Patient :");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel7.setText("Date Consultation :");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel8.setText("Heure Consultation : ");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 120, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel9.setText("Prix : ");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 180, -1, -1));

        id_Ord.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(id_Ord, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 140, -1));

        id_Pat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.add(id_Pat, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 140, 20));

        id_Certif.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(id_Certif, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 140, -1));

        date.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, 160, -1));

        heure.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(heure, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 120, 160, 20));

        prix.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(prix, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 180, 160, 20));

        resultlat.setColumns(20);
        resultlat.setRows(5);
        jScrollPane1.setViewportView(resultlat);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 90, -1, -1));

        tableC.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        tableC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Consultation", "Nom Patient", "Prenom Patient", "Resultat", "Prix"
            }
        ));
        tableC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tableCMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(tableC);
        if (tableC.getColumnModel().getColumnCount() > 0) {
            tableC.getColumnModel().getColumn(0).setPreferredWidth(3);
        }

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 930, 220));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cabinetmedical/delete (1).jpg"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 40, 40));

        refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cabinetmedical/refresh (1).jpg"))); // NOI18N
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });
        jPanel1.add(refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 40, 40));

        jLabel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 216, 140, 50));

        ADDUSER.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        ADDUSER.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cabinetmedical/save-_1_ (1).jpg"))); // NOI18N
        ADDUSER.setText("  Save");
        ADDUSER.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ADDUSER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ADDUSERActionPerformed(evt);
            }
        });
        jPanel1.add(ADDUSER, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 220, 120, 40));

        Clear.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        Clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cabinetmedical/delete (1).jpg"))); // NOI18N
        Clear.setText(" Cancel");
        Clear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearActionPerformed(evt);
            }
        });
        jPanel1.add(Clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 220, 120, 40));

        homebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cabinetmedical/home-_2_ (1).jpg"))); // NOI18N
        homebtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        homebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homebtnActionPerformed(evt);
            }
        });
        jPanel1.add(homebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 10, 50, 40));

        id_Med.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.add(id_Med, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, 140, 20));

        jTabbedPane1.addTab("  Consultation ", new javax.swing.ImageIcon(getClass().getResource("/cabinetmedical/1111111111 (1).jpg")), jPanel1); // NOI18N

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setText("Historiques des  Rendez-Vous ");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        tableR.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Rendez-Vous", "Date Rendez-Vous", "Heure Rendez-Vous", "Remarque"
            }
        ));
        jScrollPane4.setViewportView(tableR);
        if (tableR.getColumnModel().getColumnCount() > 0) {
            tableR.getColumnModel().getColumn(0).setPreferredWidth(3);
            tableR.getColumnModel().getColumn(1).setPreferredWidth(3);
            tableR.getColumnModel().getColumn(2).setPreferredWidth(3);
        }

        jPanel3.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 70, 670, 340));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cabinetmedical/33333333.jpg"))); // NOI18N
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 150, 250, 200));

        jTabbedPane1.addTab("  Rendez-Vous", new javax.swing.ImageIcon(getClass().getResource("/cabinetmedical/8a79e246-fca4-4202-a96d-6a243960.jpg")), jPanel3); // NOI18N

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableCr.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID Certificat", "Nbr De Jours", "Details"
            }
        ));
        jScrollPane5.setViewportView(tableCr);

        jPanel4.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 620, 340));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 0, 0));
        jLabel15.setText("Historiques des Certificats Medicals");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cabinetmedical/888888888.jpg"))); // NOI18N
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 140, -1, -1));

        jTabbedPane1.addTab("  Certificat", new javax.swing.ImageIcon(getClass().getResource("/cabinetmedical/pppppppppppppppp.jpg")), jPanel4); // NOI18N

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 0, 0));
        jLabel11.setText("Historiques des Ordonnances : ");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 370, 30));

        tableO.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Ordonance ", "Date Ordonnance ", "Nom_Medicaments", "dose", "description", "Quantite"
            }
        ));
        jScrollPane3.setViewportView(tableO);
        if (tableO.getColumnModel().getColumnCount() > 0) {
            tableO.getColumnModel().getColumn(0).setResizable(false);
        }

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 670, 340));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cabinetmedical/hhhhhhhhhhh.png"))); // NOI18N
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 150, -1, 210));

        jTabbedPane1.addTab("  Ordonance", new javax.swing.ImageIcon(getClass().getResource("/cabinetmedical/1687120.png")), jPanel2); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1ComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTabbedPane1ComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1ComponentHidden

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        try {
            afficherOrdonnance();
            afficherRendez();
            afficherCertif();
            //  Afficher();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void homebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homebtnActionPerformed
        Page_Acceuil p=new Page_Acceuil();
        p.show();// TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_homebtnActionPerformed

    private void ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearActionPerformed
        resultlat.setText("");
        prix.setText("");
        heure.setText("");
        date.setDate(null);
    }//GEN-LAST:event_ClearActionPerformed

    private void ADDUSERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ADDUSERActionPerformed
        if(resultlat.getText().equals("")||this.prix.getText().equals("")
            ||heure.getText().equals("")||date.getDate()==null){
            JOptionPane.showMessageDialog(this,"Tout les Infos sont obligatoire !");
        }
        else{
            int idOrd=Integer.parseInt(this.id_Ord.getSelectedItem().toString());
            int idCer=Integer.parseInt(this.id_Certif.getSelectedItem().toString());
            int idMed=Integer.parseInt(this.id_Med.getText());
            int idPat=Integer.parseInt(this.id_Pat.getText());
            String rslt=resultlat.getText();
            double prix =Double.parseDouble(this.prix.getText());
            String heure=this.heure.getText();
            //recuperer la date en format  sql
            java.sql.Date d1=new java.sql.Date(date.getDate().getTime());
            // transformer la date du sql  en localdate
            LocalDate date=LocalDate.parse(d1.toString());
            try{

                Insert(idOrd, idCer, idMed, idPat, date, heure, prix, rslt);
                JOptionPane.showMessageDialog(this, "Consultation est ajoute avec succes !");
            }catch(Exception e){
                JOptionPane.showMessageDialog(this, e.getMessage());
            }

        }
    }//GEN-LAST:event_ADDUSERActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        // TODO add your handling code here:
        try{
            afficherConsultation();
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Erreur!");
        }
    }//GEN-LAST:event_refreshActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //supprimer par selection
        int z=0;
        int []indices=  tableC.getSelectedRows();

        if(indices.length!=0){
            int x=JOptionPane.showConfirmDialog(this, "Etes vous sur de vouloir faire la suppression !");
            try{

                if(x==0){
                    for(int i=0;i<indices.length;i++){
                        int numeroSelectionner=(Integer)tableC.getValueAt(indices[i], 0);
                        Supprimer(numeroSelectionner);
                        z++;

                    }
                }

            }catch(Exception e){
                JOptionPane.showMessageDialog(this, "erreur !");
                e.printStackTrace();
            }
            if(z==1){
                JOptionPane.showMessageDialog(this, "consultation a été supprimé !");
            }
            if(z>1){
                JOptionPane.showMessageDialog(this, "consultations ont été supprimé !");
            }
        }else{
            JOptionPane.showMessageDialog(this, "Erreur  please select a Row !");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tableCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCMouseEntered

    }//GEN-LAST:event_tableCMouseEntered

    private void tableCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCMouseClicked

        FichePatient p= new FichePatient();
        p.show();
        dispose();

        // TODO add your handling code here:
    }//GEN-LAST:event_tableCMouseClicked
  void AfficherIDOR() {
        try{
        id_Ord.removeAllItems();
        Statement s=c.createStatement();
        ResultSet res=s.executeQuery("select id_ord from ordonnance");
        while(res.next()){
           int idOrd=res.getInt(1);
            String idO=  Integer.toString(idOrd);
            id_Ord.addItem(idO);
        }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
   void AjouterPatientCombox(){
        int x=Salle_Attente.tableRDV.getSelectedRow();
       TableModel model =Salle_Attente.tableRDV.getModel();
     
     String id= model.getValueAt(x, 5).toString();
     int code=Integer.parseInt(id);
          id_Pat.setText(Integer.toString(code));
   
   }
    
       void AfficherIDMED() throws Exception {
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
           if(re.next()){
               idM=re.getInt(1);
           }
           }
           
           
           
           
           
           
           
      id_Med.setText(Integer.toString(idM));
    }
       
       void AfficherIDCER() {
        try{
        id_Certif.removeAllItems();
        Statement s=c.createStatement();
        ResultSet res=s.executeQuery("select id_certif from certificat_medical");
        while(res.next()){
           int idOrd=res.getInt(1);
            String idO=  Integer.toString(idOrd);
            id_Certif.addItem(idO);
        }
        }catch(Exception e){
            e.printStackTrace();
        }
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
            java.util.logging.Logger.getLogger(resultat1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(resultat1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(resultat1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(resultat1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new resultat1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ADDUSER;
    private javax.swing.JButton Clear;
    private com.toedter.calendar.JDateChooser date;
    private javax.swing.JTextField heure;
    private javax.swing.JButton homebtn;
    private javax.swing.JComboBox<String> id_Certif;
    private javax.swing.JLabel id_Med;
    private javax.swing.JComboBox<String> id_Ord;
    private javax.swing.JLabel id_Pat;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField prix;
    private javax.swing.JButton refresh;
    private javax.swing.JTextArea resultlat;
    public static javax.swing.JTable tableC;
    private javax.swing.JTable tableCr;
    private javax.swing.JTable tableO;
    private javax.swing.JTable tableR;
    // End of variables declaration//GEN-END:variables
}
