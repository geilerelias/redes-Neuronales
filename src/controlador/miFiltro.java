/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.File;

/**
 *
 * @author eldoc
 */
public class miFiltro extends javax.swing.filechooser.FileFilter {

    @Override
    public boolean accept(File file) {
        // Permitir sólo directorios o archivos con extensión ".txt"
        return file.isDirectory() || file.getAbsolutePath().endsWith(".txt");
    }

    @Override
    public String getDescription() {
        // Esta descripción se mostrará en el cuadro de diálogo,
        // hard-coded = feo, debe hacerse vía I18N
        return "Text documents (*.txt)";
    }

    public String setDescription() {
        // Esta descripción se mostrará en el cuadro de diálogo,
        // hard-coded = feo, debe hacerse vía I18N
        return "Text documents (*.txt)";
    }
}

