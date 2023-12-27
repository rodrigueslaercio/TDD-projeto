import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositorio {

    private List<Usuario> usuarios = new ArrayList<>();

    public void inserir(Usuario usuario) {
        usuarios.add(usuario);
    }

    public int obterIndice(Usuario usuarioAlvo) {
        
        for (int counter = 0; counter < this.usuarios.size(); counter++) {
            if (this.usuarios.get(counter).equals(usuarioAlvo)) {
                return counter;
            }
        }

        return -1;
    }

    public void atualizar(int indice, Usuario novoUsuario) {

        this.usuarios.set(indice, novoUsuario);
    }

    public Usuario obterUsuario(int indice) {

        return this.usuarios.get(indice);
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
