/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author eldoc
 */
public class perceptronUCO extends red {

    //Multicapa Una Capa Oculta (MUCO)
    private int o;
    private String funcionActivacionEntrada;
    private String funcionActivacionSalida;
    private String algoritmo;

    public perceptronUCO() {
    }

    public perceptronUCO(int m, int o, int n, int patrones, double[][] x, double[][] yd, int epoch, double lr, double goal, String funcionActivacionEntrada, String funcionActivacionSalida, String algoritmo,String nombre) {
        super(m, n, patrones, x, yd, epoch, lr, goal,nombre);
        this.o = o;
        this.funcionActivacionEntrada = funcionActivacionEntrada;
        this.funcionActivacionSalida = funcionActivacionSalida;
        this.algoritmo = algoritmo;
    }

    public int getO() {
        return o;
    }

    public void setO(int o) {
        this.o = o;
    }

    public String getFuncionActivacionEntrada() {
        return funcionActivacionEntrada;
    }

    public void setFuncionActivacionEntrada(String funcionActivacionEntrada) {
        this.funcionActivacionEntrada = funcionActivacionEntrada;
    }

    public String getFuncionActivacionSalida() {
        return funcionActivacionSalida;
    }

    public void setFuncionActivacionSalida(String funcionActivacionSalida) {
        this.funcionActivacionSalida = funcionActivacionSalida;
    }

    public String getAlgoritmo() {
        return algoritmo;
    }

    public void setAlgoritmo(String algoritmo) {
        this.algoritmo = algoritmo;
    }

    //capa oculta
    public double[][] w;
    public double[] u;
    public double[][] wAnt = new double[getM()][getO()];
    public double[] uAnt = new double[getO()];
    public double[] h;
    public double[] s;
    //capa salida
    public double[][] ws;
    public double[] us;
    public double[][] wsAnt = new double[getO()][getN()];
    public double[] usAnt = new double[getN()];
    public double[] yr;
    public double[] ss;
    public double[] ep;
    public double[] el;
    public double lrD = 1;
    Random rand = new Random();

    public void inicializacionPesosUmbrales() {
        w = new double[getM()][getO()];
        u = new double[getO()];

        ws = new double[getO()][getN()];
        us = new double[getN()];
        System.out.println("INICIALIZACION DE PESOS Y UMBRALES");
        System.out.println("***Primera Capa Oculta****");
        for (int i = 0; i < getO(); i++) {
            u[i] = -1 + (1 - -1) * rand.nextDouble();

            System.out.println("Umbral [" + i + "]: " + u[i]);
        }
        for (int i = 0; i < getO(); i++) {
            for (int j = 0; j < getM(); j++) {
                w[j][i] = -1 + (1 - -1) * rand.nextDouble();
                System.out.print("Peso[" + j + "][" + i + "]: " + w[j][i] + " ");
            }
            System.out.println("");
        }
        System.out.println("***Capa Salida***");
        for (int i = 0; i < getN(); i++) {
            us[i] = -1 + (1 - -1) * rand.nextDouble();
            System.out.println("Umbral[" + i + "]: " + us[i]);
        }

        for (int i = 0; i < getN(); i++) {
            for (int j = 0; j < getO(); j++) {
                ws[j][i] = -1 + (1 - -1) * rand.nextDouble();
                System.out.print("Peso[" + j + "][" + i + "]: " + ws[j][i] + " ");
            }
            System.out.println("");
        }
    }

