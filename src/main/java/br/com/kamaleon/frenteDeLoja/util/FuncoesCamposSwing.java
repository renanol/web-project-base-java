package br.com.kamaleon.frenteDeLoja.util;
 
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.com.kamaleon.util.Cnpj;
import br.com.kamaleon.util.Cpf;
import br.com.kamaleon.util.FuncoesData;
import br.com.kamaleon.util.FuncoesString;
import br.com.kamaleon.util.ValidadorUniversal;

/**
 *
 * @author vinicius
 */
public class FuncoesCamposSwing {
    
	
	/**
	 * Formata o valor digitado na jtextfield
	 * @param e
	 */
	public static void formatarFloat(KeyEvent e) {
		JTextField text = (JTextField)e.getSource();
		String data = text.getText();

		if (data.length() > 0)
		{
			if (FuncoesSwingUtil.isNumero(data.charAt(data.length() - 1)))
			{
				data = FuncoesString.replace(data, ".", "");
				data = FuncoesString.replace(data, ",", "");
				if ((data.length() > 2) && (data.length() <= 5))
				{
					data = data.substring(0, data.length() - 2) + "," + data.substring(data.length() - 2, data.length());
				}
				else if ((data.length() >= 6) && (data.length() <= 8))
				{
					data = data.substring(0, data.length() - 5) + "." + data.substring(data.length() - 5, data.length() - 2) + "," + data.substring(data.length() - 2, data.length());
				}
				else if ((data.length() >= 9) && (data.length() <= 11))
				{
					data = data.substring(0, data.length() - 8) + "." + data.substring(data.length() - 8, data.length() - 5) + "." + data.substring(data.length() - 5, data.length() - 2) + "," + data.substring(data.length() - 2, data.length());
				}
			}
			else if ((e.getKeyCode() != KeyEvent.VK_BACK_SPACE) && !(e.getKeyCode() == KeyEvent.VK_ENTER))
			{
				data = data.substring(0, data.length() - 1);
			}
		}

		// para visualizar no visualEditor, comentar as duas linhas abaixo
		text.setText(data);
		text.setCaretPosition(data.length());
	}
    
	/**
	 * Formata a data no jtextfield
	 * @param e
	 */
	public static void formatarData(KeyEvent e)
	{
		JTextField text = (JTextField)e.getSource();
		String data = text.getText();
		
		if(data.length() > 10)
        {
        	data = data.substring(0, 10);
        }
		
		if (data.length() > 0)
		{
			if (FuncoesSwingUtil.isNumero(data.charAt(data.length() - 1)))
			{
				data = FuncoesString.replace(data, "/", "");
				if ((data.length() > 2) && (data.length() < 5))
				{
					data = data.substring(0, 2) + "/" + data.substring(2, data.length());
				}
				else if ((data.length() >= 5) && (data.length() <= 10))
				{
					data = data.substring(0, 2) + "/" + data.substring(2, 4) + "/" + data.substring(4, data.length());
				}
				else if (data.length() > 10)
				{
					data = data.substring(0, 2) + "/" + data.substring(2, 4) + "/" + data.substring(4, 10);
				}
			}
			else if ((e.getKeyCode() != KeyEvent.VK_BACK_SPACE) && !(e.getKeyCode() == KeyEvent.VK_ENTER))
			{
				data = data.substring(0, data.length() - 1);
			}
		}
		
		if(data.length() == 10)
		{
			if(!ValidadorUniversal.isDate(data, FuncoesData.sdfData))
			{
				data = "";
			}
		}

		// se for editar pelo visual editor comente essas duas linhas abaixo
		text.setText(data);
		text.setCaretPosition(data.length());
	}
	
