package br.com.kamaleon.frenteDeLoja.util;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import br.com.kamaleon.util.FuncoesString;
import br.com.kamaleon.util.ValidadorUniversal;


public class FuncoesSwingUtil
{	
	public static final int SIM = 0;
	public static final int NAO = 1;
	
	public static final String IMAGEM_LIXEIRA = "imagens" +  File.separator +  "bin.png"; 
	public static final String IMAGEM_CONFIRMAR = "imagens" +  File.separator +  "tick.png"; 
	public static final String IMAGEM_CANCELAR = "imagens" +  File.separator +  "cross.png"; 
	public static final String IMAGEM_CALENDARIO = "imagens" +  File.separator +  "date.png"; 
	public static final String IMAGEM_NFE = "imagens" +  File.separator +  "nfe.png"; 
	public static final String IMAGEM_IMPRESSORA = "imagens" +  File.separator +  "printer.png"; 
	public static final String IMAGEM_LOGIN = "imagens" +  File.separator +  "login.png"; 
	public static final String IMAGEM_FILTRAR = "imagens" +  File.separator +  "zoom.png"; 
	public static final String IMAGEM_ORCAMENTO = "imagens" +  File.separator +  "orcamento.png"; 
	public static final String IMAGEM_KAMALEON = "imagens" +  File.separator +  "kamaleon_pequeno.png";
	public static final String IMAGEM_ADICIONAR = "imagens" +  File.separator +  "add.png";
	public static final String IMAGEM_AJUDA = "imagens" +  File.separator +  "help.png";
	public static final String IMAGEM_CHEQUE = "imagens" +  File.separator +  "cheque.png";
	public static final String IMAGEM_CODIGODEBARRA = "imagens" +  File.separator +  "etiqueta.png";
	public static final String IMAGEM_CARTA = "imagens" +  File.separator +  "letter.png";
	public static final String IMAGEM_LOGO= "imagens" +  File.separator +  "logoFL.png";
	
	public static final String ATENCAO = "Aten\u00e7\u00e3o";
	public static final String NAO_CODIFICADO = "n\u00e3o";
	
	public static boolean isNumero(char caracter)
	{
		if ((caracter >= '0') && (caracter <= '9'))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public static boolean isEnter(char character)
	{
		return ((character == KeyEvent.VK_ENTER));
	}
	
	public static boolean isTAB(char character)
	{
		return ((character == KeyEvent.VK_TAB));
	}

	public static boolean isESC(char character)
	{
		return ((character == KeyEvent.VK_ESCAPE));
	}

	public static void mostrarMensagem(Component frame, String titulo, String mensagem, int tipo)
	{
        JOptionPane.showMessageDialog(frame, mensagem, titulo, tipo);        
	}

	public static void mostrarMensagem(Component frame, String titulo, String mensagem)
	{
		mostrarMensagem(frame, titulo, mensagem, JOptionPane.INFORMATION_MESSAGE);
	}
	public static void mostrarMensagemAtencao(Component frame, String mensagem)
	{
		mostrarMensagem(frame, ATENCAO, mensagem, JOptionPane.INFORMATION_MESSAGE);
	}
	public static void mostrarMensagemSucesso(Component frame, String mensagem)
	{
		mostrarMensagem(frame, "Sucesso", mensagem, JOptionPane.INFORMATION_MESSAGE);
	}
	public static void mostrarMensagemComIcone(Component frame, String titulo, String mensagem, Icon icone)
	{
		JOptionPane.showMessageDialog(frame, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE, icone);
	}

	public static void mostrarMensagemErro(Component frame, String titulo, String mensagem)
	{
		mostrarMensagem(frame, titulo, mensagem, JOptionPane.ERROR_MESSAGE);
	}

	public static int mostrarMensagemSimNao(Component frame, String titulo, String mensagem)
	{
		UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
		return JOptionPane.showConfirmDialog(frame, mensagem, titulo, JOptionPane.YES_NO_OPTION);
	}
	
	public static int getMensagemInt(Component frame, String titulo, String mensagem)
	{
		String retorno = JOptionPane.showInputDialog(frame, mensagem, titulo, JOptionPane.QUESTION_MESSAGE);
		
		if(ValidadorUniversal.isIntegerPositivo(retorno.trim()))
		{
			return Integer.parseInt(retorno.trim());
		}
		else
		{
			FuncoesSwingUtil.mostrarMensagemErro(frame, titulo, "Numero n\u00e3o \u00e9 um inteiro.\nTente novamente!");
			return 0;
		}
	}

	public static void esperarJanela(JFrame janela)
	{
		while(janela.isVisible()){}
	}
	
	public static void esperarJanela(JDialog janela)
	{
		while(janela.isVisible()){}
	}
	
	
}
