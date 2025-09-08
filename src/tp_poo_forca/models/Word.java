/*
 * Classe Word representa uma palavra e sua dica, com atributos adicionais
 * como o tamanho da palavra (tam) e o nível de dificuldade (nivel).
 */
package tp_poo_forca.models;

import java.util.Objects;

/**
 * Classe Word que encapsula uma palavra, sua dica, o tamanho da palavra
 * e seu nível de dificuldade.
 * 
 * Autor: jpcar
 */
public class Word {

    // Atributos privados que representam a palavra, a dica, o tamanho e o nível
    private String word;
    private String hint;
    private int tam;  // Tamanho da palavra
    private int nivel;  // Nível de dificuldade

    // Construtor padrão sem parâmetros
    public Word() {
    }

    // Construtor que inicializa a palavra, dica e nível. O tamanho é derivado do comprimento da palavra.
    public Word(String word, String hint, int nivel) {
        this.word = word.toUpperCase();
        this.hint = hint.toUpperCase();
        this.tam = word.length(); // O tamanho da palavra é calculado automaticamente
        this.nivel = nivel;
    }

    // Construtor que inicializa todos os atributos, incluindo o tamanho manualmente.
    public Word(String word, String hint, int tam, int nivel) {
        this.word = word;
        this.hint = hint;
        this.tam = tam;
        this.nivel = nivel;
    }

    // Métodos getter e setter para acessar e modificar os atributos privados
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public int getTam() {
        return tam;
    }

    public void setTam(int tam) {
        this.tam = tam;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    // Método toString que retorna a representação em String da classe, separando os campos com "="
    @Override
    public String toString() {
        return word + "=" + hint + "=" + tam + "=" + nivel;
    }

    // Método equals que compara duas instâncias de Word com base na palavra (word)
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;  // Verifica se são o mesmo objeto
        }
        if (o == null || getClass() != o.getClass()) {
            return false;  // Verifica se o outro objeto é nulo ou de outra classe
        }
        Word wo = (Word) o;
        return Objects.equals(this.word, wo.word);  // Compara as palavras (word) entre os dois objetos
    }

    // Método hashCode que gera um hash baseado na palavra (word)
    @Override
    public int hashCode() {
        return Objects.hash(this.word);
    }

    // Método estático que verifica se uma palavra e uma dica são válidas (apenas letras)
    public static boolean isValidWord(String word) {
        // Expressão regular para verificar se a palavra contém apenas letras (A-Z, a-z)
        return word.matches("^[a-zA-Z]+$");
    }
}
