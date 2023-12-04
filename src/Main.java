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

            opcao = Integer.parseInt(scanner.nextLine());

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

        int operacao = Integer.parseInt(scanner.nextLine());

        switch (operacao) {
            case 1:
                insereQuarto();
                break;
            case 2:
                buscaQuarto();
                break;
            case 3:
                alteraQuarto();
                break;
            case 4:
                excluiQuarto();
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
    }

    private static void insereQuarto() {
        System.out.println("Informe o numero do quarto: ");
        int numero = Integer.parseInt(scanner.nextLine());

        System.out.println("Informe o tipo de quarto(Basico, UTI, PCD...)");
        String tipo = scanner.nextLine();

        System.out.println("Informe o status do quarto(Disponível, Manutenção, Limpeza, Reservado, Indisponível)");
        String status = scanner.nextLine();

        Quarto quarto = new Quarto(numero, tipo, status);
        bancoDeDados.insereQuarto(quarto);
    }

    private static void buscaQuarto() {
        System.out.println("Informe o número do quarto: ");
        int numero = Integer.parseInt(scanner.nextLine());

        Quarto quarto = bancoDeDados.buscaQuarto(numero);

        if(quarto == null) {
            System.out.println("Quarto de número " + numero + " não esta cadastrado");
        } else {
            System.out.println(quarto.textoBonito());
        }
    }

    private static void alteraQuarto() {
        System.out.println("Informe o número do quarto: ");
        int numero = Integer.parseInt(scanner.nextLine());

        Quarto quarto = bancoDeDados.buscaQuarto(numero);

        if(quarto == null) {
            System.out.println("Quarto de número " + numero + " não esta cadastrado");
        } else {
            System.out.println(String.format("Encontramos o quarto:\n%s", quarto));
            System.out.println("Gostaria de mudar o status para qual?(Disponível, Manutenção, Limpeza, Reservado, Indisponível)");
            String novoStatus = scanner.nextLine();

            quarto.setStatus(novoStatus);

            bancoDeDados.alteraQuarto(quarto);
        }
    }

    private static void excluiQuarto() {
        System.out.println("Informe o número do quarto: ");
        int numero = Integer.parseInt(scanner.nextLine());

        Quarto quarto = bancoDeDados.buscaQuarto(numero);

        if(quarto == null) {
            System.out.println("Quarto de número " + numero + " não esta cadastrado");
        } else {
            System.out.println(String.format("Encontramos o quarto:\n%s", quarto));
            System.out.println("Deseja mesmo excluir o quarto encontrado? Digite S ou N");
            char resposta = scanner.nextLine().toUpperCase().charAt(0);

            if(resposta == 'S') {
                bancoDeDados.excluiQuarto(numero);
                System.out.println("Registro excluido.");
            } else {
                System.out.println("Operação Cancelada");
            }
        }
    }

    private static void menuPaciente() {
        System.out.println("Escolha uma operação");
        System.out.println("1 - Inserir Paciente");
        System.out.println("2 - Buscar Paciente");
        System.out.println("3 - Alterar Paciente");
        System.out.println("4 - Excluir Paciente");

        int operacao = Integer.parseInt(scanner.nextLine());

        switch (operacao) {
            case 1:
                inserePaciente();
                break;
            case 2:
                buscaPaciente();
                break;
            case 3:
                alteraPaciente();
                break;
            case 4:
                excluiPaciente();
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
    }

    private static void inserePaciente() {
        System.out.println("Inserir Paciente");
    }

    private static void buscaPaciente() {
        System.out.println("Buscar Paciente");
    }

    private static void alteraPaciente() {
        System.out.println("Alterar Paciente");
    }

    private static void excluiPaciente() {
        System.out.println("Excluir Paciente");
    }
}
