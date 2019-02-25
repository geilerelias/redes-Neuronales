/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Math.abs;
import static java.lang.Math.cos;
import static java.lang.Math.exp;
import static java.lang.Math.sin;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author eldoc
 */
public class back extends red {
    //
    private int m, n, o, patrones;
    private double[][] x;
    private double[][] yd;
    private int epoch;
    private double lr, goal;
    private String funcionActivacionCapa1;
    private String funcionActivacionSalida;

    public back() {
    }

    public back(int m, int n, int o, int patrones, double[][] x, double[][] yd, int epoch, double lr, double goal, String funcionActivacionCapa1, String funcionActivacionSalida) {
        this.m = m;
        this.n = n;
        this.o = o;
        this.patrones = patrones;
        this.x = x;
        this.yd = yd;
        this.epoch = epoch;
        this.lr = lr;
        this.goal = goal;
        this.funcionActivacionCapa1 = funcionActivacionCapa1;
        this.funcionActivacionSalida = funcionActivacionSalida;
    }

  

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getPatrones() {
        return patrones;
    }

    public void setPatrones(int patrones) {
        this.patrones = patrones;
    }

    public double[][] getX() {
        return x;
    }

    public void setX(double[][] x) {
        this.x = x;
    }

    public double[][] getYd() {
        return yd;
    }

    public void setYd(double[][] yd) {
        this.yd = yd;
    }

    public int getEpoch() {
        return epoch;
    }

    public void setEpoch(int epoch) {
        this.epoch = epoch;
    }

    public double getLr() {
        return lr;
    }

    public void setLr(double lr) {
        this.lr = lr;
    }

    public double getGoal() {
        return goal;
    }

    public void setGoal(double goal) {
        this.goal = goal;
    }

    public int getO() {
        return o;
    }

    public void setO(int o) {
        this.o = o;
    }

    public String getFuncionActivacionCapa1() {
        return funcionActivacionCapa1;
    }

    public void setFuncionActivacionCapa1(String funcionActivacionCapa1) {
        this.funcionActivacionCapa1 = funcionActivacionCapa1;
    }

    public String getFuncionActivacionSalida() {
        return funcionActivacionSalida;
    }

    public void setFuncionActivacionSalida(String funcionActivacionSalida) {
        this.funcionActivacionSalida = funcionActivacionSalida;
    }

