package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.GregorianCalendar;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedHashSet;

import javax.swing.*;

import model.vo.Aluno;

//import model.vo.Aluno;

public class AlunoView {

	JTextField matricula;
	JTextField nome;
	JTextField mensalidade;
	JTextField dataAdm;
	
	
	JButton botaoExcluir;
	JButton botaoAlterar;
	JButton botaoSalvar;
	JButton botaoCancelar;
	
	public AlunoView(Aluno aluno) {
		init(aluno);
	}
	
	protected void init(Aluno aluno) {
		JFrame frameAlunoView = new JFrame ("Aluno");
		aluno.getDataAdm();
		aluno.getNome();
		aluno.getMatricula();
		aluno.getMensalidade();
		JPanel panelAlunoView = new JPanel();
		
		frameAlunoView.setLayout (new GridLayout (3, 3));
		frameAlunoView.setSize(900, 200);
		
        matricula = new JTextField (5);
	    nome = new JTextField (15);
	    mensalidade = new JTextField (5);
	    dataAdm = new JTextField (10);
	    
	    matricula.setText(Integer.toString(aluno.getMatricula()));
	    nome.setText(aluno.getNome()); 
	    mensalidade.setText(Double.toString(aluno.getMensalidade()));
	    dataAdm.setText(aluno.formataData(aluno.getDataAdm()));
	    
	    botaoExcluir = new JButton ("Excluir");
	    botaoAlterar = new JButton ("Alterar");
	    botaoSalvar = new JButton ("Salvar");
	    botaoCancelar = new JButton ("Cancelar");
	    
	    panelAlunoView.add (new JLabel ("Matrícula "));
	    matricula.setSize (2, 5);
	    panelAlunoView.add (matricula);
	    matricula.setEnabled(false);
	    
	    panelAlunoView.add (new JLabel ("Nome "));
	    nome.setSize (2, 15);
	    panelAlunoView.add (nome);
	    nome.setEnabled(false);
        
	    panelAlunoView.add (new JLabel ("Mensalidade"));
	    mensalidade.setSize (2, 5);
	    panelAlunoView.add (mensalidade);
	    mensalidade.setEnabled(false);
	    
	    panelAlunoView.add (new JLabel ("Data"));
	    dataAdm.setSize (2, 5);
	    panelAlunoView.add (dataAdm);
	    dataAdm.setEnabled(false);
	    
	    botaoSalvar.setVisible(false);
	    botaoCancelar.setVisible(false);
	    
	    panelAlunoView.add (botaoExcluir);
	    panelAlunoView.add (botaoAlterar);
	    panelAlunoView.add (botaoSalvar);
	    panelAlunoView.add (botaoCancelar);
	    
	    frameAlunoView.add (panelAlunoView);
	    
	    botaoSalvar.addActionListener (new ActionListener() {
			@SuppressWarnings({ "unchecked", "resource" })
			@Override
			public void actionPerformed (ActionEvent arg0) {
				
				try {
					FileInputStream obj = new FileInputStream ("base.bas");
					ObjectInputStream lerObj = new ObjectInputStream (obj);
					LinkedHashSet<Aluno> lhs = (LinkedHashSet<Aluno>) lerObj.readObject();
					lerObj.close();
					
					Object[] listaobjetos = (Object[]) lhs.toArray();
					
					lhs = new LinkedHashSet<Aluno> ();
					
					for (int count = 0; count < listaobjetos.length; count++) {
						Aluno alunoIt = (Aluno) listaobjetos [count];
						
						if (aluno.getMatricula() == alunoIt.getMatricula()) {
							alunoIt.setNome(nome.getText());
							alunoIt.setMensalidade(Double.parseDouble(mensalidade.getText()));
							
							String dataInformada = dataAdm.getText();
							
							int dia = Integer.parseInt(dataInformada.substring (0,2));
							int mes = Integer.parseInt(dataInformada.substring (3,5));
							int ano = Integer.parseInt(dataInformada.substring (6));
							
							GregorianCalendar dataConvertida = new GregorianCalendar (ano, mes, dia);
							
							alunoIt.setDataAdm (dataConvertida);
						}
						lhs.add (alunoIt);
					}
					
					FileOutputStream saidaStream = new FileOutputStream ("base.bas");
					ObjectOutputStream saidaStreamobjeto = new ObjectOutputStream (saidaStream);
					saidaStreamobjeto.writeObject (lhs);
					
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				
				JOptionPane.showMessageDialog (botaoSalvar, "Gravação efetuada com sucesso");
				
				frameAlunoView.dispose();
			}
	    });
	    
	    botaoAlterar.addActionListener (new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				nome.setEnabled (true);
				mensalidade.setEnabled (true);
				dataAdm.setEnabled (true);
				
				botaoSalvar.setVisible (true);
				botaoCancelar.setVisible (true);
				botaoAlterar.setVisible (false);
				botaoExcluir.setVisible (false);
				
				frameAlunoView.repaint();
				frameAlunoView.validate();
			}
		});
	    botaoCancelar.addActionListener (new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				nome.setEnabled(false);
				mensalidade.setEnabled(false);
				dataAdm.setEnabled(false);
				
				botaoSalvar.setVisible (false);
				botaoCancelar.setVisible (false);
				botaoAlterar.setVisible (true);
				botaoExcluir.setVisible (true);
				
				
				
				frameAlunoView.repaint();
				frameAlunoView.validate();
			}});
	    botaoExcluir.addActionListener (new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed (ActionEvent e) {
				 int confirma = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja excluir o aluno?", "Sim", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
				    if (confirma == JOptionPane.YES_OPTION) {
						FileInputStream obj;
						try {
							obj = new FileInputStream ("base.bas");
							ObjectInputStream lerObj = new ObjectInputStream(obj);
							LinkedHashSet<Aluno> lhs = (LinkedHashSet<Aluno>) lerObj.readObject();
							OutputStream outputStream = new FileOutputStream ("base.bas");
							ObjectOutputStream objectOutput = new ObjectOutputStream(outputStream);
							Iterator<Aluno> iterator = lhs.iterator();
							while (iterator.hasNext()) {
							   Aluno alunoTem = iterator.next();
							    if (alunoTem.equals(aluno)) {
							        iterator.remove();
							        objectOutput.writeObject(lhs);
							    }
							}
							objectOutput.close();
							lerObj.close();
							frameAlunoView.dispose();
						} catch (IOException | ClassNotFoundException e1) {
							e1.printStackTrace();
						}
				    } else {
	                            }
				nome.setEnabled(false);
				mensalidade.setEnabled(false);
				dataAdm.setEnabled(false);
				
				botaoSalvar.setVisible (false);
				botaoCancelar.setVisible (false);
				botaoAlterar.setVisible (true);
				botaoExcluir.setVisible (true);
				
				
				
				frameAlunoView.repaint();
				frameAlunoView.validate();
			}});
	    
	    frameAlunoView.setLocationRelativeTo (null);
		
	    frameAlunoView.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
	    frameAlunoView.setVisible (true);
	}
	
	protected boolean saoDatasIguais (GregorianCalendar data, GregorianCalendar outraData) {
		boolean status = false;
		if ((data.DAY_OF_MONTH == outraData.DAY_OF_MONTH) && (data.MONTH == outraData.MONTH) && (data.YEAR == outraData.YEAR))
			status = true;
		return status;
	}
	
}

