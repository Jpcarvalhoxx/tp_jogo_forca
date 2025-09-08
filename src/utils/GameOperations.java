/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 * A classe GameOperations contém enums que definem diferentes modos de jogo,
 * níveis de dificuldade e opções para gerar jogos.
 * 
 * @author jpcar e Paulo Eduardo A
 */
 
 
public class GameOperations {

    /**
     * Enum que representa os modos de jogo disponíveis.
     */
    public enum GameMode {
        SINGLE_PLAYER(1), // Modo para um único jogador.
        MULTIPLAYER(2); // Modo para múltiplos jogadores.

        private final int value; // Valor associado ao modo de jogo.

        // Construtor para atribuir o valor ao modo de jogo.
        GameMode(int value) {
            this.value = value;
        }

        // Método que retorna o valor do modo de jogo.
        public int getValue() {
            return value;
        }
    }

    /**
     * Enum que representa os níveis de dificuldade do jogo.
     */
    public enum GameDifficult {
        NOT_VALUE(0), // Valor padrão quando a dificuldade não é definida.
        EASY(1), // Nível de dificuldade fácil.
        MIDDLE(2), // Nível de dificuldade média.
        HARD(3), // Nível de dificuldade difícil.
        RANDOM(-1); // Dificuldade aleatória.

        private final int value; // Valor associado ao nível de dificuldade.

        // Construtor para atribuir o valor ao nível de dificuldade.
        GameDifficult(int value) {
            this.value = value;
        }

        // Método que retorna o valor do nível de dificuldade.
        public int getValue() {
            return value;
        }
    }

    /**
     * Enum que representa as opções para gerar ou carregar jogos.
     */
    public enum GameGenerate {
        NEW_GAME(1), // Opção para iniciar um novo jogo.
        LOAD_GAME(2); // Opção para carregar um jogo existente.

        private final int value; // Valor associado à opção de geração.

        // Construtor para atribuir o valor à opção de geração.
        GameGenerate(int value) {
            this.value = value;
        }

        // Método que retorna o valor da opção de geração.
        public int getValue() {
            return value;
        }
    }

}
