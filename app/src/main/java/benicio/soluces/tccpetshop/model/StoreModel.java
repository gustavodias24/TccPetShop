package benicio.soluces.tccpetshop.model;

public class StoreModel {

    String nomeLoja;

    int id;
    int raio;

    public StoreModel() {
    }

    public StoreModel(String nomeLoja, int id, int raio) {
        this.nomeLoja = nomeLoja;
        this.id = id;
        this.raio = raio;
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
}
