import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TesteEditarServico {
    ServicoRepositorio sr;
    ServicoNegocio sn;

    @BeforeEach
    public void init() {
        List<Servico> servicos = new ArrayList<>();

        Servico servico = new Servico();
        servico.setId(1);
        servico.setNome("Limpeza de Casa");
        servico.setCategoria("Limpeza");
        servico.setDisponibilidade("Manhã");
        servico.setValor(80.0);
        servico.setDiferenciais("Profissional");
        servico.setRestricoes("Nenhuma");
        servico.setDescricao("Ofereço uma faxina simples");

        servicos.add(servico);

        sr = new ServicoRepositorio(servicos);
        sn = new ServicoNegocio(sr);

    }

    @Test
    public void editarServicoTest() {
        Servico servico = sr.getServico(1);

        Servico servicoEditar = new Servico();

        servicoEditar.setNome("Limpeza completa");
        servicoEditar.setCategoria("Limpeza residencial");
        servicoEditar.setDisponibilidade("Tarde");
        servicoEditar.setValor(100.0);
        servicoEditar.setDiferenciais("Nenhuma");
        servicoEditar.setRestricoes("Nenhuma");
        servicoEditar.setDescricao("Ofereço uma faxina completa");

        Boolean editou = sn.editarServico(servico, servicoEditar);

        Assertions.assertTrue(editou);
    }
}

