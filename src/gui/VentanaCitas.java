package gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import javax.swing.border.EtchedBorder;

import javax.swing.border.TitledBorder;

import negocio.citas.Cita;
import negocio.citas.Ubicacion;
import negocio.consulta.DataBaseConsultor;
import negocio.consulta.impl.DatabaseConsultorImpl;
import negocio.equipo.Especialista;
import negocio.paciente.Paciente;
import negocio.sanitario.Enfermero;
import negocio.sanitario.Medico;
import negocio.util.EnviarEmail;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.nio.charset.CodingErrorAction;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
//import javax.mail.MessagingException;
import javax.mail.MessagingException;
import javax.swing.BoxLayout;
import javax.swing.JList;
import java.awt.Component;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class VentanaCitas extends JFrame {

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
	private JPanel panelU;
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
	private static DefaultListModel<Paciente> listaPacienteAsignadoModel = new DefaultListModel<Paciente>();
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
	private JPanel panelMedico;
	private JPanel Duracion;
	private JPanel pnDuracionHora;
	private JPanel pnDuracionMin;
	private JLabel lblHoraDuracion;
	private JPanel pnHoraDuracionCb;
	private JLabel lblMinDuracion;
	private JPanel pnMinDuracionCb;
	private JPanel pnPacienteFiltrado;
	private JPanel pnNombreFiltradoPaciente;
	private JPanel pnNombrePacienteFiltradolbl;
	private JLabel lblNombrePaciente;
	private JPanel pnApellidoFiltradoPaciente;
	private JPanel pnNombrePacienteFiltradoTxt;
	private JTextField txtNombrePacienteFiltrado;
	private JPanel pnApellidoPacienteFiltradolbl;
	private JLabel lblApellidoPaciente;
	private JPanel pnApellidoPacienteFiltradoTxt;
	private JTextField txtApellidoPacienteFiltrado;
	private JPanel pnNumeroDeHistorial;
	private JPanel pnNumeroHistorialPacienteFiltradolbl;
	private JLabel lblNumeroHistorial;
	private JPanel pnNumeroHistorialPacienteFiltradotxt;
	private JTextField txtNumeroHistorial;
	private JPanel pnTarjetaSanitariaFiltradoPaciente;
	private JPanel pnCartillalPacienteFiltradolbl;
	private JLabel lblTarjetaSanitaria;
	private JPanel pnTarjetaSanitariaPacienteFiltradotxt;
	private JTextField txtTarjetaSanitaria;
	private JPanel pnAsignar;
	private JPanel pnMedicoFiltrado;
	private JPanel pnBoton;
	private JButton btFiltrar;
	private JPanel pnAsignarPaciente;
	private JPanel pnPacientes;
	private JPanel pnBotonPacienteAsignar;
	private JPanel pnTextoPacienteAsignado;
	private JPanel pnAsignarPacienteBoton;
	private JButton btAsignarPaciente;
	private JPanel pnEliminarPaciente;
	private JButton btEliminarPaciente;
	private JScrollPane scrollPane;
	private JList<Paciente> listPacientes;
	private JScrollPane scrollPanePacienteAsignado;
	private JList<Paciente> listaPacienteAsignado;

	private JPanel pnUbicaciones;
	private JPanel pnUbicacionTxt;
	private JLabel lblUbicacion;
	private JPanel pnCbUbicacion;
	private JComboBox<Ubicacion> cbUbicacion;
	static JList<Medico> listMedicosAsignados = new JList<Medico>();
	private static LocalDate fechaLocalDate;
	private static LocalTime localHoraSalida;
	private static LocalTime localHoraEntrada;
	private JPanel pnDuracionMinHora;
	private boolean horaAsignada;

	private JPanel pnUrgencia;
	private JCheckBox chbxEsUrgente;
	private JPanel pnInfo;
	private JTextArea txtInfoContacto;
	private JPanel pnAsignarStaff;
	private JButton btnNewButton;
	private JPanel PnMedicosYenfermerosYespecialista;
	private JPanel PnMedicos;
	private JScrollPane scrollMedicos;
	private JPanel pnEnfermeros;
	private JScrollPane scrollEnfermeros;
	static JList<Enfermero> listEnfermeroAsignados = new JList<Enfermero>();
	private JPanel pnNoAsignarHora;
	private JButton btNoAsignarHora;
	static boolean desactivarHorario = false;
	private JButton btnHistorial;
	private JPanel pnEspecialista;
	private JScrollPane scrollPaneEspecialista;
	static JList<Especialista> listEspecialista;

	/**
	 * Create the frame.
	 */
	public VentanaCitas() {

		d = LocalDateTime.now();
		setTitle("Hospital:Citas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1333, 657);
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
			lblCitas = new JLabel("Citas");
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
			btnAceptar = new JButton("Crear Cita");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (listaPacienteAsignadoModel.getSize() == 0) {
						warningBox("NO-paciente", "Error Paciente");

					} else {
						if (!desactivarHorario) {
							realizarCitaConHorario();

						} else {
							resalizaCitaSinHorario();

						}

					}

				}

			});
			btnAceptar.setMnemonic('A');
			btnAceptar.setForeground(Color.BLACK);
			btnAceptar.setBackground(new Color(51, 102, 51));
		}
		return btnAceptar;
	}

	private void resalizaCitaSinHorario() {
		if (!validarPaciente() || validarUbicacion()) {

			String id_cita = UUID.randomUUID().toString();
			String id_contacto = UUID.randomUUID().toString();
			anadirAlaCitaUrgente();
			DataBaseConsultor dbc = new DatabaseConsultorImpl();

			DatabaseConsultorImpl.anadirContacto(id_contacto, txtInfoContacto.getText());
			String Ubicacion = ((negocio.citas.Ubicacion) cbUbicacion.getSelectedItem()).getId_ubicacion();
			if (listEspecialista.getModel().getSize() > 0) {
				Cita c = new Cita(id_cita, listaPacienteAsignadoModel.getElementAt(0).id_paciente, fechaLocalDate, "-",
						"-", "-", "-", false, chbxEsUrgente.isSelected(), true, id_contacto, Ubicacion,
						dbc.getIdHistorialPaciente(listaPacienteAsignadoModel.getElementAt(0).id_paciente),
						listEspecialista.getModel().getElementAt(0).getId_especialista());
				DatabaseConsultorImpl.anadirCita(c);
				dispose();
			} else if (listMedicosAsignados.getModel().getSize() > 0
					|| listEnfermeroAsignados.getModel().getSize() > 0) {
				Cita c = new Cita(id_cita, listaPacienteAsignadoModel.getElementAt(0).id_paciente, fechaLocalDate, "-",
						"-", "-", "-", false, chbxEsUrgente.isSelected(), true, id_contacto, Ubicacion,
						dbc.getIdHistorialPaciente(listaPacienteAsignadoModel.getElementAt(0).id_paciente), null);
				DatabaseConsultorImpl.anadirCita(c);
				anadirMedicosYEnfermerosAlaCita(id_cita);
				dispose();
			} else {
				warningBox("No has puesto ningun Staff", "Error Staff");
				btEliminarPaciente.setEnabled(true);
				btAsignarPaciente.setEnabled(false);
			}
		}
	}

	private void realizarCitaConHorario() {
		ponerHorario();
		if (!validarHoraYDiaMayor())
			warningBox("El horario esta mal puesto", "Error Horario");
		else {
			if (!validarPaciente() || validarUbicacion()) {
				String id_cita = UUID.randomUUID().toString();
				String id_contacto = UUID.randomUUID().toString();
				anadirAlaCitaUrgente();
				DataBaseConsultor dbc = new DatabaseConsultorImpl();
				DatabaseConsultorImpl.anadirContacto(id_contacto, txtInfoContacto.getText());
				String Ubicacion = ((negocio.citas.Ubicacion) cbUbicacion.getSelectedItem()).getId_ubicacion();
				if (listEspecialista.getModel().getSize() > 0) {
					Cita c = new Cita(id_cita, listaPacienteAsignadoModel.getElementAt(0).id_paciente, fechaLocalDate,
							"-", "-", localHoraEntrada, localHoraSalida, false, chbxEsUrgente.isSelected(), true,
							id_contacto, Ubicacion,
							dbc.getIdHistorialPaciente(listaPacienteAsignadoModel.getElementAt(0).id_paciente),
							listEspecialista.getModel().getElementAt(0).getId_especialista());
					DatabaseConsultorImpl.anadirCita(c);
					dispose();
				} else if (listMedicosAsignados.getModel().getSize() > 0
						|| listEnfermeroAsignados.getModel().getSize() > 0) {
					Cita c = new Cita(id_cita, listaPacienteAsignadoModel.getElementAt(0).id_paciente, fechaLocalDate,
							"-", "-", localHoraEntrada, localHoraSalida, false, chbxEsUrgente.isSelected(), true,
							id_contacto, Ubicacion,
							dbc.getIdHistorialPaciente(listaPacienteAsignadoModel.getElementAt(0).id_paciente), null);
					DatabaseConsultorImpl.anadirCita(c);
					anadirMedicosYEnfermerosAlaCita(id_cita);
					dispose();
				} else {
					warningBox("No has puesto ningun Staff", "Error Staff");
					btEliminarPaciente.setEnabled(true);
					btAsignarPaciente.setEnabled(false);
				}
			}

		}

	}

	private void anadirAlaCitaUrgente() {
		if (chbxEsUrgente.isSelected()) {
			for (int i = 0; i < listMedicosAsignados.getModel().getSize(); i++) {
				enviarCorreo();
			}
		}

	}

	private void anadirMedicosYEnfermerosAlaCita(String id_cita) {

		for (int i = 0; i < listMedicosAsignados.getModel().getSize(); i++) {
			DatabaseConsultorImpl.anadirMedicoAlaCita(id_cita, listMedicosAsignados.getModel().getElementAt(i));

		}

		for (int i = 0; i < listEnfermeroAsignados.getModel().getSize(); i++) {
			DatabaseConsultorImpl.anadirEnfermeroAlaCita(id_cita, listEnfermeroAsignados.getModel().getElementAt(i));

		}
	}

	private void enviarCorreo() {
		EnviarEmail e = new EnviarEmail();
		try {
			// String correo =asignadoMedico.get(i).getEmail();
			String correo = "esterglez15@gmail.com";
			String asunto = "CITA URGENTE";
			String cadena = "Usted tiene una cita urgente.\nDatos de la fecha:\n";
			cadena += "Fecha de la cita: " + fechaLocalDate.toString();

			cadena += "\nHora de entrada: " + horaEntrada + ", hora de salida: " + horaSalida;
			cadena += "\nNombre del paciente: "
					+ DatabaseConsultorImpl.getPacienteById(listaPacienteAsignadoModel.getElementAt(0).id_paciente);

			cadena += "\nSala:" + cbUbicacion.getSelectedItem().toString();
			e.enviar(correo, asunto, cadena);
			JOptionPane.showMessageDialog(this, "Email enviado a los medicos asignados");
		} catch (MessagingException e1) {
			System.out.println("Error al enviar el e-mail");
			JOptionPane.showMessageDialog(this, "Ha habido un error al enviar el email al médico");
		}
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
			panelAsignarHMP.setLayout(new GridLayout(0, 3, 0, 10));
			panelAsignarHMP.add(getPanelMedico());
			panelAsignarHMP.add(getPanelHorario());
			panelAsignarHMP.add(getPanelU());
		}
		return panelAsignarHMP;
	}

	private JPanel getPanelU() {
		if (panelU == null) {
			panelU = new JPanel();
			panelU.setLayout(new GridLayout(0, 1, 0, 0));
			panelU.add(getPnUbicaciones());
			panelU.add(getPnAsignarStaff());
			panelU.add(getPnUrgencia());
			panelU.add(getPnInfo());
			panelU.add(getPnMedicosYenfermerosYespecialista());
		}
		return panelU;
	}

	private JPanel getPanelHorario() {
		if (panelHorario == null) {
			panelHorario = new JPanel();
			panelHorario.setBorder(new TitledBorder(
					new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
					"Horario", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelHorario.setLayout(new BorderLayout(0, 0));
			panelHorario.add(getPanelDHA());
		}
		return panelHorario;
	}

	private JPanel getPanelDHA() {
		if (panelDHA == null) {
			panelDHA = new JPanel();
			panelDHA.setLayout(new GridLayout(0, 1, 0, 0));
			panelDHA.add(getPanelDiaMesAno());
			panelDHA.add(getPnHoraMin());
			panelDHA.add(getDuracion());
			panelDHA.add(getPnNoAsignarHora());
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
					eliminarStaff();
				}
			});

		}
		return cbDia;
	}

	protected void eliminarStaff() {
		eliminarMedicoFinal();
		eliminarEnfemerosFinal();

	}

	private void eliminarEnfemerosFinal() {
		int i = listEnfermeroAsignados.getModel().getSize();
		if (i > 0) {
			DefaultListModel<Enfermero> list = new DefaultListModel<Enfermero>();
			listEnfermeroAsignados.setModel(list);
		}

	}

	protected void eliminarMedicoFinal() {
		int i = listMedicosAsignados.getModel().getSize();
		if (i > 0) {
			DefaultListModel<Medico> list = new DefaultListModel<Medico>();
			listMedicosAsignados.setModel(list);
		}

	}

	private JComboBox<String> getCbMes() {
		if (cbMes == null) {
			cbMes = new JComboBox<String>();
			cbMes.setModel(new DefaultComboBoxModel<String>(arrayMes()));
			cbMes.setSelectedItem(pasarMesesAEspanol(d.getMonth().toString()));
			cbMes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (asertoMesesDiasAnio())
						cbMes.setSelectedItem(pasarMesesAEspanol(d.getMonth().toString()));

					eliminarStaff();
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
					eliminarStaff();

					btnHistorial.setEnabled(false);
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
			cbHora.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					eliminarStaff();

					btnHistorial.setEnabled(false);
				}
			});
			cbHora.setModel(new DefaultComboBoxModel<String>(arrayHoras()));
			cbHora.setSelectedItem("00");
		}
		return cbHora;
	}

	protected void limpiarPaciente() {
		btAsignarPaciente.setEnabled(true);
		btEliminarPaciente.setEnabled(false);
		int i = listaPacienteAsignado.getModel().getSize();
		if (i > 0) {
			listaPacienteAsignadoModel.removeAllElements();
			DefaultListModel<Paciente> list = new DefaultListModel<Paciente>();
			listaPacienteAsignado.setModel(list);
		}

	}

	/**
	 * ComboBox minutos
	 * 
	 * @return
	 */
	private JComboBox<String> getCbMin() {
		if (cbMin == null) {
			cbMin = new JComboBox<String>();
			cbMin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					eliminarStaff();

					btnHistorial.setEnabled(false);
				}
			});
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

	private JPanel getPanelMedico() {
		if (panelMedico == null) {
			panelMedico = new JPanel();
			panelMedico
					.setBorder(new TitledBorder(null, "Paciente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelMedico.setLayout(new GridLayout(2, 0, 0, 0));
			panelMedico.add(getPnAsignar());
			panelMedico.add(getPnAsignarPaciente());
		}
		return panelMedico;
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
			cbDuracionHora.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					eliminarStaff();

					btnHistorial.setEnabled(false);
				}
			});

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
			cbDuracionMin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					eliminarStaff();

					btnHistorial.setEnabled(false);
				}
			});
			cbDuracionMin.setModel(new DefaultComboBoxModel<String>(arrayMin()));
			cbDuracionMin.setSelectedItem("00");
		}
		return cbDuracionMin;
	}

	private JPanel getPnPacienteFiltrado() {
		if (pnPacienteFiltrado == null) {
			pnPacienteFiltrado = new JPanel();
			pnPacienteFiltrado
					.setBorder(new TitledBorder(null, "Filtrado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnPacienteFiltrado.setLayout(new GridLayout(5, 0, 0, 1));
			pnPacienteFiltrado.add(getPnNombreFiltradoPaciente());
			pnPacienteFiltrado.add(getPnApellidoFiltradoPaciente());
			pnPacienteFiltrado.add(getPnNumeroDeHistorial());
			pnPacienteFiltrado.add(getPnTarjetaSanitariaFiltradoPaciente());
			pnPacienteFiltrado.add(getPnBoton());
		}
		return pnPacienteFiltrado;
	}

	private JPanel getPnNombreFiltradoPaciente() {
		if (pnNombreFiltradoPaciente == null) {
			pnNombreFiltradoPaciente = new JPanel();
			pnNombreFiltradoPaciente.setLayout(new BoxLayout(pnNombreFiltradoPaciente, BoxLayout.X_AXIS));
			pnNombreFiltradoPaciente.add(getPnNombrePacienteFiltradolbl());
			pnNombreFiltradoPaciente.add(getPnNombrePacienteFiltradoTxt());
		}
		return pnNombreFiltradoPaciente;
	}

	private JPanel getPnNombrePacienteFiltradolbl() {
		if (pnNombrePacienteFiltradolbl == null) {
			pnNombrePacienteFiltradolbl = new JPanel();
			pnNombrePacienteFiltradolbl.setLayout(new BorderLayout(0, 0));
			pnNombrePacienteFiltradolbl.add(getLblNombrePaciente(), BorderLayout.SOUTH);
		}
		return pnNombrePacienteFiltradolbl;
	}

	private JLabel getLblNombrePaciente() {
		if (lblNombrePaciente == null) {
			lblNombrePaciente = new JLabel("Nombre  ");
			lblNombrePaciente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblNombrePaciente;
	}

	private JPanel getPnApellidoFiltradoPaciente() {
		if (pnApellidoFiltradoPaciente == null) {
			pnApellidoFiltradoPaciente = new JPanel();
			pnApellidoFiltradoPaciente.setLayout(new BoxLayout(pnApellidoFiltradoPaciente, BoxLayout.X_AXIS));
			pnApellidoFiltradoPaciente.add(getPnApellidoPacienteFiltradolbl());
			pnApellidoFiltradoPaciente.add(getPnApellidoPacienteFiltradoTxt());
		}
		return pnApellidoFiltradoPaciente;
	}

	private JPanel getPnNombrePacienteFiltradoTxt() {
		if (pnNombrePacienteFiltradoTxt == null) {
			pnNombrePacienteFiltradoTxt = new JPanel();
			pnNombrePacienteFiltradoTxt.setLayout(new BorderLayout(0, 0));
			pnNombrePacienteFiltradoTxt.add(getTxtNombrePacienteFiltrado(), BorderLayout.SOUTH);
		}
		return pnNombrePacienteFiltradoTxt;
	}

	private JTextField getTxtNombrePacienteFiltrado() {
		if (txtNombrePacienteFiltrado == null) {
			txtNombrePacienteFiltrado = new JTextField();
			txtNombrePacienteFiltrado.setColumns(10);
		}
		return txtNombrePacienteFiltrado;
	}

	private JPanel getPnApellidoPacienteFiltradolbl() {
		if (pnApellidoPacienteFiltradolbl == null) {
			pnApellidoPacienteFiltradolbl = new JPanel();
			pnApellidoPacienteFiltradolbl.setLayout(new BorderLayout(0, 0));
			pnApellidoPacienteFiltradolbl.add(getLblApellidoPaciente(), BorderLayout.SOUTH);
		}
		return pnApellidoPacienteFiltradolbl;
	}

	private JLabel getLblApellidoPaciente() {
		if (lblApellidoPaciente == null) {
			lblApellidoPaciente = new JLabel("Apellidos  ");
			lblApellidoPaciente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblApellidoPaciente;
	}

	private JPanel getPnApellidoPacienteFiltradoTxt() {
		if (pnApellidoPacienteFiltradoTxt == null) {
			pnApellidoPacienteFiltradoTxt = new JPanel();
			pnApellidoPacienteFiltradoTxt.setLayout(new BorderLayout(0, 0));
			pnApellidoPacienteFiltradoTxt.add(getTxtApellidoPacienteFiltrado(), BorderLayout.SOUTH);
		}
		return pnApellidoPacienteFiltradoTxt;
	}

	private JTextField getTxtApellidoPacienteFiltrado() {
		if (txtApellidoPacienteFiltrado == null) {
			txtApellidoPacienteFiltrado = new JTextField();
			txtApellidoPacienteFiltrado.setColumns(10);
		}
		return txtApellidoPacienteFiltrado;
	}

	private JPanel getPnNumeroDeHistorial() {
		if (pnNumeroDeHistorial == null) {
			pnNumeroDeHistorial = new JPanel();
			pnNumeroDeHistorial.setLayout(new BoxLayout(pnNumeroDeHistorial, BoxLayout.X_AXIS));
			pnNumeroDeHistorial.add(getPnNumeroHistorialPacienteFiltradolbl());
			pnNumeroDeHistorial.add(getPnNumeroHistorialPacienteFiltradotxt());
		}
		return pnNumeroDeHistorial;
	}

	private JPanel getPnNumeroHistorialPacienteFiltradolbl() {
		if (pnNumeroHistorialPacienteFiltradolbl == null) {
			pnNumeroHistorialPacienteFiltradolbl = new JPanel();
			pnNumeroHistorialPacienteFiltradolbl.setLayout(new BorderLayout(0, 0));
			pnNumeroHistorialPacienteFiltradolbl.add(getLblNumeroHistorial(), BorderLayout.SOUTH);
		}
		return pnNumeroHistorialPacienteFiltradolbl;
	}

	private JLabel getLblNumeroHistorial() {
		if (lblNumeroHistorial == null) {
			lblNumeroHistorial = new JLabel("N Historial");
			lblNumeroHistorial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblNumeroHistorial;
	}

	private JPanel getPnNumeroHistorialPacienteFiltradotxt() {
		if (pnNumeroHistorialPacienteFiltradotxt == null) {
			pnNumeroHistorialPacienteFiltradotxt = new JPanel();
			pnNumeroHistorialPacienteFiltradotxt.setLayout(new BorderLayout(0, 0));
			pnNumeroHistorialPacienteFiltradotxt.add(getTxtNumeroHistorial(), BorderLayout.SOUTH);
		}
		return pnNumeroHistorialPacienteFiltradotxt;
	}

	private JTextField getTxtNumeroHistorial() {
		if (txtNumeroHistorial == null) {
			txtNumeroHistorial = new JTextField();
			txtNumeroHistorial.setColumns(10);
		}
		return txtNumeroHistorial;
	}

	private JPanel getPnTarjetaSanitariaFiltradoPaciente() {
		if (pnTarjetaSanitariaFiltradoPaciente == null) {
			pnTarjetaSanitariaFiltradoPaciente = new JPanel();
			pnTarjetaSanitariaFiltradoPaciente
					.setLayout(new BoxLayout(pnTarjetaSanitariaFiltradoPaciente, BoxLayout.X_AXIS));
			pnTarjetaSanitariaFiltradoPaciente.add(getPnCartillalPacienteFiltradolbl());
			pnTarjetaSanitariaFiltradoPaciente.add(getPnTarjetaSanitariaPacienteFiltradotxt());
		}
		return pnTarjetaSanitariaFiltradoPaciente;
	}

	private JPanel getPnCartillalPacienteFiltradolbl() {
		if (pnCartillalPacienteFiltradolbl == null) {
			pnCartillalPacienteFiltradolbl = new JPanel();
			pnCartillalPacienteFiltradolbl.setLayout(new BorderLayout(0, 0));
			pnCartillalPacienteFiltradolbl.add(getLblTarjetaSanitaria(), BorderLayout.SOUTH);
		}
		return pnCartillalPacienteFiltradolbl;
	}

	private JLabel getLblTarjetaSanitaria() {
		if (lblTarjetaSanitaria == null) {
			lblTarjetaSanitaria = new JLabel("Tarjeta Sanitaria");
			lblTarjetaSanitaria.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblTarjetaSanitaria;
	}

	private JPanel getPnTarjetaSanitariaPacienteFiltradotxt() {
		if (pnTarjetaSanitariaPacienteFiltradotxt == null) {
			pnTarjetaSanitariaPacienteFiltradotxt = new JPanel();
			pnTarjetaSanitariaPacienteFiltradotxt.setLayout(new BorderLayout(0, 0));
			pnTarjetaSanitariaPacienteFiltradotxt.add(getTxtTarjetaSanitaria(), BorderLayout.SOUTH);
		}
		return pnTarjetaSanitariaPacienteFiltradotxt;
	}

	private JTextField getTxtTarjetaSanitaria() {
		if (txtTarjetaSanitaria == null) {
			txtTarjetaSanitaria = new JTextField();
			txtTarjetaSanitaria.setColumns(10);
		}
		return txtTarjetaSanitaria;
	}

	private JPanel getPnAsignar() {
		if (pnAsignar == null) {
			pnAsignar = new JPanel();
			pnAsignar.setLayout(new BoxLayout(pnAsignar, BoxLayout.X_AXIS));
			pnAsignar.add(getPnMedicoFiltrado());
		}
		return pnAsignar;
	}

	private JPanel getPnMedicoFiltrado() {
		if (pnMedicoFiltrado == null) {
			pnMedicoFiltrado = new JPanel();
			pnMedicoFiltrado.setLayout(new GridLayout(1, 1, 0, 0));
			pnMedicoFiltrado.add(getPnPacienteFiltrado());
		}
		return pnMedicoFiltrado;
	}

	private JPanel getPnBoton() {
		if (pnBoton == null) {
			pnBoton = new JPanel();
			pnBoton.setLayout(new GridLayout(0, 1, 0, 0));
			pnBoton.add(getBtFiltrar_1_1());
		}
		return pnBoton;
	}

	private JButton getBtFiltrar_1_1() {
		if (btFiltrar == null) {
			btFiltrar = new JButton("Filtrar");
			btFiltrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clearAsignarPaciente();
					filtrarPaciente();
					activarAsignarPaciente();
				}

			});
			btFiltrar.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		return btFiltrar;
	}

	private JPanel getPnAsignarPaciente() {
		if (pnAsignarPaciente == null) {
			pnAsignarPaciente = new JPanel();
			pnAsignarPaciente.setLayout(new BoxLayout(pnAsignarPaciente, BoxLayout.X_AXIS));
			pnAsignarPaciente.add(getPnPacientes());
			pnAsignarPaciente.add(getPnBotonPacienteAsignar());
			pnAsignarPaciente.add(getPnTextoPacienteAsignado());
		}
		return pnAsignarPaciente;
	}

	private JPanel getPnPacientes() {
		if (pnPacientes == null) {
			pnPacientes = new JPanel();
			pnPacientes
					.setBorder(new TitledBorder(null, "Pacientes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnPacientes.setLayout(new BorderLayout(0, 0));
			pnPacientes.add(getScrollPane_2(), BorderLayout.CENTER);
		}
		return pnPacientes;
	}

	private JPanel getPnBotonPacienteAsignar() {
		if (pnBotonPacienteAsignar == null) {
			pnBotonPacienteAsignar = new JPanel();
			pnBotonPacienteAsignar.setLayout(new GridLayout(0, 1, 10, 10));
			pnBotonPacienteAsignar.add(getPnAsignarPacienteBoton());
			pnBotonPacienteAsignar.add(getPnEliminarPaciente());

		}
		return pnBotonPacienteAsignar;
	}

	private JPanel getPnTextoPacienteAsignado() {
		if (pnTextoPacienteAsignado == null) {
			pnTextoPacienteAsignado = new JPanel();
			pnTextoPacienteAsignado.setBorder(
					new TitledBorder(null, "Paciente Asignado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnTextoPacienteAsignado.setLayout(new BorderLayout(0, 0));
			pnTextoPacienteAsignado.add(getScrollPane_1_2(), BorderLayout.CENTER);
			pnTextoPacienteAsignado.add(getBtnHistorial(), BorderLayout.SOUTH);
		}
		return pnTextoPacienteAsignado;
	}

	private JPanel getPnAsignarPacienteBoton() {
		if (pnAsignarPacienteBoton == null) {
			pnAsignarPacienteBoton = new JPanel();
			pnAsignarPacienteBoton.setLayout(new BorderLayout(0, 0));
			pnAsignarPacienteBoton.add(getBtAsignarPaciente_1(), BorderLayout.SOUTH);
		}
		return pnAsignarPacienteBoton;
	}

	private JButton getBtAsignarPaciente_1() {
		if (btAsignarPaciente == null) {
			btAsignarPaciente = new JButton("Asignar");
			btAsignarPaciente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!horaAsignada) {
						asignarPaciente();
						btEliminarPaciente.setEnabled(true);
						btnHistorial.setEnabled(true);

					} else {
						asignarPaciente();
						validarPacienteHorario();
						btnHistorial.setEnabled(true);
					}

					btAsignarPaciente.setEnabled(false);

				}

			});
		}
		return btAsignarPaciente;
	}

	private void ponerInfoContacto() {

		txtInfoContacto.setText(
				listaPacienteAsignadoModel.get(0).getNombre() + " " + listaPacienteAsignadoModel.get(0).getApellido());
	}

	private JPanel getPnEliminarPaciente() {
		if (pnEliminarPaciente == null) {
			pnEliminarPaciente = new JPanel();
			pnEliminarPaciente.setLayout(new BorderLayout(0, 0));
			pnEliminarPaciente.add(getBtEliminarPaciente(), BorderLayout.NORTH);
		}
		return pnEliminarPaciente;
	}

	private JButton getBtEliminarPaciente() {
		if (btEliminarPaciente == null) {
			btEliminarPaciente = new JButton("Eliminar");
			btEliminarPaciente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					eliminarPacienteAsignado(listaPacienteAsignado.getSelectedValue());
					eliminarPacienteAsignado(listPacientes.getSelectedValue());
					if (listaPacienteAsignadoModel.getSize() == 0) {
						btAsignarPaciente.setEnabled(true);
						btEliminarPaciente.setEnabled(false);
						btnHistorial.setEnabled(false);

					}
				}
			});
			btEliminarPaciente.setEnabled(false);
		}
		return btEliminarPaciente;
	}

	private JScrollPane getScrollPane_2() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getListPacientes_1());
		}
		return scrollPane;
	}

	private JList<Paciente> getListPacientes_1() {
		if (listPacientes == null) {
			listPacientes = new JList<Paciente>();
			listPacientes.setBorder(new LineBorder(new Color(0, 0, 0)));
			listPacientes.setModel(principioPacientesDisponibles());
		}
		return listPacientes;
	}

	private JScrollPane getScrollPane_1_2() {
		if (scrollPanePacienteAsignado == null) {
			scrollPanePacienteAsignado = new JScrollPane();
			scrollPanePacienteAsignado.setViewportView(getLisPaciente());
		}
		return scrollPanePacienteAsignado;
	}

	private JList<Paciente> getLisPaciente() {
		if (listaPacienteAsignado == null) {
			listaPacienteAsignado = new JList<Paciente>();
			listaPacienteAsignado.setBorder(new LineBorder(new Color(0, 0, 0)));
		}
		return listaPacienteAsignado;
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

	/**
	 * Metodo que al pulsar la hora de la cita mira si esos medicos no tengan
	 * ninguan cita
	 * 
	 * @return
	 */
	protected boolean validarUbicacion() {

		Boolean validadUbicacion = true;
		Ubicacion u = (Ubicacion) cbUbicacion.getSelectedItem();
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
		if (validadUbicacion) {
			List<String> anterior = DatabaseConsultorImpl.buscarCitasEnEsaUbicacion(id_ubicacion,
					fechaLocalDate.minusDays(1));
			for (String aux : anterior) {
				String[] aux2 = aux.split(",");
				String entrada = horaEntrada + ":" + minEntrada;
				String salida = horaSalida + ":" + minSalida;
				if (mirarSitieneUnaCitaDiaAnterior(LocalTime.parse(aux2[0]), LocalTime.parse(aux2[1]),
						LocalTime.parse(entrada), LocalTime.parse(salida))) {
					warningBox("La ubicación " + u + " ya esta en uso a esa hora", "Error Ubicación");
					validadUbicacion = false;
					break;

				}
			}
		}
		filtroUbicacionesDisponibles();
		return validadUbicacion;

	}

	private boolean mirarSitieneUnaCitaDiaAnterior(LocalTime entradacita, LocalTime salidacita, LocalTime localentrada,
			LocalTime localsalida) {
		if (entradacita.isAfter(salidacita)) {
			if (localentrada.isBefore(salidacita))
				return true;
		}
		return false;
	}

	private JPanel getPnUbicaciones() {
		if (pnUbicaciones == null) {
			pnUbicaciones = new JPanel();
			pnUbicaciones.setBorder(
					new TitledBorder(null, "Ubicaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnUbicaciones.setLayout(new GridLayout(2, 1, 0, 10));
			pnUbicaciones.add(getPnUbicacionTxt());
			pnUbicaciones.add(getPnCbUbicacion());
		}
		return pnUbicaciones;
	}

	private JPanel getPnUbicacionTxt() {
		if (pnUbicacionTxt == null) {
			pnUbicacionTxt = new JPanel();
			pnUbicacionTxt.setLayout(new BorderLayout(0, 0));
			pnUbicacionTxt.add(getLblUbicacion());
		}
		return pnUbicacionTxt;
	}

	private JLabel getLblUbicacion() {
		if (lblUbicacion == null) {
			lblUbicacion = new JLabel("Ubicaci\u00F3n:");
			lblUbicacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblUbicacion;
	}

	private JPanel getPnCbUbicacion() {
		if (pnCbUbicacion == null) {
			pnCbUbicacion = new JPanel();
			pnCbUbicacion.setLayout(new BorderLayout(0, 0));
			pnCbUbicacion.add(getCbUbicacion(), BorderLayout.NORTH);
		}
		return pnCbUbicacion;
	}

	private JComboBox<Ubicacion> getCbUbicacion() {
		if (cbUbicacion == null) {
			cbUbicacion = new JComboBox<Ubicacion>();
			cbUbicacion.setModel(new DefaultComboBoxModel<Ubicacion>(ubicacionesDisponiblesPrincipio()));
		}
		return cbUbicacion;
	}

	private Ubicacion[] ubicacionesDisponiblesPrincipio() {
		List<Ubicacion> lsitasUbicacion = DatabaseConsultorImpl.buscarTodasUbicaiones();
		
		List<Ubicacion> listasCopia=new ArrayList(lsitasUbicacion);
		ubicacion.removeAllElements();
		for (Ubicacion u : lsitasUbicacion) {
			if(u.nombre.equals("not selected")) {
				listasCopia.remove(u);
			}else {
				ubicacion.addElement(u);
			}
				
		}
		
		Ubicacion[] ux = new Ubicacion[listasCopia.size()];
		return listasCopia.toArray(ux);

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
	public String pasarMesesAEspanol(String mesingles) {
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
	 * Asignar Paciente
	 */
	private void asignarPaciente() {

		if (!mirarSiYaEstaElPaciente(listPacientes.getSelectedValue()) && listaPacienteAsignadoModel.size() < 1) {
			listaPacienteAsignadoModel.addElement(listPacientes.getSelectedValue());
			listaPacienteAsignado.setModel(listaPacienteAsignadoModel);
			ponerInfoContacto();
		}

	}

	private void validarPacienteHorario() {

		Paciente p = listPacientes.getSelectedValue();
		List<String> l = DatabaseConsultorImpl.buscarCitasEnEsaCita(p.id_paciente, fechaLocalDate);
		for (String aux : l) {
			String[] aux2 = aux.split(",");
			String entrada = horaEntrada + ":" + minEntrada;
			String salida = horaSalida + ":" + minSalida;
			if (mirarSitieneUnaCitaAEsaHora(LocalTime.parse(aux2[0]), LocalTime.parse(aux2[1]),
					LocalTime.parse(entrada), LocalTime.parse(salida))) {
				warningBox("El paciente" + p + "ya tiene una cita en esa fecha", "Error Paciente");
				listaPacienteAsignadoModel.removeElement(p);
				listaPacienteAsignado.setModel(listaPacienteAsignadoModel);
				break;
			}

		}

	}

	private boolean mirarSiYaEstaElPaciente(Paciente paciente) {
		return listaPacienteAsignadoModel.contains(paciente);
	}

	/**
	 * 
	 * 
	 * /** Elimina el paciente asignado
	 * 
	 * @param paciente
	 */
	private void eliminarPacienteAsignado(Paciente paciente) {
		listaPacienteAsignadoModel.removeElement(paciente);
		listaPacienteAsignado.setModel(listaPacienteAsignadoModel);
		mirarSiHayceroPacientesAsignados();
	}

	/**
	 * Mirar si la lista de pacientes asignados para desabilitar el boton eliminar
	 */
	private void mirarSiHayceroPacientesAsignados() {
		if (listaPacienteAsignadoModel.size() == 0)
			btEliminarPaciente.setEnabled(false);

	}

//	protected void mirarAceptar() {
//		if (!listaPacienteAsignadoModel.isEmpty() && !asignadoMedico.isEmpty() && horaAsignada)
//			btnAceptar.setEnabled(true);
//		else {
//			btnAceptar.setEnabled(false);
//		}
//
//	}

	private void activarAsignarPaciente() {
		if (listaPaciente.size() == 0) {
			btAsignarPaciente.setEnabled(false);
		}
		btAsignarPaciente.setEnabled(true);

	}

	/**
	 * Clear de la Jlist de la lista del filtrado de pacientes
	 */
	private void clearAsignarPaciente() {
		if (listaPaciente != null) {
			listaPaciente.clear();
			listPacientes.setModel(listaPaciente);
		}
	}

	/**
	 * Metodo que pone los pacientes nada mas iniciar la ventana
	 * 
	 * @return
	 */
	private DefaultListModel<Paciente> principioPacientesDisponibles() {
		anadirTodosPacientesDisponibles();
		return listaPaciente;
	}

	private void anadirTodosPacientesDisponibles() {
		List<Paciente> lsitasPaciente = DatabaseConsultorImpl.todoslosPacientes();
		listaPaciente.removeAllElements();
		for (Paciente medico : lsitasPaciente) {
			this.listaPaciente.addElement(medico);
		}
	}

	/**
	 * Filtra los medicos dependiendo de los datos puesto en los campos
	 */
	void filtrarPaciente() {
		// TODAS
		if (!txtNombrePacienteFiltrado.getText().equals("") && !txtApellidoPacienteFiltrado.getText().equals("")
				&& !txtNumeroHistorial.getText().equals("") && !txtTarjetaSanitaria.getText().equals("")) {
			anadirPacienteFiltro(DatabaseConsultorImpl.buscarTodoPaciente(txtNombrePacienteFiltrado.getText(),
					txtApellidoPacienteFiltrado.getText(), txtNumeroHistorial.getText(),
					txtTarjetaSanitaria.getText()));
		}

		// Todo vacio
		else if (txtNombrePacienteFiltrado.getText().equals("") && txtApellidoPacienteFiltrado.getText().equals("")
				&& txtNumeroHistorial.getText().equals("") && txtTarjetaSanitaria.getText().equals("")) {
			listaPaciente.clear();
			listPacientes.setModel(principioPacientesDisponibles());

		}
		// Vacio menos nombre
		else if (txtApellidoPacienteFiltrado.getText().equals("") && txtNumeroHistorial.getText().equals("")
				&& txtTarjetaSanitaria.getText().equals(""))
			anadirPacienteFiltro(DatabaseConsultorImpl.buscarNombrePaciente(txtNombrePacienteFiltrado.getText()));
		// Vacia menos apellido
		else if (txtNombrePacienteFiltrado.getText().equals("") && txtNumeroHistorial.getText().equals("")
				&& txtTarjetaSanitaria.getText().equals(""))
			anadirPacienteFiltro(DatabaseConsultorImpl.buscarApellidosPaciente(txtApellidoPacienteFiltrado.getText()));
		// Vacia menos nhistorial
		else if (txtNombrePacienteFiltrado.getText().equals("") && txtApellidoPacienteFiltrado.getText().equals("")
				&& txtTarjetaSanitaria.getText().equals(""))
			anadirPacienteFiltro(DatabaseConsultorImpl.buscarNHistorialPaciente(txtNumeroHistorial.getText()));
		// Vacia menos SANITARIA
		else if (txtNombrePacienteFiltrado.getText().equals("") && txtApellidoPacienteFiltrado.getText().equals("")
				&& txtNumeroHistorial.getText().equals(""))
			anadirPacienteFiltro(DatabaseConsultorImpl.buscarSanitariaPaciente(txtTarjetaSanitaria.getText()));
		// Nombre Apellido
		else if (txtNumeroHistorial.getText().equals("") && txtTarjetaSanitaria.getText().equals(""))
			anadirPacienteFiltro(DatabaseConsultorImpl.buscarNombreApellidoPaciente(txtNombrePacienteFiltrado.getText(),
					txtApellidoPacienteFiltrado.getText()));
		// Nombre Historial
		else if (txtApellidoPacienteFiltrado.getText().equals("") && txtTarjetaSanitaria.getText().equals(""))
			anadirPacienteFiltro(DatabaseConsultorImpl
					.buscarNombreHistorialPaciente(txtNombrePacienteFiltrado.getText(), txtNumeroHistorial.getText()));
		// Nombre Sanitaria
		else if (txtApellidoPacienteFiltrado.getText().equals("") && txtNumeroHistorial.getText().equals(""))
			anadirPacienteFiltro(DatabaseConsultorImpl
					.buscarNombreSanitariaPaciente(txtNombrePacienteFiltrado.getText(), txtTarjetaSanitaria.getText()));
		// Apellido historial
		else if (txtNombrePacienteFiltrado.getText().equals("") && txtTarjetaSanitaria.getText().equals(""))
			anadirPacienteFiltro(DatabaseConsultorImpl.buscarApellidoHistorialPaciente(
					txtApellidoPacienteFiltrado.getText(), txtNumeroHistorial.getText()));
		// Apellido Sanitaria
		else if (txtNombrePacienteFiltrado.getText().equals("") && txtNumeroHistorial.getText().equals(""))
			anadirPacienteFiltro(DatabaseConsultorImpl.buscarApellidoSanitariaPaciente(
					txtApellidoPacienteFiltrado.getText(), txtTarjetaSanitaria.getText()));
		// Sanitaria Historial
		else if (txtNombrePacienteFiltrado.getText().equals("") && txtApellidoPacienteFiltrado.getText().equals(""))
			anadirPacienteFiltro(DatabaseConsultorImpl.buscarHistorialSanitariaPaciente(txtNumeroHistorial.getText(),
					txtTarjetaSanitaria.getText()));
		// Nombre Apellidos Historial
		else if (txtTarjetaSanitaria.getText().equals(""))
			anadirPacienteFiltro(DatabaseConsultorImpl.buscarNAHPaciente(txtNombrePacienteFiltrado.getText(),
					txtApellidoPacienteFiltrado.getText(), txtNumeroHistorial.getText()));
		// Nombre Apellidos Sanitario
		else if (txtNumeroHistorial.getText().equals(""))
			anadirPacienteFiltro(DatabaseConsultorImpl.buscarNASPaciente(txtNombrePacienteFiltrado.getText(),
					txtApellidoPacienteFiltrado.getText(), txtTarjetaSanitaria.getText()));
		// historial Apellidos Sanitario
		else if (txtNombrePacienteFiltrado.getText().equals(""))
			anadirPacienteFiltro(DatabaseConsultorImpl.buscarHASPaciente(txtNumeroHistorial.getText(),
					txtApellidoPacienteFiltrado.getText(), txtTarjetaSanitaria.getText()));
		// Historial nombre Sanitario
		else if (txtApellidoPacienteFiltrado.getText().equals(""))
			anadirPacienteFiltro(DatabaseConsultorImpl.buscarHNSPaciente(txtNumeroHistorial.getText(),
					txtNombrePacienteFiltrado.getText(), txtTarjetaSanitaria.getText()));

		listPacientes.setModel(listaPaciente);
	}

	private void anadirPacienteFiltro(List<Paciente> filtro) {
		List<Paciente> lsitasPaciente = filtro;
		listaPaciente.removeAllElements();
		for (Paciente paciente : lsitasPaciente) {
			this.listaPaciente.addElement(paciente);
		}

	}

	private boolean validarPaciente() {
		ponerHorario();
		if (!desactivarHorario) {
			boolean cita = false;
			if (listaPacienteAsignadoModel.size() >= 1) {
				Paciente p = listaPacienteAsignadoModel.getElementAt(0);
				List<String> l = DatabaseConsultorImpl.buscarCitasEnEsaCita(p.id_paciente, fechaLocalDate);
				for (String aux : l) {
					String[] aux2 = aux.split(",");
					String entrada = horaEntrada + ":" + minEntrada;
					String salida = horaSalida + ":" + minSalida;
					if (mirarSitieneUnaCitaAEsaHora(LocalTime.parse(aux2[0]), LocalTime.parse(aux2[1]),
							LocalTime.parse(entrada), LocalTime.parse(salida))) {
						warningBox("El paciente " + p + " ya tiene una cita en esa fecha", "Error Paciente");
						cita = true;
						break;
					}
				}
				List<String> li = DatabaseConsultorImpl.buscarCitasEnEsaCita(p.id_paciente,
						fechaLocalDate.minusDays(1));
				for (String aux : li) {
					String[] aux2 = aux.split(",");
					String entrada = horaEntrada + ":" + minEntrada;
					String salida = horaSalida + ":" + minSalida;
					if (mirarSitieneUnaCitaAnterior(LocalTime.parse(aux2[0]), LocalTime.parse(aux2[1]),
							LocalTime.parse(entrada), LocalTime.parse(salida))) {
						warningBox("El paciente " + p + " ya tiene una cita en esa fecha", "Error Paciente");
						cita = true;
						break;
					}
				}

				if (cita) {
					listaPacienteAsignadoModel.removeElement(p);
					listaPacienteAsignado.setModel(listaPacienteAsignadoModel);
				}

				validarAsignarPaciente();
			}
			return cita;
		} else {
			boolean cita = false;
			if (listaPacienteAsignadoModel.size() >= 1) {
				Paciente p = listaPacienteAsignadoModel.getElementAt(0);
				cita = DatabaseConsultorImpl.buscarCitasEnEsaCitaBoolean(p.id_paciente, fechaLocalDate);

			}
			return cita;
		}

	}

	private boolean mirarSitieneUnaCitaAnterior(LocalTime citaentrada, LocalTime salidacita, LocalTime entrada,
			LocalTime salida) {
		if (salidacita.isBefore(citaentrada)) {
			if (entrada.isBefore(salidacita))
				return true;
		}
		return false;
	}

	private void validarAsignarPaciente() {
		if (listaPacienteAsignadoModel.isEmpty()) {
			btAsignarPaciente.setEnabled(false);

		} else {
			btAsignarPaciente.setEnabled(true);
			btEliminarPaciente.setEnabled(false);
		}
	}

	protected void restablecerUbicaciones() {
		cbUbicacion.setModel(new DefaultComboBoxModel<Ubicacion>(ubicacionesDisponiblesPrincipio()));

	}

	/**
	 * Colocar todas las ubicaiones disponibles segun ese horario
	 */
	private void filtroUbicacionesDisponibles() {
		cbUbicacion.setModel(new DefaultComboBoxModel<Ubicacion>(ubicacionesDisponibles()));
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
			if (!entra2) {
				List<String> le = DatabaseConsultorImpl.buscarCitasEnEsaUbicacion(u.id_ubicacion,
						fechaLocalDate.minusDays(1));
				for (String aux : le) {
					entra = true;
					String[] aux2 = aux.split(",");
					String entrada = horaEntrada + ":" + minEntrada;
					String salida = horaSalida + ":" + minSalida;
					if (mirarSitieneUnaCitaDiaAnterior(LocalTime.parse(aux2[0]), LocalTime.parse(aux2[1]),
							LocalTime.parse(entrada), LocalTime.parse(salida))) {
						entra2 = true;
						break;
					}
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
		if (localHoraEntrada.isAfter(localHoraSalida) && horaEntrada.isAfter(horaSalida)) {
			return true;
		} else if (!localHoraEntrada.isAfter(localHoraSalida) && !horaEntrada.isAfter(horaSalida)) {
			if ((!localHoraEntrada.isBefore(horaEntrada)) && (!localHoraEntrada.isAfter(horaSalida)))
				return true;
			else if ((!localHoraSalida.isBefore(horaEntrada)) && (!localHoraSalida.isAfter(horaSalida)))
				return true;
			else if ((!localHoraEntrada.isAfter(horaEntrada)) && (!localHoraSalida.isBefore(horaSalida)))
				return true;
			else if ((!localHoraEntrada.isBefore(horaEntrada)) && (!localHoraSalida.isAfter(horaSalida)))
				return true;
		} else if (localHoraEntrada.isAfter(localHoraSalida)) {
			if (!horaSalida.isBefore(localHoraEntrada))
				return true;
		} else if (horaEntrada.isAfter(horaSalida)) {
			if (!horaEntrada.isAfter(localHoraSalida))
				return true;
		}
		return false;

	}

	private void ponerHorario() {
		// Dia
		diaHorario = cbDia.getSelectedItem().toString();

		if (diaHorario.length() == 1) {
			diaHorario = "0" + diaHorario;
		}
		mesHorario = "" + cambiarMesANumero(cbMes.getSelectedItem().toString());
		anioHorario = cbAnio.getSelectedItem().toString();
		String fecha = diaHorario + "/" + mesHorario + "/" + anioHorario;
		fechaLocalDate = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		// Hora Entrada
		localHoraEntrada = convertirHoraALocalTime();
		// Hora Salida
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
		if (!desactivarHorario) {

			LocalTime horaEntrada = convertirHoraALocalTime();
			LocalTime horaSalida = convertirHoraSalidaTime();
			if (horaEntrada.isBefore(horaSalida))
				return true;
			else {
				return false;
			}
		} else {
			return true;
		}
	}

	/**
	 * Coge la fecha de la cita
	 * 
	 * @return
	 */
	public static LocalDate getDia() {
		return fechaLocalDate;
	}

	/**
	 * Coge la hora de entrada de la cita
	 * 
	 * @return
	 */
	public static LocalTime getHoraEntrada() {
		return localHoraEntrada;
	}

	/**
	 * Coge la hora de salida de la cita
	 * 
	 * @return
	 */
	public static LocalTime getHoraSalida() {
		return localHoraSalida;
	}

	private JPanel getPnUrgencia() {
		if (pnUrgencia == null) {
			pnUrgencia = new JPanel();
			pnUrgencia
					.setBorder(new TitledBorder(null, "Urgencia", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnUrgencia.add(getChbxEsUrgente());
		}
		return pnUrgencia;
	}

	private JCheckBox getChbxEsUrgente() {
		if (chbxEsUrgente == null) {
			chbxEsUrgente = new JCheckBox("Es urgente");

			chbxEsUrgente.setMnemonic('U');
		}
		return chbxEsUrgente;
	}

	private JPanel getPnInfo() {
		if (pnInfo == null) {
			pnInfo = new JPanel();
			pnInfo.setBorder(new TitledBorder(null, "Informaci\u00F3n de contacto", TitledBorder.LEADING,
					TitledBorder.TOP, null, null));
			pnInfo.setLayout(new BorderLayout(0, 0));
			pnInfo.add(getTxtInfoContacto(), BorderLayout.CENTER);
		}
		return pnInfo;
	}

	private JTextArea getTxtInfoContacto() {
		if (txtInfoContacto == null) {
			txtInfoContacto = new JTextArea();

		}
		return txtInfoContacto;
	}

	private JPanel getPnAsignarStaff() {
		if (pnAsignarStaff == null) {
			pnAsignarStaff = new JPanel();
			pnAsignarStaff.setBorder(
					new TitledBorder(null, "Asignar Staff", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnAsignarStaff.setLayout(new GridLayout(1, 0, 0, 0));
			pnAsignarStaff.add(getBtnNewButton());
		}
		return pnAsignarStaff;
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Asginar");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Ponemos la hora para llevarla a la ventana citas:
					ponerHorario();
					VentanaAsignarMedicosCita va = new VentanaAsignarMedicosCita();
					va.setVisible(true);
				}

			});
		}
		return btnNewButton;
	}

	private JPanel getPnMedicosYenfermerosYespecialista() {
		if (PnMedicosYenfermerosYespecialista == null) {
			PnMedicosYenfermerosYespecialista = new JPanel();
			PnMedicosYenfermerosYespecialista.setLayout(new GridLayout(0, 2, 0, 0));
			PnMedicosYenfermerosYespecialista.add(getPnMedicos());
			PnMedicosYenfermerosYespecialista.add(getPnEnfermeros());
			PnMedicosYenfermerosYespecialista.add(getPnEspecialista());
		}
		return PnMedicosYenfermerosYespecialista;
	}

	private JPanel getPnMedicos() {
		if (PnMedicos == null) {
			PnMedicos = new JPanel();
			PnMedicos.setBorder(new TitledBorder(
					new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
					"Medicos Elegidos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			PnMedicos.setLayout(new BorderLayout(0, 0));
			PnMedicos.add(getScrollMedicos(), BorderLayout.CENTER);
		}
		return PnMedicos;
	}

	private JScrollPane getScrollMedicos() {
		if (scrollMedicos == null) {
			scrollMedicos = new JScrollPane();
			scrollMedicos.setViewportView(getListMedicosAsignados());
		}
		return scrollMedicos;
	}

	private JList<Medico> getListMedicosAsignados() {
		if (listMedicosAsignados == null) {
			listMedicosAsignados = new JList<Medico>();
		}
		return listMedicosAsignados;
	}

	private JPanel getPnEnfermeros() {
		if (pnEnfermeros == null) {
			pnEnfermeros = new JPanel();
			pnEnfermeros.setBorder(new TitledBorder(
					new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
					"Enfermeros Elegidos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnEnfermeros.setLayout(new BorderLayout(0, 0));
			pnEnfermeros.add(getScrollEnfermeros());
		}
		return pnEnfermeros;
	}

	private JScrollPane getScrollEnfermeros() {
		if (scrollEnfermeros == null) {
			scrollEnfermeros = new JScrollPane();
			scrollEnfermeros.setViewportView(getListEnfermeroAsignados());
		}
		return scrollEnfermeros;
	}

	private JList<Enfermero> getListEnfermeroAsignados() {
		if (listEnfermeroAsignados == null) {
			listEnfermeroAsignados = new JList<Enfermero>();
		}
		return listEnfermeroAsignados;
	}

	private JPanel getPnNoAsignarHora() {
		if (pnNoAsignarHora == null) {
			pnNoAsignarHora = new JPanel();
			pnNoAsignarHora.setBorder(new TitledBorder(
					new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
					"No asignar un horario", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnNoAsignarHora.setLayout(new BorderLayout(0, 0));
			pnNoAsignarHora.add(getBtNoAsignarHora(), BorderLayout.NORTH);
		}
		return pnNoAsignarHora;
	}

	private JButton getBtNoAsignarHora() {
		if (btNoAsignarHora == null) {
			btNoAsignarHora = new JButton("No asignar horario");
			btNoAsignarHora.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					eliminarStaff();
					// limpiarPaciente();
					// btnHistorial.setEnabled(false);
					if (!desactivarHorario)
						NoAsignarHora();
					else {
						ActivarAsignarHora();
					}
				}

			});
		}
		return btNoAsignarHora;
	}

	protected void ActivarAsignarHora() {
		activarPanelHorario();
		pnNoAsignarHora.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"No asignar un horario", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		btNoAsignarHora.setText("Desactivar Horario");
		desactivarHorario = false;

	}

	private void activarPanelHorario() {
		cbAnio.setEnabled(true);
		cbDia.setEnabled(true);
		cbDuracionHora.setEnabled(true);
		cbDuracionMin.setEnabled(true);
		cbHora.setEnabled(true);
		cbMes.setEnabled(true);
		cbMin.setEnabled(true);

	}

	private void NoAsignarHora() {
		desactivarPanelHorario();
		desactivarHorario = true;
		btNoAsignarHora.setText("Activar Horario");
		pnNoAsignarHora.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Asignar un horario", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		btNoAsignarHora.setText("Activar Horario");

	}

	private void desactivarPanelHorario() {
		// cbAnio.setEnabled(false);
		// cbDia.setEnabled(false);
		cbDuracionHora.setEnabled(false);
		cbDuracionMin.setEnabled(false);
		cbHora.setEnabled(false);
		// cbMes.setEnabled(false);
		cbMin.setEnabled(false);

	}

	private JButton getBtnHistorial() {
		if (btnHistorial == null) {
			btnHistorial = new JButton("Historial del paciente");
			btnHistorial.setEnabled(false);
			btnHistorial.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaHistorial vh = new VentanaHistorial(getPaciente());
					vh.setVisible(true);
				}
			});
		}
		return btnHistorial;
	}

	public static Paciente getPaciente() {
		return listaPacienteAsignadoModel.get(0);
	}

	private JPanel getPnEspecialista() {
		if (pnEspecialista == null) {
			pnEspecialista = new JPanel();
			pnEspecialista.setBorder(new TitledBorder(
					new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
					"Especialista Elegido", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnEspecialista.setLayout(new BorderLayout(0, 0));
			pnEspecialista.add(getScrollPaneEspecialista(), BorderLayout.CENTER);
		}
		return pnEspecialista;
	}

	private JScrollPane getScrollPaneEspecialista() {
		if (scrollPaneEspecialista == null) {
			scrollPaneEspecialista = new JScrollPane();
			scrollPaneEspecialista.setViewportView(getListEspecialista());
		}
		return scrollPaneEspecialista;
	}

	private JList<Especialista> getListEspecialista() {
		if (listEspecialista == null) {
			listEspecialista = new JList<>();
		}
		return listEspecialista;
	}
}
