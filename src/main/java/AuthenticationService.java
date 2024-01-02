public class AuthenticationService {

  private final UsuarioRepositorio usuarioRepositorio;

  public AuthenticationService(UsuarioRepositorio usuarioRepositorio)
  {
    this.usuarioRepositorio = usuarioRepositorio;
  }

  public boolean authenticate(String email, String senha) {

    Usuario authenticatedUser = this.usuarioRepositorio.findByEmail(email);

    if (authenticatedUser == null)                   return false;
    if (!authenticatedUser.getSenha().equals(senha)) return false;

    return true;
  }
}
