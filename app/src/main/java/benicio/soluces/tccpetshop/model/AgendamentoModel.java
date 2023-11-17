package benicio.soluces.tccpetshop.model;

public class AgendamentoModel {
    String data, id, idCliente, nomePet, estabelecimento;

    public AgendamentoModel() {
    }

    public AgendamentoModel(String data, String id, String idCliente, String nomePet, String estabelecimento) {
        this.data = data;
        this.id = id;
        this.idCliente = idCliente;
        this.nomePet = nomePet;
        this.estabelecimento = estabelecimento;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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

    public String getNomePet() {
        return nomePet;
    }

    public void setNomePet(String nomePet) {
        this.nomePet = nomePet;
    }

    public String getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(String estabelecimento) {
        this.estabelecimento = estabelecimento;
    }
}
