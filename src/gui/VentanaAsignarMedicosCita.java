
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import negocio.consulta.impl.DatabaseConsultorImpl;
import negocio.equipo.Especialista;
import negocio.sanitario.Enfermero;
import negocio.sanitario.Medico;

public class VentanaAsignarMedicosCita extends JFrame {

	private static final long serialVersionUID = 1L;
	boolean borrado = false;
	private JPanel contentPane;
	private JPanel panelNombreCita;
	private JLabel lblStaff;
	private JPanel panelAsignarHMPU;
	private JPanel panelBotones;
	private JPanel panelBotones2;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JPanel panelAsignarHMP;
	private DefaultListModel<Medico> medico = new DefaultListModel<Medico>();
	private DefaultListModel<Medico> asignadoMedico = new DefaultListModel<Medico>();
	private DefaultListModel<Enfermero> defaultEnfermeroFiltrado = new DefaultListModel<Enfermero>();
	private DefaultListModel<Enfermero> defaultAsignarEnfermero = new DefaultListModel<Enfermero>();
	DefaultListModel<Medico>  copiadefaultMedicoFiltrado =new DefaultListModel<Medico>();
	DefaultListModel<Enfermero>  copiadefaultEnfermeroFiltrado =new DefaultListModel<Enfermero>();
	DefaultListModel<Especialista>  defaultEspecialistaFiltrado =new DefaultListModel<Especialista>();
	DefaultListModel<Especialista>  copiadefaultEspecialistaFiltrado =new DefaultListModel<Especialista>();
	DefaultListModel<Especialista>  defaultEspecialistaAsignado =new DefaultListModel<Especialista>();
	LocalDateTime d;
	String id_medico;
	private JPanel panelMedicos;
	private JPanel pnFiltradoMedicos;
	private JPanel pnFiltradoNombreMedico;
	private JPanel pnFiltradoApellidoMedico;
	private JPanel pnNombreMedicoTxt;
	private JTextField txtNombreMedico;
	private JPanel pnMedicoApellidoTxt;
	private JTextField txtApellidos;
	private JPanel pnMedicoAsignado;
	private JPanel pnMedicosDisponibles;
	private JPanel pnMedicosAsignarEliminar;
	private JPanel pnMedicosAsignar;
	private JPanel pnFiltradoBotonMedico;
	private JButton btFiltradoMedico;
	private JPanel pnAsignarMedico;
	private JPanel pnEliminarMedico;
	private JButton btEliminarMedico;
	private JButton btAsignarMedico;
	private JPanel pnNombreMedicoFlitradolbl;
	private JLabel lblMedicoNombre;
	private JPanel pnApellidosFiltradolbl;
	private JLabel lblMedicoApellidos;
	private JScrollPane scrollPaneMedicosAAsignar;
	private JList<Medico> listMedicoFiltro;
	private JScrollPane scrollPaneMedicosAsignados;
	private JList<Medico> listMedicoAsignado;
	private boolean filtradomedico = false;
	private LocalDate fechaLocalDate;
	private JPanel pnFiltradoEspecialidadMedico;
	private JPanel pnEspecialidadFIltradolbl;
	private JLabel lblEspecialidad;
	private JPanel pnEspecialidadCb;
	private JComboBox<String> cbEspecialidadMedico;
	private JPanel pnHoras;
	private JLabel lblDia;
	private JPanel pnlblHora;
	private JTextField txtDia;
	private JPanel pnlblMes;
	private JLabel lblHoraEntrada;
	private JTextField txtHoraEntrada;
	private JPanel pnlblAno;
	private JLabel llblHoraSalida;
	private JTextField txtHoraSalida;
	private boolean filtradoEnfermero;
	private String horaEntrada;
	private int minSalida;
	private String horaSalida;
	private String minEntrada;
	private  DefaultListModel<Medico> finalMedicos=new DefaultListModel<Medico>();
	private  DefaultListModel<Enfermero> finalEnfermeros=new DefaultListModel<Enfermero>();
	private DefaultListModel<Especialista> finalEspecialista=new DefaultListModel<Especialista>();;
	private JPanel pnEnfermero;
	private JPanel pnFiltradoEnfermero;
	private JPanel pnFiltradoNombreEnfermero;
	private JPanel pnNombreEnfermeroFlitradolbl;
	private JPanel pnNombreEnfermeroTxt;
	private JPanel pnFiltradoApellidoEnfermero;
	private JPanel pnApellidosFiltradolblEnfermero;
	private JPanel pnEnfermeroApellido;
	private JPanel pnFiltradoEspecialidadEnfermero;
	private JPanel pnEspecialidadFIltradolbl_1;
	private JPanel pnEspecialidadCb_1;
	private JPanel pnFiltradoBotonEnfermero;
	private JLabel lblEnfermeroNombre;
	private JTextField txtNombreEnfermero;
	private JLabel lblEnfermeroApellidos;
	private JTextField txtApellidosEnfermero;
	private JLabel lblEspecialidad_1;
	private JComboBox<String> cbEspecialidadEnfermero;
	private JButton btFiltradoEnfemero;
	private JPanel pnEnfermeroAsignado;
	private JPanel pnEnfermeroDisponibles;
	private JPanel pnEnfermeroAsignarEliminar;
	private JPanel pnAsignarEnfemero;
	private JPanel pnEliminarEnfemero;
	private JPanel pnEnfermeroAsignar;
	private JScrollPane scrollPaneEnfermeroAAsignar;
	private JList<Enfermero> listEnfermeroFiltro;
	private JButton btAsignarEnfemero;
	private JButton btEliminarEnfermero;
	private JScrollPane scrollPaneEnfermeroAsignados;
	private JList<Enfermero> listEnfermeroAsignado;
	private JPanel pnEspecialista;
	private JPanel pnFiltradoEspecialidatas;
	private JPanel pnFiltradoNombre;
	private JPanel pnNombreEspecialidadFiltradolbl;
	private JLabel lblEspecialidadNombre;
	private JPanel pnNombreEspecialistaTxt;
	private JTextField txtNombreEspecialista;
	private JPanel pnFiltradoBotoEspecilistas;
	private JButton btnBotonFiltradoEspecilistas;
	private JPanel pnEspecilistaAsignado;
	private JPanel pnEspcialistasDisponibles;
	private JScrollPane scrollPaneEspacilistasDisponibles;
	private JList<Especialista> listEspecilistasDisponibles;
	private JPanel pnEspecialistaAsignarEliminar;
	private JPanel pnAsignarEspecilista;
	private JPanel pnEspecilistaEliminar;
	private JButton btnEspecialistaAsignar;
	private JButton btnEspecialistaEliminar;
	private JPanel pnEspecialistasAsignar;
	private JScrollPane scrollPaneEspecialistaAsignado;
	private JList<Especialista> listEspecilistaAsignado;
	private boolean medicos=false;
	private boolean enfermeros=false;


//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaCitas frame = new VentanaCitas();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public VentanaAsignarMedicosCita() {


