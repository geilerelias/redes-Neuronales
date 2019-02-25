/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author eldoc
 */
public class getSetPesosUmbrales extends red {

    private String nombre;
    private int[] neuronasPorCapas;
    private int patrones;
    private String[] funcion;
    private String algoritmo;
    private double lr;
    private ArrayList pesos;
    private ArrayList umbral;

    private int cantCapa;
    private int m;
    private int n;
    private int o;
    private int ñ;
    private int q;
    private double[] u;
    private double[] uc;
    private double[] ucc;
    private double[] us;
    private double[][] w;
    private double[][] wc;
    private double[][] wcc;
    private double[][] ws;
    private String funcionE;
    private String funcionC;
    private String funcionCC;
    private String funcionS;
    private double[][] x;
    private double[][] y;

    public getSetPesosUmbrales() {
    }

    public getSetPesosUmbrales(String nombre) {
        this.nombre = nombre;
    }

    public getSetPesosUmbrales(String nombre, int[] neuronasPorCapas, int p, String[] funcion, String algoritmo, double lr, ArrayList pesos, ArrayList umbral, double[][] x, double[][] y) {
        this.nombre = nombre;
        this.patrones = p;
        this.neuronasPorCapas = neuronasPorCapas;
        this.funcion = funcion;
        this.algoritmo = algoritmo;
        this.lr = lr;
        this.pesos = pesos;
        this.umbral = umbral;
        this.x = x;
        this.y = y;
    }

