public class Quarto {
    private int numero;
    private String tipo;
    private String comodidades;
    private String status;

    public Quarto(int numero, String tipo, String comodidades, String status) {
        this.numero = numero;
        this.tipo = tipo;
        this.comodidades = comodidades;
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

    public String getComodidades() {
        return comodidades;
    }

    public void setComodidades(String comodidades) {
        this.comodidades = comodidades;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String textoBonito() {
        return String.format("Número: %s\nTipo: %s\nComodidades: %s\nStatus: %s", numero, tipo, comodidades, status);
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s\n", numero, tipo, comodidades, status);
    }
}