		d = LocalDateTime.now();
		setTitle("Hospital:Citas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1351, 643);
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
			panelNombreCita.add(getLblStaff());
			panelNombreCita.add(getPnHoras(), BorderLayout.EAST);
		}
		return panelNombreCita;
	}

	private JLabel getLblStaff() {
		if (lblStaff == null) {
			lblStaff = new JLabel("Asignar Staff");
			lblStaff.setFont(new Font("Tahoma", Font.PLAIN, 21));
		}
		return lblStaff;
	}

	private JPanel getPnAsignarHMPU() {
		if (panelAsignarHMPU == null) {
			panelAsignarHMPU = new JPanel();
			panelAsignarHMPU.setLayout(new GridLayout(0, 1, 0, 0));
			panelAsignarHMPU.add(getPanelAsignarHMP());
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
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {					
                medicosFinales();
                enfermerosFinales();
                especialistaFinales();
                dispose();
				}
			});
			btnAceptar.setMnemonic('A');
			btnAceptar.setForeground(Color.BLACK);
			btnAceptar.setBackground(new Color(51, 102, 51));
		}
		return btnAceptar;
	}

	protected void especialistaFinales() {
		for(int i =0;i<defaultEspecialistaAsignado.size();i++) {
			finalEspecialista.addElement(defaultEspecialistaAsignado.get(i));
		}
	VentanaCitas.listEspecialista.setModel(getFinalEspecialistas());
		
	}

	private DefaultListModel<Especialista> getFinalEspecialistas() {
		return finalEspecialista;
	}

	protected void enfermerosFinales() {
		for(int i =0;i<defaultAsignarEnfermero.size();i++) {
			finalEnfermeros.addElement(defaultAsignarEnfermero.get(i));
		}
	VentanaCitas.listEnfermeroAsignados.setModel(getFinalEnfemeros());
		
	}
	protected void medicosFinales() {
		for(int i =0;i<asignadoMedico.size();i++) {
			finalMedicos.addElement(asignadoMedico.get(i));
		}
		
	VentanaCitas.listMedicosAsignados.setModel(getFinalMedicos());	
	}
	public DefaultListModel<Enfermero> getFinalEnfemeros(){
		return finalEnfermeros;
	}
	public DefaultListModel<Medico> getFinalMedicos(){
		return finalMedicos;
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
			panelAsignarHMP.add(getPanelMedicos());
			panelAsignarHMP.add(getPnEnfermero());
			panelAsignarHMP.add(getPnEspecialista());
		}
		return panelAsignarHMP;
	}

	private JPanel getPanelMedicos() {
		if (panelMedicos == null) {
			panelMedicos = new JPanel();
			panelMedicos
					.setBorder(new TitledBorder(null, "Medico/s", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelMedicos.setLayout(new GridLayout(2, 1, 0, 0));
			panelMedicos.add(getPnFiltradoMedicos());
			panelMedicos.add(getPnMedicoAsignado());
		}
		return panelMedicos;
	}

	private JPanel getPnFiltradoMedicos() {
		if (pnFiltradoMedicos == null) {
			pnFiltradoMedicos = new JPanel();
			pnFiltradoMedicos
					.setBorder(new TitledBorder(null, "Filtrado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnFiltradoMedicos.setLayout(new GridLayout(0, 1, 0, 0));
			pnFiltradoMedicos.add(getPnFiltradoNombreMedico());
			pnFiltradoMedicos.add(getPnFiltradoApellidoMedico());
			pnFiltradoMedicos.add(getPnFiltradoEspecialidadMedico());
			pnFiltradoMedicos.add(getPnFiltradoBotonMedico());
		}
		return pnFiltradoMedicos;
	}

	private JPanel getPnFiltradoNombreMedico() {
		if (pnFiltradoNombreMedico == null) {
			pnFiltradoNombreMedico = new JPanel();
			pnFiltradoNombreMedico.setLayout(new GridLayout(0, 2, 0, 0));
			pnFiltradoNombreMedico.add(getPnNombreMedicoFlitradolbl());
			pnFiltradoNombreMedico.add(getPnNombreMedicoTxt());
		}
		return pnFiltradoNombreMedico;
	}

	private JPanel getPnFiltradoApellidoMedico() {
		if (pnFiltradoApellidoMedico == null) {
			pnFiltradoApellidoMedico = new JPanel();
			pnFiltradoApellidoMedico.setLayout(new GridLayout(0, 2, 0, 0));
			pnFiltradoApellidoMedico.add(getPnApellidosFiltradolbl());
			pnFiltradoApellidoMedico.add(getPnMedicoApellidoTxt());
		}
		return pnFiltradoApellidoMedico;
	}

	private JPanel getPnNombreMedicoTxt() {
		if (pnNombreMedicoTxt == null) {
			pnNombreMedicoTxt = new JPanel();
			pnNombreMedicoTxt.setLayout(new BorderLayout(0, 0));
			pnNombreMedicoTxt.add(getTxtNombreMedico(), BorderLayout.SOUTH);
		}
		return pnNombreMedicoTxt;
	}

	private JTextField getTxtNombreMedico() {
		if (txtNombreMedico == null) {
			txtNombreMedico = new JTextField();
		}
		return txtNombreMedico;
	}

	private JPanel getPnMedicoApellidoTxt() {
		if (pnMedicoApellidoTxt == null) {
			pnMedicoApellidoTxt = new JPanel();
			pnMedicoApellidoTxt.setLayout(new BorderLayout(0, 0));
			pnMedicoApellidoTxt.add(getTextField_1(), BorderLayout.SOUTH);
		}
		return pnMedicoApellidoTxt;
	}

	private JTextField getTextField_1() {
		if (txtApellidos == null) {
			txtApellidos = new JTextField();
			txtApellidos.setColumns(10);
		}
		return txtApellidos;
	}

	private JPanel getPnMedicoAsignado() {
		if (pnMedicoAsignado == null) {
			pnMedicoAsignado = new JPanel();
			pnMedicoAsignado.setLayout(new BoxLayout(pnMedicoAsignado, BoxLayout.X_AXIS));
			pnMedicoAsignado.add(getPnMedicosDisponibles());
			pnMedicoAsignado.add(getPnMedicosAsignarEliminar());
			pnMedicoAsignado.add(getPnMedicosAsignar());
		}
		return pnMedicoAsignado;
	}

	private JPanel getPnMedicosDisponibles() {
		if (pnMedicosDisponibles == null) {
			pnMedicosDisponibles = new JPanel();
			pnMedicosDisponibles.setBorder(new TitledBorder(null, "M\u00E9dicos Disponibles", TitledBorder.LEADING,
					TitledBorder.TOP, null, null));
			pnMedicosDisponibles.setLayout(new BorderLayout(0, 0));
			pnMedicosDisponibles.add(getScrollPane_1(), BorderLayout.CENTER);
		}
		return pnMedicosDisponibles;
	}

	private JPanel getPnMedicosAsignarEliminar() {
		if (pnMedicosAsignarEliminar == null) {
			pnMedicosAsignarEliminar = new JPanel();
			pnMedicosAsignarEliminar.setLayout(new GridLayout(2, 1, 10, 10));
			pnMedicosAsignarEliminar.add(getPnAsignarMedico());
			pnMedicosAsignarEliminar.add(getPnEliminarMedico());
		}
		return pnMedicosAsignarEliminar;
	}

	private JPanel getPnMedicosAsignar() {
		if (pnMedicosAsignar == null) {
			pnMedicosAsignar = new JPanel();
			pnMedicosAsignar.setBorder(new TitledBorder(null, "M\u00E9dico/s asginado/s", TitledBorder.LEADING,
					TitledBorder.TOP, null, null));
			pnMedicosAsignar.setLayout(new BorderLayout(0, 0));
			pnMedicosAsignar.add(getScrollPane_1_1(), BorderLayout.CENTER);
		}
		return pnMedicosAsignar;
	}

	private JPanel getPnFiltradoBotonMedico() {
		if (pnFiltradoBotonMedico == null) {
			pnFiltradoBotonMedico = new JPanel();
			pnFiltradoBotonMedico.setLayout(new BorderLayout(0, 0));
			pnFiltradoBotonMedico.add(getBtFiltradoMedico_1(), BorderLayout.SOUTH);
		}
		return pnFiltradoBotonMedico;
	}

	private JButton getBtFiltradoMedico_1() {
		if (btFiltradoMedico == null) {
			btFiltradoMedico = new JButton("Filtrar");
			btFiltradoMedico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clearAsignarMedico();
					filtrarMedicos();

						if (filtradomedico&&!VentanaCitas.desactivarHorario) {
							ponerMedicosDisponiblesFiltrado();
							ponerMedicosDisponiblesFiltradoVacaciones();
							ponerMedicosDisponiblesFiltradoLaboral();
						}

						else if(filtradomedico&&VentanaCitas.desactivarHorario) {
							ponerMedicosDisponiblesFiltradoVacaciones();
							ponerTodosMedicosDisponiblesLaboralDia();
						}
						else if(!filtradomedico) {
						listMedicoFiltro.setModel(copiadefaultMedicoFiltrado);
						}


				}
			});
			btFiltradoMedico.setMnemonic('f');
		}
		return btFiltradoMedico;
	}

	private JPanel getPnAsignarMedico() {
		if (pnAsignarMedico == null) {
			pnAsignarMedico = new JPanel();
			pnAsignarMedico.setLayout(new BorderLayout(0, 0));
			pnAsignarMedico.add(getBtAsignar_1_1(), BorderLayout.SOUTH);
		}
		return pnAsignarMedico;
	}

	private JPanel getPnEliminarMedico() {
		if (pnEliminarMedico == null) {
			pnEliminarMedico = new JPanel();
			pnEliminarMedico.setLayout(new BorderLayout(0, 0));
			pnEliminarMedico.add(getBtEliminarMedico(), BorderLayout.NORTH);
		}
		return pnEliminarMedico;
	}

	private JButton getBtEliminarMedico() {
		if (btEliminarMedico == null) {
			btEliminarMedico = new JButton("Eliminar");
			btEliminarMedico.setEnabled(false);
			btEliminarMedico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
		
					eliminarMedicoAsignado(listMedicoAsignado.getSelectedValue());

					eliminarMedicoAsignado(listMedicoFiltro.getSelectedValue());
			     
				
				}

			});
		}
		return btEliminarMedico;
	}

	private JButton getBtAsignar_1_1() {
		if (btAsignarMedico == null) {
			btAsignarMedico = new JButton("Asignar");
			btAsignarMedico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					asignarMedico();
				}

			});
		}
		return btAsignarMedico;
	}

	private JPanel getPnNombreMedicoFlitradolbl() {
		if (pnNombreMedicoFlitradolbl == null) {
			pnNombreMedicoFlitradolbl = new JPanel();
			pnNombreMedicoFlitradolbl.setLayout(new BorderLayout(0, 0));
			pnNombreMedicoFlitradolbl.add(getLblMedicoNombre_1(), BorderLayout.SOUTH);
		}
		return pnNombreMedicoFlitradolbl;
	}

	private JLabel getLblMedicoNombre_1() {
		if (lblMedicoNombre == null) {
			lblMedicoNombre = new JLabel("Nombre");
			lblMedicoNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblMedicoNombre.setDisplayedMnemonic('n');
		}
		return lblMedicoNombre;
	}

	private JPanel getPnApellidosFiltradolbl() {
		if (pnApellidosFiltradolbl == null) {
			pnApellidosFiltradolbl = new JPanel();
			pnApellidosFiltradolbl.setLayout(new BorderLayout(0, 0));
			pnApellidosFiltradolbl.add(getLblMedicoApellidos(), BorderLayout.SOUTH);
		}
		return pnApellidosFiltradolbl;
	}

	private JLabel getLblMedicoApellidos() {
		if (lblMedicoApellidos == null) {
			lblMedicoApellidos = new JLabel("Apellidos");
			lblMedicoApellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblMedicoApellidos.setDisplayedMnemonic('p');
		}
		return lblMedicoApellidos;
	}


	private JScrollPane getScrollPane_1() {
		if (scrollPaneMedicosAAsignar == null) {
			scrollPaneMedicosAAsignar = new JScrollPane();
			scrollPaneMedicosAAsignar.setViewportView(getListMedicoMirar_1());
		}
		return scrollPaneMedicosAAsignar;
	}

	private JList<Medico> getListMedicoMirar_1() {
		if (listMedicoFiltro == null) {
			listMedicoFiltro = new JList<Medico>();
			listMedicoFiltro.setBorder(new LineBorder(new Color(0, 0, 0)));
			listMedicoFiltro.setModel(principioMedicosDisponibles());
		}
		return listMedicoFiltro;
	}

	private JScrollPane getScrollPane_1_1() {
		if (scrollPaneMedicosAsignados == null) {
			scrollPaneMedicosAsignados = new JScrollPane();
			scrollPaneMedicosAsignados.setViewportView(getListMedicoAsignado_1());
		}
		return scrollPaneMedicosAsignados;
	}

	private JList<Medico> getListMedicoAsignado_1() {
		if (listMedicoAsignado == null) {
			listMedicoAsignado = new JList<Medico>();
			listMedicoAsignado.setBorder(new LineBorder(new Color(0, 0, 0)));
			if(VentanaCitas.listMedicosAsignados.getModel().getSize()>0) {
				for(int i=0;i<VentanaCitas.listMedicosAsignados.getModel().getSize();i++) {
				asignadoMedico.addElement(VentanaCitas.listMedicosAsignados.getModel().getElementAt(i));
				}
				listMedicoAsignado.setModel(asignadoMedico);
			   medicos=true;
				btEliminarMedico.setEnabled(true);
		
			}
		}
		return listMedicoAsignado;
	}

	private JPanel getPnFiltradoEspecialidadMedico() {
		if (pnFiltradoEspecialidadMedico == null) {
			pnFiltradoEspecialidadMedico = new JPanel();
			pnFiltradoEspecialidadMedico.setLayout(new GridLayout(0, 2, 0, 0));
			pnFiltradoEspecialidadMedico.add(getPnEspecialidadFIltradolbl());
			pnFiltradoEspecialidadMedico.add(getPnEspecialidadCb());
		}
		return pnFiltradoEspecialidadMedico;
	}

	private JPanel getPnEspecialidadFIltradolbl() {
		if (pnEspecialidadFIltradolbl == null) {
			pnEspecialidadFIltradolbl = new JPanel();
			pnEspecialidadFIltradolbl.setLayout(new BorderLayout(0, 0));
			pnEspecialidadFIltradolbl.add(getLblEspecialidad(), BorderLayout.SOUTH);
		}
		return pnEspecialidadFIltradolbl;
	}

	private JLabel getLblEspecialidad() {
		if (lblEspecialidad == null) {
			lblEspecialidad = new JLabel("Especialidad");
			lblEspecialidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblEspecialidad;
	}

	private JPanel getPnEspecialidadCb() {
		if (pnEspecialidadCb == null) {
			pnEspecialidadCb = new JPanel();
			pnEspecialidadCb.setLayout(new BorderLayout(0, 0));
			pnEspecialidadCb.add(getCbEspecialidadMedico(), BorderLayout.SOUTH);
		}
		return pnEspecialidadCb;
	}

	private JComboBox<String> getCbEspecialidadMedico() {
		if (cbEspecialidadMedico == null) {
			cbEspecialidadMedico = new JComboBox<String>();
			cbEspecialidadMedico.setModel(new DefaultComboBoxModel<String>(arrayEspecialidades()));
			cbEspecialidadMedico.setSelectedItem(0);

		}
		return cbEspecialidadMedico;
	}



	// AVISOS
	public void warningBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(this, infoMessage, "WarningBox: " + titleBar, JOptionPane.WARNING_MESSAGE);
	}

	

	private String[] arrayEspecialidades() {
		String res[] = { " ", "Traumatología", "Oftalmología", "Otorrinolaringología", "Dermatología" };
		return res;
	}
	// combox:ASERTOS



	// MEDICO/S:

	/**
	 * Filtra los medicos dependiendo de los datos puesto en los campos
	 */
	void filtrarMedicos() {
		if (!txtNombreMedico.getText().equals("") && !txtApellidos.getText().equals("")
				&& !cbEspecialidadMedico.getSelectedItem().equals(" "))
			añadirMedicosEspecialidadApellidoYNombre();
		else if (txtNombreMedico.getText().equals("") && txtApellidos.getText().equals("")
				&& cbEspecialidadMedico.getSelectedItem().equals(" ")) {
			listMedicoFiltro.setModel(copiadefaultMedicoFiltrado);
			filtradomedico=false;
		} else if (txtNombreMedico.getText().equals("") && txtApellidos.getText().equals("")) {
			añadirMedicosEspecialidad();
			filtradomedico = true;
		} else if (txtNombreMedico.getText().equals("") && cbEspecialidadMedico.getSelectedItem().equals(" ")) {
			añadirMedicosApellido();
			filtradomedico = true;
		} else if (cbEspecialidadMedico.getSelectedItem().equals(" ") && txtApellidos.getText().equals("")) {
			añadirMedicosNombre();
			filtradomedico = true;
		} else if (txtApellidos.getText().equals("")) {
			añadirNombreEspecialidad();
			filtradomedico = true;
		} else if (cbEspecialidadMedico.getSelectedItem().equals(" ")) {
			añadirMedicosApellidoYNombre();
			filtradomedico = true;
		} else if (txtNombreMedico.getText().equals("")) {
			añadirMedicosEspecialidadApellido();
			filtradomedico = true;
		}

		listMedicoFiltro.setModel(medico);
	}
	

	private void añadirMedicosEspecialidad() {
		List<Medico> lsitasMedico = DatabaseConsultorImpl
				.buscarEspecialidadMedico(cbEspecialidadMedico.getSelectedItem().toString());
		medico.removeAllElements();
		for (Medico medico : lsitasMedico) {
			this.medico.addElement(medico);
		}

	}

	// EMPIEZA
	private void añadirMedicosEspecialidadApellidoYNombre() {
		List<Medico> lsitasMedico = DatabaseConsultorImpl.buscarNombreEspecialidadApellidosMedico(
				txtNombreMedico.getText(), cbEspecialidadMedico.getSelectedItem().toString(), txtApellidos.getText());
		medico.removeAllElements();
		for (Medico medico : lsitasMedico) {
			this.medico.addElement(medico);
		}

	}

	private void añadirMedicosEspecialidadApellido() {
		List<Medico> lsitasMedico = DatabaseConsultorImpl.buscarEspecialidadApellidosMedico(
				cbEspecialidadMedico.getSelectedItem().toString(), txtApellidos.getText());
		medico.removeAllElements();
		for (Medico medico : lsitasMedico) {
			this.medico.addElement(medico);
		}

	}

	private void añadirNombreEspecialidad() {
		List<Medico> lsitasMedico = DatabaseConsultorImpl.buscarNombreEspecialidadMedico(txtNombreMedico.getText(),
				txtApellidos.getText());
		
		medico.removeAllElements();
		for (Medico medico : lsitasMedico) {
			this.medico.addElement(medico);
		}

	}