    public JFileChooser fileChooser;
    //primera capa oculta
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
            //u[i] = -1 + (1 - -1) * rand.nextDouble();
            u[i] = rand.nextDouble();
            System.out.println("Umbral [" + i + "]: " + u[i]);
        }
        for (int i = 0; i < getO(); i++) {
            for (int j = 0; j < getM(); j++) {
                //w[j][i] = -1 + (1 - -1) * rand.nextDouble();
                w[j][i] = rand.nextDouble();
                System.out.print("Peso[" + j + "][" + i + "]: " + w[j][i] + " ");
            }
            System.out.println("");
        }

        System.out.println("***Capa Salida***");
        for (int i = 0; i < getN(); i++) {
            //us[i] = -1 + (1 - -1) * rand.nextDouble();
            us[i] = rand.nextDouble();
            System.out.println("Umbral[" + i + "]: " + us[i]);
        }

        for (int i = 0; i < getN(); i++) {
            for (int j = 0; j < getO(); j++) {
                //ws[j][i] = -1 + (1 - -1) * rand.nextDouble();
                ws[j][i] = rand.nextDouble();
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
        eNl = new double[getO()];

        double erms;
        System.out.println("");
        System.out.println("");

        for (int e = 1; e < getEpoch(); e++) {//ciclo de iteraciones
            System.out.println("INTERACION # " + e);
            double sumEp = 0;
            for (int p = 0; p < getPatrones(); p++) {//ciclo de patrones
                double sumEl = 0;
                System.out.println("");
                System.out.println("");
                System.out.println("Primera Capa Oculta");
                System.out.println("");
                for (int i = 0; i < getO(); i++) {
                    double suma = 0;
                    for (int j = 0; j < getM(); j++) {
                        System.out.println("soma[" + i + "] = soma[" + i + "]:" + s[i] + " + x[" + p + "][" + j + "]:" + getX()[p][j] + " * w[" + j + "][" + i + "]:" + w[j][i]);
                        suma = suma + getX()[p][j] * w[j][i];
                    }
                    s[i] = suma;
                    System.out.println("soma : " + s[i]);
                    System.out.println("soma Atenuada= " + "soma[" + i + "]:" + s[i] + " - " + "u[" + i + "]" + u[i]);
                    s[i] = s[i] - u[i];
                    System.out.println("soma Atenuada: " + s[i]);
                    h[i] = activacion(getFuncionActivacionCapa1(), (s[i]));
                    System.out.println("");
                    System.out.println("Salida de la primera capa neurona[ " + i + " ] : " + h[i]);
                    System.out.println("");

                }

                System.out.println("");
                System.out.println("");
                System.out.println("Capa de salida");
                System.out.println("");
                System.out.println("");
                System.out.println("");

                for (int i = 0; i < getN(); i++) {
                    ss[i] = 0;
                    for (int j = 0; j < getO(); j++) {
                        System.out.println("somaS[" + i + "] = somaS[" + i + "]:" + ss[i] + " + h[" + j + "]:" + h[j] + " * wS[" + j + "][" + i + "]:" + ws[j][i]);
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
                    System.out.println("Error Lineal = " + "yd[" + p + "][" + i + "]:" + getYd()[p][i] + " - " + "y[" + i + "]:" + yr[i]);
                    System.out.println("Error Lineal: " + el[i]);

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
                System.out.println("");
                System.out.println("");
                System.out.println("Error del patron[" + p + "]: " + ep[p]);
                System.out.println("\n\n");
                sumEp = sumEp + ep[p];

                actualizacionPesosUmbrales(p);
            }
            erms = sumEp / getPatrones();
            sumEp = 0;
            System.out.println("Error de la iterecion #" + e + ": " + erms);
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
                l.add(4, ws);
                l.add(5, us);
                return l;
            }
        }
        return null;
    }

    private void abrirArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Elige un Archivo qu econtenga las Entradas y salid");
        int returnVal = fileChooser.showOpenDialog(null);
        String linea;

        if (returnVal == JFileChooser.APPROVE_OPTION) {

            File file = fileChooser.getSelectedFile();
            try {
                if (file == null) {
                    JOptionPane.showMessageDialog(null, "\nNo se ha encontrado el archivo o \n esta inaccesible en estos momentos", "ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);
                } else {
                    FileReader archivos = new FileReader(file);
                    BufferedReader lee = new BufferedReader(archivos);
                    int cont = 0;
                    int cw = 0;
                    int cws = 0;
                    while ((linea = lee.readLine()) != null) {

                        String[] values = linea.split(" ");
                        Object[] conf = null;//vector definido por [m-0,n-1,p-2,o-3,fc-4,fs-5]
                        //cargo el vector deumbrales
                        switch (cont) {
                            case 0: {
                                conf = values;
                            }
                            case 1: {
                                for (int i = 0; i < values.length; i++) {
                                    u[i] = Double.parseDouble(values[i]);
                                }
                                break;
                            }
                            case 2: {
                                for (int i = 0; i < values.length; i++) {
                                    us[i] = Double.parseDouble(values[i]);
                                }
                                break;
                            }
                            case 3: {
                                for (int i = 0; i < values.length; i++) {
                                    //se obtiene el primer caracter de el arreglo de strings
                                    w[i][cw] = Double.parseDouble(values[i]);
                                }
                                cw++;
                                if ((int) conf[3] != cw) {
                                    cont = 2;
                                }

                                break;
                            }
                            case 4: {
                                for (int i = 0; i < values.length; i++) {
                                    //se obtiene el primer caracter de el arreglo de strings
                                    ws[i][cws] = Double.parseDouble(values[i]);
                                }
                                cw++;
                                if ((int) conf[1] != cw) {
                                    cont = 3;
                                }
                                break;
                            }

                        }

                        cont++;

                    }
                    lee.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex + "" + "\nNo se ha encontrado el archivo", "ADVERTENCIA!!!", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "\nOpeeracion cancelada por el usuario", "ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);

        }
    }

    private void guardarArchivo() {
        String ruta;
        try {
            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                ruta = fileChooser.getSelectedFile().getAbsolutePath();
                //Aqui ya tiens la ruta,,,ahora puedes crear un fichero n esa ruta y escribir lo k kieras...
                File archivo = new File(ruta);
                boolean seguir = true;
                if (archivo.exists()) {
                    if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(null, "El fichero existe,deseas reemplazarlo?", "Titulo", JOptionPane.YES_NO_OPTION)) {
                        seguir = false;
                    }
                }

                if (seguir) {
                    try {
                        String cad;
                        try (FileWriter fw = new FileWriter(archivo, true)) {
                            PrintWriter pw = new PrintWriter(fw);
                            //[m-0,n-1,p-2,o-3,fc-4,fs-5]
                            cad = getM() + " " + getN() + " " + getPatrones() + " " + getO() + " " + getFuncionActivacionCapa1() + " " + getFuncionActivacionSalida() + " ";
                            pw.println(cad);
                            cad = "";
                            for (int i = 0; i < getO(); i++) {
                                cad = cad + (u[i] + " ");
                            }
                            pw.println(cad);
                            cad = "";
                            for (int i = 0; i < getN(); i++) {
                                cad = cad + (us[i] + " ");
                            }
                            pw.println(cad);
                            for (int i = 0; i < getO(); i++) {
                                cad = "";
                                for (int j = 0; j < getM(); j++) {
                                    cad = cad + (w[j][i] + " ");
                                }
                                pw.println(cad);
                            }

                            for (int i = 0; i < getN(); i++) {
                                cad = "";
                                for (int j = 0; j < getO(); j++) {
                                    cad = cad + (ws[j][i] + " ");
                                }
                                pw.println(cad);
                            }
                        }

                    } catch (IOException ex) {
                        System.err.print("Excepcion: " + ex.getMessage());
                    }
                    if (!(ruta.endsWith(".txt"))) {
                        File temp = new File(ruta + ".txt");
                        fileChooser.getSelectedFile().renameTo(temp);//renombramos el archivo
                    }

                    JOptionPane.showMessageDialog(null, "Archivo Guardado exitoso!", "Guardando", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar el archivo!", "Oops! Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void simular() {
        double[] v = new double[this.getM()];
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 1));//filas, columnas, espacio entre filas, espacio entre columnas
        for (int i = 0; i < this.getM(); i++) {
            panel.add(new JLabel("entrada " + i + ": "));
            panel.add(new JTextField());

        }
        JOptionPane.showMessageDialog(null, panel);
        int k = 0;
        for (int x = 0; x < panel.getComponentCount(); x++) {
            if (panel.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) panel.getComponent(x);
                textField.setEditable(false);
                System.out.println(textField.getText());
                v[k] = Double.parseDouble(textField.getText());
                k++;
            }
        }
        for (int i = 0; i < getO(); i++) {
            s[i] = 0;
            for (int j = 0; j < getM(); j++) {
                System.out.println("soma[" + i + "] = soma[" + i + "]:" + s[i] + " + x[" + j + "]:" + v[j] + " * w[" + j + "][" + i + "]:" + w[j][i]);
                s[i] = s[i] + v[j] * w[j][i];
            }
            System.out.println("soma : " + s[i]);
            System.out.println("soma Atenuada= " + "soma[" + i + "]:" + s[i] + " - " + "u[" + i + "]" + u[i]);
            s[i] = s[i] - u[i];
            System.out.println("soma Atenuada: " + s[i]);
            h[i] = activacion(getFuncionActivacionCapa1(), (s[i]));
            System.out.println("");
            System.out.println("Salida de la primera capa neurona[ " + i + " ] : " + h[i]);
            System.out.println("");

        }

        System.out.println("");
        System.out.println("");

        System.out.println("");
        System.out.println("");
        panel.removeAll();
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

        }
        JPanel panel2 = new JPanel(new GridLayout(2, getM() + getN(), 5, 1));//filas, columnas, espacio entre filas, espacio entre columnas
        for (int i = 0; i < getM(); i++) {
            panel2.add(new JLabel("Entrada " + i + ": "));
        }
        for (int i = 0; i < getN(); i++) {
            panel2.add(new JLabel("salida " + i + ": "));
        }

        for (int i = 0; i < getM(); i++) {

            panel2.add(new JLabel("" + v[i]));
        }
        for (int i = 0; i < getN(); i++) {

            panel2.add(new JLabel("" + yr[i]));
        }
        JOptionPane.showMessageDialog(null, panel2);
    }

    private void actualizacionPesosUmbrales(int p) {
        System.out.println("ACTUALIZACION DE PESOS Y UMBRALES");
        System.out.println("***Primera Capa Oculta****");
        for (int i = 0; i < getO(); i++) {
            System.out.print("Umbral Anterior[" + i + "]: " + u[i] + " ");
            u[i] = u[i] + 2 * getLr() * eNl[i] * derivada(funcionActivacionCapa1, s[i] - u[i]);
            System.out.println("Umbral Modificado[" + i + "]: " + u[i]);
        }

        for (int i = 0; i < getO(); i++) {
            for (int j = 0; j < getM(); j++) {
                System.out.print("Peso Anterior[" + j + "][" + i + "]: " + w[j][i] + " ");
                w[j][i] = w[j][i] + 2 * getLr() * eNl[i] * getX()[p][j] * derivada(funcionActivacionCapa1, s[i] - u[i]);
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
                ws[j][i] = ws[j][i] + 2 * getLr() * el[i] * h[j];
                System.out.println("Peso Modificado[" + j + "][" + i + "]: " + ws[j][i] + " ");
            }
        }
        System.out.println("");
        System.out.println("");
    }

    public double activacion(String funcion, double potencial) {
        double activacion = 0.0;
        switch (funcion) {
            case "Lineal":
                /**
                 * Funcion de activacion(lineal): Identidad
                 */
                activacion = potencial;
                break;
            case "Sigmoidal":
                /**
                 * Funcion de activacion(sigmoidea): Logaritmo Sigmoidal
                 */
                activacion = 1.0 / (1.0 + (Math.pow(Math.E, -potencial)));
                break;
            case "TanSigmoidal":
                /**
                 * Funcion de activacion(sigmoidea): Tangente Sigmoidal
                 */
                activacion = (2.0 / (1.0 + (Math.pow(Math.E, -potencial)))) - 1.0;
                break;
            case "TanHiperbolica":
                /**
                 * Funcion de activacion(sigmoidea): Tangente Hiperbolica
                 */
                activacion = ((Math.pow(Math.E, potencial)) - ((Math.pow(Math.E, -potencial)))) / ((Math.pow(Math.E, potencial)) + ((Math.pow(Math.E, -potencial))));
                break;
            case "Gaussiana":
                /**
                 * Funcion de activacion (gaussiana): Campana de Gauss
                 */
                activacion = potencial * Math.pow(Math.E, -potencial);
                break;
            case "Seno":
                /**
                 * Funcion de activacion (gaussiana): Campana de Gauss
                 */
                activacion = sin(potencial);
                break;
            case "Coseno":
                /**
                 * Funcion de activacion (gaussiana): Campana de Gauss
                 */
                activacion = cos(potencial);
                break;
            default:
                break;
        }
        return activacion;
    }

    public double derivada(String funcion, double potencial) {
        double derivada = 0.0;
        switch (funcion) {
            case "Lineal":
                /**
                 * Derivada 'Identidad Lineal'
                 */
                derivada = 1.0;
                break;
            case "Sigmoidal":
                /**
                 * Derivada 'Logaritmo Sigmoidal'
                 */
                derivada = activacion("Sigmoidal", potencial) * (1 - activacion("Sigmoidal", potencial));
                break;
            case "TanSigmoidal":
                /**
                 * Derivada 'Tangente Sigmoidal'
                 */
                derivada = (2 * (Math.pow(Math.E, -potencial))) / Math.pow((1.0 + (Math.pow(Math.E, -potencial))), 2);
                break;
            case "TanHiperbolica":
                /**
                 * Derivada 'Tangente Hiperbolica'
                 */
                derivada = 1 - Math.pow(activacion("TanHiperbolica", potencial), 2);
                break;
            case "Gaussiana":
                /**
                 * Derivada 'Gaussiana'
                 */
                derivada = -exp(-potencial) * (potencial - 1); //gaussiana;
                break;
            case "Seno":
                /**
                 * Funcion de activacion (gaussiana): Campana de Gauss
                 */
                derivada = cos(potencial);
                break;
            case "Coseno":
                /**
                 * Funcion de activacion (gaussiana): Campana de Gauss
                 */
                derivada = -sin(potencial);
                break;
            default:
                break;
        }
        return derivada;
    }

}
