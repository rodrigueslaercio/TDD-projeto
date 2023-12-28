public class PagamentoNegocio {

    private PagamentoRepositorio pr;

    public PagamentoNegocio(PagamentoRepositorio pr) {
        this.pr = pr;
    }

    public boolean realizarPagamento(PagamentoPix pix, Servico servico, Usuario usuario) {
        if (pix != null) {
            pr = new PagamentoRepositorio();

            if (pix.getValor().equals(servico.getValor())) {
                pix.setUsuario(usuario);
                pix.setServico(servico);

                pr.inserirPagamentoPix(pix);

                return true;
            }
        }

        return false;
    }

    public boolean realizarPagamentoCartao(PagamentoCartao cartao, Servico servico, Usuario usuario) {
        if (cartao != null) {
            pr = new PagamentoRepositorio();

            if (cartao.getValor().equals(servico.getValor())) {
                cartao.setUsuario(usuario);
                cartao.setServico(servico);

                pr.inserirPagamentoCartao(cartao);

                return true;
            }
        }

        return false;
    }
}
