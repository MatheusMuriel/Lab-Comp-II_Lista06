package br.unifil.dc.lab2;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        final int inicio = 10, limite = 10000, passo = 250;

        //Mede os tempos

        /**
         **//**
        List<Medicao> medicoesBolha = Benchmarking.benchmarkIntervalo(
                inicio, limite, passo,
                Ordenadores::bubblesort);
         /**
         **//**
        List<Medicao> medicoesSelecao = Benchmarking.benchmarkIntervalo(
                inicio, limite, passo,
                Ordenadores::selectionsort);
         /**
         **/
        List<Medicao> medicoesInsertion = Benchmarking.benchmarkIntervalo(
                inicio, limite, passo,
                Ordenadores::insertionsort);
         /**
         **/
        List<Medicao> medicoesMerge = Benchmarking.benchmarkIntervalo(
                inicio, limite, passo,
                Ordenadores::mergesort);
        /**
         **/
        List<Medicao> medicoesQuick = Benchmarking.benchmarkIntervalo(
                inicio, limite, passo,
                Ordenadores::quicksort);
         /**
        **/


        //Gera o Grafico
        TabelaTempos tt = new TabelaTempos();
        tt.setTitulo("Nº de ordenação de uma lista Decrescente");
        //tt.setTitulo("Nº de ordenação de uma lista Crescente");
        tt.setEtiquetaX("Tamanho da lista");
        tt.setEtiquetaY("Operações");
        //tt.setLegendas("Bubble", "Selection", "Insertion");
        tt.setLegendas("Insertion", "Merge", "Quick");
        //tt.setLegendas("Bubble", "Selection", "Insertion", "Merge", "Quick");

        //Pega os tempos
        for (int i = 0; i < medicoesInsertion.size(); i++) {
            //Medicao amostraBolha = medicoesBolha.get(i);
            //Medicao amostraSelecao = medicoesSelecao.get(i);
            Medicao amostraInsercao = medicoesInsertion.get(i);
            Medicao amostraMerge = medicoesMerge.get(i);
            Medicao amostraQuick = medicoesQuick.get(i);

            tt.anotarAmostra(amostraQuick.extratoAmostra,
                    //amostraBolha.operacoes,
                    //amostraSelecao.operacoes,
                    amostraInsercao.operacoes,
                    amostraMerge.operacoes,
                    amostraQuick.operacoes
                    );
        }
        tt.exibirGraficoXY();
    }
}
