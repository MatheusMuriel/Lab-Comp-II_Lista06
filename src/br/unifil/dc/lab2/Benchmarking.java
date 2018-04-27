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

    /**
     *  Metodo que calcula Benchmark
     * @param inicio Numero de inicio da lista a ser ordenada
     * @param limite Numero de limite da lista a ser ordenada
     * @param passo  Numero de intervalos da lista a ser ordenada
     * @param metodo
     * @return
     */
    public static List<Medicao> benchmarkIntervalo(
            int inicio,
            int limite,
            int passo,
            Consumer< List<Integer> > metodo) {

        //Inicia uma lista de tempos
        ArrayList<Medicao> tempos = new ArrayList<>();

        for (; inicio < limite; inicio+=passo) {
            List<Integer> listaA = IntStream.range(inicio,limite) //revRange = Decrescente, IntStream.range = Crescente
                    .boxed()
                    .collect(Collectors.toList());
            //System.out.println(listaA);

            tempos.add(
                    new Medicao(
                            inicio,
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
        Cronometro cronometro = new Cronometro();

        ArrayList<Long> temposTomados = new ArrayList<>(nRuns);
        for (int i = 0; i < nRuns; i++) {
            // Inicialização
            List<Integer> listaCopiada
                    = new ArrayList<>(listaOriginal);

            // Inicia o Cronometro
            cronometro.iniciar();

            // Realizar tarefa
            metodo.accept(listaCopiada);

            // Anotar tempo pós-tarefa
            //final long tempoPosterior = System.currentTimeMillis();
            cronometro.parar();
            temposTomados.add( (long) cronometro.parar() );
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
