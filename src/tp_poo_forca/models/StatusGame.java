/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp_poo_forca.models;

import java.util.ArrayList;

/**
 * @author jpcar
 */
public class StatusGame {

    private Word word; // A palavra a ser adivinhada.
    private int nErrors; // Número de erros cometidos pelo jogador.
    private int nCorrects; // Número de acertos do jogador.
    private int nImage; // Índice da imagem representando o estado do jogo.
    private int nWins; // Total de vitórias do jogador.
    private int nLosses; // Total de derrotas do jogador.
    private int n_Total; // Total de jogos jogados.
    private boolean isAceptLatter; // Indica se a letra foi aceita.
    private ArrayList<Character> correctLetters; // Lista de letras corretas adivinhadas.
    private ArrayList<Character> wrongLetters; // Lista de letras incorretas adivinhadas.

    // Construtor padrão que inicializa as listas de letras corretas e incorretas.
    public StatusGame() {
        this.correctLetters = new ArrayList<>(); // Inicializa a lista de letras corretas.
        this.wrongLetters = new ArrayList<>(); // Inicializa a lista de letras incorretas.
    }

    // Construtor que inicializa o jogo com a palavra, número de erros, acertos e imagem correspondente.
    public StatusGame(Word word, int nErrors, int nCorrects, int nImage) {
        this.word = word; // Atribui a palavra ao atributo.
        this.nErrors = nErrors; // Atribui o número de erros.
        this.nCorrects = nCorrects; // Atribui o número de acertos.
        this.nImage = nImage; // Atribui o índice da imagem.
        this.correctLetters = new ArrayList<>(); // Inicializa a lista de letras corretas.
        this.wrongLetters = new ArrayList<>(); // Inicializa a lista de letras incorretas.
    }

    // Construtor que inicializa o número de vitórias e derrotas.
    public StatusGame(int nErrors, int nCorrects, int nTotal) {
        this.nWins = nErrors; // Atribui o número de vitórias.
        this.nLosses = nCorrects; // Atribui o número de derrotas.
        this.n_Total = nTotal; // Atribui o total de jogos jogados.
    }

    // Construtor que inicializa todos os atributos do jogo.
    public StatusGame(Word word, int nErrors, int nCorrects, int nImage, int nWins,
            int nLosses, int nTotal, boolean isAceptLatter, ArrayList<Character> correctLetters, ArrayList<Character> wrongLetters) {
        this.word = word; // Atribui a palavra ao atributo.
        this.nErrors = nErrors; // Atribui o número de erros.
        this.nCorrects = nCorrects; // Atribui o número de acertos.
        this.nImage = nImage; // Atribui o índice da imagem.
        this.nWins = nWins; // Atribui o número de vitórias.
        this.nLosses = nLosses; // Atribui o número de derrotas.
        this.n_Total = nTotal; // Atribui o total de jogos jogados.
        this.isAceptLatter = isAceptLatter; // Atribui se a letra foi aceita.
        this.correctLetters = correctLetters; // Atribui a lista de letras corretas.
        this.wrongLetters = wrongLetters; // Atribui a lista de letras incorretas.
    }

    // Método que retorna a lista de letras corretas.
    public ArrayList<Character> getCorrectLetters() {
        return correctLetters;
    }

    // Método que define a lista de letras corretas.
    public void setCorrectLetters(ArrayList<Character> correctLetters) {
        this.correctLetters = correctLetters;
    }

    // Método que retorna a lista de letras incorretas.
    public ArrayList<Character> getWrongLetters() {
        return wrongLetters;
    }

    // Método que define a lista de letras incorretas.
    public void setWrongLetters(ArrayList<Character> wrongLetters) {
        this.wrongLetters = wrongLetters;
    }

    // Método que retorna o número de vitórias.
    public int getnWins() {
        return nWins;
    }

    // Método que define o número de vitórias.
    public void setnWins(int nWins) {
        this.nWins = nWins;
    }

    // Método que retorna o número de derrotas.
    public int getnLosses() {
        return nLosses;
    }

    // Método que define o número de derrotas.
    public void setnLosses(int nLosses) {
        this.nLosses = nLosses;
    }

    // Método que retorna o total de jogos jogados.
    public int getN_Total() {
        return n_Total;
    }

    // Método que define o total de jogos jogados.
    public void setN_Total(int n_Total) {
        this.n_Total = n_Total;
    }

    // Método que retorna a palavra em jogo.
    public Word getWord() {
        return word;
    }

    // Método que define a palavra em jogo.
    public void setWord(Word word) {
        this.word = word;
    }

    // Método que retorna o número de erros.
    public int getnErrors() {
        return nErrors;
    }

    // Método que retorna se a letra foi aceita.
    public boolean isIsAceptLatter() {
        return isAceptLatter;
    }

    // Método que define se a letra foi aceita.
    public void setIsAceptLatter(boolean isAceptLatter) {
        this.isAceptLatter = isAceptLatter;
    }

    // Método que define o número de erros.
    public void setnErrors(int nErrors) {
        this.nErrors = nErrors;
    }

    // Método que retorna o número de acertos.
    public int getnCorrects() {
        return nCorrects;
    }

    // Método que define o número de acertos.
    public void setnCorrects(int nCorrects) {
        this.nCorrects = nCorrects;
    }

    // Método que retorna o índice da imagem.
    public int getnImage() {
        return nImage;
    }

    // Método que define o índice da imagem.
    public void setnImage(int nImage) {
        this.nImage = nImage;
    }

    // Método que retorna uma representação em String do estado do jogo.
    @Override
    public String toString() {
        return nErrors + "=" + nCorrects + "=" + nImage + "=" + nWins
                + "=" + nLosses + "=" + n_Total + "=" + correctLetters.toString()
                + "=" + wrongLetters.toString();
    }
}
