import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class OrderService {
  private final Servico servico;

  public OrderService(Servico servico) {
    this.servico = servico;
  }

  public double faturamento() {
    servico.setStatus(ServicoStatus.FINALIZADO);
    return servico.getValor();
  }
}
