import java.util.Scanner;

public class Main {


    private static Scanner scanner = new Scanner(System.in);

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
                System.out.println("Inserir Quarto");
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
