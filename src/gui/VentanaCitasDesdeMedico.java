package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import negocio.citas.Cita;
import negocio.citas.Ubicacion;
import negocio.consulta.DataBaseConsultor;
import negocio.consulta.impl.DatabaseConsultorImpl;
import negocio.paciente.Paciente;
import negocio.sanitario.Medico;

public class VentanaCitasDesdeMedico extends JFrame {

	private static final long serialVersionUID = 1L;
	boolean borrado = false;
	private JPanel contentPane;
	private JPanel panelNombreCita;
	private JLabel lblCitas;
	private JPanel panelAsignarHMPU;
	private JPanel panelBotones;
	private JPanel panelBotones2;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JPanel panelAsignarHMP;
	private JPanel panelHorario;
	private JPanel panelDHA;
	private JPanel panelDiaMesAno;
	private JPanel panelDia;
	private JLabel lblDia;
	private JPanel panelMes;
	private JPanel panelAno;
	private JPanel panelDiaTexto;
	private JLabel lblMes;
	private JPanel panel;
	private JLabel lblAno;
	private JPanel panelAnio;
	private JPanel pnHoraMin;
	private JPanel pnHora;
	private JLabel lblHora;
	private JPanel pnHoraCentral;
	private JPanel pnMin;
	private JLabel lblMin;
	private JPanel pnMinCentral;
	private JComboBox<String> cbDia;
	private JComboBox<String> cbMes;
	private JComboBox<String> cbAnio;
	private JComboBox<String> cbMin;
	private JComboBox<String> cbHora;
	private JComboBox<String> cbDuracionMin;
	private JComboBox<String> cbDuracionHora;
	private DefaultListModel<Ubicacion> ubicacion = new DefaultListModel<Ubicacion>();
	private DefaultListModel<Paciente> listaPaciente = new DefaultListModel<Paciente>();
	DefaultListModel<Medico> medico2 = new DefaultListModel<Medico>();
	LocalDateTime d;
	int dia;
	int mes;
	int anio;
	String diaHorario;
	String mesHorario;
	String anioHorario;
	String horaEntrada;
	String horaSalida;
	String minEntrada;
	String minSalida;
	String id_medico;
	private JPanel Duracion;
	private JPanel pnDuracionHora;
	private JPanel pnDuracionMin;
	private JLabel lblHoraDuracion;
	private JPanel pnHoraDuracionCb;
	private JLabel lblMinDuracion;
	private JPanel pnMinDuracionCb;
	private JPanel pnDuracionMinHora;
	private JPanel pnBotonAsginarHora;
	private JButton btAsignarHora;
	private JButton btEliminarHora;
	private boolean horaAsignada;
	private LocalTime localHoraEntrada;
	private LocalDate fechaLocalDate;
	private LocalTime localHoraSalida;
	private JPanel panelU;
	private JPanel pnUrgencia;
	private JCheckBox chbxEsUrgente;

	private Medico medico;
	private Paciente paciente;

