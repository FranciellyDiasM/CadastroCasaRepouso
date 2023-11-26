import java.io.*;

public class BancoDeDados {

    private String nomeDiretorio = "banco";
    private File diretorio = new File(nomeDiretorio);

    private String nomeArquivoQuartos = "quartos.txt";
    private String nomeArquivoPacientes = "pacientes.txt";


    private File arquivoQuartos = new File(diretorio, nomeArquivoQuartos);
    private File arquivoPacientes = new File(diretorio, nomeArquivoPacientes);

    public BancoDeDados() {
        criaDiretorioCasoNaoExista();
        criaArquivoQuartosCasoNaoExista();
        criaArquivoPacientesCasoNaoExista();
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

    public void insere(Quarto quarto) {
        try {
            BufferedWriter escrita = new BufferedWriter(new FileWriter(arquivoQuartos, true));
            escrita.write(quarto.toString());
            escrita.flush();
            escrita.close();
        } catch (Exception e) {
            System.out.println("\nErro de gravacao!");
        }
    }

    public Quarto buscaQuarto(int numeroQuarto) {
        Quarto registro = null;
        String numero, tipo, comodidade, status;

        int inicio, fim, ultimo, primeiro;
        boolean achou = false;
        int procura;

        StringBuffer memoria = carregarConteudo(arquivoQuartos);

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

    private StringBuffer carregarConteudo(File arquivo) {
        StringBuffer retorno = new StringBuffer();
        try {
            BufferedReader arquivoEntrada = new BufferedReader(new FileReader(arquivo));
            String linha = "";
            do {
                linha = arquivoEntrada.readLine();
                if (linha != null) {
                    retorno.append(linha + "\n");
                }
            } while (linha != null);
            arquivoEntrada.close();
        } catch (Exception e) {
            System.out.println("\nErro de Leitura!");
        }

        return retorno;
    }
}
