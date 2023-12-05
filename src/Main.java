import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static BancoDeDados bancoDeDados = new BancoDeDados();

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.print("\n\n");
            System.out.println("\t     CASA DE REPOUSO SERENIDADE DOURADA \n");
            System.out.println("\t --- MENU DE OPERAÇÕES ---.");
            System.out.println("1 - Inserir quartos.");
            System.out.println("2 - Cadastrar Pacientes.");
            System.out.println("3 - Carga do banco");
            System.out.println("0 - Sair");

            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    menuQuarto();
                    break;
                case 2:
                    menuPaciente();
                    break;
                case 3:
                    cargaBanco();
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
        System.out.println(" Escolha uma operação");
        System.out.println("1 - Inserir um Quarto.");
        System.out.println("2 - Buscar um Quarto.");
        System.out.println("3 - Alterar um Quarto.");
        System.out.println("4 - Excluir um Quarto.");
        System.out.println("5 - Exibir os Quartos cadastrados.");

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
            case 5:
                exibirQuartos();
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
    }

    private static void insereQuarto() {
        System.out.println("Informe o numero do quarto: ");
        int numero = Integer.parseInt(scanner.nextLine());

        System.out.println("Informe o tipo do quarto(Basico, UTI, PCD)");
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

        if (quarto == null) {
            System.out.println("O quarto de numero " + numero + " nao esta cadastrado.");
        } else {
            System.out.println(quarto.textoBonito());
        }
    }

    private static void alteraQuarto() {
        System.out.println("Informe o numero do quarto: ");
        int numero = Integer.parseInt(scanner.nextLine());

        Quarto quarto = bancoDeDados.buscaQuarto(numero);

        if (quarto == null) {
            System.out.println("O quarto de numero " + numero + " não esta cadastrado");
        } else {
            System.out.println(String.format("Encontramos o quarto:\n%s", quarto));
            System.out.println(
                    "Mudar o status para qual?(Disponivel, Manutencao, Limpeza, Reservado, Indisponivel)");
            String novoStatus = scanner.nextLine();

            quarto.setStatus(novoStatus);

            bancoDeDados.alteraQuarto(quarto);
        }
    }

    private static void excluiQuarto() {
        System.out.println("Informe o numero do quarto: ");
        int numero = Integer.parseInt(scanner.nextLine());

        Quarto quarto = bancoDeDados.buscaQuarto(numero);

        if (quarto == null) {
            System.out.println("O quarto de numero " + numero + " não esta cadastrado");
        } else {
            System.out.println(String.format("Encontramos o quarto:\n%s", quarto));
            System.out.println("Deseja mesmo excluir o quarto encontrado? Digite S ou N");
            char resposta = scanner.nextLine().toUpperCase().charAt(0);

            if (resposta == 'S') {
                bancoDeDados.excluiQuarto(numero);
                System.out.println("Registro excluido.");
            } else {
                System.out.println("Operacao Cancelada");
            }
        }
    }

    private static void exibirQuartos() {
        Quarto[] quartos = bancoDeDados.buscaQuartos();

        if (quartos.length > 0) {
            System.out.println(" Quartos cadastrados:\n");

            for (int i = 0; i < quartos.length; i++) {
                System.out.println(quartos[i].textoBonito());
            }
        } else {
            System.out.println("Nao possui quartos cadastrados");
        }
    }

    private static void menuPaciente() {
        System.out.println("Escolha uma operação");
        System.out.println("1 - Inserir Paciente.");
        System.out.println("2 - Buscar Paciente.");
        System.out.println("3 - Alterar Paciente.");
        System.out.println("4 - Excluir Paciente.");
        System.out.println("5 - Exibir lista de pacientes.");

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
            case 5:
                exibirPaciente();
                break;
            default:
                System.out.println("Opcao invalida");
                break;
        }
    }

    private static void inserePaciente() {
        System.out.println("Informe o nome do paciente: ");
        String nome = scanner.nextLine();

        System.out.println("Informe o ano de nascimento do paciente: ");
        int anoNascimento = Integer.parseInt(scanner.nextLine());

        System.out.println("Informe o genero do paciente: ");
        String genero = scanner.nextLine();

        System.out.println("Informe o telefone do responsavel pelo paciente: ");
        String telefoneResponsavel = scanner.nextLine();

        System.out.println("Informe o numero do quarto do paciente: ");
        int numeroQuarto = Integer.parseInt(scanner.nextLine());

        Paciente paciente = new Paciente(nome, anoNascimento, genero, telefoneResponsavel, numeroQuarto);

        bancoDeDados.inserePaciente(paciente);
    }

    private static void buscaPaciente() {
        System.out.println("Informe o nome do paciente: ");
        String nome = scanner.nextLine();

        Paciente paciente = bancoDeDados.buscaPaciente(nome);

        if (paciente == null) {
            System.out.println("O paciente " + nome + " nao esta cadastrado");
        } else {
            System.out.println(paciente.textoBonito());

            int numero = paciente.getNumeroQuarto();

            Quarto quarto = bancoDeDados.buscaQuarto(numero);

            if (quarto == null) {
                System.out.println(" " + paciente.getNome() + " nao esta hospedado!");
            } else {
                System.out.println(" " + paciente.getNome() + " esta hospedado no quarto:\n" + quarto.getTipo());
            }
        }
    }

    private static void alteraPaciente() {
        System.out.println("Informe o nome do paciente: ");
        String nome = scanner.nextLine();

        Paciente paciente = bancoDeDados.buscaPaciente(nome);

        if (paciente == null) {
            System.out.println("O paciente " + nome + " nao esta cadastrado");
        } else {
            System.out.println(String.format("Encontramos o paciente:\n%s", paciente.textoBonito()));
            System.out.println("Gostaria de mudar o numero do quarto para qual?");
            int novoNumeroQuarto = Integer.parseInt(scanner.nextLine());

            paciente.setNumeroQuarto(novoNumeroQuarto);

            bancoDeDados.alteraPaciente(paciente);
        }
    }

    private static void cargaBanco() {
        if (bancoDeDados.estaVazio()) {
            Quarto quarto1 = new Quarto(12, "Basico", "Indisponivel");
            Quarto quarto2 = new Quarto(13, "UTI", "Disponivel");
            Quarto quarto3 = new Quarto(14, "PCD", "Indisponivel");
            Quarto quarto4 = new Quarto(15, "Basico", "Reservado");

            bancoDeDados.insereQuarto(quarto1);
            bancoDeDados.insereQuarto(quarto2);
            bancoDeDados.insereQuarto(quarto3);
            bancoDeDados.insereQuarto(quarto4);

            Paciente fran = new Paciente("Francielly Dias", 1927, "F", "27 99999-9991", 12);
            Paciente juan = new Paciente("Juan Lirio", 1943, "M", "27 99999-9992", 13);
            Paciente patrick = new Paciente("Patrick Costa", 1937, "M", "27 99999-9993", 14);
            Paciente anaKesia = new Paciente("Ana Kesia Santos", 1940, "F", "27 99999-9994", 15);

            bancoDeDados.inserePaciente(fran);
            bancoDeDados.inserePaciente(juan);
            bancoDeDados.inserePaciente(patrick);
            bancoDeDados.inserePaciente(anaKesia);

            System.out.println("Carga efetuada com sucesso");
        } else {
            System.out.println("Nao e possivel efetuar uma carga em um banco preenchido");
        }

    }

    private static void excluiPaciente() {
        System.out.println("Informe o nome do paciente: ");
        String nome = scanner.nextLine();

        Paciente paciente = bancoDeDados.buscaPaciente(nome);

        if (paciente == null) {
            System.out.println("O paciente " + nome + " nao esta cadastrado.");
        } else {
            System.out.println(String.format("Encontramos o paciente:\n%s", paciente.textoBonito()));
            System.out.println("Deseja mesmo excluir o paciente encontrado? Digite S ou N");
            char resposta = scanner.nextLine().toUpperCase().charAt(0);

            if (resposta == 'S') {
                bancoDeDados.excluiPaciente(nome);
                System.out.println("Registro excluído.");
            } else {
                System.out.println("Operação Cancelada");
            }
        }
    }

    private static void exibirPaciente() {
        Paciente[] quartos = bancoDeDados.buscaPaciente();

        if (quartos.length > 0) {
            System.out.println("Os pacientes Cadastrados:\n");

            for (int i = 0; i < quartos.length; i++) {
                System.out.println(quartos[i].textoBonito());
            }
        } else {
            System.out.println("Nao possui pacientes cadastrados");
        }
    }
}
