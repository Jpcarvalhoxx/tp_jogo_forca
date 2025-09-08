package tp_poo_forca.game;

// Importação das classes necessárias para o jogo
import tp_poo_forca.models.StatusGame; // Modelo que representa o status do jogo
import tp_poo_forca.models.Word; // Modelo que representa as palavras do jogo
import tp_poo_forca.models.Turn; // Modelo que representa os turnos do jogo
import utils.Database; // Classe utilitária para manipulação de banco de dados

import javax.swing.*; // Importação para componentes da interface gráfica
import java.util.ArrayList; // Importação da classe ArrayList para gerenciamento de listas
import java.util.List; // Importação da interface List
import java.util.Random; // Importação da classe Random para geração de números aleatórios
import utils.GameOperations;

/**
 *
 * @author jpcar e Paulo Eduardo A
 */

public class Game {

    private List<Word> db; // Lista de palavras disponíveis para o jogo
    private Word word; // Palavra atual do jogo
    private Turn turns_game; // Turno atual do jogo
    private static List<Word> words_used = new ArrayList<>(); // Lista de palavras já usadas
    private String player1; // Nome do jogador
    private String player2;
    private static int turn = 0; // Número do turno atual
    private static long idPlayerMS = 0; // ID do jogador
    private static int nWins = 0; // Número de vitórias do jogador
    private static int nLoses = 0; // Número de derrotas do jogador
    private static int nivel; // Nível de dificuldade do jogo
    private static int modeGame;

    // Construtor da classe Game que inicializa o jogo com o nome do usuário, ID e nível de dificuldade
    public Game(String player1, long idPlayerMS, int nvl, int mod) {
        validateUserName(player1); // Valida o nome do usuário
        Game.idPlayerMS = idPlayerMS; // Atribui o ID do jogador
        Game.nivel = nvl; // Define o nível de dificuldade
        this.player1 = player1; // Atribui o nome do jogador
        Game.modeGame = mod;
        if (player2 == null) {
            this.player2 = "NULL";
        }

        if ((nvl == GameOperations.GameDifficult.EASY.getValue() && words_used.size() == 26)
                || (nvl == GameOperations.GameDifficult.MIDDLE.getValue() && words_used.size() == 22)
                || (nvl == GameOperations.GameDifficult.HARD.getValue() && words_used.size() == 35)) {
            words_used = new ArrayList<>(); // Reinicia a lista de palavras usadas
        }

        // Verifica se o número de palavras usadas excede o limite, e reinicia se necessário
        initGame(nvl); // Inicializa o jogo
    }
//inicializao para o modo multiplayer

    public Game(Word w, String player1, String player2, long idPlayerMS, int nvl, int mod) {
        validateUserName(player1); // Valida o nome do usuário
        Game.idPlayerMS = idPlayerMS; // Atribui o ID do jogador
        Game.nivel = nvl; // Define o nível de dificuldade
        this.player1 = player1; // Atribui o nome do jogador
        Game.modeGame = mod;

        this.player2 = player2;
        this.word = w;

        // Verifica se o número de palavras usadas excede o limite, e reinicia se necessário
        initGame(nvl); // Inicializa o jogo
    }

    // Construtor alternativo que recebe um objeto Turn já existente
    public Game(Turn turns_game) {

        this.turns_game = turns_game; // Atribui o turno atual
        this.word = turns_game.getWord_turn(); // Obtém a palavra do turno
        this.player1 = turns_game.getPlayer(); // Obtém o nome do jogador

        // Adiciona a palavra do turno atual à lista de palavras usadas
        words_used.add(turns_game.getWord_turn());
        Game.turn = turns_game.getStatusGame().getN_Total(); // Atualiza o número de turnos
        Game.nWins = turns_game.getStatusGame().getnWins(); // Atualiza o número de vitórias
        Game.nLoses = turns_game.getStatusGame().getnLosses(); // Atualiza o número de derrotas
        Game.nivel = turns_game.getNivel(); // Define o nível de dificuldade
        Game.modeGame = turns_game.getModeGame();

        if (turns_game.getModeGame() == GameOperations.GameMode.SINGLE_PLAYER.getValue()) {
            this.db = Database.database(); // Inicializa a base de dados
        }

    }

    // Método para inicializar o jogo
    private void initGame(int nvl) {

        if (modeGame == GameOperations.GameMode.SINGLE_PLAYER.getValue()) {

            try {
                if (db == null) {
                    db = Database.database(); // Inicializa db diretamente com a base de dados
                }

                // Verifica se a base de dados está vazia
                if (db.isEmpty()) {
                    throw new IllegalStateException("A base de dados de palavras está vazia.");
                }

                // Seleciona uma palavra aleatória com base no nível de dificuldade
                selectRandomWord(nvl);
                // Cria um novo objeto Turn com o jogador, a palavra selecionada e o status do jogo

            } catch (Exception e) {
                showError("Erro ao iniciar o jogo: " + e.getMessage()); // Exibe erro se houver falha
            }

            turns_game = new Turn(player1, word, new StatusGame(word, 0, 0, 0), idPlayerMS, nvl, GameOperations.GameMode.SINGLE_PLAYER.getValue());
        } else {
            turns_game = new Turn(player1, player2, word, new StatusGame(word, 0, 0, 0), idPlayerMS, nvl, GameOperations.GameMode.MULTIPLAYER.getValue());
        }
    }

