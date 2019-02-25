/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.perceptronUCO;

/**
 *
 * @author eldoc
 */
public class perceptronMUCOControlador {

    static double[][] x = {{1, 1}, {1, 0}, {0, 1}, {0, 0}};
    static double[][] yd = {{1}, {0}, {0}, {0}};

    public static void main(String[] args) {
        System.out.println("ENTRADAS");
        for (double[] x1 : x) {
            for (int j = 0; j < x1.length; j++) {
                System.out.print(x1[j] + " ");
            }
            System.out.println("");
        }
        System.out.println("SALIDAS");
        for (double[] y1 : yd) {
            for (int j = 0; j < y1.length; j++) {
                System.out.print(y1[j] + " ");
            }
            System.out.println("");
        }
        int m = 2;
        int o = 4;
        int n = 1;
        int patrones = 4;
        int epoch = 1000;
        double lr = 0.00001;
        double goal = 0.001;
        String funcionActivacionEntrada = "gaussiana";
        String funcionActivacionSalida = "sigmoide";
        String algoritmo = "deltamodificada";
        String nombre = "nombre red";
        perceptronUCO p = new perceptronUCO(m, o, n, patrones, x, yd, epoch, lr, goal, funcionActivacionEntrada, funcionActivacionSalida, algoritmo,nombre);
        p.inicializacionPesosUmbrales();
        p.entrenamiento();
    }
}
