package app;

import static spark.Spark.*;
import model.Relogio;
import service.RelogioService;
import java.util.List;

public class Aplicacao {
    public static RelogioService relogioService = new RelogioService();
    
    public static void main(String[] args) {
        port(8081);
        
        staticFiles.externalLocation("src/main/resources");

        get("/formulario", (req, res) -> {
            res.redirect("/formulario.html");
            return null;
        });

        post("/relogios", (request, response) -> {
            String nome = request.queryParams("nome");
            String modelo = request.queryParams("modelo");
            relogioService.inserirRelogio(nome, modelo);
            response.status(201); 
            return "Relógio inserido com sucesso!";
        });

        post("/excluir-relogio", (request, response) -> {
            int id = Integer.parseInt(request.queryParams("excluir-id"));
            relogioService.excluirRelogio(id);
            response.status(200);
            return "Relógio excluído com sucesso!";
        });

        post("/atualizar-relogio", (request, response) -> {
            int id = Integer.parseInt(request.queryParams("atualizar-id"));
            String novoNome = request.queryParams("novo-nome");
            String novoModelo = request.queryParams("novo-modelo");
            relogioService.atualizarRelogio(id, novoNome, novoModelo);
            response.status(200);
            return "Relógio atualizado com sucesso!";
        });

        
        
        
        
        get("/listar-relogios", (request, response) -> {
            List<Relogio> listaDeRelogios = relogioService.listarRelogios();

            StringBuilder html = new StringBuilder();
            html.append("<html>");
            html.append("<head><title>Lista de Relógios</title></head>");
            html.append("<body>");
            html.append("<h1>Lista de Relógios Cadastrados</h1>");
            html.append("<ul>");

            for (Relogio relogio : listaDeRelogios) {
                html.append("<li>ID: ").append(relogio.getId()).append("</li>");
                html.append("<li>Nome: ").append(relogio.getNome()).append("</li>");
                html.append("<li>Modelo: ").append(relogio.getModelo()).append("</li>");
                html.append("<br>");
            }

            html.append("</ul>");
            html.append("</body>");
            html.append("</html>");

            return html.toString();
        });

    }
}
