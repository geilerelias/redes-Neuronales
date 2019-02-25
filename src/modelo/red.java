/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.exp;
import static java.lang.Math.sin;

/**
 *
 * @author HOME
 */
public class red {

    private int m;
    private int n;
    private int patrones;
    //matriz de entrada yd matriz de salida
    private double[][] x;
    private double[][] yd;
    //parametros de entrenamiento
    private int epoch;
    private double lr;
    private double goal;
    private String nombre;

    public red() {
    }

    public red(int m, int n, int patrones, double[][] x, double[][] yd, int epoch, double lr, double goal, String nombre) {
        this.m = m;
        this.n = n;
        this.patrones = patrones;
        this.x = x;
        this.yd = yd;
        this.epoch = epoch;
        this.lr = lr;
        this.goal = goal;
        this.nombre = nombre;
    }

    public double activacion(String funcion, double num) {
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
     * @return the goal
     */
    public double getGoal() {
        return goal;
    }

    /**
     * @param goal the goal to set
     */
    public void setGoal(double goal) {
        this.goal = goal;
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

}
