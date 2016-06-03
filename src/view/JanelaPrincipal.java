package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

import com.sun.glass.ui.MenuBar;

@SuppressWarnings("serial")
public class JanelaPrincipal extends JFrame {

	public JanelaPrincipal() {
		
		super ("TRABALHO JAVA A2");
		
		JPanel panel = new JPanel();
		panel.setLayout (new BorderLayout());
	    
		JMenuBar menuMestre = new JMenuBar();
		
		JMenu menuCadastro = new JMenu("Cadastro");
		
		menuMestre.add(menuCadastro);
		
		setJMenuBar(menuMestre);
		
		JMenuItem subMenuCadastro = new JMenuItem("Cadastro");
		JMenuItem subMenuPesquisa = new JMenuItem("Pesquisa");
		
		menuCadastro.add(subMenuCadastro);
		menuCadastro.add(subMenuPesquisa);
		
		
		// ------------ EVENTOS DO MENU ----------------
		subMenuCadastro.addActionListener (new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				System.out.println ("teste de evento - Cadastro");
				JFrame frameCadastro = new JFrame ("Cadastro");
				
				JPanel panelCadastro = new JPanel();
				frameCadastro.setLayout (new GridLayout (3, 2));
				
			    JTextField textFieldNome = new JTextField (15);
			    JTextField textFieldSobrenome = new JTextField (15);
			    
			    panelCadastro.add (new JLabel ("Nome: "));
			    panelCadastro.add (textFieldNome);
		        
			    panelCadastro.add (new JLabel ("Sobrenome"));
			    panelCadastro.add (textFieldSobrenome);
			    
			    frameCadastro.add(panelCadastro);
			    
			    frameCadastro.pack();
			    frameCadastro.setLocationRelativeTo (null);
				
			    frameCadastro.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
			    frameCadastro.setVisible (true);
			}
		});

		subMenuPesquisa.addActionListener (new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				System.out.println("teste de evento - Pesquisa");
			}
		});

        pack();
		setLocationRelativeTo (null);
		
		setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
		setVisible (true);
	}
	
	public static void main (String args[]) {
		new JanelaPrincipal();
	}
}