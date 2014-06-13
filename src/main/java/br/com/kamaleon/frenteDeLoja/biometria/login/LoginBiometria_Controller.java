/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kamaleon.frenteDeLoja.biometria.login;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import br.com.kamaleon.frenteDeLoja.ControllerSwing;
import br.com.kamaleon.frenteDeLoja.Fachada;
import br.com.kamaleon.frenteDeLoja.biometria.ListarCliente_FL;
import br.com.kamaleon.frenteDeLoja.util.FuncoesSwingUtil;
import br.com.kamaleon.model.User;
import br.com.kamaleon.util.Crypto;
import br.com.kamaleon.util.DES;
import br.com.kamaleon.util.ValidadorUniversal;

/**
 *
 * @author Vinicius
 */
public class LoginBiometria_Controller extends ControllerSwing {

    LoginBiometria_FL tela = null;
    
    public LoginBiometria_Controller(LoginBiometria_FL tela) {
        this.tela = tela;
        tela.labelVerificacao.setText("VERIFICA\u00C7\u00c3O");
        tela.labelBiometrica.setText("BIOM\u00c9TRICA");
    }
    
    protected void login() throws Exception
    {
    	if(!ValidadorUniversal.check(tela.textLogin.getText()))
    	{
    		FuncoesSwingUtil.mostrarMensagemErro(tela, "Erro", "Login n\u00e3o preenchido!");
    		tela.textLogin.requestFocus();
    		return;
    	}
    	if(!ValidadorUniversal.check(tela.textSenha.getText()))
    	{
    		FuncoesSwingUtil.mostrarMensagemErro(tela, "Erro", "Senha n\u00e3o preenchida!");
    		tela.textSenha.requestFocus();
    		return;
    	}
    	
    	String login = tela.textLogin.getText();
    	String senha = tela.textSenha.getText();
    	
    	User usuario = Fachada.login(login, senha);
    	
    	if(usuario != null)
    	{
    		setUser(usuario);
    	}
    	else
    	{
    		FuncoesSwingUtil.mostrarMensagemErro(tela, "Erro", "Login/Senha inv\u00e3lidos!");
    		tela.textLogin.requestFocus();
    		return;
    	}
    	
    	tela.dispose();
    	
    	ListarCliente_FL listarCliente = new ListarCliente_FL();
    	listarCliente.setVisible(true);
    	
    }
}
