package model.vo;
import java.util.GregorianCalendar;

public class Aluno {
	private int matricula;
	private String nome;
	private double mensalidade;
	private GregorianCalendar dataAdm;
	
	public Aluno(int matricula, String nome, double mensalidade, GregorianCalendar dataAdm){
		this.matricula=matricula;
		this.nome=nome;
		this.mensalidade=mensalidade;
		this.dataAdm=dataAdm;
	}

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

}
