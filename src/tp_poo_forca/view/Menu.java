/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package tp_poo_forca.view;


import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author jpcar e Paulo Eduardo A
 */
public class Menu extends javax.swing.JPanel {

    /**
     * Creates new form Menu
     */
    private JPanel changeScreen;

    public Menu(JPanel changeScreen) {
        initComponents();
        this.changeScreen = changeScreen;

    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        createNewGame = new javax.swing.JButton();
        loadGames = new javax.swing.JButton();
        multiplayer = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        createNewGame.setBackground(new java.awt.Color(0, 0, 0));
        createNewGame.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        createNewGame.setForeground(new java.awt.Color(255, 255, 255));
        createNewGame.setText("Novo Jogo");
        createNewGame.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        createNewGame.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        createNewGame.setPreferredSize(new java.awt.Dimension(450, 50));
        createNewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createNewGameActionPerformed(evt);
            }
        });

        loadGames.setBackground(new java.awt.Color(0, 0, 0));
        loadGames.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        loadGames.setForeground(new java.awt.Color(255, 255, 255));
        loadGames.setText("Carregar Jogo");
        loadGames.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        loadGames.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loadGames.setPreferredSize(new java.awt.Dimension(450, 50));
        loadGames.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadGamesActionPerformed(evt);
            }
        });

        multiplayer.setBackground(new java.awt.Color(0, 0, 0));
        multiplayer.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        multiplayer.setForeground(new java.awt.Color(255, 255, 255));
        multiplayer.setText("2 Jogadores");
        multiplayer.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        multiplayer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        multiplayer.setPreferredSize(new java.awt.Dimension(450, 50));
        multiplayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                multiplayerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(multiplayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loadGames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createNewGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {createNewGame, loadGames});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(117, Short.MAX_VALUE)
                .addComponent(createNewGame, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(loadGames, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(multiplayer, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {createNewGame, loadGames});

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

    private boolean isNameInvalid(String player) {
        return player == null || player.trim().isEmpty(); // Verifica se o nome é nulo ou vazio
    }


    private void createNewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createNewGameActionPerformed
        try {
            // Solicita o nome do jogador
            String player = JOptionPane.showInputDialog(this, "Digite seu nome:", "Entrada do Nome", JOptionPane.PLAIN_MESSAGE);

            // Verifica se o nome é inválido (vazio ou nulo)
            if (isNameInvalid(player)) {
                JOptionPane.showMessageDialog(this, "Nome não pode ser vazio.", "Erro", JOptionPane.ERROR_MESSAGE);
                return; // Encerra a ação se o nome for inválido
            }

            // Tenta criar a nova tela do jogo da forca com o nome do jogador
            Nivel nv = new Nivel(this.changeScreen, player);
            Transition.changeScreen(nv, this.changeScreen);

        } catch (Exception e) {
            // Captura qualquer erro inesperado e exibe uma mensagem de erro
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao iniciar o jogo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Para fins de depuração, exibe o stack trace no console
        }
    }//GEN-LAST:event_createNewGameActionPerformed

    private void loadGamesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadGamesActionPerformed

        try {
            // Cria a nova tela do jogo da forca com o nome do jogador
            LoadGame lg = new LoadGame(changeScreen);
            Transition.changeScreen(lg, this.changeScreen); // Realiza a transição para a nova tela
        } catch (Exception e) {
            // Captura qualquer erro ao carregar os jogos salvos e exibe uma mensagem de erro
            JOptionPane.showMessageDialog(this, "Erro ao carregar os jogos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Exibe o stack trace no console para depuração
        }


    }//GEN-LAST:event_loadGamesActionPerformed

    private void multiplayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_multiplayerActionPerformed
        // TODO add your handling code here:

        try {
            // Cria a nova tela do jogo da forca com o nome do jogador
            Multiplayer m = new Multiplayer(changeScreen);
            Transition.changeScreen(m, this.changeScreen); // Realiza a transição para a nova tela
        } catch (Exception e) {
            // Captura qualquer erro ao carregar os jogos salvos e exibe uma mensagem de erro
            JOptionPane.showMessageDialog(this, "Erro ao carregar os jogos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Exibe o stack trace no console para depuração
        }
    }//GEN-LAST:event_multiplayerActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createNewGame;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton loadGames;
    private javax.swing.JButton multiplayer;
    // End of variables declaration//GEN-END:variables
}
