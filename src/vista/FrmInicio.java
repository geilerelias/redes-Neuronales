/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 *
 * @author proxc
 */
public class FrmInicio extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    public FrmInicio() {
        initComponents();
        setLocationRelativeTo(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContenedor = new javax.swing.JPanel();
        btnPerceptronLg = new MaterialSwing.MaterialButtomPanel();
        jLabel36 = new javax.swing.JLabel();
        btnMadalineLg = new MaterialSwing.MaterialButtomPanel();
        jLabel16 = new javax.swing.JLabel();
        btnSimularLg = new MaterialSwing.MaterialButtomPanel();
        jLabel31 = new javax.swing.JLabel();
        btnCrearLg = new MaterialSwing.MaterialButtomPanel();
        jLabel34 = new javax.swing.JLabel();
        btnVerRegistrosLg = new MaterialSwing.MaterialButtomPanel();
        jLabel37 = new javax.swing.JLabel();
        panelTitulo = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnInicioLg1 = new MaterialSwing.MaterialButtomPanel();
        jLabel24 = new javax.swing.JLabel();
        btnPerceptronLg1 = new MaterialSwing.MaterialButtomPanel();
        jLabel38 = new javax.swing.JLabel();
        btnMadalineLg1 = new MaterialSwing.MaterialButtomPanel();
        jLabel19 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panelContenedor.setBackground(new java.awt.Color(255, 255, 255));
        panelContenedor.setMinimumSize(new java.awt.Dimension(811, 600));
        panelContenedor.setPreferredSize(new java.awt.Dimension(160, 128));

        btnPerceptronLg.setBackground(new java.awt.Color(204, 204, 204));
        btnPerceptronLg.setPreferredSize(new java.awt.Dimension(120, 115));
        btnPerceptronLg.setRippleColor(new java.awt.Color(51, 204, 0));
        btnPerceptronLg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPerceptronLgMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPerceptronLgMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPerceptronLgMouseExited(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 12)); // NOI18N
        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/icons8_Google_Scholar_48px_2.png"))); // NOI18N
        jLabel36.setText("Perceptron");
        jLabel36.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel36.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel36.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout btnPerceptronLgLayout = new javax.swing.GroupLayout(btnPerceptronLg);
        btnPerceptronLg.setLayout(btnPerceptronLgLayout);
        btnPerceptronLgLayout.setHorizontalGroup(
            btnPerceptronLgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPerceptronLgLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel36)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        btnPerceptronLgLayout.setVerticalGroup(
            btnPerceptronLgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnPerceptronLgLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel36)
                .addContainerGap())
        );

        btnMadalineLg.setBackground(new java.awt.Color(204, 204, 204));
        btnMadalineLg.setPreferredSize(new java.awt.Dimension(120, 115));
        btnMadalineLg.setRippleColor(new java.awt.Color(51, 204, 0));
        btnMadalineLg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMadalineLgMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMadalineLgMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMadalineLgMouseExited(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 14)); // NOI18N
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/icons8_Brain_48px_5.png"))); // NOI18N
        jLabel16.setText("Madaline");
        jLabel16.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel16.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout btnMadalineLgLayout = new javax.swing.GroupLayout(btnMadalineLg);
        btnMadalineLg.setLayout(btnMadalineLgLayout);
        btnMadalineLgLayout.setHorizontalGroup(
            btnMadalineLgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnMadalineLgLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel16)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        btnMadalineLgLayout.setVerticalGroup(
            btnMadalineLgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnMadalineLgLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel16)
                .addContainerGap())
        );

        btnSimularLg.setBackground(new java.awt.Color(204, 204, 204));
        btnSimularLg.setPreferredSize(new java.awt.Dimension(160, 128));
        btnSimularLg.setRippleColor(new java.awt.Color(51, 204, 0));
        btnSimularLg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSimularLgMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSimularLgMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSimularLgMouseExited(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 12)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/icons8_System_Task_48px_2.png"))); // NOI18N
        jLabel31.setText("Simular");
        jLabel31.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel31.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel31.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout btnSimularLgLayout = new javax.swing.GroupLayout(btnSimularLg);
        btnSimularLg.setLayout(btnSimularLgLayout);
        btnSimularLgLayout.setHorizontalGroup(
            btnSimularLgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSimularLgLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel31)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        btnSimularLgLayout.setVerticalGroup(
            btnSimularLgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnSimularLgLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel31)
                .addContainerGap())
        );

        btnCrearLg.setBackground(new java.awt.Color(204, 204, 204));
        btnCrearLg.setPreferredSize(new java.awt.Dimension(120, 115));
        btnCrearLg.setRippleColor(new java.awt.Color(51, 204, 0));
        btnCrearLg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCrearLgMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCrearLgMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCrearLgMouseExited(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 12)); // NOI18N
        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/icons8_Edit_Property_48px_3.png"))); // NOI18N
        jLabel34.setText("Crear Registro");
        jLabel34.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel34.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel34.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout btnCrearLgLayout = new javax.swing.GroupLayout(btnCrearLg);
        btnCrearLg.setLayout(btnCrearLgLayout);
        btnCrearLgLayout.setHorizontalGroup(
            btnCrearLgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnCrearLgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        btnCrearLgLayout.setVerticalGroup(
            btnCrearLgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnCrearLgLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel34)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnVerRegistrosLg.setBackground(new java.awt.Color(204, 204, 204));
        btnVerRegistrosLg.setPreferredSize(new java.awt.Dimension(120, 115));
        btnVerRegistrosLg.setRippleColor(new java.awt.Color(51, 204, 0));
        btnVerRegistrosLg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVerRegistrosLgMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVerRegistrosLgMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVerRegistrosLgMouseExited(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 12)); // NOI18N
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/icons8_Search_Property_48px_2.png"))); // NOI18N
        jLabel37.setText("Ver Registro");
        jLabel37.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel37.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel37.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout btnVerRegistrosLgLayout = new javax.swing.GroupLayout(btnVerRegistrosLg);
        btnVerRegistrosLg.setLayout(btnVerRegistrosLgLayout);
        btnVerRegistrosLgLayout.setHorizontalGroup(
            btnVerRegistrosLgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnVerRegistrosLgLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel37)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        btnVerRegistrosLgLayout.setVerticalGroup(
            btnVerRegistrosLgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnVerRegistrosLgLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel37)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelTitulo.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShadowSize(10);
        dropShadowBorder1.setShowRightShadow(false);
        panelTitulo.setBorder(dropShadowBorder1);

        jPanel2.setBackground(new java.awt.Color(139, 195, 74));

        jLabel18.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/IAT.png"))); // NOI18N
        jLabel18.setText("Inteligencia Artificial");
        jLabel18.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel18.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Redes Neuronales");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/logoU.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(104, 159, 56));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(jLabel17)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(31, 31, 31))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelTituloLayout = new javax.swing.GroupLayout(panelTitulo);
        panelTitulo.setLayout(panelTituloLayout);
        panelTituloLayout.setHorizontalGroup(
            panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelTituloLayout.setVerticalGroup(
            panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        btnInicioLg1.setBackground(new java.awt.Color(204, 204, 204));
        btnInicioLg1.setPreferredSize(new java.awt.Dimension(120, 115));
        btnInicioLg1.setRippleColor(new java.awt.Color(51, 204, 0));
        btnInicioLg1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInicioLg1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnInicioLg1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnInicioLg1MouseExited(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 12)); // NOI18N
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/icons8_Satellites_48px.png"))); // NOI18N
        jLabel24.setText("BackPropagation");
        jLabel24.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel24.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel24.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout btnInicioLg1Layout = new javax.swing.GroupLayout(btnInicioLg1);
        btnInicioLg1.setLayout(btnInicioLg1Layout);
        btnInicioLg1Layout.setHorizontalGroup(
            btnInicioLg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnInicioLg1Layout.createSequentialGroup()
                .addComponent(jLabel24)
                .addGap(0, 16, Short.MAX_VALUE))
        );
        btnInicioLg1Layout.setVerticalGroup(
            btnInicioLg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnInicioLg1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel24)
                .addContainerGap())
        );

        btnPerceptronLg1.setBackground(new java.awt.Color(204, 204, 204));
        btnPerceptronLg1.setPreferredSize(new java.awt.Dimension(120, 115));
        btnPerceptronLg1.setRippleColor(new java.awt.Color(51, 204, 0));
        btnPerceptronLg1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPerceptronLg1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPerceptronLg1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPerceptronLg1MouseExited(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 12)); // NOI18N
        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/icons8_Radar_48px.png"))); // NOI18N
        jLabel38.setText("Bases Radiales");
        jLabel38.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel38.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel38.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout btnPerceptronLg1Layout = new javax.swing.GroupLayout(btnPerceptronLg1);
        btnPerceptronLg1.setLayout(btnPerceptronLg1Layout);
        btnPerceptronLg1Layout.setHorizontalGroup(
            btnPerceptronLg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPerceptronLg1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel38)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        btnPerceptronLg1Layout.setVerticalGroup(
            btnPerceptronLg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnPerceptronLg1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel38)
                .addContainerGap())
        );

        btnMadalineLg1.setBackground(new java.awt.Color(204, 204, 204));
        btnMadalineLg1.setPreferredSize(new java.awt.Dimension(120, 115));
        btnMadalineLg1.setRippleColor(new java.awt.Color(51, 204, 0));
        btnMadalineLg1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMadalineLg1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMadalineLg1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMadalineLg1MouseExited(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 12)); // NOI18N
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/icons8_Waypoint_Map_48px.png"))); // NOI18N
        jLabel19.setText("Kohonen");
        jLabel19.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel19.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel19.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout btnMadalineLg1Layout = new javax.swing.GroupLayout(btnMadalineLg1);
        btnMadalineLg1.setLayout(btnMadalineLg1Layout);
        btnMadalineLg1Layout.setHorizontalGroup(
            btnMadalineLg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnMadalineLg1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel19)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        btnMadalineLg1Layout.setVerticalGroup(
            btnMadalineLg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnMadalineLg1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel19)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelContenedorLayout = new javax.swing.GroupLayout(panelContenedor);
        panelContenedor.setLayout(panelContenedorLayout);
        panelContenedorLayout.setHorizontalGroup(
            panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelContenedorLayout.createSequentialGroup()
                .addContainerGap(132, Short.MAX_VALUE)
                .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPerceptronLg1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSimularLg, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMadalineLg1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPerceptronLg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCrearLg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMadalineLg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnInicioLg1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerRegistrosLg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 169, Short.MAX_VALUE))
        );
        panelContenedorLayout.setVerticalGroup(
            panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContenedorLayout.createSequentialGroup()
                .addComponent(panelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnPerceptronLg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMadalineLg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInicioLg1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSimularLg, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCrearLg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMadalineLg1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVerRegistrosLg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPerceptronLg1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(139, 139, 139))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    int xx, xy;
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:


    }//GEN-LAST:event_formWindowOpened

    private void btnPerceptronLgMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPerceptronLgMouseEntered
        // TODO add your handling code here:
        setColor(btnPerceptronLg);
    }//GEN-LAST:event_btnPerceptronLgMouseEntered

    private void btnPerceptronLgMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPerceptronLgMouseExited
        // TODO add your handling code here:
        resetColor(btnPerceptronLg);
    }//GEN-LAST:event_btnPerceptronLgMouseExited

    private void btnMadalineLgMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMadalineLgMouseEntered
        // TODO add your handling code here:
        setColor(btnMadalineLg);
    }//GEN-LAST:event_btnMadalineLgMouseEntered

    private void btnMadalineLgMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMadalineLgMouseExited
        // TODO add your handling code here:
        resetColor(btnMadalineLg);
    }//GEN-LAST:event_btnMadalineLgMouseExited

    private void btnSimularLgMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimularLgMouseEntered
        // TODO add your handling code here:
        setColor(btnSimularLg);
    }//GEN-LAST:event_btnSimularLgMouseEntered

    private void btnSimularLgMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimularLgMouseExited
        // TODO add your handling code here:
        resetColor(btnSimularLg);
    }//GEN-LAST:event_btnSimularLgMouseExited

    private void btnCrearLgMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCrearLgMouseEntered
        // TODO add your handling code here:
        setColor(btnCrearLg);
    }//GEN-LAST:event_btnCrearLgMouseEntered

    private void btnCrearLgMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCrearLgMouseExited
        // TODO add your handling code here:
        resetColor(btnCrearLg);
    }//GEN-LAST:event_btnCrearLgMouseExited

    private void btnVerRegistrosLgMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVerRegistrosLgMouseEntered
        // TODO add your handling code here:
        setColor(btnVerRegistrosLg);
    }//GEN-LAST:event_btnVerRegistrosLgMouseEntered

    private void btnVerRegistrosLgMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVerRegistrosLgMouseExited
        // TODO add your handling code here:
        resetColor(btnVerRegistrosLg);
    }//GEN-LAST:event_btnVerRegistrosLgMouseExited

    private void btnPerceptronLgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPerceptronLgMouseClicked
        // TODO add your handling code here:
        new FrmPerceptron().setVisible(true);
    }//GEN-LAST:event_btnPerceptronLgMouseClicked

    private void btnMadalineLgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMadalineLgMouseClicked
        // TODO add your handling code here:
        new FrmMadaline().setVisible(true);
    }//GEN-LAST:event_btnMadalineLgMouseClicked

    private void btnSimularLgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimularLgMouseClicked
        // TODO add your handling code here:
        new FrmSimular().setVisible(true);
    }//GEN-LAST:event_btnSimularLgMouseClicked

    private void btnCrearLgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCrearLgMouseClicked
        // TODO add your handling code here:
        new FrmCrearArchivo().setVisible(true);
    }//GEN-LAST:event_btnCrearLgMouseClicked

    private void btnVerRegistrosLgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVerRegistrosLgMouseClicked
        // TODO add your handling code here:
        new FrmConsultarArchivos().setVisible(true);
    }//GEN-LAST:event_btnVerRegistrosLgMouseClicked

    private void btnInicioLg1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioLg1MouseClicked
        // TODO add your handling code here:
        new FrmBackPropagation().setVisible(true);
    }//GEN-LAST:event_btnInicioLg1MouseClicked

    private void btnInicioLg1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioLg1MouseEntered
        // TODO add your handling code here:
        setColor(btnInicioLg1);
    }//GEN-LAST:event_btnInicioLg1MouseEntered

    private void btnInicioLg1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioLg1MouseExited
        // TODO add your handling code here:
        resetColor(btnInicioLg1);
    }//GEN-LAST:event_btnInicioLg1MouseExited

    private void btnPerceptronLg1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPerceptronLg1MouseClicked
        // TODO add your handling code here:
        new FrmBasesRadiales().setVisible(true);
    }//GEN-LAST:event_btnPerceptronLg1MouseClicked

    private void btnPerceptronLg1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPerceptronLg1MouseEntered
        // TODO add your handling code here:
        setColor(btnPerceptronLg1);
    }//GEN-LAST:event_btnPerceptronLg1MouseEntered

    private void btnPerceptronLg1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPerceptronLg1MouseExited
        // TODO add your handling code here:
        resetColor(btnPerceptronLg1);
    }//GEN-LAST:event_btnPerceptronLg1MouseExited

    private void btnMadalineLg1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMadalineLg1MouseClicked
        // TODO add your handling code here:
        new FrmKohonen().setVisible(true);
    }//GEN-LAST:event_btnMadalineLg1MouseClicked

    private void btnMadalineLg1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMadalineLg1MouseEntered
        // TODO add your handling code here:
        setColor(btnMadalineLg1);
    }//GEN-LAST:event_btnMadalineLg1MouseEntered

    private void btnMadalineLg1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMadalineLg1MouseExited
        // TODO add your handling code here:
        resetColor(btnMadalineLg1);
    }//GEN-LAST:event_btnMadalineLg1MouseExited
    public void setColor(JPanel panel) {
        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
    }

    public void resetColor(JPanel panel) {
        panel.setBackground(new java.awt.Color(204, 204, 204));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmInicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private MaterialSwing.MaterialButtomPanel btnCrearLg;
    private MaterialSwing.MaterialButtomPanel btnInicioLg1;
    private MaterialSwing.MaterialButtomPanel btnMadalineLg;
    private MaterialSwing.MaterialButtomPanel btnMadalineLg1;
    private MaterialSwing.MaterialButtomPanel btnPerceptronLg;
    private MaterialSwing.MaterialButtomPanel btnPerceptronLg1;
    private MaterialSwing.MaterialButtomPanel btnSimularLg;
    private MaterialSwing.MaterialButtomPanel btnVerRegistrosLg;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel panelContenedor;
    private javax.swing.JPanel panelTitulo;
    // End of variables declaration//GEN-END:variables
}