package br.unifil.dc.lab2;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        final int inicio = 500, limite = 10001, passo = 500;

        //Mede os tempos


        List<Medicao> medicoesSelecao = Benchmarking.benchmarkIntervalo(
                inicio, limite, passo,
                Ordenadores::selectionsort);

        List<Medicao> medicoesBolha = Benchmarking.benchmarkIntervalo(
                inicio, limite, passo,
                Ordenadores::bubblesort);


        List<Medicao> medicoesInsertion = Benchmarking.benchmarkIntervalo(
                inicio, limite, passo,
                Ordenadores::insertionsort);
        /**
        List<Medicao> medicoesMerge = Benchmarking.benchmarkIntervalo(
                inicio, limite, passo,
                Ordenadores::mergesort);

        List<Medicao> medicoesQuick = Benchmarking.benchmarkIntervalo(
                inicio, limite, passo,
                Ordenadores::quicksort);
        **/
        //Gera o Grafico
        TabelaTempos tt = new TabelaTempos();
        //tt.setTitulo("Tempo para ordenação de uma lista Decrescente");
        tt.setTitulo("Tempo para ordenação de uma lista Crescente");
        tt.setEtiquetaX("Qtde elementos lista");
        tt.setEtiquetaY("Tempo (ms)");
        //tt.setLegendas("Insertion", "Merge", "Quick");
        tt.setLegendas("Selection", "Bubble", "Insertion");

        //Pega os tempos
        for (int i = 0; i < medicoesInsertion.size(); i++) {
            Medicao amostraSelecao = medicoesSelecao.get(i);
            Medicao amostraBolha = medicoesBolha.get(i);
            Medicao amostraInsercao = medicoesInsertion.get(i);
            //Medicao amostraMerge = medicoesMerge.get(i);
            //Medicao amostraQuick = medicoesQuick.get(i);

            tt.anotarAmostra(amostraInsercao.extratoAmostra,
                    amostraSelecao.tempoMillis,
                    amostraBolha.tempoMillis,
                    amostraInsercao.tempoMillis
                    //amostraMerge.tempoMillis,
                    //amostraQuick.tempoMillis
                    );
        }
        tt.exibirGraficoXY();
    }
}
