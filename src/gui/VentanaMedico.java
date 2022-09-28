package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import gui.cellRenders.CitaCellRender;
import gui.cellRenders.PacienteCellRender;
import gui.cellRenders.VacunaCellRender;
import negocio.citas.Calendario;
import negocio.citas.Cita;
import negocio.consulta.DataBaseConsultor;
import negocio.consulta.impl.DatabaseConsultorImpl;
import negocio.paciente.Paciente;
import negocio.prescripcion.Vacuna;
import negocio.sanitario.Medico;

public class VentanaMedico extends JFrame {

	private static final int numeroAñosHaciaAtras = 20;

	private Calendario calendar;
	private Medico medico;

	private JPanel contentPane;

	private DefaultListModel<Cita> modelosCitas;

	private DefaultListModel<Vacuna> modeloVacunas;

	private JTabbedPane princialTabbedPane;
	private JTabbedPane tabbedPane;
	private JPanel panelCitasMedico;
	private JPanel panelCalendario;
	private JPanel panelIntroducirElcalendario;
	private JLabel lblNewLabel;
	private JPanel panelSeleccionDias;
	private JLabel lblNewLabel_1;
	private JComboBox comboBoxDias;
	private JLabel lblNewLabel_2;
	private JComboBox comboBoxMes;
	private JLabel lblNewLabel_3;
	private JComboBox comboBoxAño;
	private JScrollPane scrollPane;

	private JList<Cita> list;

	private JButton btnAceptarCalendario;
	private JPanel paneleditarCitas;
	private JButton btnEditarCitas;
	private JPanel panelAñadirCitas;
	private JPanel pnPacienteFiltrado;
	private JPanel pnNombreFiltradoPaciente;
	private JPanel pnApellidoFiltradoPaciente;
	private JPanel pnNumeroHistorialPacienteFiltradolbl;
	private JPanel pnTarjetaSanitariaFiltradoPaciente;
	private JButton btFiltrar;
	private JPanel pnNombrePacienteFiltradolbl;
	private JPanel pnNombrePacienteFiltradoTxt;
	private JLabel lblNombrePaciente;
	private JTextField txtNombrePacienteFiltrado;
	private JPanel pnApellidoPacienteFiltradolbl;
	private JPanel pnApellidoPacienteFiltradoTxt_1;
	private JLabel lblApellidoPaciente;
	private JTextField txtApellidoPacienteFiltrado;
	private JPanel pnNHistorialPacienteFiltradolbl_1;
	private JPanel pnNHisotrialPacienteFiltradoTxt_1_1;
	private JLabel lblNumeroHistorial;
	private JTextField txtNumeroHistorial;
	private JPanel pnTarjetaPacienteFiltradolbl;
	private JPanel pnTarjetaPacienteFiltradoTxt;
	private JLabel lblTarjetaSanitaria;
	private JTextField txtTarjetaSanitaria;
	private JPanel panelMostrarPacientes;
	private JScrollPane scrollPanePacientes;
	private JList listPacientes;
	private JPanel panelSeleccionar;
	private JButton btnSeleccionarPaciente;
	private JButton btnNewButton;

	private DefaultListModel<Paciente> modeloPacientes = new DefaultListModel<>();

	private Paciente pacienteSeleccionado = null;
	private JPanel panelSeleccionadoPaciente;
	private JLabel lblSelecionadoPAciente;
	private JTextField textFieldPacienteSeleccionado;
	private JButton btnNewButton_1;
	private JPanel panelVacunas;
	private JPanel panelvacunasCAlendario;
	private JPanel panelSeleccionDias_1;
	private JLabel lblNewLabel_1_1;
	private JComboBox comboBoxDias_1;
	private JLabel lblNewLabel_2_1;
	private JComboBox comboBoxMes_1;
	private JLabel lblNewLabel_3_1;
	private JComboBox comboBoxAño_1;
	private JButton btnAceptarCalendario_1;
	private JLabel lblNewLabel_4;
	private JScrollPane scrollPaneVacunas;
	private JList<Vacuna> listVacunas;

