package model.vo;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Aluno implements Serializable{
	
	/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	//private static final long serialVersionUID = 6325158508878264619L;
	private int matricula;
	private String nome;
	private double mensalidade;
	private GregorianCalendar dataAdm;

	public Aluno(int matricula, String nome, double mensalidade, GregorianCalendar dataAdm) {
		this.matricula=matricula;
		this.nome=nome;
		this.mensalidade=mensalidade;
		this.dataAdm=dataAdm;
	}

	public Aluno(){}
	
	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getMensalidade() {
		return mensalidade;
	}
	public void setMensalidade(double mensalidade) {
		this.mensalidade = mensalidade;
	}
	public GregorianCalendar getDataAdm() {
		return dataAdm;
	}
	public void setDataAdm(GregorianCalendar dataAdm) {
		this.dataAdm = dataAdm;
	}
	public String toString() {
		return "Aluno [matricula=" + matricula + ", nome=" + nome + ", mensalidade=" + mensalidade + ", dataAdm="
				+ dataAdm + "]";
	}
	
	public String formataData(GregorianCalendar data){
		Calendar cal = data;
		SimpleDateFormat formata = new SimpleDateFormat("dd/MM/yyyy");
	    formata.setCalendar(data);
	    cal.add(Calendar.MONTH, -1);
	    String dataFormatada = formata.format(data.getTime());
	    return dataFormatada;
	}
	
	public static GregorianCalendar formataData(String data){
		
		int dia = Integer.parseInt(data.substring(0, 2));
		int mes = Integer.parseInt(data.substring(3, 5));
		int ano = Integer.parseInt(data.substring(6));
		
		return new GregorianCalendar (ano, mes, dia);
	}
	
	public boolean equals(Object o){
		Aluno aluno = (Aluno) o;
		
		return this.getMatricula() == aluno.getMatricula();
		
	}
/*
	@Override
	public int compareTo(Object arg0) {
		Aluno aluno = (Aluno) arg0;
		return this.getNome().compareTo(aluno.getNome());
	}*/
}
