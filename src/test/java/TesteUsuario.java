import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import service.ValidationService;

public class TesteUsuario {
    Usuario usuario;
    UsuarioRepositorio usuarioRepositorio;
    UsuarioNegocio usuarioNegocio;

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
        usuario.setTipo(Tipo.None);

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

    private Usuario criarUsuario() {
        
        Usuario usuario = new Usuario();

        usuario.setNome("Mark");
        usuario.setSobrenome("Doe");
        usuario.setSexo("M");
        usuario.setCpf("11111111111");
        usuario.setDataNascimento("01/01/2001");
        usuario.setEndereco("51011-00 Avenida Boa Viagem, Pina, Recife-PE");
        usuario.setEmail("markdoe@exampe.com");
        usuario.setSenha("Markdoe123@");

        return usuario;
   }

}