package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.GregorianCalendar;
import java.util.LinkedHashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.vo.Aluno;

public class PesquisaView {

	JTextField matricula;
	JTextField nome;
	JTextField mensalidade;
	JTextField dataAdm;
	
	public PesquisaView() {
		init();
	}
	
	protected void init() {
		JFrame framePesquisa = new JFrame ("Pesquisa");
		
		JPanel panelPesquisa = new JPanel();
		framePesquisa.setLayout (new GridLayout (3, 3));
		
		matricula = new JTextField (5);
	    nome = new JTextField (15);
	    mensalidade = new JTextField (5);
	    dataAdm = new JTextField (10);
	    
	    JButton botaoPesquisar = new JButton ("Pesquisar");
	    
	    
	    panelPesquisa.add (new JLabel ("Matrícula "));
	    panelPesquisa.add (matricula);
	    
	    panelPesquisa.add (new JLabel ("Nome "));
	    panelPesquisa.add (nome);
        
	    panelPesquisa.add (new JLabel ("Mensalidade"));
	    panelPesquisa.add (mensalidade);
	    
	    panelPesquisa.add (new JLabel ("Data"));
	    panelPesquisa.add (dataAdm);
	    
	    panelPesquisa.add (botaoPesquisar);
	    
	    framePesquisa.add (panelPesquisa);
	    
	    botaoPesquisar.addActionListener(
	    	new ActionListener() {
				@Override
				public void actionPerformed (ActionEvent e) {
                                    FileInputStream obj = null;
                                    try {
                                        String dataInformada = dataAdm.getText();
                                        String nomeInformado = nome.getText();
                                        int dia = Integer.parseInt(dataInformada.substring (0,2));
                                        int mes = Integer.parseInt(dataInformada.substring (3,5));
                                        int ano = Integer.parseInt(dataInformada.substring (6));
                                        GregorianCalendar dataConvertida = new GregorianCalendar (ano, mes, dia);
                                        double mensalidadeConvertida = Double.parseDouble(mensalidade.getText());
                                        
                                        Aluno aluno = new Aluno(Integer.parseInt(matricula.getText()), nome.getText(), mensalidadeConvertida, dataConvertida);
                                        
                                        obj = new FileInputStream ("base.bas");
                                        ObjectInputStream lerObj = new ObjectInputStream(obj);
                                        
                                        LinkedHashSet<Aluno> lhs = (LinkedHashSet<Aluno>) lerObj.readObject();
                                        
                                        
                                        for (Aluno alu : lhs){
                                            System.out.println(aluno.toString());
                                        }
                                        
                                        
                                        
                                        
                                        framePesquisa.dispose();
                                        
                                        
                                        
                                    } catch (FileNotFoundException ex) {
                                        Logger.getLogger(PesquisaView.class.getName()).log(Level.SEVERE, null, ex);
                                        System.out.println("Arquivo não encontrado!");
                                    } catch (IOException ex) {
                                        Logger.getLogger(PesquisaView.class.getName()).log(Level.SEVERE, null, ex);
                                        System.out.println("Erro de IO!");
                                    } catch (ClassNotFoundException ex) {
                                        Logger.getLogger(PesquisaView.class.getName()).log(Level.SEVERE, null, ex);
                                    } finally {
                                        try {
                                            obj.close();
                                        } catch (IOException ex) {
                                            Logger.getLogger(PesquisaView.class.getName()).log(Level.SEVERE, null, ex);
                                            System.out.println("Erro de IO!");
                                        }
                                    }
						
			
					}
	    	}
	    );
	    
	    framePesquisa.pack();
	    framePesquisa.setLocationRelativeTo (null);
		
	    framePesquisa.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
	    framePesquisa.setVisible (true);
	}
}
