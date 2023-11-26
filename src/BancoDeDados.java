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
}
