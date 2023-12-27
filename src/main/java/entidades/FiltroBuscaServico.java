package main.java.entidades;

public class FiltroBuscaServico {
	
	private String nome;
	private String categoria;
	private String disponibilidade;
	private Double valorInicio;
	private Double valorFinal;
	private String diferenciais;
	private String restricoes;
	
	public FiltroBuscaServico() {
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(String disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	public Double getValorInicio() {
		return valorInicio;
	}

	public void setValorInicio(Double valorInicio) {
		this.valorInicio = valorInicio;
	}

	public Double getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(Double valorFinal) {
		this.valorFinal = valorFinal;
	}

	public String getDiferenciais() {
		return diferenciais;
	}

	public void setDiferenciais(String diferenciais) {
		this.diferenciais = diferenciais;
	}

	public String getRestricoes() {
		return restricoes;
	}

	public void setRestricoes(String restricoes) {
		this.restricoes = restricoes;
	}
	
}
