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
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FileInputStream obj = null;
				ObjectInputStream lerObj = null;
				LinkedHashSet<Aluno> lhs = null;
				
				FileOutputStream saidaStream = null;
				ObjectOutputStream saidaStreamobjeto = null;
				
				try {
					obj = new FileInputStream ("base.bas");
					lerObj = new ObjectInputStream (obj);
					lhs = (LinkedHashSet<Aluno>) lerObj.readObject();
					lerObj.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				
				Object[] listaobjetos = (Object[]) lhs.toArray();
				
				for (int count = 0; count < listaobjetos.length; count++) {
					Aluno alunoIt = (Aluno) listaobjetos [count];
					
					if (aluno.getMatricula() == alunoIt.getMatricula()) {
						
						if (!aluno.getNome().equals (alunoIt.getNome()))
							alunoIt.setNome(aluno.getNome());
						
						if (aluno.getMensalidade() == alunoIt.getMensalidade())
							alunoIt.setMensalidade(aluno.getMensalidade());
						
						if (aluno.getMensalidade() == alunoIt.getMensalidade())
							alunoIt.setMensalidade (aluno.getMensalidade());
						
						if (saoDatasIguais (aluno.getDataAdm(), alunoIt.getDataAdm()))
							alunoIt.setDataAdm (aluno.getDataAdm());
					}
				}
				
				for (Aluno alu : lhs) {
					System.out.println ("===========================================");
					System.out.println ("matricula: "+alu.getMatricula());
					System.out.println ("Nome: "+alu.getNome());
					System.out.println ("Mesalidade: "+alu.getMensalidade());
					System.out.println ("Data:"+new Aluno().formataData(alu.getDataAdm()));
					System.out.println ("===========================================");
				}
				
				try {
					saidaStream = new FileOutputStream ("base.bas");
					saidaStreamobjeto = new ObjectOutputStream (saidaStream);
					saidaStreamobjeto.writeObject(lhs);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	    });
	    
	    botaoAlterar.addActionListener (new ActionListener() {
			@SuppressWarnings ("unchecked")
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

