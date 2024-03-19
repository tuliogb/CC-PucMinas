static void start(){
    String input = MyIO.readLine();
    int[] valor = new int[300];

    char c;
    for(int i=0; i<input.length; i++){
        c = input.charAt(i);
        valor[c]++;
    }
    
    System.out.println(valor['a']);
}

/*
    Economia de 2 lacos: Um pra inserir e o outro pra procurar.
    Obs: Tem que haver tamanho compativel no array pros valores AscII das letras adicionadas.
*/