    public ArrayList entrenamiento() {
        s = new double[getO()];
        h = new double[getO()];
        yr = new double[getN()];
        ss = new double[getN()];
        el = new double[getN()];
        ep = new double[getPatrones()];

        double erms;
        System.out.println("");
        System.out.println("");
        for (int e = 1; e < getEpoch(); e++) {//ciclo de iteraciones
            System.out.println("INTERACION # " + e);
            double sumEp = 0;
            for (int p = 0; p < getPatrones(); p++) {//ciclo de patrones
                double sumEl = 0;
                for (int i = 0; i < getO(); i++) {
                    s[i] = 0;
                    for (int j = 0; j < getM(); j++) {
                        System.out.println("soma[" + i + "] = soma[" + i + "]:" + s[i] + " + x[" + p + "][" + j + "]:" + getX()[p][j] + " * w[" + j + "][" + i + "]:" + w[j][i]);
                        s[i] = s[i] + getX()[p][j] * w[j][i];
                    }
                    System.out.println("soma : " + s[i]);
                    System.out.println("soma Atenuada= " + "soma[" + i + "]:" + s[i] + " - " + "u[" + i + "]" + u[i]);
                    s[i] = s[i] - u[i];
                    System.out.println("soma Atenuada: " + s[i]);
                    h[i] = activacion(getFuncionActivacionEntrada(), (s[i]));
                    System.out.println("");
                    System.out.println("Salida de la primera capa: " + h[i]);
                    System.out.println("");

                }
                System.out.println("");
                System.out.println("");
                for (int i = 0; i < getN(); i++) {
                    ss[i] = 0;
                    for (int j = 0; j < getO(); j++) {
                        System.out.println("somaS[" + i + "] = somaS[" + i + "]:" + ss[i] + " + h[" + j + "]:" + h[j] + " * w[" + j + "][" + i + "]:" + ws[j][i]);
                        ss[i] = ss[i] + h[j] * ws[j][i];
                    }  
                    System.out.println("somaS : " + ss[i]);
                    System.out.println("soma Atenuada= " + "somaS[" + i + "]:" + ss[i] + " - " + "us[" + i + "]" + us[i]);
                    ss[i] = ss[i] - us[i];
                    System.out.println("soma Atenuada: " + ss[i]);
                    yr[i] = activacion(getFuncionActivacionSalida(), (ss[i]));
                    System.out.println("");
                    System.out.println("");
                    System.out.println("Salida de la Red: " + yr[i]);
                    System.out.println("");
                    System.out.println("");
                    el[i] = getYd()[p][i] - yr[i];
                    System.out.println("Error Lineal = " + "yd[" + p + "][" + i + "]:" + getYd()[p][i] + " - " + "y[" + i + "]:" + el[i]);
                    System.out.println("Error Lineal: " + el[i]);

                    sumEl = sumEl + abs(el[i]);

                }

                ep[p] = sumEl / getN();
                sumEl = 0;
                System.out.println("");
                System.out.println("");
                System.out.println("Error por patron: " + ep[p]);
                System.out.println("\n\n");
                sumEp = sumEp + ep[p];

                actualizacionPesosUmbrales(getAlgoritmo(), p, e);
            }
            erms = sumEp / getPatrones();
            sumEp = 0;
            System.out.println("Error de la iterecion: " + erms);
            System.out.println("");
            System.out.println("");
            System.out.println("");
            if (erms <= getGoal()) {
                System.out.println("ENTRENAMIENTO FINALIZADO CON EXITO EN INTERACION " + e + "... ");
                System.out.println("");
                System.out.println("Umbrales Optimos");
                for (int i = 0; i < u.length; i++) {
                    System.out.println(u[i]);
                }
                System.out.println("");
                System.out.println("Pesos Optimos");
                for (int i = 0; i < getN(); i++) {
                    for (int j = 0; j < getM(); j++) {
                        System.out.println(w[j][i] + " ");
                    }
                    System.out.println("");
                }
                ArrayList l;
                l = new ArrayList<>();
                l.add(0, w);
                l.add(1, u);
                l.add(2, ws);
                l.add(3, us);
                return l;
            }
        }
        return null;
    }

   
    private void actualizacionPesosUmbrales(String algoritmo, int p, int e) {

        switch (algoritmo) {
            case "delta":
                delta(p);
                break;
            case "deltaModificado":
                deltaModificado(p, e);
                break;
            default:
                delta(p);
                break;
        }
    }

