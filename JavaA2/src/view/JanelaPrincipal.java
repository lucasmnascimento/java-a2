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
                panel.setLayout(new BorderLayout());
                             	    
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
				new CadastroView();
			}
		});

		subMenuPesquisa.addActionListener (new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				System.out.println("teste de evento - Pesquisa");
                                new PesquisaView();
			}
		});
               
        pack();
		setLocationRelativeTo (null); // Inicia a Janela no centro da tela 
               // setSize(400, 200); // Ajusta o tamanho da janela (largura x altura) em pixel 
                addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent e) {
			    int confirma = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja sair do programa?", "Sair", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
			    if (confirma == JOptionPane.YES_OPTION) {
			      dispose();
			    } else {
                             setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                            }	
			  }
			});
        	setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
		setVisible (true);
	}
	
	public static void main (String args[]) {
		new JanelaPrincipal();
	}
}