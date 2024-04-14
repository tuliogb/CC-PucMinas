class Personagem{
    private String nome, cidade;
    private int telefone, idade;

    Personagem(){
        this.nome = "";
        this.cidade = "";
        this.telefone = 0;
        this.idade = 0;
    }

    Personagem(String nome, String cidade, int telefone, int idade){
        this.nome = nome;
        this.cidade = cidade;
        this.telefone = telefone;
        this.idade = idade;
    }

    public void mostrarPersonagem(){
        System.out.println("Nome:" + this.nome + "  Cidade:" + this.cidade + "  Telefone:" + this.telefone + "  Idade:" + this.idade);
    }
}

public class Celula{
    private Personagem personagem;
    private Celula prox;
    public static Celula primeiro=null, ultimo=null;

    public Celula(Personagem personagem){
        this.personagem = personagem;
        this.prox = null;
    }

    public static void inserirInicio(Personagem p){
        Celula tmp = new Celula(p);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;

        if(ultimo==primeiro) ultimo=tmp;
        tmp = null;
    }
    
    public static void inserir(Personagem p, int posicao){
        Celula pos = primeiro;
        for(int i=0; i<posicao-1; i++, pos=pos.prox);

        Celula tmp = new Celula(p);
        tmp.prox = pos.prox;
        pos.prox = tmp;

        tmp = pos = null;
    }

    public static void inserirFim(Personagem p){
        ultimo.prox = new Celula(p);
        ultimo = ultimo.prox;
    }
    
    public static void removerInicio(){
        primeiro.prox = primeiro.prox.prox;
    }

    public static void removerFim(){
        for(Celula i=primeiro; i.prox!=ultimo; i=i.prox);
        ultimo=i;
        i=i.prox=null;
    }

    public static void remover(int posicao){
        Celula tmp = primeiro;
        for(int i=0; i<posicao-1; i++, tmp=tmp.prox);

        
    }

    public static void mostrarLista(){
        for(Celula i=primeiro.prox; i!=null; i=i.prox){
            i.personagem.mostrarPersonagem();
        }
    }


    public static void main(String[] args){
        primeiro = ultimo = new Celula(new Personagem());

        inserirInicio(new Personagem("Lucas", "Belo Horizonte", 999556677, 19));
        inserir(new Personagem("Tulio", "Ponte Nova", 999434343, 89), 1);
        inserirFim(new Personagem("Kleber", "Nova Lima", 975439879, 17));

        mostrarLista();
    }
}