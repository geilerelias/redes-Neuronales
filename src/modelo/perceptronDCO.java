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
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author eldoc
 */
public class perceptronDCO extends red {

    //Multicapa Una Capa Oculta (MUCO)
    private int o;
    private int ñ;
    private String funcionActivacionCapa1;
    private String funcionActivacionCapa2;
    private String funcionActivacionSalida;
    private String algoritmo;
    private JFileChooser fileChooser;

    public static void main(String[] args) {
        double x[][] = {{1, 1}, {1, 0}, {0, 1}, {0, 0}};
        double yd[][] = {{1}, {0}, {0}, {0}};
        String nombre = "prueba";
        int m = 2;
        int n = 1;
        int pat = 4;

        int o = 6;
        int ñ = 3;

        //aprendizajes de la red
        //parámetros de entrenamiento
        double rata = 0.1;
        double errorMax = 0.0025;
        int itMax = 100;
        double rd;

        // regla delta
        String algoritmo = "deltaModificado";
        String funcionActivacionCapa1 = "Sigmoidal";
        String funcionActivacionCapa2 = "Gaussiana";
        String funcionActivacionSalida = "Sigmoidal";
        perceptronDCO p = new perceptronDCO(m, o, ñ, n, pat, x, yd, itMax, rata, errorMax, funcionActivacionCapa1, funcionActivacionCapa2, funcionActivacionSalida, algoritmo, nombre);
        p.inicializacionPesosUmbrales();
        p.entrenamiento();
    }
    
    public perceptronDCO() {
    }

    public perceptronDCO(int m, int o, int ñ, int n, int patrones, double[][] x, double[][] yd, int epoch, double lr, double goal, String funcionActivacionCapa1, String funcionActivacionCapa2, String funcionActivacionSalida, String algoritmo,String nombre) {
        super(m, n, patrones, x, yd, epoch, lr, goal,nombre);
        this.o = o;
        this.ñ = ñ;
        this.funcionActivacionCapa1 = funcionActivacionCapa1;
        this.funcionActivacionCapa2 = funcionActivacionCapa2;
        this.funcionActivacionSalida = funcionActivacionSalida;
        this.algoritmo = algoritmo;
    }

    public int getO() {
        return o;
    }

    public void setO(int o) {
        this.o = o;
    }

    public int getÑ() {
        return ñ;
    }

    public void setÑ(int ñ) {
        this.ñ = ñ;
    }

    public String getFuncionActivacionCapa1() {
        return funcionActivacionCapa1;
    }

    public void setFuncionActivacionCapa1(String funcionActivacion) {
        this.funcionActivacionCapa1 = funcionActivacion;
    }

    public String getFuncionActivacionCapa2() {
        return funcionActivacionCapa2;
    }

