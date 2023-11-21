package benicio.soluces.tccpetshop.model;

import java.util.ArrayList;
import java.util.List;

public class PedidoCompraModel {

    List<ProductModel> produtosComprados = new ArrayList<>();
    String id, idCliente;

    public PedidoCompraModel() {
    }

    public PedidoCompraModel(List<ProductModel> produtosComprados, String id, String idCliente) {
        this.produtosComprados = produtosComprados;
        this.id = id;
        this.idCliente = idCliente;
    }

    public List<ProductModel> getProdutosComprados() {
        return produtosComprados;
    }

    public void setProdutosComprados(List<ProductModel> produtosComprados) {
        this.produtosComprados = produtosComprados;
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
}
