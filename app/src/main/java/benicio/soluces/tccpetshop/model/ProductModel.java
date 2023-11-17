package benicio.soluces.tccpetshop.model;

public class ProductModel {
    String nome, lojaId, id;
    float valor;
    int categoria, quanti, quanti_vend;

    public ProductModel() {
    }

    public ProductModel(String nome, String lojaId, String id, float valor, int categoria, int quanti, int quanti_vend) {
        this.nome = nome;
        this.lojaId = lojaId;
        this.id = id;
        this.valor = valor;
        this.categoria = categoria;
        this.quanti = quanti;
        this.quanti_vend = quanti_vend;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLojaId() {
        return lojaId;
    }

    public void setLojaId(String lojaId) {
        this.lojaId = lojaId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getQuanti() {
        return quanti;
    }

    public void setQuanti(int quanti) {
        this.quanti = quanti;
    }

    public int getQuanti_vend() {
        return quanti_vend;
    }

    public void setQuanti_vend(int quanti_vend) {
        this.quanti_vend = quanti_vend;
    }
}
