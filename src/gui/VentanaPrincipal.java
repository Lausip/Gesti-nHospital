package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import negocio.sanitario.Medico;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel baseContentPane;
	private JButton botonVentanaMedico;
	private JButton btAsignarVacaciones;
	private JButton btnJornadas;
	private JButton btnCitas;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		baseContentPane = new JPanel();
		baseContentPane.setBackground(Color.WHITE);
		baseContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(baseContentPane);
		baseContentPane.setLayout(null);
		baseContentPane.add(getBotonVentanaMedico());
		baseContentPane.add(getBtAsignarVacaciones());
		baseContentPane.add(getBtnCitas());
		baseContentPane.add(getBtnJornadas());
		baseContentPane.add(getBtnNewButton());
	}

	private JButton getBotonVentanaMedico() {
		if (botonVentanaMedico == null) {
			botonVentanaMedico = new JButton("Médico");
			botonVentanaMedico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// Linea escrita a mano para probar funcionamientos
					Medico med = new Medico("medico_1", "Guillermo", "Astorga Manzanal",
							"guillermoastorgamanzanal@gmail.com");
					VentanaMedico ventanaMedico = new VentanaMedico(med);
					ventanaMedico.setVisible(true);
				}
			});
			botonVentanaMedico.setBounds(53, 35, 94, 23);

		}
		return botonVentanaMedico;
	}

	
	private JButton getBtAsignarVacaciones() {
		if (btAsignarVacaciones == null) {
			btAsignarVacaciones = new JButton("Asignar vacaciones");
			btAsignarVacaciones.setBounds(191, 35, 181, 23);
			btAsignarVacaciones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaAsignarVacaciones ventanaMedico = new VentanaAsignarVacaciones();
					ventanaMedico.setVisible(true);
				}
			});
		}
		return btAsignarVacaciones;
	}

	private JButton getBtnJornadas() {
		if (btnJornadas == null) {
			btnJornadas = new JButton("Asignar jornadas laborales");
			btnJornadas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					VentanaAsignarJornadaLaboral ventanaJornadas = new VentanaAsignarJornadaLaboral();
					ventanaJornadas.setVisible(true);
				}
			});

			btnJornadas.setBounds(191, 179, 181, 23);

		}
		return btnJornadas;
	}
	private JButton getBtnCitas() {
		if (btnCitas == null) {
			btnCitas = new JButton("Crear Cita");
			btnCitas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					VentanaCitas vc = new VentanaCitas();
					vc.setVisible(true);
				}
			});

			btnCitas.setBounds(53, 179, 115, 23);

		}
		return btnCitas;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Aceptar citas");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					VentanaAceptarCitas va= new VentanaAceptarCitas();
					va.setVisible(true);
					
				}
			});
			btnNewButton.setBounds(53, 196, 143, 21);
		}
		return btnNewButton;
	}
}
