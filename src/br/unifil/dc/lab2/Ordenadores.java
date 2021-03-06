package br.unifil.dc.lab2;

import java.util.ArrayList;
import java.util.List;

public class Ordenadores {

    public static class AnotacoesOperacoes {
        public int comparacoes = 0;
        public int atribuicoes = 0;

        public int getOperacoes(){
            //System.out.println(this.atribuicoes + this.comparacoes);
            return this.atribuicoes + this.comparacoes;
        }
    }

    /**
     * Ordena uma lista de inteiros usando o método da bolha.
     *
     * @param vals lista de inteiros, não nula.
     */
    public static int bubblesort(List<Integer> vals) {
        assert vals != null : "Lista não pode ser nula.";
        //System.out.println(vals.toString());
        AnotacoesOperacoes ao = new AnotacoesOperacoes();

        boolean houveTroca;
        do {
            houveTroca = false;
            // Varredura
            for (int i = 0; i < vals.size()-1; i++) {

                ao.comparacoes++;
                if (vals.get(i) > vals.get(i+1)) {
                    //ao.atribuicoes += 2;
                    trocar(vals, i, i+1, ao);
                    houveTroca = true;
                }
            }
            //System.out.println(vals.toString());
        } while(houveTroca);

        return ao.getOperacoes();
    }

    /**
     * Ordena uma lista de inteiros usando o método da seleção.
     *
     * @param vals lista de inteiros, não nula.
     */
    public static int selectionsort(List<Integer> vals) {
        assert vals != null : "Lista não pode ser nula."; // 1
        AnotacoesOperacoes ao = new AnotacoesOperacoes(); // 1

        for (int i = 0; i < vals.size()-1; i++) { // n
            // Qual o índice do menor elemento?
            int menor = i; // (n-1) * 1
            for (int j = i+1; j < vals.size(); j++) { // (n-1) * (n)
                ao.comparacoes++; // (n-1) * (n-1)
                if (vals.get(menor) > vals.get(j)) { // (n-1) * (n-1)
                    menor = j; // (n-1) * (n-1)
                }
            }

            //ao.atribuicoes += 2; // (n-1) * 1
            trocar(vals, i, menor, ao); // (n-1) * 1 * 3
        }

        return ao.getOperacoes(); // 1
    }

    /**
     * Ordena uma lista de inteiros usando o método da inserção.
     *
     * @param vals lista de inteiros, não nula.
     */
    public static int insertionsort(List<Integer> vals) {
        assert vals != null : "Lista não pode ser nula.";
        AnotacoesOperacoes ao = new AnotacoesOperacoes();

        for (int i = 1; i < vals.size(); i++) {
            int elemAtual = vals.get(i);

            int posicaoTroca;
            for (posicaoTroca = i; posicaoTroca > 0; posicaoTroca--){

                ao.comparacoes++;
                if (vals.get(posicaoTroca-1) < elemAtual) {
                    break;
                }

                ao.atribuicoes++;
                vals.set(posicaoTroca, vals.get(posicaoTroca-1));
            }

            ao.atribuicoes++;
            vals.set(posicaoTroca, elemAtual);
        }
        //System.out.println("Insertion " + ao.getOperacoes());
        return ao.getOperacoes();
    }

    /**
     * Ordena uma lista de inteiros usando o método da reunião.
     *
     * @param vals lista de inteiros, não nula.
     */
    public static int mergesort(List<Integer> vals) {
        assert vals != null : "Lista não pode ser nula.";
        AnotacoesOperacoes ao = new AnotacoesOperacoes();

        mergesortRec(vals, 0, vals.size(), ao);

        //System.out.println("Merge " + ao.getOperacoes());
        return ao.getOperacoes();
    }

    /**
     * Ordena uma lista de inteiros usando o método da reunião.
     *
     * @param vals lista de inteiros, não nula.
     */
    public static int quicksort(List<Integer> vals) {
        AnotacoesOperacoes ao = new AnotacoesOperacoes();
        quicksort(vals, 0, vals.size(), ao);
        //System.out.println("Quick " + ao.getOperacoes());
        return ao.getOperacoes();
    }



    //Metodos recursivos

    private static void mergesortRec(
            List<Integer> vals, int e, int d,
            AnotacoesOperacoes ao) {

        // Caso base
        final int numElems = d - e;
//        if (numElems <= 20) {
//            insertionsort(vals, e, d);
//            return;
//        }
        if (numElems <= 1) return ;

        // Subdivisão
        final int meio = (e + d) / 2;
        mergesortRec(vals, e, meio, ao);
        mergesortRec(vals, meio, d, ao);
        merge(vals, e, d, ao);
        //System.out.println(vals);
    }

    private static void quicksort(
            List<Integer> vals, int e, int d, AnotacoesOperacoes ao) {

        // Caso base
        ao.comparacoes++;
        if (d-e <= 1) {
            return;
        }

        // Subdivisão
        int iPivo = escolherPivo(vals, e, d);
        //particiona e retorna um objeto contendo o iPivo e o ao
        Object[] oParticionar = particionarLomuto(vals, e, d, iPivo, ao);
        iPivo = (int) oParticionar[0];
        ao = (AnotacoesOperacoes) oParticionar[1];
        quicksort(vals, e, iPivo, ao);
        quicksort(vals, iPivo+1, d, ao);
    }



