/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasEcuaciones;

/**
 *
 * @author eldoc
 */
public class Determinantes {
    public static void main(String[] args) {
        double[][]m= {{2 ,1,3},{ 2 ,-1,3}};
        Determinantes dt = new Determinantes();
        System.out.println("Determinate de la matriz: "+dt.calDeterminante(m));
    }
    public double calDeterminante(double[][] matriz) {
        double determinante = 0;
        int filas = matriz.length;
        int columnas = matriz[0].length;

        // En caso de que sea una matriz 1x1
        if ((filas == 1) && (columnas == 1)) {
            return matriz[0][0];
        }

        int signo = 1;
        for (int col = 0; col < columnas; col++) {
            double[][] submatriz = getSubmatriz(matriz, filas, columnas, col);
            determinante = determinante + signo * matriz[0][col] * calDeterminante(submatriz);
            signo *= -1;
        }
        return determinante;
    }

    public double[][] getSubmatriz(double[][] matriz, int filas, int col, int columna) {

        double[][] submatriz = new double[filas - 1][col - 1];
        int contador = 0;

        for (int x = 0; x < col; x++) {
            if (x == col) {
                continue;
            }
            for (int y = 1; y < filas; y++) {
                submatriz[y - 1][contador] = matriz[y][x];
            }
            contador++;
        }
        return submatriz;
    }
}
