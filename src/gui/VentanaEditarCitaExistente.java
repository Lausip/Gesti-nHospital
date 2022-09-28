package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.time.LocalTime;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import gui.cellRenders.CitaCellRender;
import negocio.citas.Cita;
import negocio.consulta.DataBaseConsultor;
import negocio.consulta.impl.DatabaseConsultorImpl;
import negocio.paciente.Paciente;
import negocio.sanitario.Medico;

@SuppressWarnings("serial")
public class VentanaEditarCitaExistente extends JFrame {

	private JPanel contentPane;

	Medico med;
	Cita cit;
	private JPanel panelInfo;
	private JPanel panelTitulo;
	private JLabel lblDatosDeLacita;
	private JPanel panelDatosPaciente;
	private JLabel lblNombrePaciente;
	private JPanel panelEditarCampos;
	private JTextField textFieldNombreApellidosPaciente;
	private JLabel lblhoraentrada;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JPanel panelHoraEntrada;
	private JComboBox<Integer> comboBoxMinutosEntrada;
	private JComboBox<Integer> comboBoxHoraEntrada;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnDefaultHoraEntrada;
	private JPanel panelHoraSalida;
	private JComboBox<Integer> comboBoxHoraSalida;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_1_1;
	private JComboBox<Integer> comboBoxMinutosSalida;
	private JLabel lblhoraentrada_1;
	private JButton btnDefaultHoraSalida;

	private DefaultComboBoxModel<Integer> modeloHoraE = new DefaultComboBoxModel<Integer>();
	private DefaultComboBoxModel<Integer> modeloHoraS = new DefaultComboBoxModel<Integer>();
	private DefaultComboBoxModel<Integer> modeloMinutosE = new DefaultComboBoxModel<Integer>();
	private DefaultComboBoxModel<Integer> modeloMinutosS = new DefaultComboBoxModel<Integer>();
	private JRadioButton rdbtnHaAcudido;
	private JButton btnActivarAcudir;
	private JButton btnActivarHoraEntrada;
	private JButton btnActivarHorasalida;

	private JButton btHistoralPaciente;

	private JButton btPrescripcion;
	private JButton btAñadirCausas;


