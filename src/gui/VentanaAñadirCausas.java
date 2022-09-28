package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import negocio.citas.Cita;
import negocio.consulta.impl.DatabaseConsultorImpl;

public class VentanaA�adirCausas extends JFrame {

	private Cita cita;
	private List<String> causasA�adidas = new ArrayList<>();
	private List<String> causasBorrar = new ArrayList<>();

	private JPanel contentPane;
	private JPanel pnPrincipal;
	private JPanel pnCausasYaA�adidas;
	private JPanel pnBotones;
	private JButton btAceptar;
	private JButton btCancelar;
	private JLabel lbInstrucciones;
	private JPanel pnCb;
	private JComboBox cbCausas;
	private JPanel pbA�adirEliminar;
	private JButton btA�adir;
	private JButton btEliminar;
	private JTextPane textPane;

	/**
	 * Create the frame.
	 */
	public VentanaA�adirCausas(Cita cita) {
		setTitle("Asignar causas de la cita");
		this.cita = cita;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPnPrincipal(), BorderLayout.CENTER);
		contentPane.add(getPnCausasYaA�adidas(), BorderLayout.EAST);
		contentPane.add(getPnBotones(), BorderLayout.SOUTH);
		cargarBD();
	}

	private void cargarBD() {
		List<String> causas = DatabaseConsultorImpl.findCausasYaAsignadas(cita.id_cita);
		String cadena="";
		for (String causa:causas) {
			cadena+= causa+"\n";
		}
		textPane.setText(cadena);
	}

	private JPanel getPnPrincipal() {
		if (pnPrincipal == null) {
			pnPrincipal = new JPanel();
			pnPrincipal.setLayout(new BorderLayout(0, 0));
			pnPrincipal.add(getLbInstrucciones(), BorderLayout.NORTH);
			pnPrincipal.add(getPnCb(), BorderLayout.CENTER);
		}
		return pnPrincipal;
	}

	private JPanel getPnCausasYaA�adidas() {
		if (pnCausasYaA�adidas == null) {
			pnCausasYaA�adidas = new JPanel();
			pnCausasYaA�adidas.setLayout(new BorderLayout(0, 0));
			pnCausasYaA�adidas.add(getPbA�adirEliminar(), BorderLayout.NORTH);
			pnCausasYaA�adidas.add(getTextPane());
		}
		return pnCausasYaA�adidas;
	}

	private JPanel getPnBotones() {
		if (pnBotones == null) {
			pnBotones = new JPanel();
			pnBotones.add(getBtAceptar());
			pnBotones.add(getBtCancelar());
		}
		return pnBotones;
	}

	private JButton getBtAceptar() {
		if (btAceptar == null) {
			btAceptar = new JButton("Aceptar");
			btAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					guardarBD();
					dispose();
				}
			});
		}
		return btAceptar;
	}

	private void guardarBD() {
		DatabaseConsultorImpl.updateCausas(cita, causasA�adidas, causasBorrar);
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

	private JLabel getLbInstrucciones() {
		if (lbInstrucciones == null) {
			lbInstrucciones = new JLabel("Seleccione las causas que desee de la lista y a\u00F1\u00E1dalas.");
		}
		return lbInstrucciones;
	}

	private JPanel getPnCb() {
		if (pnCb == null) {
			pnCb = new JPanel();
			pnCb.setLayout(new BorderLayout(0, 0));
			pnCb.add(getCbCausas(), BorderLayout.NORTH);
		}
		return pnCb;
	}

	private JComboBox<String> getCbCausas() {
		if (cbCausas == null) {
			cbCausas = new JComboBox();
			cbCausas.setModel(new DefaultComboBoxModel<String>(arrayCausas()));
		}
		return cbCausas;
	}

	private String[] arrayCausas() {
		return DatabaseConsultorImpl.findCausas();
	}

	private JPanel getPbA�adirEliminar() {
		if (pbA�adirEliminar == null) {
			pbA�adirEliminar = new JPanel();
			pbA�adirEliminar.add(getBtA�adir());
			pbA�adirEliminar.add(getBtEliminar());
		}
		return pbA�adirEliminar;
	}

	private JButton getBtA�adir() {
		if (btA�adir == null) {
			btA�adir = new JButton("A\u00F1adir");
			btA�adir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!comprobarRepetido()) {
						String seleccionado = cbCausas.getSelectedItem().toString();
						textPane.setText(textPane.getText() + seleccionado + "\n");
						if (causasBorrar.contains(seleccionado)) {
							causasA�adidas.add(seleccionado);
							causasBorrar.remove(seleccionado);
						} else {
							causasA�adidas.add(seleccionado);
						}
					} else {
						advertenciaYaA�adida();
					}
				}

				
			});
		}
		return btA�adir;
	}

	private boolean comprobarRepetido() {
		String[] lineas = textPane.getText().split("\n");
		for (String linea : lineas) {
			if (linea.equals(cbCausas.getSelectedItem().toString())) {
				return true;
			}
		}
		return false;
	}

	private void advertenciaYaA�adida() {
		JOptionPane.showMessageDialog(this, "La causa ya ha sido a�adida");
	}

	private JButton getBtEliminar() {
		if (btEliminar == null) {
			btEliminar = new JButton("Eliminar");
			btEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String[] lineas = textPane.getText().split("\n");
					String texto = "";
					for (int i = 0; i < lineas.length - 1; i++) {
						texto += lineas[i] + "\n";
					}
					textPane.setText(texto);
					causasBorrar.add(lineas[lineas.length - 1]);
					causasA�adidas.remove(lineas[lineas.length - 1]);
					

				}
			});
		}
		return btEliminar;
	}

	private JTextPane getTextPane() {
		if (textPane == null) {
			textPane = new JTextPane();
		}
		return textPane;
	}
}