    private static void merge(List<Integer> vals, int e, int d,
                              AnotacoesOperacoes ao) {

        final int meio = (e + d) / 2;
        int topoEsq = e, topoDir = meio;
        ArrayList<Integer> intercalados
                = new ArrayList<>(d-e);

        while (topoEsq < meio && topoDir < d) {
            ao.comparacoes++;
            ao.atribuicoes++;
            if (vals.get(topoEsq) < vals.get(topoDir)) {
                intercalados.add(vals.get(topoEsq));
                topoEsq++;
            } else {
                intercalados.add(vals.get(topoDir));
                topoDir++;
            }
        }

        while (topoEsq < meio) {
            ao.atribuicoes++;
            intercalados.add(vals.get(topoEsq));
            topoEsq++;
        }
        while (topoDir < d) {
            ao.atribuicoes++;
            intercalados.add(vals.get(topoDir));
            topoDir++;
        }

        int j = 0;
        for (int i = e; i < d; i++) {
            ao.atribuicoes++;

            // vals[i] = intercalados[j]
            vals.set(i, intercalados.get(j));
            j++;
        }
    }

    /**
     * Recebe uma lista em vals, com sublista delimitada por 'e' e 'd'.
     * Ao retornar, entre 'e' e 'd', todos os elementos menores
     * que indicePivo estarão na esquerda, e todos os maiores estarão
     * na direita. A posição de indicePivo muda, e é retornada pelo
     * método.
     *
     * Utiliza o algoritmo original de Hoare.
     *
     * @param vals Referencia para a lista original que contém a
     *             sublista indicada pelos marcadores 'e' e 'd'.
     * @param e Índice do ínicio da sublista em vals.
     * @param d Índice do fim, fechado, da sublista em vals.
     * @param indicePivo Índice do elemento pré-escolhido como pivô.
     * @return Índice da nova posição de pivo, que permanece apontando
     * para o mesmo elemento pivo original.
     */
    /**
    public static int particionarHoare(
            List<Integer> vals, int e, int d, int indicePivo) {

        final Integer pivo = vals.get(indicePivo);
        trocar(vals, e, indicePivo);

        int menores = e, maiores = d-1;
        while (true) {
            while (vals.get(menores) < pivo) menores++;
            while (vals.get(maiores) > pivo) maiores--;
            if (menores >= maiores) return maiores;

            trocar(vals, menores, maiores);
        }
    }
    **/

    /**
     * Recebe uma lista em vals, com sublista delimitada por 'e' e 'd'.
     * Ao retornar, entre 'e' e 'd', todos os elementos menores
     * que indicePivo estarão na esquerda, e todos os maiores estarão
     * na direita. A posição de indicePivo muda, e é retornada pelo
     * método.
     *
     * Utiliza o algoritmo original de Lomuto.
     *
     * @param vals Referencia para a lista original que contém a
     *             sublista indicada pelos marcadores 'e' e 'd'.
     * @param e Índice do ínicio da sublista em vals.
     * @param d Índice do fim, fechado, da sublista em vals.
     //* @param indicePivo Índice do elemento pré-escolhido como pivô.
     * @return Índice da nova posição de pivo, que permanece apontando
     * para o mesmo elemento pivo original.
     */
    public static Object[] particionarLomuto(
            List<Integer> vals, int e, int d, int iPivo, AnotacoesOperacoes ao) {

        final Integer pivo = vals.get(iPivo);
        trocar(vals, d-1, iPivo, ao);

        int divisor = e - 1;

        for (int i = e; i < d-1; i++) {
            ao.comparacoes++;
            if (vals.get(i) < pivo) {
                divisor++;
                trocar(vals, divisor, i, ao);
            }
        }
        trocar(vals, divisor+1, d-1, ao);
        //cria um objeto para retorno, podendo assim retornar o divisor e o ao
        Object[] o = new Object[2];
        o[0] = divisor + 1;
        o[1] = ao;
        return o;
    }

    /**
    private static int particionar(
            List<Integer> vals, int e, int d, int indicePivo) {

        // Escolha seu método, comentando uma das linhas
        // e descomentando a outra.
        //return particionarHoare(vals, e, d, indicePivo);
        return particionarLomuto(vals, e, d, indicePivo);
    }
     **/

    private static int escolherPivo(
            List<Integer> vals, int e, int d) {

        return d-1;
    }

    // T(n) = 3
    private static AnotacoesOperacoes trocar(List<Integer> vals, int i, int j, AnotacoesOperacoes ao) {
        Integer aux = vals.get(i);
        vals.set(i, vals.get(j));   // vals[i] = vals[j]
        ao.atribuicoes++;
        vals.set(j, aux);           // vals[j] = aux
        ao.atribuicoes++;
        return ao;
    }
}
