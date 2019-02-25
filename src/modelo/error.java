/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author eldoc
 */
public class error {

    int pos;//pocision en la que se almacena originalmente
    double error;//elemento o error lineal
    boolean es;//estado [activo =1; inanctivo=0]
    int posVO;//pocision en error vector ordenado

    public error() {
        this.es = true;
    }
    

}
