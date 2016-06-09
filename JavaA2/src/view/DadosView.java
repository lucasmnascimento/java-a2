package view;
import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.GregorianCalendar;
import java.util.LinkedHashSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.vo.Aluno;

public class DadosView extends JFrame{
	
	public DadosView() throws IOException{
		super("Resultados");
		dataGrid();
		setSize(400,300);
		setResizable(false);
		setVisible(true);
	}
	
	public void dataGrid() throws IOException{
		
		FileInputStream obj = new FileInputStream("base.bas");
		ObjectInputStream lerObj = new ObjectInputStream(obj);
		
	
		String[] colunas = {"Matrícula","Nome","Mensalidade", "Data Admissão"};

		try {
			LinkedHashSet<Aluno> lhs = (LinkedHashSet<Aluno>) lerObj.readObject();
			lhs.iterator();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
		
		
		JTable tabela = new JTable();
		DefaultTableModel modeloTabela = new DefaultTableModel (0,4);
		modeloTabela.setColumnIdentifiers(colunas);
		tabela.setModel(modeloTabela);
		
		
		
		/*
		try{
			FileInputStream obj = new FileInputStream("base.bas");
			ObjectInputStream lerObj = new ObjectInputStream(obj);
			 LinkedHashSet<Aluno> lhs = (LinkedHashSet<Aluno>) lerObj.readObject();
			
			 for (Aluno alu : lhs){
                 modeloTabela.addRow();
                 new DadosView();
             }
			
		}
		catch(FileNotFoundException ex){
			JOptionPane.showMessageDialog(null, "Arquivo não encontrado!");
		}*/
	}

}
