package br.unifil.dc.lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import br.unifil.dc.lab2.Ordenadores.AnotacoesOperacoes;

class Medicao {
    public Medicao(int extratoAmostra, int operacoes) {
        this.extratoAmostra = extratoAmostra;
        this.operacoes = operacoes;
    }

    public int extratoAmostra;
    public int operacoes;

    public int getOperacoes(){
        return this.operacoes;
    }
}

public class Benchmarking {

        public static List<Medicao> benchmarkIntervalo(
            int inicial,
            int limite,
            int passo,
            Function< List<Integer>, Integer > metodo) {

        ArrayList<Medicao> tempos = new ArrayList<>();
        for (; inicial < limite; inicial+=passo) {
            List<Integer> listaA = revRange(0,inicial) //Lista Decrescente
            //List<Integer> listaA = IntStream.range(0,inicial) //Lista Crescente
                    .boxed()
                    .collect(Collectors.toList());
            //System.out.println(listaA.size());
            tempos.add(
                    new Medicao(
                            inicial,
                            benchmarkLista(listaA,2, metodo)
                    )
            );
            //System.out.println( (tempos.get(tempos.size()-1)).getOperacoes() );
        }

        //System.out.println(tempos.get(15).getOperacoes());
        return tempos;
    }

    /**
     * Medoto criado pelo professor Ricardo e alterado pelo aluno Matheus Muriel
     * Metodo que anota o tempo de execução de um metodo de ordenação.
     *
     * @param listaOriginal Lista de Inteiros contendo os numeros a serem ordenados.
     * @param nRuns Numero de vezes que o metodo vai ser rodado, afim de tirar uma media mais precisa.
     * @param metodo Um Consumer do tipo Lista de inteiros
     * @return Media dos (nRuns)tempos de execução do metodo.
     */
    public static int benchmarkLista(
            List<Integer> listaOriginal,
            int nRuns,
            Function< List<Integer>, Integer > metodo) {


        ArrayList<Integer> temposTomados = new ArrayList<>(nRuns);
        for (int i = 0; i < nRuns; i++) {
            // Inicialização
            List<Integer> listaCopiada
                    = new ArrayList<>(listaOriginal);
            Cronometro cronometro = new Cronometro();
            //AnotacoesOperacoes ao = new AnotacoesOperacoes();

            // Anotar tempo atual
            //final long tempoAnterior = System.currentTimeMillis();
            //cronometro.iniciar();

            // Realizar tarefa
            int valor = metodo.apply( listaCopiada );

            System.out.println(valor);

            // Anotar tempo pós-tarefa
            //final long tempoPosterior = System.currentTimeMillis();
            //cronometro.parar();
            temposTomados.add( valor );
            
        }

        //System.out.println(temposTomados);
        return calcularMedia(temposTomados.subList(1,temposTomados.size()));
    }

    private static int calcularMedia(List<Integer> valores) {
        int accumulador = 0;
        for (Integer v : valores) accumulador += v;
        //System.out.println(accumulador / valores.size());
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
