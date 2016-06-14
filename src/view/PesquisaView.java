package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.ParseException;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.LinkedHashSet;
import java.util.SortedSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.text.MaskFormatter;

import model.vo.Aluno;

public class PesquisaView {

	JTextField matricula;
	JTextField nome;
	JTextField mensalidade;
	JTextField dataAdm;
	JTable tabela;
	JScrollPane scroll;
	
	JCheckBox ordenacaoNome;
	JCheckBox ordenacaoMatricula;
	
	
	JButton botaoPesquisar;
	JButton botaoNovaPesquisa;
	JButton botaoEditar;
	
	public PesquisaView() {
		init();
	}

	protected void init(){
		JFrame framePesquisa = new JFrame ("Pesquisa");

		JPanel panelPesquisa = new JPanel();
		framePesquisa.setLayout (new GridLayout (3, 3));
		framePesquisa.setSize(900, 200);
		framePesquisa.setResizable(false);

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
			e1.printStackTrace();
		}
		
		ordenacaoNome = new JCheckBox();
		ordenacaoMatricula = new JCheckBox();
		
		botaoPesquisar = new JButton ("Pesquisar");
		botaoNovaPesquisa = new JButton ("Limpar");
		botaoEditar = new JButton ("Editar");
		
		panelPesquisa.add (new JLabel ("Matrícula "));
		panelPesquisa.add (matricula);

		panelPesquisa.add (new JLabel ("Nome "));
		panelPesquisa.add (nome);

		panelPesquisa.add (new JLabel ("Mensalidade"));
		panelPesquisa.add (mensalidade);

		panelPesquisa.add (new JLabel ("Data"));
		panelPesquisa.add (dataAdm);

		panelPesquisa.add (new JLabel ("Ordenação por Nome"));
		panelPesquisa.add (ordenacaoNome);
		
		panelPesquisa.add (new JLabel ("Ordenação por Matrícula"));
		panelPesquisa.add (ordenacaoMatricula);
		
		panelPesquisa.add (botaoPesquisar);
		panelPesquisa.add (botaoNovaPesquisa);
		botaoNovaPesquisa.setVisible(false);
		panelPesquisa.add (botaoEditar);
		botaoEditar.setVisible(false);

		framePesquisa.add (panelPesquisa);
		
		botaoPesquisar.addActionListener(
				new ActionListener() {
					@SuppressWarnings({ "unchecked", "unused", "resource" })
					@Override
					public void actionPerformed (ActionEvent e) {
						executaPesquisa(framePesquisa);
					}
				});
		
