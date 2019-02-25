/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import Jama.Matrix;
import java.awt.TextArea;
import static java.lang.Math.PI;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import jdk.nashorn.internal.objects.NativeMath;
import vista.FrmBasesRadiales;

/**
 *
 * @author eldoc
 */
public class centrosRadiales implements Runnable {

    private double d[][];
    private double x[][];
    private double yd[][];
    private double r[][];
    private int numeroCentrosRadiales;
    private int goal;

    public centrosRadiales() {
    }

    public centrosRadiales(double[][] d, double[][] x, double[][] yd, double[][] r, int numeroCentrosRadiales, int goal) {
        this.d = d;
        this.x = x;
        this.yd = yd;
        this.r = r;
        this.numeroCentrosRadiales = numeroCentrosRadiales;
        this.goal = goal;
    }

    /**
     * @return the d
     */
    public double[][] getD() {
        return d;
    }

    /**
     * @param d the d to set
     */
    public void setD(double[][] d) {
        this.d = d;
    }

    /**
     * @return the x
     */
    public double[][] getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(double[][] x) {
        this.x = x;
    }

    /**
     * @return the yd
     */
    public double[][] getYd() {
        return yd;
    }

    /**
     * @param yd the yd to set
     */
    public void setYd(double[][] yd) {
        this.yd = yd;
    }

    /**
     * @return the r
     */
    public double[][] getR() {
        return r;
    }

    /**
     * @param r the r to set
     */
    public void setR(double[][] r) {
        this.r = r;
    }

    /**
     * @return the numeroCentrosRadiales
     */
    public int getNumeroCentrosRadiales() {
        return numeroCentrosRadiales;
    }

    /**
     * @param numeroCentrosRadiales the numeroCentrosRadiales to set
     */
    public void setNumeroCentrosRadiales(int numeroCentrosRadiales) {
        this.numeroCentrosRadiales = numeroCentrosRadiales;
    }

    /**
     * @return the goal
     */
    public int getGoal() {
        return goal;
    }

    /**
     * @param goal
     */
    public void setGoal(int goal) {
        this.goal = goal;
    }

