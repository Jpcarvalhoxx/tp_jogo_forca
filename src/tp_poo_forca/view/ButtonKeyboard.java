package tp_poo_forca.view;

// Importa classes necessárias do Swing e AWT
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import javax.accessibility.AccessibleContext;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;
import javax.swing.plaf.ComponentUI;

/**
 *
 * @author jpcar e Paulo Eduardo A
 */
// Classe que representa um botão personalizado para o teclado do jogo da forca
public class ButtonKeyboard extends JButton {

    // Atributos da classe
    private boolean active; // Indica se o botão está ativo
    private char latter;    // Armazena a letra correspondente ao botão
    private int id;         // ID único para identificar o botão

    // Construtor padrão que recebe o texto do botão
    public ButtonKeyboard(String text) {
        super(text); // Chama o construtor da classe pai (JButton) com o texto fornecido
        this.active = true; // Inicializa o botão como ativo
        initialize(); // Chama o método de inicialização
    }

    // Inicializa o botão com algumas configurações padrão
    private void initialize() {
        // Define o tamanho preferido do botão
        this.setPreferredSize(new Dimension(40, 40));
        this.setBackground(Color.BLACK); // Define a cor de fundo como preto
        this.setForeground(Color.WHITE); // Define a cor do texto como branca
        this.setFont(new Font("Calibri", Font.BOLD, 15)); // Define a fonte como Calibri, negrito, tamanho 15
        this.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Define o cursor como mão ao passar o mouse
        this.setBorder(new EmptyBorder(0, 0, 0, 0)); // Remove a borda do botão

        // Centraliza o texto no botão
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setVerticalTextPosition(SwingConstants.CENTER);
    }

    // Método para alterar a cor de fundo do botão
    public void setButtonBackgroundColor(Color color) {
        this.setBackground(color); // Define a nova cor de fundo
    }

    // Método para definir o ID do botão
    public void setId(int i) {
        this.id = i; // Define o ID do botão
    }

    // Método para alterar a cor do texto do botão
    public void setButtonForegroundColor(Color color) {
        this.setForeground(color); // Define a nova cor do texto
    }

    // Método para alterar a fonte do botão
    public void setButtonFont(Font font) {
        this.setFont(font); // Define a nova fonte
    }

    // Métodos getter e setter para a variável 'active'
    public boolean isActive() {
        return active; // Retorna o estado ativo do botão
    }

    public void setActive(boolean active) {
        this.active = active; // Define o estado ativo do botão
    }

    // Métodos getter e setter para a variável 'id'
    public int getId() {
        return id; // Retorna o ID do botão
    }

    // Métodos getter e setter para a variável 'latter'
    public char getLatter() {
        return latter; // Retorna a letra associada ao botão
    }

    public void setLatter(char latter) {
        this.latter = latter; // Define a letra associada ao botão
    }

    // Métodos para gerenciar listeners
    private ChangeListener changeListener; // Declaração do listener de mudança
    private ActionListener actionListener; // Declaração do listener de ação
    private ItemListener itemListener; // Declaração do listener de item
    private ChangeEvent changeEvent; // Declaração do evento de mudança
    private ComponentUI ui; // Declaração do UI do componente
    private EventListenerList listenerList = new EventListenerList(); // Lista de listeners

    public ChangeListener getChangeListener() {
        return changeListener; // Retorna o listener de mudança
    }

    public void setChangeListener(ChangeListener changeListener) {
        this.changeListener = changeListener; // Define o listener de mudança
    }

    public ActionListener getActionListener() {
        return actionListener; // Retorna o listener de ação
    }

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener; // Define o listener de ação
    }

    public ItemListener getItemListener() {
        return itemListener; // Retorna o listener de item
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener; // Define o listener de item
    }

    public ChangeEvent getChangeEvent() {
        return changeEvent; // Retorna o evento de mudança
    }

    public void setChangeEvent(ChangeEvent changeEvent) {
        this.changeEvent = changeEvent; // Define o evento de mudança
    }

    public ComponentUI getUi() {
        return ui; // Retorna o UI do componente
    }

    public void setUi(ComponentUI ui) {
        this.ui = ui; // Define o UI do componente
    }

    public EventListenerList getListenerList() {
        return listenerList; // Retorna a lista de listeners
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList; // Define a lista de listeners
    }

    // Método para definir o contexto acessível do botão
    public void setAccessibleContext(AccessibleContext accessibleContext) {
        this.accessibleContext = accessibleContext; // Define o contexto acessível
    }
}
