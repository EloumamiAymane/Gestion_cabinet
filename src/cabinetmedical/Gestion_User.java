/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cabinetmedical;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author HP
 */
public class Gestion_User extends javax.swing.JFrame {
    Connection c;
    File f;
    /**
     * Creates new form Gestion_User
     */
    public Gestion_User() {
         initComponents();
           this.setLocationRelativeTo(null);
             //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          try {
              
            //étape 1: charger la classe de driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //étape 2: créer l'objet de connexion
            c = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Cabinet_medicale", "root", "");
             this.setTitle("Gestion User");
          // afficherRDV();
          afficherMedecin();
          afficherSecretaire();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur:" + e.getMessage());
            e.printStackTrace();
        }

       
    }

    void afficherMedecin() throws Exception{
        DefaultTableModel  m= new DefaultTableModel(null,
                   new String[]{"ID","NOM","Prenom","Age","Sexe","Tel","Adresse","Specialite","CIN"});
           
		Statement stmt =c.createStatement();
	ResultSet rslt=stmt.executeQuery("select * from medecin order by ID_MED ");
		
		while(rslt.next()) {
                    System.out.println(rslt.getString(5));
                    m.addRow(new Object[]{rslt.getInt(1),rslt.getString(2),rslt.getString(3),
                           rslt.getInt(5) ,rslt.getString(4),
                           rslt.getString(6),rslt.getString(7),rslt.getString(8),rslt.getString(9)});
			
		}
               user.setModel(m);
               
                
 
    }
      void afficherSecretaire() throws Exception{
        DefaultTableModel  m= new DefaultTableModel(null,
                   new String[]{"ID","NOM","Prenom","Age","Sexe","Tel","Adresse","CIN"});
           
		Statement stmt =c.createStatement();
	ResultSet rslt=stmt.executeQuery("select * from secretaire order by ID_SEC ");
		
		while(rslt.next()) {
                   
                    m.addRow(new Object[]{rslt.getInt(1),rslt.getString(2),rslt.getString(3),
                           rslt.getInt(5) ,rslt.getString(4),
                           rslt.getString(6),rslt.getString(7),rslt.getString(8)});
			
		}
               sec.setModel(m);
               
                
 
    }
    
    
    
    
    
     void AjouterMedecin(String Name,String Prenom,int age,String Adresse,String Sexe,String Tel,String specialite,String cin) throws Exception{
        PreparedStatement stm=c.prepareStatement("Insert into medecin(NOM_MED,PRENOM_MED,AGE_MED,ADRESSE_MED,SEXE_MED,TEL_MED,Specialite,cin,photo) "
                + "values(?,?,?,?,?,?,?,?,?)");
        stm.setString(1, Name);
        stm.setString(2, Prenom);
        stm.setInt(3, age);
        stm.setString(4, Adresse);
        stm.setString(5, Sexe);
        stm.setString(6, Tel);
        stm.setString(7, specialite);
        stm.setString(8, cin);
        FileInputStream flux= new FileInputStream(f);
        stm.setBinaryStream(9, flux,f.length());
        stm.executeUpdate();
       
    }
      void AjouterSecretaire(String Name,String Prenom,int age,String Adresse,String Sexe,String Tel,String cin) throws Exception{
        PreparedStatement stm=c.prepareStatement("Insert into secretaire(NOM_SEC,PRENOM_SEC,AGE_SEC,ADDRESS_SEC,SEXE_SEC,TEL_SEC,cin_SEC,photo) "
                + "values(?,?,?,?,?,?,?,?)");
        stm.setString(1, Name);
        stm.setString(2, Prenom);
        stm.setInt(3, age);
        stm.setString(4, Adresse);
        stm.setString(5, Sexe);
        stm.setString(6, Tel);
        stm.setString(7, cin);
          FileInputStream flux= new FileInputStream(f);
        stm.setBinaryStream(8, flux,f.length());
        stm.executeUpdate();
       
    }
      
       void SupprimerMedecin(int numeroSelected) throws Exception {
		PreparedStatement stmt =c.prepareStatement("delete from Medecin where id_med=?");
		stmt.setInt(1,numeroSelected );

		 stmt.executeUpdate();
 }
        void SupprimerSecretaire(int numeroSelected) throws Exception {
		PreparedStatement stmt =c.prepareStatement("delete from secretaire where id_sec=?");
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Tel = new javax.swing.JTextField();
        Nom = new javax.swing.JTextField();
        Prenom = new javax.swing.JTextField();
        role = new javax.swing.JComboBox<>();
        GenreF = new javax.swing.JRadioButton();
        GenreH = new javax.swing.JRadioButton();
        cin = new javax.swing.JTextField();
        adresse = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        photo = new javax.swing.JLabel();
        ADDUSER = new javax.swing.JButton();
        Clear = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        specialite = new javax.swing.JTextField();
        age = new javax.swing.JTextField();
        AjouterCompte = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        refresh = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        upload = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        user = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        sec = new javax.swing.JTable();
        Home = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel1.setText("CIN :");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 190, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel2.setText("Nom :");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel3.setText("Prenom :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel4.setText("Sexe :");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel5.setText("Tel :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel6.setText("Role : ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel7.setText("Adresse : ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, -1, -1));

        Tel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(Tel, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 90, 110, -1));

        Nom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(Nom, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 110, -1));

        Prenom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(Prenom, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, 110, -1));

        role.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "medecin", "secretaire" }));
        role.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(role, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 140, 110, -1));

        GenreF.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        GenreF.setText("F");
        jPanel1.add(GenreF, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, -1, -1));

        GenreH.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        GenreH.setText("H");
        jPanel1.add(GenreH, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, -1, -1));

        cin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(cin, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 190, 110, -1));

        adresse.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(adresse, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, 230, 50));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel8.setText("Gestion des utilisateurs");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 280, -1));

        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, 630, -1));

        photo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.add(photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 70, 150, 130));

        ADDUSER.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        ADDUSER.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cabinetmedical/save-_1_ (1).jpg"))); // NOI18N
        ADDUSER.setText("  Save");
        ADDUSER.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ADDUSER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ADDUSERActionPerformed(evt);
            }
        });
        jPanel1.add(ADDUSER, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 230, 120, 40));

        Clear.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        Clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cabinetmedical/delete (1).jpg"))); // NOI18N
        Clear.setText(" Cancel");
        Clear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearActionPerformed(evt);
            }
        });
        jPanel1.add(Clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 230, 120, 40));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel10.setText("Specialite :");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 140, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel11.setText("Age :");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, -1, -1));

        specialite.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(specialite, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, 120, -1));

        age.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(age, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, 110, -1));

        AjouterCompte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cabinetmedical/folder_add (1).jpg"))); // NOI18N
        AjouterCompte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjouterCompteActionPerformed(evt);
            }
        });
        jPanel1.add(AjouterCompte, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 320, 40, 30));

        delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cabinetmedical/delete (1).jpg"))); // NOI18N
        delete.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        jPanel1.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, 40, 30));

        refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cabinetmedical/refresh (1).jpg"))); // NOI18N
        refresh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });
        jPanel1.add(refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, 40, 30));

        jLabel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 963, 50));

        upload.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        upload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cabinetmedical/image_up (1).jpg"))); // NOI18N
        upload.setText("Upload");
        upload.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        upload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadActionPerformed(evt);
            }
        });
        jPanel1.add(upload, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 122, 110, 40));

        user.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "Nom", "Prenom", "Age", "Sexe", "Tel", "Adresse", "Specialite", "CIN"
            }
        ));
        jScrollPane1.setViewportView(user);
        if (user.getColumnModel().getColumnCount() > 0) {
            user.getColumnModel().getColumn(7).setHeaderValue("Specialite");
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 970, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 970, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 209, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Medecin", jPanel3);

        sec.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "Nom", "Prenom", "Age", "Sexe", "Tel", "Adresse", "CIN"
            }
        ));
        sec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                secMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(sec);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 970, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 970, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 209, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("secretaire", jPanel2);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 970, 240));

        Home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cabinetmedical/home-_2_ (1).jpg"))); // NOI18N
        Home.setToolTipText("");
        Home.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeActionPerformed(evt);
            }
        });
        jPanel1.add(Home, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 20, 50, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cabinetmedical/background.jpg"))); // NOI18N
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 1020, 640));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearActionPerformed
        Nom.setText("");
        Prenom.setText("");
        age.setText("");
        Tel.setText("");
        adresse.setText("");
        cin.setText("");
        specialite.setText("");
        photo.setText("");
       
    }//GEN-LAST:event_ClearActionPerformed

    private void uploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadActionPerformed
       try{
            JFileChooser jf=new JFileChooser();
            jf.addChoosableFileFilter(new FileNameExtensionFilter("image file","jpg","png"));
            jf.showOpenDialog(this);
             f=jf.getSelectedFile();
            BufferedImage img=ImageIO.read(f);
            Image img2=img.getScaledInstance(photo.getWidth(),photo.getHeight(),Image.SCALE_SMOOTH);
            photo.setIcon( new ImageIcon(img2));
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Erreur de lecture de l'image");
        }
    }//GEN-LAST:event_uploadActionPerformed

    private void ADDUSERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ADDUSERActionPerformed
         if(Nom.getText().equals("")||this.Prenom.getText().equals("")||this.Tel.getText().equals("")||
              GenreH.getText().equals("")||GenreF.getText().equals("")||this.cin.getText().equals("")||
                 this.age.getText().equals("")||this.adresse.getText().equals("")||this.f==null){
            JOptionPane.showMessageDialog(this,"Tout les Infos sont obligatoire !");
      }
      else{
        try{
        String name=Nom.getText();
            
        String Prenom=this.Prenom.getText();
        int Age=Integer.parseInt(age.getText());
        String Adresse=this.adresse.getText();
        String Sexe="";
        if(GenreH.isSelected()){
            Sexe=GenreH.getText();
        }
        if(GenreF.isSelected()){
            Sexe=GenreF.getText();
        }
      
        String Tel=this.Tel.getText();
        String adresse=this.adresse.getText();
         String specialite=this.specialite.getText();
       
        
        String Cin=cin.getText();
       String role=this.role.getSelectedItem().toString();
       
       if(role.equals("medecin")){
           System.out.println(role);
           AjouterMedecin(name, Prenom, Age, adresse, Sexe, Tel, specialite, Cin);
           JOptionPane.showMessageDialog(this,"Le Medecin est ajouté avec succés !");
       }
              if(role.equals("secretaire")){
     // System.out.println(Cin);
                  AjouterSecretaire(name, Prenom, Age, adresse, Sexe, Tel, Cin);
           JOptionPane.showMessageDialog(this,"Secretaire est ajouté avec succés !");
       }
     
 
        }catch(Exception e){
          JOptionPane.showMessageDialog(this,"Erreur:"+e.getMessage());
          e.printStackTrace();
        }
      }
    }//GEN-LAST:event_ADDUSERActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        try{
           afficherMedecin();
           afficherSecretaire();
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Erreur!");
        }
    }//GEN-LAST:event_refreshActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
              int z=0;
      int []indicesM=  user.getSelectedRows();
      int []indicesS=  sec.getSelectedRows();
      
     if(indicesM.length!=0){
          int x=JOptionPane.showConfirmDialog(this, "Etes vous sur de vouloir faire la suppression !");
         try{
             
         if(x==0){
      for(int i=0;i<indicesM.length;i++){
         int numeroSelectionner=(Integer)user.getValueAt(indicesM[i], 0);
          SupprimerMedecin(numeroSelectionner);
          z++;
     
      }
         }
     
        }catch(Exception e){
             JOptionPane.showMessageDialog(this, "erreur  !");
             e.printStackTrace();
        } 
         if(z==1){
              JOptionPane.showMessageDialog(this, "Medecin a été supprimé !");
         }
         if(z>1){
              JOptionPane.showMessageDialog(this, "Medecins ont été supprimé !");
         }
         
         
         
         
     }else if(indicesS.length!=0){
           int x=JOptionPane.showConfirmDialog(this, "Etes vous sur de vouloir faire la suppression !");
         try{
             
         if(x==0){
      for(int i=0;i<indicesS.length;i++){
         int numeroSelectionne=(Integer)sec.getValueAt(indicesS[i], 0);
          SupprimerSecretaire(numeroSelectionne);
          z++;
     
      }
         }
     
        }catch(Exception e){
             JOptionPane.showMessageDialog(this, "erreur !");
             e.printStackTrace();
        } 
         if(z==1){
              JOptionPane.showMessageDialog(this, "Secretaire a été supprimé !");
         }
         if(z>1){
              JOptionPane.showMessageDialog(this, "Secretaires ont été supprimé !");
         }
     }
     else{
          JOptionPane.showMessageDialog(this, "Erreur  please select a Row !");
     }
    }//GEN-LAST:event_deleteActionPerformed

    private void secMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_secMouseClicked
       
        
    }//GEN-LAST:event_secMouseClicked

    private void HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeActionPerformed

