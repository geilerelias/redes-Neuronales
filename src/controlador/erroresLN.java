package controlador;

import java.util.Random;

/**
 *
 * @author eldoc
 */
public class erroresLN {

    static Random r = new Random();

    public static void main(String[] args) {
        double[] hc;
        double[] el;
        double[][] ws;
        double[] us;
        error[] elOrd;
        int ñ = 2;
        int n = 4;
        double lr = 0.001;
        hc = new double[ñ];
        for (int i = 0; i < ñ; i++) {
            hc[i] = Math.random() * n + 1;
        }
        ws = new double[ñ][n];
        us = new double[n];
        for (int i = 0; i < n; i++) {
            us[i] = Math.random() * n + 1;
            for (int j = 0; j < ñ; j++) {
                ws[j][i] = Math.random() * n + 1;
            }
        }
        
        System.out.println("UMBRALES");
        for (int i = 0; i < n; i++) {
            System.out.print("U[" + i + "]" + us[i] + " ");
        }
        System.out.println("");
        System.out.println("PESOS SINAPTICOS");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < ñ; j++) {
                System.out.print("w[" + j + "][" + i + "]" + ws[j][i] + "  ");
            }
            System.out.println("");
        }
        
        System.out.println("");
        System.out.println("");
        el = new double[n];
        elOrd = new error[n];
        error ob;
        System.out.println("vector Inicial");
        for (int i = 0; i < n; i++) {
            double num = Math.random() * n + 1;

            ob = new error();
            ob.el = num;
            ob.pos = i;
            System.out.println(ob.el + " pos -> " + ob.pos);

            el[i] = num;//este el valor real de los errores lineal de la red
            elOrd[i] = ob;//tambien lo guardo en otro vector que ordenaremos y utilizaremos para el calculo de los errores lineales

        }

        int imin;
        error aux;
        System.out.println("");
        System.out.println("");

        for (int i = 0; i < n; i++) {
            imin = i;
            for (int j = i + 1; j < n; j++) {
                if (elOrd[j].el < elOrd[imin].el) {
                    imin = j;
                }
            }
            aux = elOrd[i];
            elOrd[i] = elOrd[imin];
            elOrd[imin] = aux;
        }
        
        System.out.println("vector ordenado");
        for (int i = 0; i < n; i++) {
            String estado = (elOrd[i].es == true) ? "Activo" : "Inactivo";
            System.out.println(elOrd[i].el + " Posicion -> " + elOrd[i].pos + " Estado -> " + estado);
        }
        int cont = 2;
        System.out.println("");
        System.out.println("barrido de los errores lineales");
        

        boolean ban = true;
        while (true) {
            error[] elAux = new error[n];
            double[][] wsDin = ws;
            double[] usDin = us;
            double[] elDin = us;//los errores lineales que se generan dinamicamente nuevos 
            int cantEl = 0;//cantidad de errores lineales que calculo
            int k = 0;
            
            while (k < n) {//me recorre todo elOrd vector
                if (elOrd[k].es == true) {//valida si esa neurona(la neurona asociada al error lineal) esta activa o inactiva 
                    elAux[cantEl] = elOrd[k];//almaceno elOrd error lineal en un vector auxiliar para hacerle elOrd recorrido a ese vector auxiliar luego
                    if (cantEl == cont) {//si ya esta elOrd total de errores lineales para ese recorrido cierro elOrd ciclo
                        break;
                    }
                    cantEl++;
                }
                k++;
            }
            
            //actualizacion de pesos y umbrales asociados a la neurona que queremos verificar
            for (int j = 0; j < cantEl; j++) {
                usDin[elAux[j].pos] = usDin[elAux[j].pos] + 2 * lr * el[elAux[j].pos];
                for (int l = 0; l < ñ; l++) {
                    wsDin[l][elAux[j].pos] = wsDin[l][elAux[j].pos] + 2 * lr * el[elAux[j].pos]* hc[l];
                }
            }
            System.out.println("UMBRALES");
            for (int l = 0; l < n; l++) {
                System.out.print("U[" + l + "]" + us[l] + " ");
            }
            System.out.println("");
            System.out.println("PESOS FINALES");
            for (int l = 0; l < n; l++) {
                for (int j = 0; j < ñ; j++) {
                    System.out.print("w[" + j + "][" + l + "]" + ws[j][l] + "  ");
                }
                System.out.println("");
            }
            System.out.println("");
            System.out.println("");
            System.out.println("Calculo de las salidas de la Red nuevamnente");
            //la primera capa oculta
            for (int l = 0; l < 10; l++) {

            }
            //la segunda capa oculta
            for (int l = 0; l < 10; l++) {

            }
            //la capa de salida
            for (int l = 0; l < 10; l++) {

            }
            //errores lineales producidos a la salida dinamicamente 

            for (int j = 0; j < cantEl; j++) {
                if (ban) {
                    
                }
                //si es el menor de todos 
                if (cont == 1) {
                    elOrd[j].es = false;//el menor de todos siempre se desactiva
                }
            }
            cont++;
            break;
        }
    }
}

class error {

    int pos;//pocision en la que se almacena originalmente
    double el;//elemento o error lineal
    boolean es;//estado [activo =1; inanctivo=0]

    public error() {
        this.es = true;
    }
}
