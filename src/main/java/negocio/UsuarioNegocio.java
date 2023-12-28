package negocio;
import java.lang.reflect.Field;
import java.util.Objects;

import entidades.TipoUsuario;
import entidades.Usuario;
import repositorio.UsuarioRepositorio;

public class UsuarioNegocio {

    private UsuarioRepositorio usuarioRepositorio;

    public UsuarioNegocio (UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public Usuario cadastrar(Usuario usuario) {
        // Verifica se existe algum atributo como null no objeto instanciado
        for (Field field : usuario.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            try {
                if (Objects.isNull(field.get(usuario))) {
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        if (!verificarCaracteresSenha(usuario)) {
            return null;
        }

        if (!verificarSenhaLength(usuario)) {
            return null;
        }

        if (usuarioRepositorio.verificarCpf(usuario.getCpf())) {
            return null;
        }

        usuario.setTipoUsuario(TipoUsuario.TomadorDeServicos);
        this.usuarioRepositorio.inserir(usuario);
        return  usuario;
    }


    private boolean verificarCaracteresSenha(Usuario usuario) {
        int countLetter, countDigit, countSpecial;
        countLetter = 0;
        countDigit = 0;
        countSpecial = 0;

        for (char c : usuario.getSenha().toCharArray()) {
            if (Character.isUpperCase(c)) {
                countLetter++;
            } else if (Character.isDigit(c)) {
                countDigit++;
            } else {
                countSpecial++;
            }
        }

        return countLetter >= 1 && countDigit >= 1 && countSpecial >= 1;
    }

    private boolean verificarSenhaLength(Usuario usuario) {
        return usuario.getSenha().length() >= 8 && usuario.getSenha().length() <= 32;
    }

}
