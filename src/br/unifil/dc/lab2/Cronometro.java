package br.unifil.dc.lab2;

/**
 * Write a description of class Cronometro here.
 * 
 * @author Matheus Muriel
 * @version 27-04-18
 */
public class Cronometro
{
    /**
     * Construtor padrão da classe.
     */
    public Cronometro() {
        //throw new RuntimeException("O aluno ainda não implementou essa funcionalidade.");
    }
    
    /**
     * Inicia ou reinicia a contagem de tempo. Nunca zera o último estado do contador. Se o tempo já
     * estiver correndo, não faz nada.
     */
    public void iniciar() {
        tempoInicial = System.currentTimeMillis();
    }

    /**
     * Para a contagem de tempo e retorna uma leitura do tempo decorrido.
     * 
     * @return Tempo decorrido até o momento da parada.
     */
    public double parar() {
        if (tempoInicial != 0){
            tempoFinal = System.currentTimeMillis();
            tempoTotal = tempoFinal - tempoInicial;
        }else{
            throw new RuntimeException("Não foi possivel parar o cronometro pois ele não foi iniciado.");
        }
        return tempoTotal;
    }
    
    /**
     * Retorna o tempo decorrido contado até então, independente se está parado ou correndo. Não
     * altera o estado de contagem (parado/correndo).
     * 
     * return Tempo decorrido contado pelo cronômetro.
     */
    public double lerTempoEmMilissegundos() {
        throw new RuntimeException("O aluno ainda não implementou essa funcionalidade.");


    }
    
    /**
     * Zera o contador de tempo do cronômetro. Se o cronômetro estava em estado de contagem, ele é
     * parado.
     */
    public void zerar() {
        throw new RuntimeException("O aluno ainda não implementou essa funcionalidade.");
    }
    
    // Atributos da classe são declarados aqui
    private long tempoInicial;
    private long tempoFinal;
    private double tempoTotal;
    
}
