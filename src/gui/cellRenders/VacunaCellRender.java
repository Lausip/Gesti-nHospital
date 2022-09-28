package gui.cellRenders;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.border.MatteBorder;

import negocio.prescripcion.Vacuna;

public class VacunaCellRender extends JTextArea implements ListCellRenderer<Vacuna>{

	@Override
	public Component getListCellRendererComponent(JList<? extends Vacuna> arg0, Vacuna vacuna, int arg2, boolean arg3,
			boolean arg4) {
		
		if(vacuna!=null) {
			this.setText(vacuna.getTextToList());
			
		}
		
		this.setBorder(new MatteBorder(1, 0, 1, 0, Color.BLACK) );
		if (arg3)
        {
            setBackground(arg0.getSelectionBackground());
            setForeground(arg0.getSelectionForeground());
        }
        else
        {
        	
            setBackground(arg0.getBackground());
            setForeground(arg0.getForeground());
        }
		
		this.setFont(new Font("Serif", Font.BOLD, 17));
		
		
		return this;
	}


}
