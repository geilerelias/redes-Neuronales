/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import vista.FrmMadaline;

/**
 *
 * @author eldoc
 */
public class madalineUCO extends red implements Runnable {

    //Multicapa Una Capa Oculta (MUCO)
    private int o;
    private String funcionActivacionEntrada;
    private String funcionActivacionSalida;
    private String algoritmo;

    public madalineUCO() {
    }

    public madalineUCO(int m, int o, int n, int patrones, double[][] x, double[][] yd, int epoch, double lr, double goal, String funcionActivacionEntrada, String funcionActivacionSalida, String algoritmo, String nombre) {
        super(m, n, patrones, x, yd, epoch, lr, goal, nombre);
        this.o = o;
        this.funcionActivacionEntrada = funcionActivacionEntrada;
        this.funcionActivacionSalida = funcionActivacionSalida;
        this.algoritmo = algoritmo;
    }

    public int getO() {
        return o;
    }

    public void setO(int o) {
        this.o = o;
    }

    public String getFuncionActivacionEntrada() {
        return funcionActivacionEntrada;
    }

    public void setFuncionActivacionEntrada(String funcionActivacionEntrada) {
        this.funcionActivacionEntrada = funcionActivacionEntrada;
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

    public double[][] getW() {
        return w;
    }

    public void setW(double[][] w) {
        this.w = w;
    }

    public double[] getU() {
        return u;
    }

    public void setU(double[] u) {
        this.u = u;
    }

    public double[][] getWs() {
        return ws;
    }

    public void setWs(double[][] ws) {
        this.ws = ws;
    }

    public double[] getUs() {
        return us;
    }

    public void setUs(double[] us) {
        this.us = us;
    }

    //capa oculta
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
    double sumEp;
    double sumEl;
    public double lrD = 1;

    Random rand = new Random();

    public void inicializacionPesosUmbrales(int m, int o, int n) {
        this.setM(m);
        this.setO(o);
        this.setN(n);

        w = new double[getM()][getO()];
        u = new double[getO()];

        ws = new double[getO()][getN()];
        us = new double[getN()];
        //System.out.println("INICIALIZACION DE PESOS Y UMBRALES");
        //System.out.println("***Primera Capa Oculta****");
        for (int i = 0; i < getO(); i++) {
            u[i] = Math.random() * ThreadLocalRandom.current().nextInt(-1, 1 + 1);

            // System.out.println("Umbral [" + i + "]: " + u[i]);
        }
        for (int i = 0; i < getO(); i++) {
            for (int j = 0; j < getM(); j++) {
                w[j][i] = Math.random() * ThreadLocalRandom.current().nextInt(-1, 1 + 1);
                //System.out.print("Peso[" + j + "][" + i + "]: " + w[j][i] + " ");
            }
            //System.out.println("");
        }
        // System.out.println("***Capa Salida***");
        for (int i = 0; i < getN(); i++) {
            us[i] = Math.random() * ThreadLocalRandom.current().nextInt(-1, 1 + 1);
            // System.out.println("Umbral[" + i + "]: " + us[i]);
        }

        for (int i = 0; i < getN(); i++) {
            for (int j = 0; j < getO(); j++) {
                ws[j][i] = Math.random() * ThreadLocalRandom.current().nextInt(-1, 1 + 1);
                //System.out.print("Peso[" + j + "][" + i + "]: " + ws[j][i] + " ");
            }
            //System.out.println("");
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
        FrmMadaline.txtAreaVista.setText("");
        double erms;
        //System.out.println("");
        //System.out.println("");
        ArrayList lista = new ArrayList<>();
        for (int e = 1; e <= getEpoch(); e++) {//ciclo de iteraciones
            //System.out.println("INTERACION # " + e);
            sumEp = 0;
            for (int p = 0; p < getPatrones(); p++) {//ciclo de patrones

                for (int i = 0; i < getO(); i++) {
                    s[i] = 0;
                    for (int j = 0; j < getM(); j++) {
                        // System.out.println("soma[" + i + "] = soma[" + i + "]:" + s[i] + " + x[" + p + "][" + j + "]:" + getX()[p][j] + " * w[" + j + "][" + i + "]:" + w[j][i]);
                        s[i] = s[i] + getX()[p][j] * w[j][i];
                    }
                    //System.out.println("soma : " + s[i]);
                    //System.out.println("soma Atenuada= " + "soma[" + i + "]:" + s[i] + " - " + "u[" + i + "]" + u[i]);
                    s[i] = s[i] - u[i];
                    //System.out.println("soma Atenuada: " + s[i]);
                    h[i] = activacion(getFuncionActivacionEntrada(), (s[i]));
                    //System.out.println("");
                    //System.out.println("Salida de la primera capa: " + h[i]);
                    //System.out.println("");

                }
                //System.out.println("");
                //System.out.println("");
                for (int i = 0; i < getN(); i++) {
                    ss[i] = 0;
                    for (int j = 0; j < getO(); j++) {
                        //System.out.println("somaS[" + i + "] = somaS[" + i + "]:" + ss[i] + " + h[" + j + "]:" + h[j] + " * w[" + j + "][" + i + "]:" + ws[j][i]);
                        ss[i] = ss[i] + h[j] * ws[j][i];
                    }
                    //System.out.println("somaS : " + ss[i]);
                    //System.out.println("soma Atenuada= " + "somaS[" + i + "]:" + ss[i] + " - " + "us[" + i + "]" + us[i]);
                    ss[i] = ss[i] - us[i];
                    //System.out.println("soma Atenuada: " + ss[i]);
                    yr[i] = activacion(getFuncionActivacionSalida(), (ss[i]));
                    //System.out.println("");
                    //System.out.println("");
                    //System.out.println("Salida de la Red: " + yr[i]);
                    //System.out.println("");
                    //System.out.println("");
                    el[i] = getYd()[p][i] - yr[i];
                    //System.out.println("Error Lineal = " + "yd[" + p + "][" + i + "]:" + getYd()[p][i] + " - " + "y[" + i + "]:" + el[i]);
                    //System.out.println("Error Lineal: " + el[i]);
                }
                //proceso de eleccion de errores lineales optimos
                erroreLineasOptimos(p);
                sumEl = 0;
                for (int i = 0; i < getN(); i++) {
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
                erroreNoLineasOptimos(p);

                ep[p] = sumEl / getN();
                FrmMadaline.txtAreaVista.append(String.format("ErrorPatron(%d):%f \n", p, ep[p]));
                sumEl = 0;
                //System.out.println("");
                //System.out.println("");
                //System.out.println("Error por patron: " + ep[p]);
                //System.out.println("\n\n");
                sumEp = sumEp + ep[p];

                actualizacionPesosUmbrales(p);
            }
            erms = sumEp / getPatrones();
            lista.add(erms);
            FrmMadaline.cargar(lista, this.getGoal());
            //System.out.println("Error de la iterecion #" + e + ": " + erms + "\n");
            FrmMadaline.txtAreaVista.append("Error ERMS: " + erms + "\n");
            sumEp = 0;
            //System.out.println("Error de la iterecion: " + erms);
            System.out.println("");
            System.out.println("");
            System.out.println("");
            if (erms <= getGoal()) {
                ArrayList pesos = new ArrayList();
                pesos.add(getW());
                pesos.add(getWs());
                ArrayList umbrales = new ArrayList();
                umbrales.add(getU());
                umbrales.add(getUs());
                int[] numCapas = {getM(), getO(), getN()};
                String[] funcion = {getFuncionActivacionEntrada(), getFuncionActivacionSalida()};
                getSetPesosUmbrales gspu;
                gspu = new getSetPesosUmbrales(getNombre(), numCapas, getPatrones(), funcion, getAlgoritmo(), getLr(), pesos, umbrales, getX(), getYd());
                gspu.guardarPesosUmbrales();
                FrmMadaline.lblEstado.setText("Finalizado");
                System.out.println("ENTRENAMIENTO FINALIZADO CON EXITO EN INTERACION " + e + "... ");
                FrmMadaline.txtAreaVista.append("ENTRENAMIENTO FINALIZADO CON EXITO EN INTERACION " + e + "... ");
                System.out.println("");
                System.out.println("Umbrales Optimos");
                FrmMadaline.txtAreaVista.append("Umbrales Optimos");
                String cad = "";
                for (int i = 0; i < u.length; i++) {
                    System.out.println(u[i]);
                    cad = cad + u[i] + " ";
                }
                FrmMadaline.txtAreaVista.append(cad);
                System.out.println("");
                System.out.println("Pesos Optimos");
                FrmMadaline.txtAreaVista.append("Pesos Optimos");
                cad = "";
                for (int i = 0; i < getN(); i++) {
                    for (int j = 0; j < getM(); j++) {
                        System.out.println(w[j][i] + " ");
                        cad = cad + w[j][i] + " ";
                    }
                    System.out.println("");
                    FrmMadaline.txtAreaVista.append(cad);
                }
                ArrayList l;
                l = new ArrayList<>();
                l.add(0, w);
                l.add(1, u);
                l.add(2, ws);
                l.add(3, us);
                return l;
            }
        }
        FrmMadaline.lblEstado.setText("Finalizado");

        return null;
    }

    private void actualizacionPesosUmbrales(int p) {
        //System.out.println("ACTUALIZACION DE PESOS Y UMBRALES");
        //System.out.println("***Primera Capa Oculta****");
        for (int i = 0; i < getO(); i++) {
            //System.out.print("Umbral Anterior[" + i + "]: " + u[i] + " ");
            u[i] = u[i] + 2 * getLr() * eNl[i];
            //System.out.println("Umbral Modificado[" + i + "]: " + u[i]);
        }

        for (int i = 0; i < getO(); i++) {
            for (int j = 0; j < getM(); j++) {
                //System.out.print("Peso Anterior[" + j + "][" + i + "]: " + w[j][i] + " ");
                w[j][i] = w[j][i] + 2 * getLr() * eNl[i] * getX()[p][j];
                //System.out.println("Peso Modificado[" + j + "][" + i + "]: " + w[j][i] + " ");
            }
        }

        //System.out.println("***Capa de Salida****");
        for (int i = 0; i < getN(); i++) {
            //System.out.print("Umbral Anterior[" + i + "]: " + us[i] + " ");
            us[i] = us[i] + getLr() * el[i];
            //System.out.println("Umbral Modificado[" + i + "]: " + us[i]);
        }

        for (int i = 0; i < getN(); i++) {
            for (int j = 0; j < getO(); j++) {
                //System.out.print("Peso Anterior[" + j + "][" + i + "]: " + ws[j][i] + " ");
                ws[j][i] = ws[j][i] + 2 * getLr() * el[i] * h[j];
                //System.out.println("Peso Modificado[" + j + "][" + i + "]: " + ws[j][i] + " ");
            }
        }
        //System.out.println("");
        //System.out.println("");
    }

    @Override
    public void run() {
        entrenamiento();
    }

    private void erroreLineasOptimos(int p) {
        error[] elOrd = new error[getN()];
        error ob;
        //System.out.println("vector Inicial");
        //seria todo error proceso de entrenamiento y al final calculo errores lineales 
        for (int i = 0; i < getN(); i++) {

            ob = new error();
            ob.error = el[i];
            ob.pos = i;
            //System.out.println(ob.error + " pos -> " + ob.pos);
            elOrd[i] = ob;// vector que ordenaremos y utilizaremos para error calculo de los errores lineales
        }
        int imin;
        error aux;
        //System.out.println("");
        //System.out.println("");
        //ordenamiento del vector elOrd
        for (int i = 0; i < getN(); i++) {
            imin = i;
            for (int j = i + 1; j < getN(); j++) {
                if (elOrd[j].error < elOrd[imin].error) {
                    imin = j;
                }
            }
            aux = elOrd[i];
            elOrd[i] = elOrd[imin];
            elOrd[imin] = aux;
        }

        //System.out.println("vector ordenado");
        for (int i = 0; i < getN(); i++) {
            String estado = (elOrd[i].es == true) ? "Activo" : "Inactivo";
            System.out.println(elOrd[i].error + " Posicion -> " + elOrd[i].pos + " Estado -> " + estado);
        }
        int cont = 0;
        //System.out.println("");
        //System.out.println("barrido de los errores lineales");

        do {
            ArrayList<error> elAux = new ArrayList();
            double[][] wsDin = ws;
            double[] usDin = us;
            double[] elDin = el;//los errores lineales que se generan dinamicamente nuevos 

            int k = 0;

            while (k < getN()) {//me recorre todo elOrd vector
                if (elOrd[k].es == true) {//valida si esa neurona(la neurona asociada al error lineal) esta activa o inactiva 
                    elOrd[k].posVO = k;//le asigno la pocision que tiene del vector ordenado
                    elAux.add(elOrd[k]);//almaceno elOrd error lineal en una lista auxiliar para hacerle elOrd recorrido a ese vector auxiliar luego
                    if (elAux.size() > cont) {//si ya esta elOrd total de errores lineales para ese recorrido cierro elOrd ciclo
                        break;
                    }
                }
                k++;
            }
            int cantEl = elAux.size();//cantidad de errores lineales que calculo
            //actualizacion de pesos y umbrales asociados a la neurona que queremos verificar
            for (int j = 0; j < cantEl; j++) {
                usDin[elAux.get(j).pos] = usDin[elAux.get(j).pos] + 2 * getLr() * el[elAux.get(j).pos];
                for (int l = 0; l < getO(); l++) {
                    wsDin[l][elAux.get(j).pos] = wsDin[l][elAux.get(j).pos] + 2 * getLr() * el[elAux.get(j).pos] * h[l];
                }
            }

            //System.out.println("");
            //System.out.println("");
            //System.out.println("Calculo de las salidas de la Red nuevamnente");
            for (int i = 0; i < getO(); i++) {
                s[i] = 0;
                for (int j = 0; j < getM(); j++) {
                    //System.out.println("soma[" + i + "] = soma[" + i + "]:" + s[i] + " + x[" + p + "][" + j + "]:" + getX()[p][j] + " * w[" + j + "][" + i + "]:" + w[j][i]);
                    s[i] = s[i] + getX()[p][j] * w[j][i];
                }
                //System.out.println("soma : " + s[i]);
                //System.out.println("soma Atenuada= " + "soma[" + i + "]:" + s[i] + " - " + "u[" + i + "]" + u[i]);
                s[i] = s[i] - u[i];
                //System.out.println("soma Atenuada: " + s[i]);
                h[i] = activacion(getFuncionActivacionEntrada(), (s[i]));
                //System.out.println("");
                //System.out.println("Salida de la primera capa: " + h[i]);
                //System.out.println("");

            }
            //System.out.println("");
            //System.out.println("");
            for (int i = 0; i < getN(); i++) {
                ss[i] = 0;
                for (int j = 0; j < getO(); j++) {
                    //System.out.println("somaS[" + i + "] = somaS[" + i + "]:" + ss[i] + " + h[" + j + "]:" + h[j] + " * wsDin[" + j + "][" + i + "]:" + wsDin[j][i]);
                    ss[i] = ss[i] + h[j] * wsDin[j][i];
                }
                //System.out.println("somaS : " + ss[i]);
                //System.out.println("soma Atenuada= " + "somaS[" + i + "]:" + ss[i] + " - " + "us[" + i + "]" + usDin[i]);
                ss[i] = ss[i] - usDin[i];
                //System.out.println("soma Atenuada: " + ss[i]);
                yr[i] = activacion(getFuncionActivacionSalida(), (ss[i]));
                //System.out.println("");
                //System.out.println("");
                //System.out.println("Salida de la Red: " + yr[i]);
                //System.out.println("");
                //System.out.println("");
                elDin[i] = getYd()[p][i] - yr[i];
                //System.out.println("Error Lineal Dinamico = " + "yd[" + p + "][" + i + "]:" + getYd()[p][i] + " - " + "y[" + i + "]:" + el[i]);
                //System.out.println("Error Lineal: " + elDin[i]);

            }
            //errores lineales producidos a la salida dinamicamente 
            //comparacion
            for (int j = 0; j < cantEl; j++) {
                if (elDin[elAux.get(j).pos] < el[elAux.get(j).pos]) { //otengo error error lineal que tengo almacenadoo en error aux y lo compara con error error lineal original pero con la pocision que tengo almacenada en error elaux
                    //actualizo los pesos asociados a esa neurona 
                    us[elAux.get(j).pos] = usDin[elAux.get(j).pos];
                    for (int l = 0; l < getO(); l++) {
                        ws[l][elAux.get(j).pos] = wsDin[l][elAux.get(j).pos];
                    }
                    elOrd[elAux.get(j).posVO].es = false;
                }
                //si es error menor de todos siempre lo desactivo
                if (cont < 1) {
                    elOrd[j].es = false;//el menor de todos siempre se desactiva
                }
            }
            cont++;
        } while (cont < getN());

    }

    private void erroreNoLineasOptimos(int p) {
        error[] eNolOrd = new error[getO()];
        error ob;
        //System.out.println("vector Inicial");
        //seria todo error proceso de entrenamiento y al final calculo errores lineales 
        for (int i = 0; i < getO(); i++) {

            ob = new error();
            ob.error = eNl[i];
            ob.pos = i;
            //System.out.println(ob.error + " pos -> " + ob.pos);
            eNolOrd[i] = ob;// vector que ordenaremos y utilizaremos para error calculo de los errores lineales
        }
        int imin;
        error aux;
        //System.out.println("");
        //System.out.println("");
        //ordenamiento del vector eNlOrd
        for (int i = 0; i < getO(); i++) {
            imin = i;
            for (int j = i + 1; j < getO(); j++) {
                if (eNolOrd[j].error < eNolOrd[imin].error) {
                    imin = j;
                }
            }
            aux = eNolOrd[i];
            eNolOrd[i] = eNolOrd[imin];
            eNolOrd[imin] = aux;
        }

        //System.out.println("vector ordenado");
        for (int i = 0; i < getO(); i++) {
            String estado = (eNolOrd[i].es == true) ? "Activo" : "Inactivo";
            System.out.println(eNolOrd[i].error + " Posicion -> " + eNolOrd[i].pos + " Estado -> " + estado);
        }
        int cont = 0;
        //System.out.println("");
        //System.out.println("barrido de los errores No lineales");

        do {
            ArrayList<error> eNlAux = new ArrayList();
            double[][] wDin = w;
            double[] uDin = u;
            double[] eNlDin = eNl;//los errores lineales que se generan dinamicamente nuevos 
            int k = 0;

            while (k < getO()) {//me recorre todo elOrd vector
                if (eNolOrd[k].es == true) {//valida si esa neurona(la neurona asociada al error lineal) esta activa o inactiva 
                    eNolOrd[k].posVO = k;//le asigno la pocision que tiene del vector ordenado
                    eNlAux.add(eNolOrd[k]);//almaceno elOrd error lineal en una lista auxiliar para hacerle elOrd recorrido a ese vector auxiliar luego
                    if (eNlAux.size() > cont) {//si ya esta elOrd total de errores lineales para ese recorrido cierro elOrd ciclo
                        break;
                    }
                }
                k++;
            }
            int cantENl = eNlAux.size();//cantidad de errores lineales que calculo
            //actualizacion de pesos y umbrales asociados a la neurona que queremos verificar
            for (int j = 0; j < cantENl; j++) {
                //System.out.println("error no lineal: " + eNlAux.get(j).error);
            }
            for (int j = 0; j < cantENl; j++) {
                uDin[eNlAux.get(j).pos] = uDin[eNlAux.get(j).pos] + 2 * getLr() * eNl[eNlAux.get(j).pos];
                for (int l = 0; l < getM(); l++) {
                    wDin[l][eNlAux.get(j).pos] = wDin[l][eNlAux.get(j).pos] + 2 * getLr() * eNl[eNlAux.get(j).pos] * h[l];
                }
            }

            //System.out.println("");
            //System.out.println("");
            //System.out.println("Calculo de las salidas de la Red nuevamnente");
            for (int i = 0; i < getO(); i++) {
                s[i] = 0;
                for (int j = 0; j < getM(); j++) {
                    //System.out.println("soma[" + i + "] = soma[" + i + "]:" + s[i] + " + x[" + p + "][" + j + "]:" + getX()[p][j] + " * wDin[" + j + "][" + i + "]:" + wDin[j][i]);
                    s[i] = s[i] + getX()[p][j] * wDin[j][i];
                }
                //System.out.println("soma : " + s[i]);
                //System.out.println("soma Atenuada= " + "soma[" + i + "]:" + s[i] + " - " + "uDin[" + i + "]" + uDin[i]);
                s[i] = s[i] - uDin[i];
                //System.out.println("soma Atenuada: " + s[i]);
                h[i] = activacion(getFuncionActivacionEntrada(), (s[i]));
                //System.out.println("");
                //System.out.println("Salida de la primera capa: " + h[i]);
                //System.out.println("");

            }
            //System.out.println("");
            //System.out.println("");
            for (int i = 0; i < getN(); i++) {
                ss[i] = 0;
                for (int j = 0; j < getO(); j++) {
                    //System.out.println("somaS[" + i + "] = somaS[" + i + "]:" + ss[i] + " + h[" + j + "]:" + h[j] + " * ws[" + j + "][" + i + "]:" + ws[j][i]);
                    ss[i] = ss[i] + h[j] * ws[j][i];
                }
                //System.out.println("somaS : " + ss[i]);
                //System.out.println("soma Atenuada= " + "somaS[" + i + "]:" + ss[i] + " - " + "us[" + i + "]" + us[i]);
                ss[i] = ss[i] - us[i];
                //System.out.println("soma Atenuada: " + ss[i]);
                yr[i] = activacion(getFuncionActivacionSalida(), (ss[i]));
                //System.out.println("");
                //System.out.println("");
                //System.out.println("Salida de la Red: " + yr[i]);
                //System.out.println("");
                //System.out.println("");
                el[i] = getYd()[p][i] - yr[i];
                //System.out.println("Error Lineal = " + "yd[" + p + "][" + i + "]:" + getYd()[p][i] + " - " + "y[" + i + "]:" + el[i]);
                //System.out.println("Error Lineal: " + el[i]);

            }
            //calculo de los errores no lineales  dinamicamente
            for (int i = 0; i < getO(); i++) {
                double sum = 0;
                for (int j = 0; j < getN(); j++) {
                    sum = sum + el[j] * ws[i][j];
                }
                eNlDin[i] = sum;
            }

            //comparacion
            for (int j = 0; j < cantENl; j++) {
                if (eNlDin[eNlAux.get(j).pos] < eNl[eNlAux.get(j).pos]) { //otengo error error lineal que tengo almacenadoo en error aux y lo compara con error error lineal original pero con la pocision que tengo almacenada en error elaux
                    //actualizo los pesos asociados a esa neurona 
                    u[eNlAux.get(j).pos] = uDin[eNlAux.get(j).pos];
                    for (int l = 0; l < getM(); l++) {
                        w[l][eNlAux.get(j).pos] = wDin[l][eNlAux.get(j).pos];
                    }
                    eNolOrd[eNlAux.get(j).posVO].es = false;
                }
                //si es error menor de todos siempre lo desactivo
                if (cont < 1) {
                    eNolOrd[j].es = false;//el menor de todos siempre se desactiva
                }
            }
            cont++;
        } while (cont < getO());

    }

}
