/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import modelo.perceptronDCO;

/**
 *
 * @author eldoc
 */
public class perceptronMDOCControlador {

    public static void main(String[] args) {

        JSpinner txtEntradas = new JSpinner();
        txtEntradas.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        txtEntradas.setValue(3);

        JSpinner txt1Capa = new JSpinner();
        txt1Capa.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        txt1Capa.setValue(6);

        JSpinner txt2Capa = new JSpinner();
        txt2Capa.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        txt2Capa.setValue(3);

        JSpinner txtSalidas = new JSpinner();
        txtSalidas.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        txtSalidas.setValue(1);

        JSpinner txtPatrones = new JSpinner();
        txtPatrones.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        txtPatrones.setValue(16);

        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 1));//filas, columnas, espacio entre filas, espacio entre columnas

        panel.add(new JLabel("Numero de entradas"));
        panel.add(txtEntradas);

        panel.add(new JLabel("Numero Neuronas 1 Capa O."));
        panel.add(txt1Capa);

        panel.add(new JLabel("Numero Neuronas 2 Capa O."));
        panel.add(txt2Capa);

        panel.add(new JLabel("Numero de salidas"));
        panel.add(txtSalidas);

        panel.add(new JLabel("Numero de Patrones"));
        panel.add(txtPatrones);

        JOptionPane.showMessageDialog(null, panel);

        int m = (int) txtEntradas.getValue();
        int o = (int) txt1Capa.getValue();
        int ñ = (int) txt2Capa.getValue();
        int n = (int) txtSalidas.getValue();
        int patrones = (int) txtPatrones.getValue();

        double[][] x = abrirArchivo(m, patrones, "Entradas");
        double[][] yd = abrirArchivo(n, patrones, "Salidas");
        System.out.println("ENTRADAS");
        for (double[] x1 : x) {
            for (int j = 0; j < x1.length; j++) {
                System.out.print("x[" + j + "] : " + x1[j] + " ");
            }
            System.out.println("");
        }
        System.out.println("SALIDAS");
        for (double[] y1 : yd) {
            for (int j = 0; j < y1.length; j++) {
                System.out.print("Y[" + j + "] : " + y1[j] + " ");
            }
            System.out.println("");
        }
        do {

            JPanel panel2 = new JPanel(new GridLayout(7, 2, 5, 1));//filas, columnas, espacio entre filas, espacio entre columnas
            JSpinner txtEpoch = new JSpinner();
            txtEpoch.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
            txtEpoch.setValue(100);

            JTextField txtGoal = new JTextField();
            txtGoal.setText("0.0");

            JTextField txtLr = new JTextField();
            txtLr.setText("0.1");

            String[] funcionesActivacionCapas = {"gaussiana", "sigmoide"};
            String[] funcionesActivacionSalida = {"gaussiana", "sigmoide", "lineal"};

            JComboBox cbxFuncionCapa1 = new JComboBox(funcionesActivacionCapas);
            cbxFuncionCapa1.setSelectedIndex(0);

            JComboBox cbxFuncionCapa2 = new JComboBox(funcionesActivacionCapas);
            cbxFuncionCapa2.setSelectedIndex(0);

            JComboBox cbxFuncionSalida = new JComboBox(funcionesActivacionSalida);
            cbxFuncionSalida.setSelectedIndex(0);

            String[] algoritmos = {"delta", "deltaModificado"};
            JComboBox cbxAlgoritmo = new JComboBox(algoritmos);
            cbxAlgoritmo.setSelectedIndex(0);

            panel2.add(new JLabel("Numero de Interaciones"));
            panel2.add(txtEpoch);

            panel2.add(new JLabel("Rata de Aprendizaje"));
            panel2.add(txtLr);

            panel2.add(new JLabel("Error maximo Permitido"));
            panel2.add(txtGoal);

            panel2.add(new JLabel("Fuancion Activacion Capa1"));
            panel2.add(cbxFuncionCapa1);

            panel2.add(new JLabel("Fuancion Activacion Capa2"));
            panel2.add(cbxFuncionCapa2);

            panel2.add(new JLabel("Fuancion Activacion Salida"));
            panel2.add(cbxFuncionSalida);

            panel2.add(new JLabel("Algoritmo de Entrenamiento"));
            panel2.add(cbxAlgoritmo);
            JOptionPane.showMessageDialog(null, panel2);

            int epoch = (int) txtEpoch.getValue();
            double lr = Double.parseDouble(txtLr.getText());
            double goal = Double.parseDouble(txtGoal.getText());

            String funcionActivacioncapa1 = (String) cbxFuncionCapa1.getSelectedItem();
            String funcionActivacioncapa2 = (String) cbxFuncionCapa2.getSelectedItem();
            String funcionActivacionSalida = (String) cbxFuncionSalida.getSelectedItem();
            String algoritmo = (String) cbxAlgoritmo.getSelectedItem();
            String nombre = "red";
            perceptronDCO p = new perceptronDCO(m, o, ñ, n, patrones, x, yd, epoch, lr, goal, funcionActivacioncapa1, funcionActivacioncapa2, funcionActivacionSalida, algoritmo, nombre);
            p.inicializacionPesosUmbrales();
            p.entrenamiento() ;
                    p.simular();
                
           
        } while (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Deseas Volver a Entrenar", "", JOptionPane.YES_NO_OPTION));

    }

    static double[][] abrirArchivo(int n_m, int patrones, String cadena) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(cadena);
        fileChooser.setFileFilter(new miFiltro());
        int returnVal = fileChooser.showOpenDialog(null);
        String linea;
        double[][] mat = new double[patrones][n_m];
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

                        for (int i = 0; i < values.length; i++) {
                            //se obtiene el primer caracter de el arreglo de strings
                            mat[cont][i] = Double.parseDouble(values[i]);
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

        return mat;

    }

}
