package controlador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @web http://www.jc-mouse.net
 * @author Mouse
 */
public class progresBar implements Runnable {

    private JProgressBar progresBar;
    private JTextArea texArea;
    private JTextField txtEpoch;
    private JLabel lblInteracion;
    private JLabel lblEstado;

    /**
     * Constructor de clase
     * @param jProgressBar
     * @param texArea
     * @param txtEpoch
     * @param lblInteracion
     * @param lblEstado
     */
    public progresBar(JProgressBar jProgressBar, JTextArea texArea, JTextField txtEpoch, JLabel lblInteracion,JLabel lblEstado) {
        this.progresBar = jProgressBar;
        this.texArea = texArea;
        this.txtEpoch = txtEpoch;
        this.lblInteracion = lblInteracion;
        this.lblEstado=lblEstado;
    }

    /**
     * Constructor de clase
     * @param jProgressBar
     */
    public progresBar(JProgressBar jProgressBar) {
        this.progresBar = jProgressBar;
    }

    @Override
    public void run() {
        int cont =0;
        //mientra el trabajo en paralelo no finalice el jProgressBar continuara su animacion una y otra vez
        while (true) {
            cont++;
            int num= Integer.parseInt(lblInteracion.getText());
            double num2= Double.parseDouble(txtEpoch.getText());
            double por = (num/num2)*100;
            
            //JOptionPane.showMessageDialog(null, por);
            progresBar.setValue((int)por);
            progresBar.repaint();
            //si el trabajo en paralelo a terminado
            if (lblEstado.getText().equals("Finalizado")) {
                progresBar.setValue(100);
                progresBar.repaint();
                texArea.append("Trabajo finalizado...\n");
                texArea.repaint();
                JOptionPane.showMessageDialog(null, "Entrenamiento finalizado");
                break;//rompe ciclo     
            }

        }

    }

}
