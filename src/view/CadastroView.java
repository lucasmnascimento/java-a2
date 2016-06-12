package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.GregorianCalendar;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import model.vo.Aluno;

public class CadastroView {

	JTextField matricula;
	JTextField nome;
	JTextField mensalidade;
	JTextField dataAdm;
	LinkedHashSet<Aluno> lhs;
	
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
	    MaskFormatter ftmData; 
		try {
			ftmData = new MaskFormatter("##/##/####");
			ftmData.setValidCharacters("0123456789");
			ftmData.setPlaceholderCharacter('_');
			dataAdm = new JFormattedTextField(ftmData);		
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	    JButton botaoSalvar = new JButton ("Salvar");
	    
	    
	    panelCadastro.add (new JLabel ("Matrícula "));
	    matricula.setSize (2, 5);
	    panelCadastro.add (matricula);
	    
	    panelCadastro.add (new JLabel ("Nome "));
	    nome.setSize (2, 15);
	    panelCadastro.add (nome);
        
	    panelCadastro.add (new JLabel ("Mensalidade"));
	    mensalidade.setSize (2, 5);
	    panelCadastro.add (mensalidade);
	    
	    panelCadastro.add (new JLabel ("Data"));
	    dataAdm.setSize (2, 5);
	    panelCadastro.add (dataAdm);
	    
	    panelCadastro.add (botaoSalvar);
	    
	    frameCadastro.add (panelCadastro);
	    
	    botaoSalvar.addActionListener(
	    	new ActionListener() {
				@SuppressWarnings({ "rawtypes", "unchecked" })
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
							FileInputStream obj = new FileInputStream ("base.bas");
							ObjectInputStream lerObj = new ObjectInputStream(obj);
							lhs = (LinkedHashSet<Aluno>) lerObj.readObject();
							
							obj.close();
						} catch (IOException | ClassNotFoundException e1) {
							e1.printStackTrace();
							
						} 
						
						try {
							outputStream = new FileOutputStream ("base.bas");
							objectOutput = new ObjectOutputStream(outputStream);
                            lhs.add(aluno);
                            objectOutput.writeObject(lhs);
                            
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						JOptionPane.showMessageDialog(botaoSalvar, "Gravação efetuada com sucesso");
						
						frameCadastro.dispose();
						
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
