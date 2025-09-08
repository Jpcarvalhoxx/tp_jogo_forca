package tp_poo_forca.view;

import java.awt.BorderLayout;
import javax.swing.JPanel;

public class HangmanFrame extends javax.swing.JFrame {

    private JPanel changeScreen;

    public HangmanFrame() {
        initComponents(); // Inicializa os componentes gerados automaticamente
        this.changeScreen = new JPanel(); // Inicializa o painel de troca
        init(); // Método personalizado para configuração inicial
    }
    // Método para realizar a transição de telas no painel de troca


    
    

    public void init() {
        try {
            // Configuração do layout do JFrame
            configureWindow();

            // Verifica e inicializa o painel de troca
            initializeSwapPanel();

            // Inicializa o Menu com o painel de troca
            initializeMenu();

            // Adiciona o painel de troca ao JFrame
            addPanelToFrame();

        } catch (Exception e) {
            // Tratamento de exceção genérico
            System.err.println("Erro ao iniciar a interface: " + e.getMessage());
        }
    }

    private void configureWindow() {
        this.setLayout(new BorderLayout());
        this.setSize(750, 670); // Define o tamanho da janela
        this.setResizable(false); // Desabilita redimensionamento
        this.setLocationRelativeTo(null); // Centraliza a janela
    }

    private void initializeSwapPanel() {
        if (this.changeScreen == null) {
            this.changeScreen = new JPanel(); // Inicializa o painel de troca, se necessário
        }
    }

    private void initializeMenu() {
        Menu m = new Menu(changeScreen); // Inicializa o Menu
        Transition.changeScreen(m,this.changeScreen); // Realiza a transição do menu
    }

    private void addPanelToFrame() {
        if (this.getContentPane().getComponentCount() == 0 || this.getContentPane().getComponent(0) != this.changeScreen) {
            this.add(this.changeScreen, BorderLayout.CENTER); // Adiciona o painel de troca ao JFrame
        }
        this.revalidate(); // Atualiza o layout
        this.repaint(); // Re-pinta o JFrame
    }

    // Método gerado automaticamente para inicialização de componentes
    @SuppressWarnings("unchecked")
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // Remover configuração de layout automático se estiver usando BorderLayout
        // Isso evita conflitos com o layout gerado automaticamente.
        getContentPane().setLayout(new BorderLayout());
        pack();
    }

    public static void main(String args[]) {
        /* Configuração do Look and Feel */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(HangmanFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Criação e exibição do formulário */
        java.awt.EventQueue.invokeLater(() -> {
            new HangmanFrame().setVisible(true);
        });
    }
}
