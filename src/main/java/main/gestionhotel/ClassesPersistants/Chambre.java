package main.gestionhotel.ClassesPersistants;

public class Chambre {
  private int id_chmbr;
  private int num_chmbr;
  private String desq_chmbr;
  private boolean dispo_chmbr;
  private Type_Chambre type_chambre = new Type_Chambre();

  public Chambre(int id_chmbr, int num_chmbr, String desq_chmbr, boolean dispo_chmbr) {
    this.id_chmbr = id_chmbr;
    this.num_chmbr = num_chmbr;
    this.desq_chmbr = desq_chmbr;
    this.dispo_chmbr = dispo_chmbr;
  }

  public Chambre(int num_chmbr, String desq_chmbr, boolean dispo_chmbr) {
    this.num_chmbr = num_chmbr;
    this.desq_chmbr = desq_chmbr;
    this.dispo_chmbr = dispo_chmbr;
  }

  public Chambre() {}

  public Type_Chambre getType_chambre() {
    return type_chambre;
  }

  public void setType_chambre(Type_Chambre type_chambre) {
    this.type_chambre = type_chambre;
  }

  public int getId_chmbr() {
    return id_chmbr;
  }


  public void setId_chmbr(int id_chmbr) {
    this.id_chmbr = id_chmbr;
  }

  public int getNum_chmbr() {
    return num_chmbr;
  }

  public void setNum_chmbr(int num_chmbr) {
    this.num_chmbr = num_chmbr;
  }

  public String getDesq_chmbr() {
    return desq_chmbr;
  }

  public void setDesq_chmbr(String desq_chmbr) {
    this.desq_chmbr = desq_chmbr;
  }

  public boolean isDispo_chmbr() {
    return dispo_chmbr;
  }

  public void setDispo_chmbr(boolean dispo_chmbr) {
    this.dispo_chmbr = dispo_chmbr;
  }

  @Override
  public String toString() {
    return "Chambre{"
        + ", num_chmbr="
        + num_chmbr
        + ", desq_chmbr='"
        + desq_chmbr
        + '\''
        + ", dispo_chmbr="
        + dispo_chmbr
        + '}';
  }
}
