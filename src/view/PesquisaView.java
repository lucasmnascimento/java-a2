package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.ParseException;
import java.util.GregorianCalendar;
import java.util.LinkedHashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  

		JButton botaoPesquisar = new JButton ("Pesquisar");
		JButton botaoNovaPesquisa = new JButton ("Limpar");
		JButton botaoEditar = new JButton ("Editar");

		panelPesquisa.add (new JLabel ("Matrícula "));
		panelPesquisa.add (matricula);

		panelPesquisa.add (new JLabel ("Nome "));
		panelPesquisa.add (nome);

		panelPesquisa.add (new JLabel ("Mensalidade"));
		panelPesquisa.add (mensalidade);

		panelPesquisa.add (new JLabel ("Data"));
		panelPesquisa.add (dataAdm);

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
						botaoPesquisar.setVisible(false);
						botaoEditar.setVisible(true);
						botaoNovaPesquisa.setVisible(true);
						FileInputStream obj = null;
						try {
							String matriculaInformada = matricula.getText();
							String dataInformada = dataAdm.getText();
							String nomeInformado = nome.getText();

							GregorianCalendar dataConvertida = null;

							if (dataInformada != null && !dataInformada.equals ("")) {
								int dia = Integer.parseInt(dataInformada.substring (0,2));
								int mes = Integer.parseInt(dataInformada.substring (3,5));
								int ano = Integer.parseInt(dataInformada.substring (6));

								dataConvertida = new GregorianCalendar (ano, mes, dia);
							}

							dataConvertida = new GregorianCalendar();

							double mensalidadeConvertida = Double.parseDouble(mensalidade.getText());
							int matricula = Integer.parseInt(matriculaInformada);

							Aluno aluno = new Aluno(matricula, nome.getText(), mensalidadeConvertida, dataConvertida);
							obj = new FileInputStream ("base.bas");
							ObjectInputStream lerObj = new ObjectInputStream(obj);
							LinkedHashSet<Aluno> lhs = (LinkedHashSet<Aluno>) lerObj.readObject();

							for (Aluno alu : lhs){
								System.out.println(aluno.toString());
							}

							String[] colunas = new String[] {"Matrícula","Nome", "Mensalidade", "Data"};

							Object[] listaobjetos = (Object[]) lhs.toArray();

							String matrizAluno[][] = new String[listaobjetos.length][4];


							for (int j = 0; j < lhs.size(); j++) {

								Aluno alunoObj = (Aluno) listaobjetos[j];

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
						
						new AlunoView(aluno);
						framePesquisa.dispose();
					}});

		//framePesquisa.pack();
		framePesquisa.setLocationRelativeTo (null);

		framePesquisa.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
		framePesquisa.setVisible (true);
	}

	protected void limparPesquisa() {
		matricula.setText("");
		nome.setText("");
		mensalidade.setText("");
		dataAdm.setText("");

	}
}