/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

/**
 *
 * @author eldoc
 */
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class grafica {

    private static Panel panel;
    private static Frame frame;

    public static void main(String[] args) {
        frame = new Frame();
        double[] num = new double[10];
        double goal = 0.001;
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            num[i] = rand.nextDouble();
        }
        cargarGrafica(num, goal);
        frame.add(panel);
        frame.setVisible(true);
        frame.pack();
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(grafica.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = 0; i < 10; i++) {
            num[i] = rand.nextDouble();
        }

        frame.removeAll();
        frame.add(cargarGrafica(num, goal));
        frame.repaint();
        frame.pack();
    }

    public static Panel cargarGrafica(double num[], double goal) {

        //nombre de las variables que contendran la lista de tipo XYSeries
        XYSeries sErms;//serie Erms
        XYSeries sIteraciones;//serie Iteraciones
        //titulos de las dos lineas que se graficaran
        sErms = new XYSeries("error maximo permitido");
        sIteraciones = new XYSeries("Erms vs Iteraciones");
        // ejemplo de valores que puedo cargar en la serie 

        for (int i = 0; i < num.length; i++) {
            sIteraciones.add(i, num[i]);
            sErms.add(i, goal);
        }

        //creo un contenedor que me almacenara el valor de las do series
        XYSeriesCollection contenedor = new XYSeriesCollection();

        //almaceno cada serie en el contendor
        contenedor.addSeries(sErms);
        contenedor.addSeries(sIteraciones);

        //creo un marco de para que se pueda visualizar en la pantalla
        JFreeChart marco;
        marco = ChartFactory.createXYLineChart("Gráfico de Erms vs Iteración", "Iteración", "Error RMS", contenedor, PlotOrientation.VERTICAL, true, true, true);

        //personalización del grafico color de fondo 
        XYPlot xyplot = (XYPlot) marco.getPlot();
        xyplot.setBackgroundPaint(Color.white);
        //personalización del grafico color de los grit 
        xyplot.setDomainGridlinePaint(Color.BLACK);
        xyplot.setRangeGridlinePaint(Color.BLACK);

        // -> Pinta Shapes en los puntos dados por el XYDataset
        XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer) xyplot.getRenderer();
        xylineandshaperenderer.setBaseShapesVisible(false);

        //--> muestra los valores de cada punto XY
        XYItemLabelGenerator xy = new StandardXYItemLabelGenerator();
        xylineandshaperenderer.setBaseItemLabelGenerator(xy);
        xylineandshaperenderer.setBaseItemLabelsVisible(true);
        xylineandshaperenderer.setBaseLinesVisible(true);
        xylineandshaperenderer.setBaseItemLabelsVisible(false);
        // Mostramos la grafica dentro del panel
        ChartPanel chartPanel = new ChartPanel(marco);
        panel = new Panel();
        panel.setBounds(30, 300, 700, 200);
        panel.removeAll();
        panel.setLayout(new java.awt.BorderLayout());
        panel.add(chartPanel);
        panel.validate();
        return panel;
    }

}
