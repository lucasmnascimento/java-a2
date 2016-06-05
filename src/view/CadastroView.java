package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.vo.Aluno;

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
	    
	    
	    panelCadastro.add (new JLabel ("Matr�cula "));
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
						
						String dataInformada = dataAdm.getText();
						
						int dia = Integer.parseInt(dataInformada.substring (0,2));
						int mes = Integer.parseInt(dataInformada.substring (3,5));
						int ano = Integer.parseInt(dataInformada.substring (6));
						
						GregorianCalendar dataConvertida = new GregorianCalendar (ano, mes, dia);
						
						double mensalidadeConvertida = Double.parseDouble(mensalidade.getText());
						
						Aluno aluno = new Aluno(Integer.parseInt(matricula.getText()), nome.getText(), mensalidadeConvertida, dataConvertida);
						
						OutputStream outputStream = null;
						ObjectOutputStream objectOutput = null;
						try {
							outputStream = new FileOutputStream ("base.bas");
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
						
						try {
							objectOutput = new ObjectOutputStream(outputStream);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						try {
							objectOutput.writeObject(aluno);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						JOptionPane.showMessageDialog(botaoSalvar, "Grava��o efetuada com sucesso");
						
						frameCadastro.dispose();
						
					} else {
						JOptionPane.showMessageDialog(botaoSalvar, "Todos os campos s�o obrigat�rios");
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