    public void leerPesosUmbrales() {
        //Creamos un String que va a contener todo el texto del archivo
        File directorio = new File(System.getProperty("user.dir") + "\\RedesNeuronales\\" + getNombre());
        if (directorio.exists()) {

            String linea = "";
            ArrayList conf = new ArrayList();
            ArrayList umbrales = new ArrayList();
            ArrayList peso = new ArrayList();
             ArrayList entradas = new ArrayList();
              ArrayList salidas = new ArrayList();
            try {
                //Creamos un archivo FileReader que obtiene lo que tenga el archivo
                FileReader lector = new FileReader(directorio + "\\" + getNombre() + "Conf.txt");

                //El contenido de lector se guarda en un BufferedReader
                BufferedReader contenido = new BufferedReader(lector);

                while ((linea = contenido.readLine()) != null) {
                    conf.add(linea);
                }
            } //Si se causa un error al leer cae aqui
            catch (Exception e) {
                System.out.println("Error al leer");
            }
            try {
                //Creamos un archivo FileReader que obtiene lo que tenga el archivo
                FileReader lector = new FileReader(directorio + "\\" + getNombre() + "Umbrales.txt");

                //El contenido de lector se guarda en un BufferedReader
                BufferedReader contenido = new BufferedReader(lector);

                //Con el siguiente ciclo extraemos todo el contenido del objeto "contenido" y lo mostramos
                while ((linea = contenido.readLine()) != null) {
                    umbrales.add(linea);
                }
            } catch (IOException ex) {

            } //Si se causa un error al leer cae aqui

            try {
                //Creamos un archivo FileReader que obtiene lo que tenga el archivo
                FileReader lector = new FileReader(directorio + "\\" + getNombre() + "Pesos.txt");

                //El contenido de lector se guarda en un BufferedReader
                BufferedReader contenido = new BufferedReader(lector);

                //Con el siguiente ciclo extraemos todo el contenido del objeto "contenido" y lo mostramos
                while ((linea = contenido.readLine()) != null) {
                    peso.add(linea);
                }
            } catch (IOException ex) {
                //Si se causa un error al leer cae aqui
            }
            
            try {
                //Creamos un archivo FileReader que obtiene lo que tenga el archivo
                FileReader lector = new FileReader(directorio + "\\" + getNombre() + "Entradas.txt");

                //El contenido de lector se guarda en un BufferedReader
                BufferedReader contenido = new BufferedReader(lector);

                //Con el siguiente ciclo extraemos todo el contenido del objeto "contenido" y lo mostramos
                while ((linea = contenido.readLine()) != null) {
                    entradas.add(linea);
                }
            } catch (IOException ex) {
                //Si se causa un error al leer cae aqui
            }
            
            try {
                //Creamos un archivo FileReader que obtiene lo que tenga el archivo
                FileReader lector = new FileReader(directorio + "\\" + getNombre() + "Salidas.txt");

                //El contenido de lector se guarda en un BufferedReader
                BufferedReader contenido = new BufferedReader(lector);

                //Con el siguiente ciclo extraemos todo el contenido del objeto "contenido" y lo mostramos
                while ((linea = contenido.readLine()) != null) {
                    salidas.add(linea);
                }
            } catch (IOException ex) {
                //Si se causa un error al leer cae aqui
            }

            System.out.println("");
            System.out.println("Configuracion de la red ");
            for (int i = 0; i < conf.size(); i++) {
                System.out.println(conf.get(i));
            }

            setCantCapa(Integer.parseInt((String) conf.get(0)) - 2);
            switch (getCantCapa()) {
                case 1: {
                    setM(Integer.parseInt((String) conf.get(1)));
                    setO(Integer.parseInt((String) conf.get(2)));
                    setN(Integer.parseInt((String) conf.get(3)));
                    setFuncionE((String) conf.get(4));
                    setFuncionS((String) conf.get(5));
                    setAlgoritmo((String) conf.get(6));
                    setLr(Double.parseDouble((String) conf.get(7)));
                    setU(new double[getO()]);
                    setUs(new double[getN()]);

                    setW(new double[getM()][getO()]);
                    setWs(new double[getO()][getN()]);

                    System.out.println("");
                    System.out.println("Umbrales de la red ");

                    String[] uAux = ((String) umbrales.get(0)).split(" ");
                    for (int j = 0; j < uAux.length; j++) {
                        getU()[j] = Double.parseDouble(uAux[j]);
                    }

                    String[] usAux = ((String) umbrales.get(1)).split(" ");
                    for (int j = 0; j < usAux.length; j++) {
                        getUs()[j] = Double.parseDouble(usAux[j]);
                    }

                    System.out.println("");
                    System.out.println("Pesos de la red ");
                    String[] pesosAux;
                    for (int i = 0; i < getM(); i++) {
                        pesosAux = ((String) peso.get(i)).split(" ");
                        for (int j = 0; j < getO(); j++) {
                            getW()[i][j] = Double.parseDouble(pesosAux[j]);
                            System.out.print(getW()[i][j] + " ");
                        }
                        System.out.println("");
                    }

                    for (int i = 0; i < getO(); i++) {
                        pesosAux = ((String) peso.get(i + getM())).split(" ");
                        for (int j = 0; j < getN(); j++) {
                            getWs()[i][j] = Double.parseDouble(pesosAux[j]);
                            System.out.print(getWs()[i][j] + " ");
                        }
                        System.out.println("");
                    }
                    System.out.println("");
                }
                break;
                case 2: {
                    setM(Integer.parseInt((String) conf.get(1)));
                    setO(Integer.parseInt((String) conf.get(2)));
                    setÑ(Integer.parseInt((String) conf.get(3)));
                    setN(Integer.parseInt((String) conf.get(4)));
                    setFuncionE((String) conf.get(5));
                    setFuncionC((String) conf.get(6));
                    setFuncionS((String) conf.get(7));
                    setAlgoritmo((String) conf.get(8));
                    setLr(Double.parseDouble((String) conf.get(9)));
                    setU(new double[getO()]);
                    setUc(new double[getÑ()]);
                    setUs(new double[getN()]);

                    setW(new double[getM()][getO()]);
                    setWc(new double[getO()][getÑ()]);
                    setWs(new double[getÑ()][getN()]);

                    System.out.println("");
                    System.out.println("Umbrales de la red ");

                    String[] uAux = ((String) umbrales.get(0)).split(" ");
                    for (int j = 0; j < uAux.length; j++) {
                        getU()[j] = Double.parseDouble(uAux[j]);
                    }

                    String[] ucAux = ((String) umbrales.get(1)).split(" ");
                    for (int j = 0; j < ucAux.length; j++) {
                        getUc()[j] = Double.parseDouble(ucAux[j]);
                    }

                    String[] usAux = ((String) umbrales.get(2)).split(" ");
                    for (int j = 0; j < usAux.length; j++) {
                        getUs()[j] = Double.parseDouble(usAux[j]);
                    }

                    System.out.println("");
                    System.out.println("Pesos de la red ");
                    String[] pesosAux;
                    for (int i = 0; i < getM(); i++) {
                        pesosAux = ((String) peso.get(i)).split(" ");
                        for (int j = 0; j < getO(); j++) {
                            getW()[i][j] = Double.parseDouble(pesosAux[j]);
                            System.out.print(getW()[i][j] + " ");
                        }
                        System.out.println("");
                    }

                    for (int i = 0; i < getO(); i++) {
                        pesosAux = ((String) peso.get(i + getM())).split(" ");
                        for (int j = 0; j < getÑ(); j++) {
                            getWc()[i][j] = Double.parseDouble(pesosAux[j]);
                            System.out.print(getWc()[i][j] + " ");
                        }
                        System.out.println("");
                    }

                    for (int i = 0; i < getÑ(); i++) {
                        pesosAux = ((String) peso.get(i + getM() + getÑ())).split(" ");
                        for (int j = 0; j < getN(); j++) {
                            getWs()[i][j] = Double.parseDouble(pesosAux[j]);
                            System.out.print(getWs()[i][j] + " ");
                        }
                        System.out.println("");
                    }
                    System.out.println("");
                }

                break;
                case 3: {
                    setM(Integer.parseInt((String) conf.get(1)));
                    setO(Integer.parseInt((String) conf.get(2)));
                    setÑ(Integer.parseInt((String) conf.get(3)));
                    setQ(Integer.parseInt((String) conf.get(4)));
                    setN(Integer.parseInt((String) conf.get(5)));
                    setFuncionE((String) conf.get(6));
                    setFuncionC((String) conf.get(7));
                    setFuncionCC((String) conf.get(8));
                    setFuncionS((String) conf.get(9));
                    setAlgoritmo((String) conf.get(10));
                    setLr(Double.parseDouble((String) conf.get(11)));
                    setU(new double[getO()]);
                    setUc(new double[getÑ()]);
                    setUcc(new double[getQ()]);
                    setUs(new double[getN()]);

                    setW(new double[getM()][getO()]);
                    setWc(new double[getO()][getÑ()]);
                    setWcc(new double[getÑ()][getQ()]);
                    setWs(new double[getQ()][getN()]);

                    System.out.println("");
                    System.out.println("Umbrales de la red ");

                    String[] uAux = ((String) umbrales.get(0)).split(" ");
                    for (int j = 0; j < uAux.length; j++) {
                        getU()[j] = Double.parseDouble(uAux[j]);
                    }

                    String[] ucAux = ((String) umbrales.get(1)).split(" ");
                    for (int j = 0; j < ucAux.length; j++) {
                        getUc()[j] = Double.parseDouble(ucAux[j]);
                    }

                    String[] uccAux = ((String) umbrales.get(2)).split(" ");
                    for (int j = 0; j < uccAux.length; j++) {
                        getUcc()[j] = Double.parseDouble(uccAux[j]);
                    }

                    String[] usAux = ((String) umbrales.get(3)).split(" ");
                    for (int j = 0; j < usAux.length; j++) {
                        getUs()[j] = Double.parseDouble(usAux[j]);
                    }

                    System.out.println("");
                    System.out.println("Pesos de la red ");
                    String[] pesosAux;
                    for (int i = 0; i < getM(); i++) {
                        pesosAux = ((String) peso.get(i)).split(" ");
                        for (int j = 0; j < getO(); j++) {
                            getW()[i][j] = Double.parseDouble(pesosAux[j]);
                            System.out.print(getW()[i][j] + " ");
                        }
                        System.out.println("");
                    }

                    for (int i = 0; i < getO(); i++) {
                        pesosAux = ((String) peso.get(i + getM())).split(" ");
                        for (int j = 0; j < getÑ(); j++) {
                            getWc()[i][j] = Double.parseDouble(pesosAux[j]);
                            System.out.print(getWc()[i][j] + " ");
                        }
                        System.out.println("");
                    }

                    for (int i = 0; i < getÑ(); i++) {
                        pesosAux = ((String) peso.get(i + getM() + getO())).split(" ");
                        for (int j = 0; j < getQ(); j++) {
                            getWcc()[i][j] = Double.parseDouble(pesosAux[j]);
                            System.out.print(getWcc()[i][j] + " ");
                        }
                        System.out.println("");
                    }

                    for (int i = 0; i < getQ(); i++) {
                        pesosAux = ((String) peso.get(i + getM() + getÑ())).split(" ");
                        for (int j = 0; j < getN(); j++) {
                            getWs()[i][j] = Double.parseDouble(pesosAux[j]);
                            System.out.print(getWs()[i][j] + " ");
                        }
                        System.out.println("");
                    }
                    System.out.println("");
                }
                break;
            }
            String[] get = ((String)entradas.get(0)).split(" ");
            double xAux[][] = new double[entradas.size()][get.length];
            for (int i = 0; i < entradas.size(); i++) {
                String []cad =( (String)entradas.get(i)).split(" ");
                for (int j = 0; j < cad.length; j++) {
                    xAux[i][j]=Double.parseDouble(cad[j]);
                    System.out.print(xAux[i][j]+" ");
                }
                System.out.println("");
            }
            setX(xAux);
            setPatrones(entradas.size());
            String[] getS = ((String)salidas.get(0)).split(" ");
            double yAux[][] = new double[salidas.size()][getS.length];
            for (int i = 0; i < salidas.size(); i++) {
                String []cad =( (String)salidas.get(i)).split(" ");
                for (int j = 0; j < cad.length; j++) {
                    yAux[i][j]=Double.parseDouble(cad[j]);
                    System.out.print(yAux[i][j]+" ");
                }
                System.out.println("");
            }
            setY(yAux);
        }

    }

