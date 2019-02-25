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
/**
 *
 * @author http://jonathan-palomino.blogspot.com/
 *
 */
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.geom.AffineTransform;

public class pruebaSombra extends JFrame {

    private long serialVersionUID;
    private JPanel jContentPane = null;
    private int tamaño = 55;
    private String mensaje = "Programación fácil con JAVA";//  @jve:decl-index=0: 
    private int Width = 50;
    private double inclinacion_vertical = 0.1;
    private double inclinacion_horizontal = 0;
    private double escala_altura = 2;

    /**
     *      * @param args 
     
     */
    public static void main(String[] args) {
// TODO Auto-generated method stub 

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                pruebaSombra thisClass = new pruebaSombra();
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.setVisible(true);
            }
        });
    }

    /**
     *      * This is the default constructor 
     
     */
    public pruebaSombra() {
        super();
        initialize();

    }

    /**
     *      * This method initializes this      *      * @return void 
     
     */
    private void initialize() {
        this.setSize(985, 257);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setContentPane(getJContentPane());
        this.setTitle("TEXTO CON SOMBRA");
        this.setBackground(Color.white);
        Font font = new Font("DokChampa", Font.BOLD, tamaño);
        this.setFont(font);
    }

    /**
     *      * This method initializes jContentPane      *      * @return
     * javax.swing.JPanel 
     
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(new BorderLayout());
        }
        return jContentPane;
    }


    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graph = (Graphics2D) g;
        int x = Width;
        int y = tamaño * 5 / 2;
        graph.translate(10, y);
        graph.setPaint(Color.lightGray);
        AffineTransform origTransform = graph.getTransform();
        graph.shear(inclinacion_vertical, inclinacion_horizontal);
        graph.scale(1, escala_altura);
        graph.drawString(mensaje, 0, 0);
        graph.setTransform(origTransform);
        graph.setPaint(Color.blue);
        graph.drawString(mensaje, 0, 0);
    }

}
