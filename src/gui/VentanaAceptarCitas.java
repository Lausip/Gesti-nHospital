package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import gui.cellRenders.CitaCellRender;
import negocio.citas.Cita;
import negocio.consulta.DataBaseConsultor;
import negocio.consulta.impl.DatabaseConsultorImpl;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaAceptarCitas extends JFrame {

	private JPanel contentPane;

	private JPanel panel;
	private JButton btnModificar;
	private JButton btnAceptar;
	private JScrollPane scrollPane;
	private JList<Cita> list;

	private DefaultListModel<Cita> modeloCitas;

	/**
	 * Create the frame.
	 */
	public VentanaAceptarCitas() {
		setTitle("Administrador: Aceptar citas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 838, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanel(), BorderLayout.SOUTH);
		contentPane.add(getScrollPane(), BorderLayout.CENTER);

		setContentPane(contentPane);

		cargarCitas();
	}

	private void cargarCitas() {
		DataBaseConsultor dbc = new DatabaseConsultorImpl();

		List<Cita> citas = dbc.findNotAproved();
		if (citas.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No hay citas sin aceptar");
			this.dispose();
		}
		for (Cita cita : citas) {
			this.modeloCitas.addElement(cita);
		}
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.add(getBtnModificar());
			panel.add(getBtnAceptar());
		}
		return panel;
	}

	private JButton getBtnModificar() {
		if (btnModificar == null) {
			btnModificar = new JButton("Modificar");
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					modificarSelectedCita();
				}
			});
		}
		return btnModificar;
	}

	protected void modificarSelectedCita() {
		Cita citaSelected = list.getSelectedValue();

		if (citaSelected != null) {
			VentanaEditarCitaAdministrador vece = new VentanaEditarCitaAdministrador(citaSelected);

			vece.setEnabled(true);
			vece.setVisible(true);
		}

	}

	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					aceptarCitaSeleccionada();
				}
			});
		}
		return btnAceptar;
	}

	protected void aceptarCitaSeleccionada() {
		Cita citaSelected = list.getSelectedValue();
		int i= list.getSelectedIndex();

		if (citaSelected != null) {
			DataBaseConsultor dbc= new DatabaseConsultorImpl();
			
			dbc.actualizarCitaAceptada(citaSelected.id_cita);
			
			modeloCitas.remove(i);
		}
	}

	

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getList());
		}
		return scrollPane;
	}

	private JList getList() {
		if (list == null) {
			list = new JList();
			modeloCitas = new DefaultListModel<Cita>();
			list.setModel(modeloCitas);
			list.setCellRenderer(new CitaCellRender());
		}
		return list;
	}

}
