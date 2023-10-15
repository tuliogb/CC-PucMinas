import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class XDAO {
    private Connection connection;

    public XDAO(Connection connection) {
        this.connection = connection;
    }

    public List<X> getAllX() throws SQLException {
        List<X> xList = new ArrayList<>();
        String selectQuery = "SELECT * FROM X";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                X x = new X();
                x.setId(resultSet.getInt("id"));
                x.setNome(resultSet.getString("nome"));
                x.setIdade(resultSet.getInt("idade"));
                xList.add(x);
            }
        }

        return xList;
    }

    public void inserirX(X x) throws SQLException {
        String insertQuery = "INSERT INTO X (nome, idade) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, x.getNome());
            preparedStatement.setInt(2, x.getIdade());

            preparedStatement.executeUpdate();
        }
    }

    public void excluirX(int id) throws SQLException {
        String deleteQuery = "DELETE FROM X WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }
    }

    public void atualizarX(X x) throws SQLException {
        String updateQuery = "UPDATE X SET nome = ?, idade = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, x.getNome());
            preparedStatement.setInt(2, x.getIdade());
            preparedStatement.setInt(3, x.getId());

            preparedStatement.executeUpdate();
        }
    }
}
