/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package tp_poo_forca.view;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import tp_poo_forca.game.Game;
import tp_poo_forca.models.StatusGame;
import tp_poo_forca.models.Turn;
import tp_poo_forca.models.Word;
import utils.GameOperations;

/**
 *
 * @author jpcar e Paulo Eduardo A
 */
public class HangmanTela extends javax.swing.JPanel {

    /**
     * Creates new form HangmanTela
     */


    private List<ButtonKeyboard> buttonsKeyboard;
    private int errors;
    private int corrects;
    private int indexButtons;
    private StatusGame statusGame;
    private Game game;
    private StringBuilder hiddenWord;
    private Map<Integer, ImageIcon> hangmanImages;
    private String player1;
    private String player2;

    public HangmanTela( String player1, String player2, Game g) {

        initComponents();

        this.player1 = player1;
        this.player1Label.setText(player1);
        this.winsLabel.setText("0");
        this.defeatsLabel.setText("0");
        this.totalGameLabel.setText("0");
        this.player2 = player2;

        this.player2Label.setText(player2);

        initGame(g, null);

        // Adicionando o JTextArea ao JFrame com JScrollPane para rolagem
    }

    public HangmanTela( String player1, Game g) {

        initComponents();

        this.player1 = player1;
        this.player1Label.setText(player1);
        this.winsLabel.setText("0");
        this.defeatsLabel.setText("0");
        this.totalGameLabel.setText("0");

        this.player2Label.setText("");

        initGame(g, null);

        // Adicionando o JTextArea ao JFrame com JScrollPane para rolagem
    }

    public HangmanTela( Turn turn, Game g) {
        initComponents();
        this.player1 = turn.getPlayer();
        this.player1Label.setText(player1);
        this.player2 = turn.getPlayer2();
        this.player2Label.setText(player2);
      
        this.winsLabel.setText(turn.getStatusGame().getnWins() + "");
        this.defeatsLabel.setText(turn.getStatusGame().getnLosses() + "");
        this.totalGameLabel.setText(turn.getStatusGame().getN_Total() + "");

        initGame(g, turn);

        // Adicionando o JTextArea ao JFrame com JScrollPane para rolagem
    }

