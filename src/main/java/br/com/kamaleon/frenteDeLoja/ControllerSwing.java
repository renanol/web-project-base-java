/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kamaleon.frenteDeLoja;

import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import br.com.kamaleon.model.User;

/**
 *
 * @author Vinicius
 */
public abstract class ControllerSwing {
    
    private static User user ;
    
    public static User getUser() {
		return user;
	}
    
    public static void setUser(User user) {
		ControllerSwing.user = user;
	}
    
    public void registraEnterNoBotao(JButton b) {
        b.registerKeyboardAction(
                b.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                JComponent.WHEN_FOCUSED);
        
        b.registerKeyboardAction(
                b.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                JComponent.WHEN_FOCUSED);
    }
}
