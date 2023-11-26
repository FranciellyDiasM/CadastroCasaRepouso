import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static BancoDeDados bancoDeDados = new BancoDeDados();

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.print("\n\n\n");
            System.out.println("Escolha um tipo");
            System.out.println("1 - Quarto");
            System.out.println("2 - Paciente");
            System.out.println("0 - Sair");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    menuQuarto();
                    break;
                case 2:
                    menuPaciente();
                    break;
                case 0:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } while (opcao != 0);
    }

    private static void menuQuarto() {
        System.out.println("Escolha uma operação");
        System.out.println("1 - Inserir Quarto");
        System.out.println("2 - Buscar Quarto");
        System.out.println("3 - Alterar Quarto");
        System.out.println("4 - Excluir Quarto");

        int operacao = scanner.nextInt();

        switch (operacao) {
            case 1:
                insereQuarto();
                break;
            case 2:
                System.out.println("Buscar Quarto");
                break;
            case 3:
                System.out.println("Alterar Quarto");
                break;
            case 4:
                System.out.println("Excluir Quarto");
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
    }

    private static void insereQuarto() {
        Quarto quarto = new Quarto();

        System.out.println("Informe o numero do quarto: ");
        int numero = scanner.nextInt();
        scanner.nextLine();  // corrige bug
        quarto.setNumero(numero);

        System.out.println("Informe o tipo de quarto(Basico, UTI, PHC...)");
        String tipo = scanner.nextLine();
        quarto.setTipo(tipo);

        System.out.println("Informe as comidades(Wi-fi, Suite, TV, Frigobar)");
        String comodidades = scanner.nextLine();
        quarto.setComodidades(comodidades);

        System.out.println("Informe o status do quarto(Disponível, Manutenção, Limpeza, Reservado, Indisponível)");
        String status = scanner.nextLine();
        quarto.setStatus(status);

        bancoDeDados.insere(quarto);
    }

    private static void menuPaciente() {
        System.out.println("Escolha uma operação");
        System.out.println("1 - Inserir Paciente");
        System.out.println("2 - Buscar Paciente");
        System.out.println("3 - Alterar Paciente");
        System.out.println("4 - Excluir Paciente");

        int operacao = scanner.nextInt();

        switch (operacao) {
            case 1:
                System.out.println("Inserir Paciente");
                break;
            case 2:
                System.out.println("Buscar Paciente");
                break;
            case 3:
                System.out.println("Alterar Paciente");
                break;
            case 4:
                System.out.println("Excluir Paciente");
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
    }
}
