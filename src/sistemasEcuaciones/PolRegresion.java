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
public class PolRegresion {

    private double[] x;  //datos
    private double[] y;
    private int nDatos;
    double[][] m;    //matriz de los coeficientes
    double[] t;      //términos independientes
    public double[] a; //polinomio   a[0]+a[1]·x+a[2]·x2+...
    public int grado; //grado del polinomio

    public static void main(String[] args) {
        double[] x = {0.05, 0.1, 0.15, 0.2, 0.25, 0.3, 0.35, 0.4};
        double[] y = {0.28, 0.50, 0.64, 0.75, 0.82, 0.90, 0.95, 1.02};
        int grado =9;
        PolRegresion pl = new PolRegresion(x, y, grado);
        pl.calculaPolinomio();
    }

    public PolRegresion(double[] x, double[] y, int grado) {
        this.x = x;
        this.y = y;
        nDatos = x.length;
        this.grado = grado;
        t = new double[grado + 1];
        m = new double[grado + 1][grado + 1];
        a = new double[grado + 1];
    }

    private void coeficientes() {
        double[] s = new double[2 * grado + 1];
        double suma;
        for (int k = 0; k <= 2 * grado; k++) {
            suma = 0.0;
            for (int i = 0; i < nDatos; i++) {
                suma += potencia(x[i], k);
            }
            s[k] = suma;
        }
        for (int k = 0; k <= grado; k++) {
            suma = 0.0;
            for (int i = 0; i < nDatos; i++) {
                suma += potencia(x[i], k) * y[i];
            }
            t[k] = suma;
        }
        for (int i = 0; i <= grado; i++) {
            for (int j = 0; j <= grado; j++) {
                m[i][j] = s[i + j];
            }
        }
    }

    private double potencia(double base, int exp) {
        double producto = 1.0;
        for (int i = 0; i < exp; i++) {
            producto *= base;
        }
        return producto;
    }
//procedimiento de Siedel

    public void calculaPolinomio() {
        coeficientes();
//matriz
        double aux;
        for (int i = 0; i <= grado; i++) {
            aux = m[i][i];
            for (int j = 0; j <= grado; j++) {
                m[i][j] = -m[i][j] / aux;
            }
            t[i] = t[i] / aux;
            m[i][i] = 0.0;
        }
//primera aproximación
        double[] p = new double[grado + 1];
        p[0] = t[0];
        for (int i = 1; i <= grado; i++) {
            p[i] = t[i];
        }
//aproximaciones  sucesivas
        double error = 0.0, maximo = 0.0;
        do {
            error = 0.0;
            maximo = 0.0;
            for (int i = 0; i <= grado; i++) {
                a[i] = t[i];
                for (int j = 0; j < i; j++) {
                    a[i] += m[i][j] * a[j];
                }
                for (int j = i + 1; j <= grado; j++) {
                    a[i] += m[i][j] * p[j];
                }
                error = Math.abs((a[i] - p[i]) / a[i]);
                if (error > maximo) {
                    maximo = error;
                }
            }
            for (int i = 0; i <= grado; i++) {
                p[i] = a[i];
            }
        } while (maximo > 0.001);
        for (int i = 0; i < a.length; i++) {
            System.out.println("a["+i+"]:"+a[i]);
        }
    }
}
