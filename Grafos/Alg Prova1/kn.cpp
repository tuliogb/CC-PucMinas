#include <iostream>
using namespace std;

const int numVertice = 10;

int matriz1[numVertice][numVertice] = {
    //  a, b, c, d, e, f, g, h, i, j
    {0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, // a -> b
    {0, 0, 1, 1, 1, 0, 0, 0, 0, 0}, // b -> c, d, e
    {0, 0, 0, 1, 1, 1, 0, 0, 0, 0}, // c -> d, e, f
    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, // d -> e
    {0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, // e -> f
    {0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, // f -> g
    {0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, // g -> h
    {0, 0, 0, 0, 0, 0, 0, 0, 1, 0}, // h -> i
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // i -> j
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}  // j
};

int matriz2[numVertice][numVertice] = {
    //  a, b, c, d, e, f, g, h, i, j
    {0, 1, 1, 1, 1, 1, 1, 1, 1, 1}, // a -> todos os outros vértices
    {1, 0, 1, 1, 1, 1, 1, 1, 1, 1}, // b -> todos os outros vértices exceto b
    {1, 1, 0, 1, 1, 1, 1, 1, 1, 1}, // c -> todos os outros vértices exceto c
    {1, 1, 1, 0, 1, 1, 1, 1, 1, 1}, // d -> todos os outros vértices exceto d
    {1, 1, 1, 1, 0, 1, 1, 1, 1, 1}, // e -> todos os outros vértices exceto e
    {1, 1, 1, 1, 1, 0, 1, 1, 1, 1}, // f -> todos os outros vértices exceto f
    {1, 1, 1, 1, 1, 1, 0, 1, 1, 1}, // g -> todos os outros vértices exceto g
    {1, 1, 1, 1, 1, 1, 1, 0, 1, 1}, // h -> todos os outros vértices exceto h
    {1, 1, 1, 1, 1, 1, 1, 1, 0, 1}, // i -> todos os outros vértices exceto i
    {1, 1, 1, 1, 1, 1, 1, 1, 1, 0}  // j -> todos os outros vértices exceto j
};


bool isKn(int matriz[numVertice][numVertice])
{
    int soma=0;

    for(int i=0; i<numVertice; i++)
    {
        for(int j=0; j<numVertice; j++) soma += matriz[i][j];
        if(soma!=numVertice-1) return false;
        soma=0;
    }

    return true;
}



int main() {

    if(isKn(matriz2)) printf("\ncompleto\n");
    else printf("\nn completo\n");

    return 0;
}