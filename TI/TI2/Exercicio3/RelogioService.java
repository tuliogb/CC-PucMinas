package service;

import dao.RelogioDAO;
import model.Relogio;
/*
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
*/
import java.util.List;

public class RelogioService {
    public RelogioDAO relogioDAO;
    private int proximoId = 1;

    public RelogioService() {
        relogioDAO = new RelogioDAO(); 
    }

    public void inserirRelogio(String nome, String modelo) {
            Relogio Relogio = new Relogio();
            Relogio.setId(proximoId++);
            Relogio.setNome(nome);
            Relogio.setModelo(modelo);
            
            System.out.println("Nome: " + nome);
            System.out.println("Modelo: " + modelo);
            
            relogioDAO.inserirRelogio(Relogio);
        }
    
    public void atualizarRelogio(int id, String novoNome, String novoModelo) {
        Relogio relogio = new Relogio();
        relogio.setId(id); 
        relogio.setNome(novoNome);
        relogio.setModelo(novoModelo);
        
        relogioDAO.atualizarRelogio(relogio);
    }
    
    public void excluirRelogio(int id) {
        relogioDAO.excluirRelogio(id);
    }
    
    public List<Relogio> listarRelogios() {
        return relogioDAO.listarRelogios();
    }



}  

