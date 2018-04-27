package br.unifil.dc.lab2;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        final int inicio = 500, limite = 10001, passo = 500;
        List<Medicao> medicoesBolha = Benchmarking.benchmarkIntervalo(
                inicio, limite, passo,
                Ordenadores::bubblesort);
        List<Medicao> medicoesSelecao = Benchmarking.benchmarkIntervalo(
                inicio, limite, passo,
                Ordenadores::selectionsort);

        TabelaTempos tt = new TabelaTempos();
        tt.setTitulo("Tempo para ordenação");
        tt.setEtiquetaX("Qtde elementos lista");
        tt.setEtiquetaY("Tempo (ms)");
        tt.setLegendas("Bubble", "Selection");
        for (int i = 0; i < medicoesBolha.size(); i++) {
            Medicao amostraBolha = medicoesBolha.get(i);
            Medicao amostraSelecao = medicoesSelecao.get(i);

            tt.anotarAmostra(amostraBolha.extratoAmostra,
                    amostraBolha.tempoMillis,
                    amostraSelecao.tempoMillis);
        }
        tt.exibirGraficoXY();
    }
}
