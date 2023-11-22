package benicio.soluces.tccpetshop.model;

public class StoreModel {

    String nomeLoja;
    String agenda;
    int id;
    int raio;

    public StoreModel() {
    }

    public StoreModel(String nomeLoja, String agenda, int id, int raio) {
        this.nomeLoja = nomeLoja;
        this.agenda = agenda;
        this.id = id;
        this.raio = raio;
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
}
