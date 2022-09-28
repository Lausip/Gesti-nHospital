package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;

import negocio.consulta.impl.DatabaseConsultorImpl;
import negocio.sanitario.Medico;
import negocio.sanitario.Sanitario;

public class VentanaAsignarJornadaLaboralPpal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Sanitario sanitario;
	private List<String> jornadasYaAsignadas = new ArrayList<String>();
	private List<String[]> nuevasJornadas = new ArrayList<>();
	private List<String[]> paraBorrar = new ArrayList<>();

	private JPanel contentPane;
	private JPanel bnAceptarCancelar;
	private JButton btAceptar;
	private JButton btCancelar;
	private JPanel pnCalendario;
	private JPanel pnIndicaciones;
	private JLabel lbIndicaciones;
	private JPanel pnBotones;
	private JPanel pnAddDelete;
	private JButton btAñadirDia;
	private JButton btEliminarDia;
	private JPanel pnResumen;
	private JTextPane textPane;
	private JLabel lblNewLabel;
	private JPanel pnHoras;
	private JPanel pnHora;
	private JLabel lbHoraInicio;
	private JSpinner spHora;
	private JPanel pnHoraFin;
	private JLabel lbHoraFin;
	private JSpinner spHora_1;

	/**
	 * Create the frame.
	 */
	public VentanaAsignarJornadaLaboralPpal(Sanitario sanitario) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.sanitario = sanitario;
		setBounds(100, 100, 497, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getBnAceptarCancelar(), BorderLayout.SOUTH);
		contentPane.add(getPnCalendario(), BorderLayout.CENTER);
		contentPane.add(getPnIndicaciones(), BorderLayout.NORTH);
		contentPane.add(getPnBotones(), BorderLayout.EAST);

		setTitle("Asignar jornadas laborales para :" + sanitario.getNombre() + " " + sanitario.getApellidos() + "; "
				+ sanitario.getEmail());

	}

	private JPanel getBnAceptarCancelar() {
		if (bnAceptarCancelar == null) {
			bnAceptarCancelar = new JPanel();
			bnAceptarCancelar.add(getBtAceptar());
			bnAceptarCancelar.add(getBtCancelar());
		}
		return bnAceptarCancelar;
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
			btAceptar.setForeground(Color.WHITE);
			btAceptar.setBackground(Color.GREEN);
		}
		return btAceptar;
	}

	private void guardarBaseDeDatos() {
		if (sanitario.getClass().equals(Medico.class)) {
			DatabaseConsultorImpl.updateJornadaLabMedico(nuevasJornadas, sanitario.getId(), paraBorrar);
		} else {
			DatabaseConsultorImpl.updateJornadaLabEnfermero(nuevasJornadas, sanitario.getId(), paraBorrar);
		}

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

	private JPanel getPnCalendario() {
		if (pnCalendario == null) {
			pnCalendario = new JPanel();
			pnCalendario.setLayout(new BorderLayout(0, 0));
			pnCalendario.add(getPnHoras());
		}
		return pnCalendario;
	}

	private JPanel getPnIndicaciones() {
		if (pnIndicaciones == null) {
			pnIndicaciones = new JPanel();
			pnIndicaciones.add(getLbIndicaciones());
		}
		return pnIndicaciones;
	}

	private JLabel getLbIndicaciones() {
		if (lbIndicaciones == null) {
			lbIndicaciones = new JLabel(
					"Seleccione el d\u00EDa que desee y a\u00F1\u00E1dalo. Para confirmar haga click en aceptar.");
		}
		return lbIndicaciones;
	}

	private JPanel getPnBotones() {
		if (pnBotones == null) {
			pnBotones = new JPanel();
			pnBotones.setLayout(new BorderLayout(0, 0));
			pnBotones.add(getPnAddDelete(), BorderLayout.NORTH);
			pnBotones.add(getPnResumen(), BorderLayout.CENTER);
		}
		return pnBotones;
	}

	private JPanel getPnAddDelete() {
		if (pnAddDelete == null) {
			pnAddDelete = new JPanel();
			pnAddDelete.add(getBtAñadirDia());
			pnAddDelete.add(getBtEliminarDia());
		}
		return pnAddDelete;
	}

	private JButton getBtAñadirDia() {
		if (btAñadirDia == null) {
			btAñadirDia = new JButton("A\u00F1adir d\u00EDa");
			btAñadirDia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// obtenemos la fecha de inicio seleccionada y la fecha de fin
					Date fechainicio = (Date) spHora.getValue();
					Date fechafin = (Date) spHora_1.getValue();
					LocalDate localDate = LocalDate.now();
					if (fechainicio.compareTo(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()))<0) {
						advertenciaFechaAnterior();
					} else {
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy-HH:mm");
						String fechainiciocadena = sdf.format(fechainicio);
						String fechafincadena = sdf.format(fechafin);

						if (fechainicio.compareTo(fechafin) >= 0) {
							advertenciaInicioMayorFin();
						} else {
							// Ahora comprobamos que la fecha no esté repetida (mismo día)
							if (jornadasYaAsignadas.contains(fechainiciocadena.split("-")[0])
									|| jornadasYaAsignadas.contains(fechafincadena.split("-")[0])) {
								advertenciaYaAsignado();
							} else {
								// no se podria añadir una jornada laboral cuando haya vacaciones
								if (!sanitarioTieneVacaciones(fechainiciocadena, fechafincadena)) {
									textPane.setText(textPane.getText() + fechainiciocadena + " " + fechafincadena + "\n");
									jornadasYaAsignadas.add(fechainiciocadena.split("-")[0]);
									if (!jornadasYaAsignadas.contains(fechafincadena.split("-")[0])) {
										jornadasYaAsignadas.add(fechafincadena.split("-")[0]);
									}
									String[] nuevo = new String[2];
									nuevo[0] = fechainiciocadena;
									nuevo[1] = fechafincadena;
									nuevasJornadas.add(nuevo);
								} else {
									advertenciaVacaciones();
								}

							}

						}
					}
					
				}
			});
		}
		return btAñadirDia;
	}
	
	private void advertenciaFechaAnterior() {
		JOptionPane.showMessageDialog(this, "La fecha de inicio tiene que ser a partir del momento actual");
	}

	private boolean sanitarioTieneVacaciones(String fechainicio, String fechafin) {
		List<String> dias = new ArrayList<>();
		if (sanitario.getClass().equals(Medico.class)) {
			dias = DatabaseConsultorImpl.diasVacacionesMedico(sanitario);
		} else {
			dias = DatabaseConsultorImpl.diasVacacionesEnfermero(sanitario);
		}
		for (String fecha : dias) {
			if (fecha.equals(fechainicio.split("-")[0]) || fecha.equals(fechafin.split("-")[0])) {
				return true;
			} else if (Integer.parseInt(fecha.split("/")[0])>Integer.parseInt(fechainicio.split("-")[0].split("/")[0])&&Integer.parseInt(fecha.split("/")[0])<Integer.parseInt(fechafin.split("-")[0].split("/")[0])) {
				return true;
			}
		}
		return false;
	}

	private void advertenciaVacaciones() {
		JOptionPane.showMessageDialog(this, "El sanitario seleccionado tiene vacaciones en la fecha seleccionada");
	}

	private void advertenciaYaAsignado() {
		JOptionPane.showMessageDialog(this,
				"Ya existe una jornada laboral para la fecha seleccionada. Por favor, compruebe la fecha de nuevo");
	}

	private void advertenciaInicioMayorFin() {
		JOptionPane.showMessageDialog(this, "La fecha de inicio tiene que ser antes que la fecha de fin");

	}

	// elimina el ultimo dia añadido
	private JButton getBtEliminarDia() {
		if (btEliminarDia == null) {
			btEliminarDia = new JButton("Eliminar d\u00EDa");
			btEliminarDia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String fechas = textPane.getText();
					String[] lineas = fechas.split("\n");
					String fecha1 = lineas[lineas.length - 1].split(" ")[0];
					String fecha2 = lineas[lineas.length - 1].split(" ")[1];

					String[] borrar = new String[2];
					borrar[0] = fecha1;
					borrar[1] = fecha2;
					paraBorrar.add(borrar);
					String nuevo = "";
					for (int i = 0; i < lineas.length - 1; i++) {
						nuevo += lineas[i] + "\n";
					}
					textPane.setText(nuevo);

				}
			});
		}
		return btEliminarDia;
	}

	private JPanel getPnResumen() {
		if (pnResumen == null) {
			pnResumen = new JPanel();
			pnResumen.setLayout(new BorderLayout(0, 0));
			pnResumen.add(getTextPane(), BorderLayout.CENTER);
			pnResumen.add(getLblNewLabel(), BorderLayout.NORTH);
		}
		return pnResumen;
	}

	private JTextPane getTextPane() {
		if (textPane == null) {
			textPane = new JTextPane();
			int i = 0;
			String texto = "";
			for (String[] s : getDiasTrabajo()) {
				jornadasYaAsignadas.add(s[0].split("-")[0]);
				String segunda = s[1].split("-")[0];
				if (!jornadasYaAsignadas.contains(segunda)) {
					jornadasYaAsignadas.add(segunda);
				}
				if (i == getDiasTrabajo().size()) {
					texto += s[0] + "  " + s[1];
				} else {
					texto += s[0] + "  " + s[1] + "\n";
				}

			}
			textPane.setEditable(false);
			textPane.setText(texto);
		}
		return textPane;
	}

	private List<String[]> getDiasTrabajo() {
		if (sanitario.getClass().equals(Medico.class)) {
			return DatabaseConsultorImpl.diasTrabajoMedico(sanitario);
		} else {
			return DatabaseConsultorImpl.diasTrabajoEnfermero(sanitario);
		}

	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Los d\u00EDas seleccionados aparecer\u00E1n aqu\u00ED");
		}
		return lblNewLabel;
	}

	private JPanel getPnHoras() {
		if (pnHoras == null) {
			pnHoras = new JPanel();
			pnHoras.setLayout(new BorderLayout(0, 0));
			pnHoras.add(getPnHora(), BorderLayout.NORTH);
			pnHoras.add(getPnHoraFin(), BorderLayout.SOUTH);
		}
		return pnHoras;
	}

	private JPanel getPnHora() {
		if (pnHora == null) {
			pnHora = new JPanel();
			pnHora.setLayout(new BorderLayout(0, 0));
			pnHora.add(getLbHoraInicio(), BorderLayout.NORTH);
			pnHora.add(getSpHora());
		}
		return pnHora;
	}

	private JLabel getLbHoraInicio() {
		if (lbHoraInicio == null) {
			lbHoraInicio = new JLabel("Fecha inicio");
		}
		return lbHoraInicio;
	}

	private JSpinner getSpHora() {
		if (spHora == null) {
			spHora = new JSpinner();
			spHora.setModel(
					new SpinnerDateModel(new Date(1606518000000L), new Date(1606518000000L), null, Calendar.HOUR));

		}
		return spHora;
	}

	private JPanel getPnHoraFin() {
		if (pnHoraFin == null) {
			pnHoraFin = new JPanel();
			pnHoraFin.setLayout(new BorderLayout(0, 0));
			pnHoraFin.add(getLbHoraFin(), BorderLayout.NORTH);
			pnHoraFin.add(getSpHora_1(), BorderLayout.CENTER);
		}
		return pnHoraFin;
	}

	private JLabel getLbHoraFin() {
		if (lbHoraFin == null) {
			lbHoraFin = new JLabel("Fecha fin");
		}
		return lbHoraFin;
	}

	private JSpinner getSpHora_1() {
		if (spHora_1 == null) {
			spHora_1 = new JSpinner();
			spHora_1.setModel(
					new SpinnerDateModel(new Date(1606518000000L), new Date(1606518000000L), null, Calendar.HOUR));
		}
		return spHora_1;
	}
}