	/**
	 * Create the frame.
	 */
	public VentanaCitasDesdeMedico(Medico med, Paciente pa) {
		this.medico = med;
		this.paciente = pa;

		d = LocalDateTime.now();
		setTitle("Hospital: Medico-> Crear Cita");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 825, 740);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanelNombreCita(), BorderLayout.NORTH);

		contentPane.add(getPnBotones(), BorderLayout.SOUTH);
		contentPane.add(getPnAsignarHMPU());
	}

	private JPanel getPanelNombreCita() {
		if (panelNombreCita == null) {
			panelNombreCita = new JPanel();
			panelNombreCita.setLayout(new BorderLayout(0, 0));
			panelNombreCita.add(getLblCitas());
		}
		return panelNombreCita;
	}

	private JLabel getLblCitas() {
		if (lblCitas == null) {
			lblCitas = new JLabel("Medico: Crear Citas");
			lblCitas.setFont(new Font("Tahoma", Font.PLAIN, 21));
		}
		return lblCitas;
	}

	private JPanel getPnAsignarHMPU() {
		if (panelAsignarHMPU == null) {
			panelAsignarHMPU = new JPanel();
			panelAsignarHMPU.setLayout(new BorderLayout(0, 0));
			panelAsignarHMPU.add(getPanelAsignarHMP(), BorderLayout.CENTER);
		}
		return panelAsignarHMPU;
	}

	private JPanel getPnBotones() {
		if (panelBotones == null) {
			panelBotones = new JPanel();
			panelBotones.setLayout(new BorderLayout(0, 0));
			panelBotones.add(getPanelBotones2(), BorderLayout.EAST);
		}
		return panelBotones;
	}

	private JPanel getPanelBotones2() {
		if (panelBotones2 == null) {
			panelBotones2 = new JPanel();
			panelBotones2.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
			panelBotones2.add(getBtnAceptar());
			panelBotones2.add(getBtnCancelar());
		}
		return panelBotones2;
	}

	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.setEnabled(false);
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					String ubicacion = "not selected";
					String id_cita = UUID.randomUUID().toString();
					DataBaseConsultor db = new DatabaseConsultorImpl();
					Cita c = new Cita(id_cita, paciente.id_paciente, fechaLocalDate, localHoraEntrada, localHoraSalida,

							chbxEsUrgente.isSelected(), "contacto_1", ubicacion, false);
					
					c.id_historial=db.getIdHistorialPaciente(c.id_paciente);

					DatabaseConsultorImpl.anadirCita(c);

					cerrar();

				}
			});
			btnAceptar.setMnemonic('A');
			btnAceptar.setForeground(Color.BLACK);
			btnAceptar.setBackground(new Color(51, 102, 51));
		}
		return btnAceptar;
	}

	private void cerrar() {
		this.dispose();
	}

	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cerrarVentana();
				}
			});
			btnCancelar.setMnemonic('C');
			btnCancelar.setBackground(new Color(153, 0, 0));
		}
		return btnCancelar;
	}

	protected void cerrarVentana() {
		this.dispose();
	}

	private JPanel getPanelAsignarHMP() {
		if (panelAsignarHMP == null) {
			panelAsignarHMP = new JPanel();
			panelAsignarHMP.setLayout(new BorderLayout(0, 0));
			panelAsignarHMP.add(getPanelHorario());
			panelAsignarHMP.add(getPanelU_1(), BorderLayout.SOUTH);
		}
		return panelAsignarHMP;
	}

	private JPanel getPanelHorario() {
		if (panelHorario == null) {
			panelHorario = new JPanel();
			panelHorario.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 3), "Horario",
					TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelHorario.setLayout(new BorderLayout(0, 0));
			panelHorario.add(getPanelDHA());
		}
		return panelHorario;
	}

	private JPanel getPanelDHA() {
		if (panelDHA == null) {
			panelDHA = new JPanel();
			GridBagLayout gbl_panelDHA = new GridBagLayout();
			gbl_panelDHA.columnWidths = new int[] { 318, 0 };
			gbl_panelDHA.rowHeights = new int[] { 85, 85, 0, 0, 0 };
			gbl_panelDHA.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
			gbl_panelDHA.rowWeights = new double[] { 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
			panelDHA.setLayout(gbl_panelDHA);
			GridBagConstraints gbc_panelDiaMesAno = new GridBagConstraints();
			gbc_panelDiaMesAno.fill = GridBagConstraints.BOTH;
			gbc_panelDiaMesAno.insets = new Insets(0, 0, 5, 0);
			gbc_panelDiaMesAno.gridx = 0;
			gbc_panelDiaMesAno.gridy = 0;
			panelDHA.add(getPanelDiaMesAno(), gbc_panelDiaMesAno);
			GridBagConstraints gbc_pnHoraMin = new GridBagConstraints();
			gbc_pnHoraMin.insets = new Insets(0, 0, 5, 0);
			gbc_pnHoraMin.fill = GridBagConstraints.BOTH;
			gbc_pnHoraMin.gridx = 0;
			gbc_pnHoraMin.gridy = 1;
			panelDHA.add(getPnHoraMin(), gbc_pnHoraMin);
			GridBagConstraints gbc_Duracion = new GridBagConstraints();
			gbc_Duracion.insets = new Insets(0, 0, 5, 0);
			gbc_Duracion.fill = GridBagConstraints.BOTH;
			gbc_Duracion.gridx = 0;
			gbc_Duracion.gridy = 2;
			panelDHA.add(getDuracion(), gbc_Duracion);
			GridBagConstraints gbc_pnBotonAsginarHora = new GridBagConstraints();
			gbc_pnBotonAsginarHora.gridx = 0;
			gbc_pnBotonAsginarHora.gridy = 3;
			panelDHA.add(getPnBotonAsginarHora(), gbc_pnBotonAsginarHora);
		}
		return panelDHA;
	}

	private JPanel getPanelDiaMesAno() {
		if (panelDiaMesAno == null) {
			panelDiaMesAno = new JPanel();
			panelDiaMesAno.setLayout(new GridLayout(1, 3, 15, 5));
			panelDiaMesAno.add(getPanelDia());
			panelDiaMesAno.add(getPanelMes());
			panelDiaMesAno.add(getPanelAno());
		}
		return panelDiaMesAno;
	}

	private JPanel getPanelDia() {
		if (panelDia == null) {
			panelDia = new JPanel();
			panelDia.setLayout(new BorderLayout(0, 0));
			panelDia.add(getLblDia(), BorderLayout.NORTH);
			panelDia.add(getPanelDiaTexto(), BorderLayout.CENTER);
		}
		return panelDia;
	}

	private JLabel getLblDia() {
		if (lblDia == null) {
			lblDia = new JLabel("D\u00EDa");
			lblDia.setLabelFor(getCbDia());
			lblDia.setDisplayedMnemonic('d');
			lblDia.setHorizontalAlignment(SwingConstants.CENTER);
			lblDia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblDia;
	}

	private JPanel getPanelMes() {
		if (panelMes == null) {
			panelMes = new JPanel();
			panelMes.setLayout(new BorderLayout(0, 0));
			panelMes.add(getLblMes(), BorderLayout.NORTH);
			panelMes.add(getPanel(), BorderLayout.CENTER);
		}
		return panelMes;
	}

	private JPanel getPanelAno() {
		if (panelAno == null) {
			panelAno = new JPanel();
			panelAno.setLayout(new BorderLayout(0, 0));
			panelAno.add(getLblAno(), BorderLayout.NORTH);
			panelAno.add(getPanelAnio(), BorderLayout.CENTER);
		}
		return panelAno;
	}

	private JPanel getPanelDiaTexto() {
		if (panelDiaTexto == null) {
			panelDiaTexto = new JPanel();
			panelDiaTexto.setLayout(new BorderLayout(0, 0));
			panelDiaTexto.add(getCbDia(), BorderLayout.NORTH);
		}
		return panelDiaTexto;
	}

	private JLabel getLblMes() {
		if (lblMes == null) {
			lblMes = new JLabel("Mes");
			lblMes.setLabelFor(getCbMes());
			lblMes.setDisplayedMnemonic('m');
			lblMes.setHorizontalAlignment(SwingConstants.CENTER);
			lblMes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblMes;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getCbMes(), BorderLayout.NORTH);
		}
		return panel;
	}

	private JLabel getLblAno() {
		if (lblAno == null) {
			lblAno = new JLabel("A\u00F1o");
			lblAno.setLabelFor(getCbAnio());
			lblAno.setDisplayedMnemonic('o');
			lblAno.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblAno.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblAno;
	}

	private JPanel getPanelAnio() {
		if (panelAnio == null) {
			panelAnio = new JPanel();
			panelAnio.setLayout(new BorderLayout(0, 0));
			panelAnio.add(getCbAnio(), BorderLayout.NORTH);
		}
		return panelAnio;
	}

	private JComboBox<String> getCbDia() {
		if (cbDia == null) {
			cbDia = new JComboBox<String>();
			cbDia.setModel(new DefaultComboBoxModel<String>(arrayDia()));
			cbDia.setSelectedItem(String.valueOf(d.getDayOfMonth()));
			cbDia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (asertoMesesDiasAnio())
						cbDia.setSelectedItem(String.valueOf(d.getDayOfMonth()));
				}
			});

		}
		return cbDia;
	}

	private JComboBox<String> getCbMes() {
		if (cbMes == null) {
			cbMes = new JComboBox<String>();
			cbMes.setModel(new DefaultComboBoxModel<String>(arrayMes()));
			cbMes.setSelectedItem(pasarMesesAEspañol(d.getMonth().toString()));
			cbMes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (asertoMesesDiasAnio())
						cbMes.setSelectedItem(pasarMesesAEspañol(d.getMonth().toString()));
				}
			});

		}
		return cbMes;
	}

	/**
	 * ComboBox de años
	 * 
	 * @return
	 */
	private JComboBox<String> getCbAnio() {
		if (cbAnio == null) {
			cbAnio = new JComboBox<String>();
			cbAnio.setModel(new DefaultComboBoxModel<String>(arrayAnio()));
			cbMes.setSelectedItem(String.valueOf(d.getYear()));
			cbAnio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (asertoMesesDiasAnio()) {
						cbAnio.setSelectedItem(Integer.toString(d.getYear()));
					}
				}
			});
		}
		return cbAnio;
	}

	/**
	 * ComboBox horas
	 * 
	 * @return
	 */
	private JComboBox<String> getCbHora() {
		if (cbHora == null) {
			cbHora = new JComboBox<String>();
			cbHora.setModel(new DefaultComboBoxModel<String>(arrayHoras()));
			cbHora.setSelectedItem("00");
		}
		return cbHora;
	}

	/**
	 * ComboBox minutos
	 * 
	 * @return
	 */
	private JComboBox<String> getCbMin() {
		if (cbMin == null) {
			cbMin = new JComboBox<String>();
			cbMin.setModel(new DefaultComboBoxModel<String>(arrayMin()));
			cbMin.setSelectedItem("00");
		}
		return cbMin;
	}

	private JPanel getPnHoraMin() {
		if (pnHoraMin == null) {
			pnHoraMin = new JPanel();
			pnHoraMin.setBorder(new TitledBorder(
					new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
					"Hora Entrada", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnHoraMin.setLayout(new GridLayout(0, 2, 15, 0));
			pnHoraMin.add(getPnHora());
			pnHoraMin.add(getPnMin());
		}
		return pnHoraMin;
	}

	private JPanel getPnHora() {
		if (pnHora == null) {
			pnHora = new JPanel();
			pnHora.setLayout(new BorderLayout(0, 0));
			pnHora.add(getLblHora(), BorderLayout.NORTH);
			pnHora.add(getPnHoraCentral(), BorderLayout.CENTER);
		}
		return pnHora;
	}

	private JLabel getLblHora() {
		if (lblHora == null) {
			lblHora = new JLabel("Hora");
			lblHora.setLabelFor(getCbHora());
			lblHora.setDisplayedMnemonic('h');
			lblHora.setHorizontalAlignment(SwingConstants.CENTER);
			lblHora.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblHora;
	}

	private JPanel getPnHoraCentral() {
		if (pnHoraCentral == null) {
			pnHoraCentral = new JPanel();
			pnHoraCentral.setLayout(new BorderLayout(0, 0));
			pnHoraCentral.add(getCbHora(), BorderLayout.NORTH);
		}
		return pnHoraCentral;
	}

	private JPanel getPnMin() {
		if (pnMin == null) {
			pnMin = new JPanel();
			pnMin.setLayout(new BorderLayout(0, 0));
			pnMin.add(getLblMin(), BorderLayout.NORTH);
			pnMin.add(getPnMinCentral());
		}
		return pnMin;
	}

	private JLabel getLblMin() {
		if (lblMin == null) {
			lblMin = new JLabel("Minutos");
			lblMin.setLabelFor(getCbMin());
			lblMin.setDisplayedMnemonic('i');
			lblMin.setHorizontalAlignment(SwingConstants.CENTER);
			lblMin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblMin;
	}

	private JPanel getPnMinCentral() {
		if (pnMinCentral == null) {
			pnMinCentral = new JPanel();
			pnMinCentral.setLayout(new BorderLayout(0, 0));
			pnMinCentral.add(getCbMin(), BorderLayout.NORTH);
		}
		return pnMinCentral;
	}

	private JPanel getDuracion() {
		if (Duracion == null) {
			Duracion = new JPanel();
			Duracion.setBorder(new TitledBorder(
					new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
					"Hora Salida", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			Duracion.setLayout(new GridLayout(1, 1, 0, 0));
			Duracion.add(getPnDuracionMinHora());
		}
		return Duracion;
	}

	private JPanel getPnDuracionHora() {
		if (pnDuracionHora == null) {
			pnDuracionHora = new JPanel();
			pnDuracionHora.setLayout(new BorderLayout(0, 0));
			pnDuracionHora.add(getLblHoraDuracion(), BorderLayout.NORTH);
			pnDuracionHora.add(getPnHoraDuracionCb());
		}
		return pnDuracionHora;
	}

	private JPanel getPnDuracionMin() {
		if (pnDuracionMin == null) {
			pnDuracionMin = new JPanel();
			pnDuracionMin.setLayout(new BorderLayout(0, 0));
			pnDuracionMin.add(getLblMinDuracion(), BorderLayout.NORTH);
			pnDuracionMin.add(getPnMinDuracionCb());
		}
		return pnDuracionMin;
	}

	private JLabel getLblHoraDuracion() {
		if (lblHoraDuracion == null) {
			lblHoraDuracion = new JLabel("Hora");
			lblHoraDuracion.setLabelFor(getCbDuracionHora());
			lblHoraDuracion.setDisplayedMnemonic('r');
			lblHoraDuracion.setHorizontalAlignment(SwingConstants.CENTER);
			lblHoraDuracion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblHoraDuracion;
	}

	private JPanel getPnHoraDuracionCb() {
		if (pnHoraDuracionCb == null) {
			pnHoraDuracionCb = new JPanel();
			pnHoraDuracionCb.setLayout(new BorderLayout(0, 0));
			pnHoraDuracionCb.add(getCbDuracionHora(), BorderLayout.NORTH);
		}
		return pnHoraDuracionCb;
	}

	private JComboBox<String> getCbDuracionHora() {
		if (cbDuracionHora == null) {
			cbDuracionHora = new JComboBox<String>();
			cbDuracionHora.setModel(new DefaultComboBoxModel<String>(arrayHoras()));
			cbDuracionHora.setSelectedItem("00");
		}
		return cbDuracionHora;
	}

	private JLabel getLblMinDuracion() {
		if (lblMinDuracion == null) {
			lblMinDuracion = new JLabel("Minutos");
			lblMinDuracion.setDisplayedMnemonic('u');
			lblMinDuracion.setLabelFor(getCbDuracionMin());
			lblMinDuracion.setHorizontalAlignment(SwingConstants.CENTER);
			lblMinDuracion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblMinDuracion;
	}

	private JPanel getPnMinDuracionCb() {
		if (pnMinDuracionCb == null) {
			pnMinDuracionCb = new JPanel();
			pnMinDuracionCb.setLayout(new BorderLayout(0, 0));
			pnMinDuracionCb.add(getCbDuracionMin(), BorderLayout.NORTH);
		}
		return pnMinDuracionCb;
	}

	private JComboBox<String> getCbDuracionMin() {
		if (cbDuracionMin == null) {
			cbDuracionMin = new JComboBox<String>();
			cbDuracionMin.setModel(new DefaultComboBoxModel<String>(arrayMin()));
			cbDuracionMin.setSelectedItem("00");
		}
		return cbDuracionMin;
	}

	private JPanel getPnDuracionMinHora() {
		if (pnDuracionMinHora == null) {
			pnDuracionMinHora = new JPanel();
			pnDuracionMinHora.setLayout(new GridLayout(0, 2, 15, 0));
			pnDuracionMinHora.add(getPnDuracionHora());
			pnDuracionMinHora.add(getPnDuracionMin());
		}
		return pnDuracionMinHora;
	}

	private JPanel getPnBotonAsginarHora() {
		if (pnBotonAsginarHora == null) {
			pnBotonAsginarHora = new JPanel();
			pnBotonAsginarHora.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnBotonAsginarHora.add(getBtAsignarHora());
			pnBotonAsginarHora.add(getBtEliminarHora());
		}
		return pnBotonAsginarHora;
	}

	private JButton getBtAsignarHora() {
		if (btAsignarHora == null) {
			btAsignarHora = new JButton("Asignar Hora");
			btAsignarHora.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Mirar si la hora esta bien
					if (validarHora() && validarHoraYDiaMayor()) {

						ponerHorario();

						validarUbicacion();
						if (validarVacacionesMedico()) {
							return;
						}

						// horaAsignada = true;
						btEliminarHora.setEnabled(true);
						JOptionPane.showMessageDialog(null, "Fecha asignada");
						btAsignarHora.setEnabled(false);
						deshabilitaHabilitarrHorario(false);
						btnAceptar.setEnabled(true);
					} else {
						warningBox("Error a la hora de asignar la hora porfavor introduzca esta bien",
								"Hora Equivocada");
					}
				}

			});
		}
		return btAsignarHora;
	}

	protected boolean validarVacacionesMedico() {
		boolean cita = DatabaseConsultorImpl.validarMedicoNoVacaciones(medico.getId_Medico(), fechaLocalDate);
		if (cita) {
			JOptionPane.showMessageDialog(this, "Error en el día, el medico tiene vacaciones");
		}
		return cita;
	}

	private JButton getBtEliminarHora() {
		if (btEliminarHora == null) {
			btEliminarHora = new JButton("Eliminar  Hora");
			btEliminarHora.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					restablecerUbicaciones();
					horaAsignada = false;
					btEliminarHora.setEnabled(false);
					btAsignarHora.setEnabled(true);
					btnAceptar.setEnabled(false);
					// TODO
				}
			});
			btEliminarHora.setEnabled(false);
		}
		return btEliminarHora;
	}

	private Ubicacion[] ubicacionesDisponiblesPrincipio() {
		List<Ubicacion> lsitasUbicacion = DatabaseConsultorImpl.buscarTodasUbicaiones();
		ubicacion.removeAllElements();
		for (Ubicacion u : lsitasUbicacion) {
			ubicacion.addElement(u);
		}
		Ubicacion[] ux = new Ubicacion[lsitasUbicacion.size()];
		return lsitasUbicacion.toArray(ux);

	}

	// AVISOS
	public void warningBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(this, infoMessage, "WarningBox: " + titleBar, JOptionPane.WARNING_MESSAGE);
	}

	// METODOS
	// COMBOBOX
	// combobox:rellenar
	/**
	 * Crea una array de años
	 * 
	 * @return
	 */
	private String[] arrayAnio() {
		String[] res = new String[11];
		int pos = 0;
		for (int i = 2020; i <= 2030; i++) {
			String j = Integer.toString(i);
			res[pos] = j;
			pos = pos + 1;
		}
		return res;
	}

	/**
	 * Crea una array de dias
	 * 
	 * @return
	 */
	private String[] arrayDia() {
		String[] res = new String[31];
		int pos = 0;
		for (int i = 1; i <= 31; i++) {
			String j = Integer.toString(i);
			res[pos] = j;
			pos = pos + 1;
		}
		return res;
	}

	/**
	 * Crea una array de meses
	 * 
	 * @return
	 */
	private String[] arrayMes() {
		String[] res = new String[12];
		res[0] = ("Enero");
		res[1] = ("Febrero");
		res[2] = ("Marzo");
		res[3] = ("Abril");
		res[4] = ("Mayo");
		res[5] = ("Junio");
		res[6] = ("Julio");
		res[7] = ("Agosto");
		res[8] = ("Septiembre");
		res[9] = ("Octubre");
		res[10] = ("Noviembre");
		res[11] = ("Diciembre");
		return res;
	}

	/**
	 * Rellenar las horas en el comboBox
	 * 
	 * @return
	 */
	private String[] arrayHoras() {
		String[] res = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09" };
		String[] res2 = new String[14];
		String[] rest = new String[24];
		int pos = 0;
		for (int i = 10; i <= 23; i++) {
			String j = Integer.toString(i);
			res2[pos] = j;
			pos = pos + 1;
		}
		rest = unirDosArrays(res, res2, rest);
		return rest;
	}

	private String[] unirDosArrays(String[] res, String[] res2, String[] rest) {
		int i;
		int a;
		for (i = 0; i < res.length; i++) {
			rest[i] = res[i];
		}
		for (a = 0; a < res2.length; a++) {
			rest[i] = res2[a];
			i++;
		}
		return rest;
	}

	/**
	 * Rellenar los minutos en el comboBox
	 * 
	 * @return
	 */
	private String[] arrayMin() {
		String[] res = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09" };
		String[] res2 = new String[50];
		String[] rest = new String[60];
		int pos = 0;
		for (int i = 10; i < 60; i++) {
			String j = Integer.toString(i);
			res2[pos] = j;
			pos = pos + 1;
		}
		rest = unirDosArrays(res, res2, rest);
		return rest;
	}

	private String[] arrayEspecialidades() {
		String res[] = { " ", "Traumatología", "Oftalmología", "Otorrinolaringología", "Dermatología" };
		return res;
	}
	// combox:ASERTOS

	private boolean validarHoraYDiaMayor() {
		LocalDate hoy = LocalDate.now();
		LocalDate a = LocalDate.of(Integer.parseInt(cbAnio.getSelectedItem().toString()),
				cambiarMesANumero(cbMes.getSelectedItem().toString()),
				Integer.parseInt(cbDia.getSelectedItem().toString()));
		LocalTime horaActual = LocalTime.now();
		LocalTime horaPuesta = LocalTime.of(Integer.parseInt(cbHora.getSelectedItem().toString()),
				Integer.parseInt(cbMin.getSelectedItem().toString()));

		if (a.equals(hoy)) {
			if (horaPuesta.isBefore(horaActual))
				return false;
		}

		return true;
	}

	public boolean asertoMesesDiasAnio() {
		GregorianCalendar calendar = new GregorianCalendar();
		String mes = cbMes.getSelectedItem().toString();
		String dia = cbDia.getSelectedItem().toString();
		String anio = cbAnio.getSelectedItem().toString();
		int a = Integer.parseInt(anio);
		int d = Integer.parseInt(dia);

		if ((mes == "Abril" || mes == "Junio" || mes == "Septiembre" || mes == "Noviembre") && d == 31) {
			warningBox("Fecha incorrecta ese mes no tiene tantos días", "Fecha Equivocada");
			return true;
		}
		if (mes == "Febrero" && !calendar.isLeapYear(a) && d == 29) {
			warningBox("Fecha incorrecta en ese año febrero es bisiesto", "Fecha Equivocada");
			return true;
		}

		if (mes == "Febrero" && (d == 30 || d == 31)) {
			warningBox("Fecha incorrecta: febrero no tiene tantos días", "Fecha Equivocada");
			return true;
		}
		if (asertDiasYaPasados(mes, dia, anio)) {
			warningBox("Fecha incorrecta: no puedes poner una cita anterior al día actual", "Fecha Equivocada");
			return true;
		}
		return false;
	}

	boolean asertDiasYaPasados(String mes, String dia, String anio) {
		LocalDate a = LocalDate.of(Integer.parseInt(anio), cambiarMesANumero(mes), Integer.parseInt(dia));
		LocalDate b = LocalDate.now();
		return a.isBefore(b);
	}

	public int cambiarMesANumero(String mes) {
		switch (mes) {
		case "Enero":
			mes = "1";
			break;
		case "Febrero":
			mes = "2";
			break;
		case "Marzo":
			mes = "3";
			break;
		case "Abril":
			mes = "4";
			break;
		case "Mayo":
			mes = "5";
			break;
		case "Junio":
			mes = "6";
			break;
		case "Julio":
			mes = "7";
			break;
		case "Agosto":
			mes = "8";
			break;
		case "Septiembre":
			mes = "9";
			break;
		case "Octubre":
			mes = "10";
			break;
		case "Noviembre":
			mes = "11";
			break;
		case "Diciembre":
			mes = "12";
			break;
		}
		return Integer.parseInt(mes);
	}

	/**
	 * Cambair mes de ingles a español
	 * 
	 * @param mesingles
	 * @return
	 */
	public String pasarMesesAEspañol(String mesingles) {
		switch (mesingles) {
		case "JANUARY":
			mesingles = "Enero";
			break;
		case "FEBRUARY":
			mesingles = "Febrero";
			break;
		case "MACRH":
			mesingles = "Marzo";
			break;
		case "APRIL":
			mesingles = "Abril";
			break;
		case "MAY":
			mesingles = "Mayo";
			break;
		case "JUNE":
			mesingles = "Junio";
			break;
		case "JULY":
			mesingles = "Julio";
			break;
		case "AUGUST":
			mesingles = "Agosto";
			break;
		case "SEPTEMBER":
			mesingles = "Septiembre";
			break;
		case "OCTOBER":
			mesingles = "Octubre";
			break;
		case "NOVEMBER":
			mesingles = "Noviembre";
			break;
		case "DECEMBER":
			mesingles = "Diciembre";
			break;
		}
		return mesingles;
	}

	/**
	 * Metodo que al pulsar la hora de la cita mira si esos medicos no tengan
	 * ninguan cita
	 * 
	 * @return
	 */
	protected boolean validarUbicacion() {
		Boolean validadUbicacion = true;
		Ubicacion u = new Ubicacion();
		String id_ubicacion = u.getId_ubicacion();
		List<String> l = DatabaseConsultorImpl.buscarCitasEnEsaUbicacion(id_ubicacion, fechaLocalDate);
		for (String aux : l) {
			String[] aux2 = aux.split(",");
			String entrada = horaEntrada + ":" + minEntrada;
			String salida = horaSalida + ":" + minSalida;
			if (mirarSitieneUnaCitaAEsaHora(LocalTime.parse(aux2[0]), LocalTime.parse(aux2[1]),
					LocalTime.parse(entrada), LocalTime.parse(salida))) {
				warningBox("La ubicación " + u + " ya esta en uso a esa hora", "Error Ubicación");
				validadUbicacion = false;
				break;

			}
		}
		return validadUbicacion;

	}

	protected void restablecerUbicaciones() {

	}

	private Ubicacion[] ubicacionesDisponibles() {
		List<Ubicacion> ret = new ArrayList<Ubicacion>();
		List<Ubicacion> lsitasUbicacion = DatabaseConsultorImpl.buscarTodasUbicaiones();
		ubicacion.removeAllElements();
		for (Ubicacion u : lsitasUbicacion) {
			boolean entra = false;
			boolean entra2 = false;
			List<String> l = DatabaseConsultorImpl.buscarCitasEnEsaUbicacion(u.id_ubicacion, fechaLocalDate);
			for (String aux : l) {
				entra = true;
				String[] aux2 = aux.split(",");
				String entrada = horaEntrada + ":" + minEntrada;
				String salida = horaSalida + ":" + minSalida;
				if (mirarSitieneUnaCitaAEsaHora(LocalTime.parse(aux2[0]), LocalTime.parse(aux2[1]),
						LocalTime.parse(entrada), LocalTime.parse(salida))) {
					entra2 = true;
					break;
				}

			}
			if (!entra) {
				ret.add(u);
				ubicacion.addElement(u);
			}
			if (entra && !entra2) {
				ret.add(u);
				ubicacion.addElement(u);
			}
		}

		Ubicacion[] ux = new Ubicacion[ret.size()];
		return ret.toArray(ux);
	}

	/**
	 * Metodo que mira si el medico ya tiene una cita a esas horas y dias
	 * 
	 * @param fechaAmirar
	 * @param localHoraEntrada
	 * @param localHoraSalida
	 * @param fechaLocalDate
	 * @param horaEntrada
	 * @param horaSalida
	 * @return
	 */
	private static boolean mirarSitieneUnaCitaAEsaHora(LocalTime localHoraEntrada, LocalTime localHoraSalida,
			LocalTime horaEntrada, LocalTime horaSalida) {

		if ((!localHoraEntrada.isBefore(horaEntrada)) && (!localHoraEntrada.isAfter(horaSalida)))
			return true;
		else if ((!localHoraSalida.isBefore(horaEntrada)) && (!localHoraSalida.isAfter(horaSalida)))
			return true;
		else if ((!localHoraEntrada.isAfter(horaEntrada)) && (!localHoraSalida.isBefore(horaSalida)))
			return true;
		else if ((!localHoraEntrada.isBefore(horaEntrada)) && (!localHoraSalida.isAfter(horaSalida)))
			return true;

		return false;

	}

	private void ponerHorario() {
		diaHorario = cbDia.getSelectedItem().toString();

		if (diaHorario.length() == 1) {
			diaHorario = "0" + diaHorario;
		}
		mesHorario = "" + cambiarMesANumero(cbMes.getSelectedItem().toString());
		anioHorario = cbAnio.getSelectedItem().toString();
		String fecha = diaHorario + "/" + mesHorario + "/" + anioHorario;

		fechaLocalDate = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		localHoraEntrada = convertirHoraALocalTime();
		localHoraSalida = convertirHoraSalidaTime();
	}

	/**
	 * Convierte la hora y mins a localTime
	 * 
	 * @return
	 */
	private LocalTime convertirHoraALocalTime() {
		horaEntrada = cbHora.getSelectedItem().toString();
		minEntrada = cbMin.getSelectedItem().toString();
		String timeEntrada = horaEntrada + ":" + minEntrada;
		return LocalTime.parse(timeEntrada);
	}

	/**
	 * Convierte hora de salida en LocalTime
	 * 
	 * @return
	 */
	private LocalTime convertirHoraSalidaTime() {
		horaSalida = cbDuracionHora.getSelectedItem().toString();
		minSalida = cbDuracionMin.getSelectedItem().toString();
		String timeSalida = horaSalida + ":" + minSalida;
		return LocalTime.parse(timeSalida);
	}

	private void clearHorario() {
		cbDia.setSelectedItem(String.valueOf(d.getDayOfMonth()));
		cbDia.setSelectedItem(String.valueOf((pasarMesesAEspañol(d.getMonth().toString()))));
		cbAnio.setSelectedItem(Integer.toString(d.getYear()));
		cbHora.setSelectedItem("00");
		cbMin.setSelectedItem("00");
		cbDuracionHora.setSelectedItem("00");
		cbDuracionMin.setSelectedItem("00");
		deshabilitaHabilitarrHorario(true);

	}

	/**
	 * Habilita o deshabilita el todos los componentes del horario
	 * 
	 * @param dh dependiendo del parametro deshabilita o habilita
	 */
	protected void deshabilitaHabilitarrHorario(boolean dh) {
		cbDia.setEnabled(dh);
		cbMes.setEnabled(dh);
		cbAnio.setEnabled(dh);
		cbHora.setEnabled(dh);
		cbMin.setEnabled(dh);
		cbDuracionHora.setEnabled(dh);
		cbDuracionMin.setEnabled(dh);
	}

	private boolean validarHora() {
		LocalTime horaEntrada = convertirHoraALocalTime();
		LocalTime horaSalida = convertirHoraSalidaTime();
		if (horaEntrada.isBefore(horaSalida))
			return true;
		return false;
	}

	private JPanel getPanelU_1() {
		if (panelU == null) {
			panelU = new JPanel();
			panelU.setLayout(new GridLayout(0, 1, 0, 0));
			panelU.add(getPnUrgencia_1());
		}
		return panelU;
	}

	private JPanel getPnUrgencia_1() {
		if (pnUrgencia == null) {
			pnUrgencia = new JPanel();
			pnUrgencia.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 3), "Urgencia",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnUrgencia.setLayout(new BorderLayout(0, 0));
			pnUrgencia.add(getChbxEsUrgente_1());
		}
		return pnUrgencia;
	}

	private JCheckBox getChbxEsUrgente_1() {
		if (chbxEsUrgente == null) {
			chbxEsUrgente = new JCheckBox("Es urgente");
			chbxEsUrgente.setHorizontalAlignment(SwingConstants.CENTER);
			chbxEsUrgente.setFont(new Font("Tahoma", Font.PLAIN, 24));
			chbxEsUrgente.setMnemonic('U');
		}
		return chbxEsUrgente;
	}

}
