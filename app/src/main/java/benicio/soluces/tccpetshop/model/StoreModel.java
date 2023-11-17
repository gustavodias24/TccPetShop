package benicio.soluces.tccpetshop.model;

public class StoreModel {

    String nomeLoja, id;
    float raio;

    public StoreModel() {
    }

    public StoreModel(String nomeLoja, String id, float raio) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getRaio() {
        return raio;
    }

    public void setRaio(float raio) {
        this.raio = raio;
    }
}
