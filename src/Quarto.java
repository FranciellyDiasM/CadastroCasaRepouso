public class Quarto {
    private int numero;
    private String tipo;
    private String status;

    // CONSTRUTOR
    public Quarto(int numero, String tipo, String status) {
        this.numero = numero;
        this.tipo = tipo;
        this.status = status;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String textoBonito() {
        return String.format("Numero: %s\nTipo: %s\nStatus: %s\n", numero, tipo, status);
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%s\n", numero, tipo, status);
    }
}
