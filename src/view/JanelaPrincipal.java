package view;

import java.awt.event.*;
import javax.swing.*;

public class JanelaPrincipal extends JFrame {

	public JanelaPrincipal() {
		
		super ("ReNATO bOY");
		setSize (600,600);
		
		//cria barra de menu
		JMenuBar barra = new JMenuBar();
		super.setJMenuBar (barra);
		
		//cria um menu
		JMenu menuObjeto = new JMenu ("Cadastro");
		barra.add (menuObjeto);
		
		JMenuItem criar = new JMenuItem ("Inscrição");
		JMenuItem alterar = new JMenuItem ("Alterar");
		JMenuItem carregar = new JMenuItem ("Listar");
		JMenuItem excluir = new JMenuItem ("Excluir");
		JMenuItem sair = new JMenuItem ("Sair");
		
		menuObjeto.add (criar);
		menuObjeto.add (alterar);
		menuObjeto.add (carregar);
		menuObjeto.add (excluir);
		menuObjeto.addSeparator();
		menuObjeto.add (sair);
		
		//nota: adicionar maneira de focar a janela
		criar.addActionListener (new ActionListener() {
			public void actionPerformed (ActionEvent e) {
					DialogCria dialog = new DialogCria();
			}
		});
		
		sair.addActionListener (new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				int confirma = JOptionPane.showConfirmDialog (null, "Você tem certeza que deseja sair do programa?", "Sair", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);

				if (confirma == JOptionPane.YES_OPTION)
					dispose();
			}
		});
		
		//bug ao sair da janela, cancelar também fecha...
		addWindowListener (new WindowAdapter() {
			public void windowClosing (WindowEvent e) {
				int confirma = JOptionPane.showConfirmDialog (null,	"Você tem certeza que deseja sair do programa?", "Sair", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);

				if (confirma == JOptionPane.YES_OPTION)
					dispose();
			}
		});
		
		super.setDefaultCloseOperation (EXIT_ON_CLOSE);
		
		//setVisible é colocado aqui porque antes do menu, o mesmo não fica visível até redimensionarmos a janela
		setVisible (true);
	}
	
	public static void main (String args[]) {
		JanelaPrincipal jt = new JanelaPrincipal();
	}
}