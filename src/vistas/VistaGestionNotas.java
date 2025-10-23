/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vistas;

import entidades.Alumno;
import entidades.Materia;
import entidades.Inscripcion;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import persistencia.alumnoData;
import persistencia.materiaData;
import persistencia.inscripcionData;
import persistencia.miConexion;

public class VistaGestionNotas extends javax.swing.JInternalFrame {

    private miConexion conexion;
    private alumnoData alumnoData;
    private inscripcionData inscripcionData;
    private materiaData materiaData;
    private DefaultTableModel modelo = new DefaultTableModel();

    public VistaGestionNotas() {
        initComponents();
        tablaNotas.setModel(modelo);
        try {

            conexion = new miConexion("jdbc:mariadb://localhost:3306/gp10_ulp", "root", "");

            alumnoData = new alumnoData(conexion);
            inscripcionData = new inscripcionData(conexion);
            materiaData = new materiaData(conexion);

            cargarAlumnos();
            cargarMaterias();
            armarCabeceraTabla();
            cargarTablaNotas();

            tablaNotas.getColumnModel().getColumn(0).setMaxWidth(0);
            tablaNotas.getColumnModel().getColumn(0).setMinWidth(0);
            tablaNotas.getColumnModel().getColumn(0).setPreferredWidth(0);

            tablaNotas.getColumnModel().getColumn(1).setMaxWidth(0);
            tablaNotas.getColumnModel().getColumn(1).setMinWidth(0);
            tablaNotas.getColumnModel().getColumn(1).setPreferredWidth(0);

            tablaNotas.getColumnModel().getColumn(2).setMaxWidth(0);
            tablaNotas.getColumnModel().getColumn(2).setMinWidth(0);
            tablaNotas.getColumnModel().getColumn(2).setPreferredWidth(0);
        } catch (Exception e) {

            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos: " + e.getMessage());
        }

    }

    public void cargarTablaNotas() {
        armarCabeceraTabla();
        limpiarTabla();

        try {

            List<Inscripcion> listaInscripciones = inscripcionData.obtenerInscripciones();

            System.out.println("DEBUG: Se recuperaron " + listaInscripciones.size() + " inscripciones.");
            for (Inscripcion insc : listaInscripciones) {

                Alumno alumno = alumnoData.buscarAlumnoPorId(insc.getIdAlumno());
                Materia materia = materiaData.buscarMateria(insc.getIdMateria());

                Object[] fila = {
                    insc.getIdInscripto(),
                    insc.getIdAlumno(),
                    insc.getIdMateria(),
                    alumno.toString(),
                    materia.toString(),
                    insc.getNota()

                };

                modelo.addRow(fila);
            }

        } catch (Exception ex) {

            System.err.println("Error al cargar la tabla de notas: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, "Error al cargar datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void cargarAlumnos() {
        List<Alumno> alumnos = alumnoData.listarAlumnos();
        cbAlumno.removeAllItems();
        for (Alumno a : alumnos) {
            cbAlumno.addItem(a);
        }
    }

    private void cargarMaterias() {
        List<Materia> materias = materiaData.listarMaterias();
        for (Materia m : materias) {
            cbMateria.addItem(m);
        }
    }

    private void limpiarTabla() {

        if (modelo != null) {

            int filas = modelo.getRowCount();
            for (int i = filas - 1; i >= 0; i--) {
                modelo.removeRow(i);
            }
        }
    }

    private void armarCabeceraTabla() {
        if (modelo.getColumnCount() == 0) {

            // 6 Columnas en total
            modelo.addColumn("ID Insc.");
            modelo.addColumn("ID Alumno");
            modelo.addColumn("ID Materia");
            modelo.addColumn("Alumno");
            modelo.addColumn("Materia");
            modelo.addColumn("Nota");
        }

    }

    private void cargarInscripciones() {
        Alumno alumno = (Alumno) cbAlumno.getSelectedItem();
        List<Inscripcion> inscripciones = inscripcionData.obtenerInscripcionesPorAlumno(alumno.getIdAlumno());
        for (Inscripcion i : inscripciones) {
            String nombreMateria = materiaData.buscarMateria(i.getIdMateria()).getNombre();
            modelo.addRow(new Object[]{
                i.getIdAlumno(),
                nombreMateria,
                i.getNota()
            });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbAlumno = new javax.swing.JComboBox<>();
        cbMateria = new javax.swing.JComboBox<>();
        tfNota = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaNotas = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setTitle("Gestión de Notas");
        setPreferredSize(new java.awt.Dimension(600, 400));

        jLabel1.setText("Alumno:");

        jLabel2.setText("Materia:");

        jLabel3.setText("Nota:");

        cbAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAlumnoActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        tablaNotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Alumno", "Materia", "Nota"
            }
        ));
        jScrollPane1.setViewportView(tablaNotas);

        jScrollPane2.setViewportView(jScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalir))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbAlumno, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbMateria, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfNota, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnActualizar)
                    .addComponent(btnEliminar)
                    .addComponent(btnSalir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAlumnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbAlumnoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Alumno alumno = (Alumno) cbAlumno.getSelectedItem();
        Materia materia = (Materia) cbMateria.getSelectedItem();

        miConexion conexion = new miConexion("jdbc:mariadb://localhost:3306/gp10_ulp", "root", "");
        conexion.buscarConexion();
        inscripcionData insc = new inscripcionData(conexion);

        if (alumno == null || materia == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un Alumno y una Materia.");
            return;
        }

        try {
            if (insc.existeInscripcion(alumno.getIdAlumno(), materia.getIdMateria())) {
                JOptionPane.showMessageDialog(this, "Ya hay una nota cargada para el alumno");
            } else {         
                double nota = Double.parseDouble(tfNota.getText());
                inscripcionData.guardarInscripcion(new Inscripcion(nota, alumno.getIdAlumno(), materia.getIdMateria()));
                JOptionPane.showMessageDialog(this, "Nota guardada correctamente");
                tfNota.setText("");
                limpiarTabla();
                cargarTablaNotas();
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese una nota válida (número)");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed

        try {
            // Obtener alumno y materia seleccionados
            int idAlumno = ((Alumno) cbAlumno.getSelectedItem()).getIdAlumno();
            int idMateria = ((Materia) cbMateria.getSelectedItem()).getIdMateria();

            // Obtener y validar la nota
            String notaStr = tfNota.getText();
            double nota = Double.parseDouble(notaStr);

            // Actualizar en la base de datos
            inscripcionData.actualizarNota(nota, idAlumno, idMateria);

            JOptionPane.showMessageDialog(this, "Nota actualizada correctamente.");
            limpiarTabla();
            cargarTablaNotas();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "La nota ingresada no es un número válido.",
                    "Error de formato",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Ocurrió un error al actualizar la nota: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = tablaNotas.getSelectedRow();
        if (fila != -1) {
            try {

                String idAlumnoStr = modelo.getValueAt(fila, 1).toString();
                int idAlumno = Integer.parseInt(idAlumnoStr);

                String idMateriaStr = modelo.getValueAt(fila, 2).toString();
                int idMateria = Integer.parseInt(idMateriaStr);

                inscripcionData.borrarInscripcion(idAlumno, idMateria);

                JOptionPane.showMessageDialog(this, "Inscripción eliminada correctamente.");
                limpiarTabla();
                cargarTablaNotas();

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error al obtener un ID de la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar la inscripción: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una fila de la tabla.");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<entidades.Alumno> cbAlumno;
    private javax.swing.JComboBox<entidades.Materia> cbMateria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaNotas;
    private javax.swing.JTextField tfNota;
    // End of variables declaration//GEN-END:variables
}
