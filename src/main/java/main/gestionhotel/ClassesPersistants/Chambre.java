package main.gestionhotel.ClassesPersistants;

public class Chambre {
    private int id_chmbr;
    private int id_type;
    private int num_chmbr;
    private String desq_chmbr;
    private boolean dispo_chmbr;

    public Chambre(int id_chmbr, int id_type, int num_chmbr, String desq_chmbr, boolean dispo_chmbr) {
        this.id_chmbr = id_chmbr;
        this.id_type=id_type;
        this.num_chmbr = num_chmbr;
        this.desq_chmbr = desq_chmbr;
        this.dispo_chmbr = dispo_chmbr;
    }

    public Chambre() {
    }

    public int getId_chmbr() {
        return id_chmbr;
    }

    public int getId_type() {
        return id_type;
    }

    public void setId_type(int id_type) {
        this.id_type = id_type;
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
        return "Chambre{" +
                "id_chmbr=" + id_chmbr +
                ", id_type=" + id_type +
                ", num_chmbr=" + num_chmbr +
                ", desq_chmbr='" + desq_chmbr + '\'' +
                ", dispo_chmbr=" + dispo_chmbr +
                '}';
    }
}
