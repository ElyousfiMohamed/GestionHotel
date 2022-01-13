package main.gestionhotel.ClassesPersistants;

public class Type_Chambre {
  private int id_type;
  private String intitule;
  private int capacité;
  private float prix;

  public Type_Chambre(int id_type, String intitule, int capacité, float prix) {
    this.id_type = id_type;
    this.intitule = intitule;
    this.capacité = capacité;
    this.prix = prix;
  }

  public Type_Chambre(String intitule, int capacité, float prix) {
    this.intitule = intitule;
    this.capacité = capacité;
    this.prix = prix;
  }


  public Type_Chambre() {}

  public int getId_type() {
    return id_type;
  }

  public void setId_type(int id_type) {
    this.id_type = id_type;
  }

  public String getIntitule() {
    return intitule;
  }

  public void setIntitule(String intitule) {
    this.intitule = intitule;
  }

  public int getCapacité() {
    return capacité;
  }

  public void setCapacité(int capacité) {
    this.capacité = capacité;
  }

  public float getPrix() {
    return prix;
  }

  public void setPrix(float prix) {
    this.prix = prix;
  }

  @Override
  public String toString() {
    return "intitule='"
        + intitule
        + '\''
        + ", capacité="
        + capacité
        + ", prix="
        + prix
        + '}';
  }
}