    private void delta(int p) {
        System.out.println("ACTUALIZACION DE PESOS Y UMBRALES DELTA");
        System.out.println("***Primera Capa Oculta****");
        for (int i = 0; i < getO(); i++) {
            System.out.print("Umbral Anterior[" + i + "]: " + u[i] + " ");
            u[i] = u[i] + getLr() * ep[p];
            System.out.println("Umbral Modificado[" + i + "]: " + u[i]);
        }

        for (int i = 0; i < getO(); i++) {
            for (int j = 0; j < getM(); j++) {
                System.out.print("Peso Anterior[" + j + "][" + i + "]: " + w[j][i] + " ");
                w[j][i] = w[j][i] + getLr() * ep[p] * getX()[p][j];
                System.out.println("Peso Modificado[" + j + "][" + i + "]: " + w[j][i] + " ");
            }
        }
        System.out.println("***Capa de Salida****");
        for (int i = 0; i < getN(); i++) {
            System.out.print("Umbral Anterior[" + i + "]: " + us[i] + " ");
            us[i] = us[i] + getLr() * el[i];
            System.out.println("Umbral Modificado[" + i + "]: " + us[i]);
        }

        for (int i = 0; i < getN(); i++) {
            for (int j = 0; j < getO(); j++) {
                System.out.print("Peso Anterior[" + j + "][" + i + "]: " + ws[j][i] + " ");
                ws[j][i] = ws[j][i] + getLr() * el[i] * h[j];
                System.out.println("Peso Modificado[" + j + "][" + i + "]: " + ws[j][i] + " ");
            }
        }
        System.out.println("");
        System.out.println("");
    }

    private void deltaModificado(int p, int e) {
        double[][] wsAux = ws;
        double[] usAux = us;
        double[][] wAux = w;
        double[] uAux = u;
        lrD = lrD / e;
        System.out.println("ACTUALIZACION DE PESOS Y UMBRALES DELTAMODIFICADO");
        System.out.println("***Primera Capa Oculta****");
        for (int i = 0; i < getO(); i++) {
            System.out.print("Umbral Anterior[" + i + "]: " + u[i] + " ");
            u[i] = u[i] + getLr() * ep[p] + lrD * (uAnt[i] - u[i]);
            System.out.println("Umbral Modificado[" + i + "]: " + u[i]);
        }

        for (int i = 0; i < getO(); i++) {
            for (int j = 0; j < getM(); j++) {
                System.out.print("Peso Anterior[" + j + "][" + i + "]: " + w[j][i] + " ");
                w[j][i] = w[j][i] + getLr() * ep[p] * getX()[p][j] + lrD * (wAnt[j][i] - w[j][i]);
                System.out.println("Peso Modificado[" + j + "][" + i + "]: " + w[j][i] + " ");
            }
        }
        System.out.println("***Capa de Salida****");
        for (int i = 0; i < getN(); i++) {
            System.out.print("Umbral Anterior[" + i + "]: " + us[i] + " ");
            us[i] = us[i] + getLr() * el[i] + lrD * (usAnt[i] - us[i]);
            System.out.println("Umbral Modificado[" + i + "]: " + us[i]);
        }

        for (int i = 0; i < getN(); i++) {
            for (int j = 0; j < getO(); j++) {
                System.out.print("Peso Anterior[" + j + "][" + i + "]: " + ws[j][i] + " ");
                ws[j][i] = ws[j][i] + getLr() * el[i] * h[j] + lrD * (wsAnt[j][i] - ws[j][i]);
                System.out.println("Peso Modificado[" + j + "][" + i + "]: " + ws[j][i] + " ");
            }
        }

        wsAnt = wsAux;
        usAnt = usAux;
        wAnt = wAux;
        uAnt = uAux;
        System.out.println("");
        System.out.println("");
    }

}
