package supergiros.view;

import supergiros.controller.ctlUsuario;
import supergiros.model.mdlUsuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class verUsuarios {
    public DefaultTableModel mdlTabla;
    public JTable tblUsuarios;

    JButton btnCrear = new JButton("Crear");
    JButton btnModificar = new JButton("Modificar");
    JButton btnEliminar = new JButton("Eliminar");

    JTextField txtNombre = new JTextField(10);
    JTextField txtApellido = new JTextField(10);
    JTextField txtEdad = new JTextField(2);

    ctlUsuario controller = new ctlUsuario();

    public void Interfaz() {
        //CREAR Y LLENAR TABLA
        mdlTabla = new DefaultTableModel();
        mdlTabla.addColumn("Nombre");
        mdlTabla.addColumn("Apellido");
        mdlTabla.addColumn("Edad");

        llenarTabla();
        tblUsuarios = new JTable(mdlTabla);

        //BOTONES
        JPanel pnlBotones = new JPanel(new FlowLayout());
        pnlBotones.add(btnCrear);
        pnlBotones.add(btnModificar);
        pnlBotones.add(btnEliminar);

        //INTERFAZ MODIFICAR
        JPanel pnlModificar = new JPanel();
        pnlModificar.add(new JLabel("Nombre: ") );
        pnlModificar.add(txtNombre);
        pnlModificar.add(new JLabel("Apellido: ") );
        pnlModificar.add(txtApellido);
        pnlModificar.add(new JLabel("Edad: ") );
        pnlModificar.add(txtEdad);

        //PANEL DE CONTROL PRINCIPAL
        JPanel pnlPrincipal = new JPanel(new BorderLayout());
        pnlPrincipal.add(new JScrollPane(tblUsuarios), BorderLayout.CENTER);
        pnlPrincipal.add(pnlModificar, BorderLayout.NORTH);
        pnlPrincipal.add(pnlBotones, BorderLayout.SOUTH);

        JFrame frame = new JFrame("Tabla de Personas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(pnlPrincipal);
        frame.setSize(600, 400);
        pnlModificar.setVisible(false);
        frame.setVisible(true);

        //Eventos de boton y mouse
        btnCrear.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                String nombre = JOptionPane.showInputDialog("Nombre:");
                String apellido = JOptionPane.showInputDialog("Apellido:");
                String edad = JOptionPane.showInputDialog("Edad:");
                controller.crearUsuario(nombre, apellido, Integer.parseInt(edad));
                int filas = tblUsuarios.getRowCount();
                for (int i = filas - 1; i >= 0; i--) {
                    mdlTabla.removeRow(i);
                }
                llenarTabla();
            }
        });


        tblUsuarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                pnlModificar.setVisible(true);
                String nombre = (String) tblUsuarios.getValueAt(tblUsuarios.getSelectedRow(), 0);
                String apellido = (String) tblUsuarios.getValueAt(tblUsuarios.getSelectedRow(), 1);
                Integer edad= (Integer) tblUsuarios.getValueAt(tblUsuarios.getSelectedRow(), 2);
                txtNombre.setText(nombre);
                txtApellido.setText(apellido);
                txtEdad.setText(edad.toString());
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.eliminarUsuario(consultarId().getId());
                int filas = tblUsuarios.getRowCount();
                for (int i = filas - 1; i >= 0; i--) {
                    mdlTabla.removeRow(i);
                }
                llenarTabla();
            }
        });

        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.modificarUsuario(consultarId().getId(), txtNombre.getText(), txtApellido.getText(), Integer.parseInt(txtEdad.getText()));
                int filas = tblUsuarios.getRowCount();
                for (int i = filas - 1; i >= 0; i--) {
                    mdlTabla.removeRow(i);
                }
                llenarTabla();
            }
        });
    }

    //Metodo para rellenar o cargar tabla
    public void llenarTabla(){
        ArrayList <mdlUsuario> resultadosConsulta = controller.consultar();
        for (int i = 0; i < resultadosConsulta.size(); i++) {
            mdlTabla.addRow(new Object[] { resultadosConsulta.get(i).getNombre(), resultadosConsulta.get(i).getApellido(), resultadosConsulta.get(i).getEdad()});
        }
    }

    //Metodo que extrae la informacion de la Jtable del usuario y la compara en la BD para traer ID.
    public mdlUsuario consultarId(){
        String nombre = (String) tblUsuarios.getValueAt(tblUsuarios.getSelectedRow(), 0);
        String apellido = (String) tblUsuarios.getValueAt(tblUsuarios.getSelectedRow(), 1);
        Integer edad= (Integer) tblUsuarios.getValueAt(tblUsuarios.getSelectedRow(), 2);
        mdlUsuario usuario = controller.consultaId(nombre, apellido, edad);
        return usuario;
    }
}
