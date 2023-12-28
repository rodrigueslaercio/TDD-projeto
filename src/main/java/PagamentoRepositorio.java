import java.util.ArrayList;
import java.util.List;

public class PagamentoRepositorio {

    private List<PagamentoPix> pagamentoPixList = new ArrayList<>();
    public void inserirPagamentoPix(PagamentoPix pix) {
        pagamentoPixList.add(pix);
    }
}
