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
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class prueba extends JFrame {

    public prueba() {
        setLayout(null);
        panelTransparente pt1 = new panelTransparente(Color.BLACK);
        pt1.setPreferredSize(new Dimension(100, 50));
        pt1.setBounds(0, 0, 100, 100);

        panelTransparente pt2 = new panelTransparente(Color.BLUE);
        pt2.setPreferredSize(new Dimension(100, 50));
        pt2.setBounds(50, 50, 100, 100);

        panelTransparente pt3 = new panelTransparente(Color.GREEN);
        pt3.setPreferredSize(new Dimension(100, 50));
        pt3.setBounds(100, 100, 100, 100);

        panelTransparente pt4 = new panelTransparente(Color.MAGENTA);
        pt4.setPreferredSize(new Dimension(100, 50));
        pt4.setBounds(150, 150, 100, 100);
        
        add(pt1);
        add(pt2);
        add(pt3);
        add(pt4);
    }

    public static void main(String[] args) {
        prueba p = new prueba();
        p.setVisible(true);
        p.setBounds(0, 0, 300, 300);
        p.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