    public void setFuncionActivacionCapa2(String funcionActivacion) {
        this.funcionActivacionCapa2 = funcionActivacion;
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

    //primera capa oculta
    public double[][] w;
    public double[] u;
    public double[][] wAnt = new double[getM()][getO()];
    public double[] uAnt = new double[getO()];
    public double[] h;
    public double[] s;

    //primera capa oculta
    public double[][] wc;
    public double[] uc;
    public double[][] wcAnt = new double[getO()][getÑ()];
    public double[] ucAnt = new double[getÑ()];
    public double[] hc;
    public double[] sc;

    //capa salida
    public double[][] ws;
    public double[] us;
    public double[][] wsAnt = new double[getÑ()][getN()];
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
        wc = new double[getO()][getÑ()];
        uc = new double[getÑ()];
        ws = new double[getÑ()][getN()];
        us = new double[getN()];
        System.out.println("INICIALIZACION DE PESOS Y UMBRALES");
        System.out.println("***Primera Capa Oculta****");
        for (int i = 0; i < getO(); i++) {
            //u[i] = -1 + (1 - -1) * rand.nextDouble();
            u[i] = Math.random() * ThreadLocalRandom.current().nextInt(-1, 1 + 1);;
            System.out.println("Umbral [" + i + "]: " + u[i]);
        }
        for (int i = 0; i < getO(); i++) {
            for (int j = 0; j < getM(); j++) {
                //w[j][i] = -1 + (1 - -1) * rand.nextDouble();
                w[j][i] = Math.random() * ThreadLocalRandom.current().nextInt(-1, 1 + 1);;
                System.out.print("Peso[" + j + "][" + i + "]: " + w[j][i] + " ");
            }
            System.out.println("");
        }

        System.out.println("***Segunda Capa Oculta****");
        for (int i = 0; i < getÑ(); i++) {
            //uc[i] = -1 + (1 - -1) * rand.nextDouble();
            uc[i] = Math.random() * ThreadLocalRandom.current().nextInt(-1, 1 + 1);;
            System.out.println("Umbral [" + i + "]: " + uc[i]);
        }
        for (int i = 0; i < getÑ(); i++) {
            for (int j = 0; j < getO(); j++) {
                //wc[j][i] = -1 + (1 - -1) * rand.nextDouble();
               wc[j][i] = Math.random() * ThreadLocalRandom.current().nextInt(-1, 1 + 1);;
                System.out.print("Peso[" + j + "][" + i + "]: " + wc[j][i] + " ");
            }
            System.out.println("");
        }

        System.out.println("***Capa Salida***");
        for (int i = 0; i < getN(); i++) {
            //us[i] = -1 + (1 - -1) * rand.nextDouble();
            us[i] = Math.random() * ThreadLocalRandom.current().nextInt(-1, 1 + 1);;
            System.out.println("Umbral[" + i + "]: " + us[i]);
        }

        for (int i = 0; i < getN(); i++) {
            for (int j = 0; j < getÑ(); j++) {
                //ws[j][i] = -1 + (1 - -1) * rand.nextDouble();
                ws[j][i] = Math.random() * ThreadLocalRandom.current().nextInt(-1, 1 + 1);;
                System.out.print("Peso[" + j + "][" + i + "]: " + ws[j][i] + " ");
            }
            System.out.println("");
        }
    }

    public void entrenamiento() {
        s = new double[getO()];
        h = new double[getO()];
        sc = new double[getÑ()];
        hc = new double[getÑ()];
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
                System.out.println("");
                System.out.println("");
                System.out.println("Primera Capa Oculta");
                System.out.println("");
                for (int i = 0; i < getO(); i++) {
                    s[i] = 0;
                    for (int j = 0; j < getM(); j++) {
                        System.out.println("soma[" + i + "] = soma[" + i + "]:" + s[i] + " + x[" + p + "][" + j + "]:" + getX()[p][j] + " * w[" + j + "][" + i + "]:" + w[j][i]);
                        s[i] = s[i] +  w[j][i]*getX()[p][j];
                    }
                    System.out.println("soma : " + s[i]);
                    System.out.println("soma Atenuada= " + "soma[" + i + "]:" + s[i] + " - " + "u[" + i + "]" + u[i]);
                    s[i] = s[i] - u[i];
                    System.out.println("soma Atenuada: " + s[i]);
                    h[i] = activacion(getFuncionActivacionCapa1(), s[i]);
                    System.out.println("");
                    System.out.println("Salida de la primera capa neurona[ " + i + " ] : " + h[i]);
                    System.out.println("");

                }

                System.out.println("");
                System.out.println("");
                System.out.println("Segunda Capa Oculta");
                System.out.println("");

                for (int i = 0; i < getÑ(); i++) {
                    sc[i] = 0;
                    for (int j = 0; j < getO(); j++) {
                        System.out.println("soma[" + i + "] = soma[" + i + "]:" + sc[i] + " + x[" + p + "][" + j + "]:" + h[j] + " * w[" + j + "][" + i + "]:" + wc[j][i]);
                        sc[i] = sc[i] +  wc[j][i]*h[j];
                    }
                    System.out.println("soma : " + sc[i]);
                    System.out.println("soma Atenuada= " + "soma[" + i + "]:" + sc[i] + " - " + "u[" + i + "]" + uc[i]);
                    sc[i] = sc[i] - uc[i];
                    System.out.println("somaC Atenuada: " + sc[i]);
                    hc[i] = activacion(getFuncionActivacionCapa2(), (sc[i]));
                    System.out.println("");
                    System.out.println("Salida de la segunda capa neurona[ " + i + " ] : " + hc[i]);
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
                    for (int j = 0; j < getÑ(); j++) {
                        System.out.println("somaS[" + i + "] = somaS[" + i + "]:" + ss[i] + " + hC[" + j + "]:" + hc[j] + " * wS[" + j + "][" + i + "]:" + ws[j][i]);
                        ss[i] = ss[i] + hc[j] * ws[j][i];
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

                ep[p] = sumEl / getN();
                sumEl = 0;
                System.out.println("");
                System.out.println("");
                System.out.println("Error del patron[" + p + "]: " + ep[p]);
                System.out.println("\n\n");
                sumEp = sumEp + ep[p];

                actualizacionPesosUmbrales(getAlgoritmo(), p, e);
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
                l.add(2, wc);
                l.add(3, uc);
                l.add(4, ws);
                l.add(5, us);
                
            }
        }
      
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

        System.out.println("***Segunda Capa Oculta****");
        for (int i = 0; i < getÑ(); i++) {
            System.out.print("Umbral Anterior[" + i + "]: " + uc[i] + " ");
            uc[i] = uc[i] + getLr() * ep[p];
            System.out.println("Umbral Modificado[" + i + "]: " + uc[i]);
        }

        for (int i = 0; i < getÑ(); i++) {
            for (int j = 0; j < getO(); j++) {
                System.out.print("Peso Anterior[" + j + "][" + i + "]: " + wc[j][i] + " ");
                wc[j][i] = wc[j][i] + getLr() * ep[p] * h[j];
                System.out.println("Peso Modificado[" + j + "][" + i + "]: " + wc[j][i] + " ");
            }
        }

        System.out.println("***Capa de Salida****");
        for (int i = 0; i < getN(); i++) {
            System.out.print("Umbral Anterior[" + i + "]: " + us[i] + " ");
            us[i] = us[i] + getLr() * el[i];
            System.out.println("Umbral Modificado[" + i + "]: " + us[i]);
        }

        for (int i = 0; i < getN(); i++) {
            for (int j = 0; j < getÑ(); j++) {
                System.out.print("Peso Anterior[" + j + "][" + i + "]: " + ws[j][i] + " ");
                ws[j][i] = ws[j][i] + getLr() * el[i] * hc[j];
                System.out.println("Peso Modificado[" + j + "][" + i + "]: " + ws[j][i] + " ");
            }
        }
        System.out.println("");
        System.out.println("");
    }

