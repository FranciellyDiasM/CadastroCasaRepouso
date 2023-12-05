public class Paciente {
    private String nome;
    private int anoNascimento;
    private String genero;
    private String telefoneResponsavel;
    private int numeroQuarto;

    public Paciente(String nome, int anoNascimento, String genero, String telefoneResponsavel, int numeroQuarto) {
        this.nome = nome;
        this.anoNascimento = anoNascimento;
        this.genero = genero;
        this.telefoneResponsavel = telefoneResponsavel;
        this.numeroQuarto = numeroQuarto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefoneResponsavel() {
        return telefoneResponsavel;
    }

    public void setTelefoneResponsavel(String telefoneResponsavel) {
        this.telefoneResponsavel = telefoneResponsavel;
    }

    public int getNumeroQuarto() {
        return numeroQuarto;
    }

    public void setNumeroQuarto(int numeroQuarto) {
        this.numeroQuarto = numeroQuarto;
    }

    public String textoBonito() {
        return String.format(
                "Nome: %s\nAno de Nascimento: %s\nGenero: %s\nTelefone do Responsavel: %s\nNumero do Quarto: %s\n",
                nome, anoNascimento, genero, telefoneResponsavel, numeroQuarto);
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s|%s\n", nome, anoNascimento, genero, telefoneResponsavel, numeroQuarto);
    }
}
