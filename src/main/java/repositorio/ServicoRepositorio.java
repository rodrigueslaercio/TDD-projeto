package main.java.repositorio;

import java.util.ArrayList;
import java.util.List;

import main.java.entidades.AvaliacaoServico;
import main.java.entidades.Servico;

public class ServicoRepositorio {
	
	List<Servico> servicos;
	
	public ServicoRepositorio(List<Servico> servicos) {
		this.servicos = servicos;
	}
	
	public ServicoRepositorio() {
		this.servicos = new ArrayList<>();
		
		Servico servico1 = new Servico();
		servico1.setId(1);
		servico1.setNome("Limpeza de Casa");
		servico1.setCategoria("Limpeza");
		servico1.setDisponibilidade("Manhã");
		servico1.setValor(80.0);
		servico1.setDiferenciais("Profissional");
		servico1.setRestricoes("Nenhuma");
		
		Servico servico2 = new Servico();
		servico2.setId(2);
		servico2.setNome("Manutenção de Carro");
		servico2.setCategoria("Mecanica");
		servico2.setDisponibilidade("Manhã/Tarde");
		servico2.setValor(500.0);
		servico2.setDiferenciais("Troca de Oleo");
		servico2.setRestricoes("Nenhuma");
		
		this.servicos.add(servico1);
		this.servicos.add(servico2);
	}
	
	public List<Servico> listarServicos() {
		return this.servicos;
	}
	
	public Servico getServico(Integer idServico) {
		for (Servico servico : servicos) {
			if(servico.getId().equals(idServico)) {
				return servico;
			}
		}
		return null;
	}
	
	public Boolean rmvServico(Servico servico) {
		return this.servicos.remove(servico);
	}
	
	public List<Servico> getAllServicos() {
		return servicos;
	}

	public Boolean updateServico(Servico servico, Servico servicoEditar) {
		servico.setNome(servicoEditar.getNome());
		servico.setCategoria(servicoEditar.getCategoria());
		servico.setDisponibilidade(servicoEditar.getDisponibilidade());
		servico.setValor(servicoEditar.getValor());
		servico.setDiferenciais(servicoEditar.getDiferenciais());
		servico.setRestricoes(servico.getRestricoes());
		return true;
	}
	
	public Boolean addServico(Servico servico) {
		return this.servicos.add(servico);
	}
	
	public Integer totalServicos() {
		return this.servicos.size();
	}
	
	public Boolean addAvaliacao(Servico servico, AvaliacaoServico avaliacao) {
		for (Servico serv : servicos) {
			if(servico.equals(serv)) {
				return serv.getAvaliacoes().add(avaliacao);
			}
		}
		return false;
	}

}
