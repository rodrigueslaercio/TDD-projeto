import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ServicoTests {
    ServicoRepositorio sr;
    ServicoNegocio sn;

    @BeforeEach
    public void initTestes() {
        List<Servico> servicos = new ArrayList<>();

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

        servicos.add(servico1);
        servicos.add(servico2);


        sr = new ServicoRepositorio(servicos);
        sn = new ServicoNegocio(sr);
    }

    @Test
    public void cadastrarServicoTest() {
		/*
            TC011(RF006)
            Rafael Marques
        */
        Servico servicoCadastrar = new Servico();

        servicoCadastrar.setNome("Sabor da Terra");
        servicoCadastrar.setCategoria("Alimentação");
        servicoCadastrar.setDisponibilidade("Tarde");
        servicoCadastrar.setValor(35.0);
        servicoCadastrar.setDiferenciais("Adultos: Galeto completo");
        servicoCadastrar.setRestricoes("Nenhuma");

        Assertions.assertEquals(2, (int) sr.totalServicos());

        Boolean cadastrou = sn.cadastrarServico(servicoCadastrar);
        Assertions.assertTrue(cadastrou);

        Assertions.assertEquals(3, (int) sr.totalServicos());
    }

    @Test
    public void buscarServicoTest() {
		/*
            TC018(RF009)
            Rafael Marques
        */
        FiltroBuscaServico filtro = new FiltroBuscaServico();
        sn = new ServicoNegocio(sr);

        filtro.setNome("Casa");
        filtro.setCategoria("Limpeza");
        filtro.setDisponibilidade("Manhã");
        filtro.setValorInicio(0.0);
        filtro.setValorFinal(100.0);
        filtro.setDiferenciais("Profissional");
        filtro.setRestricoes("Nenhuma");

        List<Servico> servicos = sn.buscarServico(filtro);

        Assertions.assertEquals(2, servicos.size());
        Assertions.assertEquals(1, servicos.get(0).getId().intValue());
        Assertions.assertEquals(2, servicos.get(1).getId().intValue());
    }

    @Test
    public void buscarServicoNomeTest() {
		/*
            TC018(RF009)
            Rafael Marques
        */
        FiltroBuscaServico filtro = new FiltroBuscaServico();
        sn = new ServicoNegocio(sr);

        filtro.setNome("Casa");

        List<Servico> servicos = sn.buscarServico(filtro);

        Assertions.assertEquals(1, servicos.size());
        Assertions.assertEquals(1, servicos.get(0).getId().intValue());
    }

    @Test
    public void buscarServicoCategoriaTest() {
		/*
            TC018(RF009)
            Rafael Marques
        */
        FiltroBuscaServico filtro = new FiltroBuscaServico();
        sn = new ServicoNegocio(sr);

        filtro.setCategoria("Mecanica");

        List<Servico> servicos = sn.buscarServico(filtro);

        Assertions.assertEquals(1, servicos.size());
        Assertions.assertEquals(2, servicos.get(0).getId().intValue());
    }

    @Test
    public void buscarServicoDisponibilidadeTest() {
		/*
            TC018(RF009)
            Rafael Marques
        */
        FiltroBuscaServico filtro = new FiltroBuscaServico();
        sr = new ServicoRepositorio();
        sn = new ServicoNegocio(sr);

        filtro.setDisponibilidade("Manhã");

        List<Servico> servicos = sn.buscarServico(filtro);

        Assertions.assertEquals(2, servicos.size());
        Assertions.assertEquals(1, servicos.get(0).getId().intValue());
        Assertions.assertEquals(2, servicos.get(1).getId().intValue());

    }

    @Test
    public void buscarServicoValorTest() {
		/*
            TC018(RF009)
            Rafael Marques
        */
        FiltroBuscaServico filtro = new FiltroBuscaServico();
        sn = new ServicoNegocio(sr);

        filtro.setValorInicio(100.0);
        filtro.setValorFinal(500.0);

        List<Servico> servicos = sn.buscarServico(filtro);

        Assertions.assertEquals(1, servicos.size());
        Assertions.assertEquals(2, servicos.get(0).getId().intValue());
    }

    @Test
    public void buscarServicoDiferenciaisTest() {
		/*
            TC018(RF009)
            Rafael Marques
        */
        FiltroBuscaServico filtro = new FiltroBuscaServico();
        sn = new ServicoNegocio(sr);

        filtro.setDiferenciais("Oleo");

        List<Servico> servicos = sn.buscarServico(filtro);

        Assertions.assertEquals(1, servicos.size());
        Assertions.assertEquals(2, servicos.get(0).getId().intValue());
    }

    @Test
    public void buscarServicoRestricoesTest() {
		/*
            TC018(RF009)
            Rafael Marques
        */
        FiltroBuscaServico filtro = new FiltroBuscaServico();
        sn = new ServicoNegocio(sr);

        filtro.setRestricoes("Nenhuma");

        List<Servico> servicos = sn.buscarServico(filtro);

        Assertions.assertEquals(2, servicos.size());
        Assertions.assertEquals(1, servicos.get(0).getId().intValue());
        Assertions.assertEquals(2, servicos.get(1).getId().intValue());
    }

    @Test
    public void buscarServicoNenhumTest() {
		/*
            TC018(RF009)
            Rafael Marques
        */
        FiltroBuscaServico filtro = new FiltroBuscaServico();
        sn = new ServicoNegocio(sr);

        filtro.setNome("cozinha");

        List<Servico> servicos = sn.buscarServico(filtro);

        Assertions.assertEquals(0, servicos.size());

    }

    @Test
    public void avaliarServicoTest() {
		/*
            TC016(RF015)
            Rafael Marques
        */
        Integer idUsuario = 2;
        Integer idServico = 1;

        AvaliacaoServico avaliacao = new AvaliacaoServico();
        avaliacao.setRate(4);
        avaliacao.setComentario("Serviço Bom");
        avaliacao.setIdUsuario(idUsuario);

        Servico servicoAvaliar = sr.getServico(idServico);

        Assertions.assertTrue(servicoAvaliar.getAvaliacoes().size() == 0);

        Boolean avaliado = sn.avaliarServico(servicoAvaliar, avaliacao);

        Assertions.assertTrue(avaliado);

        Assertions.assertTrue(servicoAvaliar.getAvaliacoes().size() == 1);
    }

    @Test
    public void excluirServicoTest() {
		/*
            TC028(RF008)
            Rafael Marques
        */
        Integer idServico = 1;

        List<Servico> servicosUsuarioAntes = sr.listarServicos();

        Assertions.assertEquals(2, servicosUsuarioAntes.size());

        Servico servicoDeletar = sr.getServico(idServico);

        Boolean deletou = sr.rmvServico(servicoDeletar);

        Assertions.assertTrue(deletou);

        List<Servico> servicoUsuarioDepois = sr.listarServicos();

        Assertions.assertEquals(1, servicoUsuarioDepois.size());
    }

}

