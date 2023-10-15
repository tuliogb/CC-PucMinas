package dao;

import model.Relogio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class RelogioDAO {
    String url = "jdbc:postgresql://localhost:5432/ex3"; 
    String usuario = "Tuliogb";
    String senha = "12345";
    
    

    public void inserirRelogio(Relogio relogio) {
        try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
            String sql = "INSERT INTO Relogio (nome, modelo) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, relogio.getNome());
            preparedStatement.setString(2, relogio.getModelo());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void excluirRelogio(int id) {
        try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
            String sql = "DELETE FROM Relogio WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void atualizarRelogio(Relogio relogio) {
        try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
            String sql = "UPDATE Relogio SET nome = ?, modelo = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, relogio.getNome());
            preparedStatement.setString(2, relogio.getModelo());
            preparedStatement.setInt(3, relogio.getId()); 

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    
    public List<Relogio> listarRelogios() {
        List<Relogio> relogios = new ArrayList<>();
        
        try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
            String sql = "SELECT * FROM Relogio";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String modelo = resultSet.getString("modelo");

                Relogio relogio = new Relogio();
                relogio.setId(id);
                relogio.setNome(nome);
                relogio.setModelo(modelo);

                relogios.add(relogio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return relogios;
    }


    
    




}
