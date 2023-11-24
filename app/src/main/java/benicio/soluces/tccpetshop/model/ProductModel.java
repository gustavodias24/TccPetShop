package benicio.soluces.tccpetshop.model;

public class ProductModel {
    String nome, imagem,categoria, descricao;
    int id, lojaId;

    int quantiadeComprada = 1;
    float valor;
    int quanti, quantiVenda;

    public ProductModel() {
    }

    public ProductModel(String nome, String imagem, String categoria, String descricao, int id, int lojaId, float valor, int quanti, int quantiVenda) {
        this.nome = nome;
        this.imagem = imagem;
        this.categoria = categoria;
        this.descricao = descricao;
        this.id = id;
        this.lojaId = lojaId;
        this.valor = valor;
        this.quanti = quanti;
        this.quantiVenda = quantiVenda;
    }

    public int getQuantiadeComprada() {
        return quantiadeComprada;
    }

    public void setQuantiadeComprada(int quantiadeComprada) {
        this.quantiadeComprada = quantiadeComprada;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantiVenda() {
        return quantiVenda;
    }

    public void setQuantiVenda(int quantiVenda) {
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

    @Override
    public String toString() {
        return  "Nome:\n" + nome + "\n\n" +
                "Categoria:\n" + categoria + "\n\n" +
                "Descricao:\n" + descricao + "\n\n" +
                "Valor:\nR$"+ valor +  "\n\n" +
                "Estoque:\n" + quanti + "\n\n" +
                "Quantidade vendida:\n" + quantiVenda;
    }
}
