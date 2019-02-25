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
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class panelTransparente extends JPanel{

public panelTransparente(Color c){
 setBackground(c);
 setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
}

@Override
protected void paintComponent(Graphics g) {
 Graphics2D g2 = (Graphics2D) g;
 g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
 RenderingHints.VALUE_INTERPOLATION_BILINEAR);
 AlphaComposite old = (AlphaComposite) g2.getComposite();
 g2.setComposite(AlphaComposite.SrcOver.derive(0.5f));
 super.paintComponent(g);
 g2.setComposite(old);
}

}