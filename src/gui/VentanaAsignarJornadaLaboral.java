package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import negocio.consulta.impl.DatabaseConsultorImpl;
import negocio.sanitario.Enfermero;
import negocio.sanitario.Medico;
import negocio.sanitario.Sanitario;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class VentanaAsignarJornadaLaboral extends JFrame {

	private JPanel contentPane;
	private JPanel pnGeneral;
	private JPanel pnTitulo;
	private JLabel lblAsignarJornadaLaboral;
	private JPanel pnPrincipal;
	private JPanel pnMedicoOEnfermero;
	private JRadioButton rdbtMedico;
	private JRadioButton rdbtEnfermero;
	private JPanel pnSeleccion;
	private JLabel lblSeleccioneUnSanitario;
	private JComboBox<String> cbMedicosYEnfermeros;
	private JPanel pnBotones;
	private JButton btAceptar;
	private JButton btCancelar;


	/**
	 * Create the frame.
	 */
	public VentanaAsignarJornadaLaboral() {
		setTitle("Asignar jornadas laborales");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnGeneral());
	}

	private JPanel getPnGeneral() {
		if (pnGeneral == null) {
			pnGeneral = new JPanel();
			pnGeneral.setBackground(Color.WHITE);
			pnGeneral.setLayout(new BorderLayout(0, 0));
			pnGeneral.add(getPnTitulo(), BorderLayout.NORTH);
			pnGeneral.add(getPnPrincipal(), BorderLayout.CENTER);
		}
		return pnGeneral;
	}

	private JPanel getPnTitulo() {
		if (pnTitulo == null) {
			pnTitulo = new JPanel();
			pnTitulo.setBackground(Color.WHITE);
			pnTitulo.add(getLblAsignarJornadaLaboral());
		}
		return pnTitulo;
	}

	private JLabel getLblAsignarJornadaLaboral() {
		if (lblAsignarJornadaLaboral == null) {
			lblAsignarJornadaLaboral = new JLabel("Asignar jornada laboral a:");
		}
		return lblAsignarJornadaLaboral;
	}

	private JPanel getPnPrincipal() {
		if (pnPrincipal == null) {
			pnPrincipal = new JPanel();
			pnPrincipal.setBackground(Color.WHITE);
			pnPrincipal.setLayout(new BorderLayout(0, 0));
			pnPrincipal.add(getPnMedicoOEnfermero(), BorderLayout.NORTH);
			pnPrincipal.add(getPnSeleccion(), BorderLayout.CENTER);
			pnPrincipal.add(getPnBotones(), BorderLayout.SOUTH);
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

	private JRadioButton getRdbtMedico() {
		if (rdbtMedico == null) {
			rdbtMedico = new JRadioButton("M\u00E9dico");
			rdbtMedico.setSelected(true);
			rdbtMedico.setBackground(Color.WHITE);
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

	private JRadioButton getRdbtEnfermero() {
		if (rdbtEnfermero == null) {
			rdbtEnfermero = new JRadioButton("Enfermero");
			rdbtEnfermero.setBackground(Color.WHITE);
			rdbtEnfermero.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (rdbtEnfermero.isSelected()) {
						rdbtMedico.setSelected(false);
						cbMedicosYEnfermeros.setModel(new DefaultComboBoxModel<String>(listaEnfermeros()));
					}
				}
			});
		}
		return rdbtEnfermero;
	}
	
	private String[] listaMedicos() {
		List<Medico> lista = DatabaseConsultorImpl.todoslosMedicos();
		String[] medicos = new String[lista.size()];
		int i=0;
		for (Medico m:lista) {
			medicos[i]=m.toString();
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

	private JPanel getPnSeleccion() {
		if (pnSeleccion == null) {
			pnSeleccion = new JPanel();
			pnSeleccion.setBackground(Color.WHITE);
			pnSeleccion.setLayout(new BorderLayout(0, 0));
			pnSeleccion.add(getLblSeleccioneUnSanitario(), BorderLayout.CENTER);
			pnSeleccion.add(getCbMedicosYEnfermeros(), BorderLayout.SOUTH);
		}
		return pnSeleccion;
	}

	private JLabel getLblSeleccioneUnSanitario() {
		if (lblSeleccioneUnSanitario == null) {
			lblSeleccioneUnSanitario = new JLabel("Seleccione un sanitario para asignarle sus d\u00EDas de trabajo");
			lblSeleccioneUnSanitario.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblSeleccioneUnSanitario;
	}

	private JComboBox<String> getCbMedicosYEnfermeros() {
		if (cbMedicosYEnfermeros == null) {
			cbMedicosYEnfermeros = new JComboBox<String>();
			cbMedicosYEnfermeros.setModel(new DefaultComboBoxModel<String>(listaMedicos()));
		}
		return cbMedicosYEnfermeros;
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
					VentanaAsignarJornadaLaboralPpal ventana = new VentanaAsignarJornadaLaboralPpal(sanitario);
					ventana.setVisible(true);
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
			btCancelar.setForeground(Color.WHITE);
			btCancelar.setBackground(Color.RED);
		}
		return btCancelar;
	}
}
