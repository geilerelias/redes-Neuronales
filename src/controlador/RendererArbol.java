/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.swing.ImageIcon;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author eldoc
 */
 public class RendererArbol extends DefaultTreeCellRenderer {

        /**
         * Creates a new instance of RendererArbol
         */
        public RendererArbol() {
            setLeafIcon(new ImageIcon("src/vista/images/icons8_JSON_15px_2.png"));
            setOpenIcon(new ImageIcon("src/vista/images/icons8_Open_18px.png"));
            setClosedIcon(new ImageIcon("src/vista/images/icons8_Folder_18px.png"));
        }
        
    }
