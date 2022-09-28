package gui.cellRenders;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.border.MatteBorder;

import negocio.citas.Cita;
import negocio.paciente.Paciente;

public class PacienteCellRender extends JTextArea implements ListCellRenderer<Paciente> {

	@Override
	public Component getListCellRendererComponent(JList<? extends Paciente> arg0, Paciente paciente, int arg2, boolean arg3,
			boolean arg4) {
		if (paciente != null) {

			String citaText = paciente.toStringParaJList();
			setText(citaText);

		}
		this.setBorder(new MatteBorder(1, 0, 1, 0, Color.BLACK));
		if (arg3) {
			setBackground(arg0.getSelectionBackground());
			setForeground(arg0.getSelectionForeground());
		} else {

			setBackground(arg0.getBackground());
			setForeground(arg0.getForeground());
		}

		this.setFont(new Font("Serif", Font.BOLD, 17));

		return this;
	}

}
