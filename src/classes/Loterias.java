package classes;

import java.util.ArrayList;

public interface Loterias {

    public abstract boolean validacao(int valida); //verifica se números digitados estão dentro dos parâmetros

    public abstract void setDezenas(int dezenas); //seta as dezenas

    public abstract int confere(ArrayList<Integer> aposta, ArrayList<Integer> jogo); //Confere o jogo do apostador

    public abstract void randomicos(); //gera numeros randomicos para o jogo

    public abstract void cincoMais(ArrayList<Integer> vet);

    public abstract void cincoMenos(ArrayList<Integer> vet);

    public abstract ArrayList<Integer> getDezenas();

}
