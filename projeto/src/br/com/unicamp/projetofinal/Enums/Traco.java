package br.com.unicamp.projetofinal.Enums;

public enum Traco {
    NENHUM, ELUSIVO, ATAQUEDUPLO, FURIA, SOBREPUJAR, BARREIRA;
}

    /*
    *  ELUSIVO -> so pode ser bloqueado por outra carta de traco elusivo
    *
    *  ATAQUEDUPLO -> quando atacar, pode escolher atacar mais uma vez
    *
    *  FURIA -> quando mata um adversario ganha 1 | 1
    *
    *  SOBREPUJAR -> se mata um inimigo, o resto do dano que nao foi necessario para mata-lo vai para o nexus dele
    *
    *  BARREITS -> na primeira tentatia de dar dano a essa carta, ela ignora esse dano
    *
    * */