     public void guardarPesosUmbrales() {
        File root = null;
        try {
            root = new File(System.getProperty("user.dir") + "\\RedesNeuronales");

            if (!root.exists()) {
                root.mkdirs();
            }

        } catch (Exception e) {
        }
        File directorio = null;
        try {
            directorio = new File(root + "\\" + getNombre());

            if (!directorio.exists()) {
                directorio.mkdirs();
            }

        } catch (Exception e) {
        }
        //String nombre, int m, int n, int p, int[] numeroNeuronasCapas, String[] funcion, String algoritmo, double lr, ArrayList pesos, ArrayList umbral
        //Un texto cualquiera guardado en una variable

        try {
            //Crear un objeto File se encarga de crear o abrir acceso a un archivo que se especifica en su constructor
            File archivo = new File(directorio + "\\" + getNombre() + "Conf.txt");

            //Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
            FileWriter fw = new FileWriter(archivo, false);
            PrintWriter pw = new PrintWriter(fw);
            //Escribimos en el archivo con el metodo write 
            pw.println(getNeuronasPorCapas().length);
            for (int neuronas : getNeuronasPorCapas()) {
                pw.println(neuronas);
            }

            for (String funcion1 : getFuncion()) {
                pw.println(funcion1);
            }

            pw.println(getAlgoritmo());
            pw.println(String.valueOf(getLr()));

            //Cerramos la conexion
            fw.close();
        } //Si existe un problema al escribir cae aqui //Si existe un problema al escribir cae aqui
        catch (Exception e) {
            System.out.println("Error al escribir");
        }

        try {
            //Crear un objeto File se encarga de crear o abrir acceso a un archivo que se especifica en su constructor
            File archivo = new File(directorio + "\\" + getNombre() + "Umbrales.txt");

            //Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
            FileWriter fw = new FileWriter(archivo, false);
            //Escribimos en el archivo con el metodo printwrite
            PrintWriter pw = new PrintWriter(fw);

            for (int i = 0; i < getUmbral().size(); i++) {
                double[] get = (double[]) getUmbral().get(i);
                String u = "";
                for (double get1 : get) {
                    u = u + get1 + " ";
                }
                pw.println(u);
            }
            //Cerramos la conexion
            fw.close();

        } //Si existe un problema al escribir cae aqui //Si existe un problema al escribir cae aqui
        catch (Exception e) {
            System.out.println("Error al escribir");
        }

        try {
            //Crear un objeto File se encarga de crear o abrir acceso a un archivo que se especifica en su constructor
            File archivo = new File(directorio + "\\" + getNombre() + "Pesos.txt");

            //Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
            FileWriter fw = new FileWriter(archivo, false);
            PrintWriter pw = new PrintWriter(fw);
            //Escribimos en el archivo con el metodo write 

            for (int i = 0; i < getPesos().size(); i++) {
                double[][] get = (double[][]) getPesos().get(i);
                for (double[] get1 : get) {
                    String w = "";
                    for (double item : get1) {
                        w = w + item + " ";
                    }
                    pw.println(w);

                }
            }

            //Cerramos la conexion
            fw.close();
        } //Si existe un problema al escribir cae aqui
        catch (Exception e) {
            System.out.println("Error al escribir");
        }

        try {
            //Crear un objeto File se encarga de crear o abrir acceso a un archivo que se especifica en su constructor
            File archivo = new File(directorio + "\\" + getNombre() + "Entradas.txt");

            //Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
            FileWriter fw = new FileWriter(archivo, false);
            PrintWriter pw = new PrintWriter(fw);
            //Escribimos en el archivo con el metodo write
            for (double[] x1 : getX()) {
                String cad = "";
                for (int j = 0; j < x1.length; j++) {
                    cad =cad+ String.valueOf(x1[j]) + " ";
                }
                pw.println(cad);
            }
            //Cerramos la conexion
            fw.close();
        } //Si existe un problema al escribir cae aqui //Si existe un problema al escribir cae aqui
        catch (Exception e) {
            System.out.println("Error al escribir");
        }

        try {
            //Crear un objeto File se encarga de crear o abrir acceso a un archivo que se especifica en su constructor
            File archivo = new File(directorio + "\\" + getNombre() + "Salidas.txt");

            //Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
            FileWriter fw = new FileWriter(archivo, false);
            PrintWriter pw = new PrintWriter(fw);
            //Escribimos en el archivo con el metodo write 
            for (double[] y1 : getY()) {
                String cad = "";
                for (int j = 0; j < y1.length; j++) {
                    cad =cad+ String.valueOf(y1[j]) + " ";
                }
                pw.println(cad);
            }
            //Cerramos la conexion
            fw.close();
        } //Si existe un problema al escribir cae aqui //Si existe un problema al escribir cae aqui
        catch (Exception e) {
            System.out.println("Error al escribir");
        }

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
     * @return the neuronasPorCapas
     */
    public int[] getNeuronasPorCapas() {
        return neuronasPorCapas;
    }

    /**
     * @param neuronasPorCapas the neuronasPorCapas to set
     */
    public void setNeuronasPorCapas(int[] neuronasPorCapas) {
        this.neuronasPorCapas = neuronasPorCapas;
    }

    /**
     * @return the patrones
     */
    public int getPatrones() {
        return patrones;
    }

    /**
     * @param patrones the patrones to set
     */
    public void setPatrones(int patrones) {
        this.patrones = patrones;
    }

    /**
     * @return the funcion
     */
    public String[] getFuncion() {
        return funcion;
    }

    /**
     * @param funcion the funcion to set
     */
    public void setFuncion(String[] funcion) {
        this.funcion = funcion;
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
     * @return the lr
     */
    public double getLr() {
        return lr;
    }

    /**
     * @param lr the lr to set
     */
    public void setLr(double lr) {
        this.lr = lr;
    }

    /**
     * @return the pesos
     */
    public ArrayList getPesos() {
        return pesos;
    }

    /**
     * @param pesos the pesos to set
     */
    public void setPesos(ArrayList pesos) {
        this.pesos = pesos;
    }

    /**
     * @return the umbral
     */
    public ArrayList getUmbral() {
        return umbral;
    }

    /**
     * @param umbral the umbral to set
     */
    public void setUmbral(ArrayList umbral) {
        this.umbral = umbral;
    }

    /**
     * @return the cantCapa
     */
    public int getCantCapa() {
        return cantCapa;
    }

    /**
     * @param cantCapa the cantCapa to set
     */
    public void setCantCapa(int cantCapa) {
        this.cantCapa = cantCapa;
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
     * @return the q
     */
    public int getQ() {
        return q;
    }

    /**
     * @param q the q to set
     */
    public void setQ(int q) {
        this.q = q;
    }

    /**
     * @return the u
     */
    public double[] getU() {
        return u;
    }

    /**
     * @param u the u to set
     */
    public void setU(double[] u) {
        this.u = u;
    }

    /**
     * @return the uc
     */
    public double[] getUc() {
        return uc;
    }

    /**
     * @param uc the uc to set
     */
    public void setUc(double[] uc) {
        this.uc = uc;
    }

    /**
     * @return the ucc
     */
    public double[] getUcc() {
        return ucc;
    }

    /**
     * @param ucc the ucc to set
     */
    public void setUcc(double[] ucc) {
        this.ucc = ucc;
    }

    /**
     * @return the us
     */
    public double[] getUs() {
        return us;
    }

    /**
     * @param us the us to set
     */
    public void setUs(double[] us) {
        this.us = us;
    }

    /**
     * @return the w
     */
    public double[][] getW() {
        return w;
    }

    /**
     * @param w the w to set
     */
    public void setW(double[][] w) {
        this.w = w;
    }

    /**
     * @return the wc
     */
    public double[][] getWc() {
        return wc;
    }

    /**
     * @param wc the wc to set
     */
    public void setWc(double[][] wc) {
        this.wc = wc;
    }

    /**
     * @return the wcc
     */
    public double[][] getWcc() {
        return wcc;
    }

    /**
     * @param wcc the wcc to set
     */
    public void setWcc(double[][] wcc) {
        this.wcc = wcc;
    }

    /**
     * @return the ws
     */
    public double[][] getWs() {
        return ws;
    }

    /**
     * @param ws the ws to set
     */
    public void setWs(double[][] ws) {
        this.ws = ws;
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
     * @return the funcionCC
     */
    public String getFuncionCC() {
        return funcionCC;
    }

    /**
     * @param funcionCC the funcionCC to set
     */
    public void setFuncionCC(String funcionCC) {
        this.funcionCC = funcionCC;
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
     * @return the y
     */
    public double[][] getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(double[][] y) {
        this.y = y;
    }

}
