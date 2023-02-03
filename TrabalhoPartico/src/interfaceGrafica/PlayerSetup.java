/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceGrafica;

import Enumerations.Estado;
import Excepcions.InvalidNameException;
import Locals.Mapa;
import Management.PlayerManagement;
import execeptions.InvalidPathValueExeception;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Tiago Lopes
 */
public class PlayerSetup extends javax.swing.JFrame {

    PlayerManagement pm = new PlayerManagement();
    Mapa map = new Mapa();
    ImageIcon imagemGiants = new ImageIcon("src/interfaceGrafica/GiantsTeam.png");
    ImageIcon imagemSparks = new ImageIcon("src/interfaceGrafica/sparks.png");
    ImageIcon imagemElf = new ImageIcon("src/interfaceGrafica/elf.png");
    ImageIcon imagemDwarf = new ImageIcon("src/interfaceGrafica/dwarf.png");
    ImageIcon imagemHuman = new ImageIcon("src/interfaceGrafica/human.png");
    ImageIcon imagemOrc = new ImageIcon("src/interfaceGrafica/orc.png");

    /**
     * Creates new form addPlayer
     *
     * @param map
     */
    public PlayerSetup() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        giants = new javax.swing.JButton();
        sparks = new javax.swing.JButton();
        team = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        back = new javax.swing.JButton();
        importPlayers = new javax.swing.JButton();
        output = new java.awt.TextArea();
        addRoutes = new javax.swing.JButton();
        addPlayer = new javax.swing.JButton();
        teamValue = new javax.swing.JLabel();

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Players");

        jLabel1.setText("Name:");

        jLabel4.setText("Character Creator");

        jLabel2.setText("Choose your fate");

        giants.setText("Giants");
        giants.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                giantsActionPerformed(evt);
            }
        });

        sparks.setText("Sparks");
        sparks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sparksActionPerformed(evt);
            }
        });

        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });

        back.setText("back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        importPlayers.setText("Import Players");
        importPlayers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importPlayersActionPerformed(evt);
            }
        });

        addRoutes.setText("routes");
        addRoutes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRoutesActionPerformed(evt);
            }
        });

        addPlayer.setText("ADD");
        addPlayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPlayerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(back)
                        .addGap(213, 213, 213)
                        .addComponent(importPlayers)
                        .addGap(90, 90, 90)
                        .addComponent(addRoutes))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(jLabel4)))
                .addGap(202, 515, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(giants)
                                .addGap(18, 18, 18)
                                .addComponent(sparks))
                            .addComponent(team, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(addPlayer))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(teamValue, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(output, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(team, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(teamValue)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(giants)
                            .addComponent(sparks))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addPlayer))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(output, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(back)
                    .addComponent(importPlayers)
                    .addComponent(addRoutes))
                .addGap(39, 39, 39))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    public void setMapa(Mapa map) {
        this.map = map;
    }
    private void giantsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_giantsActionPerformed
        // TODO add your handling code here:
        imagemGiants.setImage(imagemGiants.getImage().getScaledInstance(
                team.getWidth(),
                team.getHeight(),
                1));
        team.setIcon(imagemGiants);
        teamValue.setText("Giants");
    }//GEN-LAST:event_giantsActionPerformed

    private void sparksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sparksActionPerformed
        // TODO add your handling code here:
        imagemSparks.setImage(imagemSparks.getImage().getScaledInstance(
                team.getWidth(),
                team.getHeight(),
                1));
        team.setIcon(imagemSparks);
        teamValue.setText("Sparks");
    }//GEN-LAST:event_sparksActionPerformed

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_nameActionPerformed

    private void importPlayersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importPlayersActionPerformed
        try {
            // TODO add your handling code here:
            pm.importJson("src/player.json");
            output.setText(pm.listPlayerPerLevel());
        } catch (InvalidPathValueExeception ex) {
            Logger.getLogger(PlayerSetup.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PlayerSetup.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(PlayerSetup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_importPlayersActionPerformed

    private void addRoutesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRoutesActionPerformed
        RouteSetup routes = new RouteSetup();
        routes.setMapa(map);
        routes.setVisible(true);
        dispose();

    }//GEN-LAST:event_addRoutesActionPerformed


    private void addPlayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPlayerActionPerformed
        try {
            pm.addPlayer(name.getText());
            pm.findPlayer(name.getText()).setEquipa(pm.getPlayerTeam(teamValue.getText()));
            output.setText(pm.listPlayerPerLevel());
        } catch (InvalidNameException ex) {
            Logger.getLogger(PlayerSetup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addPlayerActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        //new MapaSetup().setVisible(true);
        new StartGame().setVisible(true);
        dispose();
    }//GEN-LAST:event_backActionPerformed

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
            java.util.logging.Logger.getLogger(PlayerSetup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PlayerSetup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PlayerSetup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlayerSetup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PlayerSetup().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addPlayer;
    private javax.swing.JButton addRoutes;
    private javax.swing.JButton back;
    private javax.swing.JButton giants;
    private javax.swing.JButton importPlayers;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField name;
    private java.awt.TextArea output;
    private javax.swing.JButton sparks;
    private javax.swing.JLabel team;
    private javax.swing.JLabel teamValue;
    // End of variables declaration//GEN-END:variables
}
