package br.unifil.dc.lab2;

import java.util.LinkedList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.xy.XYSeries;

/**
 * Write a description of class TabelaTempos here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TabelaTempos
{
    /**
     * Construtor da classe
     */
    public TabelaTempos() {
    }
    
    /**
     * Método que define as legendas das colunas da tabela
     * 
     * @param legendas Define as legendas das colunas.
     */
    public void setLegendas(String... legendas) {
        this.legendas = legendas;
    }
    
    /**
     * Define um título personalizado para o gráfico
     * 
     * @param titulo Título desejado.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    /**
     * Define uma etiqueta para o eixo X.
     * 
     * @param etiquetaX Nome da etiqueta.
     */
    public void setEtiquetaX(String etiquetaX) {
        this.etiquetaX = etiquetaX;
    }
    
    /**
     * Define uma etiqueta para o eixo Y.
     * 
     * @param etiquetaY Nome da etiqueta.
     */
    public void setEtiquetaY(String etiquetaY) {
        this.etiquetaY = etiquetaY;
    }
    
    /**
     * Método que registra a quantidade desejada de amostras de diversas populações na tabela.
     * 
     * @param celulaEixoX Valor base de amostras no eixo X.
     * @param colunas Valores a serem preenchidos na linha da celulaEixoX.
     */
    public void anotarAmostra(int celulaEixoX, double... colunas) {
        double[] linha = new double[1 + colunas.length];
        
        linha[0] = celulaEixoX;
        int i = 1;
        for (double celula : colunas) {
            linha[i] = celula;
            i++;
        }
        
        tabela.addLast(linha);
    }
    
    /**
     * Método que transforma a tabela de anotações em um gráfico XY e exibe em uma janela.
     */
    public void exibirGraficoXY() {
        
        int numColunas = maxNumColunas();
        XYSeries[] series = new XYSeries[numColunas-1];
        
        // Cria as linhas do gráfico e já indica o nome da legenda
        for (int i = 0; i < series.length; i++) {
            String legenda = i < legendas.length ? legendas[i] : "Y"+i;
            series[i] = new XYSeries(legenda);
        }
        
        // Adiciona cada anotação de tempo à cada linha do gráfico relativa ao dado
        for (double[] linha : tabela) {
            for (int i = 1; i < linha.length; i++) {
                series[i-1].add(linha[0],linha[i]);
            }
        }
        
        // Agrupa todas as linhas para serem desenhadas em um único gráfico
        XYSeriesCollection dadosPlotXY = new XYSeriesCollection();
        for (XYSeries s : series) {
            dadosPlotXY.addSeries(s);
        }
        
        // Cria o gráfico
        JFreeChart chart = ChartFactory.createXYLineChart(
                this.titulo,
                this.etiquetaX,
                this.etiquetaY,
                dadosPlotXY
        );

        // Cria uma janela e exibe o gráfico nela
        ChartFrame frame = new ChartFrame("First", chart);
        frame.pack();
        frame.setVisible(true);
    }
    
    
    /**
     * Método que gera uma representação da tabela no formato CSV, reconhecido por diversas
     * ferramentas de planilha, como Excel e Calc.
     * 
     * @param separador Define o caractere utilizado para separar as células de uma linha
     * @param delimitador Define o caractere utilizado para delimitar cada célula
     * 
     * @return String com representação da tabela em formato CSV.
     */
    public String gerarCSV(char separador, char delimitador) {
        StringBuilder sb = new StringBuilder();
        
        // Constroi a primeira linha da tabela, com etiquetas e legendas do gráfico
        sb.append(delimitador).append(etiquetaX).append(delimitador);
        for (String y : legendas) {
            sb.append(separador).append(delimitador).append(y).append(delimitador);
        }
        sb.append("\n");
        
        // Constroi o restante das linhas da tabela, com o valores entrados para cada X
        for (double[] linha : tabela) {
            for (double celula : linha) {
                sb.append(delimitador).append(celula).append(delimitador).append(separador);
            }
            int ultimoSep = sb.lastIndexOf(separador+"");
            sb.replace(ultimoSep, ultimoSep+1, "\n");
        }
        
        return sb.toString();
    }
    public String gerarCSV() {
        return this.gerarCSV(';', '\"');
    }

    
    private int maxNumColunas() {
        int max = 0;
        for(double[] linha : tabela) {
            max = Math.max(max, linha.length);
        }
        
        return max;
    }
    
    
    /**
     * Atributos da classe
     */
    private String titulo = "Gráfico";
    private String etiquetaX = "X";
    private String etiquetaY = "Y";
    private String[] legendas = { "Y" };
    private LinkedList<double[]> tabela = new LinkedList<double[]>();
}
