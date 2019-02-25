package modelo;

import controlador.*;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author eldoc
 */
public class madaline {

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
        //seria todo error proceso de entrenamiento y al final calculo errores lineales 
        for (int i = 0; i < n; i++) {
            double num = Math.random() * n + 1;

            ob = new error();
            ob.error = num;
            ob.pos = i;
            System.out.println(ob.error + " pos -> " + ob.pos);

            el[i] = num;//este error valor real de los errores lineal de la red
            elOrd[i] = ob;//tambien lo guardo en otro vector que ordenaremos y utilizaremos para error calculo de los errores lineales

        }

        int imin;
        error aux;
        System.out.println("");
        System.out.println("");
        //ordenamiento del vector elOrd
        for (int i = 0; i < n; i++) {
            imin = i;
            for (int j = i + 1; j < n; j++) {
                if (elOrd[j].error < elOrd[imin].error) {
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
            System.out.println(elOrd[i].error + " Posicion -> " + elOrd[i].pos + " Estado -> " + estado);
        }
        int cont = 1;
        System.out.println("");
        System.out.println("barrido de los errores lineales");

        boolean ban = true;
        do {
            ArrayList<error> elAux = new ArrayList();
            double[][] wsDin = ws;
            double[] usDin = us;
            double[] elDin = us;//los errores lineales que se generan dinamicamente nuevos 

            int k = 0;

            while (k < n) {//me recorre todo elOrd vector
                if (elOrd[k].es == true) {//valida si esa neurona(la neurona asociada al error lineal) esta activa o inactiva 
                    elOrd[k].posVO = k;//le asigno la pocision que tiene del vector ordenado
                    elAux.add(elOrd[k]);//almaceno elOrd error lineal en una lista auxiliar para hacerle elOrd recorrido a ese vector auxiliar luego
                    if (elAux.size() == cont) {//si ya esta elOrd total de errores lineales para ese recorrido cierro elOrd ciclo
                        break;
                    }
                }
                k++;
            }
            int cantEl = elAux.size();//cantidad de errores lineales que calculo
            //actualizacion de pesos y umbrales asociados a la neurona que queremos verificar
            for (int j = 0; j < cantEl; j++) {
                usDin[elAux.get(j).pos] = usDin[elAux.get(j).pos] + 2 * lr * el[elAux.get(j).pos];
                for (int l = 0; l < ñ; l++) {
                    wsDin[l][elAux.get(j).pos] = wsDin[l][elAux.get(j).pos] + 2 * lr * el[elAux.get(j).pos] * hc[l];
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
            /*/la primera capa oculta
            for (int l = 0; l < 10; l++) {

            }
            //la segunda capa oculta
            for (int l = 0; l < 10; l++) {

            }
            //la capa de salida
            for (int l = 0; l < 10; l++) {

            }*/
            //errores lineales producidos a la salida dinamicamente 

            //comparacion
            for (int j = 0; j < cantEl; j++) {
                if (elAux.get(j).error < el[elAux.get(j).pos]) { //otengo error error lineal que tengo almacenadoo en error aux y lo compara con error error lineal original pero con a pocision que tengo almacenada en error elaux
                    //actualizo los pesos asociados a esa neurona 
                    us[elAux.get(j).pos] = us[elAux.get(j).pos] + 2 * lr * el[elAux.get(j).pos];
                    for (int l = 0; l < ñ; l++) {
                        ws[l][elAux.get(j).pos] = ws[l][elAux.get(j).pos] + 2 * lr * el[elAux.get(j).pos] * hc[l];
                    }
                    elOrd[elAux.get(j).posVO].es = false;
                }
                //si es error menor de todos siempre lo desactivo
                if (cont == 1) {
                    elOrd[j].es = false;//el menor de todos siempre se desactiva
                }
            }
            cont++;
        } while (cont <= n);
    }
}

