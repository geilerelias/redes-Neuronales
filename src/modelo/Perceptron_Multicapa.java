/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import static java.lang.Math.abs;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Perceptron_Multicapa {

    private double x[][];
    private double yd[][];
    private String nombre;
    private int m;
    private int n;
    private int pat;

    private int o;
    private int ñ;

    //aprendizajes de la red
    //parámetros de entrenamiento
    private double rata;
    private double errorMax;
    private int itMax;
    private double rd;

    // regla delta
    private String algoritmo;
    private String funcionE;
    private String funcionC;
    private String funcionS;

    public Perceptron_Multicapa() {
    }

    public Perceptron_Multicapa(double[][] x, double[][] yd, String nombre, int m, int n, int pat, int o, int ñ, double rata, double errorMax, int itMax, String algoritmo, String funcionE, String funcionC, String funcionS) {
        this.x = x;
        this.yd = yd;
        this.nombre = nombre;
        this.m = m;
        this.n = n;
        this.pat = pat;
        this.o = o;
        this.ñ = ñ;
        this.rata = rata;
        this.errorMax = errorMax;
        this.itMax = itMax;
        this.algoritmo = algoritmo;
        this.funcionE = funcionE;
        this.funcionC = funcionC;
        this.funcionS = funcionS;
    }

    public static void main(String[] args) {
        //cargamos los datos de entrada

        //minibot
        //double x[][]={{1,1,1},{0,1,1},{1,1,0},{0,0,0}};
        //double yd[][]={{0,0},{0,1},{1,0},{1,1}};
        //embarazo
        /*double x[][]={{3.8,1.2,1},{3.2,1.6,1},{3.5,1.9,1},{3.9,2.0,1},{4.0,1.3,1},{3.5,1.3,1},{3.8,1.9,1},{3.9,2.1,1},
                 {4.1,5.9,0},{4.0,7.9,1},{3.6,8.3,1},{3.9,6.2,0},{3.8,6.5,0},{2.9,7.9,1},{2.9,4.6,0},{2.8,7.9,1}
    };
     double yd[][]={{1},{1},{1},{1},{1},{1},{1},{1}
                ,{0},{0},{0},{0},{0},{0},{0},{0}
};*/
        //XOR
        //double x[][]={{1,1},{1,0},{0,1},{0,0}};
        //double yd[][]={{0},{1},{1},{0}};
        //OR
        double x[][]={{1,1},{1,0},{0,1},{0,0}};
        double yd[][]={{1},{1},{1},{0}};
        //AND
        //double x[][] = {{1, 1}, {1, 0}, {0, 1}, {0, 0}};
        //double yd[][] = {{1}, {0}, {0}, {0}};
        String nombre = "trainOr";
        int m = 2;
        int n = 1;
        int pat = 4;

        int o = 6;
        int ñ = 3;

        //aprendizajes de la red
        //parámetros de entrenamiento
        double rata = 0.01;
        double errorMax = 0;
        int itMax = 1000;
        double rd;

        // regla delta
        String algoritmo = "delta";
        String funcionE = "Sigmoidal";
        String funcionC = "Gaussiana";
        String funcionS = "Sigmoidal";
        //matriz de pesos 
        Perceptron_Multicapa p = new Perceptron_Multicapa(x, yd, nombre, m, n, pat, o, ñ, rata, errorMax, itMax, algoritmo, funcionE, funcionC, funcionS);
        p.inicializarPesosUmbrales();
        do {
             p.entrenar();
        }  while (JOptionPane.showConfirmDialog(null, "Seguir Entrenado","",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION);
    }

    double[][] wo = new double[getM()][getO()];
    double[][] wñ = new double[getO()][getÑ()];
    double[][] ws = new double[getÑ()][getN()];

    double[][] wx = new double[getM()][getO()];
    double[][] wox = new double[getO()][getÑ()];
    double[][] wsx = new double[getÑ()][getN()];
    //definir vectores de umbrales
    double uo[] = new double[getO()];
    double uñ[] = new double[getÑ()];
    double us[] = new double[getN()];

    double uox[] = new double[getO()];
    double uñx[] = new double[getÑ()];
    double usx[] = new double[getN()];

    //inicializar la red neuronal
    double yo[] = new double[getO()];
    double yñ[] = new double[getÑ()];
    double yr[] = new double[getN()];
    double El[] = new double[getN()];
    double Ep[] = new double[getPat()];

    double sumEl = 0;
    double sumEp = 0;
    double ERMS = 0;

    void inicializarPesosUmbrales() {
        wo = new double[getM()][getO()];
        wñ = new double[getO()][getÑ()];
        ws = new double[getÑ()][getN()];

        wx = new double[getM()][getO()];
        wox = new double[getO()][getÑ()];
        wsx = new double[getÑ()][getN()];
        //definir vectores de umbrales
        uo = new double[getO()];
        uñ = new double[getÑ()];
        us = new double[getN()];

        uox = new double[getO()];
        uñx = new double[getÑ()];
        usx = new double[getN()];

        //inicializar los pesos y umbrales
        for (int i = 0; i < getO(); i++) {
            for (int j = 0; j < getM(); j++) {
                wo[j][i] = Math.random() * ThreadLocalRandom.current().nextInt(-1, 1 + 1);
                wx[j][i] = 0;
                System.out.print("w" + j + i + ":" + wx[j][i] + " ");
            }
            System.out.println();
            uo[i] = Math.random() * ThreadLocalRandom.current().nextInt(-1, 1 + 1);
            uox[i] = 0;
        }
        System.out.println();
        for (int i = 0; i < getÑ(); i++) {
            for (int k = 0; k < getO(); k++) {
                wñ[k][i] = Math.random() * ThreadLocalRandom.current().nextInt(-1, 1 + 1);
                wox[k][i] = 0;
                System.out.print("wo" + k + i + ":" + wox[k][i] + " ");
            }
            System.out.println();
            uñ[i] = Math.random() * ThreadLocalRandom.current().nextInt(-1, 1 + 1);
            uñx[i] = 0;
        }
        System.out.println();
        for (int i = 0; i < getN(); i++) {
            for (int k = 0; k < getÑ(); k++) {
                ws[k][i] = Math.random() * ThreadLocalRandom.current().nextInt(-1, 1 + 1);
                wsx[k][i] = 0;
                System.out.print("ws" + k + i + ":" + wsx[k][i] + " ");
            }
            System.out.println();
            us[i] = Math.random() * ThreadLocalRandom.current().nextInt(-1, 1 + 1);
            usx[i] = 0;
        }
    }

    void entrenar() {
        //inicializar la red neuronal
        yo = new double[getO()];
        yñ = new double[getÑ()];
        yr = new double[getN()];
        El = new double[getN()];
        Ep = new double[getPat()];
        int it = 1;
        while (it < getItMax()) {
            int p = 0;

            while (p < getPat()) {

                //calcular salida capa oculta 1 "O"
                double somao = 0;
                for (int i = 0; i < getO(); i++) {
                    for (int j = 0; j < getM(); j++) {
                        somao = somao + wo[j][i] * getX()[p][j];
                    }
                    somao = somao - uo[i];
                    yo[i] = activacion(getFuncionE(), somao);
                    somao = 0;
                    //yo[i]=sigmoide(somao);
                }

                //calcular la salida capa oculta 2 "Ñ"
                double somañ = 0;
                for (int i = 0; i < getÑ(); i++) {

                    for (int j = 0; j < getO(); j++) {
                        somañ = somañ + wñ[j][i] * yo[j];
                    }
                    somañ = somañ - uñ[i];
                    yñ[i] = activacion(getFuncionC(), somañ);
                    somañ = 0;
                    //yñ[i]=sigmoide(somañ);
                }

                // calculamos la salida capa de salida "N"
                double soma = 0;

                for (int i = 0; i < getN(); i++) {
                    for (int j = 0; j < getÑ(); j++) {
                        soma = soma + ws[j][i] * yñ[j];
                    }
                    soma = soma - us[i];
                    //yr[i]=soma;
                    // System.out.println("función soma "+soma);
                    yr[i] = activacion(getFuncionS(), soma);
                    soma = 0;
                    //yr[i]=gausiana(soma);
                    El[i] = getYd()[p][i] - yr[i];
                    //System.out.println("ERROR LINEAL "+El[i
                    System.out.println("salida deseada " + getYd()[p][i] + " salida de la red " + yr[i]);
                    sumEl = sumEl + abs(El[i]);
                }

                Ep[p] = sumEl / getN();
                //System.out.println("suma de errores lineales"+sumEl);
                sumEl = 0;
                sumEp = sumEp + Ep[p];

                //modificamos pesos y umbrales
                if ("delta".equals(getAlgoritmo())) {
                    for (int i = 0; i < getO(); i++) {
                        for (int j = 0; j < getM(); j++) {
                            wo[j][i] = wo[j][i] + getRata() * Ep[p] * getX()[p][j];
                        }
                        uo[i] = uo[i] + getRata() * Ep[p];
                    }

                    //modificamos pesos entre capas
                    for (int i = 0; i < getÑ(); i++) {
                        for (int j = 0; j < getO(); j++) {
                            wñ[j][i] = wñ[j][i] + getRata() * Ep[p] * yo[j];
                        }

                        uñ[i] = uñ[i] + getRata() * Ep[p];
                    }

                    //modificación pesos y umbrasel salida
                    for (int i = 0; i < getN(); i++) {
                        for (int j = 0; j < getÑ(); j++) {
                            ws[j][i] = ws[j][i] + getRata() * El[i] * yñ[j];
                        }
                        us[i] = us[i] + getRata() * El[i];
                    }
                } else if ("deltam".equals(getAlgoritmo())) {
                    //regla delta modificada

                    setRd(1 / it);
                    double pesoAux;
                    double umbAux;
                    for (int i = 0; i < getO(); i++) {
                        for (int j = 0; j < getM(); j++) {
                            pesoAux = wñ[j][i];
                            wñ[j][i] = wñ[j][i] + getRata() * Ep[p] * getX()[p][j] + getRd() * (wñ[j][i] - wx[j][i]);
                            //System.out.print("w"+j+i+":"+wx[j][i]+" ");
                            wx[j][i] = pesoAux;
                        }
                        umbAux = uo[i];
                        uo[i] = uo[i] + getRata() * Ep[p] + getRd() * (uo[i] - uox[i]);
                        uox[i] = umbAux;
                    }

                    //modificamos pesos entre capas
                    for (int i = 0; i < getÑ(); i++) {
                        for (int j = 0; j < getO(); j++) {
                            pesoAux = wñ[j][i];
                            wñ[j][i] = wñ[j][i] + getRata() * Ep[p] * yo[j] + getRd() * (wñ[j][i] - wox[j][i]);
                            wox[j][i] = pesoAux;
                        }
                        umbAux = uñ[i];
                        uñ[i] = uñ[i] + getRata() * Ep[p] + getRd() * (uñ[i] - uñx[i]);
                        uñx[i] = umbAux;
                    }

                    //modificación pesos y umbrasel salida
                    for (int i = 0; i < getN(); i++) {
                        for (int j = 0; j < getÑ(); j++) {
                            pesoAux = ws[j][i];
                            //ws[j][i]=ws[j][i]+rata*El[i]*yñ[i]+rd*(wsx[j][i]-ws[j][i]);
                            ws[j][i] = ws[j][i] + getRata() * El[i] * yñ[i] + getRd() * (ws[j][i] - wsx[j][i]);
                            wsx[j][i] = pesoAux;
                        }
                        umbAux = us[i];
                        //us[i]=us[i]+rata*El[i]+rd*(usx[i]-us[i]);
                        us[i] = us[i] + getRata() * El[i] + getRd() * (us[i] - usx[i]);
                        usx[i] = umbAux;
                    }
                }//fin regla delta modificada
                p++;
            }//ciclo de patrones

            ERMS = sumEp / getPat();
            System.out.println("ERMS :" + ERMS);
            sumEp = 0;

            if (ERMS <= getErrorMax()) {
                ArrayList pesos = new ArrayList();
                pesos.add(wo);
                pesos.add(wñ);
                pesos.add(ws);
                ArrayList umbrales = new ArrayList();
                umbrales.add(uo);
                umbrales.add(uñ);
                umbrales.add(us);
                int[] numCapas = {getM(),getO(), getÑ(),getN()};
                String[] funcion = {getFuncionE(), getFuncionC(), getFuncionS()};
                getSetPesosUmbrales gspu;
                String tipoRed = "perceptron";
                gspu = new getSetPesosUmbrales(getNombre(), numCapas, getPat(), funcion, getAlgoritmo(), getRata(), pesos, umbrales,x,yd);
                gspu.guardarPesosUmbrales();
                gspu.leerPesosUmbrales();
                System.out.println("iteración :" + it);
                /*for (int t = 0; t < p; t++){

                    double[] v = new double[m];
                    JPanel panel = new JPanel(new GridLayout(3, 2, 5, 1));//filas, columnas, espacio entre filas, espacio entre columnas
                    for (int i = 0; i < m; i++) {
                        panel.add(new JLabel("entrada " + i + ": "));
                        panel.add(new JTextField());

                    }
                    JOptionPane.showMessageDialog(null, panel);
                    int k = 0;
                    for (int l = 0; l < panel.getComponentCount(); l++) {
                        if (panel.getComponent(l) instanceof JTextField) {
                            JTextField textField = (JTextField) panel.getComponent(l);
                            textField.setEditable(false);
                            System.out.println(textField.getText());
                            v[k] = Double.parseDouble(textField.getText());
                            k++;
                        }
                    }
                    //calcular salida capa oculta 1 "O"
                    double somao = 0;
                    for (int i = 0; i < o; i++) {
                        for (int j = 0; j < m; j++) {
                            somao = somao + wo[j][i] * v[j];
                        }
                        somao = somao - uo[i];
                        yo[i] = activacion(funcionE, somao);
                        //yo[i]=sigmoide(somao);
                    }

                    //calcular la salida capa oculta 2 "Ñ"
                    double somañ = 0;
                    for (int i = 0; i < ñ; i++) {

                        for (int j = 0; j < o; j++) {
                            somañ = somañ + wñ[j][i] * yo[j];
                        }
                        somañ = somañ - uñ[i];
                        yñ[i] = activacion(funcionC, somañ);
                        //yñ[i]=sigmoide(somañ);
                    }

                    // calculamos la salida capa de salida "N"
                    double soma = 0;

                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < ñ; j++) {
                            soma = soma + ws[j][i] * yñ[j];
                        }
                        soma = soma - us[i];
                        //yr[i]=soma;
                        // System.out.println("función soma "+soma);
                        yr[i] = activacion(funcionS, soma);
                        //yr[i]=gausiana(soma);
                        //System.out.println("ERROR LINEAL "+El[i
                    }

                    JPanel panel2 = new JPanel(new GridLayout(2, m + n, 5, 1));//filas, columnas, espacio entre filas, espacio entre columnas
                    for (int i = 0; i < m; i++) {
                        panel2.add(new JLabel("Entrada " + i + ": "));
                    }
                    for (int i = 0; i < n; i++) {
                        panel2.add(new JLabel("salida " + i + ": "));
                    }

                    for (int i = 0; i < m; i++) {

                        panel2.add(new JLabel("" + v[i]));
                    }
                    for (int i = 0; i < n; i++) {

                        panel2.add(new JLabel("" + yr[i]));
                    }
                    JOptionPane.showMessageDialog(null, panel2);
                    
                }*/
                break;
            }
            it++;
            //System.out.println("ERROR ERMS:"+ERMS);
        }//ciclo iteraciones

        if (ERMS > getErrorMax()) {
            JOptionPane.showMessageDialog(null, "NO SE PUDO ENTRENAR LA RED", "ENTRENAMIENTO", JOptionPane.ERROR_MESSAGE);

        }

    }//main

    static public double sigmoide(double soma) {
        double valor;
        double e = Math.E;
        double p = 0.01;
        valor = 1 / (1 + Math.pow(e, -soma / p));
        return valor;
    }

    static public double gausiana(double soma) {
        double e = Math.E;
        double gs = soma * Math.pow(e, -soma);
        return gs;
    }

    public static double activacion(String funcion, double num) {
        double activacion = 0.0;
        switch (funcion) {
            case "Lineal":
                /**
                 * Funcion de activacion(lineal): Identidad
                 */
                activacion = num;
                break;
            case "Sigmoidal":
                /**
                 * Funcion de activacion(sigmoidea): Logaritmo Sigmoidal
                 */
                activacion = 1.0 / (1.0 + (Math.pow(Math.E, -num / 0.01)));
                break;
            case "TanSigmoidal":
                /**
                 * Funcion de activacion(sigmoidea): Tangente Sigmoidal
                 */
                activacion = (2.0 / (1.0 + (Math.pow(Math.E, -num)))) - 1.0;
                break;
            case "TanHiperbolica":
                /**
                 * Funcion de activacion(sigmoidea): Tangente Hiperbolica
                 */
                activacion = ((Math.pow(Math.E, num)) - ((Math.pow(Math.E, -num)))) / ((Math.pow(Math.E, num)) + ((Math.pow(Math.E, -num))));
                break;
            case "Gaussiana":
                /**
                 * Funcion de activacion (gaussiana): Campana de Gauss
                 */
                activacion = num * Math.pow(Math.E, -num);
                break;
            case "Seno":
                /**
                 * Funcion de activacion (gaussiana): Campana de Gauss
                 */
                activacion = sin(num);
                break;
            case "Coseno":
                /**
                 * Funcion de activacion (gaussiana): Campana de Gauss
                 */
                activacion = cos(num);
                break;
            default:
                break;
        }
        return activacion;
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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the m
     */
    public int getM() {
        return m;
    }

    /**
     * @param m the m to set
     */
    public void setM(int m) {
        this.m = m;
    }

    /**
     * @return the n
     */
    public int getN() {
        return n;
    }

    /**
     * @param n the n to set
     */
    public void setN(int n) {
        this.n = n;
    }

    /**
     * @return the pat
     */
    public int getPat() {
        return pat;
    }

    /**
     * @param pat the pat to set
     */
    public void setPat(int pat) {
        this.pat = pat;
    }

    /**
     * @return the o
     */
    public int getO() {
        return o;
    }

    /**
     * @param o the o to set
     */
    public void setO(int o) {
        this.o = o;
    }

    /**
     * @return the ñ
     */
    public int getÑ() {
        return ñ;
    }

    /**
     * @param ñ the ñ to set
     */
    public void setÑ(int ñ) {
        this.ñ = ñ;
    }

    /**
     * @return the rata
     */
    public double getRata() {
        return rata;
    }

    /**
     * @param rata the rata to set
     */
    public void setRata(double rata) {
        this.rata = rata;
    }

    /**
     * @return the errorMax
     */
    public double getErrorMax() {
        return errorMax;
    }

    /**
     * @param errorMax the errorMax to set
     */
    public void setErrorMax(double errorMax) {
        this.errorMax = errorMax;
    }

    /**
     * @return the itMax
     */
    public int getItMax() {
        return itMax;
    }

    /**
     * @param itMax the itMax to set
     */
    public void setItMax(int itMax) {
        this.itMax = itMax;
    }

    /**
     * @return the rd
     */
    public double getRd() {
        return rd;
    }

    /**
     * @param rd the rd to set
     */
    public void setRd(double rd) {
        this.rd = rd;
    }

    /**
     * @return the algoritmo
     */
    public String getAlgoritmo() {
        return algoritmo;
    }

    /**
     * @param algoritmo the algoritmo to set
     */
    public void setAlgoritmo(String algoritmo) {
        this.algoritmo = algoritmo;
    }

    /**
     * @return the funcionE
     */
    public String getFuncionE() {
        return funcionE;
    }

    /**
     * @param funcionE the funcionE to set
     */
    public void setFuncionE(String funcionE) {
        this.funcionE = funcionE;
    }

    /**
     * @return the funcionC
     */
    public String getFuncionC() {
        return funcionC;
    }

    /**
     * @param funcionC the funcionC to set
     */
    public void setFuncionC(String funcionC) {
        this.funcionC = funcionC;
    }

    /**
     * @return the funcionS
     */
    public String getFuncionS() {
        return funcionS;
    }

    /**
     * @param funcionS the funcionS to set
     */
    public void setFuncionS(String funcionS) {
        this.funcionS = funcionS;
    }

}//clase
