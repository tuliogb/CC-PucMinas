package model;

public class Relogio {
	
    public String nome;
    public String modelo;
    public int Id;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    public void setId(int id) {
        this.Id = id;
    }
    
    public int getId() {
        return Id;
    }

}
