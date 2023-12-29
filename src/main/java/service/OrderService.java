package service;

import entidades.Servico;
import entidades.ServicoStatus;
import lombok.Data;

@Data
public class OrderService {
  private final Servico servico;

  public double faturamento() {
    servico.setStatus(ServicoStatus.FINALIZADO);
    return servico.getValor();
  }
}
