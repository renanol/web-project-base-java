package br.com.kamaleon.frenteDeLoja.util;

import java.awt.Component;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ImagemDelete extends JLabel implements TableCellRenderer{  
	  
	public ImagemDelete(){  
	    setOpaque(true);  
	  }  
	    
	  public Component getTableCellRendererComponent(JTable table,   
	     Object value, boolean isSelected, boolean hasFocus, int row,   
	     int column){  
		  setHorizontalAlignment(CENTER);
	     Icon imagem = new ImageIcon(File.separator + "kash" + File.separator + "imagens"+ File.separator +"delete.png");  
	      
	     if(isSelected)  
	       setBackground(table.getSelectionBackground());  
	     else  
	       setBackground(table.getBackground());          
	       
	     setIcon(imagem);  

	     return this;         
	  }  
	    
	  public void validate() {}  
	  public void revalidate() {}  
	  protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {}  
	  public void firePropertyChange(String propertyName, boolean oldValue, boolean newValue) {}    
	}