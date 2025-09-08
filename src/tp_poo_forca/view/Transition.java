/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp_poo_forca.view;

import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author jpcar
 */
public class Transition {



    public static void changeScreen(JPanel newPanel, JPanel changeScreen) {
        try {
            changeScreen.removeAll(); // Remove todos os componentes do painel atual
            changeScreen.setLayout(new BorderLayout()); // Garante que o layout está correto

            // Adiciona a nova tela ao painel de troca
            changeScreen.add(newPanel, BorderLayout.CENTER);

            // Atualiza o painel de troca para refletir a nova tela
           changeScreen.revalidate();
           changeScreen.repaint();
        } catch (Exception e) {
            // Exibe uma mensagem de erro caso a transição de tela falhe
            JOptionPane.showMessageDialog(null, "Erro ao realizar a transição de tela: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Exibe o stack trace no console para depuração
        }
    }

}
