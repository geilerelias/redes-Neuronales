/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JOptionPane;
import vista.FrmKohonen;

/**
 *
 * @author eldoc
 */
public class kohonen implements Runnable {

    private int m;
    private int patrones;
    private int numeroNeuronas;
    private String tipoCompetencia;
    private double[][] x;
    private int epoch;
    private double coefecienteVecindad;
    private double lr;
    private double tolerancia;
    private String nombre;
    private double[][] w;

    public kohonen() {
    }

    public kohonen(int m, int patrones, int numeroNeuronas, String tipoCompetencia, double[][] x, int epoch, int coefecienteVecindad, double goal, String nombre) {
        this.m = m;
        this.patrones = patrones;
        this.numeroNeuronas = numeroNeuronas;
        this.tipoCompetencia = tipoCompetencia;
        this.x = x;
        this.epoch = epoch;
        this.coefecienteVecindad = coefecienteVecindad;
        this.tolerancia = goal;
        this.nombre = nombre;
    }

    public void entrenar() {
        FrmKohonen.txtAreaVista.setText("");
        //System.out.println("");
        //System.out.println("");
        ArrayList lista = new ArrayList<>();
        double[] d = new double[getNumeroNeuronas()];
        double sumDg;
        double pdg;
        for (int e = 1; e <= getEpoch(); e++) {//ciclo de iteraciones
            //System.out.println("INTERACION # " + e);
            sumDg = 0;
            int cont=0;
            for (int p = 0; p < getPatrones(); p++) {//ciclo de patrones

                for (int i = 0; i < getNumeroNeuronas(); i++) {
                    d[i] = 0;

                    for (int j = 0; j < getM(); j++) {
                        double sum;
                        sum = getX()[p][j] - w[j][i];
                        sum = Math.pow(sum, 2);
                        d[i] = d[i] + sum;
                    }
                    d[i] = Math.sqrt(d[i]);
                }

                double menorDistacia;

                int pos;
                menorDistacia = d[0];
  
                pos = 0;
                for (int f = 1; f < d.length; f++) {
                    if (d[f] < menorDistacia) {
                        menorDistacia = d[f];
                        pos = f;
                    }
                }
                 cont ++;
                sumDg = sumDg + menorDistacia;
                switch (getTipoCompetencia()) {
                    case "Dura":
                        actualizarPesosSipnaticosEntrenamientoDuro(e, menorDistacia, pos);
                        break;
                    case "Blanda":
                        ArrayList array = new ArrayList();
                        for (int i = 0; i < getNumeroNeuronas(); i++) {
                            if (d[i] <= menorDistacia + getCoefecienteVecindad()) {
                                array.add(i);
                            }
                        }
                        actualizarPesosSipnaticosEntrenamientoBlanda(e, menorDistacia, array);
                        break;
                }

            }
            pdg = sumDg / cont;
            lista.add(pdg);
            FrmKohonen.cargar(lista, this.getTolerancia());
            //System.out.println("Error de la iterecion #" + e + ": " + erms + "\n");
            FrmKohonen.txtAreaVista.append(String.format("PDG:%f\n ", pdg));
            if (pdg <= getTolerancia()) {
                break;
            }
        }
    }

    public void actualizarPesosSipnaticosEntrenamientoDuro(int e, double dv, int pos) {
        setLr(1 / e);
        for (int j = 0; j < getM(); j++) {
            getW()[j][pos] = getW()[j][pos] + getLr() * dv;
        }
    }

    public void actualizarPesosSipnaticosEntrenamientoBlanda(int e, double dv, ArrayList pos) {
        setLr(1 / e);
        for (int i = 0; i < pos.size(); i++) {
            for (int j = 0; j < getM(); j++) {
                getW()[j][i] = getW()[j][(int) pos.get(i)] + getLr() * dv;
            }
        }
    }

    public void inicializarPesosSipnaticos(int num, int m) {
        setM(m);
        setNumeroNeuronas(num);
        setW(new double[getM()][getNumeroNeuronas()]);
        for (int i = 0; i < getNumeroNeuronas(); i++) {
            for (int j = 0; j < getM(); j++) {
                getW()[j][i] = Math.random() * ThreadLocalRandom.current().nextInt(-1, 1 + 1);
            }
        }
    }

    @Override
    public void run() {
        entrenar();
        FrmKohonen.lblEstado.setText("Finalizado");
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
     * @return the numeroNeuronas
     */
    public int getNumeroNeuronas() {
        return numeroNeuronas;
    }

    /**
     * @param numeroNeuronas the numeroNeuronas to set
     */
    public void setNumeroNeuronas(int numeroNeuronas) {
        this.numeroNeuronas = numeroNeuronas;
    }

    /**
     * @return the tipoCompetencia
     */
    public String getTipoCompetencia() {
        return tipoCompetencia;
    }

    /**
     * @param tipoCompetencia the tipoCompetencia to set
     */
    public void setTipoCompetencia(String tipoCompetencia) {
        this.tipoCompetencia = tipoCompetencia;
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
     * @return the epoch
     */
    public int getEpoch() {
        return epoch;
    }

    /**
     * @param epoch the epoch to set
     */
    public void setEpoch(int epoch) {
        this.epoch = epoch;
    }

    /**
     * @return the coefecienteVecindad
     */
    public double getCoefecienteVecindad() {
        return coefecienteVecindad;
    }

    /**
     * @param coefecienteVecindad the coefecienteVecindad to set
     */
    public void setCoefecienteVecindad(double coefecienteVecindad) {
        this.coefecienteVecindad = coefecienteVecindad;
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
     * @return the tolerancia
     */
    public double getTolerancia() {
        return tolerancia;
    }

    /**
     * @param tolerancia the tolerancia to set
     */
    public void setTolerancia(double tolerancia) {
        this.tolerancia = tolerancia;
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

}