	/**
	 * Formata o campo CPF
	 * @param e
	 */
	public static void formatarCpf(KeyEvent e)
	{
		JTextField text = (JTextField)e.getSource();
		String data = text.getText();

		if (data.length() > 0)
		{
			if (FuncoesSwingUtil.isNumero(data.charAt(data.length() - 1)))
			{
				data = FuncoesString.replace(data, "-", "");
				data = FuncoesString.replace(data, ".", "");
				if ((data.length() > 3) && (data.length() < 7))
				{
					data = data.substring(0, 3) + "." + data.substring(3, data.length());
				}
				else if ((data.length() >= 7) && (data.length() < 10))
				{
					data = data.substring(0, 3) + "." + data.substring(3, 6) + "." + data.substring(6, data.length());
				}
				else if ((data.length() >= 10) && (data.length() <= 11))
				{
					data = data.substring(0, 3) + "." + data.substring(3, 6) + "." + data.substring(6, 9) + "-" + data.substring(9, data.length());
				}
				else if (data.length() > 11)
				{
					data = data.substring(0, 11);
					data = (new Cpf(data)).getCpf();
				}
					
			}
			else if ((e.getKeyCode() != KeyEvent.VK_BACK_SPACE) && !(e.getKeyCode() == KeyEvent.VK_ENTER))
			{
				data = data.substring(0, data.length() - 1);
			}
		}

		// se for editar pelo visual editor comente essas duas linhas abaixo
		text.setText(data);
		text.setCaretPosition(data.length());
	}
	
	/**
	 * Formata o campo CNPJ
	 * @param e
	 */
	public static void formatarCnpj(KeyEvent e) {
		JTextField text = (JTextField)e.getSource();
		String data = text.getText();

		if (data.length() > 0)
		{
			if (FuncoesSwingUtil.isNumero(data.charAt(data.length() - 1)))
			{
				data = FuncoesString.replace(data, "-", "");
				data = FuncoesString.replace(data, ".", "");
				data = FuncoesString.replace(data, "/", "");
				if ((data.length() > 2) && (data.length() < 6))
				{
					data = data.substring(0, 2) + "." + data.substring(2, data.length());
				}
				else if ((data.length() >= 6) && (data.length() < 9))
				{
					data = data.substring(0, 2) + "." + data.substring(2, 5) + "." + data.substring(5, data.length());
				}
				else if ((data.length() >= 9) && (data.length() < 13))
				{
					data = data.substring(0, 2) + "." + data.substring(2, 5) + "." + data.substring(5, 8) + "/" + data.substring(8, data.length());
				}
				else if ((data.length() >= 13) && (data.length() <= 14))
				{
					data = data.substring(0, 2) + "." + data.substring(2, 5) + "." + data.substring(5, 8) + "/" + data.substring(8, 12) + "-" + data.substring(12, data.length());
				}
				else if (data.length() > 14)
				{
					data = data.substring(0, 14);
					data = new Cnpj(data).getCnpj();
				}
			}
			else if ((e.getKeyCode() != KeyEvent.VK_BACK_SPACE) && !(e.getKeyCode() == KeyEvent.VK_ENTER))
			{
				data = data.substring(0, data.length() - 1);
			}
		}
		// se for editar pelo visual editor comente essas duas linhas abaixo
		text.setText(data);
		text.setCaretPosition(data.length());
		
	}
	
	public static MaskFormatter getMascaraData(){
        
        MaskFormatter data = null;
        
        try {
            
            data = new MaskFormatter("##/##/####");
        } 
        catch (java.text.ParseException e) 
        {
        	e.printStackTrace();
        }
        
        return data;
    }
	
	public static MaskFormatter getMascaraCpf(){
        
        MaskFormatter data = null;
        
        try {
            
            data = new MaskFormatter("###.###.###-##");
        } 
        catch (java.text.ParseException e) 
        {
        	e.printStackTrace();
        }
        
        return data;
    }
	
	public static MaskFormatter getMascaraCnpj(){
        
        MaskFormatter data = null;
        
        try {
            
            data = new MaskFormatter("##.###.###/####-##");
        } 
        catch (java.text.ParseException e) 
        {
        	e.printStackTrace();
        }
        
        return data;
    }
	
}
