import java.util.ArrayList;
import java.util.List;

public class Servico {
	
	private Integer id;
	private Usuario prestador;
	private Usuario tomador;
	private ServicoStatus status;
	private String nome;
	private String categoria;
	private Double valor;
	private String disponibilidade;
	private String restricoes;
	private String diferenciais;

	private String descricao;

	private List<AvaliacaoServico> avaliacoes;
	
	public Servico() {

		this.avaliacoes = new ArrayList<>();
		this.status = ServicoStatus.ABERTO;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getPrestador() { return prestador; }

	public void setPrestador(Usuario prestador) {
		if (prestador.getTipoUsuario() == TipoUsuario.PrestadorDeServicos)
			this.prestador = prestador;
	}

	public Usuario getTomador() { return tomador; }

	public void setTomador(Usuario tomador) {
		if (tomador.getTipoUsuario() == TipoUsuario.TomadorDeServicos)
			this.tomador = tomador;
	}

	public ServicoStatus getStatus() { return status; }

	public void setStatus(ServicoStatus status) { this.status = status; }

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

	public Double servico() {
		return valor;
	}
	
	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(String disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	public String getRestricoes() {
		return restricoes;
	}

	public void setRestricoes(String restricoes) {
		this.restricoes = restricoes;
	}

	public String getDiferenciais() {
		return diferenciais;
	}

	public void setDiferenciais(String diferenciais) {
		this.diferenciais = diferenciais;
	}
	
	public List<AvaliacaoServico> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<AvaliacaoServico> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	@Override
	public boolean equals(Object obj) {
		Servico servico = (Servico) obj;
		if(this.id == servico.getId()) {
			return true;
		}
		return false;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
