package main.gestionhotel.ClassesPersistants;

public class Client {
    private int id_cl;
    private String CIN_cl;
    private String nom_cl;
    private String prenom_cl;
    private String email_cl;
    private String numtel_cl;
    private String adresse_cl;

    public Client(int id_cl, String CIN_cl, String nom_cl, String prenom_cl, String email_cl, String numtel_cl, String adresse_cl) {
        this.id_cl = id_cl;
        this.CIN_cl = CIN_cl;
        this.nom_cl = nom_cl;
        this.prenom_cl = prenom_cl;
        this.email_cl = email_cl;
        this.numtel_cl = numtel_cl;
        this.adresse_cl = adresse_cl;
    }

    public Client(String CIN_cl, String nom_cl, String prenom_cl, String email_cl, String numtel_cl, String adresse_cl) {
        this.CIN_cl = CIN_cl;
        this.nom_cl = nom_cl;
        this.prenom_cl = prenom_cl;
        this.email_cl = email_cl;
        this.numtel_cl = numtel_cl;
        this.adresse_cl = adresse_cl;
    }

    public Client() {

    }


    public int getId_cl() {
        return id_cl;
    }

    public void setId_cl(int id_cl) {
        this.id_cl = id_cl;
    }

    public String getCIN_cl() {
        return CIN_cl;
    }

    public void setCIN_cl(String CIN_cl) {
        this.CIN_cl = CIN_cl;
    }

    public String getNom_cl() {
        return nom_cl;
    }

    public void setNom_cl(String nom_cl) {
        this.nom_cl = nom_cl;
    }

    public String getPrenom_cl() {
        return prenom_cl;
    }

    public void setPrenom_cl(String prenom_cl) {
        this.prenom_cl = prenom_cl;
    }

    public String getEmail_cl() {
        return email_cl;
    }

    public void setEmail_cl(String email_cl) {
        this.email_cl = email_cl;
    }

    public String getNumtel_cl() {
        return numtel_cl;
    }

    public void setNumtel_cl(String numtel_cl) {
        this.numtel_cl = numtel_cl;
    }

    public String getAdresse_cl() {
        return adresse_cl;
    }

    public void setAdresse_cl(String adresse_cl) {
        this.adresse_cl = adresse_cl;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id_cl=" + id_cl +
                ", CIN_cl='" + CIN_cl + '\'' +
                ", nom_cl='" + nom_cl + '\'' +
                ", prenom_cl='" + prenom_cl + '\'' +
                ", email_cl='" + email_cl + '\'' +
                ", numtel_cl='" + numtel_cl + '\'' +
                ", adresse_cl='" + adresse_cl + '\'' +
                '}';
    }
}
