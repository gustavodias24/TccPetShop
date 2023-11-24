package benicio.soluces.tccpetshop.model;

import java.util.ArrayList;
import java.util.List;

public class StoreModel {

    String descricao;
    List<Integer> produtos= new ArrayList<>();
    List<String> servicos = new ArrayList<>();
    String image;
    String nomeLoja;
    String agenda;
    int id;
    int raio;

    public StoreModel() {
    }

    public String getServicosListaado(){
        StringBuilder stringBuilder = new StringBuilder();
        for ( String s : servicos){
            stringBuilder.append(s).append("\n");
        }
        return  stringBuilder.toString();
    }
    public StoreModel(String nomeLoja, String agenda, int id, int raio) {
        this.nomeLoja = nomeLoja;
        this.agenda = agenda;
        this.id = id;
        this.raio = raio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }

    public String getNomeLoja() {
        return nomeLoja;
    }

    public void setNomeLoja(String nomeLoja) {
        this.nomeLoja = nomeLoja;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRaio() {
        return raio;
    }

    public void setRaio(int raio) {
        this.raio = raio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Integer> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Integer> produtos) {
        this.produtos = produtos;
    }

    public List<String> getServicos() {
        return servicos;
    }

    public void setServicos(List<String> servicos) {
        this.servicos = servicos;
    }
}
