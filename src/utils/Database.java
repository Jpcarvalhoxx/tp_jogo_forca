package utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;


import tp_poo_forca.models.StatusGame;
import tp_poo_forca.models.Turn;

import tp_poo_forca.models.Word;

import java.util.List;

/**
 *
 * @author jpcar e Paulo Eduardo A
 */

public class Database {

    // Caminho do arquivo
    private final String filePath = "./src/utils/gameData.txt";

    public Database() {
    }

    // Salva o StatusGame em um arquivo de texto
    public boolean saveTurnToFile(Turn s) {

        if (s == null) {
            System.err.println("Erro: Turn é null. Não pode ser salvo.");
            return false;
        }

        try {
            if (updateLine(s)) {
                return true;
            }

            try (FileWriter writer = new FileWriter(filePath, true)) { // true para modo de "append"
                writer.write(s.toString() + System.lineSeparator()); // Salva o estado do jogo como uma linha no arquivo
            } catch (IOException e) {
                System.err.println("Erro ao salvar o jogo no arquivo: " + e.getMessage());
                e.printStackTrace();
                return false;
            }

        } catch (IOException e) {
            System.err.println("Erro ao atualizar a linha do arquivo: " + e.getMessage());
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // Recupera os dados do arquivo e retorna uma lista de Turn
    public ArrayList<Turn> loadTurnsFromFile() {
        ArrayList<Turn> statusList = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            System.err.println("Erro: O arquivo " + filePath + " não existe.");
            return statusList;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Turn s = parseStatusGame(line);
                    statusList.add(s);
                } catch (Exception e) {
                    System.err.println("Erro ao processar a linha: " + line);
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            e.printStackTrace();
        }
        return statusList;
    }

    // Converte uma string do arquivo para um objeto Turn
    private Turn parseStatusGame(String line) throws Exception {
        // Verifica se a linha não é nula ou vazia
        if (line == null || line.trim().isEmpty()) {
            throw new IllegalArgumentException("A linha fornecida está vazia ou nula: " + line);
        }

        String[] parts = line.split("=");

        // Verifica se o array gerado pelo split tem o tamanho esperado
        if (parts.length < 17) {
            throw new IllegalArgumentException("Dados incompletos na linha: " + line);
        }

        Turn s = new Turn();

        try {
            // Tratamento de possíveis exceções de conversão numérica
            s.setId_ms(Long.parseLong(parts[0].trim())); // Remove possíveis espaços antes de converter
            s.setPlayer(parts[1].trim());
            s.setPlayer2(parts[2].trim());

            // Validação do modo de jogo (esperando números inteiros)
            s.setModeGame(Integer.parseInt(parts[7].trim()));

            // Criação da instância de Word
            Word w = new Word(parts[3].trim(), parts[4].trim(), Integer.parseInt(parts[5].trim()), Integer.parseInt(parts[6].trim()));
            s.setWord_turn(w);

            // Criação da instância de StatusGame
            StatusGame st = new StatusGame(
                    w,
                    Integer.parseInt(parts[8].trim()),
                    Integer.parseInt(parts[9].trim()),
                    Integer.parseInt(parts[10].trim()),
                    Integer.parseInt(parts[11].trim()),
                    Integer.parseInt(parts[12].trim()),
                    Integer.parseInt(parts[13].trim()),
                    false,
                    convertStringToArrayList(parts[14].trim()),
                    convertStringToArrayList(parts[15].trim())
            );

            s.setStatusGame(st);

            // Definir nível
            s.setNivel(Integer.parseInt(parts[16].trim()));

        } catch (NumberFormatException e) {
            // Trata exceções de conversão de números
            System.err.println("Erro ao converter números no parseStatusGame: " + e.getMessage());
            throw new Exception("Erro no formato numérico dos dados", e);

        } catch (ArrayIndexOutOfBoundsException e) {
            // Trata exceções relacionadas ao acesso indevido de posições no array
            System.err.println("Erro ao acessar elementos no array no parseStatusGame: " + e.getMessage());
            throw new Exception("Erro nos dados fornecidos: número insuficiente de parâmetros", e);

        } catch (Exception e) {
            // Captura qualquer outra exceção não tratada
            System.err.println("Erro inesperado no parseStatusGame: " + e.getMessage());
            throw new Exception("Erro inesperado ao processar os dados", e);
        }

        return s;
    }

    private static ArrayList<Character> convertStringToArrayList(String listStr) {
        ArrayList<Character> list = new ArrayList<>();
        if (listStr != null && !listStr.isEmpty()) {
            for (char c : listStr.replaceAll("[\\[\\],\\s]", "").toCharArray()) {
                list.add(c);
            }
        } else {
            System.err.println("Erro: String de entrada é null ou vazia.");
        }
        return list;
    }

    private boolean updateLine(Turn s) throws IOException {
        String searchText = s.getId_ms() + "=" + s.getPlayer();
        String newLineContent = s.toString();

        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo para atualização: " + e.getMessage());
            throw e;
        }

        boolean lineModified = false;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.contains(searchText)) {
                lines.set(i, newLineContent);
                lineModified = true;
                break;
            }
        }

        if (lineModified) {
            try {
                Files.write(Paths.get(filePath), lines);
            } catch (IOException e) {
                System.err.println("Erro ao escrever no arquivo após modificação: " + e.getMessage());
                throw e;
            }
            return true;
        }
        return false;
    }

    public static List<Word> database() {
        return Arrays.asList(
                // Nível 1: Palavras simples e comuns
                new Word("ELEFANTE", "Um grande mamífero com tromba", 1),
                new Word("MONTANHA", "Formação geológica alta", 1),
                new Word("OCEANO", "Grande corpo de água salgada", 1),
                new Word("FUTEBOL", "Esporte com uma bola e dois gols", 1),
                new Word("CARRO", "Veículo com quatro rodas", 1),
                new Word("GIRAFA", "Animal com pescoço longo", 1),
                new Word("BIBLIOTECA", "Local de empréstimo de livros", 1),
                new Word("CANGURU", "Animal marsupial da Austrália", 1),
                new Word("MICROFONE", "Dispositivo para amplificar sons", 1),
                new Word("TELEFONE", "Dispositivo de comunicação à distância", 1),
                new Word("RELOGIO", "Dispositivo para medir o tempo", 1),
                new Word("BICICLETA", "Veículo de duas rodas movido a pedal", 1),
                new Word("DINOSSAURO", "Animal pré-histórico extinto", 1),
                new Word("CACHORRO", "Animal de estimação leal e amigo do homem", 1),
                new Word("JARDIM", "Área de cultivo de plantas e flores", 1),
                new Word("OVO", "Alimento produzido por aves", 1),
                new Word("ROSAS", "Flor símbolo do amor", 1),
                new Word("URSO", "Grande mamífero com pelagem espessa", 1),
                new Word("XAXIM", "Planta usada para fazer vasos", 1),
                new Word("ZEBRA", "Animal com listras pretas e brancas", 1),
                new Word("ALFABETO", "Conjunto de letras de um idioma", 1),
                new Word("BOLSA", "Receptáculo para carregar objetos", 1),
                new Word("CEREAL", "Grão utilizado na alimentação", 1),
                new Word("DANÇA", "Movimento rítmico do corpo", 1),
                new Word("LIVRO", "Obra impressa com páginas encadernadas", 1),
                new Word("VIAJAR", "Ato de se deslocar para um lugar diferente", 1),
                // Nível 2: Palavras com maior complexidade ou menos usuais
                new Word("COMPUTADOR", "Dispositivo para processar dados", 2),
                new Word("AEROPORTO", "Local onde aviões decolam e pousam", 2),
                new Word("ESCRITOR", "Pessoa que cria textos", 2),
                new Word("TECLADO", "Dispositivo de entrada de dados", 2),
                new Word("INTERNET", "Rede mundial de computadores", 2),
                new Word("GUITARRA", "Instrumento musical de cordas", 2),
                new Word("SATELITE", "Objeto que orbita um planeta", 2),
                new Word("CAVALHEIRO", "Homem educado e cortês", 2),
                new Word("UNIVERSIDADE", "Instituição de ensino superior", 2),
                new Word("JORNALISTA", "Pessoa que escreve notícias", 2),
                new Word("CIENTISTA", "Pessoa que estuda ciência", 2),
                new Word("TERREMOTO", "Movimento sísmico da terra", 2),
                new Word("HOSPITAL", "Local de atendimento médico", 2),
                new Word("ILUMINACAO", "Ação ou efeito de iluminar", 2),
                new Word("ARQUITETO", "Profissional que projeta edificações", 2),
                new Word("PLANETA", "Corpo celeste que orbita uma estrela", 2),
                new Word("MARATONA", "Corrida de longa distância", 2),
                new Word("LANTERNA", "Dispositivo portátil de iluminação", 2),
                new Word("JOGO", "Atividade lúdica ou competitiva", 2),
                new Word("SOLIDARIEDADE", "Apoio e empatia com os outros", 2),
                new Word("PROJETO", "Plano para a realização de algo", 2),
                new Word("RELACIONAMENTO", "Vínculo entre duas ou mais pessoas", 2),
                // Nível 3: Palavras complexas ou relacionadas a temas científicos/técnicos
                new Word("QUIASMA", "Cruzamento de estruturas biológicas", 3),
                new Word("ANAMNESE", "Revisão de histórico médico do paciente", 3),
                new Word("CATASTROFE", "Desastre natural ou grande calamidade", 3),
                new Word("PARADIGMA", "Modelo ou padrão a ser seguido", 3),
                new Word("EPIDEMIA", "Propagação rápida de uma doença", 3),
                new Word("EXCÊNTRICO", "Fora do comum ou peculiar", 3),
                new Word("GALAXIA", "Conjunto enorme de estrelas e planetas", 3),
                new Word("ACELERADOR", "Dispositivo que aumenta a velocidade", 3),
                new Word("BIODEGRADAVEL", "Que se decompõe naturalmente", 3),
                new Word("SINERGIA", "Cooperação entre elementos para um efeito maior", 3),
                new Word("CROMOSSOMO", "Estrutura que carrega os genes", 3),
                new Word("LEUCEMIA", "Tipo de câncer no sangue", 3),
                new Word("HIPOTENUSA", "Maior lado de um triângulo retângulo", 3),
                new Word("OXIDACAO", "Reação química envolvendo oxigênio", 3),
                new Word("PRAGMATISMO", "Filosofia focada em resultados práticos", 3),
                new Word("RETROATIVO", "Que tem efeito sobre um período anterior", 3),
                new Word("TERRITORIO", "Área delimitada, controlada por um país", 3),
                new Word("ULTRASSOM", "Técnica de imagem médica usando som", 3),
                new Word("VELOCIDADE", "Taxa de mudança de posição", 3),
                new Word("PROFISSIONAL", "Pessoa habilitada em uma área de atuação", 3),
                new Word("TANGENTE", "Reta que toca uma curva em um único ponto", 3),
                new Word("UNIVERSAL", "Que se aplica a todos ou ao mundo inteiro", 3),
                new Word("VERTICAL", "Que se estende de cima para baixo", 3),
                new Word("ZODIACO", "Círculo de constelações usadas na astrologia", 3),
                new Word("ASTRONOMIA", "Estudo dos corpos celestes", 3),
                new Word("BIOQUIMICA", "Estudo das substâncias químicas dos seres vivos", 3),
                new Word("DENSIDADE", "Relação entre massa e volume de um corpo", 3),
                new Word("ELETRONICO", "Dispositivo que usa eletricidade para funcionar", 3),
                new Word("GASEIFICACAO", "Transformação de líquido em gás", 3),
                new Word("IMPERMEAVEL", "Que não deixa passar água", 3),
                new Word("ISOMERIA", "Fenômeno de compostos com mesma fórmula química", 3),
                new Word("JUSTAPOSICAO", "Ato de colocar lado a lado", 3),
                new Word("MICROSCOPIO", "Instrumento para visualizar objetos muito pequenos", 3),
                new Word("NEURONAL", "Relacionado aos neurônios", 3),
                new Word("OSMORREGULACAO", "Processo de regulação da água e sais no corpo", 3),
                new Word("POLIFONIA", "Combinação de sons ou vozes diferentes", 3),
                new Word("RAIOS-X", "Radiação usada para imagens internas do corpo", 3),
                new Word("SALINIDADE", "Concentração de sal em um líquido", 3),
                new Word("TELEPATIA", "Comunicação direta entre mentes", 3),
                new Word("URBANISMO", "Planejamento do desenvolvimento das cidades", 3)
        );
    }

}
