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

    public BancoDeDados() {
        criaDiretorioCasoNaoExista();
        criaArquivoQuartosCasoNaoExista();
        criaArquivoPacientesCasoNaoExista();
        iniciaMemoriaQuarto();
        iniciaMemoriaPaciente();
    }

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
            System.out.println("\nArquivo n�o encontrado");
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
            System.out.println("\nArquivo n�o encontrado");
        } catch (Exception e) {
            System.out.println("\nErro de Leitura!");
        }
    }

    public void insere(Quarto quarto) {
        memoriaQuarto.append(quarto.toString());
        gravarQuarto();
    }

    public Quarto buscaQuarto(int numeroQuarto) {
        Quarto registro = null;
        String numero, tipo, comodidade, status;

        int inicio, fim, ultimo, primeiro;
        boolean achou = false;
        int procura;

        StringBuffer memoria = memoriaQuarto;

        procura = numeroQuarto;
        inicio = 0;
        while ((inicio != memoria.length()) && (!achou)) {
            ultimo = memoria.indexOf("|", inicio);
            numero = memoria.substring(inicio, ultimo);

            primeiro = ultimo + 1;
            ultimo = memoria.indexOf("|", primeiro);
            tipo = memoria.substring(primeiro, ultimo);

            primeiro = ultimo + 1;
            ultimo = memoria.indexOf("|", primeiro);
            comodidade = memoria.substring(primeiro, ultimo);

            primeiro = ultimo + 1;
            fim = memoria.indexOf("\n", primeiro);
            status = memoria.substring(primeiro, fim);

            registro = new Quarto(Integer.parseInt(numero), tipo, comodidade, status);
            if (procura == registro.getNumero()) {
                achou = true;
            }

            inicio = fim + 1;  // continua procurando o código do quarto
        }
        if (!achou) {
            registro = null;
        }

        return registro;
    }

    public void alteraQuarto(Quarto quarto) {
        String numero;

        int inicio, fim, ultimo;
        boolean achou = false;

        StringBuffer memoria = memoriaQuarto;

        inicio = 0;
        while ((inicio != memoria.length()) && (!achou)) {
            ultimo = memoria.indexOf("|", inicio);
            numero = memoria.substring(inicio, ultimo);

            fim = memoria.indexOf("\n", inicio);

            if (Integer.parseInt(numero) == quarto.getNumero()) {
                memoriaQuarto.replace(inicio, fim + 1, quarto.toString());
                achou = true;
            }

            inicio = fim + 1;  // continua procurando o código do quarto
        }
    }

    public void excluiQuarto(int numero) {
        String numeroReg;
        int inicio, fim, ultimo;
        boolean achou = false;
        if (memoriaQuarto.length() != 0) {   // n�o est� vazia
            inicio = 0;   //inicio come�a na posi��o 0 da vari�vel memoria
            while ((inicio != memoriaQuarto.length()) && (!achou)) {
                ultimo = memoriaQuarto.indexOf("|", inicio);
                numeroReg = memoriaQuarto.substring(inicio, ultimo);

                fim = memoriaQuarto.indexOf("\n", inicio);
                if (numero == Integer.parseInt(numeroReg)) {
                    memoriaQuarto.delete(inicio, fim + 1);
                    gravarQuarto();
                    achou = true;
                }

                inicio = fim + 1;  // continua procurando o código do quarto
            }
        }
    }

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
}