    // Método para selecionar uma palavra aleatória da base de dados
    private void selectRandomWord(int nvl) {
        Random random = new Random(); // Cria uma instância de Random para seleção aleatória
        Word newWord; // Variável para armazenar a nova palavra selecionada

        // Se o nível de dificuldade for -1, seleciona uma palavra aleatória sem considerar o nível
        if (nvl == GameOperations.GameDifficult.RANDOM.getValue()) {
            do {
                newWord = db.get(random.nextInt(db.size())); // Seleciona aleatoriamente uma palavra
            } while (words_used.contains(newWord)); // Verifica se a palavra já foi usada
        } else {
            do {
                newWord = db.get(random.nextInt(db.size())); // Seleciona aleatoriamente uma palavra
            } while (words_used.contains(newWord) || newWord.getNivel() != nvl); // Verifica uso e nível de dificuldade
        }

        // Adiciona a palavra à lista de usadas e inicializa a palavra do jogo
        words_used.add(newWord); // Adiciona a palavra à lista de usadas
        this.word = newWord; // Atribui a palavra selecionada ao jogo
    }

    // Getter para a palavra atual
    public Word getWord() {
        return word; // Retorna a palavra atual
    }

    // Setter para a palavra atual
    public void setWord(Word word) {
        if (word == null) {
            throw new IllegalArgumentException("A palavra não pode ser nula."); // Lança exceção se a palavra for nula
        }
        this.word = word; // Atribui a nova palavra
    }

    // Getter para o status do jogo
    public StatusGame getStatusGame() {
        return turns_game.getStatusGame(); // Retorna o status do jogo atual
    }

