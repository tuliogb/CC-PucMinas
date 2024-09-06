START:
    LDA 8000H   ; Carrega dado01 em A
    MOV B,A    ; Move dado01 para B
    LDA 8001H   ; Carrega dado02 em A
    MOV C,A    ; Move dado02 para C
    MOV D,B    ; Move dado01 para D (para cálculo)

DIV_LOOP:
    MOV A,D       ; Move o dividendo atual para A
    CMP C           ; Compara com o divisor
    JC END_LOOP    ; Se A < C, termina o loop
    SUB C          ; Subtrai o divisor de A
    MOV D,A       ; Atualiza o dividendo com o resultado da subtração
    INR H          ; Incrementa o quociente
    JMP DIV_LOOP   ; Repete o loop

END_LOOP:
    MOV A,H       ; Move o quociente para A
    STA 8002H      ; Armazena o quociente no endereço 8002H
    HLT             ; Para o processador

// NAO ENTENDI O MOTIVO PELO QUAL QUANDO CHEGA EM 10 O ACUMULADOR -2 É 0E 15? TERIA QUE SER 08