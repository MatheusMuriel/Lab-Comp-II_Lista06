package br.unifil.dc.lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Medicao {
    public Medicao(int extratoAmostra, long tempoMillis) {
        this.extratoAmostra = extratoAmostra;
        this.tempoMillis = tempoMillis;
    }

    public int extratoAmostra;
    public long tempoMillis;
}

public class Benchmarking {

    public static List<Medicao> benchmarkIntervalo(
            int inicial,
            int limite,
            int passo,
            Consumer<List<Integer>> metodo) {

        ArrayList<Medicao> tempos = new ArrayList<>();
        for (; inicial < limite; inicial+=passo) {
            List<Integer> listaA = revRange(0,inicial) //IntStream.range(0,inicial)
                    .boxed()
                    .collect(Collectors.toList());

            tempos.add(
                    new Medicao(
                            inicial,
                            benchmarkLista(listaA,10, metodo)
                    )
            );
        }

        return tempos;
    }

    public static long benchmarkLista(
            List<Integer> listaOriginal,
            int nRuns,
            Consumer<List<Integer>> metodo) {

        ArrayList<Long> temposTomados = new ArrayList<>(nRuns);
        for (int i = 0; i < nRuns; i++) {
            // Inicialização
            List<Integer> listaCopiada
                    = new ArrayList<>(listaOriginal);

            // Anotar tempo atual
            final long tempoAnterior = System.currentTimeMillis();

            // Realizar tarefa
            metodo.accept(listaCopiada);

            // Anotar tempo pós-tarefa
            final long tempoPosterior = System.currentTimeMillis();
            temposTomados.add(tempoPosterior - tempoAnterior);
        }

        //System.out.println(temposTomados);
        return calcularMedia(temposTomados.subList(1,temposTomados.size()));
    }

    private static long calcularMedia(List<Long> valores) {
        long accumulador = 0;
        for (Long v : valores) accumulador += v;
        return accumulador / valores.size();
    }

    /**private static long calcularMediaStream(List<Long> valores) {
        return valores.stream()
            .reduce(0l, (a,b) -> a+b) / valores.size();
     }*/

    // Código de: https://stackoverflow.com/questions/24010109/java-8-stream-reverse-order#24011264
    private static IntStream revRange(int from, int to) {
        return IntStream.range(from, to)
                .map(i -> to - i + from);
    }
}
