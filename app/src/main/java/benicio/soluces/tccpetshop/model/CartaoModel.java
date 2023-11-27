package benicio.soluces.tccpetshop.model;

public class CartaoModel {
    String id, idCliente, tipo, numeroCartao, codigoSeguranca, dataVencimento, nomeCartao;

    public CartaoModel() {
    }

    public CartaoModel(String id, String idCliente, String tipo, String numeroCartao, String codigoSeguranca, String dataVencimento, String nomeCartao) {
        this.id = id;
        this.idCliente = idCliente;
        this.tipo = tipo;
        this.numeroCartao = numeroCartao;
        this.codigoSeguranca = codigoSeguranca;
        this.dataVencimento = dataVencimento;
        this.nomeCartao = nomeCartao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getCodigoSeguranca(String s) {
        return codigoSeguranca;
    }

    public void setCodigoSeguranca(String codigoSeguranca) {
        this.codigoSeguranca = codigoSeguranca;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getNomeCartao() {
        return nomeCartao;
    }

    public void setNomeCartao(String nomeCartao) {
        this.nomeCartao = nomeCartao;
    }
}
