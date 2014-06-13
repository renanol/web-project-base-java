package br.com.kamaleon.frenteDeLoja.util;

import javax.swing.table.DefaultTableCellRenderer;

public class RightAlignmentCellRenderer extends DefaultTableCellRenderer {

	@Override
	protected void setValue(Object value) {
		
		setHorizontalAlignment(RIGHT);
		if(value instanceof CellType)
		{
			CellType celula = (CellType) value;
			setBackground(celula.getCor());
			
			if(celula.getFonte() != null)
			{
				setFont(celula.getFonte());
			}
			
		    super.setValue(celula.getDado());
		}
		else
		{
			super.setValue(value);
		}
	}
	
}

