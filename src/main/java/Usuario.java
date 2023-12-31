import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String nome;
    private String sobrenome;
    private String sexo;
    private String cpf;
    private String dataNascimento;
    private String endereco;
    private String email;
    private String senha;
    private String celular;
    private TipoUsuario tipoUsuario;
    private CarrinhoCompras carrinhoCompras;
    private List<Usuario> favoritos;

    public Usuario() {
        this.carrinhoCompras = new CarrinhoCompras();
        this.favoritos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public CarrinhoCompras getCarrinhoCompras() {
        return carrinhoCompras;
    }

    public List<Usuario> getFavoritos() { return favoritos; }

    public void adicionarFavorito(Usuario favorito) {
        this.favoritos.add(favorito);
    }
}
