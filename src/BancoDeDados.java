import java.io.*;

public class BancoDeDados {

    private String nomeDiretorio = "banco";
    private File diretorio = new File(nomeDiretorio);

    private String nomeArquivoQuartos = "quartos.txt";
    private String nomeArquivoPacientes = "pacientes.txt";

    private File arquivoQuartos = new File(diretorio, nomeArquivoQuartos);
    private File arquivoPacientes = new File(diretorio, nomeArquivoPacientes);

    private StringBuffer memoriaQuarto = new StringBuffer();
    private StringBuffer memoriaPaciente = new StringBuffer();

    // CONSTRUTOR sera chamado quando for instanciado (new BancoDeDados())
    public BancoDeDados() {
        criaDiretorioCasoNaoExista();
        criaArquivoQuartosCasoNaoExista();
        criaArquivoPacientesCasoNaoExista();
        iniciaMemoriaQuarto();
        iniciaMemoriaPaciente();
    }

    // Metodos de preparacao
    private void criaDiretorioCasoNaoExista() {
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
    }

    private void criaArquivoQuartosCasoNaoExista() {
        if (!arquivoQuartos.exists()) {
            try {
                arquivoQuartos.createNewFile();
            } catch (Exception e) {
                System.out.println("Erro ao criar o arquivo quartos.txt");
            }
        }
    }

    private void criaArquivoPacientesCasoNaoExista() {
        if (!arquivoPacientes.exists()) {
            try {
                arquivoPacientes.createNewFile();
            } catch (Exception e) {
                System.out.println("Erro ao criar o arquivo pacientes.txt");
            }
        }
    }

    private void iniciaMemoriaQuarto() {
        try {
            BufferedReader readerQuarto = new BufferedReader(new FileReader(arquivoQuartos));

            String linha;
            memoriaQuarto.delete(0, memoriaQuarto.length());
            do {
                linha = readerQuarto.readLine();
                if (linha != null) {
                    memoriaQuarto.append(linha + "\n");
                }
            } while (linha != null);
            readerQuarto.close();
        } catch (FileNotFoundException erro) {
            System.out.println("\nArquivo nao encontrado");
        } catch (Exception e) {
            System.out.println("\nErro de Leitura!");
        }
    }

    private void iniciaMemoriaPaciente() {
        try {
            BufferedReader readerPaciente = new BufferedReader(new FileReader(arquivoPacientes));

            String linha;
            memoriaPaciente.delete(0, memoriaPaciente.length());
            do {
                linha = readerPaciente.readLine();
                if (linha != null) {
                    memoriaPaciente.append(linha + "\n");
                }
            } while (linha != null);
            readerPaciente.close();
        } catch (FileNotFoundException erro) {
            System.out.println("\nArquivo nao encontrado");
        } catch (Exception e) {
            System.out.println("\nErro de Leitura!");
        }
    }

    // Operacoes na classe quarto
    public void insereQuarto(Quarto quarto) {
        memoriaQuarto.append(quarto.toString());
        gravarQuarto();
    }

    public Quarto buscaQuarto(int numeroQuarto) {
        Quarto registro = null;
        String numero, tipo, status;

        int inicio, fim, ultimo, primeiro;
        boolean achou = false;
        int procura;

        procura = numeroQuarto;
        inicio = 0;
        while ((inicio != memoriaQuarto.length()) && (!achou)) {
            ultimo = memoriaQuarto.indexOf("|", inicio);
            numero = memoriaQuarto.substring(inicio, ultimo);

            primeiro = ultimo + 1;
            ultimo = memoriaQuarto.indexOf("|", primeiro);
            tipo = memoriaQuarto.substring(primeiro, ultimo);

            primeiro = ultimo + 1;
            fim = memoriaQuarto.indexOf("\n", primeiro);
            status = memoriaQuarto.substring(primeiro, fim);

            registro = new Quarto(Integer.parseInt(numero), tipo, status);
            if (procura == registro.getNumero()) {
                achou = true;
            }

            inicio = fim + 1; // continua procurando o codigo do quarto
        }
        if (!achou) {
            registro = null;
        }

        return registro;
    }

