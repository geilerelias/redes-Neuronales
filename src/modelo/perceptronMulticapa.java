/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author eldoc
 */
public class perceptronMulticapa extends red {
    
    public perceptronMulticapa() {
    }

    public perceptronMulticapa(int m, int n, int patrones, double[][] x, double[][] yd, int epoch, double lr, double goal,String nombre) {
        super(m, n, patrones, x, yd, epoch, lr, goal,nombre);
    }
    
}
