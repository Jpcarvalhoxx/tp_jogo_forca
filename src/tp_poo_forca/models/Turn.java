package tp_poo_forca.models;

/**
 *
 * @author jpcar
 */
public class Turn {

    private long id_ms;
    private String player1;
    private String player2;
    private Word word_turn;
    private int nivel;
    private int modeGame;

    private StatusGame statusGame;

    // Construtor padrão
    public Turn() {

    }

    // Construtor com parâmetros
    public Turn(String player, Word word_turn, StatusGame status, long id_ms, int nvl, int modeGame) {
        this.id_ms = id_ms;
        this.player1 = player;
        this.player2 = "";
        this.word_turn = word_turn;
        this.statusGame = status;
        this.nivel = nvl;
        this.modeGame = modeGame;
    }

    // Construtor com parâmetros
    public Turn(String player1, String player2, Word word_turn, StatusGame status, long id_ms, int nvl, int modeGame ){
        this.id_ms = id_ms;
        this.player1 = player1;
        this.player2 = player2;
        this.word_turn = word_turn;
        this.statusGame = status;
        this.nivel = nvl;
        this.modeGame = modeGame;
    }

    // Getters e Setters
    public String getPlayer() {
        return player1;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setPlayer(String player) {
        this.player1 = player;
    }

    public Word getWord_turn() {
        return word_turn;
    }

    public void setWord_turn(Word word_turn) {
        this.word_turn = word_turn;
    }

    public StatusGame getStatusGame() {
        return statusGame;
    }

    public void setStatusGame(StatusGame statusGame) {
        this.statusGame = statusGame;
    }

    public long getId_ms() {
        return id_ms;
    }

    public void setId_ms(long id_ms) {
        this.id_ms = id_ms;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public int getModeGame() {
        return modeGame;
    }

    public void setModeGame(int modeGame) {
        this.modeGame = modeGame;
    }

    @Override
    public String toString() {
        return id_ms + "=" + player1 + "=" + player2 + "=" + word_turn + "=" + modeGame + "=" + statusGame.toString() + "=" + nivel;
    }

}