    private void deltaModificado(int p, int e) {
        double[][] wAux = w;
        double[] uAux = u;
        double[][] wcAux = wc;
        double[] ucAux = uc;
        double[][] wsAux = ws;
        double[] usAux = us;

        if (e == 1) {
            wAnt = w;
            uAnt = u;
            wcAnt = wc;
            ucAnt = uc;
            wsAnt = ws;
            usAnt = us;

        }
        lrD = lrD / e;
        System.out.println("ACTUALIZACION DE PESOS Y UMBRALES DELTAMODIFICADO");
        System.out.println("***Primera Capa Oculta****");
        for (int i = 0; i < getO(); i++) {
            System.out.print("Umbral Anterior[" + i + "]: " + uAnt[i] + " ");
            u[i] = u[i] + getLr() * ep[p] + lrD * (uAnt[i] - u[i]);
            System.out.println("Umbral Modificado[" + i + "]: " + u[i]);
        }

        for (int i = 0; i < getO(); i++) {
            for (int j = 0; j < getM(); j++) {
                System.out.print("Peso Anterior[" + j + "][" + i + "]: " + wAnt[j][i] + " ");
                w[j][i] = w[j][i] + getLr() * ep[p] * getX()[p][j] + lrD * (wAnt[j][i] - w[j][i]);
                System.out.println("Peso Modificado[" + j + "][" + i + "]: " + w[j][i] + " ");
            }
        }

        System.out.println("***Segunda Capa Oculta****");
        for (int i = 0; i < getÑ(); i++) {
            System.out.print("Umbral Anterior[" + i + "]: " + ucAnt[i] + " ");
            uc[i] = uc[i] + getLr() * ep[p] + lrD * (ucAnt[i] - uc[i]);
            System.out.println("Umbral Modificado[" + i + "]: " + uc[i]);
        }

        for (int i = 0; i < getÑ(); i++) {
            for (int j = 0; j < getO(); j++) {
                System.out.print("Peso Anterior[" + j + "][" + i + "]: " + wcAnt[j][i] + " ");
                wc[j][i] = wc[j][i] + getLr() * ep[p] * h[j] + lrD * (wcAnt[j][i] - wc[j][i]);
                System.out.println("Peso Modificado[" + j + "][" + i + "]: " + wc[j][i] + " ");
            }
        }

        System.out.println("***Capa de Salida****");
        for (int i = 0; i < getN(); i++) {
            System.out.print("Umbral Anterior[" + i + "]: " + usAnt[i] + " ");
            us[i] = us[i] + getLr() * el[i] + lrD * (usAnt[i] - us[i]);
            System.out.println("Umbral Modificado[" + i + "]: " + us[i]);
        }

        for (int i = 0; i < getN(); i++) {
            for (int j = 0; j < getÑ(); j++) {
                System.out.print("Peso Anterior[" + j + "][" + i + "]: " + wsAnt[j][i] + " ");
                ws[j][i] = ws[j][i] + getLr() * el[i] * hc[j] + lrD * (wsAnt[j][i] - ws[j][i]);
                System.out.println("Peso Modificado[" + j + "][" + i + "]: " + ws[j][i] + " ");
            }
        }

        wAnt = wAux;
        uAnt = uAux;
        wcAnt = wcAux;
        ucAnt = ucAux;
        wsAnt = wsAux;
        usAnt = usAux;

        System.out.println("");
        System.out.println("");
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
                    while ((linea = lee.readLine()) != null) {

                        String[] values = linea.split(" ");
                        //cargo el vector deumbrales
                        switch (cont) {
                            case 0:
                                for (int i = 0; i < values.length; i++) {
                                    u[i] = Double.parseDouble(values[i]);
                                }
                                break;
                            case 1:
                                for (int i = 0; i < values.length; i++) {
                                    uc[i] = Double.parseDouble(values[i]);
                                }
                                break;

                            case 2:
                                for (int i = 0; i < values.length; i++) {
                                    us[i] = Double.parseDouble(values[i]);
                                }
                                break;

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
        //actualizar la Tabla con el archivo que acabamode leer
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
                            cad = "";
                            for (int i = 0; i < getÑ(); i++) {
                                cad = cad + (u[i] + " ");
                            }
                            pw.println(cad);
                            cad = "";
                            for (int i = 0; i < getÑ(); i++) {
                                cad = cad + (uc[i] + " ");
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
                            for (int i = 0; i < getÑ(); i++) {
                                cad = "";
                                for (int j = 0; j < getO(); j++) {
                                    cad = cad + (wc[j][i] + " ");
                                }
                                pw.println(cad);
                            }
                            for (int i = 0; i < getN(); i++) {
                                cad = "";
                                for (int j = 0; j < getÑ(); j++) {
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

        for (int i = 0; i < getÑ(); i++) {
            sc[i] = 0;
            for (int j = 0; j < getO(); j++) {
                System.out.println("soma[" + i + "] = soma[" + i + "]:" + sc[i] + " + x[" + j + "]:" + h[j] + " * w[" + j + "][" + i + "]:" + wc[j][i]);
                sc[i] = sc[i] + h[j] * wc[j][i];
            }
            System.out.println("soma : " + sc[i]);
            System.out.println("soma Atenuada= " + "soma[" + i + "]:" + sc[i] + " - " + "u[" + i + "]" + uc[i]);
            sc[i] = sc[i] - uc[i];
            System.out.println("soma Atenuada: " + sc[i]);
            hc[i] = activacion(getFuncionActivacionCapa1(), (sc[i]));
            System.out.println("");
            System.out.println("Salida de la segunda capa neurona[ " + i + " ] : " + hc[i]);
            System.out.println("");

        }

        System.out.println("");
        System.out.println("");
        panel.removeAll();
        for (int i = 0; i < getN(); i++) {
            ss[i] = 0;
            for (int j = 0; j < getÑ(); j++) {
                System.out.println("somaS[" + i + "] = somaS[" + i + "]:" + ss[i] + " + h[" + j + "]:" + hc[j] + " * w[" + j + "][" + i + "]:" + ws[j][i]);
                ss[i] = ss[i] + hc[j] * ws[j][i];
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
        JPanel panel2 = new JPanel(new GridLayout(2, getM()+getN(), 5, 1));//filas, columnas, espacio entre filas, espacio entre columnas
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
}