    public Word createWordAndHintDialog() {
        Word wo = null;
        try {
            // Cria os campos de texto para entrada da palavra e da hint
            JTextField wordField = new JTextField();
            JTextField hintField = new JTextField();

            // Organiza os campos em um painel
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(new JLabel("Palavra:"));
            panel.add(wordField);
            panel.add(Box.createVerticalStrut(15)); // Espaçamento entre os campos
            panel.add(new JLabel("Dica:"));
            panel.add(hintField);

            // Exibe o JOptionPane com o painel personalizado
            int result = JOptionPane.showConfirmDialog(this, panel, "Entrada de Palavra e Dica", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            // Verifica se o usuário pressionou "OK"
            if (result == JOptionPane.OK_OPTION) {
                String word = wordField.getText().trim();
                String hint = hintField.getText().trim();

                // Verifica se a palavra ou hint estão vazias
                if (word.isEmpty() || hint.isEmpty() || !Word.isValidWord(word)) {
                    JOptionPane.showMessageDialog(this, "Preencha os todos os campos ou não utilize espaços/caracteres especiais.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return null;
                }

                // Cria o objeto Word com a palavra e a hint
                wo = new Word(word.toUpperCase(), hint.toUpperCase(), GameOperations.GameDifficult.NOT_VALUE.getValue());

            }
        } catch (Exception e) {
            // Captura qualquer erro inesperado e exibe uma mensagem de erro
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao iniciar o jogo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Para fins de depuração, exibe o stack trace no console
            return null;
        }

        return wo;
    }

    public void initGame(Game game, Turn turn) {
        loadHangmanImages();

        boolean isNewGame = (turn == null); // Verifica se é um novo jogo

        if (hangmanImages == null || hangmanImages.isEmpty()) {
            throw new IllegalStateException("Imagens da forca não foram carregadas corretamente.");
        }

        this.img.setIcon(hangmanImages.get(isNewGame ? 0 : turn.getStatusGame().getnImage()));
        this.game = game;
        this.hiddenWord = new StringBuilder();

        // Define os valores iniciais, dependendo se é um novo jogo ou um turno existente
        this.hint.setText(isNewGame ? game.getWord().getHint() : turn.getWord_turn().getHint());
        this.nErros.setText(isNewGame ? "0" : String.valueOf(turn.getStatusGame().getnErrors()));
        this.remainingChances.setText(isNewGame ? "6" : String.valueOf(6 - turn.getStatusGame().getnErrors()));

        this.wrongLetters.setText(isNewGame ? "" : turn.getStatusGame().getWrongLetters().toString());
        this.guessedletters.setText(isNewGame ? "" : turn.getStatusGame().getCorrectLetters().toString());

        this.corrects = isNewGame ? 0 : turn.getStatusGame().getnCorrects();
        this.errors = isNewGame ? 0 : turn.getStatusGame().getnErrors();

        if (isNewGame) {
            // Preenche a palavra coberta com underscores para um novo jogo
            fillCoveredWord(game.getWord().getWord().length());

            this.statusGame = new StatusGame();
        } else {
            this.statusGame = turn.getStatusGame();
            atualizarPalavraCoberta(statusGame);
        }

        initKeyboard();
    }

    private void fillCoveredWord(int length) {
        for (int i = 0; i < length; i++) {
            hiddenWord.append("_ ");
        }
        this.hiddenWordLabel.setText(hiddenWord.toString());
    }

    public void initKeyboard() {
        // Configura o layout do teclado
        this.keyboardGamePanel.removeAll();
        this.keyboardGamePanel.setLayout(new GridLayout(5, 5, 2, 2));
        this.buttonsKeyboard = new ArrayList<>(); // Inicializa a lista de botões

        // Letras do alfabeto (sem o "ç")
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int i = 0;

        // Itera sobre as letras para criar os botões
        for (char letter : letters.toCharArray()) {
            ButtonKeyboard newButton = new ButtonKeyboard(String.valueOf(letter));
            newButton.setId(i); // Atribui um ID ao botão

            if (statusGame.getCorrectLetters().contains(letter) || statusGame.getWrongLetters().contains(letter)) {
                newButton.setEnabled(false);
            }

            // Adiciona um ouvinte de clique ao botão
            newButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Ação a ser executada quando o botão é clicado
                    indexButtons = newButton.getId(); // Atualiza o índice do botão clicado                
                    // Aqui você pode adicionar a lógica que deseja executar quando um botão é clicado.
                }
            });

            // Adiciona o botão ao painel do teclado
            this.keyboardGamePanel.add(newButton);
            // Adiciona o botão à lista de botões do teclado
            this.buttonsKeyboard.add(newButton);

            i++; // Incrementa o índice
        }
    }

    // Método que será chamado quando um botão for clicado
    private void loadHangmanImages() {
        this.hangmanImages = new HashMap<>();
        int newWidth = 280;
        int newHeight = 260;

        for (int i = 0; i <= 6; i++) {
            ImageIcon originalIcon = new ImageIcon(getClass().getResource("/tp_poo_forca/resources/" + i + ".png"));
            Image originalImage = originalIcon.getImage();
            Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(resizedImage);
            this.hangmanImages.put(i, resizedIcon);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        keyboardGamePanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        img = new javax.swing.JLabel();
        img1 = new javax.swing.JLabel();
        newGame = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        remainingChances = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nErros = new javax.swing.JLabel();
        guessedletters = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        wrongLetters = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        turns_win = new javax.swing.JLabel();
        turns_win1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        winsLabel = new javax.swing.JLabel();
        defeatsLabel = new javax.swing.JLabel();
        totalGameLabel = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        newGame1 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        hint = new javax.swing.JLabel();
        hiddenWordLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        player1Label = new javax.swing.JLabel();
        saveGame = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        player2Label = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setBackground(new java.awt.Color(248, 248, 248));
        setMaximumSize(new java.awt.Dimension(739, 511));
        setMinimumSize(new java.awt.Dimension(739, 511));
        setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(248, 248, 248));
        jPanel2.setMaximumSize(new java.awt.Dimension(150, 150));
        jPanel2.setPreferredSize(new java.awt.Dimension(150, 150));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(248, 248, 248));
        jPanel4.setMaximumSize(new java.awt.Dimension(150, 150));
        jPanel4.setPreferredSize(new java.awt.Dimension(471, 180));

        keyboardGamePanel.setBackground(new java.awt.Color(248, 248, 248));
        keyboardGamePanel.setPreferredSize(new java.awt.Dimension(300, 206));
        keyboardGamePanel.setLayout(new javax.swing.BoxLayout(keyboardGamePanel, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(keyboardGamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(keyboardGamePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(jPanel4, java.awt.BorderLayout.SOUTH);

        jPanel5.setBackground(new java.awt.Color(248, 248, 248));
        jPanel5.setMaximumSize(new java.awt.Dimension(150, 150));
        jPanel5.setPreferredSize(new java.awt.Dimension(471, 330));
        jPanel5.setRequestFocusEnabled(false);

        img.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        img1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        newGame.setBackground(new java.awt.Color(153, 0, 0));
        newGame.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        newGame.setForeground(new java.awt.Color(255, 255, 255));
        newGame.setText("Enviar");
        newGame.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        newGame.setBorderPainted(false);
        newGame.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newGame.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newGame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newGameMouseClicked(evt);
            }
        });
        newGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(img1, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(newGame, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addComponent(img, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(img, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(img1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newGame, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel5, java.awt.BorderLayout.NORTH);

        add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel1.setPreferredSize(new java.awt.Dimension(280, 350));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setName(""); // NOI18N

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Chances:");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        remainingChances.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        remainingChances.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        remainingChances.setText("NONE");
        remainingChances.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("N° Erros:");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        nErros.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        nErros.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nErros.setText("NONE");
        nErros.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        guessedletters.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        guessedletters.setForeground(new java.awt.Color(153, 0, 0));

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel4.setText("Acertos:");

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel5.setText("Erros:");

        wrongLetters.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        wrongLetters.setForeground(new java.awt.Color(153, 0, 0));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Turnos");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("V:");

        turns_win.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        turns_win.setForeground(new java.awt.Color(0, 102, 0));
        turns_win.setText("jLabel7");

        turns_win1.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        turns_win1.setForeground(new java.awt.Color(204, 0, 0));
        turns_win1.setText("jLabel7");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("D");

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(153, 0, 0));
        jLabel8.setText("INFORMAÇÕES");

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel10.setText("V:");

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel11.setText("D:");

        winsLabel.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        winsLabel.setForeground(new java.awt.Color(102, 0, 0));
        winsLabel.setText("V:");

        defeatsLabel.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        defeatsLabel.setForeground(new java.awt.Color(102, 0, 0));
        defeatsLabel.setText("D");

        totalGameLabel.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        totalGameLabel.setForeground(new java.awt.Color(102, 0, 0));
        totalGameLabel.setText("T");

        jLabel12.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel12.setText("T: ");

        newGame1.setBackground(new java.awt.Color(0, 0, 255));
        newGame1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        newGame1.setForeground(new java.awt.Color(255, 255, 255));
        newGame1.setText("Novo Jogo");
        newGame1.setToolTipText("");
        newGame1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newGame1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newGame1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGame1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(turns_win, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(94, 94, 94)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(107, 107, 107))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(winsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(defeatsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(totalGameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(66, 66, 66))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(guessedletters, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(wrongLetters, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(newGame1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(nErros))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                            .addGap(21, 21, 21)
                                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                                .addComponent(jLabel4)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(remainingChances)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(jLabel8)))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(turns_win1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(66, 66, 66))))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {guessedletters, wrongLetters});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel4, jLabel5});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {defeatsLabel, totalGameLabel, winsLabel});

        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addGap(39, 39, 39)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(remainingChances, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nErros))
                .addGap(45, 45, 45)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(guessedletters, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wrongLetters)
                .addGap(18, 18, 18)
                .addComponent(newGame1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel10)
                    .addComponent(winsLabel)
                    .addComponent(jLabel11)
                    .addComponent(defeatsLabel)
                    .addComponent(jLabel12)
                    .addComponent(totalGameLabel))
                .addGap(92, 92, 92)
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(turns_win, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(turns_win1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel3, nErros, remainingChances});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel4, jLabel5});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {guessedletters, wrongLetters});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {defeatsLabel, jLabel10, jLabel11, jLabel12, totalGameLabel, winsLabel});

        jPanel7.setBackground(new java.awt.Color(255, 153, 0));
        jPanel7.setPreferredSize(new java.awt.Dimension(300, 270));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setPreferredSize(new java.awt.Dimension(300, 50));
        jPanel8.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 124, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(326, 326, 326)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 419, Short.MAX_VALUE)
                .addGap(83, 83, 83))
        );

        add(jPanel1, java.awt.BorderLayout.EAST);

        jPanel3.setBackground(new java.awt.Color(248, 248, 248));
        jPanel3.setPreferredSize(new java.awt.Dimension(771, 120));

        hint.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        hint.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        hiddenWordLabel.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        hiddenWordLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hiddenWordLabel.setText("- - - - - - - - - - - - - - - - - - - - - - - ");

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel9.setText("Player 1:");

        player1Label.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        player1Label.setForeground(new java.awt.Color(153, 0, 0));
        player1Label.setText("Username");

        saveGame.setBackground(new java.awt.Color(0, 153, 0));
        saveGame.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        saveGame.setForeground(new java.awt.Color(255, 255, 255));
        saveGame.setText("Save");
        saveGame.setToolTipText("");
        saveGame.setBorderPainted(false);
        saveGame.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        saveGame.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveGame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveGameMouseClicked(evt);
            }
        });
        saveGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveGameActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel13.setText("Dica");

        jLabel14.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel14.setText("Player 2:");

        player2Label.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        player2Label.setForeground(new java.awt.Color(153, 0, 0));
        player2Label.setText("Username");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(saveGame)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addGap(220, 220, 220)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(player2Label))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(player1Label)))
                        .addGap(32, 32, 32))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(hint, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
                            .addComponent(hiddenWordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(saveGame, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(player1Label)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(player2Label))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(hint, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(hiddenWordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        add(jPanel3, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents


    private void newGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGameActionPerformed
        // TODO add your handling code here:

        if (this.buttonsKeyboard.get(indexButtons).isEnabled()) {
            this.statusGame = game.makeMove(this.buttonsKeyboard.get(indexButtons).getText().charAt(0));

            if (this.statusGame != null) {
                try {
                    if (!this.statusGame.isIsAceptLatter()) {
                        atualizarErro();

                        if (game.verifyLoseGame()) {
                            this.hiddenWordLabel.setText(statusGame.getWord().getWord());
                            this.totalGameLabel.setText(statusGame.getN_Total() + "");
                            this.defeatsLabel.setText(statusGame.getnLosses() + "");

                            exibirDialogoNovoJogo(false);
                            return;
                        }
                    }

                    if (this.statusGame.isIsAceptLatter()) {

                        atualizarAcerto();
                        atualizarPalavraCoberta(this.statusGame);

                        if (game.verifyWinGame()) {

                            this.totalGameLabel.setText(statusGame.getN_Total() + "");
                            this.winsLabel.setText((statusGame.getnWins()) + "");

                            exibirDialogoNovoJogo(true);
                            return;
                        }
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Formato de número inválido: " + e.getMessage());
                    return; // Ou outra lógica de tratamento
                }

            }

        }
    }//GEN-LAST:event_newGameActionPerformed

    private void atualizarPalavraCoberta(StatusGame r) {
        StringBuilder hiddenWord = new StringBuilder();
        String word = r.getWord().getWord();

        for (char c : word.toCharArray()) {

            if (this.statusGame.getCorrectLetters().contains(c)) {
                hiddenWord.append(c).append(" ");
            } else {
                hiddenWord.append("_ ");
            }
        }

        this.hiddenWordLabel.setText(hiddenWord.toString());
    }

    private void atualizarErro() {
        this.errors++;
        this.nErros.setText((this.errors) + "");
        // Obtém o texto atual das letras erradas
        this.remainingChances.setText((6 - this.errors) + "");

        this.wrongLetters.setText(this.statusGame.getWrongLetters().toString()); // Atualiza o texto do JLabel com as letras erradas
        this.img.setIcon(this.hangmanImages.get(statusGame.getnImage()));
    }

    private void atualizarAcerto() {
        this.corrects++;
        this.guessedletters.setText(this.statusGame.getCorrectLetters().toString());
    }


    private void newGame1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGame1ActionPerformed
        // TODO add your handling code here
        long id_ms = game.getTurn_game().getId_ms();
        int nivel = this.game.getNivel();
        int modeGame = Game.getModeGame();

        Word wo = null;

        if (modeGame == GameOperations.GameMode.MULTIPLAYER.getValue()) {
            wo = createWordAndHintDialog();
            if (wo == null) {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro com a geracao da palavra: ", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        if (modeGame == GameOperations.GameMode.SINGLE_PLAYER.getValue()) {
            this.game = new Game(player1, id_ms, nivel, modeGame);
        } else {
          
            this.game = new Game(wo, player1, this.player2, id_ms, nivel, modeGame);
        }

        initGame(game,
                null);
    }//GEN-LAST:event_newGame1ActionPerformed

    private void newGameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newGameMouseClicked
        // TODO add your handling code here:

        if (this.buttonsKeyboard.get(this.indexButtons).isEnabled()) {
            this.buttonsKeyboard.get(this.indexButtons).setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "Botão desabilitado");
        }
    }//GEN-LAST:event_newGameMouseClicked

    private void saveGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveGameActionPerformed
        // TODO add your handling code here:
        game.saveGameInFile();


    }//GEN-LAST:event_saveGameActionPerformed

    private void saveGameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveGameMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_saveGameMouseClicked

    private void exibirDialogoNovoJogo(boolean status) {
        Object[] options = {"Novo Jogo", "Cancelar"};
        int response = 0;
        if (status) {
            response = JOptionPane.showOptionDialog(
                    null,
                    "Deseja iniciar um novo jogo?",
                    "Você ganhou",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );
        } else {
            response = JOptionPane.showOptionDialog(
                    null,
                    "Deseja iniciar um novo jogo?",
                    "Você perdeu",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    null,
                    options,
                    options[0]);
        }

        if (response == JOptionPane.YES_OPTION) {
            System.out.println("Iniciar novo jogo...");
            long id_ms = game.getTurn_game().getId_ms();
            int nivel = this.game.getNivel();
            int mode = Game.getModeGame();

            Word wo = null;

            if (mode == GameOperations.GameMode.MULTIPLAYER.getValue()) {
                wo = createWordAndHintDialog();
                if (wo == null) {
                    JOptionPane.showMessageDialog(this, "Ocorreu um erro com a geracao da palavra: ", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            if (mode == GameOperations.GameMode.SINGLE_PLAYER.getValue()) {
                this.game = new Game( player1, id_ms, nivel, mode);
            } else {
                this.game = new Game(wo, player1, this.player2, id_ms, nivel, mode);
            }

            initGame(game, null);
            // Coloque aqui a lógica para iniciar um novo jogo
        } else if (response == JOptionPane.NO_OPTION) {
            System.out.println("Cancelado.");
            // Coloque aqui a lógica para cancelar ou fechar
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel defeatsLabel;
    private javax.swing.JLabel guessedletters;
    javax.swing.JLabel hiddenWordLabel;
    private javax.swing.JLabel hint;
    private javax.swing.JLabel img;
    private javax.swing.JLabel img1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel keyboardGamePanel;
    private javax.swing.JLabel nErros;
    private javax.swing.JButton newGame;
    private javax.swing.JButton newGame1;
    private javax.swing.JLabel player1Label;
    private javax.swing.JLabel player2Label;
    private javax.swing.JLabel remainingChances;
    private javax.swing.JButton saveGame;
    private javax.swing.JLabel totalGameLabel;
    private javax.swing.JLabel turns_win;
    private javax.swing.JLabel turns_win1;
    private javax.swing.JLabel winsLabel;
    private javax.swing.JLabel wrongLetters;
    // End of variables declaration//GEN-END:variables
}
