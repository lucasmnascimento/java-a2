package view;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class JanelaPrincipal extends JFrame {

	public JanelaPrincipal() {
		
		super ("TRABALHO JAVA A2");
		
		JPanel panel1 = new JPanel();
		panel1.setLayout (new GridLayout (3, 2));
	    
	    JTextField textFieldNome = new JTextField (15);
	    JTextField textFieldSobrenome = new JTextField (15);
		
	    panel1.add (new JLabel ("Nome: "));
        panel1.add (textFieldNome);
        
        panel1.add (new JLabel ("Sobrenome"));
        panel1.add (textFieldSobrenome);
        
        panel1.add (new JButton("Salvar"));
        
        add (panel1);
        //add(panel2);

        pack();
		setLocationRelativeTo (null);
		
		setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
		setVisible (true);
	}
	
	public static void main (String args[]) {
		new JanelaPrincipal();
	}
}