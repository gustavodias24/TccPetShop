package benicio.soluces.tccpetshop.model;

public class OrderModel {
    String data,pedro,produtoNome,quant,storeName,userId,id, status;

    public OrderModel() {
    }

    public OrderModel(String status, String data, String pedro, String produtoNome, String quant, String storeName, String userId, String id) {
        this.data = data;
        this.status = status;
        this.pedro = pedro;
        this.produtoNome = produtoNome;
        this.quant = quant;
        this.storeName = storeName;
        this.userId = userId;
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPedro() {
        return pedro;
    }

    public void setPedro(String pedro) {
        this.pedro = pedro;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProdutoNome() {
        return produtoNome;
    }

    public void setProdutoNome(String produtoNome) {
        this.produtoNome = produtoNome;
    }

    public String getQuant() {
        return quant;
    }

    public void setQuant(String quant) {
        this.quant = quant;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
