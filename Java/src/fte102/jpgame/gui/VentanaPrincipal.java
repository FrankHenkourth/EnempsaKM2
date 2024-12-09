/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/Application.java to edit this template
 */
package fte102.jpgame.gui;

import fte102.jpgame.DatosFormulario;
import fte102.jpgame.Juego;
import fte102.jpgame.JuegoCiclo;
import fte102.jpgame.PanelRender;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;

public class VentanaPrincipal extends javax.swing.JFrame {

   private Juego juego;
   private JuegoCiclo juegoCiclo;

   public VentanaPrincipal() {

      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            initComponents();
            ScrollPanel1.getVerticalScrollBar().setUnitIncrement(20);
            ScrollPanel1.getHorizontalScrollBar().setUnitIncrement(20);
         }
      });
   }
   

   public void crearNuevoJuego(String nombrePartida, int dimension) {
      int anchoCelda = 22;
      int cantCeldas = 40 * dimension;

      juego = new Juego(anchoCelda, cantCeldas, infoCelda, jLabel3, nombrePartida);

      juegoCiclo = new JuegoCiclo(juego, 1500, jLabel3);

      int ancho_y_alto = juego.getAlto();

      Dimension dim = new Dimension(ancho_y_alto, ancho_y_alto);
      ContenedorJuego.setSize(ancho_y_alto, ancho_y_alto);
      ContenedorJuego.setPreferredSize(dim);
      ContenedorJuego.setMinimumSize(dim);
      ContenedorJuego.setMaximumSize(dim);

      PanelRender render = new PanelRender(juego);

      render.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            int x = e.getX() / anchoCelda;
            int y = e.getY() / anchoCelda;

            juego.deseleccionarTodo();
            juego.seleccionar(x, y);
         }
      });

      ContenedorJuego.removeAll();
      ContenedorJuego.add(render);
      setTitle("JurasiccParkGame  [" + nombrePartida + "]");
      jButton4.setText("Iniciar");
      jLabel3.setText("0");
      juego.cargaInicial();
   }

   public void finalizarJuego() {
      juegoCiclo.detener();
      juego = null;
      juegoCiclo = null;

      ContenedorJuego.removeAll();

      Dimension dim = new Dimension(400, 400);
      ContenedorJuego.setSize(400, 400);
      ContenedorJuego.setPreferredSize(dim);
      ContenedorJuego.setMinimumSize(dim);
      ContenedorJuego.setMaximumSize(dim);
      jButton4.setText("Detenido");
      jLabel3.setText("0");
   }

   @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      jPanel1 = new javax.swing.JPanel();
      jPanel2 = new javax.swing.JPanel();
      jScrollPane1 = new javax.swing.JScrollPane();
      infoCelda = new javax.swing.JTextArea();
      jLabel1 = new javax.swing.JLabel();
      jButton2 = new javax.swing.JButton();
      jButton3 = new javax.swing.JButton();
      jButton4 = new javax.swing.JButton();
      jButton5 = new javax.swing.JButton();
      jLabel2 = new javax.swing.JLabel();
      jLabel3 = new javax.swing.JLabel();
      jPanel3 = new javax.swing.JPanel();
      ScrollPanel1 = new javax.swing.JScrollPane();
      ContenedorJuego = new javax.swing.JPanel();
      menuBar = new javax.swing.JMenuBar();
      fileMenu = new javax.swing.JMenu();
      nuevo = new javax.swing.JMenuItem();
      exitMenuItem = new javax.swing.JMenuItem();
      editMenu = new javax.swing.JMenu();
      cutMenuItem = new javax.swing.JMenuItem();
      copyMenuItem = new javax.swing.JMenuItem();
      deleteMenuItem = new javax.swing.JMenuItem();
      jMenu3 = new javax.swing.JMenu();
      jMenuItem1 = new javax.swing.JMenuItem();
      jMenuItem2 = new javax.swing.JMenuItem();
      jMenuItem3 = new javax.swing.JMenuItem();
      helpMenu = new javax.swing.JMenu();
      jMenu1 = new javax.swing.JMenu();
      jMenu2 = new javax.swing.JMenu();
      jMenu4 = new javax.swing.JMenu();
      jMenuItem4 = new javax.swing.JMenuItem();
      jMenuItem5 = new javax.swing.JMenuItem();
      jMenuItem6 = new javax.swing.JMenuItem();
      jMenuItem7 = new javax.swing.JMenuItem();
      jMenuItem8 = new javax.swing.JMenuItem();
      jMenuItem9 = new javax.swing.JMenuItem();

      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
      setMaximumSize(new java.awt.Dimension(1100, 720));
      setMinimumSize(new java.awt.Dimension(1100, 720));
      setPreferredSize(new java.awt.Dimension(1100, 720));
      setResizable(false);
      setSize(new java.awt.Dimension(1100, 720));

      jScrollPane1.setBorder(null);
      jScrollPane1.setForeground(new java.awt.Color(0, 255, 51));

      infoCelda.setEditable(false);
      infoCelda.setBackground(new java.awt.Color(231, 226, 226));
      infoCelda.setColumns(20);
      infoCelda.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 14)); // NOI18N
      infoCelda.setRows(5);
      infoCelda.setTabSize(2);
      infoCelda.setBorder(null);
      infoCelda.setCaretColor(new java.awt.Color(51, 255, 204));
      infoCelda.setDisabledTextColor(new java.awt.Color(255, 153, 0));
      infoCelda.setFocusable(false);
      infoCelda.setOpaque(false);
      infoCelda.setRequestFocusEnabled(false);
      infoCelda.setSelectedTextColor(new java.awt.Color(255, 102, 102));
      infoCelda.setVerifyInputWhenFocusTarget(false);
      jScrollPane1.setViewportView(infoCelda);

      jLabel1.setBackground(new java.awt.Color(102, 51, 0));
      jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
      jLabel1.setText("Detalles de Editor");

      javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
      jPanel2.setLayout(jPanel2Layout);
      jPanel2Layout.setHorizontalGroup(
         jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(jPanel2Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
               .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
               .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
            .addContainerGap())
      );
      jPanel2Layout.setVerticalGroup(
         jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(jPanel2Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      );

      jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
      jButton2.setForeground(new java.awt.Color(0, 102, 102));
      jButton2.setText(">>");
      jButton2.setToolTipText("");
      jButton2.setAlignmentY(0.0F);
      jButton2.setMargin(new java.awt.Insets(0, 4, 2, 4));
      jButton2.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton2ActionPerformed(evt);
         }
      });

      jButton3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
      jButton3.setForeground(new java.awt.Color(0, 102, 102));
      jButton3.setText("<<");
      jButton3.setToolTipText("");
      jButton3.setAlignmentY(0.0F);
      jButton3.setMargin(new java.awt.Insets(0, 4, 2, 4));
      jButton3.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton3ActionPerformed(evt);
         }
      });

      jButton4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
      jButton4.setForeground(new java.awt.Color(0, 102, 102));
      jButton4.setText("Detenido");
      jButton4.setToolTipText("");
      jButton4.setAlignmentY(0.0F);
      jButton4.setMargin(new java.awt.Insets(0, 4, 2, 4));
      jButton4.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton4ActionPerformed(evt);
         }
      });

      jButton5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
      jButton5.setForeground(new java.awt.Color(0, 102, 102));
      jButton5.setText("Ir..");
      jButton5.setToolTipText("");
      jButton5.setAlignmentY(0.0F);
      jButton5.setMargin(new java.awt.Insets(0, 4, 2, 4));
      jButton5.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton5ActionPerformed(evt);
         }
      });

      jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
      jLabel2.setForeground(new java.awt.Color(51, 102, 0));
      jLabel2.setText("Ciclo Actual:");

      jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
      jLabel3.setForeground(new java.awt.Color(102, 0, 102));
      jLabel3.setText("0");

      javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
      jPanel1.setLayout(jPanel1Layout);
      jPanel1Layout.setHorizontalGroup(
         jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel1Layout.createSequentialGroup()
                  .addComponent(jButton3)
                  .addGap(12, 12, 12)
                  .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                  .addComponent(jButton2)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                  .addComponent(jButton5))
               .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addGroup(jPanel1Layout.createSequentialGroup()
                  .addGap(19, 19, 19)
                  .addComponent(jLabel2)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap())
      );
      jPanel1Layout.setVerticalGroup(
         jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(jButton3)
               .addComponent(jButton4)
               .addComponent(jButton2)
               .addComponent(jButton5))
            .addGap(26, 26, 26)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(jLabel2)
               .addComponent(jLabel3))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      );

      ScrollPanel1.setMaximumSize(new java.awt.Dimension(720, 520));
      ScrollPanel1.setName(""); // NOI18N
      ScrollPanel1.setPreferredSize(new java.awt.Dimension(100, 100));

      ContenedorJuego.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));
      ContenedorJuego.setMaximumSize(new java.awt.Dimension(300, 300));
      ContenedorJuego.setMinimumSize(new java.awt.Dimension(300, 300));
      ContenedorJuego.setName(""); // NOI18N
      ContenedorJuego.setPreferredSize(new java.awt.Dimension(300, 300));

      javax.swing.GroupLayout ContenedorJuegoLayout = new javax.swing.GroupLayout(ContenedorJuego);
      ContenedorJuego.setLayout(ContenedorJuegoLayout);
      ContenedorJuegoLayout.setHorizontalGroup(
         ContenedorJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGap(0, 763, Short.MAX_VALUE)
      );
      ContenedorJuegoLayout.setVerticalGroup(
         ContenedorJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGap(0, 634, Short.MAX_VALUE)
      );

      ScrollPanel1.setViewportView(ContenedorJuego);

      javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
      jPanel3.setLayout(jPanel3Layout);
      jPanel3Layout.setHorizontalGroup(
         jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ScrollPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 767, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
      );
      jPanel3Layout.setVerticalGroup(
         jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ScrollPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
      );

      fileMenu.setMnemonic('f');
      fileMenu.setText("Archivo");

      nuevo.setMnemonic('o');
      nuevo.setText("Nuevo...");
      nuevo.addMouseListener(new java.awt.event.MouseAdapter() {
         public void mouseClicked(java.awt.event.MouseEvent evt) {
            nuevoMouseClicked(evt);
         }
      });
      nuevo.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            nuevoActionPerformed(evt);
         }
      });
      fileMenu.add(nuevo);

      exitMenuItem.setMnemonic('x');
      exitMenuItem.setText("Salir");
      exitMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
         public void mouseClicked(java.awt.event.MouseEvent evt) {
            exitMenuItemMouseClicked(evt);
         }
      });
      exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            exitMenuItemActionPerformed(evt);
         }
      });
      fileMenu.add(exitMenuItem);

      menuBar.add(fileMenu);

      editMenu.setMnemonic('e');
      editMenu.setText("Ejecucion");

      cutMenuItem.setMnemonic('t');
      cutMenuItem.setText("Iniciar");
      cutMenuItem.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            cutMenuItemActionPerformed(evt);
         }
      });
      editMenu.add(cutMenuItem);

      copyMenuItem.setMnemonic('y');
      copyMenuItem.setText("Pausar");
      copyMenuItem.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            copyMenuItemActionPerformed(evt);
         }
      });
      editMenu.add(copyMenuItem);

      deleteMenuItem.setMnemonic('d');
      deleteMenuItem.setText("Finalizar");
      deleteMenuItem.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            deleteMenuItemActionPerformed(evt);
         }
      });
      editMenu.add(deleteMenuItem);

      jMenu3.setText("Linea de Tiempo");

      jMenuItem1.setText("Adelantar");
      jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem1ActionPerformed(evt);
         }
      });
      jMenu3.add(jMenuItem1);

      jMenuItem2.setText("Rebobinar");
      jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem2ActionPerformed(evt);
         }
      });
      jMenu3.add(jMenuItem2);

      jMenuItem3.setText("Seleccionar Ciclo");
      jMenuItem3.addMouseListener(new java.awt.event.MouseAdapter() {
         public void mouseClicked(java.awt.event.MouseEvent evt) {
            jMenuItem3MouseClicked(evt);
         }
      });
      jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem3ActionPerformed(evt);
         }
      });
      jMenu3.add(jMenuItem3);

      editMenu.add(jMenu3);

      menuBar.add(editMenu);

      helpMenu.setMnemonic('h');
      helpMenu.setText("Ayuda");
      helpMenu.addMouseListener(new java.awt.event.MouseAdapter() {
         public void mouseClicked(java.awt.event.MouseEvent evt) {
            helpMenuMouseClicked(evt);
         }
      });
      helpMenu.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            helpMenuActionPerformed(evt);
         }
      });
      menuBar.add(helpMenu);

      jMenu1.setText("Acerca");
      jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
         public void mouseClicked(java.awt.event.MouseEvent evt) {
            jMenu1MouseClicked(evt);
         }
      });
      menuBar.add(jMenu1);

      jMenu2.setText("Editor");
      jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
         public void mouseClicked(java.awt.event.MouseEvent evt) {
            jMenu2MouseClicked(evt);
         }
      });
      jMenu2.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenu2ActionPerformed(evt);
         }
      });
      menuBar.add(jMenu2);

      jMenu4.setText("Agregar");
      jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
         public void mouseClicked(java.awt.event.MouseEvent evt) {
            jMenu4MouseClicked(evt);
         }
      });
      jMenu4.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenu4ActionPerformed(evt);
         }
      });

      jMenuItem4.setText("Planta");
      jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem4ActionPerformed(evt);
         }
      });
      jMenu4.add(jMenuItem4);

      jMenuItem5.setText("Velociraptor");
      jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem5ActionPerformed(evt);
         }
      });
      jMenu4.add(jMenuItem5);

      jMenuItem6.setText("Tiranosaurios");
      jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem6ActionPerformed(evt);
         }
      });
      jMenu4.add(jMenuItem6);

      jMenuItem7.setText("Pterodactilos");
      jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem7ActionPerformed(evt);
         }
      });
      jMenu4.add(jMenuItem7);

      jMenuItem8.setText("Braquiosaurios");
      jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem8ActionPerformed(evt);
         }
      });
      jMenu4.add(jMenuItem8);

      jMenuItem9.setText("Triceratops");
      jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem9ActionPerformed(evt);
         }
      });
      jMenu4.add(jMenuItem9);

      menuBar.add(jMenu4);

      setJMenuBar(menuBar);

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 6, Short.MAX_VALUE))
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addGroup(layout.createSequentialGroup()
                  .addContainerGap()
                  .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(10, Short.MAX_VALUE))
      );

      pack();
   }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
       System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoActionPerformed
       DatosFormulario datos = new DatosFormulario();
       new Inicio(datos).addWindowListener(new WindowListener() {
          @Override
          public void windowOpened(WindowEvent e) {
             /**/
          }

          @Override
          public void windowClosing(WindowEvent e) {
             /**/
          }

          @Override
          public void windowClosed(WindowEvent e) {
             crearNuevoJuego(datos.getTexto(), datos.getNumero());
          }

          @Override
          public void windowIconified(WindowEvent e) {
             /**/
          }

          @Override
          public void windowDeiconified(WindowEvent e) {
             /**/
          }

          @Override
          public void windowActivated(WindowEvent e) {
             /**/
          }

          @Override
          public void windowDeactivated(WindowEvent e) {
             /**/
          }
       });
    }//GEN-LAST:event_nuevoActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       irCicloSiguiente();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
       irAlCiclo();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void exitMenuItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMenuItemMouseClicked
       System.exit(1);
    }//GEN-LAST:event_exitMenuItemMouseClicked

    private void nuevoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nuevoMouseClicked

    }//GEN-LAST:event_nuevoMouseClicked

    private void helpMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpMenuActionPerformed
       /**/
    }//GEN-LAST:event_helpMenuActionPerformed

    private void helpMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpMenuMouseClicked
       new Ayuda().setVisible(true);
    }//GEN-LAST:event_helpMenuMouseClicked

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
       new Acerca().setVisible(true);
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
    }//GEN-LAST:event_jMenu2ActionPerformed

    private void jMenuItem3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem3MouseClicked
       // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3MouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
       new Editor().setVisible(true);
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       irCicloSiguiente();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       irCicloAnterior();
    }//GEN-LAST:event_jButton3ActionPerformed

   public void irCicloAnterior() {
      if (juego == null) {
         JOptionPane.showMessageDialog(null, "El juego no se ha iniciado aun!", "Error", JOptionPane.ERROR_MESSAGE);
         return;
      }
      if (juegoCiclo != null) {
         if (!juegoCiclo.estaDetenido()) {
            juegoCiclo.detener();
            jButton4.setText("Iniciar");

         }
      } else {
         jButton4.setText("Detenido");
         jLabel3.setText("0");
      }
      juego.anterior_ciclo();

   }

   public void irCicloSiguiente() {
      if (juego == null) {
         JOptionPane.showMessageDialog(null, "El juego no se ha iniciado aun!", "Error", JOptionPane.ERROR_MESSAGE);
         return;
      }
      if (juegoCiclo != null) {
         if (!juegoCiclo.estaDetenido()) {
            juegoCiclo.detener();
            jButton4.setText("Iniciar");
         }
      } else {
         jButton4.setText("Detenido");
         jLabel3.setText("0");
      }
      juego.pasar_ciclo();
   }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       if (juegoCiclo != null) {
          if (juegoCiclo.estaDetenido()) {
             juegoCiclo.iniciar();
             jButton4.setText("Pausar");
          } else {
             juegoCiclo.detener();
             jButton4.setText("Iniciar");
          }
       } else {
          jButton4.setText("Detenido");
          jLabel3.setText("0");
       }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       irAlCiclo();
    }//GEN-LAST:event_jButton5ActionPerformed

   public void irAlCiclo() {
      if (juego == null) {
         JOptionPane.showMessageDialog(null, "El juego no se ha iniciado aun!", "Error", JOptionPane.ERROR_MESSAGE);
         return;
      }
      new SelectordeCiclos(juego).addWindowListener(new WindowListener() {
         @Override
         public void windowOpened(WindowEvent e) {
            /**/
         }

         @Override
         public void windowClosing(WindowEvent e) {
            /**/
         }

         @Override
         public void windowClosed(WindowEvent e) {
            jLabel3.setText("" + juego.getCicloActual());
         }

         @Override
         public void windowIconified(WindowEvent e) {
            /**/
         }

         @Override
         public void windowDeiconified(WindowEvent e) {
            /**/
         }

         @Override
         public void windowActivated(WindowEvent e) {
            /**/
         }

         @Override
         public void windowDeactivated(WindowEvent e) {
            /**/
         }
      });
   }

    private void cutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cutMenuItemActionPerformed
       if (juegoCiclo != null) {
          if (juegoCiclo.estaDetenido()) {
             juegoCiclo.iniciar();
             jButton4.setText("Pausar");
          }
       } else {
          jButton4.setText("Detenido");
          jLabel3.setText("0");
       }
    }//GEN-LAST:event_cutMenuItemActionPerformed

    private void copyMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyMenuItemActionPerformed
       if (juegoCiclo != null) {
          if (!juegoCiclo.estaDetenido()) {
             juegoCiclo.detener();
             jButton4.setText("Iniciar");
          }
       } else {
          jButton4.setText("Detenido");
          jLabel3.setText("0");
       }
    }//GEN-LAST:event_copyMenuItemActionPerformed

    public void addEspecie(int id) {
          juego.addCelda(id);
    }
    
    private void deleteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteMenuItemActionPerformed
       finalizarJuego();
    }//GEN-LAST:event_deleteMenuItemActionPerformed

   private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
      irCicloAnterior();
   }//GEN-LAST:event_jMenuItem2ActionPerformed

   private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
      // TODO add your handling code here:
   }//GEN-LAST:event_jMenu4MouseClicked

   private void jMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu4ActionPerformed
      // TODO add your handling code here:
   }//GEN-LAST:event_jMenu4ActionPerformed

   private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
      juego.addCelda(2);
   }//GEN-LAST:event_jMenuItem6ActionPerformed

   private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
      juego.addCelda(0);
   }//GEN-LAST:event_jMenuItem4ActionPerformed

   private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
      juego.addCelda(1);
   }//GEN-LAST:event_jMenuItem5ActionPerformed

   private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
      juego.addCelda(3);
   }//GEN-LAST:event_jMenuItem7ActionPerformed

   private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
     juego.addCelda(4);
   }//GEN-LAST:event_jMenuItem8ActionPerformed

   private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
      juego.addCelda(5);
   }//GEN-LAST:event_jMenuItem9ActionPerformed


   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JPanel ContenedorJuego;
   private javax.swing.JScrollPane ScrollPanel1;
   private javax.swing.JMenuItem copyMenuItem;
   private javax.swing.JMenuItem cutMenuItem;
   private javax.swing.JMenuItem deleteMenuItem;
   private javax.swing.JMenu editMenu;
   private javax.swing.JMenuItem exitMenuItem;
   private javax.swing.JMenu fileMenu;
   private javax.swing.JMenu helpMenu;
   private javax.swing.JTextArea infoCelda;
   private javax.swing.JButton jButton2;
   private javax.swing.JButton jButton3;
   private javax.swing.JButton jButton4;
   private javax.swing.JButton jButton5;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JLabel jLabel2;
   private javax.swing.JLabel jLabel3;
   private javax.swing.JMenu jMenu1;
   private javax.swing.JMenu jMenu2;
   private javax.swing.JMenu jMenu3;
   private javax.swing.JMenu jMenu4;
   private javax.swing.JMenuItem jMenuItem1;
   private javax.swing.JMenuItem jMenuItem2;
   private javax.swing.JMenuItem jMenuItem3;
   private javax.swing.JMenuItem jMenuItem4;
   private javax.swing.JMenuItem jMenuItem5;
   private javax.swing.JMenuItem jMenuItem6;
   private javax.swing.JMenuItem jMenuItem7;
   private javax.swing.JMenuItem jMenuItem8;
   private javax.swing.JMenuItem jMenuItem9;
   private javax.swing.JPanel jPanel1;
   private javax.swing.JPanel jPanel2;
   private javax.swing.JPanel jPanel3;
   private javax.swing.JScrollPane jScrollPane1;
   private javax.swing.JMenuBar menuBar;
   private javax.swing.JMenuItem nuevo;
   // End of variables declaration//GEN-END:variables

}
