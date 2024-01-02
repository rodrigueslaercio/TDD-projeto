import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CarrinhoCompras {

  private final List<Servico> servicos;

  public CarrinhoCompras() {
    this.servicos = new ArrayList<Servico>();
  }

  public void add(Servico service) {
    this.servicos.add(service);
  }

  public void remove(Servico service) {
    this.servicos.remove(service);
  }

  public List<Servico> getAllServices() {
    return this.servicos;
  }

  public void clear() {
    this.servicos.clear();
  }

  public boolean contains(Servico service) {
    return this.servicos.contains(service);
  }
}
