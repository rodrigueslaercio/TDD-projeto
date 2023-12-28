import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestePagamentoPix {
    PagamentoNegocio pn;
    PagamentoRepositorio pr;
    ServicoRepositorio sr;
    ServicoNegocio sn;
    Usuario usuario;

    @BeforeEach
    public void init() {
        pr = new PagamentoRepositorio();
        pn = new PagamentoNegocio(pr);

        List<Servico> servicos = new ArrayList<>();

        Servico servico = new Servico();
        servico.setId(1);
        servico.setNome("Limpeza de Casa");
        servico.setCategoria("Limpeza");
        servico.setDisponibilidade("Manhã");
        servico.setValor(250.0);
        servico.setDiferenciais("Profissional");
        servico.setRestricoes("Nenhuma");
        servico.setDescricao("Ofereço uma faxina simples");

        servicos.add(servico);

        sr = new ServicoRepositorio(servicos);
        sn = new ServicoNegocio(sr);

        usuario = new Usuario();
        usuario.setNome("John");
        usuario.setSobrenome("Doe");
        usuario.setSexo("M");
        usuario.setCpf("11111111111");
        usuario.setDataNascimento("01/01/2001");
        usuario.setEndereco("51011-00 Avenida Boa Viagem, Pina, Recife-PE");
        usuario.setEmail("johndoe@exampe.com");
        usuario.setSenha("Johndoe123@");
        usuario.setCelular("");
        usuario.setTipo(Tipo.None);
    }

    @Test
    public void pagarPixTest() {
        PagamentoPix pagamentoPix = new PagamentoPix();
        Servico servico = sr.getServico(1);

        pagamentoPix.setChave("79613706219");
        pagamentoPix.setValor(250.0);

        boolean pagou = pn.realizarPagamento(pagamentoPix, servico, usuario);

        Assertions.assertTrue(pagou);

    }
}
