package model.vo;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Aluno implements Serializable, Comparable<Object>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6325158508878264619L;
	private int matricula;
	private String nome;
	private double mensalidade;
	private GregorianCalendar dataAdm;
	


	public Aluno(int parseInt, String text, double mensalidadeConvertida, GregorianCalendar dataConvertida) {
		this.matricula=matricula;
		this.nome=nome;
		this.mensalidade=mensalidade;
		this.dataAdm=dataAdm;
	}
	
	public Aluno(int matricula, String nome){
		this.matricula=matricula;
		this.nome=nome;
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
	public String formataData(){
	    SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
	    fmt.setCalendar(getDataAdm());
	    String dateFormatted = fmt.format(getDataAdm().getTime());
	    return dateFormatted;
	}

	@Override
	public int compareTo(Object arg0) {
		Aluno aluno = (Aluno) arg0;
		return this.getNome().compareTo(aluno.getNome());
	}
}
