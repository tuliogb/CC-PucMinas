import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/ex2";
        String username = "tuliogb";
        String password = "12345";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            XDAO xdao = new XDAO(connection);

            Scanner scanner = new Scanner(System.in);
            int opcao = 0;

            while (opcao != 5) {
                System.out.println("Escolha uma opção:");
                System.out.println("1. Listar");
                System.out.println("2. Inserir");
                System.out.println("3. Excluir");
                System.out.println("4. Atualizar");
                System.out.println("5. Sair");
                System.out.print("Opção: ");

                try {
                    opcao = scanner.nextInt();
                    scanner.nextLine();  // Consumir a quebra de linha após o número
                } catch (InputMismatchException e) {
                    System.out.println("Opção inválida. Digite um número válido.");
                    scanner.nextLine();  // Limpar o buffer de entrada
                    continue;
                }

                switch (opcao) {
                    case 1:
                        listarRegistros(xdao);
                        break;
                    case 2:
                        inserirRegistro(xdao, scanner);
                        break;
                    case 3:
                        excluirRegistro(xdao, scanner);
                        break;
                    case 4:
                        atualizarRegistro(xdao, scanner);
                        break;
                    case 5:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida. Escolha um número de 1 a 5.");
                        break;
                }
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void listarRegistros(XDAO xdao) throws SQLException {
        List<X> xList = xdao.getAllX();

        System.out.println("Registros da tabela X:");
        for (X x : xList) {
            System.out.println("ID: " + x.getId() + ", Nome: " + x.getNome() + ", Idade: " + x.getIdade());
        }
    }

    private static void inserirRegistro(XDAO xdao, Scanner scanner) throws SQLException {
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a idade: ");
        int idade = scanner.nextInt();

        X novoX = new X();
        novoX.setNome(nome);
        novoX.setIdade(idade);

        xdao.inserirX(novoX);

        System.out.println("Registro inserido com sucesso!");
    }

    private static void excluirRegistro(XDAO xdao, Scanner scanner) throws SQLException {
        System.out.print("Digite o ID do registro a ser excluído: ");
        int id = scanner.nextInt();

        xdao.excluirX(id);

        System.out.println("Registro excluído com sucesso!");
    }

    private static void atualizarRegistro(XDAO xdao, Scanner scanner) throws SQLException {
        System.out.print("Digite o ID do registro a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consumir a quebra de linha

        System.out.print("Digite o novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a nova idade: ");
        int idade = scanner.nextInt();

        X xAtualizado = new X();
        xAtualizado.setId(id);
        xAtualizado.setNome(nome);
        xAtualizado.setIdade(idade);

        xdao.atualizarX(xAtualizado);

        System.out.println("Registro atualizado com sucesso!");
    }
}