    public void entrenar() {

        for (int i = 0; i < getX().length; i++) {
            double[] ds = getX()[i];
            for (int j = 0; j < ds.length; j++) {
                double e = ds[j];
                System.out.printf("X[%d][%d]: %f \t", (i + 1), (j + 1), e);
                //System.out.print("X[" + (i + 1) + "][" + (j + 1) + "]:" + e + "  ");
            }
            System.out.println("");

        }

        setNumeroCentrosRadiales(getR().length);
        for (int i = 0; i < getR().length; i++) {
            for (int j = 0; j < getR()[i].length; j++) {
                // r[i][j] = Math.random() * ThreadLocalRandom.current().nextDouble(0, 1 + may);
                //System.out.print("R[" + (i + 1) + "][" + (1 + j) + "]:" + r[i][j] + " ");
                System.out.printf("R[%d][%d]: %f \t", (i + 1), (j + 1), getR()[i][j]);
            }

            System.out.println("");
        }
        setD(new double[getX().length][getNumeroCentrosRadiales()]);

        for (int i = 0; i < getD().length; i++) {
            for (int j = 0; j < getNumeroCentrosRadiales(); j++) {
                double sum = 0;
                double euclide;
                for (int k = 0; k < getX()[0].length; k++) {
                    double num1 = getX()[i][k];
                    double num2 = getR()[j][k];
                    euclide = num1 - num2;
                    euclide = Math.pow(euclide, 2);
                    sum = sum + euclide;
                }

                getD()[i][j] = Math.sqrt(sum);
                System.out.printf("Distancia[%d][%d]: %f \t", (i + 1), (j + 1), getD()[i][j]);
                //System.out.print("Distancia[" + (i + 1) + "][" + (j + 1) + "]:" + d[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("Salidas: " + getYd()[0].length);
        ArrayList num = new ArrayList();

        for (int h = 0; h < getYd()[0].length; h++) {

            double[][] ecuacion = new double[getX().length * getYd()[0].length][getNumeroCentrosRadiales() + 1];
            for (int i = 0; i < ecuacion.length; i++) {
                ecuacion[i][0] = 1;
            }
            for (int i = 0; i < ecuacion.length; i++) {
                for (int j = 1; j < ecuacion[i].length; j++) {
                    ecuacion[i][j] = Math.pow(getD()[i][j - 1], 2) * Math.log(getD()[i][j - 1]);
                }
            }
            for (int i = 0; i < ecuacion.length; i++) {
                for (int j = 0; j < ecuacion[i].length; j++) {
                    //System.out.printf("%.6f %s", ecuacion[i][j], " ");
                    System.out.printf("w[%d] * %f \t", (j + 1), ecuacion[i][j]);
                    FrmBasesRadiales.txtAreaVista.append(String.format("w[%d] * %f \t", (j + 1), ecuacion[i][j]));
                    // System.out.print("ecuacion[" + (i + 1) + "][" + (j + 1) + "]:" + incognitas[i][j] + " \t");
                }
                System.out.println("");
                FrmBasesRadiales.txtAreaVista.append("\n");
            }
            System.out.println("");
            for (int i = 0; i < getYd().length; i++) {
                for (int j = 0; j < getYd()[i].length; j++) {
                    //System.out.printf("%.6f %s", yd[i][j], " ");
                    System.out.printf("b[%d][%d]: %f \t", (i + 1), (j + 1), getYd()[i][j]);
                    FrmBasesRadiales.txtAreaVista.append(String.format("b[%d][%d]: %f \t", (i + 1), (j + 1), getYd()[i][j]));
                }
                System.out.println("");
                FrmBasesRadiales.txtAreaVista.append("\n");
            }
            double[] ydAux = new double[getYd().length];
            for (int i = 0; i < ydAux.length; i++) {
                ydAux[i] = getYd()[i][h];
            }
            Matrix A = new Matrix(ecuacion);
            Matrix b = new Matrix(ydAux, ydAux.length);
            //Calculate Solved Matrix
            Matrix ans = null;
            try {
                ans = A.solve(b);
                //Printing Answers
                System.out.println("dimenciones" + ans.getColumnDimension());
                System.out.println("dimenciones" + ans.getRowDimension());
                for (int i = 0; i < ans.getRowDimension(); i++) {
                    for (int j = 0; j < ans.getColumnDimension(); j++) {
                        FrmBasesRadiales.txtAreaVista.append(String.format("w[%d]: %f \t", (i + 1), ans.get(i, j)));
                        System.out.printf("w[%d][%d]: %f \t", (i + 1), (j + 1), ans.get(i, j));
                    }

                }
                System.out.println("");
                FrmBasesRadiales.txtAreaVista.append("\n");
                double[] f = new double[getX().length];
                for (int i = 0; i < ecuacion.length; i++) {
                    for (int j = 0; j < ecuacion[i].length; j++) {
                        //System.out.printf("%.6f %s", ecuacion[i][j], " ");
                        f[i] = f[i] + ans.get(j, 0) * ecuacion[i][j];
                        //System.out.printf("A[%d][%d]: %f \t", (i + 1),(j + 1) ,ecuacion[i][j]);
                        // System.out.print("ecuacion[" + (i + 1) + "][" + (j + 1) + "]:" + incognitas[i][j] + " \t");
                    }
                    System.out.printf("f(%d): %f \n", (i + 1), f[i]);
                    FrmBasesRadiales.txtAreaVista.append(String.format("f(%d): %f \n", (i + 1), f[i]));
                }
                double[] ep = new double[getX().length];
                double erms = 0;
                for (int i = 0; i < ep.length; i++) {
                    ep[i] = ydAux[i] - f[i];
                    System.out.printf("error P[%d]= ydAux[%d]:%f - f[%d]:%f = %f\n", i, i, ydAux[i], i, f[i], ep[i]);
                    FrmBasesRadiales.txtAreaVista.append(String.format("error P[%d]= ydAux[%d]:%f - f[%d]:%f = %f\n", i, i, ydAux[i], i, f[i], ep[i]));
                    erms = erms + Math.abs(ep[i]);
                }
                System.out.printf("Erms: %f \n", erms / getX().length);
                num.add(erms);
                FrmBasesRadiales.cargar(num, getGoal());
                FrmBasesRadiales.txtAreaVista.append(String.format("Erms: %f \n", erms / getX().length));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "La matriz no tiene solucion");
            }

        }

    }

    public void inicializarCentrosRadiales() {
        setR(new double[getNumeroCentrosRadiales()][getX()[0].length]);

        FrmBasesRadiales.txtAreaVista.removeAll();
        double may = getX()[0][0];
        for (double[] x1 : getX()) {
            for (int j = 0; j < x1.length; j++) {
                if (x1[j] > may) {
                    may = x1[j];
                }
            }
        }
        FrmBasesRadiales.txtAreaVista.append("El numero maximo es : " + may + "\n");
        System.out.println("El numero maximo es : " + may);
        if (may > (2 * PI)) {
            may = 2 * PI;
            FrmBasesRadiales.txtAreaVista.append("Numero maximo modificado: " + may);
            System.out.println("Numero maximo modificado: " + may);
        }
        for (int i = 0; i < getR().length; i++) {
            for (int j = 0; j < getR()[i].length; j++) {
                r[i][j] = Math.random() * ThreadLocalRandom.current().nextDouble(0, 1 + may);
                System.out.printf("R[%d][%d]: %f \t", (i + 1), (j + 1), getR()[i][j]);
                FrmBasesRadiales.txtAreaVista.append(String.format("R[%d][%d]: %f \t", (i + 1), (j + 1), getR()[i][j]));
            }

            System.out.println("");
            FrmBasesRadiales.txtAreaVista.append("\n");
        }
    }

    @Override
    public void run() {
        entrenar();
        FrmBasesRadiales.lblEstado.setText("Finalizado");
    }

}