    // Setter para o status do jogo
    public void setStatusGame(StatusGame status) {
        if (status == null) {
            throw new IllegalArgumentException("O status do jogo não pode ser nulo."); // Lança exceção se o status for nulo
        }
        this.turns_game.setStatusGame(status); // Atribui o novo status do jogo
    }

// Método getter para obter a lista de palavras (db)
    public List<Word> getDb() {
        return db; // Retorna a lista de palavras disponíveis
    }

// Método setter para definir a lista de palavras (db)
    public void setDb(List<Word> db) {
        // Lança exceção se a lista for nula ou vazia
        if (db == null || db.isEmpty()) {
            throw new IllegalArgumentException("A lista de palavras não pode ser nula ou vazia.");
        }
        this.db = db; // Atribui a nova lista de palavras
    }

// Método getter para obter o turno atual do jogo
    public Turn getTurn_game() {
        return turns_game; // Retorna o turno atual
    }

// Método setter para definir o turno atual do jogo
    public void setTurn_game(Turn turns_game) {
        // Lança exceção se o turno for nulo
        if (turns_game == null) {
            throw new IllegalArgumentException("O turno do jogo não pode ser nulo.");
        }
        this.turns_game = turns_game; // Atribui o novo turno
    }

// Método getter para obter o nível de dificuldade do jogo
    public int getNivel() {
        return nivel; // Retorna o nível de dificuldade
    }

// Método setter para definir o nível de dificuldade do jogo
    public void setNivel(int nivel) {
        Game.nivel = nivel; // Atribui o novo nível de dificuldade
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public static int getModeGame() {
        return modeGame;
    }

    public static void setModeGame(int modeGame) {
        Game.modeGame = modeGame;
    }

// Método privado para verificar se a letra está presente na palavra atual
    private boolean checkLetter(Character letter) {
        // Verifica se a letra não é nula e se está contida na palavra atual (convertida para maiúscula)
        return letter != null && word.getWord().contains(String.valueOf(Character.toUpperCase(letter)));
    }

// Método para processar uma jogada com a letra fornecida
    public StatusGame makeMove(Character letter) {
        // Lança erro se a letra for nula
        if (letter == null) {
            showError("Letra não pode ser nula.");
            return null;
        }

        letter = Character.toUpperCase(letter); // Converte a letra para maiúscula

        // Verifica se a letra já foi usada anteriormente
        if (turns_game.getStatusGame().getCorrectLetters().contains(letter) || 
            turns_game.getStatusGame().getWrongLetters().contains(letter)) {
            showError("Letra já foi usada."); // Exibe erro se a letra já foi usada
            return null;
        }

        return processLetter(letter); // Processa a letra e retorna o status do jogo
    }

// Método privado para processar a letra e atualizar o status do jogo
    private StatusGame processLetter(Character letter) {
        StatusGame status = turns_game.getStatusGame(); // Obtém o status atual do jogo
        try {
            // Verifica se a letra está na palavra atual
            if (checkLetter(letter)) {
                status.getCorrectLetters().add(letter); // Adiciona a letra correta à lista
                status.setnCorrects(status.getnCorrects() + countLetter(letter)); // Atualiza o número de acertos
                status.setIsAceptLatter(true); // Indica que a letra foi aceita
            } else {
                status.setnErrors(status.getnErrors() + 1); // Atualiza o número de erros
                status.setnImage(status.getnImage() + 1); // Atualiza a imagem do jogo (se aplicável)
                status.getWrongLetters().add(letter); // Adiciona a letra errada à lista
                status.setIsAceptLatter(false); // Indica que a letra não foi aceita
            }
        } catch (Exception e) {
            showError("Erro ao processar a letra: " + e.getMessage()); // Exibe erro se ocorrer uma exceção
        }

        return status; // Retorna o status atualizado do jogo
    }

// Método para verificar se o jogador perdeu o jogo
    public boolean verifyLoseGame() {
        StatusGame status = turns_game.getStatusGame(); // Obtém o status atual do jogo
        // Lança erro se o status for nulo
        if (status == null) {
            showError("O status do jogo está indefinido.");
            return false;
        }

        // Verifica se o número de erros atingiu o limite (6)
        if (status.getnErrors() >= 6) {
            nLoses++; // Incrementa o contador de derrotas
            status.setnLosses(nLoses); // Atualiza o número de derrotas no status do jogo
            turn++; // Incrementa o número total de turnos
            status.setN_Total(turn); // Atualiza o número total de turnos no status do jogo

            return true; // Retorna verdadeiro, indicando que o jogador perdeu
        }
        return false; // Retorna falso se o jogador não perdeu
    }

// Método para verificar se o jogador ganhou o jogo
    public boolean verifyWinGame() {
        StatusGame status = turns_game.getStatusGame(); // Obtém o status atual do jogo
        // Lança erro se o status for nulo
        if (status == null) {
            showError("O status do jogo está indefinido.");
            return false;
        }

        // Verifica se o número de acertos é igual ao tamanho da palavra
        if (status.getnCorrects() == word.getTam()) {
            nWins++; // Incrementa o contador de vitórias
            status.setnWins(nWins); // Atualiza o número de vitórias no status do jogo
            turn++; // Incrementa o número total de turnos
            status.setN_Total(turn); // Atualiza o número total de turnos no status do jogo
            return true; // Retorna verdadeiro, indicando que o jogador ganhou
        }

        return false; // Retorna falso se o jogador não ganhou
    }

// Método privado para contar quantas vezes uma letra aparece na palavra atual
    private int countLetter(char letter) {
        int count = 0; // Inicializa o contador
        try {
            String palavra = word.getWord(); // Obtém a palavra atual
            // Percorre a palavra para contar a ocorrência da letra
            for (int i = 0; i < palavra.length(); i++) {
                if (palavra.charAt(i) == letter) {
                    count++; // Incrementa o contador se a letra for encontrada
                }
            }
        } catch (Exception e) {
            showError("Erro ao contar a letra: " + e.getMessage()); // Exibe erro se ocorrer uma exceção
        }
        return count; // Retorna o número de ocorrências da letra
    }

// Método para salvar o estado do jogo em um arquivo
    public void saveGameInFile() {
        try {
            // Atualiza o status do jogo antes de salvar
            turns_game.getStatusGame().setN_Total(turn);
            turns_game.getStatusGame().setnWins(nWins);
            turns_game.getStatusGame().setnLosses(nLoses);

            Database database = new Database(); // Cria uma instância da classe Database
            // Tenta salvar o turno no arquivo

            if (database.saveTurnToFile(turns_game)) {

                // Exibe mensagem de sucesso se o jogo for salvo
                JOptionPane.showMessageDialog(null, "Jogo salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                showError("Não foi possível salvar o jogo!"); // Exibe erro se não for possível salvar
            }

        } catch (Exception e) {
            showError("Erro ao salvar o jogo: " + e.getMessage()); // Exibe erro se ocorrer uma exceção
        }
    }

// Método privado para validar o nome do usuário
    private void validateUserName(String userName) {
        // Lança exceção se o nome do usuário for nulo ou vazio
        if (userName == null || userName.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do usuário não pode ser nulo ou vazio.");
        }
    }

// Método privado para exibir mensagens de erro
    private void showError(String message) {
        // Exibe uma janela de diálogo com a mensagem de erro
        JOptionPane.showMessageDialog(null, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
