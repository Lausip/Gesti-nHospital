package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import negocio.citas.Cita;
import negocio.consulta.impl.DatabaseConsultorImpl;

public class VentanaPrescripcion extends JFrame {

	private Cita cita;
	private boolean medicamento;
	private List<String> prescripcionesA�adidas = new ArrayList<>();
	private List<String[]> medicamentosA�adidos = new ArrayList<>();
	private List<String> prescripcionesABorrar = new ArrayList<>();
	private List<String[]> medicamentosABorrar = new ArrayList<>();

	private JPanel contentPane;
	private JPanel pnComboBox;
	private JPanel pnA�adido;
	private JPanel pnBotones;
	private JButton btA�adir;
	private JButton btEliminar;
	private JPanel pnYaA�adido;
	private JLabel lbInfo;
	private JPanel pnCb;
	private JLabel lbSeleccione;
	private JPanel pnComponentes;
	private JPanel pnPrescrpOMedicamento;
	private JCheckBox chbxMedicamento_1;
	private JPanel pnOtros;
	private JPanel pnInfo;
	private JPanel pnCantidad;
	private JLabel lbCantidad;
	private JSpinner spCantidad;
	private JPanel pnIntervalo;
	private JLabel lbIntervalo;
	private JTextField textField;
	private JPanel pnDuracion;
	private JLabel lbDuracion;
	private JSpinner spDuracion;
	private JPanel pnOtraInfo;
	private JLabel lbOtraInfo;
	private JTextField textField_1;
	private JTextPane txtPrescripcionesA�adidas;
	private JPanel pnGuardarCancelar;
	private JButton btAceptar;
	private JButton btCancelar;
	private JPanel pnCbBtn;
	private JComboBox<String> cbPrescripciones;
	private JButton btA�adirNuevaPrescrp;

