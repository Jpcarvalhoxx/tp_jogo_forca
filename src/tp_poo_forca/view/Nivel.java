/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package tp_poo_forca.view;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import tp_poo_forca.game.Game;
import utils.GameOperations;

/**
 *
 * @author jpcar e Paulo Eduardo A
 */
public class Nivel extends javax.swing.JPanel {

    private JPanel changeScreen;
    private String player1;

    public Nivel(JPanel changeScreen, String player) {
        this.changeScreen = changeScreen;
        this.player1 = player;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        rand = new javax.swing.JButton();
        middle = new javax.swing.JButton();
        hard = new javax.swing.JButton();
        easy = new javax.swing.JButton();
        back = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        rand.setBackground(new java.awt.Color(0, 0, 0));
        rand.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        rand.setForeground(new java.awt.Color(255, 255, 255));
        rand.setText("Aleatório");
        rand.setBorderPainted(false);
        rand.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randActionPerformed(evt);
            }
        });

        middle.setBackground(new java.awt.Color(0, 0, 0));
        middle.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        middle.setForeground(new java.awt.Color(255, 255, 255));
        middle.setText("Médio");
        middle.setBorderPainted(false);
        middle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        middle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                middleActionPerformed(evt);
            }
        });

        hard.setBackground(new java.awt.Color(0, 0, 0));
        hard.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        hard.setForeground(new java.awt.Color(255, 255, 255));
        hard.setText("Difícil");
        hard.setBorderPainted(false);
        hard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hardActionPerformed(evt);
            }
        });

        easy.setBackground(new java.awt.Color(0, 0, 0));
        easy.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        easy.setForeground(new java.awt.Color(255, 255, 255));
        easy.setText("Fácil");
        easy.setBorderPainted(false);
        easy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        easy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                easyActionPerformed(evt);
            }
        });

        back.setBackground(new java.awt.Color(0, 0, 0));
        back.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        back.setForeground(new java.awt.Color(255, 255, 255));
        back.setText("<<");
        back.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        back.setBorderPainted(false);
        back.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        back.setFocusPainted(false);
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(812, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(easy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(middle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rand, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(171, 171, 171))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(easy, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(middle, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(hard, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rand, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(114, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {easy, hard, middle, rand});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void randActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_randActionPerformed
        // TODO add your handling code here:
        try {
            // Inicia o jogo com dificuldade aleatoria (nível 1)
            HangmanTela hgTela = new HangmanTela( this.player1, new Game(this.player1, System.currentTimeMillis(),
                    GameOperations.GameDifficult.RANDOM.getValue(),
                    GameOperations.GameMode.SINGLE_PLAYER.getValue()));

            Transition.changeScreen(hgTela, this.changeScreen); // Realiza a transição para a nova tela do jogo
        } catch (Exception e) {
            // Exibe uma mensagem de erro caso algo dê errado ao iniciar o jogo
            JOptionPane.showMessageDialog(this, "Erro ao iniciar o jogo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Exibe o stack trace no console para depuração
        }
    }//GEN-LAST:event_randActionPerformed

    private void middleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_middleActionPerformed
        // TODO add your handling code here:
        try {
            // Inicia o jogo com dificuldade medio (nível 2)
            HangmanTela hgTela = new HangmanTela( this.player1, new Game(this.player1, System.currentTimeMillis(),
                    GameOperations.GameDifficult.MIDDLE.getValue(),
                    GameOperations.GameMode.SINGLE_PLAYER.getValue()));

            Transition.changeScreen(hgTela, this.changeScreen); // Realiza a transição para a nova tela do jogo
        } catch (Exception e) {
            // Exibe uma mensagem de erro caso algo dê errado ao iniciar o jogo
            JOptionPane.showMessageDialog(this, "Erro ao iniciar o jogo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Exibe o stack trace no console para depuração
        }
    }//GEN-LAST:event_middleActionPerformed

    private void hardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hardActionPerformed
        // TODO add your handling code here:
        try {
            // Inicia o jogo com dificuldade dificl (nível 3)
            HangmanTela hgTela = new HangmanTela( this.player1, new Game(this.player1, System.currentTimeMillis(),
                    GameOperations.GameDifficult.HARD.getValue(),
                    GameOperations.GameMode.SINGLE_PLAYER.getValue()));

            Transition.changeScreen(hgTela, this.changeScreen); // Realiza a transição para a nova tela do jogo
        } catch (Exception e) {
            // Exibe uma mensagem de erro caso algo dê errado ao iniciar o jogo
            JOptionPane.showMessageDialog(this, "Erro ao iniciar o jogo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Exibe o stack trace no console para depuração
        }
    }//GEN-LAST:event_hardActionPerformed

    private void easyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_easyActionPerformed
        // TODO add your handling code here:
        try {
            // Inicia o jogo com dificuldade fácil (nível 1)
            HangmanTela hgTela = new HangmanTela(this.player1, new Game(this.player1, System.currentTimeMillis(),
                    GameOperations.GameDifficult.EASY.getValue(),
                    GameOperations.GameMode.SINGLE_PLAYER.getValue()));
            Transition.changeScreen(hgTela, this.changeScreen); // Realiza a transição para a nova tela do jogo
        } catch (Exception e) {
            // Exibe uma mensagem de erro caso algo dê errado ao iniciar o jogo
            JOptionPane.showMessageDialog(this, "Erro ao iniciar o jogo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Exibe o stack trace no console para depuração
        }
    }//GEN-LAST:event_easyActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        Menu m = new Menu(changeScreen);
        // Limpa e adiciona o Menu ao painel de troca usando o método transition
        Transition.changeScreen(m, changeScreen);
    }//GEN-LAST:event_backActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JButton easy;
    private javax.swing.JButton hard;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton middle;
    private javax.swing.JButton rand;
    // End of variables declaration//GEN-END:variables
}