//HASTA AQUI
	private void añadirMedicosApellidoYNombre() {
		List<Medico> lsitasMedico = DatabaseConsultorImpl.buscarNombreApellidosMedico(txtNombreMedico.getText(),
				txtApellidos.getText());
		medico.removeAllElements();
		for (Medico medico : lsitasMedico) {
			this.medico.addElement(medico);
		}

	}

	private void añadirMedicosApellido() {
		List<Medico> lsitasMedico = DatabaseConsultorImpl.buscarApellidosMedico(txtApellidos.getText());
		medico.removeAllElements();
		for (Medico medico : lsitasMedico) {
			this.medico.addElement(medico);
		}

	}

	private void añadirMedicosNombre() {
		List<Medico> lsitasMedico = DatabaseConsultorImpl.buscarNombreMedico(txtNombreMedico.getText());
		medico.removeAllElements();
		for (Medico medico : lsitasMedico) {
			this.medico.addElement(medico);
		}

	}

	/**
	 * Clear de la Jlist de la lista del filtrado de medicos
	 */
	private void clearAsignarMedico() {
		if (medico != null) {
			// medico.clear();
			listMedicoFiltro.setModel(medico);
			filtradomedico = false;
		}

	}


	/**
	 * Poner medicos en la lista para asignar
	 */
	private void asignarMedico() {
		if (!mirarSiYaEstaElMedico(listMedicoFiltro.getSelectedValue())) {
			asignadoMedico.addElement(listMedicoFiltro.getSelectedValue());
			btEliminarMedico.setEnabled(true);
			listMedicoAsignado.setModel(asignadoMedico);
           panelEspecialistas(false);
		}
	}




	private boolean mirarSiYaEstaElMedico(Medico medico) {
		return asignadoMedico.contains(medico);

	}

	/**
	 * Elimina el medico asignado
	 */
	private void eliminarMedicoAsignado(Medico medico) {
		if(asignadoMedico.getSize()>0) {
		asignadoMedico.removeElement(medico);
		listMedicoAsignado.setModel(asignadoMedico);
		mirarSiHayceroMedicosAsignados();
		}
	}

	/**
	 * Mirar si la lista de medicos asignados para desabilitar el boton eliminar
	 */
	private void mirarSiHayceroMedicosAsignados() {
		if (asignadoMedico.size() == 0) {
			btEliminarMedico.setEnabled(false);

			  panelEspecialistas(true);}
		

	}





	/**
	 * Metodo que pone los medicos nada mas iniciar la ventana
	 * 
	 * @return
	 */
	private DefaultListModel<Medico> principioMedicosDisponibles() {
		if(VentanaCitas.desactivarHorario) {
			ponerTodosTodosMedicos();
			ponerTodosMedicosDisponiblesVacaiones();
			ponerTodosMedicosDisponiblesLaboralDia();

		}
		else {
		ponerTodosMedicosDisponibles();
		ponerTodosMedicosDisponiblesVacaiones();
		ponerTodosMedicosDisponiblesLaboral();
		}
		copiarMedicosDisponibles();
		return medico;
		
	}


	private void ponerTodosMedicosDisponiblesLaboralDia() {
		for (int i = 0; i < medico.getSize(); i++) {
			borrado=false;
			Medico medic = medico.getElementAt(i);
			camprobarMedicoJLaboralDia(medic, false);
			if(borrado) {
				i--;}
		}
       
	
	}

	private void camprobarMedicoJLaboralDia(Medico medic, boolean mensaje) {
			List<String> dias= DatabaseConsultorImpl.validarMedicoLaboralDia(medic.getId_Medico());
			boolean entro=false;
			for(String d:dias) {
				String[] r=d.split(",");
				String finicio=r[0];
				String ffin=r[1];
				 LocalDate fechainicio = LocalDate.parse(finicio, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				 LocalDate fechafin = LocalDate.parse(ffin, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				 if(fechainicio.equals(getDay())||fechafin.equals(getDay())) {
					  entro=true;
			
			}
					if(!entro) {
						 eliminarMedicoDisponibles(medic);
						 borrado=true;}
					 }
		
		
	}


	private void ponerTodosTodosMedicos() {
		List<Medico> m=DatabaseConsultorImpl.todoslosMedicos();
		for(int i=0;i<m.size();i++) 
			medico.addElement(m.get(i));
 	}
	

	private void copiarMedicosDisponibles() {
		for(int i=0;i<medico.getSize();i++)
		copiadefaultMedicoFiltrado.addElement(medico.get(i));
		
	}


	/**
	 * Metodo que al pulsar la hora de la cita mira si esos medicos no tengan
	 * ninguan cita
	 * 
	 * @return
	 */
	protected boolean validarMedicoCita() {
		boolean cita = false;
		for (int i = 0; i < asignadoMedico.getSize(); i++) {
			Medico medic = asignadoMedico.getElementAt(i);
			List<String> list = DatabaseConsultorImpl.validarMedicoNoCita(medic.getId_Medico(), fechaLocalDate);
			for (String aux : list) {
				String[] aux2 = aux.split(",");
				String entrada = horaEntrada + ":" + minEntrada;
				String salida = horaSalida + ":" + minSalida;
				if (mirarSitieneUnaCitaAEsaHora(LocalTime.parse(aux2[0]), LocalTime.parse(aux2[1]),
						LocalTime.parse(entrada), LocalTime.parse(salida))) {
					warningBox("El médico " + medic + " ya tiene una cita en ese dia y esas horas",
							"Cita ya existente");
					eliminarMedicoAsignado(medic);
					cita = true;
					i--;
				}
			}
		}
		ponerMedicoDisponiblesHorarioCita();
		activarDescativarBotonAsignar();
		return cita;
	}

	private void ponerMedicoDisponiblesHorarioCita() {
		if (filtradomedico) {
			ponerMedicosDisponiblesFiltrado();

		} else {
			ponerTodosMedicosDisponibles();
		}
		activarDescativarBotonAsignar();

	}

	protected void validarLaboralMedico() {
		for (int i = 0; i < asignadoMedico.getSize(); i++) {
			borrado=false;
			Medico medic = asignadoMedico.getElementAt(i);
			camprobarMedicoJLaboral(medic, true);
			if(borrado) {
				i--;
			mostrarMensajeLaboral(medic);}
		}
		ponerMedicoDisponiblesHorarioLaboral();
	
	}

		private void camprobarMedicoJLaboral(Medico medic, boolean mensaje) {
			boolean entra = false;
			LocalTime entrada = getHoraEntrada();
			LocalTime salida = getHoraSalida();
			boolean mantener = false;
			if(!salida.isBefore(entrada)) {
			String horaInicioFin = DatabaseConsultorImpl.validarMedicoLaboralInicioFinIgual(medic.getId_Medico(),
					getDay());
			if (horaInicioFin != null) {
				String[] separarFechas = horaInicioFin.split(",");
				String[] fhinicio = separarFechas[0].split("-");
				String[] fhfin = separarFechas[1].split("-");
				String horaInicio = fhinicio[1];
				String horafin = fhfin[1];
				if (mirarSiJornadaLaboralAEsaHora(LocalTime.parse(horaInicio), LocalTime.parse(horafin),
						entrada, salida)) {
					entra = true;
					if (mensaje) {
						eliminarMedicoAsignado(medic);
						mostrarMensajeLaboral(medic);
					} else {
						borrado = true;
						eliminarMedicoDisponibles(medic);
					}

				} else {
					entra = true;
					mantener = true;
				}

			}
			if (!entra) {
				horaInicioFin = DatabaseConsultorImpl.validarMedicoLaboralInicioIgual(medic.getId_Medico(), getDay());
				if (horaInicioFin != null) {
					String[] separarFechas = horaInicioFin.split("-");
					String horaInicio = separarFechas[1];
					if (!mirarSiEsDespues(LocalTime.parse(horaInicio), entrada)) {
						if (mensaje) {
							entra = true;
							eliminarMedicoAsignado(medic);
							mostrarMensajeLaboral(medic);
						} else {
							entra = true;
							borrado = true;
							eliminarMedicoDisponibles(medic);
						}

					} else {
						entra = true;
						mantener = true;
					}
				}
			}
			if (!entra) {
				horaInicioFin = DatabaseConsultorImpl.validarMedicoLaboralFinIgual(medic.getId_Medico(), getDay());
				if (horaInicioFin != null) {
					String[] separarFechas = horaInicioFin.split("-");
					String horaInicio = separarFechas[1];
					if (!mirarSiEsAntes(LocalTime.parse(horaInicio), salida)) {
						
						if (mensaje) {
							entra = true;
							eliminarMedicoAsignado(medic);
							mostrarMensajeLaboral(medic);
						} else {
							entra = true;
							borrado = true;
							eliminarMedicoDisponibles(medic);
						}

					} else {
						entra = true;
						mantener = true;
					}
				}
			}
			if (!mantener) {
				borrado = true;
				eliminarMedicoDisponibles(medic);
				eliminarMedicoAsignado(medic);

			}
			}
			else {
				String horasInico = DatabaseConsultorImpl.validarMedicoLaboralInicioIgual(medic.getId_Medico(), getDay());
				if (horasInico != null) {
					String horasFin = DatabaseConsultorImpl.validarMedicoLaboralFinIgual(medic.getId_Medico(), getDay().plusDays(1));
					if(horasFin!=null) {
					String[] separarFechas = horasInico.split("-");
					String horaInico = separarFechas[1];
					String[] separaFechasF = horasFin.split("-");
					String horaFin = separaFechasF[1];
					if (entrada.isBefore(LocalTime.parse(horaInico)) ||salida.isAfter(LocalTime.parse(horaFin))){
						if (mensaje) {
							entra = true;
							eliminarMedicoAsignado(medic);
							mostrarMensajeLaboral(medic);
						} else {
							entra = true;
							borrado = true;
							eliminarMedicoDisponibles(medic);
						}

					}
					else {
						entra = true;
						mantener = true;
					}
					}}
		if (!mantener) {
			if (mensaje) {
				entra = true;
				eliminarMedicoAsignado(medic);
				mostrarMensajeLaboral(medic);
			} else {
				entra = true;
				borrado = true;
				eliminarMedicoDisponibles(medic);
			}

		}}
			
		
	}

	private boolean mirarSiJornadaLaboralAEsaHora(LocalTime medicoentrada, LocalTime medicosalida,
			LocalTime entradalocal, LocalTime entradasalida) {
		if (entradalocal.isBefore(medicoentrada))
			return true;
		if (entradasalida.isAfter(medicosalida))
			return true;
		return false;
	}

	private void mostrarMensajeLaboral(Medico medic) {
		warningBox("La hora asignada para la cita no entra dentro de la jornada laboral del medico" + medic,
				"Hora Equivocada");

	}

	private boolean mirarSiEsAntes(LocalTime horaSalidaMedico, LocalTime horaCitaSalida) {
		if (!horaCitaSalida.isAfter(horaSalidaMedico))
			return true;
		return false;
	}

	private boolean mirarSiEsDespues(LocalTime horaEntradaMedico, LocalTime horaCitaEntrada) {
		if (!horaCitaEntrada.isBefore(horaEntradaMedico))
			return true;
		return false;
	}

	protected void validarVacacionesMedico() {
		if (asignadoMedico.getSize() != 0) {
			boolean cita = false;
			for (int i = 0; i < asignadoMedico.getSize(); i++) {
				cita = false;
				Medico medic = asignadoMedico.getElementAt(i);
				cita = DatabaseConsultorImpl.validarMedicoNoVacaciones(medic.getId_Medico(), fechaLocalDate);
				if (cita) {
					i--;
					eliminarMedicoAsignado(medic);
					warningBox("El médico " + medic + " está de vacaciones en esas fechas", "Error horario asignado");
				}
			}
		}
		ponerMedicosDisponiblesVacFiltrado();

	}

	private void ponerMedicosDisponiblesVacFiltrado() {
		borrado = false;
		if (filtradomedico) {
			ponerMedicosDisponiblesFiltradoVacaciones();

		} else {
			ponerTodosMedicosDisponiblesVacaiones();
		}
		activarDescativarBotonAsignar();
	}

	private void ponerTodosMedicosDisponiblesVacaiones() {
		boolean cita = false;
		DefaultListModel<Medico> list = medico;
		for (int i = 0; i < list.getSize(); i++) {
			Medico medic = list.getElementAt(i);
			cita = DatabaseConsultorImpl.validarMedicoNoVacaciones(medic.getId_Medico(), getDay());
			if (cita) {
				eliminarMedicoDisponibles(medic);
				i--;
			}
		}
	}

	private void ponerMedicosDisponiblesFiltradoVacaciones() {
		if (medico.getSize() != 0) {
			DefaultListModel<Medico> list = medico;
			boolean cita = false;
			for (int i = 0; i < list.getSize(); i++) {
				Medico medic = list.getElementAt(i);
				cita = DatabaseConsultorImpl.validarMedicoNoVacaciones(medic.getId_Medico(), getDay());
				if (cita) {
					eliminarMedicoDisponibles(medic);
					i--;

				}

			}

		}
	}

	private void ponerMedicoDisponiblesHorarioLaboral() {
		if (filtradomedico) {
			ponerMedicosDisponiblesFiltradoLaboral();

		} else {
			ponerTodosMedicosDisponiblesLaboral();
		}
		activarDescativarBotonAsignar();
	}

	private void ponerTodosMedicosDisponiblesLaboral() {
		for (int i = 0; i < medico.getSize(); i++) {
			borrado=false;
			Medico medic = medico.getElementAt(i);
			camprobarMedicoJLaboral(medic, false);

			if (borrado)
				i--;

		}
	}

	private void ponerMedicosDisponiblesFiltradoLaboral() {
		DefaultListModel<Medico> medico2 = medico;
		for (int i = 0; i < medico2.getSize(); i++) {
			borrado=false;
			Medico medic = medico2.getElementAt(i);
			camprobarMedicoJLaboral(medic, false);
			if (borrado)
				i--;
		}

	}

	private void eliminarMedicoDisponibles(Medico medic) {
		if(medico.getSize()>0) {
		medico.removeElement(medic);
		listMedicoFiltro.setModel(medico);
		}
	}

	private void ponerMedicosDisponiblesFiltrado() {
		DefaultListModel<Medico> l = medico;
		for (int i = 0; i < l.getSize(); i++) {
			Medico m = l.getElementAt(i);
			List<String> list = DatabaseConsultorImpl.validarMedicoNoCita(m.getId_Medico(), getDay());
			for (String aux : list) {
				String[] aux2 = aux.split(",");
				LocalTime entrada =getHoraEntrada();
				LocalTime salida = getHoraSalida();
				if (mirarSitieneUnaCitaAEsaHora(LocalTime.parse(aux2[0]), LocalTime.parse(aux2[1]),
						entrada, salida)) {
					medico.removeElement(m);
					i--;
					break;
				}

			}
		}
		listMedicoFiltro.setModel(medico);

	}

	private LocalTime getHoraSalida() {
		return VentanaCitas.getHoraSalida();
	}

	private LocalTime getHoraEntrada() {
		return VentanaCitas.getHoraEntrada();
	}

	private LocalDate getDay() {
		return VentanaCitas.getDia();
	}

	private void ponerTodosMedicosDisponibles() {
		List<Medico> listasMedico = DatabaseConsultorImpl.todoslosMedicos();
		medico.removeAllElements();
		for (Medico m : listasMedico) {
			boolean entra = false;
			boolean entra2 = false;
			List<String> list = DatabaseConsultorImpl.validarMedicoNoCita(m.getId_Medico(), getDay());
			for (String aux : list) {
				entra = true;
				String[] aux2 = aux.split(",");
				LocalTime entrada = getHoraEntrada();
				LocalTime salida = getHoraSalida();
				if (mirarSitieneUnaCitaAEsaHora(LocalTime.parse(aux2[0]), LocalTime.parse(aux2[1]),
						entrada, salida)) {
					entra2 = true;
					break;
				}
			}
			List<String> list2 = DatabaseConsultorImpl.validarMedicoNoCita(m.getId_Medico(), getDay().minusDays(1));
				for (String aux : list2) {
					entra = true;
					String[] aux2 = aux.split(",");
					LocalTime entrada = getHoraEntrada();
					LocalTime salida = getHoraSalida();
					if (mirarSitieneUnaCitaAnterior(LocalTime.parse(aux2[0]), LocalTime.parse(aux2[1]),
							entrada, salida)) {
						entra2 = true;
						break;
					}
				}
			if (!entra) {
				medico.addElement(m);
			}
			if (entra && !entra2)
				medico.addElement(m);
		}
		listMedicoFiltro.setModel(medico);

	}




	private boolean mirarSitieneUnaCitaAnterior(LocalTime citaentrada, LocalTime salidacita, LocalTime entrada,
			LocalTime salida) {
		if(salidacita.isBefore(citaentrada)) {
			if(entrada.isBefore(salidacita))
				return true;
		}
		return false;
	}

	private void activarDescativarBotonAsignar() {
		if (medico.isEmpty()) {
			btAsignarMedico.setEnabled(false);
		}
		btAsignarMedico.setEnabled(true);
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
		 if(localHoraEntrada.isAfter(localHoraSalida)&& horaEntrada.isAfter(horaSalida)) {
			return true;
		}
		else if(!localHoraEntrada.isAfter(localHoraSalida)&& !horaEntrada.isAfter(horaSalida)){
			if ((!localHoraEntrada.isBefore(horaEntrada)) && (!localHoraEntrada.isAfter(horaSalida)))
				return true;
			else if ((!localHoraSalida.isBefore(horaEntrada)) && (!localHoraSalida.isAfter(horaSalida)))
				return true;
			else if ((!localHoraEntrada.isAfter(horaEntrada)) && (!localHoraSalida.isBefore(horaSalida)))
				return true;
			else if ((!localHoraEntrada.isBefore(horaEntrada)) && (!localHoraSalida.isAfter(horaSalida)))
				return true;
	    }
		else if(localHoraEntrada.isAfter(localHoraSalida)) {
			if(!horaSalida.isBefore(localHoraEntrada))
				return true;
		}
		else if(horaEntrada.isAfter(horaSalida)) {
			if(!horaEntrada.isAfter(localHoraSalida))
				return true;
		}
		return false;

	}



	private JPanel getPnHoras() {
		if (pnHoras == null) {
			pnHoras = new JPanel();
			pnHoras.setBorder(new TitledBorder(null, "Hora de la cita", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnHoras.setLayout(new GridLayout(3, 2, 0, 0));
			pnHoras.add(getPnlblHora());
			pnHoras.add(getPnlblMes());
			pnHoras.add(getPnlblAno());
		}
		return pnHoras;
	}
	private JLabel getLblDia() {
		if (lblDia == null) {
			lblDia = new JLabel("Dia");
		}
		return lblDia;
	}
	private JPanel getPnlblHora() {
		if (pnlblHora == null) {
			pnlblHora = new JPanel();
			pnlblHora.setLayout(new GridLayout(1, 1, 0, 0));
			pnlblHora.add(getLblDia());
			pnlblHora.add(getTxtDia());
		}
		return pnlblHora;
	}
	private JTextField getTxtDia() {
		if (txtDia == null) {
			txtDia = new JTextField();
			txtDia.setEnabled(false);
			txtDia.setEditable(false);
			txtDia.setText(asignarHoraCita());
			txtDia.setColumns(10);
		}
		return txtDia;
	}
	private String asignarHoraCita() {
	return VentanaCitas.getDia().toString();
	}

	private JPanel getPnlblMes() {
		if (pnlblMes == null) {
			pnlblMes = new JPanel();
			pnlblMes.setLayout(new GridLayout(1, 1, 0, 0));
			pnlblMes.add(getLblHoraEntrada());
			pnlblMes.add(getTxtHoraEntrada());
		}
		return pnlblMes;
	}
	private JLabel getLblHoraEntrada() {
		if (lblHoraEntrada == null) {
			lblHoraEntrada = new JLabel("Hora Entrada");
		}
		return lblHoraEntrada;
	}
	private JTextField getTxtHoraEntrada() {
		if (txtHoraEntrada == null) {
			txtHoraEntrada = new JTextField();
			txtHoraEntrada.setEnabled(false);
			txtHoraEntrada.setEditable(false);
			txtHoraEntrada.setColumns(10);
			txtHoraEntrada.setText(ponerHoraEntradaCita());
		}
		return txtHoraEntrada;
	}
	private String ponerHoraEntradaCita() {
	return VentanaCitas.getHoraEntrada().toString();
	}

	private JPanel getPnlblAno() {
		if (pnlblAno == null) {
			pnlblAno = new JPanel();
			pnlblAno.setLayout(new GridLayout(1, 1, 0, 0));
			pnlblAno.add(getLlblHoraSalida());
			pnlblAno.add(getTxtHoraSalida());
		}
		return pnlblAno;
	}
	private JLabel getLlblHoraSalida() {
		if (llblHoraSalida == null) {
			llblHoraSalida = new JLabel("Hora Salida");
		}
		return llblHoraSalida;
	}
	private JTextField getTxtHoraSalida() {
		if (txtHoraSalida == null) {
			txtHoraSalida = new JTextField();
			txtHoraSalida.setEnabled(false);
			txtHoraSalida.setEditable(false);
			txtHoraSalida.setColumns(10);
			txtHoraSalida.setText(ponerHoraSalidaCita());
		}
		return txtHoraSalida;
	}
	private String ponerHoraSalidaCita() {
		return VentanaCitas.getHoraSalida().toString();
		}
	private JPanel getPnEnfermero() {
		if (pnEnfermero == null) {
			pnEnfermero = new JPanel();
			pnEnfermero.setBorder(new TitledBorder(null, "Enfermero/s", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnEnfermero.setLayout(new GridLayout(2, 1, 0, 0));
			pnEnfermero.add(getPnFiltradoEnfermero());
			pnEnfermero.add(getPnEnfermeroAsignado());
		}
		return pnEnfermero;
	}
	private JPanel getPnFiltradoEnfermero() {
		if (pnFiltradoEnfermero == null) {
			pnFiltradoEnfermero = new JPanel();
			pnFiltradoEnfermero.setBorder(new TitledBorder(null, "Filtrado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnFiltradoEnfermero.setLayout(new GridLayout(0, 1, 0, 0));
			pnFiltradoEnfermero.add(getPnFiltradoNombreEnfermero());
			pnFiltradoEnfermero.add(getPnFiltradoApellidoEnfermero());
			pnFiltradoEnfermero.add(getPnFiltradoEspecialidadEnfermero());
			pnFiltradoEnfermero.add(getPnFiltradoBotonEnfermero());
		}
		return pnFiltradoEnfermero;
	}
	private JPanel getPnFiltradoNombreEnfermero() {
		if (pnFiltradoNombreEnfermero == null) {
			pnFiltradoNombreEnfermero = new JPanel();
			pnFiltradoNombreEnfermero.setLayout(new GridLayout(0, 2, 0, 0));
			pnFiltradoNombreEnfermero.add(getPnNombreEnfermeroFlitradolbl());
			pnFiltradoNombreEnfermero.add(getPnNombreEnfermeroTxt());
		}
		return pnFiltradoNombreEnfermero;
	}
	private JPanel getPnNombreEnfermeroFlitradolbl() {
		if (pnNombreEnfermeroFlitradolbl == null) {
			pnNombreEnfermeroFlitradolbl = new JPanel();
			pnNombreEnfermeroFlitradolbl.setLayout(new BorderLayout(0, 0));
			pnNombreEnfermeroFlitradolbl.add(getLblMedicoNombre_1_1(), BorderLayout.SOUTH);
		}
		return pnNombreEnfermeroFlitradolbl;
	}
	private JPanel getPnNombreEnfermeroTxt() {
		if (pnNombreEnfermeroTxt == null) {
			pnNombreEnfermeroTxt = new JPanel();
			pnNombreEnfermeroTxt.setLayout(new BorderLayout(0, 0));
			pnNombreEnfermeroTxt.add(getTxtNombreEnfermero(), BorderLayout.SOUTH);
		}
		return pnNombreEnfermeroTxt;
	}
	private JPanel getPnFiltradoApellidoEnfermero() {
		if (pnFiltradoApellidoEnfermero == null) {
			pnFiltradoApellidoEnfermero = new JPanel();
			pnFiltradoApellidoEnfermero.setLayout(new GridLayout(0, 2, 0, 0));
			pnFiltradoApellidoEnfermero.add(getPnApellidosFiltradolblEnfermero());
			pnFiltradoApellidoEnfermero.add(getPnEnfermeroApellido());
		}
		return pnFiltradoApellidoEnfermero;
	}
	private JPanel getPnApellidosFiltradolblEnfermero() {
		if (pnApellidosFiltradolblEnfermero == null) {
			pnApellidosFiltradolblEnfermero = new JPanel();
			pnApellidosFiltradolblEnfermero.setLayout(new BorderLayout(0, 0));
			pnApellidosFiltradolblEnfermero.add(getLblEnfermeroApellidos(), BorderLayout.SOUTH);
		}
		return pnApellidosFiltradolblEnfermero;
	}
	private JPanel getPnEnfermeroApellido() {
		if (pnEnfermeroApellido == null) {
			pnEnfermeroApellido = new JPanel();
			pnEnfermeroApellido.setLayout(new BorderLayout(0, 0));
			pnEnfermeroApellido.add(getTxtApellidosEnfermero(), BorderLayout.SOUTH);
		}
		return pnEnfermeroApellido;
	}
	private JPanel getPnFiltradoEspecialidadEnfermero() {
		if (pnFiltradoEspecialidadEnfermero == null) {
			pnFiltradoEspecialidadEnfermero = new JPanel();
			pnFiltradoEspecialidadEnfermero.setLayout(new GridLayout(0, 2, 0, 0));
			pnFiltradoEspecialidadEnfermero.add(getPnEspecialidadFIltradolbl_1());
			pnFiltradoEspecialidadEnfermero.add(getPnEspecialidadCb_1());
		}
		return pnFiltradoEspecialidadEnfermero;
	}
	private JPanel getPnEspecialidadFIltradolbl_1() {
		if (pnEspecialidadFIltradolbl_1 == null) {
			pnEspecialidadFIltradolbl_1 = new JPanel();
			pnEspecialidadFIltradolbl_1.setLayout(new BorderLayout(0, 0));
			pnEspecialidadFIltradolbl_1.add(getLblEspecialidad_1(), BorderLayout.SOUTH);
		}
		return pnEspecialidadFIltradolbl_1;
	}
	private JPanel getPnEspecialidadCb_1() {
		if (pnEspecialidadCb_1 == null) {
			pnEspecialidadCb_1 = new JPanel();
			pnEspecialidadCb_1.setLayout(new BorderLayout(0, 0));
			pnEspecialidadCb_1.add(getCbEspecialidadEnfermero(), BorderLayout.SOUTH);
		}
		return pnEspecialidadCb_1;
	}
	private JPanel getPnFiltradoBotonEnfermero() {
		if (pnFiltradoBotonEnfermero == null) {
			pnFiltradoBotonEnfermero = new JPanel();
			pnFiltradoBotonEnfermero.setLayout(new BorderLayout(0, 0));
			pnFiltradoBotonEnfermero.add(getBtFiltradoMedico_1_1(), BorderLayout.SOUTH);
		}
		return pnFiltradoBotonEnfermero;
	}
	private JLabel getLblMedicoNombre_1_1() {
		if (lblEnfermeroNombre == null) {
			lblEnfermeroNombre = new JLabel("Nombre");
			lblEnfermeroNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblEnfermeroNombre.setDisplayedMnemonic('n');
		}
		return lblEnfermeroNombre;
	}
	private JTextField getTxtNombreEnfermero() {
		if (txtNombreEnfermero == null) {
			txtNombreEnfermero = new JTextField();
		}
		return txtNombreEnfermero;
	}
	private JLabel getLblEnfermeroApellidos() {
		if (lblEnfermeroApellidos == null) {
			lblEnfermeroApellidos = new JLabel("Apellidos");
			lblEnfermeroApellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblEnfermeroApellidos.setDisplayedMnemonic('p');
		}
		return lblEnfermeroApellidos;
	}
	private JTextField getTxtApellidosEnfermero() {
		if (txtApellidosEnfermero == null) {
			txtApellidosEnfermero = new JTextField();
			txtApellidosEnfermero.setColumns(10);
		}
		return txtApellidosEnfermero;
	}
	private JLabel getLblEspecialidad_1() {
		if (lblEspecialidad_1 == null) {
			lblEspecialidad_1 = new JLabel("Especialidad");
			lblEspecialidad_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblEspecialidad_1;
	}
	private JComboBox<String> getCbEspecialidadEnfermero() {
		if (cbEspecialidadEnfermero == null) {
			cbEspecialidadEnfermero = new JComboBox<String>();
			cbEspecialidadEnfermero.setModel(new DefaultComboBoxModel<String>(arrayEspecialidades()));
			cbEspecialidadEnfermero.setSelectedItem(0);
		}
		return cbEspecialidadEnfermero;
	}
	private JButton getBtFiltradoMedico_1_1() {
		if (btFiltradoEnfemero == null) {
			btFiltradoEnfemero = new JButton("Filtrar");
			btFiltradoEnfemero.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clearAsignarEnfermero();
					filtrarEnfermero();
					if (filtradoEnfermero&&!VentanaCitas.desactivarHorario) {
						ponerEnfermeroDisponiblesFiltrado();
						ponerEnfermerosDisponiblesFiltradoVacaciones();
						ponerEnfermerosDisponiblesFiltradoLaboral();
					}

					else if(filtradoEnfermero&&VentanaCitas.desactivarHorario) {
						ponerEnfermerosDisponiblesFiltradoVacaciones();
						ponerTodosEnfermerosDisponiblesLaboralDia();
					}

					else if(!filtradomedico) {
					listEnfermeroFiltro.setModel(copiadefaultEnfermeroFiltrado);}
				}
			});
			btFiltradoEnfemero.setMnemonic('f');
		}
		return btFiltradoEnfemero;
	}
	
	protected void ponerEnfermerosDisponiblesFiltradoVacaciones() {
			if (defaultEnfermeroFiltrado.getSize() != 0) {
				DefaultListModel<Enfermero> list = defaultEnfermeroFiltrado;
				boolean cita = false;
				for (int i = 0; i < list.getSize(); i++) {
					Enfermero e = list.getElementAt(i);
					cita = DatabaseConsultorImpl.validarMedicoNoVacaciones(e.getId_Enfermero(), getDay());
					if (cita) {
						eliminarEnfermerosDisponibles(e);
						i--;

					}

				}

			}
		
		
	}
	private void ponerEnfermerosDisponiblesFiltradoLaboral() {
		DefaultListModel<Enfermero> e = defaultEnfermeroFiltrado;
		for (int i = 0; i < e.getSize(); i++) {
			borrado=false;
			Enfermero e2 = e.getElementAt(i);
			comprobarEnfermeroJLaboral(e2, false);
			if (borrado)
				i--;
		}

	}

	private void ponerEnfermeroDisponiblesFiltrado() {
		DefaultListModel<Enfermero> l = defaultEnfermeroFiltrado;
		for (int i = 0; i < l.getSize(); i++) {
			Enfermero m = l.getElementAt(i);
			List<String> list = DatabaseConsultorImpl.validarEnfermeroNoCita(m.getId_Enfermero(), getDay());
			for (String aux : list) {
				String[] aux2 = aux.split(",");
				LocalTime entrada =getHoraEntrada();
				LocalTime salida = getHoraSalida();
				if (mirarSitieneUnaCitaAEsaHora(LocalTime.parse(aux2[0]), LocalTime.parse(aux2[1]),
						entrada,salida)) {
					defaultEnfermeroFiltrado.removeElement(m);
					i--;
					break;
				}

			}
		}
		listEnfermeroFiltro.setModel(defaultEnfermeroFiltrado);
		}
	/**
	 * Filtra Enfermeros
	 */
	protected void filtrarEnfermero() {
			defaultEnfermeroFiltrado.clear();
			if (!txtNombreEnfermero.getText().equals("") && !txtApellidosEnfermero.getText().equals("")
					&& !cbEspecialidadEnfermero.getSelectedItem().equals(" ")) {
				 añadirEnfermeroEspecialidadApellidoYNombre();
			     filtradoEnfermero = true;
			    }			
			else if (txtNombreEnfermero.getText().equals("") && txtApellidosEnfermero.getText().equals("")
					&& cbEspecialidadEnfermero.getSelectedItem().equals(" ")) {
				listEnfermeroFiltro.setModel(copiadefaultEnfermeroFiltrado);
				  filtradoEnfermero = false;
					
			} else if (txtNombreEnfermero.getText().equals("") && txtApellidosEnfermero.getText().equals("")) {
				añadirEnfermeroEspecialidad();
				filtradoEnfermero = true;
			} else if (txtNombreEnfermero.getText().equals("") && cbEspecialidadEnfermero.getSelectedItem().equals(" ")) {
				añadirEnfermeroApellido();
				filtradoEnfermero = true;
			} else if (cbEspecialidadEnfermero.getSelectedItem().equals(" ") && txtApellidosEnfermero.getText().equals("")) {
				añadirEnfermeroNombre();
				filtradoEnfermero = true;
			} else if (txtApellidosEnfermero.getText().equals("")) {
				añadirNombreEspecialidadEnfermero();
				filtradoEnfermero = true;
			} else if (cbEspecialidadEnfermero.getSelectedItem().equals(" ")) {
				añadirApellidoYNombreEnfermero();
				filtradoEnfermero = true;
			} else if (txtNombreEnfermero.getText().equals("")) {
				añadirEnfermeroEspecialidadApellido();
				filtradoEnfermero = true;
			}

			listEnfermeroFiltro.setModel(defaultEnfermeroFiltrado);

		
		
	}
	
	private void añadirEnfermeroEspecialidadApellido() {
		List<Enfermero> lsitasMedico = DatabaseConsultorImpl.buscarEspecialidadApellidosEnfermero(
				cbEspecialidadEnfermero.getSelectedItem().toString(), txtApellidosEnfermero.getText());
		defaultEnfermeroFiltrado.removeAllElements();
		for (Enfermero medico : lsitasMedico) {
			this.defaultEnfermeroFiltrado.addElement(medico);
		}
		
	}

	private void añadirApellidoYNombreEnfermero() {
		List<Enfermero> lsitase = DatabaseConsultorImpl.buscarNombreApellidosEnfermero(txtNombreEnfermero.getText(),
				txtApellidosEnfermero.getText());
		defaultEnfermeroFiltrado.removeAllElements();
		for (Enfermero e : lsitase) {
			this.defaultEnfermeroFiltrado.addElement(e);
		}
		
	}

	private void añadirNombreEspecialidadEnfermero() {
		List<Enfermero> lsitasMedico = DatabaseConsultorImpl.buscarNombreEspecialidadEnfermero(txtNombreEnfermero.getText(),
				txtApellidosEnfermero.getText());
		
		defaultEnfermeroFiltrado.removeAllElements();
		for (Enfermero e : lsitasMedico) {
			this.defaultEnfermeroFiltrado.addElement(e);
		}
		
	}

	private void añadirEnfermeroNombre() {
		List<Enfermero> lsitase = DatabaseConsultorImpl.buscarNombreEnfermero(txtNombreEnfermero.getText());
		defaultEnfermeroFiltrado.removeAllElements();
		for (Enfermero e : lsitase) {
			this.defaultEnfermeroFiltrado.addElement(e);
		}
		
	}

	private void añadirEnfermeroApellido() {
		List<Enfermero> lsitase = DatabaseConsultorImpl.buscarApellidosEnfermero(txtApellidosEnfermero.getText());
		defaultEnfermeroFiltrado.removeAllElements();
		for (Enfermero e : lsitase) {
			this.defaultEnfermeroFiltrado.addElement(e);
		}
		
	}

	private void añadirEnfermeroEspecialidad() {
	List<Enfermero> lsitase = DatabaseConsultorImpl.buscarEspecialidadEnfemero(cbEspecialidadEnfermero.getSelectedItem().toString());
	defaultEnfermeroFiltrado.removeAllElements();
			for (Enfermero e : lsitase) {
				this.defaultEnfermeroFiltrado.addElement(e);
			}

		}
		
	

	private DefaultListModel<Enfermero> principioEnfermeroDisponibles(){
		if(VentanaCitas.desactivarHorario) {
			ponerTodosTodosEnfermero();
			ponerTodosEnfermerosDisponiblesVacaciones();
			ponerTodosEnfermerosDisponiblesLaboralDia();

		}
		else {
		ponerTodosEnfermerosDisponibles();
		ponerTodosEnfermerosDisponiblesVacaciones();
		ponerTodosEnfermerosDisponiblesLaboral();}
		
		copiarEnfemeroDisponibles();
		return copiadefaultEnfermeroFiltrado;
	}


	private void ponerTodosEnfermerosDisponiblesLaboralDia() {
		for (int i = 0; i < defaultEnfermeroFiltrado.getSize(); i++) {
			borrado=false;
			Enfermero medic = defaultEnfermeroFiltrado.getElementAt(i);
			camprobarEnfermerosJLaboralDia(medic);
			if(borrado) {
				i--;}
		}
       
	
	}

	private void camprobarEnfermerosJLaboralDia(Enfermero e) {
		    boolean entro=false;
			List<String> dias= DatabaseConsultorImpl.validarEnfermeroLaboralDia(e.getId_Enfermero());
			for(String d:dias) {
				String[] r=d.split(",");
				String finicio=r[0];
				String ffin=r[1];
				 LocalDate fechainicio = LocalDate.parse(finicio, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				 LocalDate fechafin = LocalDate.parse(ffin, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				 if(fechainicio.equals(getDay())||fechafin.equals(getDay())) {
					  entro=true;
					
				 }
					if(!entro) {
						 eliminarEnfermerosDisponibles(e);
						 borrado=true;}
			}
		
		
	}

		
	private void ponerTodosTodosEnfermero() {
		List<Enfermero> m=DatabaseConsultorImpl.TodoslosEnfermeros();
		for(int i=0;i<m.size();i++) {
			defaultEnfermeroFiltrado.addElement(m.get(i));
		}

	}
	private void copiarEnfemeroDisponibles() {
		for(int i=0;i<defaultEnfermeroFiltrado.getSize();i++)
			copiadefaultEnfermeroFiltrado.addElement(defaultEnfermeroFiltrado.get(i));
		
	}

	private void ponerTodosEnfermerosDisponiblesLaboral() {
		for (int i = 0; i < defaultEnfermeroFiltrado.getSize(); i++) {
			borrado=false;
			Enfermero medic = defaultEnfermeroFiltrado.getElementAt(i);
			comprobarEnfermeroJLaboral(medic, false);

			if (borrado)
				i--;

		}
		
	}

	private void comprobarEnfermeroJLaboral(Enfermero e, boolean mensaje) {
		boolean entra = false;
		LocalTime entrada = getHoraEntrada();
		LocalTime salida = getHoraSalida();
		boolean mantener = false;
		String horaInicioFin;
		if(!salida.isBefore(entrada)) {
		horaInicioFin = DatabaseConsultorImpl.validarEnferermeroLaboralInicioFinIgual(e.getId_Enfermero(),
				getDay() );
		if (horaInicioFin != null) {
			String[] separarFechas = horaInicioFin.split(",");
			String[] fhinicio = separarFechas[0].split("-");
			String[] fhfin = separarFechas[1].split("-");
			String horaInicio = fhinicio[1];
			String horafin = fhfin[1];
			if (mirarSiJornadaLaboralAEsaHora(LocalTime.parse(horaInicio), LocalTime.parse(horafin),
					entrada, salida)) {
				entra = true;
				if (mensaje) {
					eliminarEnfermeroAsignado(e);
					mostrarMensajeLaboral(e);
				} else {
					borrado = true;
					eliminarEnfermerosDisponibles(e);
				}

			} else {
				entra = true;
				mantener = true;
			}

		}
		if (!entra) {
			horaInicioFin = DatabaseConsultorImpl.validarEnfermeroLaboralInicioIgual(e.getId_Enfermero(),getDay() );
			if (horaInicioFin != null) {
				String[] separarFechas = horaInicioFin.split("-");
				String horaInicio = separarFechas[1];
				if (!mirarSiEsDespues(LocalTime.parse(horaInicio), entrada)) {
					if (mensaje) {
						entra = true;
						eliminarEnfermeroAsignado(e);
						mostrarMensajeLaboral(e);
					} else {
						entra = true;
						borrado = true;
						eliminarEnfermerosDisponibles(e);
					}

				} else {
					entra = true;
					mantener = true;
				}
			}
		}
		if (!entra) {
			horaInicioFin = DatabaseConsultorImpl.validarEnfermeroLaboralFinIgual(e.getId_Enfermero(), getDay());
			if (horaInicioFin != null) {
				String[] separarFechas = horaInicioFin.split("-");
				String horaInicio = separarFechas[1];
				if (!mirarSiEsAntes(LocalTime.parse(horaInicio), salida)) {
					
					if (mensaje) {
						entra = true;
						eliminarEnfermeroAsignado(e);
						mostrarMensajeLaboral(e);
					} else {
						entra = true;
						borrado = true;
						eliminarEnfermerosDisponibles(e);
					}

				} else {
					entra = true;
					mantener = true;
				}
			}
		}
		if (!mantener) {
			borrado = true;
			eliminarEnfermerosDisponibles(e);
			eliminarEnfermeroAsignado(e);

		}}
		else {
				String horasInico = DatabaseConsultorImpl.validarEnfermeroLaboralInicioIgual(e.getId_Enfermero(),getDay());
				if (horasInico != null) {
					String horasFin = DatabaseConsultorImpl.validarEnfermeroLaboralFinIgual(e.getId_Enfermero(),getDay().plusDays(1));
					if(horasFin!=null) {
					String[] separarFechas = horasInico.split("-");
					String horaInico = separarFechas[1];
					String[] separaFechasF = horasFin.split("-");
					String horaFin = separaFechasF[1];
					if (entrada.isBefore(LocalTime.parse(horaInico)) ||salida.isAfter(LocalTime.parse(horaFin))){
						if (mensaje) {
							entra = true;
							eliminarEnfermeroAsignado(e);
							mostrarMensajeLaboral(e);
						} else {
							entra = true;
							borrado = true;
							eliminarEnfermerosDisponibles(e);
						}

					}
					else {
						entra = true;
						mantener = true;
					}
					}}
		if (!mantener) {
			if (mensaje) {
				entra = true;
				eliminarEnfermeroAsignado(e);
				mostrarMensajeLaboral(e);
			} else {
				entra = true;
				borrado = true;
				eliminarEnfermerosDisponibles(e);
			}

		}}
		}
	


	private void mostrarMensajeLaboral(Enfermero e) {
		warningBox("La hora asignada para la cita no entra dentro de la jornada laboral del enfermero" + e,
				"Hora Equivocada");
		
	}

	private void ponerTodosEnfermerosDisponiblesVacaciones() {
		boolean cita = false;
		DefaultListModel<Enfermero> list = defaultEnfermeroFiltrado;
		for (int i = 0; i < list.getSize(); i++) {
			Enfermero e = list.getElementAt(i);
			cita = DatabaseConsultorImpl.validarEnfermeroNoVacaciones(e.getId_Enfermero(), getDay());
			if (cita) {
				eliminarEnfermerosDisponibles(e);
				i--;
			}
		}
	}

	private void eliminarEnfermerosDisponibles(Enfermero e) {
		    defaultEnfermeroFiltrado.removeElement(e);
			listEnfermeroFiltro.setModel(defaultEnfermeroFiltrado);
//			mirarSiHayceroEnfermerosAsignados();
		
		
	}

	private void ponerTodosEnfermerosDisponibles() {
		List<Enfermero> listase = DatabaseConsultorImpl.TodoslosEnfermeros();
		defaultEnfermeroFiltrado.removeAllElements();
		for (Enfermero e: listase) {
			boolean entra = false;
			boolean entra2 = false;
			
			List<String> list = DatabaseConsultorImpl.validarEnfermeroNoCita(e.getId_Enfermero(), getDay());
			for (String aux : list) {
				entra = true;
				String[] aux2 = aux.split(",");
				LocalTime entrada = getHoraEntrada();
				LocalTime salida = getHoraSalida();
				if (mirarSitieneUnaCitaAEsaHora(LocalTime.parse(aux2[0]), LocalTime.parse(aux2[1]),
						entrada,salida)) {
					entra2 = true;
					break;
				}
			}
			List<String> listw = DatabaseConsultorImpl.validarMedicoNoCita(e.getId_Enfermero(), getDay().minusDays(1));
			for (String aux : listw) {
				entra = true;
				String[] aux2 = aux.split(",");
				LocalTime entrada = getHoraEntrada();
				LocalTime salida = getHoraEntrada();
				if (mirarSitieneUnaCitaAnterior(LocalTime.parse(aux2[0]), LocalTime.parse(aux2[1]),
						entrada,salida)) {
					entra2 = true;
					break;
				}
			}
			if (!entra) {
				defaultEnfermeroFiltrado.addElement(e);
			}
			if (entra && !entra2)
				defaultEnfermeroFiltrado.addElement(e);
		}
		listEnfermeroFiltro.setModel(defaultEnfermeroFiltrado);
		
	}

	private void añadirEnfermeroDisponibles() {
		List<Enfermero> lsitase = DatabaseConsultorImpl.TodoslosEnfermeros();
		defaultEnfermeroFiltrado.removeAllElements();
		for (Enfermero e : lsitase) {
			this.defaultEnfermeroFiltrado.addElement(e);
		}
	}

	// EMPIEZA
	private void añadirEnfermeroEspecialidadApellidoYNombre() {
		List<Enfermero> lsitase = DatabaseConsultorImpl.buscarNombreEspecialidadApellidosEnfermero(
				txtNombreEnfermero.getText(), cbEspecialidadEnfermero.getSelectedItem().toString(), txtApellidosEnfermero.getText());
		defaultEnfermeroFiltrado.removeAllElements();
		for (Enfermero e : lsitase) {
			this.defaultEnfermeroFiltrado.addElement(e);
		}

	}


	protected void clearAsignarEnfermero() {
			if (defaultEnfermeroFiltrado != null) {
				// medico.clear();
				listEnfermeroFiltro.setModel(defaultEnfermeroFiltrado);
				filtradoEnfermero = false;
			}

		}
		


	private JPanel getPnEnfermeroAsignado() {
		if (pnEnfermeroAsignado == null) {
			pnEnfermeroAsignado = new JPanel();
			pnEnfermeroAsignado.setLayout(new BoxLayout(pnEnfermeroAsignado, BoxLayout.X_AXIS));
			pnEnfermeroAsignado.add(getPnEnfermeroDisponibles());
			pnEnfermeroAsignado.add(getPnEnfermeroAsignarEliminar());
			pnEnfermeroAsignado.add(getPnEnfermeroAsignar());
		}
		return pnEnfermeroAsignado;
	}
	private JPanel getPnEnfermeroDisponibles() {
		if (pnEnfermeroDisponibles == null) {
			pnEnfermeroDisponibles = new JPanel();
			pnEnfermeroDisponibles.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Enfermeros Disponibles", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnEnfermeroDisponibles.setLayout(new BorderLayout(0, 0));
			pnEnfermeroDisponibles.add(getScrollPaneEnfermeroAAsignar(), BorderLayout.CENTER);
		}
		return pnEnfermeroDisponibles;
	}
	private JPanel getPnEnfermeroAsignarEliminar() {
		if (pnEnfermeroAsignarEliminar == null) {
			pnEnfermeroAsignarEliminar = new JPanel();
			pnEnfermeroAsignarEliminar.setLayout(new GridLayout(2, 1, 10, 10));
			pnEnfermeroAsignarEliminar.add(getPnAsignarEnfemero());
			pnEnfermeroAsignarEliminar.add(getPnEliminarEnfemero());
		}
		return pnEnfermeroAsignarEliminar;
	}
	private JPanel getPnAsignarEnfemero() {
		if (pnAsignarEnfemero == null) {
			pnAsignarEnfemero = new JPanel();
			pnAsignarEnfemero.setLayout(new BorderLayout(0, 0));
			pnAsignarEnfemero.add(getBtAsignarEnfemero(), BorderLayout.SOUTH);
		}
		return pnAsignarEnfemero;
	}
	private JPanel getPnEliminarEnfemero() {
		if (pnEliminarEnfemero == null) {
			pnEliminarEnfemero = new JPanel();
			pnEliminarEnfemero.setLayout(new BorderLayout(0, 0));
			pnEliminarEnfemero.add(getBtEliminarEnfermero(), BorderLayout.NORTH);
		}
		return pnEliminarEnfemero;
	}
	private JPanel getPnEnfermeroAsignar() {
		if (pnEnfermeroAsignar == null) {
			pnEnfermeroAsignar = new JPanel();
			pnEnfermeroAsignar.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Enfermero/s asginado/s", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnEnfermeroAsignar.setLayout(new BorderLayout(0, 0));
			pnEnfermeroAsignar.add(getScrollPaneEnfermeroAsignados(), BorderLayout.CENTER);
		}
		return pnEnfermeroAsignar;
	}
	private JScrollPane getScrollPaneEnfermeroAAsignar() {
		if (scrollPaneEnfermeroAAsignar == null) {
			scrollPaneEnfermeroAAsignar = new JScrollPane();
			scrollPaneEnfermeroAAsignar.setViewportView(getListEnfermeroFiltro());
		}
		return scrollPaneEnfermeroAAsignar;
	}
	private JList<Enfermero> getListEnfermeroFiltro() {
		if (listEnfermeroFiltro == null) {
			listEnfermeroFiltro = new JList<Enfermero>();
			listEnfermeroFiltro.setBorder(new LineBorder(new Color(0, 0, 0)));
			listEnfermeroFiltro.setModel(principioEnfermeroDisponibles());
		}
		return listEnfermeroFiltro;
	}
	private JButton getBtAsignarEnfemero() {
		if (btAsignarEnfemero == null) {
			btAsignarEnfemero = new JButton("Asignar");
			btAsignarEnfemero.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				asignarEnfermero();

				}
			});
		}
		return btAsignarEnfemero;
	}
	//Pone a los enfermeros en la lista de enfermeros a asignar
	protected void asignarEnfermero() {
			if (!mirarSiYaEstaElEnfermero(listEnfermeroFiltro.getSelectedValue())) {
				defaultAsignarEnfermero.addElement(listEnfermeroFiltro.getSelectedValue());
				btEliminarEnfermero.setEnabled(true);
				listEnfermeroAsignado.setModel(defaultAsignarEnfermero);
		        panelEspecialistas(false);

		}
		
	}

	private boolean mirarSiYaEstaElEnfermero(Enfermero enfermero) {
			return defaultAsignarEnfermero.contains(enfermero);

		
	}

	private JButton getBtEliminarEnfermero() {
		if (btEliminarEnfermero == null) {
			btEliminarEnfermero = new JButton("Eliminar");
			btEliminarEnfermero.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
                    eliminarEnfermeroAsignado(listEnfermeroAsignado.getSelectedValue());
					eliminarEnfermeroAsignado(listEnfermeroFiltro.getSelectedValue());
					mirarSiHayceroEnfermerosAsignados();
				}
			});
			btEliminarEnfermero.setEnabled(false);
		}
		return btEliminarEnfermero;
	}
	/**
	 * Elimina el medico asignado
	 */
	private void eliminarEnfermeroAsignado(Enfermero enfermero) {
		if(defaultAsignarEnfermero.getSize()>0) {
		defaultAsignarEnfermero.removeElement(enfermero);
		listEnfermeroAsignado.setModel(defaultAsignarEnfermero);
		mirarSiHayceroEnfermerosAsignados();
		}
	}

	/**
	 * Mirar si la lista de medicos asignados para desabilitar el boton eliminar
	 */
	private void mirarSiHayceroEnfermerosAsignados() {
		if (defaultAsignarEnfermero.size() == 0) {
			btEliminarEnfermero.setEnabled(false);
		panelEspecialistas(true);}
	}

	private JScrollPane getScrollPaneEnfermeroAsignados() {
		if (scrollPaneEnfermeroAsignados == null) {
			scrollPaneEnfermeroAsignados = new JScrollPane();
			scrollPaneEnfermeroAsignados.setViewportView(getListEnfermeroAsignado());
		}
		return scrollPaneEnfermeroAsignados;
	}
	private JList<Enfermero> getListEnfermeroAsignado() {
		if (listEnfermeroAsignado == null) {
			listEnfermeroAsignado = new JList<Enfermero>();
			listEnfermeroAsignado.setBorder(new LineBorder(new Color(0, 0, 0)));
			if(VentanaCitas.listEnfermeroAsignados.getModel().getSize()>0) {
				for(int i=0;i<VentanaCitas.listEnfermeroAsignados.getModel().getSize();i++) {
				defaultAsignarEnfermero.addElement(VentanaCitas.listEnfermeroAsignados.getModel().getElementAt(i));
				}
				listEnfermeroAsignado.setModel(defaultAsignarEnfermero);
				btEliminarEnfermero.setEnabled(true);
				enfermeros=true;

			}
		}
		return listEnfermeroAsignado;
	}
	private JPanel getPnEspecialista() {
		if (pnEspecialista == null) {
			pnEspecialista = new JPanel();
			pnEspecialista.setBorder(new TitledBorder(null, "Especialistas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnEspecialista.setLayout(new GridLayout(2, 1, 0, 0));
			pnEspecialista.add(getPnFiltradoEspecialidatas());
			pnEspecialista.add(getPnEspecilistaAsignado());
		}
		return pnEspecialista;
	}
	private JPanel getPnFiltradoEspecialidatas() {
		if (pnFiltradoEspecialidatas == null) {
			pnFiltradoEspecialidatas = new JPanel();
			pnFiltradoEspecialidatas.setBorder(new TitledBorder(null, "Filtrado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnFiltradoEspecialidatas.setLayout(new GridLayout(2, 1, 0, 0));
			pnFiltradoEspecialidatas.add(getPnFiltradoNombre());
			pnFiltradoEspecialidatas.add(getPnFiltradoBotoEspecilistas());
		}
		return pnFiltradoEspecialidatas;
	}
	private JPanel getPnFiltradoNombre() {
		if (pnFiltradoNombre == null) {
			pnFiltradoNombre = new JPanel();
			pnFiltradoNombre.setLayout(new GridLayout(0, 2, 0, 0));
			pnFiltradoNombre.add(getPnNombreEspecialidadFiltradolbl());
			pnFiltradoNombre.add(getPnNombreEspecialistaTxt());
		}
		return pnFiltradoNombre;
	}
	private JPanel getPnNombreEspecialidadFiltradolbl() {
		if (pnNombreEspecialidadFiltradolbl == null) {
			pnNombreEspecialidadFiltradolbl = new JPanel();
			pnNombreEspecialidadFiltradolbl.setLayout(new BorderLayout(0, 0));
			pnNombreEspecialidadFiltradolbl.add(getLblEspecialidadNombre(), BorderLayout.SOUTH);
		}
		return pnNombreEspecialidadFiltradolbl;
	}
	private JLabel getLblEspecialidadNombre() {
		if (lblEspecialidadNombre == null) {
			lblEspecialidadNombre = new JLabel("Nombre");
			lblEspecialidadNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblEspecialidadNombre;
	}
	private JPanel getPnNombreEspecialistaTxt() {
		if (pnNombreEspecialistaTxt == null) {
			pnNombreEspecialistaTxt = new JPanel();
			pnNombreEspecialistaTxt.setLayout(new BorderLayout(0, 0));
			pnNombreEspecialistaTxt.add(getTxtNombreEspecialista(), BorderLayout.SOUTH);
		}
		return pnNombreEspecialistaTxt;
	}
	private JTextField getTxtNombreEspecialista() {
		if (txtNombreEspecialista == null) {
			txtNombreEspecialista = new JTextField();
			txtNombreEspecialista.setColumns(10);
		}
		return txtNombreEspecialista;
	}
	private JPanel getPnFiltradoBotoEspecilistas() {
		if (pnFiltradoBotoEspecilistas == null) {
			pnFiltradoBotoEspecilistas = new JPanel();
			pnFiltradoBotoEspecilistas.setLayout(new BorderLayout(0, 0));
			pnFiltradoBotoEspecilistas.add(getBtnBotonFiltradoEspecilistas(), BorderLayout.SOUTH);
		}
		return pnFiltradoBotoEspecilistas;
	}
	private JButton getBtnBotonFiltradoEspecilistas() {
		if (btnBotonFiltradoEspecilistas == null) {
			btnBotonFiltradoEspecilistas = new JButton("Filtrar");
			btnBotonFiltradoEspecilistas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					filtradoEspecialista();
				}
			});
		}
		return btnBotonFiltradoEspecilistas;
	}
	protected void filtradoEspecialista() {
		if (txtNombreEspecialista.getText().equals("")) {
			listEspecilistasDisponibles.setModel(copiadefaultEspecialistaFiltrado);}
		else {
			List<Especialista> lsitase = DatabaseConsultorImpl.buscarEspecialistasPorNombre(txtNombreEspecialista.getText());
			defaultEspecialistaFiltrado.removeAllElements();
			for (Especialista e : lsitase) {
				this.defaultEspecialistaFiltrado.addElement(e);
			}
		}

		
	}

	private JPanel getPnEspecilistaAsignado() {
		if (pnEspecilistaAsignado == null) {
			pnEspecilistaAsignado = new JPanel();
			pnEspecilistaAsignado.setLayout(new BoxLayout(pnEspecilistaAsignado, BoxLayout.X_AXIS));
			pnEspecilistaAsignado.add(getPnEspcialistasDisponibles());
			pnEspecilistaAsignado.add(getPnEspecialistaAsignarEliminar());
			pnEspecilistaAsignado.add(getPnEspecialistasAsignar());
		}
		return pnEspecilistaAsignado;
	}
	private JPanel getPnEspcialistasDisponibles() {
		if (pnEspcialistasDisponibles == null) {
			pnEspcialistasDisponibles = new JPanel();
			pnEspcialistasDisponibles.setBorder(new TitledBorder(null, "Especialistas Disponibles", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnEspcialistasDisponibles.setLayout(new BorderLayout(0, 0));
			pnEspcialistasDisponibles.add(getScrollPaneEspacilistasDisponibles());
		}
		return pnEspcialistasDisponibles;
	}
	private JScrollPane getScrollPaneEspacilistasDisponibles() {
		if (scrollPaneEspacilistasDisponibles == null) {
			scrollPaneEspacilistasDisponibles = new JScrollPane();
			scrollPaneEspacilistasDisponibles.setViewportView(getListEspecilistasDisponibles());
		}
		return scrollPaneEspacilistasDisponibles;
	}
	private JList<Especialista> getListEspecilistasDisponibles() {
		if (listEspecilistasDisponibles == null) {
			listEspecilistasDisponibles = new JList<Especialista>();
			listEspecilistasDisponibles.setBorder(new LineBorder(new Color(0, 0, 0)));
			listEspecilistasDisponibles.setModel(todosLosEspecialistas());
		}
		return listEspecilistasDisponibles;
	}
	private DefaultListModel<Especialista> todosLosEspecialistas() {
		List<Especialista> m=DatabaseConsultorImpl.todoslosEspecialistas();
		for(int i=0;i<m.size();i++) 
			defaultEspecialistaFiltrado.addElement(m.get(i));
		copiarEspecialistasFiltrado();
		return defaultEspecialistaFiltrado;
	}

	private void copiarEspecialistasFiltrado() {
		for (int i=0;i<defaultEspecialistaFiltrado.size();i++) {
			copiadefaultEspecialistaFiltrado.addElement(defaultEspecialistaFiltrado.get(i));
		}
		
	}

	private JPanel getPnEspecialistaAsignarEliminar() {
		if (pnEspecialistaAsignarEliminar == null) {
			pnEspecialistaAsignarEliminar = new JPanel();
			pnEspecialistaAsignarEliminar.setLayout(new GridLayout(2, 1, 10, 10));
			pnEspecialistaAsignarEliminar.add(getPnAsignarEspecilista());
			pnEspecialistaAsignarEliminar.add(getPnEspecilistaEliminar());
		}
		return pnEspecialistaAsignarEliminar;
	}
	private JPanel getPnAsignarEspecilista() {
		if (pnAsignarEspecilista == null) {
			pnAsignarEspecilista = new JPanel();
			pnAsignarEspecilista.setLayout(new BorderLayout(0, 0));
			pnAsignarEspecilista.add(getBtnEspecialistaAsignar(), BorderLayout.SOUTH);
		}
		return pnAsignarEspecilista;
	}
	private JPanel getPnEspecilistaEliminar() {
		if (pnEspecilistaEliminar == null) {
			pnEspecilistaEliminar = new JPanel();
			pnEspecilistaEliminar.setLayout(new BorderLayout(0, 0));
			pnEspecilistaEliminar.add(getBtnEspecialistaEliminar(), BorderLayout.NORTH);
		}
		return pnEspecilistaEliminar;
	}
	private JButton getBtnEspecialistaAsignar() {
		if (btnEspecialistaAsignar == null) {
			btnEspecialistaAsignar = new JButton("Asignar");
			btnEspecialistaAsignar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnEspecialistaAsignar.setEnabled(false);
					añadirEspecialista();
					btnEspecialistaEliminar.setEnabled(true);
					pnMedicosYEnfermeros(false);
				}
			});
		}
		return btnEspecialistaAsignar;
	}
	protected void pnMedicosYEnfermeros(boolean b) {
	pnMedicos(b);
	pnEnfermeros(b);
	}

	private void pnEnfermeros(boolean b) {
		txtNombreEnfermero.setEnabled(b);
		txtApellidosEnfermero.setEnabled(b);
		cbEspecialidadEnfermero.setEnabled(b);
		listEnfermeroAsignado.setEnabled(b);
		listEnfermeroFiltro.setEnabled(b);
		btAsignarEnfemero.setEnabled(b);

		
	}

	private void pnMedicos(boolean b) {
	txtNombreMedico.setEnabled(b);
	txtApellidos.setEnabled(b);
	cbEspecialidadMedico.setEnabled(b);
	listMedicoAsignado.setEnabled(b);
	listMedicoFiltro.setEnabled(b);
	btAsignarMedico.setEnabled(b);

		
	}

	protected void añadirEspecialista() {
		if (!mirarSiYaEstaElEspecialista(listEspecilistasDisponibles.getSelectedValue()) && defaultEspecialistaAsignado.size() < 1) {
			defaultEspecialistaAsignado.addElement(listEspecilistasDisponibles.getSelectedValue());
			listEspecilistaAsignado.setModel(defaultEspecialistaAsignado);
		}
		
	}

	private boolean mirarSiYaEstaElEspecialista(Especialista especialista) {
		return defaultEspecialistaAsignado.contains(especialista);
	}

	private JButton getBtnEspecialistaEliminar() {
		if (btnEspecialistaEliminar == null) {
			btnEspecialistaEliminar = new JButton("Eliminar");
			btnEspecialistaEliminar.setEnabled(false);
			btnEspecialistaEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					eliminarEspecialistas(listEspecilistaAsignado.getSelectedValue());
					eliminarEspecialistas(listEspecilistasDisponibles.getSelectedValue());
					btnEspecialistaAsignar.setEnabled(true);
					btnEspecialistaEliminar.setEnabled(false);
					pnMedicosYEnfermeros(true);
			
				}
			});
		}
		return btnEspecialistaEliminar;
	}
	protected void eliminarEspecialistas(Especialista especialista) {
		defaultEspecialistaAsignado.removeElement(especialista);
		listEspecilistaAsignado.setModel(defaultEspecialistaAsignado);
		
		
	}

	private JPanel getPnEspecialistasAsignar() {
		if (pnEspecialistasAsignar == null) {
			pnEspecialistasAsignar = new JPanel();
			pnEspecialistasAsignar.setBorder(new TitledBorder(null, "Especialista asignado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnEspecialistasAsignar.setLayout(new BorderLayout(0, 0));
			pnEspecialistasAsignar.add(getScrollPaneEspecialistaAsignado(), BorderLayout.CENTER);
		}
		return pnEspecialistasAsignar;
	}
	private JScrollPane getScrollPaneEspecialistaAsignado() {
		if (scrollPaneEspecialistaAsignado == null) {
			scrollPaneEspecialistaAsignado = new JScrollPane();
			scrollPaneEspecialistaAsignado.setViewportView(getListEspecilistaAsignado());
		}
		return scrollPaneEspecialistaAsignado;
	}
	private JList<Especialista> getListEspecilistaAsignado() {
		if (listEspecilistaAsignado == null) {
			listEspecilistaAsignado = new JList<Especialista>();
			listEspecilistaAsignado.setBorder(new LineBorder(new Color(0, 0, 0)));
			if(VentanaCitas.listEspecialista.getModel().getSize()>0) {
				for(int i=0;i<VentanaCitas.listEspecialista.getModel().getSize();i++) {
				defaultEspecialistaAsignado.addElement(VentanaCitas.listEspecialista.getModel().getElementAt(i));
				}
				listEspecilistaAsignado.setModel(defaultEspecialistaAsignado);
				pnMedicosYEnfermeros(false);
				btnEspecialistaAsignar.setEnabled(false);
				btnEspecialistaEliminar.setEnabled(true);
				
			}
			if(medicos||enfermeros) {
				panelEspecialistas(false);
			}
		}
		return listEspecilistaAsignado;
	}

	private void panelEspecialistas(boolean b) {
		txtNombreEspecialista.setEnabled(b);
		listEspecilistaAsignado.setEnabled(b);
		listEspecilistasDisponibles.setEnabled(b);
		btnEspecialistaAsignar.setEnabled(b);
		btnBotonFiltradoEspecilistas.setEnabled(b);
		
	}
}