    public Quarto[] buscaQuartos() {
        if (memoriaQuarto.length() == 0) {
            return new Quarto[0];
        }

        int quantidadeLinhas = memoriaQuarto.toString().split("\n").length;
        Quarto[] quartos = new Quarto[quantidadeLinhas];

        Quarto registro = null;
        String numero, tipo, status;

        int inicio, fim, ultimo, primeiro;

        inicio = 0;
        for (int i = 0; i < quantidadeLinhas; i++) {
            ultimo = memoriaQuarto.indexOf("|", inicio);
            numero = memoriaQuarto.substring(inicio, ultimo);

            primeiro = ultimo + 1;
            ultimo = memoriaQuarto.indexOf("|", primeiro);
            tipo = memoriaQuarto.substring(primeiro, ultimo);

            primeiro = ultimo + 1;
            fim = memoriaQuarto.indexOf("\n", primeiro);
            status = memoriaQuarto.substring(primeiro, fim);

            registro = new Quarto(Integer.parseInt(numero), tipo, status);
            quartos[i] = registro;

            inicio = fim + 1; // continua procurando o cÃ³digo do quarto
        }

        return quartos;
    }

    public void alteraQuarto(Quarto quarto) {
        String numero;

        int inicio, fim, ultimo;
        boolean achou = false;

        inicio = 0;
        while ((inicio != memoriaQuarto.length()) && (!achou)) {
            ultimo = memoriaQuarto.indexOf("|", inicio);
            numero = memoriaQuarto.substring(inicio, ultimo);

            fim = memoriaQuarto.indexOf("\n", inicio);

            if (Integer.parseInt(numero) == quarto.getNumero()) {
                memoriaQuarto.replace(inicio, fim + 1, quarto.toString());
                gravarQuarto();
                achou = true;
            }

            inicio = fim + 1; // continua procurando o codigo do quarto
        }
    }

    public void excluiQuarto(int numero) {
        String numeroReg;
        int inicio, fim, ultimo;
        boolean achou = false;
        inicio = 0;
        while ((inicio != memoriaQuarto.length()) && (!achou)) {
            ultimo = memoriaQuarto.indexOf("|", inicio);
            numeroReg = memoriaQuarto.substring(inicio, ultimo);

            fim = memoriaQuarto.indexOf("\n", inicio);
            if (numero == Integer.parseInt(numeroReg)) {
                memoriaQuarto.delete(inicio, fim + 1);
                gravarQuarto();
                achou = true;
            }

            inicio = fim + 1; // continua procurando o codigo do quarto
        }
    }

    // Operacoes na classe paciente
    public void inserePaciente(Paciente paciente) {
        memoriaPaciente.append(paciente.toString());
        gravarPaciente();
    }

    public Paciente buscaPaciente(String nomePaciente) {
        Paciente registro = null;
        String nome, anoNascimento, genero, telefoneResponsavel, numeroQuarto;

        int inicio, fim, ultimo, primeiro;
        boolean achou = false;
        String procura = nomePaciente;

        inicio = 0;
        while ((inicio != memoriaPaciente.length()) && (!achou)) {
            ultimo = memoriaPaciente.indexOf("|", inicio);
            nome = memoriaPaciente.substring(inicio, ultimo);

            primeiro = ultimo + 1;
            ultimo = memoriaPaciente.indexOf("|", primeiro);
            anoNascimento = memoriaPaciente.substring(primeiro, ultimo);

            primeiro = ultimo + 1;
            ultimo = memoriaPaciente.indexOf("|", primeiro);
            genero = memoriaPaciente.substring(primeiro, ultimo);

            primeiro = ultimo + 1;
            ultimo = memoriaPaciente.indexOf("|", primeiro);
            telefoneResponsavel = memoriaPaciente.substring(primeiro, ultimo);

            primeiro = ultimo + 1;
            fim = memoriaPaciente.indexOf("\n", primeiro);
            numeroQuarto = memoriaPaciente.substring(primeiro, fim);

            registro = new Paciente(nome, Integer.parseInt(anoNascimento), genero, telefoneResponsavel,
                    Integer.parseInt(numeroQuarto));
            if (procura.equalsIgnoreCase(registro.getNome())) {
                achou = true;
            }

            inicio = fim + 1; // continua procurando o nome do paciente
        }
        if (!achou) {
            registro = null;
        }

        return registro;
    }

