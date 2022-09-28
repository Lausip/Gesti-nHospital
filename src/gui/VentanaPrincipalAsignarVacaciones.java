package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import negocio.consulta.impl.DatabaseConsultorImpl;
import negocio.sanitario.Medico;
import negocio.sanitario.Sanitario;
import javax.swing.JLabel;

public class VentanaPrincipalAsignarVacaciones extends JFrame {

	private JPanel contentPane;

	private Sanitario sanitario;
	private JPanel pnCalendario;
	private JCalendar calendario;
	private JPanel pnBotones;
	private JPanel bnAceptarCancelar;
	private JButton btAceptar;
	private JButton btCancelar;
	private JPanel pnAddDelete;
	private JButton btAñadirDia;
	private JButton btEliminarDia;
	private JPanel pnResumen;
	private JTextPane textPane;
	
	private List<String> vacacionesYaAsignadas = new ArrayList<String>();
	private List<String> nuevasVacaciones = new ArrayList<String>();
	private List<String> paraBorrar = new ArrayList<String>();
	private JPanel pnIndicaciones;
	private JLabel lbIndicaciones;
	private JLabel lblNewLabel;

	/**
	 * Create the frame.
	 */
	public VentanaPrincipalAsignarVacaciones(Sanitario sanitario) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 537, 326);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.sanitario = sanitario;

		configurarCalendario();
		setTitle("Asignar vacaciones para :" + sanitario.getNombre() + " " + sanitario.getApellidos() + "; " + sanitario.getEmail());
	}

	private void configurarCalendario() {
		contentPane.add(getPnCalendario(), BorderLayout.CENTER);
		contentPane.add(getPnBotones(), BorderLayout.EAST);
		contentPane.add(getBnAceptarCancelar(), BorderLayout.SOUTH);
		contentPane.add(getPnIndicaciones(), BorderLayout.NORTH);

	}

	private JPanel getPnCalendario() {
		if (pnCalendario == null) {
			pnCalendario = new JPanel();
			pnCalendario.add(getCalendario());
		}
		return pnCalendario;
	}

	private JCalendar getCalendario() {
		if (calendario == null) {
			calendario = new JCalendar();
		}
		return calendario;
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
			btAceptar.setBackground(Color.GREEN);
			btAceptar.setForeground(Color.WHITE);
		}
		return btAceptar;
	}

	private void guardarBaseDeDatos() {
		if (sanitario.getClass().equals(Medico.class)) {
			DatabaseConsultorImpl.updateJornadaVacMedico(nuevasVacaciones, sanitario.getId(), paraBorrar);
		} else {
			DatabaseConsultorImpl.updateJornadaVacEnfermero(nuevasVacaciones,sanitario.getId(), paraBorrar);
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
			btCancelar.setBackground(Color.RED);
			btCancelar.setForeground(Color.WHITE);
		}
		return btCancelar;
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
					String[] datos = fechaSeleccionada().split("/");
					if (Integer.parseInt(datos[2]) < LocalDate.now().getYear()) {
						advertencia();
					} else if (Integer.parseInt(datos[1]) < LocalDate.now().getMonthValue()
							&& Integer.parseInt(datos[2]) < LocalDate.now().getYear()) {
						advertencia();
					} else if ((Integer.parseInt(datos[0]) < LocalDate.now().getDayOfMonth()
							&& Integer.parseInt(datos[1]) < LocalDate.now().getMonthValue()
							&& Integer.parseInt(datos[2]) < LocalDate.now().getYear())
							|| (Integer.parseInt(datos[0]) < LocalDate.now().getDayOfMonth()
									&& Integer.parseInt(datos[1]) < LocalDate.now().getMonthValue()
									&& Integer.parseInt(datos[2]) == LocalDate.now().getYear())
							|| (Integer.parseInt(datos[0]) < LocalDate.now().getDayOfMonth()
									&& Integer.parseInt(datos[1]) == LocalDate.now().getMonthValue()
									&& Integer.parseInt(datos[2]) == LocalDate.now().getYear())
							|| Integer.parseInt(datos[1]) < LocalDate.now().getMonthValue()
							&& Integer.parseInt(datos[2]) == LocalDate.now().getYear()) {
						advertencia();
					} else {
						if (!textPane.getText().contains(fechaSeleccionada())) {
							String[] elementos = textPane.getText().split("\n");
							String cadena = "";
							for (int i=0;i<elementos.length;i++) {
								cadena += elementos[i] + "\n";
								
							}
							nuevasVacaciones.add(fechaSeleccionada());
							textPane.setText(cadena + fechaSeleccionada());
							
						} else {
							advertenciaYaSeleccionada();
						}
					}

				}
			});
		}
		return btAñadirDia;
	}

	private void advertencia() {
		JOptionPane.showMessageDialog(this, "No puede elegir una fecha anterior");
	}

	private void advertenciaYaSeleccionada() {
		JOptionPane.showMessageDialog(this, "Esta fecha ya está seleccionada");
	}

	private String fechaSeleccionada() {
		String año = Integer.toString(calendario.getCalendar().get(java.util.Calendar.YEAR));
		String mes = Integer.toString(calendario.getCalendar().get(java.util.Calendar.MONTH) + 1);
		String dia = Integer.toString(calendario.getCalendar().get(java.util.Calendar.DATE));
		if (dia.length() == 1)
			dia = "0" + dia;
		return dia + "/" + mes + "/" + año;
	}

	private JButton getBtEliminarDia() {
		if (btEliminarDia == null) {
			btEliminarDia = new JButton("Eliminar d\u00EDa");
			btEliminarDia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!vacacionesYaAsignadas.contains(fechaSeleccionada())) {
						if (textPane.getText().contains(fechaSeleccionada())) {
							String fechasnuevas = "";
							String[] fechas = textPane.getText().split(fechaSeleccionada());
							for (int i=0;i<fechas.length;i++) {
								fechasnuevas += fechas[i].strip() +"\n";
								
							}
							textPane.setText(fechasnuevas);
							nuevasVacaciones.remove(fechaSeleccionada());
						} else {
							advertenciaSeleccionar();
						}
					} else {
						if (textPane.getText().contains(fechaSeleccionada())) {
							String fechasnuevas = "";
							String[] fechas = textPane.getText().split(fechaSeleccionada());
							for (int i=0;i<fechas.length;i++) {
								fechasnuevas += fechas[i].strip() +"\n";
								
							}
							textPane.setText(fechasnuevas);
							nuevasVacaciones.remove(fechaSeleccionada());
						} else {
							advertenciaSeleccionar();
						}
						paraBorrar.add(fechaSeleccionada());
					}
					
				}
			});
		}
		return btEliminarDia;
	}
	
	private void advertenciaSeleccionar() {
		JOptionPane.showMessageDialog(this, "El día seleccionado no se encuentra en la lista");
	}
	
	private void advertenciaYaHabiaSidoAsignada() {
		JOptionPane.showMessageDialog(this, "No puedes eliminar una fecha que ya ha sido asignada con anterioridad");
	}

	private JPanel getPnResumen() {
		if (pnResumen == null) {
			pnResumen = new JPanel();
			pnResumen.setLayout(new BorderLayout(0, 0));
			pnResumen.add(getTextPane());
			pnResumen.add(getLblNewLabel(), BorderLayout.NORTH);
		}
		return pnResumen;
	}

	private JTextPane getTextPane() {
		if (textPane == null) {
			textPane = new JTextPane();
			String texto = "";
			int i=0;
			for (String s : getDiasVacaciones()) {
				vacacionesYaAsignadas.add(s);
				if (i==getDiasVacaciones().size()) {
					texto += s ;
				} else {
					texto += s + "\n";
				}
				
			}
			textPane.setText(texto);
			textPane.setEditable(false);
		}
		return textPane;
	}

	private List<String> getDiasVacaciones() {
		if (sanitario.getClass().equals(Medico.class)) {
			return DatabaseConsultorImpl.diasVacacionesMedico(sanitario);
		} else {
			return DatabaseConsultorImpl.diasVacacionesEnfermero(sanitario);
		}

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
			lbIndicaciones = new JLabel("Seleccione el d\u00EDa que desee y a\u00F1\u00E1dalo. Para confirmar haga click en aceptar.");
		}
		return lbIndicaciones;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Los d\u00EDas seleccionados aparecer\u00E1n aqu\u00ED");
		}
		return lblNewLabel;
	}
}
