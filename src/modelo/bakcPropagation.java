/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import vista.FrmBackPropagation;


/**
 *
 * @author Usuario
 */
public class bakcPropagation extends red implements Runnable {
//Multicapa Una Capa Oculta (MUCO)

    private int o;
    private String funcionActivacionEntrada;
    private String funcionActivacionSalida;
    private String algoritmo;

    public bakcPropagation() {
    }

    public bakcPropagation(int o, String funcionActivacionEntrada, String funcionActivacionSalida, String algoritmo, int m, int n, int patrones, double[][] x, double[][] yd, int epoch, double lr, double goal, String nombre) {
        super(m, n, patrones, x, yd, epoch, lr, goal, nombre);
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

    public double[][] getW() {
        return w;
    }

    public void setW(double[][] w) {
        this.w = w;
    }

    public double[] getU() {
        return u;
    }

    public void setU(double[] u) {
        this.u = u;
    }

    public double[][] getWs() {
        return ws;
    }

    public void setWs(double[][] ws) {
        this.ws = ws;
    }

    public double[] getUs() {
        return us;
    }

    public void setUs(double[] us) {
        this.us = us;
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
    public double[] eNl;
    double sumEp;
    double sumEl;
    public double lrD = 1;

    Random rand = new Random();

    public void inicializacionPesosUmbrales(int m, int o, int n) {
        this.setM(m);
        this.setO(o);
        this.setN(n);

        w = new double[getM()][getO()];
        u = new double[getO()];

        ws = new double[getO()][getN()];
        us = new double[getN()];
        //System.out.println("INICIALIZACION DE PESOS Y UMBRALES");
        //System.out.println("***Primera Capa Oculta****");
        for (int i = 0; i < getO(); i++) {
            u[i] = Math.random() * ThreadLocalRandom.current().nextInt(-1, 1 + 1);

            //System.out.println("Umbral [" + i + "]: " + u[i]);
        }
        for (int i = 0; i < getO(); i++) {
            for (int j = 0; j < getM(); j++) {
                w[j][i] = Math.random() * ThreadLocalRandom.current().nextInt(-1, 1 + 1);
                //System.out.print("Peso[" + j + "][" + i + "]: " + w[j][i] + " ");
            }
            //System.out.println("");
        }
        //System.out.println("***Capa Salida***");
        for (int i = 0; i < getN(); i++) {
            us[i] = Math.random() * ThreadLocalRandom.current().nextInt(-1, 1 + 1);
            //System.out.println("Umbral[" + i + "]: " + us[i]);
        }

        for (int i = 0; i < getN(); i++) {
            for (int j = 0; j < getO(); j++) {
                ws[j][i] = Math.random() * ThreadLocalRandom.current().nextInt(-1, 1 + 1);
                //System.out.print("Peso[" + j + "][" + i + "]: " + ws[j][i] + " ");
            }
            //System.out.println("");
        }
    }

    public ArrayList entrenamiento() {
        s = new double[getO()];
        h = new double[getO()];
        yr = new double[getN()];
        ss = new double[getN()];
        el = new double[getN()];
        ep = new double[getPatrones()];
        eNl = new double[getO()];

        double erms;
        //System.out.println("");
        //System.out.println("");
        ArrayList lista = new ArrayList<>();
        for (int e = 1; e <= getEpoch(); e++) {//ciclo de iteraciones
            //System.out.println("INTERACION # " + e);
            sumEp = 0;
            for (int p = 0; p < getPatrones(); p++) {//ciclo de patrones

                for (int i = 0; i < getO(); i++) {
                    s[i] = 0;
                    for (int j = 0; j < getM(); j++) {
                        //System.out.println("soma[" + i + "] = soma[" + i + "]:" + s[i] + " + x[" + p + "][" + j + "]:" + getX()[p][j] + " * w[" + j + "][" + i + "]:" + w[j][i]);
                        s[i] = s[i] + getX()[p][j] * w[j][i];
                    }
                    //System.out.println("soma : " + s[i]);
                    //System.out.println("soma Atenuada= " + "soma[" + i + "]:" + s[i] + " - " + "u[" + i + "]" + u[i]);
                    s[i] = s[i] - u[i];
                    //System.out.println("soma Atenuada: " + s[i]);
                    h[i] = activacion(getFuncionActivacionEntrada(), (s[i]));
                    //System.out.println("");
                    //System.out.println("Salida de la primera capa: " + h[i]);
                    //System.out.println("");

                }
                //System.out.println("");
                //System.out.println("");
                for (int i = 0; i < getN(); i++) {
                    ss[i] = 0;
                    for (int j = 0; j < getO(); j++) {
                        //System.out.println("somaS[" + i + "] = somaS[" + i + "]:" + ss[i] + " + h[" + j + "]:" + h[j] + " * w[" + j + "][" + i + "]:" + ws[j][i]);
                        ss[i] = ss[i] + h[j] * ws[j][i];
                    }
                    //System.out.println("somaS : " + ss[i]);
                    //System.out.println("soma Atenuada= " + "somaS[" + i + "]:" + ss[i] + " - " + "us[" + i + "]" + us[i]);
                    ss[i] = ss[i] - us[i];
                    //System.out.println("soma Atenuada: " + ss[i]);
                    yr[i] = activacion(getFuncionActivacionSalida(), (ss[i]));
                    //System.out.println("");
                    //System.out.println("");
                    //System.out.println("Salida de la Red: " + yr[i]);
                    //System.out.println("");
                    //System.out.println("");
                    el[i] = getYd()[p][i] - yr[i];
                    //System.out.println("Error Lineal = " + "yd[" + p + "][" + i + "]:" + getYd()[p][i] + " - " + "y[" + i + "]:" + el[i]);
                    System.out.println("Error Lineal: " + el[i]);
                }
                //proceso de eleccion de errores lineales optimos
                sumEl = 0;
                for (int i = 0; i < getN(); i++) {
                    sumEl = sumEl + abs(el[i]);
                }
                //calculo de los errores no lineales
                for (int i = 0; i < getO(); i++) {
                    double sum = 0;
                    for (int j = 0; j < getN(); j++) {
                        sum = sum + el[j] * ws[i][j];
                    }
                    eNl[i] = sum;
                }
                ep[p] = sumEl / getN();
                sumEl = 0;
                //System.out.println("");
                //System.out.println("");
                //System.out.println("Error por patron: " + ep[p]);
                //System.out.println("\n\n");
                sumEp = sumEp + ep[p];

                actualizacionPesosUmbrales(p);
            }
            erms = sumEp / getPatrones();
            lista.add(erms);
            
            FrmBackPropagation.cargar(lista, this.getGoal());
            //System.out.println("Error de la iterecion#" + e + ": " + erms + "\n");
            FrmBackPropagation.txtAreaVista.append("Error de la iterecion: " + erms + "\n");
            sumEp = 0;
            System.out.println("Error de la iterecion: " + erms);
            //System.out.println("");
            //System.out.println("");
            System.out.println("");
            if (erms <= getGoal()) {
                ArrayList pesos = new ArrayList();
                pesos.add(getW());
                pesos.add(getWs());

                ArrayList umbrales = new ArrayList();
                umbrales.add(getU());
                umbrales.add(getUs());
                int[] numCapas = {getM(),getO(),getN()};
                String[] funcion = {getFuncionActivacionEntrada(), getFuncionActivacionSalida()};
                getSetPesosUmbrales gspu;
                String tipoRed = "perceptron";
                gspu = new getSetPesosUmbrales(getNombre(), numCapas, getPatrones(), funcion, getAlgoritmo(), getLr(), pesos, umbrales,getX(),getYd());
                gspu.guardarPesosUmbrales();
                FrmBackPropagation.lblEstado.setText("Finalizado");
                //System.out.println("ENTRENAMIENTO FINALIZADO CON EXITO EN INTERACION " + e + "... ");
                FrmBackPropagation.txtAreaVista.append("ENTRENAMIENTO FINALIZADO CON EXITO EN INTERACION " + e + "... ");
                //System.out.println("");
                //System.out.println("Umbrales Optimos");
                FrmBackPropagation.txtAreaVista.append("Umbrales Optimos");
                String cad = "";
                for (int i = 0; i < u.length; i++) {
                    System.out.println(u[i]);
                    cad = cad + u[i] + " ";
                }
                FrmBackPropagation.txtAreaVista.append(cad);
                //System.out.println("");
                //System.out.println("Pesos Optimos");
                FrmBackPropagation.txtAreaVista.append("Pesos Optimos");
                cad = "";
                for (int i = 0; i < getN(); i++) {
                    for (int j = 0; j < getM(); j++) {
                        System.out.println(w[j][i] + " ");
                        cad = cad + w[j][i] + " ";
                    }
                    System.out.println("");
                    FrmBackPropagation.txtAreaVista.append(cad);
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
        FrmBackPropagation.lblEstado.setText("Finalizado");

        return null;
    }

    private void actualizacionPesosUmbrales(int p) {
        //System.out.println("ACTUALIZACION DE PESOS Y UMBRALES");
        //System.out.println("***Primera Capa Oculta****");
        for (int i = 0; i < getO(); i++) {
           // System.out.print("Umbral Anterior[" + i + "]: " + u[i] + " ");
            u[i] = u[i] + 2 * getLr() * eNl[i] * derivada(getFuncionActivacionEntrada(), s[i] - u[i]);
            //System.out.println("Umbral Modificado[" + i + "]: " + u[i]);
        }

        for (int i = 0; i < getO(); i++) {
            for (int j = 0; j < getM(); j++) {
               // System.out.print("Peso Anterior[" + j + "][" + i + "]: " + w[j][i] + " ");
                w[j][i] = w[j][i] + 2 * getLr() * eNl[i] * getX()[p][j] * derivada(getFuncionActivacionEntrada(), s[i] - u[i]);
                //System.out.println("Peso Modificado[" + j + "][" + i + "]: " + w[j][i] + " ");
            }
        }

        //System.out.println("***Capa de Salida****");
        for (int i = 0; i < getN(); i++) {
            //System.out.print("Umbral Anterior[" + i + "]: " + us[i] + " ");
            us[i] = us[i] + getLr() * el[i];
            //System.out.println("Umbral Modificado[" + i + "]: " + us[i]);
        }

        for (int i = 0; i < getN(); i++) {
            for (int j = 0; j < getO(); j++) {
                //System.out.print("Peso Anterior[" + j + "][" + i + "]: " + ws[j][i] + " ");
                ws[j][i] = ws[j][i] + 2 * getLr() * el[i] * h[j];
                //System.out.println("Peso Modificado[" + j + "][" + i + "]: " + ws[j][i] + " ");
            }
        }
        //System.out.println("");
        //System.out.println("");
    }

    @Override
    public void run() {
       
        entrenamiento();
    }

}