    public void alteraPaciente(Paciente paciente) {
        String nome;

        int inicio, fim, ultimo;
        boolean achou = false;

        inicio = 0;
        while ((inicio != memoriaPaciente.length()) && (!achou)) {
            ultimo = memoriaPaciente.indexOf("|", inicio);
            nome = memoriaPaciente.substring(inicio, ultimo);

            fim = memoriaPaciente.indexOf("\n", inicio);

            if (nome.equalsIgnoreCase(paciente.getNome())) {
                memoriaPaciente.replace(inicio, fim + 1, paciente.toString());
                gravarPaciente();
                achou = true;
            }

            inicio = fim + 1; // continua procurando o nome do paciente
        }
    }

    public void excluiPaciente(String nomePaciente) {
        String nome;

        int inicio, fim, ultimo;
        boolean achou = false;

        inicio = 0;
        while ((inicio != memoriaPaciente.length()) && (!achou)) {
            ultimo = memoriaPaciente.indexOf("|", inicio);
            nome = memoriaPaciente.substring(inicio, ultimo);

            fim = memoriaPaciente.indexOf("\n", inicio);
            if (nome.equalsIgnoreCase(nomePaciente)) {
                memoriaPaciente.delete(inicio, fim + 1);
                gravarPaciente();
                achou = true;
            }

            inicio = fim + 1; // continua procurando o nome do paciente
        }
    }

    public Paciente[] buscaPaciente() {
        if (memoriaPaciente.length() == 0) {
            return new Paciente[0];
        }

        int quantidadeLinhas = memoriaPaciente.toString().split("\n").length;
        Paciente[] pacientes = new Paciente[quantidadeLinhas];

        Paciente registro = null;
        String nome, anoNascimento, genero, telefoneResponsavel, numeroQuarto;

        int inicio, fim, ultimo, primeiro;

        inicio = 0;
        for (int i = 0; i < quantidadeLinhas; i++) {
            ultimo = memoriaPaciente.indexOf("|", inicio);
            nome = memoriaPaciente.substring(inicio, ultimo);

            primeiro = ultimo + 1;
            ultimo = memoriaPaciente.indexOf("|", primeiro);
            anoNascimento = memoriaPaciente.substring(primeiro, ultimo);

            primeiro = ultimo + 1;
            ultimo = memoriaPaciente.indexOf("|", primeiro);
            genero = memoriaPaciente.substring(primeiro, ultimo);

            primeiro = ultimo + 1;
            ultimo = memoriaPaciente.indexOf("|", primeiro);
            telefoneResponsavel = memoriaPaciente.substring(primeiro, ultimo);

            primeiro = ultimo + 1;
            fim = memoriaPaciente.indexOf("\n", primeiro);
            numeroQuarto = memoriaPaciente.substring(primeiro, fim);

            registro = new Paciente(nome, Integer.parseInt(anoNascimento), genero, telefoneResponsavel,
                    Integer.parseInt(numeroQuarto));
            pacientes[i] = registro;

            inicio = fim + 1; // continua procurando o cÃ³digo do quarto
        }

        return pacientes;
    }

    public boolean estaVazio() {
        return memoriaQuarto.length() == 0 && memoriaPaciente.length() == 0;
    }

    // Metodos de persistencia
    private void gravarQuarto() {
        try {
            BufferedWriter escrita = new BufferedWriter(new FileWriter(arquivoQuartos));
            escrita.write(memoriaQuarto.toString());
            escrita.flush();
            escrita.close();
        } catch (Exception e) {
            System.out.println("\nErro de gravacao!");
        }
    }

    private void gravarPaciente() {
        try {
            BufferedWriter escrita = new BufferedWriter(new FileWriter(arquivoPacientes));
            escrita.write(memoriaPaciente.toString());
            escrita.flush();
            escrita.close();
        } catch (Exception e) {
            System.out.println("\nErro de gravacao!");
        }
    }
}
