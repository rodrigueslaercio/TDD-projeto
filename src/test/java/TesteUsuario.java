import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TesteUsuario {

    private Usuario usuario;
    private UsuarioRepositorio usuarioRepositorio;
    private UsuarioNegocio usuarioNegocio;
    private ServicoRepositorio servicoRepositorio;
    private ServicoNegocio servicoNegocio;

    @BeforeEach
    public void init() {
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
        usuario.setTipoUsuario(TipoUsuario.None);

        usuarioRepositorio = new UsuarioRepositorio();
        usuarioNegocio = new UsuarioNegocio(usuarioRepositorio);

        servicoRepositorio = new ServicoRepositorio();
        servicoNegocio = new ServicoNegocio(servicoRepositorio);
    }

    @Test
    public void cadastrarUsuarioCorretoTest() {
        /*
            TC001(RF001)
            Laércio Rodrigues
        */
        Assertions.assertNotNull(usuarioNegocio.cadastrar(usuario));

    }

    @Test
    public void cadastarUsuarioCelularTest() {
        /*
            TC002(RF001)
            Laércio Rodrigues
        */
        usuario.setEmail("");
        usuario.setCelular("81 99999-9999");

        Assertions.assertNotNull(usuarioNegocio.cadastrar(usuario));
    }

    @Test
    public void cadastrarUsuarioInformacoesAusentesTest() {
        /*
            TC003(RF001)
            Laércio Rodrigues
        */
        usuario.setCpf(null);
        usuario.setSenha(null);

        Assertions.assertNull(usuarioNegocio.cadastrar(usuario));
    }

    @Test
    public void cadastrarUsuarioSenhaInvalidaTest() {
        /*
            TC004(RF001)
            Laércio Rodrigues
        */
        usuario.setSenha("johndoeplaceholder");
        Assertions.assertNull(usuarioNegocio.cadastrar(usuario));
    }

    @Test
    public void cadastrarUsuarioCpfJaCadastrado() {
        /*
            TC005(RF001)
            Laércio Rodrigues
        */
        usuarioNegocio.cadastrar(usuario);

        Usuario usuario2 = new Usuario();
        usuario.setNome("Mark");
        usuario.setSobrenome("Doe");
        usuario.setSexo("M");
        usuario.setCpf("11111111111");
        usuario.setDataNascimento("01/01/2001");
        usuario.setEndereco("51011-00 Avenida Boa Viagem, Pina, Recife-PE");
        usuario.setEmail("markdoe@exampe.com");
        usuario.setSenha("Markdoe123@");

        Assertions.assertNull(usuarioNegocio.cadastrar(usuario2));
    }

    @Test
    public void cadastroSenhaMenor8CharTeste() {
        /*
            TC006(RF001)
            Laércio Rodrigues
        */
        usuario.setSenha("John2@");

        Assertions.assertNull(usuarioNegocio.cadastrar(usuario));
    }

    @Test
    public void cadastroSenhaMaior32CharTeste() {
        /*
            TC007(RF001)
            Laércio Rodrigues
        */
        usuario.setSenha("Johndoeplaceholder1234567890@@#!@#$@");

        Assertions.assertNull(usuarioNegocio.cadastrar(usuario));
    }

    @Test
    public void deveEditarDadosDeUsuario() {
        /**
         * TC023 (RF002)
         * Lucas Gomes
         * 
         * O sistema deverá ser capaz de editar os dados de um determinado usuário, sem editar
         * e-mail e número de celular.
        */

        Usuario usuarioOriginal = this.criarUsuario();
        this.usuarioRepositorio.inserir(usuarioOriginal);

        String novoNome      = "John eh um Cara";
        String novoSobrenome = "Desenrolado";

        Usuario usuarioAtualizado = this.criarUsuario();
        usuarioAtualizado.setNome(novoNome);
        usuarioAtualizado.setSobrenome(novoSobrenome);

        int indiceUsuario = this.usuarioRepositorio.obterIndice(usuarioOriginal);
        this.usuarioRepositorio.atualizar(indiceUsuario, usuarioAtualizado);

        Assertions.assertEquals(this.usuarioRepositorio.obterUsuario(indiceUsuario), usuarioAtualizado);
    }

    @Test
    public void deveEditarDadosDeUsuarioEmail() {
        /**
         * TC024 (RF002)
         * Lucas Gomes
         * 
         * O sistema deverá ser capaz de editar os dados de um determinado usuário, atualizando
         * também o e-mail do usuário ou o número de celular.
        */

        Usuario usuarioOriginal = this.criarUsuario();
        this.usuarioRepositorio.inserir(usuarioOriginal);

        String novoNome  = "John eh o Cara";
        String novoEmail = "johndoeehocara@example.com";

        Usuario usuarioAtualizado = this.criarUsuario();
        usuarioAtualizado.setNome(novoNome);
        usuarioAtualizado.setEmail(novoEmail);

        if (ValidationService.validateEmail(novoEmail)) {

            int indiceUsuario = this.usuarioRepositorio.obterIndice(usuarioOriginal);
            this.usuarioRepositorio.atualizar(indiceUsuario, usuarioAtualizado);

            Assertions.assertEquals(this.usuarioRepositorio.obterUsuario(indiceUsuario), usuarioAtualizado);
        }
    }

    @Test
    public void deveEfetuarLogin() {
        /**
         * TC025 (RF005)
         * Lucas Gomes
         * 
         * O usuário deverá ser capaz de efetuar login no sistema utilizando E-Mail e Senha.
        */

        Usuario usuarioCadastrado = this.criarUsuario();
        this.usuarioRepositorio.inserir(usuarioCadastrado);

        String emailLogin = "johndoe@example.com";
        String senhaLogin = "Johndoe@123";

        AuthenticationService authService = new AuthenticationService(this.usuarioRepositorio);
        boolean authenticationResult = authService.authenticate(emailLogin, senhaLogin);

        Assertions.assertTrue(authenticationResult);
    }

    @Test
    public void deveFalharAoEfetuarLoginPorEmailInexistente() {
        /**
         * TC026 (RF005)
         * Lucas Gomes
         *
         * Este caso de teste simula uma tentativa fracassada de efetuar login, onde, no momento
         * do login, o usuário informa um e-mail que não está armazenado no banco de dados do
         * sistema.
         */

        Usuario usuarioCadastrado = this.criarUsuario();
        this.usuarioRepositorio.inserir(usuarioCadastrado);

        String emailInexistente = "john-doe@example.com";
        String senhaLogin = "Johndoe@123";

        AuthenticationService authService = new AuthenticationService(this.usuarioRepositorio);
        boolean authenticationResult = authService.authenticate(emailInexistente, senhaLogin);

        Assertions.assertFalse(authenticationResult);
    }

    @Test
    public void deveFalharAoEfetuarLoginPorSenhaErrada() {
        /**
         * TC027 (RF005)
         * Lucas Gomes
         *
         * Este caso de teste simula uma tentativa fracassada de efetuar login, onde, no momento
         * do login, onde, no momento do login, o usuário informa uma senha incorreta para um
         * e-mail válido (associado a uma conta armazenada).
         */

        Usuario usuarioCadastrado = this.criarUsuario();
        this.usuarioRepositorio.inserir(usuarioCadastrado);

        String emailLogin = "johndoe@example.com";
        String senhaIncorreta = "Johndoe@321";

        AuthenticationService authService = new AuthenticationService(this.usuarioRepositorio);
        boolean authenticationResult = authService.authenticate(emailLogin, senhaIncorreta);

        Assertions.assertFalse(authenticationResult);
    }

    @Test
    public void usuarioPrestadorDeServicoPoderaExcluirServicosCadastrados() {
        /**
         * TC028 (RF008)
         * Lucas Gomes
         *
         * O usuário prestador de serviços poderá excluir serviços previamente cadastrados no sistema.
         */

        Usuario usuarioPrestadorDeServicos = this.criarUsuario();
        usuarioPrestadorDeServicos.setTipoUsuario(TipoUsuario.PrestadorDeServicos);

        Servico servico = this.criarServico();
        servico.setPrestador(usuarioPrestadorDeServicos);

        this.usuarioRepositorio.inserir(usuarioPrestadorDeServicos);
        this.servicoRepositorio.addServico(servico);

        boolean removeResult = this.servicoNegocio.removerServico(servico, usuarioPrestadorDeServicos);
        Assertions.assertTrue(removeResult);
    }

    @Test
    public void usuarioPossuiCarrinhoDeCompras() {
        /**
         * TC029 (RF011)
         * Lucas Gomes
         *
         * De acordo com o requisito, o usuário (Tomador de Serviço) poderá ter um carrinho que funcionará
         * de maneira similar a um carrinho de compras de e-commerce. Neste carrinho o usuário poderá incluir
         * todos os serviços que deseja para então contratar. Além disso o requisito especifica também que
         * o usuário poderá remover serviços deste carrinho. Nesse caso, vamos testar as funcionalidades
         * de inserção e remoção de serviços no carrinho de serviços.
         */

        Usuario usuarioTomadorDeServicos = this.criarUsuario();
        usuarioTomadorDeServicos.setTipoUsuario(TipoUsuario.TomadorDeServicos);

        Servico servico1 = this.criarServico();
        servico1.setId(1);
        servico1.setNome("Nail Designer");

        Servico servico2 = this.criarServico();
        servico2.setId(2);
        servico2.setNome("Mario Encanamentos");

        Servico servico3 = this.criarServico();
        servico3.setId(3);
        servico3.setNome("Conserto de Televisão");

        usuarioTomadorDeServicos.getCarrinhoCompras().add(servico1);
        usuarioTomadorDeServicos.getCarrinhoCompras().add(servico2);
        usuarioTomadorDeServicos.getCarrinhoCompras().add(servico3);

        usuarioTomadorDeServicos.getCarrinhoCompras().remove(servico2);

        CarrinhoCompras userCart = usuarioTomadorDeServicos.getCarrinhoCompras();
        boolean emptyCart = userCart.getAllServices().isEmpty();

        Assertions.assertFalse(emptyCart);
        Assertions.assertTrue(userCart.contains(servico1));
        Assertions.assertFalse(userCart.contains(servico2));
        Assertions.assertTrue(userCart.contains(servico3));
    }

    @Test
    public void finalizaOrdemDeServico() {
        /**
         * TC030 (RF011)
         * Lucas Gomes
         *
         * Neste caso ambos usuários envolvidos (tomador e prestador de serviços) finalizam a
         * ordem de serviços, liberando assim, o faturamento para o usuário prestador de
         * serviços.
         */

        Usuario usuarioPrestadorDeServico = this.criarUsuario();
        Usuario usuarioTomadorDeServico   = this.criarUsuario();

        usuarioPrestadorDeServico.setNome("Lorena Pietra Campos");
        usuarioPrestadorDeServico.setTipoUsuario(TipoUsuario.PrestadorDeServicos);

        usuarioTomadorDeServico.setNome("Silvana Elza Allana Campos");
        usuarioTomadorDeServico.setTipoUsuario(TipoUsuario.TomadorDeServicos);

        Servico servico = this.criarServico();
        servico.setId(1);
        servico.setNome("Mario Encanamentos");
        servico.setPrestador(usuarioPrestadorDeServico);
        servico.setTomador(usuarioTomadorDeServico);

        usuarioRepositorio.inserir(usuarioPrestadorDeServico);
        usuarioRepositorio.inserir(usuarioTomadorDeServico);
        servicoRepositorio.addServico(servico);

        OrderService orderService = new OrderService(servico);
        double valor = orderService.faturamento();

        Assertions.assertTrue(servico.getStatus() == ServicoStatus.FINALIZADO);
        Assertions.assertEquals(valor, servico.getValor());
    }

    @Test
    public void usuarioTomadorDeServicosPossuiFavoritos() {
        /**
         * TC031 (RF017)
         * Lucas Gomes
         *
         * O sistema irá disponibilizar a opção dos anúncios favoritos do Tomador. O "favorito"
         * pode ser dado tanto na pesquisa quanto pela visualização das Ordens de Serviço.
         * Favorito por ordem de serviço no momento da contratação do serviço.
         */

        Usuario tomadorDeServicos = this.criarUsuario();
        tomadorDeServicos.setNome("Sueli Allana Corte Real");

        Usuario prestadorDeServicos1 = this.criarUsuario();
        prestadorDeServicos1.setNome("Manuel Márcio Pedro Henrique Almada");

        Usuario prestadorDeServicos2 = this.criarUsuario();
        prestadorDeServicos2.setNome("Carlos Eduardo Igor Costa");

        tomadorDeServicos.adicionarFavorito(prestadorDeServicos1);
        tomadorDeServicos.adicionarFavorito(prestadorDeServicos2);

        Assertions.assertFalse(tomadorDeServicos.getFavoritos().isEmpty());
        Assertions.assertTrue(tomadorDeServicos.getFavoritos().contains(prestadorDeServicos1));
        Assertions.assertTrue(tomadorDeServicos.getFavoritos().contains(prestadorDeServicos2));
    }

    private Usuario criarUsuario() {
        
        Usuario usuario = new Usuario();

        usuario.setNome("John");
        usuario.setSobrenome("Doe");
        usuario.setSexo("Masculino");
        usuario.setCpf("11111111111");
        usuario.setDataNascimento("01/01/2001");
        usuario.setEndereco("51011-00 Avenida Boa Viagem, Pina, Recife-PE");
        usuario.setEmail("johndoe@example.com");
        usuario.setSenha("Johndoe@123");

        return usuario;
   }

    private Servico criarServico() {

        Servico servico = new Servico();

        servico.setId(1);
        servico.setNome("Nail Desginer");
        servico.setCategoria("Higiene e Saúde Pessoal");
        servico.setValor(200.00);
        servico.setDisponibilidade("Segunda à Sábado, 08:00Hrs às 16:00Hrs");
        servico.setRestricoes("Não trabalha com animais");
        servico.setDiferenciais("Adultos: Alongamento de unhas, Unhas de gel, Unhas de porcelana, Unhas de fibra de vidro");

        return servico;
    }
}