	/**
	 * Create the frame.
	 */
	public VentanaMedico(Medico medico) {
		this.medico = medico;
		calendar = new Calendario(numeroAñosHaciaAtras, 2020);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		this.setTitle("Ventana para médico");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getTabbedPane_1(), BorderLayout.CENTER);
		valoresDiasPorDefecto();

	}

	private void valoresDiasPorDefecto() {
		rellenarComboboxes();
		LocalDate local = LocalDate.now();

		int day = local.getDayOfMonth();
		int month = local.getMonthValue();
		int year = local.getYear();

		this.comboBoxDias.setSelectedItem(day);
		this.comboBoxMes.setSelectedIndex(month - 1);
		this.comboBoxAño.setSelectedItem(year);
		
		this.comboBoxDias_1.setSelectedItem(day);
		this.comboBoxMes_1.setSelectedIndex(month - 1);
		this.comboBoxAño_1.setSelectedItem(year);

	}

	private JTabbedPane getTabbedPane() {
		if (princialTabbedPane == null) {
			princialTabbedPane = new JTabbedPane(JTabbedPane.TOP);

			JTabbedPane princialTabbedPane = new JTabbedPane(JTabbedPane.TOP);

			princialTabbedPane.addTab("Tab1", makePanel("This is tab 1"));
			princialTabbedPane.addTab("Tab2", makePanel("This is tab 2"));
		}
		return princialTabbedPane;
	}

	private static JPanel makePanel(String text) {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 1));
		return p;
	}

	private JTabbedPane getTabbedPane_1() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			// tabbedPane.addTab("Citas", null, getPanelCitasMedico(), null);
			tabbedPane.addTab("Bucador Citas", null, getPanelCalendario(), null);
			tabbedPane.addTab("Añadir Citas", null, getPanelAñadirCitas(), null);
			tabbedPane.addTab("Vacunas", null, getPanelVacunas(), null);
		}
		return tabbedPane;
	}

	private JPanel getPanelCitasMedico() {
		if (panelCitasMedico == null) {
			panelCitasMedico = new JPanel();
			panelCitasMedico.setBackground(Color.WHITE);
		}
		return panelCitasMedico;
	}

	private JPanel getPanelCalendario() {
		if (panelCalendario == null) {
			panelCalendario = new JPanel();
			panelCalendario.setBackground(Color.WHITE);
			panelCalendario.setLayout(new BorderLayout(0, 0));
			panelCalendario.add(getPanelIntroducirElcalendario(), BorderLayout.WEST);
			panelCalendario.add(getScrollPane(), BorderLayout.CENTER);
			panelCalendario.add(getPaneleditarCitas(), BorderLayout.SOUTH);
		}
		return panelCalendario;
	}

	private JPanel getPanelIntroducirElcalendario() {
		if (panelIntroducirElcalendario == null) {
			panelIntroducirElcalendario = new JPanel();
			panelIntroducirElcalendario.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panelIntroducirElcalendario.setBackground(Color.WHITE);
			panelIntroducirElcalendario.setLayout(new BorderLayout(0, 0));
			panelIntroducirElcalendario.add(getLblNewLabel(), BorderLayout.NORTH);
			panelIntroducirElcalendario.add(getPanelSeleccionDias(), BorderLayout.CENTER);
		}
		return panelIntroducirElcalendario;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Introducir el d\u00EDa para listar citas");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblNewLabel;
	}

	private JPanel getPanelSeleccionDias() {
		if (panelSeleccionDias == null) {
			panelSeleccionDias = new JPanel();
			panelSeleccionDias.setBorder(new LineBorder(new Color(0, 0, 0)));
			panelSeleccionDias.setBackground(Color.WHITE);
			panelSeleccionDias.setLayout(new GridLayout(7, 1, 0, 0));
			panelSeleccionDias.add(getLblNewLabel_1());
			panelSeleccionDias.add(getComboBoxDias());
			panelSeleccionDias.add(getLblNewLabel_2());
			panelSeleccionDias.add(getComboBoxMes());
			panelSeleccionDias.add(getLblNewLabel_3());
			panelSeleccionDias.add(getComboBoxAño());
			panelSeleccionDias.add(getBtnAceptarCalendario());
			
		}
		return panelSeleccionDias;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("D\u00EDa");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		}
		return lblNewLabel_1;
	}
	
	private void rellenarComboboxes() {
		defaultComboboxDiaRelleno();
		defaultComboboxMesRelleno();
		defaultComboboxAñoRelleno();
	}

	private JComboBox getComboBoxDias() {
		if (comboBoxDias == null) {
			comboBoxDias = new JComboBox();
			comboBoxDias.setFont(new Font("Tahoma", Font.PLAIN, 21));
			comboBoxDias.setBackground(SystemColor.controlHighlight);
			comboBoxDias.setBorder(new MatteBorder(1, 0, 1, 0, Color.DARK_GRAY));
			comboBoxDias.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {

					rellenarMesSegunDia((int) comboBoxDias.getSelectedItem());
				}
			});
		}
		return comboBoxDias;
	}

	private void defaultComboboxDiaRelleno() {
		for (int i = 1; i < 32; i++) {
			comboBoxDias.addItem(i);
			comboBoxDias_1.addItem(i);
		}
	}

	private void defaultComboboxAñoRelleno() {
		for (Integer inter : calendar.getAñosList()) {
			comboBoxAño.addItem(inter);
			comboBoxAño_1.addItem(inter);
		}
	}

	private void rellenarMesSegunDia(int dia) {
		List<String> strings = calendar.getMeses(dia);
		if (strings != null) {
			comboBoxMes.removeAllItems();
			for (String str : strings) {
				comboBoxMes.addItem(str);
			}
		}
	}

	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("Mes");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		}
		return lblNewLabel_2;
	}

	private JComboBox getComboBoxMes() {
		if (comboBoxMes == null) {
			comboBoxMes = new JComboBox();
			comboBoxMes.setFont(new Font("Tahoma", Font.PLAIN, 21));
			comboBoxMes.setBackground(SystemColor.controlHighlight);
			comboBoxMes.setBorder(new MatteBorder(1, 0, 1, 0, Color.DARK_GRAY));
			comboBoxMes.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					rellenarComboDiasSegunMes((String) comboBoxMes.getSelectedItem());
				}
			});

		}
		return comboBoxMes;
	}

	public void rellenarComboDiasSegunMes(String str) {
		int dias = calendar.getDiasPorMes(str);
		if (dias > -1 && dias < Integer.parseInt(comboBoxDias.getSelectedItem().toString())) {
			comboBoxDias.removeAllItems();
			for (int i = 1; i <= dias; i++) {
				comboBoxDias.addItem(i);
			}
		}
	}

	private void defaultComboboxMesRelleno() {
		for (String str : calendar.getMesList()) {
			comboBoxMes.addItem(str);
			comboBoxMes_1.addItem(str);
		}
	}

	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("A\u00F1o");
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 30));
		}
		return lblNewLabel_3;
	}

	private JComboBox getComboBoxAño() {
		if (comboBoxAño == null) {
			comboBoxAño = new JComboBox();
			comboBoxAño.setFont(new Font("Tahoma", Font.PLAIN, 21));
			comboBoxAño.setBackground(SystemColor.controlHighlight);
			comboBoxAño.setBorder(new MatteBorder(1, 0, 1, 0, Color.DARK_GRAY));
			comboBoxAño.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					configureCurrentyear((int) comboBoxAño.getSelectedItem());
				}
			});
		}
		return comboBoxAño;
	}

	protected void configureCurrentyear(int selectedItem) {
		calendar.setCurrentYear(selectedItem);

	}

	private boolean checkComboDaysAreReady() {
		return comboBoxDias.getSelectedIndex() > -1 && comboBoxAño.getSelectedIndex() > -1
				&& comboBoxMes.getSelectedIndex() > -1;
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
			list = new JList<Cita>();
			list.setFont(new Font("Tahoma", Font.PLAIN, 16));
			list.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JList list = (JList) e.getSource();
					int index = list.locationToIndex(e.getPoint());
					VentanaEditarCitaExistente vce = new VentanaEditarCitaExistente(medico,
							(Cita) list.getSelectedValue());

				}
			});
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			modelosCitas = new DefaultListModel<Cita>();
			list.setModel(modelosCitas);
			list.setCellRenderer(new CitaCellRender());

		}
		return list;
	}

