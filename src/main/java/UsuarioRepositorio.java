import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositorio {

    private List<Usuario> usuarios = new ArrayList<>();

    public void inserir(Usuario usuario) {
        usuarios.add(usuario);
    }

    public boolean verificarCpf(String cpf) {
        for(Usuario usuario : this.usuarios) {
            if (usuario.getCpf().equals(cpf)) {
                return true;
            }
        }

        return false;
    }
 }