		botaoNovaPesquisa.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						limparPesquisa();
						framePesquisa.remove(scroll);
						framePesquisa.repaint();
						framePesquisa.validate();
						botaoNovaPesquisa.setVisible(false);
						botaoEditar.setVisible(false);
						botaoPesquisar.setVisible(true);
						
					}});
		
		botaoEditar.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						int linhaSelecionada = tabela.getSelectedRow();
						
						String matriculaRetornada = (String) tabela.getValueAt(linhaSelecionada,0);
						String nomeRetornado = (String) tabela.getValueAt(linhaSelecionada,1);
						String mensalidadeRetornada = (String) tabela.getValueAt(linhaSelecionada,2);
						String dataRetornada = (String) tabela.getValueAt(linhaSelecionada, 3);
						
						GregorianCalendar dataConvertida = Aluno.formataData(dataRetornada);
						
						Aluno aluno = new Aluno(Integer.parseInt(matriculaRetornada), nomeRetornado, 
								Double.parseDouble(mensalidadeRetornada), dataConvertida);
						
						new AlunoView(aluno);
					}});

		//framePesquisa.pack();
		framePesquisa.setLocationRelativeTo (null);

		framePesquisa.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
		framePesquisa.setVisible (true);
	}

	@SuppressWarnings({ "unused", "unchecked" })
	protected void executaPesquisa (JFrame framePesquisa) {
		botaoPesquisar.setVisible (false);
		botaoEditar.setVisible (true);
		botaoNovaPesquisa.setVisible (true);
		FileInputStream obj = null;
		try {
			String matriculaInformada = matricula.getText();
			String dataInformada = dataAdm.getText();
			String nomeInformado = nome.getText();

			GregorianCalendar dataConvertida = null;

			boolean presencaData = false;
			
			if (!dataInformada.contains("_")) {
				if (dataInformada != null && !dataInformada.equals ("")) {
					int dia = Integer.parseInt(dataInformada.substring (0,2));
					int mes = Integer.parseInt(dataInformada.substring (3,5));
					int ano = Integer.parseInt(dataInformada.substring (6));

					dataConvertida = new GregorianCalendar (ano, mes, dia);
					
					presencaData = true;
				}
			}
			
			dataConvertida = new GregorianCalendar();
			
			double mensalidadeConvertida = 0D;
			
			if (mensalidade.getText() != null && !("").equals(mensalidade.getText()))
				mensalidadeConvertida = Double.parseDouble(mensalidade.getText());
			
			int matriculaConvertida = 0;
			
			if (matricula.getText() != null && !("").equals(matricula.getText()))
				matriculaConvertida = Integer.parseInt(matriculaInformada);

			Aluno aluno = new Aluno(matriculaConvertida, nome.getText(), mensalidadeConvertida, dataConvertida);
			obj = new FileInputStream ("base.bas");
			ObjectInputStream lerObj = new ObjectInputStream(obj);
			LinkedHashSet<Aluno> lhs = (LinkedHashSet<Aluno>) lerObj.readObject();
			
			String[] colunas = new String[] {"Matrícula","Nome", "Mensalidade", "Data"};
			
			Object[] listaobjetos = (Object[]) lhs.toArray();
			
			if (ordenacaoNome.isSelected()) {
				SortedSet<Aluno> setSorted = (SortedSet<Aluno>) lhs;
				SortedSet<Aluno> sorted = Collections.checkedSortedSet(setSorted, Aluno.class);
			} else if (ordenacaoMatricula.isSelected()){
				
			}
				  
			
			LinkedHashSet<Aluno> resultSet = new LinkedHashSet<Aluno>();
			
			boolean resultadoExiste = false;
			
			for (int count = 0; count < listaobjetos.length; count++) {
				Aluno alunoRecuperado = (Aluno) listaobjetos[count];
					
					if (matricula.getText() != null && !("").equals(matricula.getText())) { 
						if (alunoRecuperado.getMatricula() == Integer.parseInt (matricula.getText()))
							resultadoExiste = true;
						else
							resultadoExiste = false;
					}
					
					if (nome.getText() != null && !nome.getText().equals ("")) { 
						if (alunoRecuperado.getNome().equalsIgnoreCase (nome.getText()))
							resultadoExiste = true;
						else
							resultadoExiste = false;
					}
					
					if (mensalidade.getText() != null && !mensalidade.getText().equals ("")) { 
						if (alunoRecuperado.getMensalidade() == mensalidadeConvertida)
							resultadoExiste = true;
						else
							resultadoExiste = false;
					}
					
					if (presencaData) {
						if(saoDatasIguais(alunoRecuperado.getDataAdm(), dataConvertida ))
							resultadoExiste = true;
						else
							resultadoExiste = false;
					}
					
					if (resultadoExiste)
						resultSet.add (alunoRecuperado);
				}
			
			if (!resultSet.isEmpty()) {
			
				Object[] listaResultado = (Object[]) resultSet.toArray();
				
				String matrizAluno[][] = new String[listaResultado.length][4];


				for (int j = 0; j < listaResultado.length; j++) {

					Aluno alunoObj = (Aluno) listaResultado[j];

					matrizAluno[j][0] = Integer.toString(alunoObj.getMatricula());
					matrizAluno[j][1] = alunoObj.getNome();
					matrizAluno[j][2] = Double.toString(alunoObj.getMensalidade());
					matrizAluno[j][3] = alunoObj.formataData(alunoObj.getDataAdm());
				}

				tabela = new JTable(matrizAluno,colunas);
				scroll = new JScrollPane();
				scroll.setViewportView(tabela);
				tabela.setEnabled(true);
				tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
				framePesquisa.add(scroll);
				framePesquisa.repaint();
				framePesquisa.validate();
			} else {
				JOptionPane.showMessageDialog(botaoPesquisar, "Nenhum registro foi encontrado");
				limparPesquisa();
			}
			

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
	
	protected void limparPesquisa() {
		matricula.setText("");
		nome.setText("");
		mensalidade.setText("");
		dataAdm.setText("");
	}
	
	// TODO refatorar o código... colocar em Aluno.
	protected boolean saoDatasIguais (GregorianCalendar data, GregorianCalendar outraData) {
		
		boolean status = false;
		
		if ((data.DAY_OF_MONTH == outraData.DAY_OF_MONTH) && (data.MONTH == outraData.MONTH) && (data.YEAR == outraData.YEAR))
			status = true;
		
		return status;
	}
}