Page_Acceuil p=new Page_Acceuil();
p.show();
dispose();// TODO add your handling code here:
    }//GEN-LAST:event_HomeActionPerformed

    private void AjouterCompteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjouterCompteActionPerformed
        Authentifier a= new Authentifier();
        a.show();
    }//GEN-LAST:event_AjouterCompteActionPerformed

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
            java.util.logging.Logger.getLogger(Gestion_User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gestion_User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gestion_User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gestion_User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gestion_User().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ADDUSER;
    private javax.swing.JButton AjouterCompte;
    private javax.swing.JButton Clear;
    private javax.swing.JRadioButton GenreF;
    private javax.swing.JRadioButton GenreH;
    private javax.swing.JButton Home;
    private javax.swing.JTextField Nom;
    private javax.swing.JTextField Prenom;
    private javax.swing.JTextField Tel;
    private javax.swing.JTextField adresse;
    private javax.swing.JTextField age;
    private javax.swing.JTextField cin;
    private javax.swing.JButton delete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel photo;
    private javax.swing.JButton refresh;
    private javax.swing.JComboBox<String> role;
    private javax.swing.JTable sec;
    private javax.swing.JTextField specialite;
    private javax.swing.JButton upload;
    private javax.swing.JTable user;
    // End of variables declaration//GEN-END:variables
}
