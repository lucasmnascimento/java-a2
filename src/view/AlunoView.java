package view;

import java.awt.*;
import java.util.GregorianCalendar;

import javax.swing.*;

import model.vo.Aluno;

//import model.vo.Aluno;

public class AlunoView {

	JTextField matricula;
	JTextField nome;
	JTextField mensalidade;
	JTextField dataAdm;
	
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
		
        matricula = new JTextField (5);
	    nome = new JTextField (15);
	    mensalidade = new JTextField (5);
	    dataAdm = new JTextField (10);
	    
	    matricula.setText(Integer.toString(aluno.getMatricula()));
	    nome.setText(aluno.getNome()); 
	    mensalidade.setText(Double.toString(aluno.getMensalidade()));
	    dataAdm.setText(aluno.formataData(aluno.getDataAdm()));
	    
	    JButton botaoExcluir = new JButton ("Excluir");
	    JButton botaoAlterar = new JButton ("Alterar");
	    
	    
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
	    
	    panelAlunoView.add (botaoExcluir);
	    panelAlunoView.add (botaoAlterar);
	    
	    frameAlunoView.add (panelAlunoView);
	    
	    frameAlunoView.pack();
	    frameAlunoView.setLocationRelativeTo (null);
		
	    frameAlunoView.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
	    frameAlunoView.setVisible (true);
	}
	
}

