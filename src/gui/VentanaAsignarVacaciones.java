package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import negocio.consulta.impl.DatabaseConsultorImpl;
import negocio.sanitario.Enfermero;
import negocio.sanitario.Medico;
import negocio.sanitario.Sanitario;

public class VentanaAsignarVacaciones extends JFrame {

	private JPanel contentPane;
	private JPanel pnGeneral;
	private JPanel pnTitulo;
	private JLabel lbAsignar;
	private JPanel pnPrincipal;
	private JPanel pnMedicoOEnfermero;
	private JRadioButton rdbtEnfermero;
	private JRadioButton rdbtMedico;
	private JPanel pnSeleccion;
	private JPanel pnBotones;
	private JLabel lbTexto;
	private JComboBox cbMedicosYEnfermeros;
	private JButton btAceptar;
	private JButton btCancelar;

	/**
	 * Create the frame.
	 */
	public VentanaAsignarVacaciones() {
		setTitle("Asignar vacaciones");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 438, 292);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPnGeneral(), BorderLayout.CENTER);
	}

	private JPanel getPnGeneral() {
		if (pnGeneral == null) {
			pnGeneral = new JPanel();
			pnGeneral.setBackground(Color.WHITE);
			pnGeneral.setLayout(new BorderLayout(0, 0));
			pnGeneral.add(getPnTitulo(), BorderLayout.NORTH);
			pnGeneral.add(getPnPrincipal(), BorderLayout.CENTER);
			pnGeneral.add(getPnBotones(), BorderLayout.SOUTH);
		}
		return pnGeneral;
	}

	private JPanel getPnTitulo() {
		if (pnTitulo == null) {
			pnTitulo = new JPanel();
			pnTitulo.setBackground(Color.WHITE);
			pnTitulo.add(getLbAsignar());
		}
		return pnTitulo;
	}

	private JLabel getLbAsignar() {
		if (lbAsignar == null) {
			lbAsignar = new JLabel("Asignar vacaciones a:");
		}
		return lbAsignar;
	}

	private JPanel getPnPrincipal() {
		if (pnPrincipal == null) {
			pnPrincipal = new JPanel();
			pnPrincipal.setBackground(Color.WHITE);
			pnPrincipal.setLayout(new BorderLayout(0, 0));
			pnPrincipal.add(getPnMedicoOEnfermero(), BorderLayout.NORTH);
			pnPrincipal.add(getPnSeleccion(), BorderLayout.CENTER);
		}
		return pnPrincipal;
	}

	private JPanel getPnMedicoOEnfermero() {
		if (pnMedicoOEnfermero == null) {
			pnMedicoOEnfermero = new JPanel();
			pnMedicoOEnfermero.setBackground(Color.WHITE);
			pnMedicoOEnfermero.add(getRdbtMedico());
			pnMedicoOEnfermero.add(getRdbtEnfermero());
		}
		return pnMedicoOEnfermero;
	}

	private JRadioButton getRdbtEnfermero() {
		if (rdbtEnfermero == null) {
			rdbtEnfermero = new JRadioButton("Enfermero");
			rdbtEnfermero.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (rdbtEnfermero.isSelected()) {
						rdbtMedico.setSelected(false);
						cbMedicosYEnfermeros.setModel(new DefaultComboBoxModel<String>(listaEnfermeros()));
					}
				}
			});
			rdbtEnfermero.setBackground(Color.WHITE);
		}
		return rdbtEnfermero;
	}

	private JRadioButton getRdbtMedico() {
		if (rdbtMedico == null) {
			rdbtMedico = new JRadioButton("M\u00E9dico");
			rdbtMedico.setBackground(Color.WHITE);
			rdbtMedico.setSelected(true);
			rdbtMedico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (rdbtMedico.isSelected()) {
						rdbtEnfermero.setSelected(false);
						cbMedicosYEnfermeros.setModel(new DefaultComboBoxModel<String>(listaMedicos()));
					}
				}
			});
		}
		return rdbtMedico;
	}

	private JPanel getPnSeleccion() {
		if (pnSeleccion == null) {
			pnSeleccion = new JPanel();
			pnSeleccion.setBackground(Color.WHITE);
			pnSeleccion.setLayout(new BorderLayout(0, 0));
			pnSeleccion.add(getLbTexto(), BorderLayout.CENTER);
			pnSeleccion.add(getCbMedicosYEnfermeros(), BorderLayout.SOUTH);
		}
		return pnSeleccion;
	}

	private JPanel getPnBotones() {
		if (pnBotones == null) {
			pnBotones = new JPanel();
			pnBotones.setBackground(Color.WHITE);
			pnBotones.add(getBtAceptar());
			pnBotones.add(getBtCancelar());
		}
		return pnBotones;
	}

	private JLabel getLbTexto() {
		if (lbTexto == null) {
			lbTexto = new JLabel("Seleccione un sanitario para asignarle sus d\u00EDas de vacaciones");
			lbTexto.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbTexto;
	}

	private JComboBox<String> getCbMedicosYEnfermeros() {
		if (cbMedicosYEnfermeros == null) {
			cbMedicosYEnfermeros = new JComboBox<String>();
			cbMedicosYEnfermeros.setModel(new DefaultComboBoxModel<String>(listaMedicos()));
		}
		return cbMedicosYEnfermeros;
	}

	private String[] listaMedicos() {
		List<Medico> lista = DatabaseConsultorImpl.todoslosMedicos();
		String[] medicos = new String[lista.size()];
		int i = 0;
		for (Medico m : lista) {
			medicos[i] = m.toString();
			i++;
		}

		return medicos;
	}

	private String[] listaEnfermeros() {
		List<Enfermero> lista = DatabaseConsultorImpl.TodoslosEnfermeros();
		String[] enfermeros = new String[lista.size()];
		int i = 0;
		for (Enfermero m : lista) {
			enfermeros[i] = m.toString();
			i++;
		}

		return enfermeros;
	}

	private JButton getBtAceptar() {
		if (btAceptar == null) {
			btAceptar = new JButton("Aceptar");
			btAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Sanitario sanitario;
					if (rdbtEnfermero.isSelected()) {
						sanitario = DatabaseConsultorImpl.TodoslosEnfermeros()
								.get(cbMedicosYEnfermeros.getSelectedIndex());
					} else {
						sanitario = DatabaseConsultorImpl.todoslosMedicos()
								.get(cbMedicosYEnfermeros.getSelectedIndex());
					}
					VentanaPrincipalAsignarVacaciones p = new VentanaPrincipalAsignarVacaciones(sanitario);
					p.setVisible(true);
				}
			});
			btAceptar.setForeground(Color.WHITE);
			btAceptar.setBackground(Color.GREEN);
		}
		return btAceptar;
	}

	private JButton getBtCancelar() {
		if (btCancelar == null) {
			btCancelar = new JButton("Cancelar");
			btCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btCancelar.setBackground(Color.RED);
			btCancelar.setForeground(Color.WHITE);
		}
		return btCancelar;
	}
}
