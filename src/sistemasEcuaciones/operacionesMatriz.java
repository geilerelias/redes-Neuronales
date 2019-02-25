/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasEcuaciones;

import controlador.miFiltro;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author eldoc
 */
public class operacionesMatriz {

    public operacionesMatriz() {
    }

    double[][] abrirArchivo() {

        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setDialogTitle("Cargar Archivo");
        fileChooser.setFileFilter(new miFiltro());
        int returnVal = fileChooser.showOpenDialog(null);
        String linea;
        double[][] mat;
        ArrayList lista = new ArrayList();
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
                        lista.add(linea.split(" "));
                    }
                    lee.close();

                    Object[] get = (Object[]) lista.get(0);
                    mat = new double[lista.size()][get.length];

                    for (int i = 0; i < lista.size(); i++) {
                        get = (Object[]) lista.get(i);
                        for (int j = 0; j < get.length; j++) {
                            mat[i][j] = Double.parseDouble((String) get[j]);
                        }
                        System.out.println("");
                    }
                    for (double[] mat1 : mat) {
                        for (int k = 0; k < mat1.length; k++) {
                            System.out.print("x[" + k + "]: " + mat1[k] + " ");
                        }
                        System.out.println("");
                    }

                    return mat;
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex + "" + "\nNo se ha encontrado el archivo", "ADVERTENCIA!!!", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "\nOpeeracion cancelada por el usuario", "ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);

        }
        //actualizar la Tabla con el archivo que acabamode leer
        return null;

    }

    double[][] matrizInversa(double[][] matriz) {
        double det = 1 / determinante(matriz);
        double[][] nmatriz = matrizAdjunta(matriz);
        multiplicarMatriz(det, nmatriz);
        return nmatriz;
    }

    void multiplicarMatriz(double n, double[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j] *= n;
            }
        }
    }

    double[][] matrizAdjunta(double[][] matriz) {
        return matrizTranspuesta(matrizCofactores(matriz));
    }

    double[][] matrizCofactores(double[][] matriz) {
        double[][] nm = new double[matriz.length][matriz.length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                double[][] det = new double[matriz.length - 1][matriz.length - 1];
                double detValor;
                for (int k = 0; k < matriz.length; k++) {
                    if (k != i) {
                        for (int l = 0; l < matriz.length; l++) {
                            if (l != j) {
                                int indice1 = k < i ? k : k - 1;
                                int indice2 = l < j ? l : l - 1;
                                det[indice1][indice2] = matriz[k][l];
                            }
                        }
                    }
                }
                detValor = determinante(det);
                nm[i][j] = detValor * (double) Math.pow(-1, i + j + 2);
            }
        }
        return nm;
    }

    double[][] matrizTranspuesta(double[][] matriz) {
        double[][] nuevam = new double[matriz[0].length][matriz.length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                nuevam[i][j] = matriz[j][i];
            }
        }
        return nuevam;
    }

    double determinante(double[][] matriz) {
        double det;
        if (matriz.length == 2) {
            det = (matriz[0][0] * matriz[1][1]) - (matriz[1][0] * matriz[0][1]);
            return det;
        }
        double suma = 0;
        for (int i = 0; i < matriz.length; i++) {
            double[][] nm = new double[matriz.length - 1][matriz.length - 1];
            for (int j = 0; j < matriz.length; j++) {
                if (j != i) {
                    for (int k = 1; k < matriz.length; k++) {
                        int indice = -1;
                        if (j < i) {
                            indice = j;
                        } else if (j > i) {
                            indice = j - 1;
                        }
                        nm[indice][k - 1] = matriz[j][k];
                    }
                }
            }
            if (i % 2 == 0) {
                suma += matriz[i][0] * determinante(nm);
            } else {
                suma -= matriz[i][0] * determinante(nm);
            }
        }
        return suma;
    }

    double[][] matrizTranspuestaPorMatriz(double[][] matriz,double [] salidas) {
        double[][] matrizT = matrizTranspuesta(matriz);
        
        return null;
    }

    //ALGORITMO DEL METODO DE GAUSS JORDAN
    void muestramatriz(double matriz[][], int var) {
        for (int x = 0; x < var; x++) {
            for (int y = 0; y < (var + 1); y++) {
                frmMatriz.textArea.append(" " + matriz[x][y] + " |");
            }
            frmMatriz.textArea.append("\n");
        }

    }

    void pivote(double matriz[][], int piv, int var) {
        double temp = 0;
        temp = matriz[piv][piv];
        for (int y = 0; y < (var + 1); y++) {

            matriz[piv][y] = matriz[piv][y] / temp;
        }
    }

    void hacerceros(double matriz[][], int piv, int var) {
        for (int x = 0; x < var; x++) {
            if (x != piv) {
                double c = matriz[x][piv];
                for (int z = 0; z < (var + 1); z++) {
                    matriz[x][z] = ((-1 * c) * matriz[piv][z]) + matriz[x][z];
                }
            }
        }
    }

    public void gauss(double[][] matriz) {
        if (matriz.length == matriz[0].length - 1) {

            int var = 0, piv = 0;
            frmMatriz.textArea.append("\t ** Este programa nos muestra la solución de un sistema de ecuaciones \n\t\tlineales a través del método Gauss_Jordan **" + "\n");
            var = matriz.length;

            for (int a = 0; a < var; a++) {
                pivote(matriz, piv, var);
                frmMatriz.textArea.append("\tRenglon " + (a + 1) + " entre el pivote" + "\n");
                muestramatriz(matriz, var);

                frmMatriz.textArea.append("");

                frmMatriz.textArea.append("\tHaciendo ceros" + "\n");
                hacerceros(matriz, piv, var);

                muestramatriz(matriz, var);
                frmMatriz.textArea.append("");
                piv++;
            }
            for (int x = 0; x < var; x++) {
                frmMatriz.textArea.append("La variable X" + (x + 1) + " es: " + matriz[x][var] + "\n");
            }
        } else {
            JOptionPane.showMessageDialog(null, "La matriz no es cuadrada", "Error Matriz", JOptionPane.ERROR_MESSAGE);
        }

    }
}
