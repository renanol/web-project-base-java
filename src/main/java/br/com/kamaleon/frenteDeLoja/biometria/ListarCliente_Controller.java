/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kamaleon.frenteDeLoja.biometria;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.table.DefaultTableModel;

import com.google.gson.JsonSyntaxException;

import br.com.kamaleon.frenteDeLoja.ControllerSwing;
import br.com.kamaleon.frenteDeLoja.Fachada;
import br.com.kamaleon.frenteDeLoja.util.CenterAlignmentCellRenderer;
import br.com.kamaleon.frenteDeLoja.util.FuncoesSwingUtil;
import br.com.kamaleon.frenteDeLoja.util.ImagemCaneta;
import br.com.kamaleon.frenteDeLoja.util.ImagemLupa;
import br.com.kamaleon.frenteDeLoja.util.LeftAlignmentCellRenderer;
import br.com.kamaleon.util.ValidadorUniversal;

/**
 *
 * @author Vinicius
 */
public class ListarCliente_Controller extends ControllerSwing {

    ListarCliente_FL tela = null;
    String[] status = new String[]{"","A", "B", "I", "P" , "C"};
    List<Object[]> lista = new ArrayList();
    
    public ListarCliente_Controller(ListarCliente_FL tela) {
        this.tela = tela;
        preencherTabela();
    }
    
    protected void listarCliente() throws JsonSyntaxException, IOException
    {
    	if(!ValidadorUniversal.check(tela.textNome.getText()) && !ValidadorUniversal.check(tela.textCpfCnpj.getText()))
    	{
    		FuncoesSwingUtil.mostrarMensagemErro(tela, "Erro", "Informe pelo menos uma parte do nome ou CPF/CNPJ!");
    		tela.textNome.requestFocus();
    		return;
    		
    	}
    	
    	lista = Fachada.listarCliente(tela.textNome.getText(), tela.textCpfCnpj.getText(), status[tela.comboStatus.getSelectedIndex()], tela.comboTipo.getSelectedIndex());
    	
    	preencherTabela();
    }
    
    private void preencherTabela()
	{
		int quantidadeItens = lista.size();
		
		Object dados[][] = new Object[quantidadeItens][5];
		String colunas[] = new String[]{
				"Código","Cliente", "Cpf/Cnpj", "Visualizar", "Alterar"
		};
		

		for(int i = 0 ; i < quantidadeItens; i++)
		{
			dados[i][0] = lista.get(i)[0];
			dados[i][1] = lista.get(i)[1];
			dados[i][2] = lista.get(i)[2];
		}
		
		DefaultTableModel modelo = new DefaultTableModel(dados, colunas){
            boolean[] canEdit = new boolean [] {
                    false, false, false, true, true
                };
            Class[] types = new Class [] {
                    Integer.class, String.class, String.class, Boolean.class, Boolean.class
                };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
            
            @Override
            public void setValueAt(Object aValue, int row, int column) {
            	// TODO Auto-generated method stub
            	super.setValueAt(aValue, row, column);
            	if(column == 3)
            	{
            		
            	}
            	if(column == 4)
            	{
            		
            	}
            }
                
        };	 
            
        tela.tableClientes.setModel(modelo);	        
        tela.tableClientes.getColumnModel().getColumn(0).setPreferredWidth(100);
        tela.tableClientes.getColumnModel().getColumn(0).setCellRenderer(new LeftAlignmentCellRenderer());
        tela.tableClientes.getColumnModel().getColumn(1).setPreferredWidth(330);	        
        tela.tableClientes.getColumnModel().getColumn(1).setCellRenderer(new LeftAlignmentCellRenderer());
        tela.tableClientes.getColumnModel().getColumn(2).setPreferredWidth(150);	        
        tela.tableClientes.getColumnModel().getColumn(2).setCellRenderer(new CenterAlignmentCellRenderer());
        tela.tableClientes.getColumnModel().getColumn(3).setPreferredWidth(95);	        
        tela.tableClientes.getColumnModel().getColumn(3).setCellRenderer(new ImagemLupa());
        tela.tableClientes.getColumnModel().getColumn(4).setPreferredWidth(95);
        tela.tableClientes.getColumnModel().getColumn(4).setCellRenderer(new ImagemCaneta());
        
	}
}
