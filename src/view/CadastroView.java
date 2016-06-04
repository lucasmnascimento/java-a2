package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CadastroView {

	JTextField matricula;
	JTextField nome;
	JTextField mensalidade;
	JTextField dataAdm;
	
	public CadastroView() {
		init();
	}
	
	protected void init() {
		JFrame frameCadastro = new JFrame ("Cadastro");
		
		JPanel panelCadastro = new JPanel();
		frameCadastro.setLayout (new GridLayout (3, 3));
		
		matricula = new JTextField (5);
	    nome = new JTextField (15);
	    mensalidade = new JTextField (5);
	    dataAdm = new JTextField (10);
	    
	    JButton botaoSalvar = new JButton ("Salvar");
	    
	    
	    panelCadastro.add (new JLabel ("Matrícula "));
	    panelCadastro.add (matricula);
	    
	    panelCadastro.add (new JLabel ("Nome "));
	    panelCadastro.add (nome);
        
	    panelCadastro.add (new JLabel ("Mensalidade"));
	    panelCadastro.add (mensalidade);
	    
	    panelCadastro.add (new JLabel ("Data"));
	    panelCadastro.add (dataAdm);
	    
	    panelCadastro.add (botaoSalvar);
	    
	    frameCadastro.add (panelCadastro);
	    
	    botaoSalvar.addActionListener(
	    	new ActionListener() {
				@Override
				public void actionPerformed (ActionEvent e) {
					if (matricula.getText() != null && !matricula.getText().equals ("") &&
							nome.getText() != null && !nome.getText().equals ("") &&
							mensalidade.getText() != null && !mensalidade.getText().equals ("") && 
							dataAdm.getText() != null && !dataAdm.getText().equals ("")) {
						System.out.println("teste");
					} else {
						JOptionPane.showMessageDialog(botaoSalvar, "Todos os campos são obrigatórios");
					}
				}
	    	}
	    );
	    
	    frameCadastro.pack();
	    frameCadastro.setLocationRelativeTo (null);
		
	    frameCadastro.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
	    frameCadastro.setVisible (true);
	}
}
