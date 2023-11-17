package benicio.soluces.tccpetshop.model;

public class ProductModel {
    String nome, imagem,categoria;
    int id, lojaId;
    float valor;
    int quanti, quantiVenda;

    public ProductModel() {
    }

    public ProductModel(String nome, int lojaId, int id, String imagem, float valor, String categoria, int quanti, int quantiVenda) {
        this.nome = nome;
        this.lojaId = lojaId;
        this.id = id;
        this.imagem = imagem;
        this.valor = valor;
        this.categoria = categoria;
        this.quanti = quanti;
        this.quantiVenda = quantiVenda;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getLojaId() {
        return lojaId;
    }

    public void setLojaId(int lojaId) {
        this.lojaId = lojaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getQuanti() {
        return quanti;
    }

    public void setQuanti(int quanti) {
        this.quanti = quanti;
    }

    public int getquantiVenda() {
        return quantiVenda;
    }

    public void setquantiVenda(int quantiVenda) {
        this.quantiVenda = quantiVenda;
    }
}