//	VentanaEditarCitaExistente vce=
//			new VentanaEditarCitaExistente(medico, (Cita) list.getSelectedValue());

	private JButton getBtnAceptarCalendario() {
		if (btnAceptarCalendario == null) {
			btnAceptarCalendario = new JButton("Aceptar");
			btnAceptarCalendario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					LocalDate date = getLocalDateFromCombo();
					if (date != null) {

						doAddCitasToJList(date);

					}

				}
			});
			btnAceptarCalendario.setBackground(Color.GREEN);
		}
		return btnAceptarCalendario;
	}

	protected void doAddCitasToJList(LocalDate date) {
		DatabaseConsultorImpl dbc = new DatabaseConsultorImpl();

		List<Cita> citas = dbc.getCitasFromCalendario(medico, date);
		if (citas != null && citas.size() > 0) {
			modelosCitas.removeAllElements();

			for (Cita cita : citas) {
				modelosCitas.addElement(cita);
			}
			btnAceptarCalendario.setBackground(Color.green);
		} else {
			btnAceptarCalendario.setBackground(Color.RED);
			JOptionPane.showMessageDialog(this, "No se encontraron paciente esa fecha!");
		}

	}

	protected LocalDate getLocalDateFromCombo() {

		if (checkComboDaysAreReady()) {
			return LocalDate.of(((int) comboBoxAño.getSelectedItem()),
					Month.of(getMesNumero((String) comboBoxMes.getSelectedItem())),
					(int) comboBoxDias.getSelectedItem());
		}
		return null;
	}

	private int getMesNumero(String selectedItem) {
		return calendar.getMesnumero(selectedItem);
	}

	private JPanel getPaneleditarCitas() {
		if (paneleditarCitas == null) {
			paneleditarCitas = new JPanel();
			paneleditarCitas.setLayout(new BorderLayout(0, 0));
			paneleditarCitas.add(getBtnEditarCitas());

		}
		return paneleditarCitas;
	}

	private JButton getBtnEditarCitas() {
		if (btnEditarCitas == null) {
			btnEditarCitas = new JButton("Editar Cita");
			btnEditarCitas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Cita cita = list.getSelectedValue();
					VentanaEditarCitaExistente vce = new VentanaEditarCitaExistente(medico, cita);
					vce.setVisible(true);
				}
			});
			btnEditarCitas.setBackground(Color.ORANGE);
		}
		return btnEditarCitas;
	}

	private JPanel getPanelAñadirCitas() {
		if (panelAñadirCitas == null) {
			panelAñadirCitas = new JPanel();
			panelAñadirCitas.setBackground(Color.WHITE);
			panelAñadirCitas.setLayout(null);
			panelAñadirCitas.add(getPnPacienteFiltrado());
			panelAñadirCitas.add(getPanelMostrarPacientes());
			panelAñadirCitas.add(getPanelSeleccionadoPaciente());
			panelAñadirCitas.add(getBtnNewButton_1());
		}
		return panelAñadirCitas;
	}

	private JPanel getPnPacienteFiltrado() {
		if (pnPacienteFiltrado == null) {
			pnPacienteFiltrado = new JPanel();
			// pnPacienteFiltrado.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			pnPacienteFiltrado
					.setBorder(new TitledBorder(null, "Filtro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnPacienteFiltrado.setBounds(10, 10, 472, 243);
			pnPacienteFiltrado.setLayout(new GridLayout(5, 1, 0, 0));
			pnPacienteFiltrado.add(getPnNombreFiltradoPaciente());
			pnPacienteFiltrado.add(getPnApellidoFiltradoPaciente());
			pnPacienteFiltrado.add(getPnNumeroHistorialPacienteFiltradolbl());
			pnPacienteFiltrado.add(getPnTarjetaSanitariaFiltradoPaciente());
			pnPacienteFiltrado.add(getBtFiltrar());
		}
		return pnPacienteFiltrado;
	}

	private JPanel getPnNombreFiltradoPaciente() {
		if (pnNombreFiltradoPaciente == null) {
			pnNombreFiltradoPaciente = new JPanel();
			pnNombreFiltradoPaciente.setLayout(new BorderLayout(0, 0));
			pnNombreFiltradoPaciente.add(getPnNombrePacienteFiltradolbl(), BorderLayout.WEST);
			pnNombreFiltradoPaciente.add(getPnNombrePacienteFiltradoTxt());
		}
		return pnNombreFiltradoPaciente;
	}

	private JPanel getPnApellidoFiltradoPaciente() {
		if (pnApellidoFiltradoPaciente == null) {
			pnApellidoFiltradoPaciente = new JPanel();
			pnApellidoFiltradoPaciente.setLayout(new BorderLayout(0, 0));
			pnApellidoFiltradoPaciente.add(getPnApellidoPacienteFiltradolbl(), BorderLayout.WEST);
			pnApellidoFiltradoPaciente.add(getPnApellidoPacienteFiltradoTxt_1());
		}
		return pnApellidoFiltradoPaciente;
	}

	private JPanel getPnNumeroHistorialPacienteFiltradolbl() {
		if (pnNumeroHistorialPacienteFiltradolbl == null) {
			pnNumeroHistorialPacienteFiltradolbl = new JPanel();
			pnNumeroHistorialPacienteFiltradolbl.setLayout(new BorderLayout(0, 0));
			pnNumeroHistorialPacienteFiltradolbl.add(getPnNHistorialPacienteFiltradolbl_1(), BorderLayout.WEST);
			pnNumeroHistorialPacienteFiltradolbl.add(getPnNHisotrialPacienteFiltradoTxt_1_1());
		}
		return pnNumeroHistorialPacienteFiltradolbl;
	}

	private JPanel getPnTarjetaSanitariaFiltradoPaciente() {
		if (pnTarjetaSanitariaFiltradoPaciente == null) {
			pnTarjetaSanitariaFiltradoPaciente = new JPanel();
			pnTarjetaSanitariaFiltradoPaciente.setLayout(new BorderLayout(0, 0));
			pnTarjetaSanitariaFiltradoPaciente.add(getPnTarjetaPacienteFiltradolbl(), BorderLayout.WEST);
			pnTarjetaSanitariaFiltradoPaciente.add(getPnTarjetaPacienteFiltradoTxt());
		}
		return pnTarjetaSanitariaFiltradoPaciente;
	}

	private JButton getBtFiltrar() {
		if (btFiltrar == null) {
			btFiltrar = new JButton("Filtrar");
			btFiltrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					prepararListPacientes();
					filtrarPacientes();
				}
			});
		}
		return btFiltrar;
	}

	private void añadirPacienteFiltro(List<Paciente> filtro) {
		modeloPacientes.clear();
		if (filtro.size() == 0) {
			JOptionPane.showMessageDialog(this, "No se ha encontrado ningun paciente según esos criterios");
			return;
		}
		for (Paciente paciente : filtro) {
			this.modeloPacientes.addElement(paciente);
		}

	}

	private DefaultListModel<Paciente> principioPacientesDisponibles() {
		añadirTodosPacientesDisponibles();
		return modeloPacientes;
	}

	private void añadirTodosPacientesDisponibles() {
		List<Paciente> lsitasPaciente = DatabaseConsultorImpl.todoslosPacientes();
		modeloPacientes.clear();
		for (Paciente paciente : lsitasPaciente) {
			this.modeloPacientes.addElement(paciente);
		}
	}

	protected void filtrarPacientes() {
		// TODAS
		if (!txtNombrePacienteFiltrado.getText().equals("") && !txtApellidoPacienteFiltrado.getText().equals("")
				&& !txtNumeroHistorial.getText().equals("") && !txtTarjetaSanitaria.getText().equals("")) {
			añadirPacienteFiltro(DatabaseConsultorImpl.buscarTodoPaciente(txtNombrePacienteFiltrado.getText(),
					txtApellidoPacienteFiltrado.getText(), txtNumeroHistorial.getText(),
					txtTarjetaSanitaria.getText()));
		}

		// Todo vacio
		else if (txtNombrePacienteFiltrado.getText().equals("") && txtApellidoPacienteFiltrado.getText().equals("")
				&& txtNumeroHistorial.getText().equals("") && txtTarjetaSanitaria.getText().equals("")) {
			listPacientes.setModel(principioPacientesDisponibles());

		}
		// Vacio menos nombre
		else if (txtApellidoPacienteFiltrado.getText().equals("") && txtNumeroHistorial.getText().equals("")
				&& txtTarjetaSanitaria.getText().equals(""))
			añadirPacienteFiltro(DatabaseConsultorImpl.buscarNombrePaciente(txtNombrePacienteFiltrado.getText()));
		// Vacia menos apellido
		else if (txtNombrePacienteFiltrado.getText().equals("") && txtNumeroHistorial.getText().equals("")
				&& txtTarjetaSanitaria.getText().equals(""))
			añadirPacienteFiltro(DatabaseConsultorImpl.buscarApellidosPaciente(txtApellidoPacienteFiltrado.getText()));
		// Vacia menos nhistorial
		else if (txtNombrePacienteFiltrado.getText().equals("") && txtApellidoPacienteFiltrado.getText().equals("")
				&& txtTarjetaSanitaria.getText().equals(""))
			añadirPacienteFiltro(DatabaseConsultorImpl.buscarNHistorialPaciente(txtNumeroHistorial.getText()));
		// Vacia menos SANITARIA
		else if (txtNombrePacienteFiltrado.getText().equals("") && txtApellidoPacienteFiltrado.getText().equals("")
				&& txtNumeroHistorial.getText().equals(""))
			añadirPacienteFiltro(DatabaseConsultorImpl.buscarSanitariaPaciente(txtTarjetaSanitaria.getText()));
		// Nombre Apellido
		else if (txtNumeroHistorial.getText().equals("") && txtTarjetaSanitaria.getText().equals(""))
			añadirPacienteFiltro(DatabaseConsultorImpl.buscarNombreApellidoPaciente(txtNombrePacienteFiltrado.getText(),
					txtApellidoPacienteFiltrado.getText()));
		// Nombre Historial
		else if (txtApellidoPacienteFiltrado.getText().equals("") && txtTarjetaSanitaria.getText().equals(""))
			añadirPacienteFiltro(DatabaseConsultorImpl
					.buscarNombreHistorialPaciente(txtNombrePacienteFiltrado.getText(), txtNumeroHistorial.getText()));
		// Nombre Sanitaria
		else if (txtApellidoPacienteFiltrado.getText().equals("") && txtNumeroHistorial.getText().equals(""))
			añadirPacienteFiltro(DatabaseConsultorImpl
					.buscarNombreSanitariaPaciente(txtNombrePacienteFiltrado.getText(), txtTarjetaSanitaria.getText()));
		// Apellido historial
		else if (txtNombrePacienteFiltrado.getText().equals("") && txtTarjetaSanitaria.getText().equals(""))
			añadirPacienteFiltro(DatabaseConsultorImpl.buscarApellidoHistorialPaciente(
					txtApellidoPacienteFiltrado.getText(), txtNumeroHistorial.getText()));
		// Apellido Sanitaria
		else if (txtNombrePacienteFiltrado.getText().equals("") && txtNumeroHistorial.getText().equals(""))
			añadirPacienteFiltro(DatabaseConsultorImpl.buscarApellidoSanitariaPaciente(
					txtApellidoPacienteFiltrado.getText(), txtTarjetaSanitaria.getText()));
		// Sanitaria Historial
		else if (txtNombrePacienteFiltrado.getText().equals("") && txtApellidoPacienteFiltrado.getText().equals(""))
			añadirPacienteFiltro(DatabaseConsultorImpl.buscarHistorialSanitariaPaciente(txtNumeroHistorial.getText(),
					txtTarjetaSanitaria.getText()));
		// Nombre Apellidos Historial
		else if (txtTarjetaSanitaria.getText().equals(""))
			añadirPacienteFiltro(DatabaseConsultorImpl.buscarNAHPaciente(txtNombrePacienteFiltrado.getText(),
					txtApellidoPacienteFiltrado.getText(), txtNumeroHistorial.getText()));
		// Nombre Apellidos Sanitario
		else if (txtNumeroHistorial.getText().equals(""))
			añadirPacienteFiltro(DatabaseConsultorImpl.buscarNASPaciente(txtNombrePacienteFiltrado.getText(),
					txtApellidoPacienteFiltrado.getText(), txtTarjetaSanitaria.getText()));
		// historial Apellidos Sanitario
		else if (txtNombrePacienteFiltrado.getText().equals(""))
			añadirPacienteFiltro(DatabaseConsultorImpl.buscarHASPaciente(txtNumeroHistorial.getText(),
					txtApellidoPacienteFiltrado.getText(), txtTarjetaSanitaria.getText()));
		// Historial nombre Sanitario
		else if (txtApellidoPacienteFiltrado.getText().equals(""))
			añadirPacienteFiltro(DatabaseConsultorImpl.buscarHNSPaciente(txtNumeroHistorial.getText(),
					txtNombrePacienteFiltrado.getText(), txtTarjetaSanitaria.getText()));
		else {

		}

	}

	protected void prepararListPacientes() {
		modeloPacientes.clear();

	}

	private JPanel getPnNombrePacienteFiltradolbl() {
		if (pnNombrePacienteFiltradolbl == null) {
			pnNombrePacienteFiltradolbl = new JPanel();
			pnNombrePacienteFiltradolbl.setLayout(new BorderLayout(0, 0));
			pnNombrePacienteFiltradolbl.add(getLblNombrePaciente(), BorderLayout.SOUTH);
		}
		return pnNombrePacienteFiltradolbl;
	}

	private JPanel getPnNombrePacienteFiltradoTxt() {
		if (pnNombrePacienteFiltradoTxt == null) {
			pnNombrePacienteFiltradoTxt = new JPanel();
			pnNombrePacienteFiltradoTxt.setLayout(new BorderLayout(0, 0));
			pnNombrePacienteFiltradoTxt.add(getTxtNombrePacienteFiltrado(), BorderLayout.CENTER);
		}
		return pnNombrePacienteFiltradoTxt;
	}

	private JLabel getLblNombrePaciente() {
		if (lblNombrePaciente == null) {
			lblNombrePaciente = new JLabel("Nombre: ");
			lblNombrePaciente.setFont(new Font("Tahoma", Font.PLAIN, 24));
		}
		return lblNombrePaciente;
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

	private JPanel getPnApellidoPacienteFiltradoTxt_1() {
		if (pnApellidoPacienteFiltradoTxt_1 == null) {
			pnApellidoPacienteFiltradoTxt_1 = new JPanel();
			pnApellidoPacienteFiltradoTxt_1.setLayout(new BorderLayout(0, 0));
			pnApellidoPacienteFiltradoTxt_1.add(getTxtApellidoPacienteFiltrado(), BorderLayout.CENTER);
		}
		return pnApellidoPacienteFiltradoTxt_1;
	}

	private JLabel getLblApellidoPaciente() {
		if (lblApellidoPaciente == null) {
			lblApellidoPaciente = new JLabel("Apellidos: ");
			lblApellidoPaciente.setFont(new Font("Tahoma", Font.PLAIN, 24));
		}
		return lblApellidoPaciente;
	}

	private JTextField getTxtApellidoPacienteFiltrado() {
		if (txtApellidoPacienteFiltrado == null) {
			txtApellidoPacienteFiltrado = new JTextField();
			txtApellidoPacienteFiltrado.setColumns(10);
		}
		return txtApellidoPacienteFiltrado;
	}

	private JPanel getPnNHistorialPacienteFiltradolbl_1() {
		if (pnNHistorialPacienteFiltradolbl_1 == null) {
			pnNHistorialPacienteFiltradolbl_1 = new JPanel();
			pnNHistorialPacienteFiltradolbl_1.setLayout(new BorderLayout(0, 0));
			pnNHistorialPacienteFiltradolbl_1.add(getLblNumeroHistorial(), BorderLayout.WEST);
		}
		return pnNHistorialPacienteFiltradolbl_1;
	}

	private JPanel getPnNHisotrialPacienteFiltradoTxt_1_1() {
		if (pnNHisotrialPacienteFiltradoTxt_1_1 == null) {
			pnNHisotrialPacienteFiltradoTxt_1_1 = new JPanel();
			pnNHisotrialPacienteFiltradoTxt_1_1.setLayout(new BorderLayout(0, 0));
			pnNHisotrialPacienteFiltradoTxt_1_1.add(getTxtNumeroHistorial(), BorderLayout.CENTER);
		}
		return pnNHisotrialPacienteFiltradoTxt_1_1;
	}

	private JLabel getLblNumeroHistorial() {
		if (lblNumeroHistorial == null) {
			lblNumeroHistorial = new JLabel("Numero Historial: ");
			lblNumeroHistorial.setFont(new Font("Tahoma", Font.PLAIN, 24));
		}
		return lblNumeroHistorial;
	}

	private JTextField getTxtNumeroHistorial() {
		if (txtNumeroHistorial == null) {
			txtNumeroHistorial = new JTextField();
			txtNumeroHistorial.setColumns(10);
		}
		return txtNumeroHistorial;
	}

	private JPanel getPnTarjetaPacienteFiltradolbl() {
		if (pnTarjetaPacienteFiltradolbl == null) {
			pnTarjetaPacienteFiltradolbl = new JPanel();
			pnTarjetaPacienteFiltradolbl.setLayout(new BorderLayout(0, 0));
			pnTarjetaPacienteFiltradolbl.add(getLblTarjetaSanitaria());
		}
		return pnTarjetaPacienteFiltradolbl;
	}

	private JPanel getPnTarjetaPacienteFiltradoTxt() {
		if (pnTarjetaPacienteFiltradoTxt == null) {
			pnTarjetaPacienteFiltradoTxt = new JPanel();
			pnTarjetaPacienteFiltradoTxt.setLayout(new BorderLayout(0, 0));
			pnTarjetaPacienteFiltradoTxt.add(getTextField_1());
		}
		return pnTarjetaPacienteFiltradoTxt;
	}

	private JLabel getLblTarjetaSanitaria() {
		if (lblTarjetaSanitaria == null) {
			lblTarjetaSanitaria = new JLabel("Tarjeta Sanitaria: ");
			lblTarjetaSanitaria.setFont(new Font("Tahoma", Font.PLAIN, 24));
		}
		return lblTarjetaSanitaria;
	}

	private JTextField getTextField_1() {
		if (txtTarjetaSanitaria == null) {
			txtTarjetaSanitaria = new JTextField();
			txtTarjetaSanitaria.setColumns(10);
		}
		return txtTarjetaSanitaria;
	}

	private JPanel getPanelMostrarPacientes() {
		if (panelMostrarPacientes == null) {
			panelMostrarPacientes = new JPanel();
			panelMostrarPacientes.setBounds(10, 263, 472, 253);
			panelMostrarPacientes
					.setBorder(new TitledBorder(null, "Pacientes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelMostrarPacientes.setLayout(new BorderLayout(0, 0));
			panelMostrarPacientes.add(getScrollPane_1_1(), BorderLayout.CENTER);
			panelMostrarPacientes.add(getPanelSeleccionar(), BorderLayout.SOUTH);
		}
		return panelMostrarPacientes;
	}

	private JScrollPane getScrollPane_1_1() {
		if (scrollPanePacientes == null) {
			scrollPanePacientes = new JScrollPane();
			scrollPanePacientes.setViewportView(getListPacientes());
		}
		return scrollPanePacientes;
	}

	private JList getListPacientes() {
		if (listPacientes == null) {
			listPacientes = new JList();
			listPacientes.setModel(modeloPacientes);
			listPacientes.setCellRenderer(new PacienteCellRender());
		}
		return listPacientes;
	}

	private JPanel getPanelSeleccionar() {
		if (panelSeleccionar == null) {
			panelSeleccionar = new JPanel();
			panelSeleccionar.add(getBtnSeleccionarPaciente());
			panelSeleccionar.add(getBtnNewButton());
		}
		return panelSeleccionar;
	}

	private JButton getBtnSeleccionarPaciente() {
		if (btnSeleccionarPaciente == null) {
			btnSeleccionarPaciente = new JButton("Seleccionar Paciente");
			btnSeleccionarPaciente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Paciente paciente = (Paciente) listPacientes.getSelectedValue();
					if (paciente != null) {
						setSeleccionarPaciente(paciente);
					}
				}
			});
		}
		return btnSeleccionarPaciente;
	}

	protected void setSeleccionarPaciente(Paciente paciente) {
		this.pacienteSeleccionado = paciente;
		textFieldPacienteSeleccionado.setText(paciente.toStringParaJList());

	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Deseleccionar Paciente");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					deseleccionarPaciente();
				}
			});
		}
		return btnNewButton;
	}

	protected void deseleccionarPaciente() {
		this.pacienteSeleccionado = null;
		this.textFieldPacienteSeleccionado.setText("<<No hay ningún paciente seleccionado>>");

	}

	private JPanel getPanelSeleccionadoPaciente() {
		if (panelSeleccionadoPaciente == null) {
			panelSeleccionadoPaciente = new JPanel();
			panelSeleccionadoPaciente.setBounds(492, 10, 469, 103);
			panelSeleccionadoPaciente.setLayout(new BorderLayout(0, 0));
			panelSeleccionadoPaciente.add(getLblSelecionadoPAciente(), BorderLayout.NORTH);
			panelSeleccionadoPaciente.add(getTextFieldPacienteSeleccionado(), BorderLayout.CENTER);
		}
		return panelSeleccionadoPaciente;
	}

	private JLabel getLblSelecionadoPAciente() {
		if (lblSelecionadoPAciente == null) {
			lblSelecionadoPAciente = new JLabel("Paciente Seleccionado:");
			lblSelecionadoPAciente.setHorizontalAlignment(SwingConstants.CENTER);
			lblSelecionadoPAciente.setFont(new Font("Tahoma", Font.PLAIN, 22));
		}
		return lblSelecionadoPAciente;
	}

	private JTextField getTextFieldPacienteSeleccionado() {
		if (textFieldPacienteSeleccionado == null) {
			textFieldPacienteSeleccionado = new JTextField();
			textFieldPacienteSeleccionado.setFont(new Font("Tahoma", Font.PLAIN, 20));
			textFieldPacienteSeleccionado.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldPacienteSeleccionado.setText("<<No hay ning\u00FAn paciente seleccionado>>");
			textFieldPacienteSeleccionado.setColumns(10);
		}
		return textFieldPacienteSeleccionado;
	}

	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("Abrir Ventana Crear Cita");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					lanzarCrearCitaMedico();
				}
			});
			btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnNewButton_1.setBounds(572, 248, 326, 103);
		}
		return btnNewButton_1;
	}

	protected void lanzarCrearCitaMedico() {
		if (pacienteSeleccionado != null) {

			VentanaCitasDesdeMedico vcm = new VentanaCitasDesdeMedico(this.medico, this.pacienteSeleccionado);
			vcm.show();
		}

	}

	private JPanel getPanelVacunas() {
		if (panelVacunas == null) {
			panelVacunas = new JPanel();
			panelVacunas.setLayout(new BorderLayout(0, 0));
			panelVacunas.add(getPanelvacunasCAlendario(), BorderLayout.WEST);
			panelVacunas.add(getScrollPaneVacunas(), BorderLayout.CENTER);
			// panelVacunas.add(getListVacunas(), BorderLayout.SOUTH);
		}
		return panelVacunas;
	}

	private JPanel getPanelvacunasCAlendario() {
		if (panelvacunasCAlendario == null) {
			panelvacunasCAlendario = new JPanel();
			panelvacunasCAlendario.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panelvacunasCAlendario.setBackground(Color.WHITE);
			panelvacunasCAlendario.setLayout(new BorderLayout(0, 0));
			panelvacunasCAlendario.add(getPanelSeleccionDias_1(), BorderLayout.CENTER);
			panelvacunasCAlendario.add(getLblNewLabel_4(), BorderLayout.NORTH);
		}
		return panelvacunasCAlendario;
	}

	private JPanel getPanelSeleccionDias_1() {
		if (panelSeleccionDias_1 == null) {
			panelSeleccionDias_1 = new JPanel();
			panelSeleccionDias_1.setBorder(new LineBorder(new Color(0, 0, 0)));
			panelSeleccionDias_1.setBackground(Color.WHITE);
			panelSeleccionDias_1.setLayout(new GridLayout(7, 1, 0, 0));
			panelSeleccionDias_1.add(getLblNewLabel_1_1());
			panelSeleccionDias_1.add(getComboBoxDias_1());
			panelSeleccionDias_1.add(getLblNewLabel_2_1());
			panelSeleccionDias_1.add(getComboBoxMes_1());
			panelSeleccionDias_1.add(getLblNewLabel_3_1());
			panelSeleccionDias_1.add(getComboBoxAño_1());
			panelSeleccionDias_1.add(getBtnAceptarCalendario_1());
		}
		return panelSeleccionDias_1;
	}

	private JLabel getLblNewLabel_1_1() {
		if (lblNewLabel_1_1 == null) {
			lblNewLabel_1_1 = new JLabel("D\u00EDa");
			lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		}
		return lblNewLabel_1_1;
	}

	private JComboBox getComboBoxDias_1() {
		if (comboBoxDias_1 == null) {
			comboBoxDias_1 = new JComboBox();
			comboBoxDias_1.setFont(new Font("Tahoma", Font.PLAIN, 21));
			comboBoxDias_1.setBorder(new MatteBorder(1, 0, 1, 0, Color.DARK_GRAY));
			comboBoxDias_1.setBackground(SystemColor.controlHighlight);
		}
		return comboBoxDias_1;
	}

	private JLabel getLblNewLabel_2_1() {
		if (lblNewLabel_2_1 == null) {
			lblNewLabel_2_1 = new JLabel("Mes");
			lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		}
		return lblNewLabel_2_1;
	}

	private JComboBox getComboBoxMes_1() {
		if (comboBoxMes_1 == null) {
			comboBoxMes_1 = new JComboBox();
			//comboBoxMes_1.setSelectedIndex(LocalDate.now().getMonthValue());
			comboBoxMes_1.setFont(new Font("Tahoma", Font.PLAIN, 21));
			comboBoxMes_1.setBorder(new MatteBorder(1, 0, 1, 0, Color.DARK_GRAY));
			comboBoxMes_1.setBackground(SystemColor.controlHighlight);
		}
		return comboBoxMes_1;
	}

	private JLabel getLblNewLabel_3_1() {
		if (lblNewLabel_3_1 == null) {
			lblNewLabel_3_1 = new JLabel("A\u00F1o");
			lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		}
		return lblNewLabel_3_1;
	}

	private JComboBox getComboBoxAño_1() {
		if (comboBoxAño_1 == null) {
			comboBoxAño_1 = new JComboBox();
			comboBoxAño_1.setFont(new Font("Tahoma", Font.PLAIN, 21));
			comboBoxAño_1.setBorder(new MatteBorder(1, 0, 1, 0, Color.DARK_GRAY));
			comboBoxAño_1.setBackground(SystemColor.controlHighlight);
		}
		return comboBoxAño_1;
	}

	private JButton getBtnAceptarCalendario_1() {
		if (btnAceptarCalendario_1 == null) {
			btnAceptarCalendario_1 = new JButton("Aceptar");
			btnAceptarCalendario_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					LocalDate date = getLocalDateFromCombo_1();
					if (date != null)
						getVacunasByDate(date);
				}
			});
			btnAceptarCalendario_1.setBackground(Color.GREEN);
		}
		return btnAceptarCalendario_1;
	}

	protected LocalDate getLocalDateFromCombo_1() {
		if (checkComboDaysAreReady()) {
			return LocalDate.of(((int) comboBoxAño_1.getSelectedItem()),
					Month.of(getMesNumero((String) comboBoxMes_1.getSelectedItem())),
					(int) comboBoxDias_1.getSelectedItem());
		}
		return null;
	}

	protected void getVacunasByDate(LocalDate date) {

		DataBaseConsultor dbc = new DatabaseConsultorImpl();

		List<Vacuna> vacunas = dbc.findAllVacunasByDAte(date, medico);
		if(vacunas.isEmpty())
			JOptionPane.showMessageDialog(this,"No existen vacunas para ese día");
		for(Vacuna vacuna: vacunas) {
			this.modeloVacunas.addElement(vacuna);
		}

	}

	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("Introducir el d\u00EDa para lisat vacunas");
			lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 17));
		}
		return lblNewLabel_4;
	}

	private JScrollPane getScrollPaneVacunas() {
		if (scrollPaneVacunas == null) {
			scrollPaneVacunas = new JScrollPane();
			scrollPaneVacunas.setViewportView(getListVacunas());
		}
		return scrollPaneVacunas;
	}

	private JList getListVacunas() {
		if (listVacunas == null) {
			listVacunas = new JList();
			modeloVacunas = new DefaultListModel<>();
			listVacunas.setModel(modeloVacunas);
			listVacunas.setCellRenderer(new VacunaCellRender());
		}
		return listVacunas;
	}
}