	/**
	 * Create the frame.
	 */
	public VentanaPrescripcion(Cita cita) {
		setTitle("Asignar prescripciones a una cita");
		this.cita = cita;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 639, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPnComboBox(), BorderLayout.CENTER);
		contentPane.add(getPnA�adido(), BorderLayout.EAST);
		contentPane.add(getPnGuardarCancelar(), BorderLayout.SOUTH);
		cargarBD();
	}

	private void cargarBD() {
		List<String> prescripciones = DatabaseConsultorImpl.cargarPrescripciones(cita.id_cita);
		String cadena = "";
		for (String s : prescripciones) {
			//prescripcionesA�adidas.add(s);
			cadena += s + "\n";
		}
		
		List<String[]> medicamentos = DatabaseConsultorImpl.cargarMedicamentos(cita.id_cita);
		for (String[] s : medicamentos) {
			//medicamentosA�adidos.add(s);
			cadena += s[0] + "-" + s[1] + "-"
					+ s[2] + "-" + s[3] + "-"
					+ s[4] + "\n";
		}
		txtPrescripcionesA�adidas.setText(cadena);
	}

	private JPanel getPnComboBox() {
		if (pnComboBox == null) {
			pnComboBox = new JPanel();
			pnComboBox.setLayout(new BorderLayout(0, 0));
			pnComboBox.add(getPnCb(), BorderLayout.CENTER);
		}
		return pnComboBox;
	}

	private JPanel getPnA�adido() {
		if (pnA�adido == null) {
			pnA�adido = new JPanel();
			pnA�adido.setLayout(new BorderLayout(0, 0));
			pnA�adido.add(getPnBotones(), BorderLayout.NORTH);
			pnA�adido.add(getPnYaA�adido());
		}
		return pnA�adido;
	}

	private JPanel getPnBotones() {
		if (pnBotones == null) {
			pnBotones = new JPanel();
			pnBotones.add(getBtA�adir());
			pnBotones.add(getBtEliminar());
		}
		return pnBotones;
	}

	private JButton getBtA�adir() {
		if (btA�adir == null) {
			btA�adir = new JButton("A\u00F1adir");
			btA�adir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String seleccionada = (String) cbPrescripciones.getSelectedItem();
					if (!comprobarRepetida()) {
						// ahora comprobamos que sea un medicamento o una prescripcion como tal
						if (!medicamento) {
							txtPrescripcionesA�adidas
									.setText(txtPrescripcionesA�adidas.getText() + seleccionada + "\n");
							if (prescripcionesABorrar.contains(seleccionada)) {
								prescripcionesA�adidas.add(seleccionada);
								prescripcionesABorrar.remove(seleccionada);
							} else {
								prescripcionesA�adidas.add(seleccionada);
							}

						} else {
							String cadena = seleccionada + "-" + spCantidad.getValue().toString() + "-"
									+ textField.getText() + "-" + spDuracion.getValue().toString() + "-"
									+ textField_1.getText();
							txtPrescripcionesA�adidas.setText(txtPrescripcionesA�adidas.getText() + cadena + "\n");
							String[] med = new String[5];
							med[0] = seleccionada;
							med[1] = spCantidad.getValue().toString();
							med[2] = textField.getText();
							med[3] = spDuracion.getValue().toString();
							med[4] = textField_1.getText();
							if (medicamentosABorrar.contains(med)) {

								medicamentosA�adidos.add(med);
								medicamentosABorrar.remove(med);
							} else {
								medicamentosA�adidos.add(med);
							}

						}

					} else {
						advertenciaYaA�adido();
					}

				}

			});
		}
		return btA�adir;
	}

	private boolean comprobarRepetida() {
		String[] lineas = txtPrescripcionesA�adidas.getText().split("\n");
		String seleccionada = (String) cbPrescripciones.getSelectedItem();
		for (int i = 0; i < lineas.length; i++) {
			if (lineas[i].split("-")[0].equals(seleccionada)) {
				return true;
			}
		}
		return false;
	}

	private void advertenciaYaA�adido() {
		JOptionPane.showMessageDialog(this, "Esta prescripci�n ya est� en la lista");
	}

	private JButton getBtEliminar() {
		if (btEliminar == null) {
			btEliminar = new JButton("Eliminar");
			btEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String[] lineas = txtPrescripcionesA�adidas.getText().split("\n");
					String texto = "";
					for (int i = 0; i < lineas.length - 1; i++) {
						texto += lineas[i] + "\n";
					}
					txtPrescripcionesA�adidas.setText(texto);
					if (!lineas[lineas.length - 1].contains("-")) {
						prescripcionesABorrar.add(lineas[lineas.length - 1]);
						prescripcionesA�adidas.remove(lineas[lineas.length - 1]);
					} else {
						String[] partes = lineas[lineas.length - 1].split("-");
						String[] med =new String[5];
						med[0] = partes[0];
						med[1] = partes[1];
						med[2] = partes[2];
						med[3] = partes[3];
						med[4] = partes[4];
						medicamentosABorrar.add(med);
						medicamentosA�adidos.remove(med);
					}

				}
			});
		}
		return btEliminar;
	}

	private JPanel getPnYaA�adido() {
		if (pnYaA�adido == null) {
			pnYaA�adido = new JPanel();
			pnYaA�adido.setLayout(new BorderLayout(0, 0));
			pnYaA�adido.add(getLbInfo(), BorderLayout.NORTH);
			pnYaA�adido.add(getTxtPrescripcionesA�adidas(), BorderLayout.CENTER);
		}
		return pnYaA�adido;
	}

	private JLabel getLbInfo() {
		if (lbInfo == null) {
			lbInfo = new JLabel("Las prescripciones a\u00F1adidas se mostrar\u00E1n aqu\u00ED.");
		}
		return lbInfo;
	}

	private JPanel getPnCb() {
		if (pnCb == null) {
			pnCb = new JPanel();
			pnCb.setLayout(new BorderLayout(0, 0));
			pnCb.add(getLbSeleccione(), BorderLayout.NORTH);
			pnCb.add(getPnComponentes());
		}
		return pnCb;
	}

	private String[] arrayPrescrp() {
		return DatabaseConsultorImpl.findPrescripciones();
	}

	private String[] arrayMedicamentos() {
		return DatabaseConsultorImpl.findMedicamentos();

	}

	private JLabel getLbSeleccione() {
		if (lbSeleccione == null) {
			lbSeleccione = new JLabel("Seleccione una prescripci\u00F3n de la lista");
		}
		return lbSeleccione;
	}

	private void cargarCbMedicamentos() {
		cbPrescripciones.setModel(new DefaultComboBoxModel<String>(arrayMedicamentos()));
	}

	private void cargarCbPrescp() {
		cbPrescripciones.setModel(new DefaultComboBoxModel<String>(arrayPrescrp()));
	}

	private JPanel getPnComponentes() {
		if (pnComponentes == null) {
			pnComponentes = new JPanel();
			pnComponentes.setLayout(new BorderLayout(0, 0));
			pnComponentes.add(getPnPrescrpOMedicamento_1_1(), BorderLayout.NORTH);
			pnComponentes.add(getPnOtros(), BorderLayout.CENTER);
		}
		return pnComponentes;
	}

	private JPanel getPnPrescrpOMedicamento_1_1() {
		if (pnPrescrpOMedicamento == null) {
			pnPrescrpOMedicamento = new JPanel();
			pnPrescrpOMedicamento.add(getChbxMedicamento_1_1());
		}
		return pnPrescrpOMedicamento;
	}

	private JCheckBox getChbxMedicamento_1_1() {
		if (chbxMedicamento_1 == null) {
			chbxMedicamento_1 = new JCheckBox("Medicamento");
			chbxMedicamento_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (chbxMedicamento_1.isSelected()) {
						textField.setEnabled(true);
						spCantidad.setEnabled(true);
						textField_1.setEnabled(true);
						spDuracion.setEnabled(true);
						cargarCbMedicamentos();
						medicamento = true;
					} else {
						textField.setEnabled(false);
						spCantidad.setEnabled(false);
						textField_1.setEnabled(false);
						spDuracion.setEnabled(false);
						cargarCbPrescp();
						medicamento = false;
					}
				}
			});
		}
		return chbxMedicamento_1;
	}

	private JPanel getPnOtros() {
		if (pnOtros == null) {
			pnOtros = new JPanel();
			pnOtros.setLayout(new BorderLayout(0, 0));
			pnOtros.add(getPnInfo_1(), BorderLayout.CENTER);
			pnOtros.add(getPnCbBtn(), BorderLayout.NORTH);
		}
		return pnOtros;
	}

	private JPanel getPnInfo_1() {
		if (pnInfo == null) {
			pnInfo = new JPanel();
			pnInfo.setLayout(new GridLayout(4, 1, 2, 2));
			pnInfo.add(getPnCantidad_1());
			pnInfo.add(getPnIntervalo_1());
			pnInfo.add(getPnDuracion_1());
			pnInfo.add(getPnOtraInfo_1());
		}
		return pnInfo;
	}

	private JPanel getPnCantidad_1() {
		if (pnCantidad == null) {
			pnCantidad = new JPanel();
			pnCantidad.add(getLbCantidad_1());
			pnCantidad.add(getSpCantidad_1());
		}
		return pnCantidad;
	}

	private JLabel getLbCantidad_1() {
		if (lbCantidad == null) {
			lbCantidad = new JLabel("Cantidad");
		}
		return lbCantidad;
	}

	private JSpinner getSpCantidad_1() {
		if (spCantidad == null) {
			spCantidad = new JSpinner();
			spCantidad.setEnabled(false);
		}
		return spCantidad;
	}

	private JPanel getPnIntervalo_1() {
		if (pnIntervalo == null) {
			pnIntervalo = new JPanel();
			pnIntervalo.add(getLbIntervalo_1());
			pnIntervalo.add(getTextField_2());
		}
		return pnIntervalo;
	}

	private JLabel getLbIntervalo_1() {
		if (lbIntervalo == null) {
			lbIntervalo = new JLabel("Intervalo entre tomas");
		}
		return lbIntervalo;
	}

	private JTextField getTextField_2() {
		if (textField == null) {
			textField = new JTextField();
			textField.setEnabled(false);
			textField.setColumns(10);
		}
		return textField;
	}

	private JPanel getPnDuracion_1() {
		if (pnDuracion == null) {
			pnDuracion = new JPanel();
			pnDuracion.add(getLbDuracion_1());
			pnDuracion.add(getSpDuracion_1());
		}
		return pnDuracion;
	}

	private JLabel getLbDuracion_1() {
		if (lbDuracion == null) {
			lbDuracion = new JLabel("Duraci\u00F3n de la toma (d\u00EDas):");
		}
		return lbDuracion;
	}

	private JSpinner getSpDuracion_1() {
		if (spDuracion == null) {
			spDuracion = new JSpinner();
			spDuracion.setEnabled(false);
		}
		return spDuracion;
	}

	private JPanel getPnOtraInfo_1() {
		if (pnOtraInfo == null) {
			pnOtraInfo = new JPanel();
			pnOtraInfo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnOtraInfo.add(getLbOtraInfo_1());
			pnOtraInfo.add(getTextField_1_1());
		}
		return pnOtraInfo;
	}

	private JLabel getLbOtraInfo_1() {
		if (lbOtraInfo == null) {
			lbOtraInfo = new JLabel("Otra informaci\u00F3n:");
		}
		return lbOtraInfo;
	}

	private JTextField getTextField_1_1() {
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.setEnabled(false);
			textField_1.setColumns(10);
		}
		return textField_1;
	}

	private JTextPane getTxtPrescripcionesA�adidas() {
		if (txtPrescripcionesA�adidas == null) {
			txtPrescripcionesA�adidas = new JTextPane();
			txtPrescripcionesA�adidas.setEditable(false);
		}
		return txtPrescripcionesA�adidas;
	}

	private JPanel getPnGuardarCancelar() {
		if (pnGuardarCancelar == null) {
			pnGuardarCancelar = new JPanel();
			pnGuardarCancelar.add(getBtAceptar());
			pnGuardarCancelar.add(getBtCancelar());
		}
		return pnGuardarCancelar;
	}

	private JButton getBtAceptar() {
		if (btAceptar == null) {
			btAceptar = new JButton("Aceptar");
			btAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					guardarBaseDeDatos();
					dispose();
				}
			});
		}
		return btAceptar;
	}

	private void guardarBaseDeDatos() {
		DatabaseConsultorImpl.actualizarPrescripciones(cita, prescripcionesA�adidas, prescripcionesABorrar);
		DatabaseConsultorImpl.actualizarMedicamentos(cita, medicamentosA�adidos, medicamentosABorrar);
	}

	private JButton getBtCancelar() {
		if (btCancelar == null) {
			btCancelar = new JButton("Cancelar");
			btCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return btCancelar;
	}

	private JPanel getPnCbBtn() {
		if (pnCbBtn == null) {
			pnCbBtn = new JPanel();
			pnCbBtn.setLayout(new BorderLayout(0, 0));
			pnCbBtn.add(getCbPrescripciones_1(), BorderLayout.CENTER);
			pnCbBtn.add(getBtA�adirNuevaPrescrp(), BorderLayout.EAST);
		}
		return pnCbBtn;
	}

	private JComboBox<String> getCbPrescripciones_1() {
		if (cbPrescripciones == null) {
			cbPrescripciones = new JComboBox<String>();
			cbPrescripciones.setModel(new DefaultComboBoxModel<String>(arrayPrescrp()));
		}
		return cbPrescripciones;
	}

	private JButton getBtA�adirNuevaPrescrp() {
		if (btA�adirNuevaPrescrp == null) {
			btA�adirNuevaPrescrp = new JButton("+");
			btA�adirNuevaPrescrp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!medicamento) {
						ventanaA�adirNuevaPrescripcion();
					} else {
						ventanaA�adirNuevoMedicamento();
					}
				}
			});

		}
		return btA�adirNuevaPrescrp;
	}

	private void ventanaA�adirNuevaPrescripcion() {
		String nueva = JOptionPane.showInputDialog(this, "Introduzca la nueva prescripci�n",
				"Introduzca la nueva prescripci�n");
		DatabaseConsultorImpl.guardarNuevaPrescripcion(nueva);
		cargarCbPrescp();
	}

	private void ventanaA�adirNuevoMedicamento() {
		String nueva = JOptionPane.showInputDialog(this, "Introduzca el nuevo medicamento",
				"Introduzca el nuevo medicamento");
		DatabaseConsultorImpl.guardarNuevoMedicamento(nueva);
		cargarCbMedicamentos();
	}
}
