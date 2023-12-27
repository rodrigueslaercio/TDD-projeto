package negocio;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import entidades.AvaliacaoServico;
import entidades.FiltroBuscaServico;
import entidades.Servico;
import repositorio.ServicoRepositorio;

public class ServicoNegocio {
	
	private ServicoRepositorio sr;
	
	public ServicoNegocio(ServicoRepositorio sr) {
		this.sr = sr;
	}
	
	private Boolean filtrarNome(String filtroNome, String nome) {
		if(Objects.nonNull(filtroNome)) {
			return (nome.toUpperCase().contains(filtroNome.toUpperCase()));
		}
		return false;
	}
	
	private Boolean filtrarCategoria(String filtroCategoria, String categoria) {
		if(Objects.nonNull(filtroCategoria)) {
			return (categoria.toUpperCase().contains(filtroCategoria.toUpperCase()));
		}
		return false;
	}
	
	private Boolean filtrarDisponibilidade(String filtroDisponibilidade, String disponibilidade) {
		if(Objects.nonNull(filtroDisponibilidade)) {
			return (disponibilidade.toUpperCase().contains(filtroDisponibilidade.toUpperCase()));
		}
		return false;
	}
	
	private Boolean filtrarValor(Double filtroValorInicio, Double filtroValorFinal, Double valor) {
		if(Objects.nonNull(filtroValorInicio) && Objects.nonNull(filtroValorFinal)) {
			return ((valor >= filtroValorInicio) && (valor <=filtroValorFinal));
		}
		return false;
	}
	
	private Boolean filtrarDiferenciais(String filtroDiferenciais, String diferenciais) {
		if(Objects.nonNull(filtroDiferenciais)) {
			return (diferenciais.toUpperCase().contains(filtroDiferenciais.toUpperCase()));
		}
		return false;
	}
	
	private Boolean filtrarRestricoes(String filtroRetricoes, String retricoes) {
		if(Objects.nonNull(filtroRetricoes)) {
			return (retricoes.toUpperCase().contains(retricoes.toUpperCase()));
		}
		return false;
	}
	
	public List<Servico> buscarServico(FiltroBuscaServico filtros) {
		List<Servico> result = this.sr.listarServicos().stream().filter(s -> {
			Boolean filter = this.filtrarNome(filtros.getNome(), s.getNome()) || 
					this.filtrarCategoria(filtros.getCategoria(), s.getCategoria()) ||
					this.filtrarDisponibilidade(filtros.getDisponibilidade(), s.getDisponibilidade()) ||
					this.filtrarValor(filtros.getValorInicio(), filtros.getValorFinal(), s.getValor()) ||
					this.filtrarDiferenciais(filtros.getDiferenciais(), s.getDiferenciais()) ||
					this.filtrarRestricoes(filtros.getRestricoes(), s.getRestricoes());
			return filter;
		}).collect(Collectors.toList());
		
		return result;
	}
	
	public Boolean editarServico(Servico servico, Servico servicoEditar) {
		if(!servico.equals(servicoEditar)) {
			return sr.updateServico(servico, servicoEditar);
		}
		return false;
	}
	
	public Boolean cadastrarServico(Servico servicoCad) {
	    if (validateServico(servicoCad) && !sr.getAllServicos().contains(servicoCad)) {
	        return sr.addServico(servicoCad);
	    }
	    return false;
	}

	private Boolean validateServico(Servico servico) {
	    if (Objects.isNull(servico.getNome()) || servico.getNome().trim().isEmpty()) {
	        return false;
	    }if (Objects.isNull(servico.getValor()) || !sr.getAllServicos().contains(servico)) {

	    }
	    return true;
	}
	
	
	
	public Boolean avaliarServico(Servico servicoAvaliar, AvaliacaoServico avaliacao) {
		for (AvaliacaoServico aval: servicoAvaliar.getAvaliacoes()) {
			if(aval.getIdUsuario().equals(avaliacao.getIdUsuario())) {
				return false;
			}
		}
		return sr.addAvaliacao(servicoAvaliar, avaliacao);
	}

}
