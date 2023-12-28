import entidades.TipoUsuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entidades.Usuario;
import negocio.UsuarioNegocio;
import repositorio.UsuarioRepositorio;
import service.AuthenticationService;
import service.ValidationService;

public class TesteUsuario {
    private Usuario usuario;
    private UsuarioRepositorio usuarioRepositorio;
    private UsuarioNegocio usuarioNegocio;

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

        if (ValidationService.validarEmail(novoEmail)) {

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
}