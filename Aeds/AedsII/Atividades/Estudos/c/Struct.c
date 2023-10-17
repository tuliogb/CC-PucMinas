typedef struct {
    int id;
    char nome[15];
} Jogador;

void troca(Jogador *lista,int x,int y){
    Jogador tmp = lista[x];
    lista[x] = lista[y];
    lista[y] = tmp;
}

void novo(Jogador *lista){
  lista[1].id = 50;
  strcpy(lista[1].nome,"Dirceu");
}

void novoRef(Jogador *jogador){
  jogador->id = 50;
  strcpy(jogador->nome,"Tulio");
}

int main() {
  Jogador lista[3];
  
  lista[0].id = 40;
  strcpy(lista[0].nome,"Paulo");
  
  novo(lista);
  novoRef(&lista[2]);
  troca(lista,0,1);

  return 0;
}