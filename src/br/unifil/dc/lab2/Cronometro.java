package br.unifil.dc.lab2;

/**
 * Write a description of class Cronometro here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cronometro
{
    /**
     * Construtor padrão da classe.
     */
    public Cronometro() {

        zerar();

        //throw new RuntimeException("O aluno ainda não implementou essa funcionalidade.");
    }
    
    /**
     * Inicia ou reinicia a contagem de tempo. Nunca zera o último estado do contador. Se o tempo já
     * estiver correndo, não faz nada.
     */
    public void iniciar() {

        // Anotar tempo atual
        tempoAnterior = System.currentTimeMillis();

        //throw new RuntimeException("O aluno ainda não implementou essa funcionalidade.");
    }
    
    /**
     * Para a contagem de tempo e retorna uma leitura do tempo decorrido.
     * 
     * @return Tempo decorrido até o momento da parada.
     */
    public double parar() {

        // Anotar tempo pós-tarefa
        tempoPosterior = System.currentTimeMillis();
        return tempoPosterior - tempoAnterior;

        //throw new RuntimeException("O aluno ainda não implementou essa funcionalidade.");
    }
    
    /**
     * Retorna o tempo decorrido contado até então, independente se está parado ou correndo. Não
     * altera o estado de contagem (parado/correndo).
     * 
     * return Tempo decorrido contado pelo cronômetro.
     */
    //retorna um double
    public long lerTempoEmMilissegundos() {

        return tempoPosterior - tempoAnterior;


        //throw new RuntimeException("O aluno ainda não implementou essa funcionalidade.");
    }
    
    /**
     * Zera o contador de tempo do cronômetro. Se o cronômetro estava em estado de contagem, ele é
     * parado.
     */
    public void zerar() {

        tempoPosterior = 0;
        tempoAnterior = 0;

        //throw new RuntimeException("O aluno ainda não implementou essa funcionalidade.");
    }
    
    // Atributos da classe são declarados aqui
    private long tempoAnterior;
    private long tempoPosterior;
    
}
