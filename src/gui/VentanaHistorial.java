package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import negocio.citas.Cita;
import negocio.citas.Ubicacion;
import negocio.consulta.impl.DatabaseConsultorImpl;
import negocio.paciente.Paciente;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.LineBorder;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaHistorial extends JFrame {

	private JPanel contentPane;
	private JPanel pnInformaciónPaciente;
	private JPanel pnInformacionNombre;
	private JPanel pnInformacionHistorial;
	private JLabel lblPacienteNombre;
	private JTextField txtNombre;
	private JLabel lblNHistorial;
	private JTextField txtHistorial;
	private JPanel pnInformacionSanitaria;
	private JLabel lblSanitaria;
	private JTextField txtSanitaria;
	private JPanel pnIzqInformacionPanel;
	private JPanel pnTablas;
	private JPanel pnTablasTodos;
	private JPanel pnVacunasTodas;
	private DefaultListModel<String> listTodasLasVacunas = new DefaultListModel<String>();
	private DefaultListModel<String> listTodasLasEnfermedades = new DefaultListModel<String>();
	private JPanel pnEnfermedadesTodas;
	private Paciente paciente= new Paciente();
	private JPanel pnFiltroVacuna;
	private JPanel pnNombreVacuna;
	private JPanel pnNombrelbl;
	private JLabel lblNombreVacuna;
	private JPanel pnTxtNombre;
	private JTextField txtNombreVacuna;
	private JPanel pnVacunaD;
	private JScrollPane scrollPaneVacunasTodas;
	private JPanel pnBtFiltroVacuna;
	private JButton btFiltradoVacuna;
	private JList<String> listTodasVacunas;
	private JPanel pnFiltroEnfermedad;
	private JPanel pnNombreEnfermedad;
	private JPanel pnNombrelblEnfermedad;
	private JLabel lblNombreEnfermedad;
	private JPanel pnTxtNombreEnfermedad;
	private JTextField txtNombreEnfermedad;
	private JPanel pnBtFiltroEnfermedad;
	private JButton btFiltradoEnfermedad;
	private JPanel pnEnfermedadD;
	private JScrollPane scrollPaneETodasnfermedades;
	private JList<String> listTodasEnfermedades;
	private DefaultListModel<String> copiaTodasLasVacunas= new DefaultListModel<String>();
	private DefaultListModel<String> copiaTodasLasEnfermedades= new DefaultListModel<String>();
	/**
	 * Create the frame.
	 * @param paciente 
	 */
	public VentanaHistorial(Paciente paciente) {
		this.paciente=paciente;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1096, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnInformaciónPaciente(), BorderLayout.NORTH);
		contentPane.add(getPnTablas());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private JPanel getPnInformaciónPaciente() {
		if (pnInformaciónPaciente == null) {
			pnInformaciónPaciente = new JPanel();
			pnInformaciónPaciente.setLayout(new GridLayout(0, 1, 50, 50));
			pnInformaciónPaciente.add(getPnIzqInformacionPanel());
		}
		return pnInformaciónPaciente;
	}
	private JPanel getPnInformacionNombre() {
		if (pnInformacionNombre == null) {
			pnInformacionNombre = new JPanel();
			pnInformacionNombre.setLayout(new BorderLayout(0, 0));
			pnInformacionNombre.add(getLblPacienteNombre(), BorderLayout.WEST);
			pnInformacionNombre.add(getTextField_1(), BorderLayout.CENTER);
		}
		return pnInformacionNombre;
	}
	private JPanel getPnInformacionHistorial_1() {
		if (pnInformacionHistorial == null) {
			pnInformacionHistorial = new JPanel();
			pnInformacionHistorial.setLayout(new BorderLayout(0, 0));
			pnInformacionHistorial.add(getLblNHistorial(), BorderLayout.WEST);
			pnInformacionHistorial.add(getTextField_1_1());
		}
		return pnInformacionHistorial;
	}
	private JLabel getLblPacienteNombre() {
		if (lblPacienteNombre == null) {
			lblPacienteNombre = new JLabel("Paciente");
			lblPacienteNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblPacienteNombre;
	}
	private JTextField getTextField_1() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setEditable(false);
			txtNombre.setColumns(10);
			txtNombre.setText(this.paciente.getNombre()+" "+this.paciente.getApellido());
		}
		return txtNombre;
	}
	private JLabel getLblNHistorial() {
		if (lblNHistorial == null) {
			lblNHistorial = new JLabel("NHistorial");
			lblNHistorial.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblNHistorial;
	}
	private JTextField getTextField_1_1() {
		if (txtHistorial == null) {
			txtHistorial = new JTextField();
			txtHistorial.setEditable(false);
			txtHistorial.setColumns(10);
			txtHistorial.setText(this.paciente.getId_historial());
		}
		return txtHistorial;
	}
	private JPanel getPnInformacionSanitaria() {
		if (pnInformacionSanitaria == null) {
			pnInformacionSanitaria = new JPanel();
			pnInformacionSanitaria.setLayout(new BorderLayout(0, 0));
			pnInformacionSanitaria.add(getLblSanitaria(), BorderLayout.WEST);
			pnInformacionSanitaria.add(getTxtSanitaria(), BorderLayout.CENTER);
		}
		return pnInformacionSanitaria;
	}
	private JLabel getLblSanitaria() {
		if (lblSanitaria == null) {
			lblSanitaria = new JLabel("Tarjeta Sanitaria");
			lblSanitaria.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblSanitaria;
	}
	private JTextField getTxtSanitaria() {
		if (txtSanitaria == null) {
			txtSanitaria = new JTextField();
			txtSanitaria.setEditable(false);
			txtSanitaria.setColumns(10);
			txtSanitaria.setText(this.paciente.getId_sanitario());
		}
		return txtSanitaria;
	}
	private JPanel getPnIzqInformacionPanel() {
		if (pnIzqInformacionPanel == null) {
			pnIzqInformacionPanel = new JPanel();
			pnIzqInformacionPanel.setLayout(new GridLayout(1, 4, 0, 0));
			pnIzqInformacionPanel.add(getPnInformacionNombre());
			pnIzqInformacionPanel.add(getPnInformacionSanitaria());
			pnIzqInformacionPanel.add(getPnInformacionHistorial_1());
		}
		return pnIzqInformacionPanel;
	}
	private JPanel getPnTablas() {
		if (pnTablas == null) {
			pnTablas = new JPanel();
			pnTablas.setLayout(new GridLayout(1, 2, 50, 50));
			pnTablas.add(getPnTablasTodos());
		}
		return pnTablas;
	}
	private JPanel getPnTablasTodos() {
		if (pnTablasTodos == null) {
			pnTablasTodos = new JPanel();
			pnTablasTodos.setLayout(new GridLayout(0, 2, 0, 0));
			pnTablasTodos.add(getPnEnfermedadesTodas());
			pnTablasTodos.add(getPnVacunasTodas());
		}
		return pnTablasTodos;
	}
	private JPanel getPnVacunasTodas() {
		if (pnVacunasTodas == null) {
			pnVacunasTodas = new JPanel();
			pnVacunasTodas.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Todas las Vacunas del paciente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			GridBagLayout gbl_pnVacunasTodas = new GridBagLayout();
			gbl_pnVacunasTodas.columnWidths = new int[]{511, 0};
			gbl_pnVacunasTodas.rowHeights = new int[] {100, 180};
			gbl_pnVacunasTodas.columnWeights = new double[]{0.0, Double.MIN_VALUE};
			gbl_pnVacunasTodas.rowWeights = new double[]{0.0, 0.0};
			pnVacunasTodas.setLayout(gbl_pnVacunasTodas);
			GridBagConstraints gbc_pnFiltroVacuna = new GridBagConstraints();
			gbc_pnFiltroVacuna.fill = GridBagConstraints.BOTH;
			gbc_pnFiltroVacuna.insets = new Insets(0, 0, 5, 0);
			gbc_pnFiltroVacuna.gridx = 0;
			gbc_pnFiltroVacuna.gridy = 0;
			pnVacunasTodas.add(getPnFiltroVacuna(), gbc_pnFiltroVacuna);
			GridBagConstraints gbc_pnVacunaD = new GridBagConstraints();
			gbc_pnVacunaD.fill = GridBagConstraints.BOTH;
			gbc_pnVacunaD.gridx = 0;
			gbc_pnVacunaD.gridy = 1;
			pnVacunasTodas.add(getPnVacunaD(), gbc_pnVacunaD);
		}
		return pnVacunasTodas;
	}
	
	private DefaultListModel<String>  todaslasVacunas() {
		List<String> listasVacunas = DatabaseConsultorImpl.todoslasVacunas(this.paciente.getId_historial());
		for(String r:listasVacunas) {
		String[] result=r.split(",");
		String resultado=result[0]+" "+result[1]+" Medicos/s: "+result[2];
		int i=3;
		int length=result.length;
		while(i<length) {
			resultado+=" , "+result[i];
			i++;
		}
		listTodasLasVacunas.addElement(resultado);
		}
		return listTodasLasVacunas;
	}

	private JPanel getPnEnfermedadesTodas() {
		if (pnEnfermedadesTodas == null) {
			pnEnfermedadesTodas = new JPanel();
			pnEnfermedadesTodas.setBorder(new TitledBorder(null, "Todas las enfermedades del paciente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagLayout gbl_pnEnfermedadesTodas = new GridBagLayout();
			gbl_pnEnfermedadesTodas.columnWidths = new int[] {511, 0};
			gbl_pnEnfermedadesTodas.rowHeights = new int[] {100, 180};
			gbl_pnEnfermedadesTodas.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_pnEnfermedadesTodas.rowWeights = new double[]{0.0, 0.0};
			pnEnfermedadesTodas.setLayout(gbl_pnEnfermedadesTodas);
			GridBagConstraints gbc_pnFiltroEnfermedad = new GridBagConstraints();
			gbc_pnFiltroEnfermedad.fill = GridBagConstraints.BOTH;
			gbc_pnFiltroEnfermedad.insets = new Insets(0, 0, 5, 0);
			gbc_pnFiltroEnfermedad.gridx = 0;
			gbc_pnFiltroEnfermedad.gridy = 0;
			pnEnfermedadesTodas.add(getPnFiltroEnfermedad(), gbc_pnFiltroEnfermedad);
			GridBagConstraints gbc_pnEnfermedadD = new GridBagConstraints();
			gbc_pnEnfermedadD.insets = new Insets(0, 0, 5, 0);
			gbc_pnEnfermedadD.fill = GridBagConstraints.BOTH;
			gbc_pnEnfermedadD.gridx = 0;
			gbc_pnEnfermedadD.gridy = 1;
			pnEnfermedadesTodas.add(getPnEnfermedadD(), gbc_pnEnfermedadD);
		}
		return pnEnfermedadesTodas;
	}

	private ListModel<String> todaslasEnfermos() {
		List<String> listasEnfermedades = DatabaseConsultorImpl.todoslasEnfermedades(this.paciente.getId_historial());
		for(String r:listasEnfermedades) {
		String[] result=r.split(",");
		String resultado=result[0]+" "+result[1]+" Medico/s: "+result[2];
		int i=3;
		int length=result.length;
		while(i<length) {
			resultado+=" , "+result[i];
			i++;
		}
		listTodasLasEnfermedades.addElement(resultado);
		}

		return listTodasLasEnfermedades;
	}
	private JPanel getPnFiltroVacuna() {
		if (pnFiltroVacuna == null) {
			pnFiltroVacuna = new JPanel();
			pnFiltroVacuna.setBorder(new TitledBorder(null, "Filtro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagLayout gbl_pnFiltroVacuna = new GridBagLayout();
			gbl_pnFiltroVacuna.columnWidths = new int[] {511};
			gbl_pnFiltroVacuna.rowHeights = new int[] {50, 50, 0};
			gbl_pnFiltroVacuna.columnWeights = new double[]{0.0};
			gbl_pnFiltroVacuna.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			pnFiltroVacuna.setLayout(gbl_pnFiltroVacuna);
			GridBagConstraints gbc_pnNombreVacuna = new GridBagConstraints();
			gbc_pnNombreVacuna.fill = GridBagConstraints.BOTH;
			gbc_pnNombreVacuna.insets = new Insets(0, 0, 5, 0);
			gbc_pnNombreVacuna.gridx = 0;
			gbc_pnNombreVacuna.gridy = 0;
			pnFiltroVacuna.add(getPnNombreVacuna(), gbc_pnNombreVacuna);
			GridBagConstraints gbc_pnBtFiltroVacuna = new GridBagConstraints();
			gbc_pnBtFiltroVacuna.fill = GridBagConstraints.BOTH;
			gbc_pnBtFiltroVacuna.gridx = 0;
			gbc_pnBtFiltroVacuna.gridy = 1;
			pnFiltroVacuna.add(getPnBtFiltroVacuna(), gbc_pnBtFiltroVacuna);
		}
		return pnFiltroVacuna;
	}
	private JPanel getPnNombreVacuna() {
		if (pnNombreVacuna == null) {
			pnNombreVacuna = new JPanel();
			pnNombreVacuna.setLayout(new GridLayout(1, 0, 0, 0));
			pnNombreVacuna.add(getPnNombrelbl());
			pnNombreVacuna.add(getPnTxtNombre());
		}
		return pnNombreVacuna;
	}
	private JPanel getPnNombrelbl() {
		if (pnNombrelbl == null) {
			pnNombrelbl = new JPanel();
			pnNombrelbl.setLayout(new BorderLayout(0, 0));
			pnNombrelbl.add(getLblNombreVacuna(), BorderLayout.SOUTH);
		}
		return pnNombrelbl;
	}
	private JLabel getLblNombreVacuna() {
		if (lblNombreVacuna == null) {
			lblNombreVacuna = new JLabel("Nombre Vacuna");
			lblNombreVacuna.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblNombreVacuna;
	}
	private JPanel getPnTxtNombre() {
		if (pnTxtNombre == null) {
			pnTxtNombre = new JPanel();
			pnTxtNombre.setLayout(new BorderLayout(0, 0));
			pnTxtNombre.add(getTxtNombreVacuna(), BorderLayout.SOUTH);
		}
		return pnTxtNombre;
	}
	private JTextField getTxtNombreVacuna() {
		if (txtNombreVacuna == null) {
			txtNombreVacuna = new JTextField();
			txtNombreVacuna.setColumns(10);
		}
		return txtNombreVacuna;
	}
	private JPanel getPnVacunaD() {
		if (pnVacunaD == null) {
			pnVacunaD = new JPanel();
			pnVacunaD.setBorder(new TitledBorder(null, "Vacunas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagLayout gbl_pnVacunaD = new GridBagLayout();
			gbl_pnVacunaD.columnWidths = new int[]{511, 0};
			gbl_pnVacunaD.rowHeights = new int[] {153, 0};
			gbl_pnVacunaD.columnWeights = new double[]{0.0, Double.MIN_VALUE};
			gbl_pnVacunaD.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			pnVacunaD.setLayout(gbl_pnVacunaD);
			GridBagConstraints gbc_scrollPaneVacunasTodas = new GridBagConstraints();
			gbc_scrollPaneVacunasTodas.fill = GridBagConstraints.BOTH;
			gbc_scrollPaneVacunasTodas.gridx = 0;
			gbc_scrollPaneVacunasTodas.gridy = 0;
			pnVacunaD.add(getScrollPaneVacunasTodas(), gbc_scrollPaneVacunasTodas);
		}
		return pnVacunaD;
	}
	private JScrollPane getScrollPaneVacunasTodas() {
		if (scrollPaneVacunasTodas == null) {
			scrollPaneVacunasTodas = new JScrollPane();
			scrollPaneVacunasTodas.setViewportView(getListTodasVacunas());
		}
		return scrollPaneVacunasTodas;
	}
	private JPanel getPnBtFiltroVacuna() {
		if (pnBtFiltroVacuna == null) {
			pnBtFiltroVacuna = new JPanel();
			pnBtFiltroVacuna.setLayout(new BorderLayout(0, 0));
			pnBtFiltroVacuna.add(getBtFiltradoVacuna(), BorderLayout.SOUTH);
		}
		return pnBtFiltroVacuna;
	}
	private JButton getBtFiltradoVacuna() {
		if (btFiltradoVacuna == null) {
			btFiltradoVacuna = new JButton("Filtrado");
			btFiltradoVacuna.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					filtradoVacunas();
				}
			});
		}
		return btFiltradoVacuna;
	}
	protected void filtradoVacunas() {
		listTodasLasVacunas.clear();
		if(txtNombreVacuna.getText().equals("")) {
			listTodasVacunas.setModel(copiaTodasLasVacunas);
		}
		else {
           anadirVacuna(DatabaseConsultorImpl.buscarVacuna(this.paciente.getId_historial(),txtNombreVacuna.getText()));
		}
		
	}

	private void anadirVacuna(List<String> buscarVacuna) {
		for(String r:buscarVacuna) {
			String[] result=r.split(",");
			String resultado=result[0]+" "+result[1]+" "+result[2];
			int i=3;
			int length=result.length;
			while(i<length) {
				resultado+=" , "+result[i];
				i++;
			}
			listTodasLasVacunas.addElement(resultado);
			}
		listTodasVacunas.setModel(listTodasLasVacunas);
		
	}

	private JList<String> getListTodasVacunas() {
		if (listTodasVacunas == null) {
			listTodasVacunas = new JList<String>();
			listTodasVacunas.setBorder(new LineBorder(new Color(0, 0, 0)));
			listTodasVacunas.setModel(todaslasVacunas());
	        copiaVacunas();
		}
		return listTodasVacunas;
	}
	private void copiaVacunas() {
			for(int i=0;i<listTodasLasVacunas.size();i++) {
				copiaTodasLasVacunas.addElement(listTodasLasVacunas.get(i));
			}

		
	}

	private JPanel getPnFiltroEnfermedad() {
		if (pnFiltroEnfermedad == null) {
			pnFiltroEnfermedad = new JPanel();
			pnFiltroEnfermedad.setBorder(new TitledBorder(null, "Filtrado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagLayout gbl_pnFiltroEnfermedad = new GridBagLayout();
			gbl_pnFiltroEnfermedad.columnWidths = new int[] {511};
			gbl_pnFiltroEnfermedad.rowHeights = new int[] {50, 50, 0};
			gbl_pnFiltroEnfermedad.columnWeights = new double[]{1.0};
			gbl_pnFiltroEnfermedad.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
			pnFiltroEnfermedad.setLayout(gbl_pnFiltroEnfermedad);
			GridBagConstraints gbc_pnNombreEnfermedad = new GridBagConstraints();
			gbc_pnNombreEnfermedad.insets = new Insets(0, 0, 5, 0);
			gbc_pnNombreEnfermedad.fill = GridBagConstraints.BOTH;
			gbc_pnNombreEnfermedad.gridx = 0;
			gbc_pnNombreEnfermedad.gridy = 0;
			pnFiltroEnfermedad.add(getPnNombreEnfermedad(), gbc_pnNombreEnfermedad);
			GridBagConstraints gbc_pnBtFiltroEnfermedad = new GridBagConstraints();
			gbc_pnBtFiltroEnfermedad.fill = GridBagConstraints.BOTH;
			gbc_pnBtFiltroEnfermedad.gridx = 0;
			gbc_pnBtFiltroEnfermedad.gridy = 1;
			pnFiltroEnfermedad.add(getPnBtFiltroEnfermedad(), gbc_pnBtFiltroEnfermedad);
		}
		return pnFiltroEnfermedad;
	}
	private JPanel getPnNombreEnfermedad() {
		if (pnNombreEnfermedad == null) {
			pnNombreEnfermedad = new JPanel();
			pnNombreEnfermedad.setLayout(new GridLayout(1, 0, 0, 0));
			pnNombreEnfermedad.add(getPnNombrelblEnfermedad());
			pnNombreEnfermedad.add(getPnTxtNombreEnfermedad());
		}
		return pnNombreEnfermedad;
	}
	private JPanel getPnNombrelblEnfermedad() {
		if (pnNombrelblEnfermedad == null) {
			pnNombrelblEnfermedad = new JPanel();
			pnNombrelblEnfermedad.setLayout(new BorderLayout(0, 0));
			pnNombrelblEnfermedad.add(getLblNombreEnfermedad(), BorderLayout.SOUTH);
		}
		return pnNombrelblEnfermedad;
	}
	private JLabel getLblNombreEnfermedad() {
		if (lblNombreEnfermedad == null) {
			lblNombreEnfermedad = new JLabel("Nombre Enfermedad");
			lblNombreEnfermedad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblNombreEnfermedad;
	}
	private JPanel getPnTxtNombreEnfermedad() {
		if (pnTxtNombreEnfermedad == null) {
			pnTxtNombreEnfermedad = new JPanel();
			pnTxtNombreEnfermedad.setLayout(new BorderLayout(0, 0));
			pnTxtNombreEnfermedad.add(getTxtNombreEnfermedad(), BorderLayout.SOUTH);
		}
		return pnTxtNombreEnfermedad;
	}
	private JTextField getTxtNombreEnfermedad() {
		if (txtNombreEnfermedad == null) {
			txtNombreEnfermedad = new JTextField();
			txtNombreEnfermedad.setColumns(10);
		}
		return txtNombreEnfermedad;
	}
	private JPanel getPnBtFiltroEnfermedad() {
		if (pnBtFiltroEnfermedad == null) {
			pnBtFiltroEnfermedad = new JPanel();
			pnBtFiltroEnfermedad.setLayout(new BorderLayout(0, 0));
			pnBtFiltroEnfermedad.add(getBtFiltradoEnfermedad(), BorderLayout.SOUTH);
		}
		return pnBtFiltroEnfermedad;
	}
	private JButton getBtFiltradoEnfermedad() {
		if (btFiltradoEnfermedad == null) {
			btFiltradoEnfermedad = new JButton("Filtrado");
			btFiltradoEnfermedad.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					filtradoEnfermedad();
				}
			});
		}
		return btFiltradoEnfermedad;
	}
	protected void filtradoEnfermedad() {

		listTodasLasEnfermedades.clear();
		if(txtNombreEnfermedad.getText().equals("")) {
			listTodasEnfermedades.setModel(copiaTodasLasEnfermedades);
		}
		else {
           anadirEnfermedad(DatabaseConsultorImpl.buscarEnfermedad(this.paciente.getId_historial(),txtNombreEnfermedad.getText()));
		}
		
	}

	private void anadirEnfermedad(List<String> buscarVacuna) {
		for(String r:buscarVacuna) {
			String[] result=r.split(",");
			String resultado=result[0]+" "+result[1]+" "+result[2];
			int i=3;
			int length=result.length;
			while(i<length) {
				resultado+=" , "+result[i];
				i++;
			}
			listTodasLasEnfermedades.addElement(resultado);
		}
		listTodasEnfermedades.setModel(listTodasLasEnfermedades);
	}

	private JPanel getPnEnfermedadD() {
		if (pnEnfermedadD == null) {
			pnEnfermedadD = new JPanel();
			pnEnfermedadD.setBorder(new TitledBorder(null, "Enfermedad", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagLayout gbl_pnEnfermedadD = new GridBagLayout();
			gbl_pnEnfermedadD.columnWidths = new int[]{511, 0};
			gbl_pnEnfermedadD.rowHeights = new int[]{153, 0};
			gbl_pnEnfermedadD.columnWeights = new double[]{0.0, Double.MIN_VALUE};
			gbl_pnEnfermedadD.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			pnEnfermedadD.setLayout(gbl_pnEnfermedadD);
			GridBagConstraints gbc_scrollPaneETodasnfermedades = new GridBagConstraints();
			gbc_scrollPaneETodasnfermedades.fill = GridBagConstraints.BOTH;
			gbc_scrollPaneETodasnfermedades.gridx = 0;
			gbc_scrollPaneETodasnfermedades.gridy = 0;
			pnEnfermedadD.add(getScrollPaneETodasnfermedades(), gbc_scrollPaneETodasnfermedades);
		}
		return pnEnfermedadD;
	}
	private JScrollPane getScrollPaneETodasnfermedades() {
		if (scrollPaneETodasnfermedades == null) {
			scrollPaneETodasnfermedades = new JScrollPane();
			scrollPaneETodasnfermedades.setViewportView(getListTodasEnfermedades_1());
		}
		return scrollPaneETodasnfermedades;
	}
	private JList<String> getListTodasEnfermedades_1() {
		if (listTodasEnfermedades == null) {
			listTodasEnfermedades = new JList<String>();
			listTodasEnfermedades.setBorder(new LineBorder(new Color(0, 0, 0)));
			listTodasEnfermedades.setModel(todaslasEnfermos());
			copiaEnfermedades();
		}
		return listTodasEnfermedades;
	}

	private void copiaEnfermedades() {
		for(int i=0;i<listTodasLasEnfermedades.size();i++) {
			copiaTodasLasEnfermedades.addElement(listTodasLasEnfermedades.get(i));
		}
		
	}
}