	/**
	 * Create the frame.
	 */
	public VentanaEditarCitaExistente(Medico med, Cita cit) {
		setTitle("Editar Cita");
		this.med = med;
		this.cit = cit;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 878, 610);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanelInfo(), BorderLayout.CENTER);
		contentPane.add(getPanelTitulo(), BorderLayout.NORTH);
		rellenarHoras();
	}

	private JPanel getPanelInfo() {
		if (panelInfo == null) {
			panelInfo = new JPanel();
			panelInfo.setLayout(new GridLayout(1, 2, 0, 0));
			panelInfo.add(getPanelDatosPaciente());
			panelInfo.add(getPanelEditarCampos());
		}
		return panelInfo;
	}

	private JPanel getPanelTitulo() {
		if (panelTitulo == null) {
			panelTitulo = new JPanel();
			panelTitulo.setBackground(Color.WHITE);
			panelTitulo.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panelTitulo.add(getLblDatosDeLacita());
		}
		return panelTitulo;
	}

	private JLabel getLblDatosDeLacita() {
		if (lblDatosDeLacita == null) {
			lblDatosDeLacita = new JLabel("Datos sobre la Cita");
			lblDatosDeLacita.setFont(new Font("Tahoma", Font.PLAIN, 26));
		}
		return lblDatosDeLacita;
	}

	private JPanel getPanelDatosPaciente() {
		if (panelDatosPaciente == null) {
			panelDatosPaciente = new JPanel();
			panelDatosPaciente.setBackground(Color.WHITE);
			panelDatosPaciente.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panelDatosPaciente.setLayout(null);
			panelDatosPaciente.add(getLblNombrePaciente());
			panelDatosPaciente.add(getTextFieldNombreApellidosPaciente());
			
			JPanel panelAuxAcudioCita = new JPanel();
			panelAuxAcudioCita.setBounds(20, 361, 330, 120);
			panelDatosPaciente.add(panelAuxAcudioCita);
			panelAuxAcudioCita.setLayout(null);
			panelAuxAcudioCita.add(getRdbtnHaAcudido());
			panelAuxAcudioCita.add(getBtnActivarAcudir());

			panelDatosPaciente.add(getBtHistoralPaciente());
			panelDatosPaciente.add(getBtPrescripcion());
			panelDatosPaciente.add(getBtAñadirCausas());

		}
		return panelDatosPaciente;
	}

	private JLabel getLblNombrePaciente() {
		if (lblNombrePaciente == null) {
			lblNombrePaciente = new JLabel("Nombre y Apellidos del Paciente:");
			lblNombrePaciente.setBounds(0, 0, 388, 101);
			lblNombrePaciente.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNombrePaciente.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNombrePaciente;
	}

	private JPanel getPanelEditarCampos() {
		if (panelEditarCampos == null) {
			panelEditarCampos = new JPanel();
			panelEditarCampos.setBackground(Color.WHITE);
			panelEditarCampos.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panelEditarCampos.setLayout(null);
			panelEditarCampos.add(getBtnNewButton());
			panelEditarCampos.add(getBtnNewButton_1());
			panelEditarCampos.add(getPanelHoraEntrada());
			panelEditarCampos.add(getPanelHoraSalida());
		}
		return panelEditarCampos;
	}

	private JTextField getTextFieldNombreApellidosPaciente() {
		if (textFieldNombreApellidosPaciente == null) {
			textFieldNombreApellidosPaciente = new JTextField();
			textFieldNombreApellidosPaciente.setFont(new Font("Tahoma", Font.PLAIN, 20));
			textFieldNombreApellidosPaciente.setBounds(10, 79, 355, 53);
			textFieldNombreApellidosPaciente.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldNombreApellidosPaciente.setColumns(10);
			DataBaseConsultor dbc = new DatabaseConsultorImpl();
			textFieldNombreApellidosPaciente.setText(dbc.getNombreYApellidosFromPaciente(cit.getId_paciente()));
			textFieldNombreApellidosPaciente.setEditable(false);
		}
		return textFieldNombreApellidosPaciente;
	}

	private JLabel getLblhoraentrada() {
		if (lblhoraentrada == null) {
			lblhoraentrada = new JLabel("Hora de entrada a la Cita");
			lblhoraentrada.setBounds(70, 10, 241, 25);
			lblhoraentrada.setHorizontalAlignment(SwingConstants.CENTER);
			lblhoraentrada.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return lblhoraentrada;
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Aceptar");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (checkCombobo()) {
						actualizarActivos();
						DataBaseConsultor dbc = new DatabaseConsultorImpl();
						dbc.actualizarHorasCita(cit);
						//dbc.actualizarCausasCita(cbCausas.getSelectedItem().toString(),cit.id_cita,cit.fecha,cit.horaSalidaPaciente, dbc.getIdHistorialPaciente(cit.getId_paciente()));
						cerrarVentana();
					} else {
						mostrarErrorCampos();

					}
				}
			});
			btnNewButton.setBounds(272, 455, 115, 40);
		}
		return btnNewButton;
	}

	
	public  String formatearHoras(int object) {
		String horaaux=""+object;
		if(horaaux.length()==1) {
			return "0"+horaaux;
		} return horaaux;
	}
	protected void mostrarErrorCampos() {
		JOptionPane.showMessageDialog(this, "Faltan campos por rellenar");

	}

	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("Cancerlar");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cerrarVentana();
				}
			});
			btnNewButton_1.setBounds(139, 455, 123, 40);
		}
		return btnNewButton_1;
	}

	protected void cerrarVentana() {
		this.dispose();
	}

	private JPanel getPanelHoraEntrada() {
		if (panelHoraEntrada == null) {
			panelHoraEntrada = new JPanel();
			panelHoraEntrada.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
			panelHoraEntrada.setBounds(22, 21, 367, 199);
			panelHoraEntrada.setLayout(null);
			panelHoraEntrada.add(getComboBox_3());
			panelHoraEntrada.add(getLblNewLabel_4());
			panelHoraEntrada.add(getLblNewLabel_1_2());
			panelHoraEntrada.add(getComboBox_1_2());
			panelHoraEntrada.add(getLblhoraentrada());
			panelHoraEntrada.add(getBtnNewButton_2_2());
			panelHoraEntrada.add(getBtnActivarHoraEntrada());
		}
		return panelHoraEntrada;
	}

	private JComboBox<Integer> getComboBox_1_2() {
		if (comboBoxMinutosEntrada == null) {
			comboBoxMinutosEntrada = new JComboBox<Integer>();
			comboBoxMinutosEntrada.setEnabled(false);
			comboBoxMinutosEntrada.setBounds(10, 148, 74, 21);
		}
		return comboBoxMinutosEntrada;
	}

	private JComboBox<Integer> getComboBox_3() {
		if (comboBoxHoraEntrada == null) {
			comboBoxHoraEntrada = new JComboBox<Integer>();
			comboBoxHoraEntrada.setEnabled(false);
			comboBoxHoraEntrada.setBounds(10, 116, 74, 21);
		}
		return comboBoxHoraEntrada;
	}

	private JLabel getLblNewLabel_4() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Hora");
			lblNewLabel.setBounds(94, 115, 32, 19);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return lblNewLabel;
	}

	private JLabel getLblNewLabel_1_2() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("Minutos");
			lblNewLabel_1.setBounds(100, 147, 52, 19);
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return lblNewLabel_1;
	}

	private JButton getBtnNewButton_2_2() {
		if (btnDefaultHoraEntrada == null) {
			btnDefaultHoraEntrada = new JButton("Usar hora acutal");
			btnDefaultHoraEntrada.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int hour = LocalTime.now().getHour();
					int minutos = LocalTime.now().getMinute();

					comboBoxHoraEntrada.setSelectedIndex(hour);
					comboBoxMinutosEntrada.setSelectedIndex(minutos);
				}
			});
			btnDefaultHoraEntrada.setBounds(211, 114, 135, 37);
		}
		return btnDefaultHoraEntrada;
	}

	private JPanel getPanelHoraSalida() {
		if (panelHoraSalida == null) {
			panelHoraSalida = new JPanel();
			panelHoraSalida.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
			panelHoraSalida.setLayout(null);
			panelHoraSalida.setBounds(22, 230, 367, 199);
			panelHoraSalida.add(getComboBox_2_1());
			panelHoraSalida.add(getLblNewLabel_2_1());
			panelHoraSalida.add(getLblNewLabel_1_1_1());
			panelHoraSalida.add(getComboBox_1_1_1());
			panelHoraSalida.add(getLblhoraentrada_1());
			panelHoraSalida.add(getBtnNewButton_2_1_1());
			panelHoraSalida.add(getBtnActivarHorasalida());
		}
		return panelHoraSalida;
	}

	private JComboBox<Integer> getComboBox_2_1() {
		if (comboBoxHoraSalida == null) {
			comboBoxHoraSalida = new JComboBox<Integer>();
			comboBoxHoraSalida.setEnabled(false);
			comboBoxHoraSalida.setBounds(10, 116, 74, 21);
		}
		return comboBoxHoraSalida;
	}

	private JLabel getLblNewLabel_2_1() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("Hora");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNewLabel_2.setBounds(94, 115, 32, 19);
		}
		return lblNewLabel_2;
	}

	private JLabel getLblNewLabel_1_1_1() {
		if (lblNewLabel_1_1 == null) {
			lblNewLabel_1_1 = new JLabel("Minutos");
			lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNewLabel_1_1.setBounds(100, 147, 52, 19);
		}
		return lblNewLabel_1_1;
	}

	private JComboBox<Integer> getComboBox_1_1_1() {
		if (comboBoxMinutosSalida == null) {
			comboBoxMinutosSalida = new JComboBox<Integer>();
			comboBoxMinutosSalida.setEnabled(false);
			comboBoxMinutosSalida.setBounds(10, 148, 74, 21);
		}
		return comboBoxMinutosSalida;
	}

	private JLabel getLblhoraentrada_1() {
		if (lblhoraentrada_1 == null) {
			lblhoraentrada_1 = new JLabel("Hora de salida a la Cita");
			lblhoraentrada_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblhoraentrada_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblhoraentrada_1.setBounds(70, 10, 241, 25);
		}
		return lblhoraentrada_1;
	}

	private JButton getBtnNewButton_2_1_1() {
		if (btnDefaultHoraSalida == null) {
			btnDefaultHoraSalida = new JButton("Usar hora acutal");
			btnDefaultHoraSalida.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					int hour = LocalTime.now().getHour();
					int minutos = LocalTime.now().getMinute();

					comboBoxHoraSalida.setSelectedIndex(hour);
					comboBoxMinutosSalida.setSelectedIndex(minutos);
				}
			});
			btnDefaultHoraSalida.setBounds(211, 114, 135, 37);
		}
		return btnDefaultHoraSalida;
	}

	private void rellenarHoras() {
		comboBoxHoraEntrada.setModel(modeloHoraE);
		comboBoxHoraSalida.setModel(modeloHoraS);
		comboBoxMinutosEntrada.setModel(modeloMinutosE);
		comboBoxMinutosSalida.setModel(modeloMinutosS);
		for (int i = 0; i < 24; i++) {
			modeloHoraE.addElement(i);
			modeloHoraS.addElement(i);
		}
		for (int i = 0; i < 60; i++) {
			modeloMinutosE.addElement(i);
			modeloMinutosS.addElement(i);
		}

	}

	private boolean checkCombobo() {
		return comboBoxHoraEntrada.getSelectedIndex() > -1 && comboBoxHoraSalida.getSelectedIndex() > -1
				&& comboBoxMinutosEntrada.getSelectedIndex() > -1 && comboBoxMinutosSalida.getSelectedIndex() > -1;

	}
	
	private String[] arrayCausas() {
		String[] s = {" ", "Dolor de cabeza" , "Dolor de estómago" , "Dolor de huesos", "Herida" ,"Gripe", "Coronavirus", "Otra causa..."};
		return s;
	}
	private JRadioButton getRdbtnHaAcudido() {
		if (rdbtnHaAcudido == null) {
			rdbtnHaAcudido = new JRadioButton("Ha acudido a la cita");
			rdbtnHaAcudido.setHorizontalAlignment(SwingConstants.CENTER);
			rdbtnHaAcudido.setFont(new Font("Tahoma", Font.PLAIN, 18));
			rdbtnHaAcudido.setEnabled(false);
			rdbtnHaAcudido.setBackground(Color.WHITE);
			rdbtnHaAcudido.setBounds(117, 39, 185, 31);
		}
		return rdbtnHaAcudido;
	}
	private JButton getBtnActivarAcudir() {
		if (btnActivarAcudir == null) {
			btnActivarAcudir = new JButton("Modificar");
			btnActivarAcudir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					rdbtnHaAcudido.setEnabled(true);
				}
			});
			btnActivarAcudir.setBounds(10, 44, 75, 21);
		}
		return btnActivarAcudir;
	}
	protected void actualizarActivos() {
		if(rdbtnHaAcudido.isEnabled()) {
			cit.haAcudido=rdbtnHaAcudido.isSelected();	
		}
		
		if(comboBoxHoraEntrada.isEnabled()) {
			cit.horaEntradaPaciente = formatearHoras((int) comboBoxHoraEntrada.getSelectedItem()) + ":"
					+ formatearHoras((int) comboBoxMinutosEntrada.getSelectedItem());
			
		}
		if(comboBoxHoraSalida.isEnabled()) {
		cit.horaSalidaPaciente = formatearHoras((int) comboBoxHoraSalida.getSelectedItem()) + ":"
				+ formatearHoras((int) comboBoxMinutosSalida.getSelectedItem());
		}
		DataBaseConsultor dbc = new DatabaseConsultorImpl();
		dbc.actualizarHorasCita(cit);
		dbc.actualizarAcudirCita(rdbtnHaAcudido.isSelected(), cit.getId_cita());
		this.dispose();
	}

	private JButton getBtnActivarHoraEntrada() {
		if (btnActivarHoraEntrada == null) {
			btnActivarHoraEntrada = new JButton("Modificar");
			btnActivarHoraEntrada.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					comboBoxHoraEntrada.setEnabled(true);
					comboBoxMinutosEntrada.setEnabled(true);
				}
			});
			btnActivarHoraEntrada.setBounds(10, 59, 85, 21);
		}
		return btnActivarHoraEntrada;
	}
	private JButton getBtnActivarHorasalida() {
		if (btnActivarHorasalida == null) {
			btnActivarHorasalida = new JButton("Modificar");
			btnActivarHorasalida.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					comboBoxHoraSalida.setEnabled(true);
					comboBoxMinutosSalida.setEnabled(true);
				}
			});
			btnActivarHorasalida.setBounds(10, 52, 85, 21);
		}
		return btnActivarHorasalida;
	}

	private JButton getBtHistoralPaciente() {
		if (btHistoralPaciente == null) {
			btHistoralPaciente = new JButton("Historial del paciente");
			btHistoralPaciente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Paciente d= DatabaseConsultorImpl.getPacienteById(cit.getId_paciente());
					VentanaHistorial vh= new VentanaHistorial(d);
					vh.setVisible(true);
				}
			});
			btHistoralPaciente.setBounds(20, 143, 330, 30);
		}
		return btHistoralPaciente;
	}
	
	private JButton getBtPrescripcion() {
		if (btPrescripcion == null) {
			btPrescripcion = new JButton("A\u00F1adir prescripci\u00F3n");
			btPrescripcion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaPrescripcion v = new VentanaPrescripcion(cit);
					v.setVisible(true);
				}
			});
			btPrescripcion.setBounds(20, 277, 330, 36);
		}
		return btPrescripcion;
	}
	private JButton getBtAñadirCausas() {
		if (btAñadirCausas == null) {
			btAñadirCausas = new JButton("A\u00F1adir causas");
			btAñadirCausas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaAñadirCausas v = new VentanaAñadirCausas(cit);
					v.setVisible(true);
				}
			});
			btAñadirCausas.setBounds(20, 206, 330, 30);
		}
		return btAñadirCausas;

	}
}
