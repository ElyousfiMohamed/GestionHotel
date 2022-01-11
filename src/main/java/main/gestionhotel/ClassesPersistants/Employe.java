package main.gestionhotel.ClassesPersistants;

public class Employe {
  private int id;
  private String cin;
  private String nom;
  private String prenom;
  private String telephone;
  private String email;
  private String password;
  private String fonction;

  public Employe() {}

  public Employe(
      int id,
      String cin,
      String nom,
      String prenom,
      String telephone,
      String email,
      String password,
      String fonction) {
    this.id = id;
    this.cin = cin;
    this.nom = nom;
    this.prenom = prenom;
    this.telephone = telephone;
    this.email = email;
    this.password = password;
    this.fonction = fonction;
  }

  public Employe(
      int id,
      String cin,
      String nom,
      String prenom,
      String telephone,
      String email,
      String fonction) {
    this.id = id;
    this.cin = cin;
    this.nom = nom;
    this.prenom = prenom;
    this.telephone = telephone;
    this.email = email;
    this.fonction = fonction;
  }

  public Employe(
      String cin,
      String nom,
      String prenom,
      String telephone,
      String email,
      String password,
      String fonction) {
    this.cin = cin;
    this.nom = nom;
    this.prenom = prenom;
    this.telephone = telephone;
    this.email = email;
    this.password = password;
    this.fonction = fonction;
  }

  public int getId() {
    return this.id;
  }

  public String getCin() {
    return this.cin;
  }

  public String getNom() {
    return this.nom;
  }

  public String getPrenom() {
    return this.prenom;
  }

  public String getTelephone() {
    return this.telephone;
  }

  public String getEmail() {
    return this.email;
  }

  public String getPassword() {
    return this.password;
  }

  public String getFonction() {
    return this.fonction;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setCin(String cin) {
    this.cin = cin;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setFonction(String fonction) {
    this.fonction = fonction;
  }

  @Override
  public String toString() {
    return "Employe{"
        + "id="
        + id
        + ", cin='"
        + cin
        + '\''
        + ", nom='"
        + nom
        + '\''
        + ", prenom='"
        + prenom
        + '\''
        + ", telephone='"
        + telephone
        + '\''
        + ", email='"
        + email
        + '\''
        + ", fonction='"
        + fonction
        + '\''
        + '}';
  }
}
